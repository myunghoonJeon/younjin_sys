if (typeof youngjin.inbound == 'undefined') {
	youngjin.inbound = {};
}

$(function() {	
	youngjin.inbound.sync();
});

youngjin.inbound.sync = function(){
	$('.freight_add').unbind('click');
	$('.freight_add').bind('click', function(){
		var url = contextPath + '/inbound/freight/add/';
		$.smartPop.open({
			width : 900,
			height : 500,
			url : url
		});
	});
	
	$('.freight_add_submit_button').unbind('click');
	$('.freight_add_submit_button').bind('click', function(){
		youngjin.inbound.freightAddSubmit();
	});
};

youngjin.inbound.freightAddSubmit = function(){
	var form = document.forms['gbl'];
	form.submit();	
};