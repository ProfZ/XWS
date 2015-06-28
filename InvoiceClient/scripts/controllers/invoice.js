'use strict';

angular.module('invoice', [
	'resource.invoice',
	'ui.bootstrap',
	'invoiceItem',
	'resource.invoiceItem'])

.controller('invoiceCtrl', function (Invoice, $scope, $routeParams, $modal, $log, $location, InvoiceItem, userService, $http, $filter) {
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
		$scope.invoice = {
			stavkaFakture: [],
			zaglavljeFakture: {
				idPoruke: "0",
			}
		};
		$scope.isNew = true;
	}
	//funkcija koja otvara datepicker
	$scope.openDatepicker = function($event, opened) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope[opened] = true;
	};

	//modalni dijalog za stavku fakutre
	$scope.openModal = function (invoiceItem) {
		$scope.oldItem = invoiceItem;
		$scope.oldInvoice = $scope.invoice;
		userService.invId = $scope.invoice.zaglavljeFakture.idPoruke;
		var modalInstance = $modal.open({
			templateUrl: 'views/invoice-item1.html',
			controller: 'invoiceItemCtrl',
			resolve: {
				invoiceItem: function () {
					return invoiceItem;
				},
				redniBroj: function () {
					return $scope.invoice.stavkaFakture.length + 1;
				},
				isNew: function () {
					if ($scope.invoice.zaglavljeFakture.idPoruke == "0")
						return true;
					else return false;
				},
				newInvoiceItem: function() {
					return invoiceItem == null;
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
				if (!$scope.isNew) {
					$scope.reload();
				}
			}
			if(data.action==='add'){
				$scope.invoice.stavkaFakture.splice($scope.invoice.stavkaFakture.length, 0, data.invoiceItem);
				//$window.location.reload();
			}
			if(data.action==='remove'){
				$scope.invoice.stavkaFakture.splice(data.redniBroj - 1, 1);
				//$window.location.reload();
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
				$scope.invoice.invoiceItems.splice(index - 1, 1);
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
			$http.post('http://localhost:8080/XWS_AMAA_Firma/api/partneri/'+userService.user.pib+'/fakture?semantic=yes', $scope.invoice)
			.success(function (data){
				$scope.invoice = data;
			});
			// $scope.invoice.$save(function () {
			// 	$location.path('/invoiceList');
			// });
		$log.info("save");
	}

	$scope.reload = function () {
		$http.get('http://localhost:8080/XWS_AMAA_Firma/api/partneri/'+userService.user.pib+'/fakture/' + $scope.invoice.zaglavljeFakture.idPoruke + '?semantic=yes')
			.success(function (data){
				$scope.invoice = data;
				console.log(data);
			});
	}

	$scope.value1 = userService.value0;
	$scope.isEnable = userService.isEnable1;
	$scope.isEnable2 = userService.isEnable1;
	$scope.znak1 = userService.znak;
	$scope.kol = userService.kol0;
	$scope.rabat = userService.rabat0;
	$scope.isRabat = userService.isRabat1;
	$scope.filterOption = userService.filterOption1;
	$scope.dateCurrency = userService.dateCurrency1;
	$scope.kg = userService.kg1; 
	$scope.kgNaziv1 = userService.kgNaziv2;
	$scope.value3= userService.value2;
	//$filter.("filtriranjeFakFilter")($scope.value1, $scope.invoice.invoiceItems,$scope.znak1, $scope.isEnable);
	//filtriranjeStavkeKolFilter($scope.kol,$scope.invoice.invoiceItems, $scope.znak2, $isEnable2);
	//filtriranjeStavkeUmanjenoZaRabatFilter($scope.rabat,$scope.invoice.invoiceItems, $scope.znak3, $isEnable2);
	// $scope.naziv = $scope.invoice.invoiceItems.nazivRobeUsluge;
});
