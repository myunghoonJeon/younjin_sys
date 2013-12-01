if (typeof youngjin.outbound == 'undefined') {
	youngjin.outbound = {};
}

$(function() {
	youngjin.outbound.sync();
});

youngjin.outbound.sync = function(){
	$('.gbl_addButton').unbind('click');
	$('.gbl_addButton').bind('click', function(){
		var url = contextPath + '/outbound/add/';
		$.smartPop.open({
			width : 400,
			height : 500,
			url : url
		});
		
		
	});
};