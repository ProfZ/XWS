/*'use strict';

angular.module('user', ['resource.user'])

.controller('userCtrl', function ($scope, User, $log, $location) {
	$scope.login = function () {
		var promise = User.login($scope.user);
		$scope.user = {};
		promise.then(function (data) {
			$log.info(data);
			$location.path('invoice-list');
		});
	}
});*/
app.controller('userCtrl',  ['$scope', function($scope, $routeProvider)
{
	$scope.user = {
			username: '',
			password: ''		
		};

	$scope.login = function(){
		$scope.user.username = document.getElementById("username");
		$scope.user.password = document.getElementById("password");
		$routeProvider = '/index.html';
	}; 
	$scope.logout = function(){
		$scope.user.username = ''; 
		$scope.user.password = '';
		$routeProvider = '#/login.html';
	};
	$scope.logged = function () {
		// if ($scope.user == null)
		// 	return false;
  //   	return $scope.user.username !=  '';
  return true;
  	};
}]);