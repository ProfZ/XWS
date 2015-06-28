app.controller('createInvoiceCtrl', ['$scope', function($scope) {
	$scope.openModal = function (invoiceItem) {
		var modalInstance = $modal.open({
			templateUrl: 'views/invoice-item.html',
			controller: 'invoiceItemCtrl',
			resolve: {
				invoiceItem: function () {
					return invoiceItem;
				}
			}
		});
		modalInstance.result.then(function (data) {
			if(data.action==='cancel'){
				$window.location.reload();
			}
			if(data.action==='updateStavka'){
			}
			if(data.action==='delete'){
				var index = $scope.invoice.invoiceItems.redniBroj;
				$scope.invoice.invoiceItems.splice(index, 1);
				//ako je stavka imala i id, treba da se obrise i na serveru (da li je to dobro?)
				// if(invoiceItem.redniBroj){
				// 	InvoiceItem.delete({invoiceItemId:invoiceItem.redniBroj});
				// }
			}
		}, function () {

		});
	};
}]);