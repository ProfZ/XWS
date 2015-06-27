angular.module('resource.invoice', ['ngResource'])
	.factory('Invoice', function ($resource) {
	return $resource('http://localhost:8080/XWS_AMAA_Firma/api/partneri/PIBKupca001/fakture/:invoiceId',null, {
        'update': { method:'PUT' }
    });
})