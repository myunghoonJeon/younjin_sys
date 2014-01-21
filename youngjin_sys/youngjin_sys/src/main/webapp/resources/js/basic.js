if (typeof youngjin.basic == 'undefined') {
	youngjin.basic = {};
}

$(function() {	
	youngjin.basic.sync();
});

youngjin.basic.sync = function(){
	youngjin.basic.branchSync();
};

youngjin.basic.branchSync = function(){
	$('.basic_branch_add').unbind('click');
	$('.basic_branch_add').bind('click', function(){
		youngjin.basic.basicAdd();
	});
	
	$('.basic_add_submit').unbind('click');
	$('.basic_add_submit').bind('click', function(){
		youngjin.basic.basicAddSubmit();
	});
	
	$('.branch_table tbody tr').unbind('click');
	$('.branch_table tbody tr').bind('click', function(){
		youngjin.basic.basicModify($(this));
	});
};

youngjin.basic.basicAdd = function(){
	var url = contextPath + '/basic/branchAdd';
	
	$.smartPop.open({
		width : 600,
		height : 700,
		url : url
	});
};

youngjin.basic.basicAddSubmit = function(){
	var form = document.forms['branch'];
	
	form.method = 'post';
	form.action = contextPath + '/basic/branchAddSubmit';
	form.submit();
};

youngjin.basic.basicModify = function(target){
	var seq = target.attr('data-seq');
	var url = contextPath + '/basic/' + seq + '/branchModify';
	
	$.smartPop.open({
		width : 600,
		height : 700,
		url : url
	});	
};