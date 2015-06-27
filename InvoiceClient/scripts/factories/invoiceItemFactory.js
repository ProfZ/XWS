angular.module('resource.invoiceItem', ['ngResource'])
	.factory('InvoiceItem', function ($resource) {
	return $resource('http://localhost:8080/XWS_AMAA_Firma/api/partneri/PIBKupca001/fakture/:invoiceId/stavke/:invoiceItemId?semantic=yes', null, {
		'putInvoiceItem' : { method:'PUT' }
	});
})
