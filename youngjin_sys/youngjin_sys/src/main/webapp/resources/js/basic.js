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
	
	youngjin.basic.mileageSync();
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

youngjin.basic.mileageSync = function(){
	$('.mileage_addButton').unbind('click');
	$('.mileage_addButton').bind('click', function(){
		youngjin.basic.mileageAdd();
	});
	
	$('.mileage_table input').unbind('change');
	$('.mileage_table input').bind('change', function(){
		youngjin.basic.mileageUpdate($(this));
	});
};

youngjin.basic.mileageAdd = function(){
	var url = contextPath + '/basic/mileage/add.json';
	
	var table = $('.yj_table');

	$.postJSON(url, {}, function(mileage){
		return jQuery.ajax({
			success : function(){	
				var html = '<tr data-seq="' + mileage.seq + '">'
						+		'<td class="mileage_storedAt"><input type="text" name="storedAt" /></td>'
						+		'<td class="mileage_destination"><input type="text" name="destination" /></td>'
						+		'<td class="mileage_miles"><input type="text" name="miles" /></td>'
						+		'<td class="mileage_minWeight"><input type="text" name="minWeight" /></td>'
						+		'<td class="mileage_minRate"><input type="text" name="minRate" /></td>'
						+		'<td class="mileage_maxWeight"><input type="text" name="maxWeight" /></td>'
						+		'<td class="mileage_maxRate"><input type="text" name="maxRate" /></td>';
				table.children('tbody').prepend(html);
				
				youngjin.basic.mileageSync();
			},
			error : function(){
				alert("에러 발생!");
			}
		});
	});
};

youngjin.basic.mileageUpdate = function(target){
	var seq = target.parents().parents().attr('data-seq');
	var name = target.attr('name');
	var value = target.val();
	
	var json = {};
	
	json[name] = value;
	json['seq'] = seq;
	
	var url = contextPath + '/basic/mileage/update.json';
	
	$.postJSON(url, json, function(){		
		return jQuery.ajax({
			success : function(){
			},
			error : function(){
				alert("에러발생!");
			}
		});
	});
};