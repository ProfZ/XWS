'use strict';

/**
 * @ngdoc overview
 * @name invoiceClientApp
 * @description
 * # invoiceClientApp
 *
 * Main module of the application.
 */
var app = angular
 .module('invoiceClientApp', [
  'ngAnimate',
  'ngCookies',
  'ngResource',
  'ngRoute',
  'ngSanitize',
  'ngTouch',
  'main',
  'about',
  'invoices',
  'invoice'//,
  //'user'//,
  // 'resource.user'
  ])
 .config(function ($routeProvider) {
  $routeProvider
  .when('/', {
    templateUrl: 'views/invoice-list.html',
    controller: 'invoicesListCtrl'
  })
  .when('/invoice-list/:invoiceId', {
    templateUrl: 'views/invoice-item1.html',
    controller: 'invoicesListCtrl'
  })
  .when('/invoice/:invoiceId', {
    templateUrl: 'views/invoice1.html',
    controller: 'invoiceCtrl'
  })
  .when('/login', {
    templateUrl: 'views/login.html',
    controller: 'userCtrl'
  })
   .when('/logout', {
    templateUrl: 'views/login.html',
    controller: 'userCtrl'
  })
  .otherwise({
    redirectTo: '/'
  });
});
app.service("userService", function() {
  this.user = {pib: ""};
  this.invId = "";
  this.itemId = "";
  this.value0 = 0;
  this.isEnable1 = false;
  this.znak = '';
  this.kol0 = 0; 
  this.rabat0 = 0;
  this.isRabat1 = false;
  this.filterOption1 = false;
  this.dateCurrency1 = false;
  this.kg1 = false; 
  this.kgNaziv2 = false;
  this.value2 = '';
})

app.filter('filtriranjeFak', function ( ) {
      return function ( iznos, value, znak, nijeiznos, filterOp, dateCurrency ) {
        if(filterOp){
          if(!nijeiznos){
            if(dateCurrency){

            }else{

            }
          }else{
             var filteredItems = []
              angular.forEach(iznos, function ( item ) {
                if(znak == 'manje'){
                  if ( item.zaglavljeFakture.iznosZaUplatu < value ) {
                    filteredItems.push(item);
                  }
                }else if(znak == 'vece'){
                  if ( item.zaglavljeFakture.iznosZaUplatu > value ) {
                    filteredItems.push(item);
                  }
                }else if(znak == ''){
                  return iznos;
                }else if(znak == 'jednako'){
                  if ( item.zaglavljeFakture.iznosZaUplatu == value ) {
                    filteredItems.push(item);
                  }
                }
            });
          }
          return filteredItems;
      }else{
        return iznos;
      }
      }
  })


app.filter('filtriranjeStavki', function ( ) {
      return function ( iznos, value, znak, enable, rabat, kgNaziv, kg, stringVal ) {
        if(enable){
          if(kgNaziv){
            if(kg){
                var filteredItems = []
                angular.forEach(iznos, function ( item ) {
                    if ( item.jedinicaMere.includes(stringVal)) {
                      filteredItems.push(item);
                    }
              });
            }else{
                var filteredItems = []
                angular.forEach(iznos, function ( item ) {
                    if ( item.nazivRobeUsluge.includes(stringVal)) {
                      filteredItems.push(item);
                    }
              });
            }
          }else{
            if(rabat){
               var filteredItems = []
                angular.forEach(iznos, function ( item ) {
                  if(znak == 'manje'){
                    if ( item.umanjenoZaRabat < value ) {
                      filteredItems.push(item);
                    }
                  }else if(znak == 'vece'){
                    if ( item.umanjenoZaRabat > value ) {
                      filteredItems.push(item);
                    }
                  }else if(znak == ''){
                    return iznos;
                  }else if(znak == 'jednako'){
                    if ( item.umanjenoZaRabat == value ) {
                      filteredItems.push(item);
                    }
                  }
              });
            }else{
                var filteredItems = []
                angular.forEach(iznos, function ( item ) {
                  if(znak == 'manje'){
                    if ( item.kolicina < value ) {
                      filteredItems.push(item);
                    }
                  }else if(znak == 'vece'){
                    if ( item.kolicina > value ) {
                      filteredItems.push(item);
                    }
                  }else if(znak == ''){
                    return iznos;
                  }else if(znak == 'jednako'){
                    if ( item.kolicina == value ) {
                      filteredItems.push(item);
                    }
                  }
            });
          }
        }
          return filteredItems;
      }else{
        return iznos;
      }
      }
  })

app.filter('dateLess', function ( ) {
      return function ( iznos, value, enable ) {
         if(enable){
           var filteredItems = []
            angular.forEach(iznos, function ( item ) {
              if ( item.zaglavljeFakture.racun.datumRacuna <= value ) {
                  filteredItems.push(item);
              }
          });
          return filteredItems;
        }else{
           return iznos;
        }
    }
  })
 //tricky deo
//  .factory('authHttpResponseInterceptor',['$q','$location',function($q,$location){// fabrika koja pravi interceptor
//   return {
//     response: function(response){//ako smo dobili noramalan odgovor vratimo taj odgovor
//       if (response.status === 401) {
//         console.log("Response 401");
//       }
//       return response || $q.when(response);
//     },
//     responseError: function(rejection, x, y) {//ako smo dobili gresku
//       if (rejection.status === 401) {//ako je greska 401 (korisnik nije prijavljen na sistem)
//         console.log("Response Error 401",rejection);
//         $location.path('/login');//redirektujemo se na login
//       }
//       return $q.reject(rejection);//i odbacimo zahtev
//     }
//   }
// }])
//  .config(['$httpProvider',function($httpProvider) {//interceptor dodamo u stek interceptora
//   $httpProvider.interceptors.push('authHttpResponseInterceptor');
// }])
 //imamo problem: koristimo $resource koji je wrapper oko $http
 //kada pristigne odgovor prvo se uradi transformResponse nad odgovorom
 //pa se onda presretne odgovor.
 //uz gresku sa servera pristigne tekstualni opis greske
 //"Not logged in"
 //ovaj opis greske ne moze da se konvertuje u JSON (pogeldaj slajd $resource - napisano sitnim slovima)
 //zbog toga moramo da izmenimo transformResponce 
 // .run(['$http','$location',//to radimo u run, jer se izvrsava pre svega ostalog
 //  function($http, $location) {
 //    var parseResponse = function(response, headers, status) {//ova funkcija proba da konvertuje pristigli odgovor u JSON
 //      if(status===401){//ako je odgovor neautorizovan radimo redirekciju
 //        $location.path('login');
 //      }
 //      else{
 //        return response;//inace vratimo odgovor
 //      }
 //    };

 //    $http.defaults.transformResponse.unshift(parseResponse);//ovu funkciju stavimo na pocetak niza transformer funkcija
 //  }
 //  ])
//  .controller('appCtrl', function($scope, User, $log, $location, $modal){
//   $scope.logout = User.logout;
//   $scope.isLoginPage = function () {
//     return $location.path() === '/login';
//   };
//   $scope.about = function (size) {
//     var modalInstance = $modal.open({
//       templateUrl: 'views/about.html',
//       controller: 'AboutCtrl',
//       size: size,
//     });
//   };
// });

