if (typeof youngjin.top == 'undefined') {
	youngjin.top = {};
}

$(function() {
	youngjin.top.home();
	
	youngjin.top.form();
	
	youngjin.top.formSelect();
	
	youngjin.top.goToAdminPage();
	
	youngjin.top.goToGblockPage();
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
		x = screen.width / 2 - 150;
		y = screen.height / 2 - 100;
		
		var win = window.open(contextPath + '/form/', 'form_pop', 'width=350, height=180, screenX=' + x + ',screenY=' + y + ', status=no, scrollbars=no');
	});
};

youngjin.top.formSelect = function(){
	$('#form_inventory').bind('click', function(){
		alert("!");
	});
};

youngjin.top.goToAdminPage = function(){
	$('.admin_service_wrap').bind('click', function(){
		location.href = contextPath + '/admin/admin/';
	});
};

youngjin.top.goToGblockPage = function(){
	$('.gblock_service_wrap').bind('click', function(){
		location.href = contextPath + '/gblock/gblock/main/';
	});
};