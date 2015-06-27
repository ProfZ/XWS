'use strict';

angular.module('invoiceItem', [])

.controller('invoiceItemCtrl', function ($scope, $modalInstance, invoiceItem, $http, userService, $location, $window) {
	if(invoiceItem){
		$scope.invoiceItem = invoiceItem;
		
	}
	else{
		$scope.invoiceItem = {};	
	}
	// $scope.getItemId = function() {
	// 	return userService.itemId;
	// }
	// $scope.setItemId = function(invoiceItemId){
	// 	userService.itemId = invoiceItemId;
	// }; 
	$scope.ok = function () {
		userService.itemId = $scope.invoiceItem.redniBroj;

		// $modalInstance.close({'invoiceItem':$scope.invoiceItem,
		// 						'action':'updateStavka'
		// 					});
		if($scope.invoiceItem.redniBroj){
			$scope.putInvoiceItem();
		}else{
			$scope.postInvoiceItem();
		}
	};

	$scope.cancel = function () {
		//$scope.invoiceItem = $scope.oldItem;
		$window.location.reload();
		//$modalInstance.close();
	};

	$scope.delete = function () {
		userService.itemId = $scope.invoiceItem.redniBroj;
		$scope.deleteInvoiceItem();
	};
	
	$scope.putInvoiceItem = function(){
			$http.put('http://localhost:8080/XWS_AMAA_Firma/api/partneri/'+userService.user.pib+'/fakture/'+userService.invId+'/stavke/'+userService.itemId+'?semantic=yes', $scope.invoiceItem)
			.success(function (data){
				$location.path('/invoice/'+userService.invId);
				$window.location.reload();
			});	
	}
	$scope.postInvoiceItem = function(){
			$http.post('http://localhost:8080/XWS_AMAA_Firma/api/partneri/'+userService.user.pib+'/fakture/'+userService.invId+'/stavke?semantic=yes', $scope.invoiceItem)
			.success(function (data){
				$location.path('/invoice/'+userService.invId);
				$window.location.reload();
			});	
	}
	$scope.deleteInvoiceItem = function(){
			$http.delete('http://localhost:8080/XWS_AMAA_Firma/api/partneri/'+userService.user.pib+'/fakture/'+userService.invId+'/stavke/'+userService.itemId)
			.success(function (data){
				$location.path('/invoice/'+userService.invId);
				$window.location.reload();
			});	
	}
});
