app.controller('userCtrl',  ['$scope', '$location', 'userService', function($scope, $location, userService) {
	$scope.user = function() {
		return userService.user;
	}
	$scope.login = function(){
		userService.user.pib = document.getElementById('pib').value;
		$location.path('/index.html');
	}; 
	$scope.logout = function(){
		userService.user.pib = '';
		$location.path('/login.html');
	};
	$scope.logged = function () {
		if (userService.user == null) {
			return false;
		}
    	return userService.user.pib !=  '';
  	};
}]);