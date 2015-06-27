'use strict';

angular.module('invoiceItem', [])

.controller('invoiceItemCtrl', function ($scope, $modalInstance, invoiceItem, $http) {
	if(invoiceItem){
		$scope.invoiceItem = invoiceItem;
	}
	else{
		$scope.invoiceItem = {};	
	}
	$scope.ok = function () {
		$modalInstance.close({'invoiceItem':$scope.invoiceItem,
								'action':'updateStavka'});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};

	$scope.delete = function () {
		$modalInstance.close({'invoiceItem':$scope.invoiceItem,
								'action':'delete'});
	};
	
	$scope.putInvoiceItem = function(){
			$http.put('http://localhost:8080/XWS_AMAA_Firma/api/partneri/PIBKupca001/fakture/:invoiceId/stavke:invoiceItemId?semantic=yes')
			.success(function (data){
				$location.path('/invoice/:invoiceId');
			});	
	}
});
