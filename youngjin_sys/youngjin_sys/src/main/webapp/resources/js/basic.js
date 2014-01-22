if (typeof youngjin.basic == 'undefined') {
	youngjin.basic = {};
}

$(function() {	
	youngjin.basic.sync();
});

youngjin.basic.sync = function(){
	youngjin.basic.branchSync();
	
	youngjin.basic.podSync();
	
	youngjin.basic.carrierSync();
	
	youngjin.basic.companySync();
};

youngjin.basic.branchSync = function(){
	$('.basic_branch_add').unbind('click');
	$('.basic_branch_add').bind('click', function(){
		youngjin.basic.branchAdd();
	});
	
	$('.basic_add_submit').unbind('click');
	$('.basic_add_submit').bind('click', function(){
		youngjin.basic.branchAddSubmit();
	});
	
	$('.branch_table tbody tr').unbind('click');
	$('.branch_table tbody tr').bind('click', function(){
		youngjin.basic.basicModify($(this));
	});
	
	$('.basic_modify_submit').unbind('click');
	$('.basic_modify_submit').bind('click', function(){
		youngjin.basic.branchModifySubmit();
	});
};

youngjin.basic.podSync = function(){
	$('.basic_pod_add').unbind('click');
	$('.basic_pod_add').bind('click', function(){
		youngjin.basic.podAdd();
	});
	
	$('.basic_pod_add_submit').unbind('click');
	$('.basic_pod_add_submit').bind('click', function(){
		youngjin.basic.podAddSubmit();
	});
	
	$('.pod_table tbody tr').unbind('click');
	$('.pod_table tbody tr').bind('click', function(){
		youngjin.basic.podModify($(this));
	});
	
	$('.basic_pod_modify_submit').unbind('click');
	$('.basic_pod_modify_submit').bind('click', function(){
		youngjin.basic.podModifySubmit();
	});
};

youngjin.basic.carrierSync = function(){
	$('.basic_carrier_add').unbind('click');
	$('.basic_carrier_add').bind('click', function(){
		youngjin.basic.carrierAdd();
	});
	
	$('.basic_carrier_add_submit').unbind('click');
	$('.basic_carrier_add_submit').bind('click', function(){
		youngjin.basic.carrierAddSubmit();
	});
	
	$('.carrier_table tbody tr').unbind('click');
	$('.carrier_table tbody tr').bind('click', function(){
		youngjin.basic.carrierModify($(this));
	});
	
	$('.basic_carrier_modify_submit').unbind('click');
	$('.basic_carrier_modify_submit').bind('click', function(){
		youngjin.basic.carrierModifySubmit();
	});
};

youngjin.basic.companySync = function(){
	$('.basic_company_add').unbind('click');
	$('.basic_company_add').bind('click', function(){
		youngjin.basic.comapanyAdd();
	});
	
	$('.basic_company_add_submit').unbind('click');
	$('.basic_company_add_submit').bind('click', function(){
		youngjin.basic.companyAddSubmit();
	});
	
	$('.company_table tbody tr').unbind('click');
	$('.company_table tbody tr').bind('click', function(){
		youngjin.basic.companyModify($(this));
	});
	
	$('.basic_company_modify_submit').unbind('click');
	$('.basic_company_modify_submit').bind('click', function(){
		youngjin.basic.companyModifySubmit();
	});
};

youngjin.basic.branchAdd = function(){
	var url = contextPath + '/basic/branchAdd';
	
	$.smartPop.open({
		width : 600,
		height : 700,
		url : url
	});
};

youngjin.basic.branchAddSubmit = function(){
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

youngjin.basic.branchModifySubmit = function(){
	var form = document.forms['branch'];
	
	form.method = 'post';
	form.action = contextPath + '/basic/branchModifySubmit';
	form.submit();	
};

youngjin.basic.podAdd = function(){
	var url = contextPath + '/basic/podAdd';
	
	$.smartPop.open({
		width : 600,
		height : 350,
		url : url
	});	
	
};

youngjin.basic.podAddSubmit = function(){
	var form = document.forms['pod'];
	
	form.method = 'post';
	form.action = contextPath + '/basic/podAddSubmit';
	form.submit();	
};

youngjin.basic.podModify = function(target){
	var seq = target.attr('data-seq');
	var url = contextPath + '/basic/' + seq + '/podModify';
	
	$.smartPop.open({
		width : 600,
		height : 350,
		url : url
	});	
};

youngjin.basic.podModifySubmit = function(){
	var form = document.forms['pod'];
	
	form.method = 'post';
	form.action = contextPath + '/basic/podModifySubmit';
	form.submit();	
};

youngjin.basic.carrierAdd = function(){
	var url = contextPath + '/basic/carrierAdd';
	
	$.smartPop.open({
		width : 600,
		height : 570,
		url : url
	});	
};

youngjin.basic.carrierAddSubmit = function(){
	var form = document.forms['carrier'];
	
	form.method = 'post';
	form.action = contextPath + '/basic/carrierAddSubmit';
	form.submit();		
};

youngjin.basic.carrierModify = function(target){
	var seq = target.attr('data-seq');
	var url = contextPath + '/basic/' + seq + '/carrierModify';
	
	$.smartPop.open({
		width : 600,
		height : 570,
		url : url
	});		
};

youngjin.basic.carrierModifySubmit = function(){
	var form = document.forms['carrier'];
	
	form.method = 'post';
	form.action = contextPath + '/basic/carrierModifySubmit';
	form.submit();		
};

youngjin.basic.comapanyAdd = function(){
	var url = contextPath + '/basic/companyAdd';
	
	$.smartPop.open({
		width : 600,
		height : 630,
		url : url
	});		
};

youngjin.basic.companyAddSubmit = function(){
	var form = document.forms['company'];
	
	form.method = 'post';
	form.action = contextPath + '/basic/companyAddSubmit';
	form.submit();		
};

youngjin.basic.companyModify = function(target){
	var seq = target.attr('data-seq');
	var url = contextPath + '/basic/' + seq + '/companyModify';
	
	$.smartPop.open({
		width : 600,
		height : 630,
		url : url
	});		
};

youngjin.basic.companyModifySubmit = function(){
	var form = document.forms['company'];
	
	form.method = 'post';
	form.action = contextPath + '/basic/companyModifySubmit';
	form.submit();			
};