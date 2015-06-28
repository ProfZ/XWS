'use strict';

angular.module('invoiceItem', [])

.controller('invoiceItemCtrl', function ($scope, $modalInstance, invoiceItem, $http, userService, $location, $window, redniBroj, isNew, newInvoiceItem, $timeout) {
	if(invoiceItem){
		$scope.invoiceItem = invoiceItem;
		
	}
	else{
		$scope.invoiceItem = {};
		$scope.invoiceItem.redniBroj = redniBroj;	
	}
	$scope.isNew = isNew;
	$scope.newInvoiceItem = newInvoiceItem;
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
		if(!$scope.newInvoiceItem && !$scope.isNew){
			$scope.putInvoiceItem();
		}else{
			$scope.postInvoiceItem();
		}
	};

	$scope.add = function () {
		var result = {
			action: 'add',
			invoiceItem: $scope.invoiceItem
		}
		$modalInstance.close(result);
	};

	$scope.remove = function() {
		var result = {
			action: 'remove',
			redniBroj: $scope.invoiceItem.redniBroj
		}
		$modalInstance.close(result);
	};

	$scope.cancel = function () {
		//$scope.invoiceItem = $scope.oldItem;
		var result = {
			action: 'cancel',
		}
		$modalInstance.close(result);
		//$window.location.reload();
	};

	$scope.delete = function () {
		userService.itemId = $scope.invoiceItem.redniBroj;
		$scope.deleteInvoiceItem();
	};
	
	$scope.putInvoiceItem = function(){
			$http.put('http://localhost:8080/XWS_AMAA_Firma/api/partneri/'+userService.user.pib+'/fakture/'+userService.invId+'/stavke/'+userService.itemId+'?semantic=yes', $scope.invoiceItem)
			.success(function (data){
				$timeout($scope.cancel(), 200);
			});	
	}
	$scope.postInvoiceItem = function(){
			$http.post('http://localhost:8080/XWS_AMAA_Firma/api/partneri/'+userService.user.pib+'/fakture/'+userService.invId+'/stavke?semantic=yes', $scope.invoiceItem)
			.success(function (data){
				$timeout($scope.cancel(), 200);
			});	
	}
	$scope.deleteInvoiceItem = function(){
			$http.delete('http://localhost:8080/XWS_AMAA_Firma/api/partneri/'+userService.user.pib+'/fakture/'+userService.invId+'/stavke/'+userService.itemId)
			.success(function (data){
				$timeout($scope.cancel(), 200);
			});	
	}
});
