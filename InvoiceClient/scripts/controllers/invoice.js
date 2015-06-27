'use strict';

angular.module('invoice', [
	'resource.invoice',
	'ui.bootstrap',
	'invoiceItem',
	'resource.invoiceItem'])

.controller('invoiceCtrl', function (Invoice, $scope, $routeParams, $modal, $log, $location, InvoiceItem, userService) {
	//ako pozivamo edit postojece fakture
	if($routeParams.invoiceId!='new'){
		//preuzimanje parametra iz URL
		var invoiceId = $routeParams.invoiceId;
		
		//preuzimanje fakure sa servera. Posto smo u Invoice factory rutu definisali kao '...invoice/:invoiceId' invoiceId ce se proslediti kao parametar rute na server 
		Invoice.get({'invoiceId':invoiceId}).$promise.then(function (data) {
			$scope.invoice = data;
		});
	}
	//ako kreiramo novu fakutru
	else{
		$scope.invoice = new Invoice();
		$scope.invoice.invoiceItems = [];
	}
	//funkcija koja otvara datepicker
	$scope.openDatepicker = function($event, opened) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope[opened] = true;
	};

	//modalni dijalog za stavku fakutre
	$scope.openModal = function (invoiceItem, size) {
		$scope.oldItem = invoiceItem;
		userService.invId = $scope.invoice.zaglavljeFakture.idPoruke;
		var modalInstance = $modal.open({
			templateUrl: 'views/invoice-item.html',
			controller: 'invoiceItemCtrl',
			size: size,
			resolve: {
				invoiceItem: function () {
					return invoiceItem;
				}
			}
		});
		modalInstance.result.then(function (data) {
			//var invoiceItem = data.invoiceItem;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
			//$log.info('Invoice item :'+invoiceItem);
			// if(!invoiceItem.redniBroj && data.action==='updateStavka'){
			// 	$scope.invoice.invoiceItems.push(invoiceItem);				
			// }
			if(data.action==='cancel'){
				$window.location.reload();
			}
			if(data.action==='updateStavka'){
				// if(invoiceItem.redniBroj){
				// 	//zbog cega redirekcija ide na callback?
				// 	// InvoiceItem.update({invoiceItemId:invoiceItem.redniBroj},function () {
				// 	// 	$location.path('/invoice/:invoiceId');
				// 	// });
				// 	InvoiceItem.putInvoiceItem();//.$promise.then(function (data) {
				// 		//$scope.invoice = data;
				// 	//});
				// }
				// else{
				// 	$scope.invoiceItem.$saveNew(function () {	
				// 		$location.path('/invoice/:invoiceId');
				// 	});
				// }
				// $log.info("update stavka");
			}
			//ako stavka treba da se obrise izbaci se iz niza
			if(data.action==='delete'){
				var index = $scope.invoice.invoiceItems.redniBroj;
				$scope.invoice.invoiceItems.splice(index, 1);
				//ako je stavka imala i id, treba da se obrise i na serveru (da li je to dobro?)
				// if(invoiceItem.redniBroj){
				// 	InvoiceItem.delete({invoiceItemId:invoiceItem.redniBroj});
				// }
			}
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};

	//cuvanje izmena
	$scope.save = function () {
		if($scope.invoice.zaglavljeFakture.idPoruke){
			//zbog cega redirekcija ide na callback?
			$scope.invoice.$update({invoiceId:$scope.invoice.zaglavljeFakture.idPoruke},function () {
				$location.path('/invoiceList');
			});
		}
		else{
			$http.post('http://localhost:8080/XWS_AMAA_Firma/api/partneri/'+userService.user.pib+'/fakture/'+userService.invId+'?semantic=yes', $scope.invoice)
			.success(function (data){
				$location.path('/invoice/'+invId);
			});
			// $scope.invoice.$save(function () {
			// 	$location.path('/invoiceList');
			// });
		}
		$log.info("save");
	}

	$scope.delete = function () {
		if($scope.invoice.idPoruke){
			$scope.invoice.$delete({invoiceId:$scope.invoice.zaglavljeFakture.idPoruke}, function () {
				$location.path('invoiceList');
			});
		}
	}
});
