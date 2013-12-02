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
	
	$('input#destGBlock').unbind('change');
	$('input#destGBlock').bind('change', function(){
		youngjin.outbound.findUsNo($(this));
	});
	
	$('.gbl_add_submit_button').unbind('click');
	$('.gbl_add_submit_button').bind('click', function(){	
		youngjin.outbound.gblAddSubmit();
	});
	
	$('input#pud').datepicker();
	$('input#rdd').datepicker();
	$('input#startPud').datepicker({dateFormat: 'yymmdd'});
	$('input#endPud').datepicker({dateFormat: 'yymmdd'});
	
	$('select#branch').unbind('change');
	$('select#branch').bind('change', function(){
		var form = document.forms['outboundFilter'];
		form.submit();
	});
	
	$('select#carrier').unbind('change');
	$('select#carrier').bind('change', function(){
		var form = document.forms['outboundFilter'];
		form.submit();
	});
	
	$('select#code').unbind('change');
	$('select#code').bind('change', function(){
		var form = document.forms['outboundFilter'];
		form.submit();
	});
	
	$('input#startPud').unbind('change');
	$('input#startPud').bind('change', function(){
		var form = document.forms['outboundFilter'];
		form.submit();
	});
	
	$('input#endPud').unbind('change');
	$('input#endPud').bind('change', function(){
		var form = document.forms['outboundFilter'];
		form.submit();
	});
	
	$('.gbl_list').unbind('click');
	$('.gbl_list').bind('click', function(){
		youngjin.outbound.getGblProcess($(this));
	});
	
};

youngjin.outbound.findUsNo = function(target){
	var url = contextPath + '/outbound/findUsNo.json';
	var gblock = target.val();
	var json = {
		"gblock" : gblock 	
	};
	
	$.postJSON(url, json, function(gBlock){
		return jQuery.ajax({
			success : function(){
				$('#usNo').val(gBlock.usNo);
			},
			
			error : function(){
				alert("에러 발생!");
			}
		});
			
	});
};

youngjin.outbound.gblAddSubmit = function(){
	var form = document.forms['gbl'];
	form.submit();	
	
	//parent.$.smartPop.close();
};

youngjin.outbound.getGblProcess = function(target){
	var seq =  target.attr('data-seq');
	var url = contextPath + '/outbound/' + seq;
	
	$.smartPop.open({
		width : 900,
		height : 600,
		url : url
	});			
	
};