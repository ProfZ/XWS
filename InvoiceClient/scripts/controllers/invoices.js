'use strict';

 angular.module('invoices', ['resource.invoice',
 	'angular-md5'])

 .controller('invoicesListCtrl', function (Invoice, $scope, $location, md5, $log, $http, userService) {
	$scope.invoices = {};
	$http.get('http://localhost:8080/XWS_AMAA_Firma/api/partneri/'+userService.user.pib+'/fakture/')
			.success(function (data){
				$scope.invoices = data;
			});	
	//$log.info($scope.invoices.length);//0
	//kada smo kliknuli na red u tabeli prelazimo na stranicu za editovanje fakture sa zadatim id-om
 	$scope.insertOrEditInvoice = function (invoice) {
 		if(invoice){
 			$location.path('/invoice/'+invoice.zaglavljeFakture.idPoruke);
 		}
 		else{
			$location.path('/invoice/new');
 		}
 	}
 });
