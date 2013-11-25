if (typeof youngjin.admin == 'undefined') {
	youngjin.admin = {};
}

$(function() {
	youngjin.admin.user_add();
	
});

youngjin.admin.user_add = function(){
	$('#admin_user_addButton span').unbind('click');
	$('#admin_user_addButton span').bind('click', function(){
		window.open(contextPath + '/admin/add/', 'userAddPop', 'width=350, height=180, status=no, scrollbars=no');
	});
};