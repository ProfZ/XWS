angular.module('resource.invoiceItem', ['ngResource'])
	.factory('InvoiceItem', function ($resource, userService) {
	return $resource('http://localhost:8080/XWS_AMAA_Firma/api/partneri/'+userService.user.pib+'/fakture/'+userService.invId+'/stavke/'+userService.itemId+'?semantic=yes', null, {
		'putInvoiceItem' : { method:'PUT' }
	});
})
