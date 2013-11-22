if (typeof youngjin.top == 'undefined') {
	youngjin.top = {};
}

$(function() {
	youngjin.top.home();
	
	youngjin.top.form();
	
	youngjin.top.formSelect();
	
	youngjin.top.goToAdminPage();
});

youngjin.top.home = function(){
	$('.home_wrap').unbind('click');
	$('.home_wrap').bind('click',function(){
		location.href=contextPath + "/";
	});
	
};

youngjin.top.form = function(){
	
	$('.top_menu_form').unbind('click');
	$('.top_menu_form').bind('click', function(event){
		var offset = $(this).offset();
		x = event.clientX - offset.left;
		y = event.clientY - offset.top;
		
		var win = window.open(contextPath + '/form/', 'form_pop', 'width=350, height=180, status=no, scrollbars=no');
		
		event.preventDefault();
		return false;
	});
};

youngjin.top.formSelect = function(){
	$('#form_inventory').bind('click', function(){
		alert("!");
	});
};

youngjin.top.goToAdminPage = function(){
	$('.admin_service_wrap').bind('click', function(){
		location.href = contextPath + '/admin/admin';
	});
};