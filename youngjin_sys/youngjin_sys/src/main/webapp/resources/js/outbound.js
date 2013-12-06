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
			width : 900,
			height : 500,
			url : url,
			name : '1'
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
	
	$('.gbl_process_preperation').unbind('click');
	$('.gbl_process_preperation').bind('click', function(){
		youngjin.outbound.preperationPop($(this));
	});
	
	$('.document_upload_button').unbind('click');
	$('.document_upload_button').bind('click', function(event){
		youngjin.outbound.uploadPage($(this));
	});
	
	$('.document_view').unbind('click');
	$('.document_view').bind('click', function(){
		youngjin.outbound.fileView();
	});
	
	$('#uploadBtn').unbind('click');
	$('#uploadBtn').bind('click', function(){
		youngjin.outbound.uploadSubmit();
	});
	
	$('.gbl_preparation_pre_move_survey').unbind('click');
	$('.gbl_preparation_pre_move_survey').bind('click', function(){
		youngjin.outbound.preMoveSurveyPop($(this));
	});
	
	$('.preMoveSurveyAddButton').unbind('click');
	$('.preMoveSurveyAddButton').bind('click', function(){
		youngjin.outbound.preMoveSurveySubmit($(this));
	});
	
	$('.preMoveSurveyEditButton').unbind('click');
	$('.preMoveSurveyEditButton').bind('click', function(){
		youngjin.outbound.preMoveSurveyEditSubmit($(this));
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
		height : 671,
		url : url
	});			
	
};

youngjin.outbound.uploadPage = function(target){
	var parent = target.parents();
	var seq = parent.attr('data-seq');
	var url = contextPath + '/outbound/' + seq + '/upload';
	
	window.open(url, 'uploadPop', 'width=350, height=180, status=no, scrollbars=no');
};

youngjin.outbound.uploadSubmit = function(){
	var form = $('form#gbl');
	var seq = $('#uploadBtn').attr('data-seq');		
	
	form.submit();	

	window.opener.location.href = contextPath + '/outbound/' + seq;
};

youngjin.outbound.fileView = function(){
	var target = $('input[name=selectFileList]:checked');
	
	var parent = target.parents();
	var seq = target.val();
	var no = parent.parents('.selectFileTr').attr('data-no');
	
	location.href= contextPath + '/outbound/file/' + seq + '/' + no + '/';	
};

youngjin.outbound.preperationPop = function(target){	
	var seq = target.parents().parents('.gbl_process').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/preparation';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 400,
		height: 500,
		url : url
	});
	
	//window.open(url, 'preperationPop', 'width=350, height=600, screenX=' + x + ',screenY=' + y + ', status=no, scrollbars=no');
	
	//location.href = url;
};

youngjin.outbound.preMoveSurveyPop = function(target){	
	var seq = target.parents('.gbl_preparation_list').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/preMoveSurvey';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 500,
		height: 350,
		url : url
	});
	
	//window.open(url, 'preperationPop', 'width=350, height=600, screenX=' + x + ',screenY=' + y + ', status=no, scrollbars=no');
	
	//location.href = url;
};

youngjin.outbound.preMoveSurveySubmit = function(target){
	var form = document.forms['preMoveSurvey'];
	var seq = form.seq.value;	
	var estimateWeight = form.estimateWeight.value;
	var specialItem = form.specialItem.value;
	var esContainer = form.esContainer.value;
	var thirdParty = form.thirdParty.value;
	var fireArms = form.fireArms.value;
	var remark = form.remark.value;
	
	var url = contextPath + '/outbound/' + seq + '/preMoveSurveySubmit.json';
	var json = {
			'estimateWeight' : estimateWeight,
			'specialItem' : specialItem,
			'esContainer' : esContainer,
			'thirdParty' : thirdParty,
			'fireArms' : fireArms,
			'remark' : remark,
			'gblSeq' : seq
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success: function(){		
				var beforeUrl = contextPath + '/outbound/' + seq + '/preparation';
				
				parent.$.smartPop.close();

				parent.$.smartPop.open({
					width: 500,
					height: 350,
					url : beforeUrl
				});				
			},
			
			error : function(){
				alert("error!");
			}
		});		
	});
};

youngjin.outbound.preMoveSurveyEditSubmit = function(target){
	var form = document.forms['preMoveSurvey'];
	var seq = form.seq.value;	
	var estimateWeight = form.estimateWeight.value;
	var specialItem = form.specialItem.value;
	var esContainer = form.esContainer.value;
	var thirdParty = form.thirdParty.value;
	var fireArms = form.fireArms.value;
	var remark = form.remark.value;
	
	var url = contextPath + '/outbound/' + seq + '/preMoveSurveyEditSubmit.json';
	var json = {
			'estimateWeight' : estimateWeight,
			'specialItem' : specialItem,
			'esContainer' : esContainer,
			'thirdParty' : thirdParty,
			'fireArms' : fireArms,
			'remark' : remark,
			'gblSeq' : seq
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success: function(){		
				var beforeUrl = contextPath + '/outbound/' + seq + '/preparation';
				
				parent.$.smartPop.close();

				parent.$.smartPop.open({
					width: 500,
					height: 350,
					url : beforeUrl
				});				
			},
			
			error : function(){
				alert("error!");
			}
		});		
	});
};