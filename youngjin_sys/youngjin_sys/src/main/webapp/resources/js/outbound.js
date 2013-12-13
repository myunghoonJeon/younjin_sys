if (typeof youngjin.outbound == 'undefined') {
	youngjin.outbound = {};
}

$(function() {	
	youngjin.outbound.sync();
});

youngjin.outbound.sync = function(){
	var eventString = webBrowserCheck() == 'C' ? 'keydown' : 'keypress';
	
	$('.gbl_addButton').unbind('click');
	$('.gbl_addButton').bind('click', function(){
		var url = contextPath + '/outbound/add/';
		$.smartPop.open({
			width : 900,
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
	
	$('.gbl_preparation_memorandum').unbind('click');
	$('.gbl_preparation_memorandum').bind('click', function(){
		youngjin.outbound.memorandum($(this));
	});
	
	$('.memorandum_table tr').unbind('click');
	$('.memorandum_table tr').bind('click', function(){
		youngjin.outbound.memorandumPop($(this));
	});
	
	$('.memorandum_back').unbind('click');
	$('.memorandum_back').bind('click', function(){
		youngjin.outbound.memorandumFormBack($(this));
	});
	
	$('.memorandum_add').unbind('click');
	$('.memorandum_add').bind('click', function(){
		youngjin.outbound.memorandumAdd($(this));
	});
	
	$('.memorandum_update').unbind('click');
	$('.memorandum_update').bind('click', function(){
		youngjin.outbound.memorandumUpdate($(this));
	});
	
	$('.memorandum_complete .yj_button').unbind('click');
	$('.memorandum_complete .yj_button').bind('click', function(){
		youngjin.outbound.memorandumBack($(this));
	});
	
	$('.gbl_preparation_dd619_write').unbind('click');
	$('.gbl_preparation_dd619_write').bind('click', function(){
		youngjin.outbound.dd619Pop($(this));
	});
	
	$('.dd619_addButton').unbind('click');
	$('.dd619_addButton').bind('click', function(){
		youngjin.outbound.dd619Add($(this));
	});
	
	$('.dd619_back').unbind('click');
	$('.dd619_back').bind('click', function(){
		youngjin.outbound.dd619Back($(this));
	});
	
	$('.dd619_add_submit_button').unbind('click');
	$('.dd619_add_submit_button').bind('click', function(){
		youngjin.outbound.dd619AddSubmit($(this));
	});
	
	$('.gbl_process_delivery').unbind('click');
	$('.gbl_process_delivery').bind('click', function(){
		parent.$.smartPop.close();
		parent.location.href = contextPath + '/outbound/delivery/main';
	});
	
	$('.gbl_preparation_weight_certificate').unbind('click');
	$('.gbl_preparation_weight_certificate').bind('click', function(){
		youngjin.outbound.weightCertificate($(this));
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

youngjin.outbound.memorandum = function(target){
	var seq = target.parents('.gbl_preparation_list').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/memorandum';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 600,
		height: 350,
		url : url
	});
	
};

youngjin.outbound.memorandumPop = function(target){	
	var seq = $('.memorandum_table').attr('data-seq');
	
	var checkbox = target.children('.memorandum_type').children('input');
	var type = checkbox.val();
	
	var url = contextPath + '/outbound/' + seq + '/memorandum/' + type;	
	
	if( type == '02' ){
		var article = target.children('.memorandum_name').children('input');
		
		if(article.val() == '' || article.val() == null){
			youngjin.outbound.sync();
			article.focus();
			return;
		}
		
		url = contextPath + '/outbound/' + seq + '/memorandum/' + type + '/' + article.val();
	}
	
	if( target.children('.memorandum_type').children('input').attr('checked') != 'checked'){
		
		checkbox.attr('checked', 'checked');
		
		parent.$.smartPop.close();
	
		parent.$.smartPop.open({
			width: 594,
			height: 841,
			url : url
		});	
	} else {
		if(confirm('선택을 해제 하시겠습니까?(yes : 해체 및 삭제)')){
			if( type == '02'){
				target.children('.memorandum_name').children('input').val('');
				checkbox.removeAttr('checked');
			} else {
				checkbox.removeAttr('checked');				
			}
			
			url = contextPath + '/outbound/' + seq + '/memorandum/' + type + '/delete.json';
			
			$.postJSON(url, {}, function(){
				return jQuery.ajax({
					error: function(){alert("에러 발생!");}
				});
			});
		} else {
			if(confirm('수정하시겠습니까?')){	
				parent.$.smartPop.close();
			
				parent.$.smartPop.open({
					width: 594,
					height: 841,
					url : url
				});	
			} else {
				alert("취소 하였습니다.");
			}
		}
	}
};

youngjin.outbound.memorandumFormBack = function(target){
	var parents = target.parents().parents().parents().parents('.memorandum_form_content_wrap');
	var seq = parents.attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/memorandum/';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 600,
		height: 350,
		url : url
	});	
};

youngjin.outbound.memorandumAdd = function(target){
	var parents = target.parents().parents().parents().parents('.memorandum_form_content_wrap');
	var type = parents.attr('data-type');
	var subject = $('#memorandum_subject').val();
	var comment = $('#memorandum_comment').val();
	var articleComment = $('#memorandum_article_comment').val();
	var chiefOfOffice = $('#memorandum_chief_of_office').val();
	var officeInfo = $('#memorandum_office_info').val();
	var areaDirector = $('#memorandum_area_director').val();
	var articles = $('#memorandum_articles').val();
	var gblSeq = parents.attr('data-seq');
	
	if( articles == undefined || articles == '' || articles == null){
		articles = '';
	}
	
	var url = contextPath + '/outbound/' + gblSeq + '/memorandum/memorandumInput.json';
	var json = {
		'type' : type,
		'subject' : subject,
		'comment' : comment,
		'articles' : articles,
		'articleComment' : articleComment,
		'chiefOfOffice' : chiefOfOffice,
		'officeInfo' : officeInfo,
		'areaDirector' : areaDirector,
		'gblSeq' : gblSeq
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				var url = contextPath + '/outbound/' + gblSeq + '/memorandum/';
				
				parent.$.smartPop.close();

				parent.$.smartPop.open({
					width: 600,
					height: 350,
					url : url
				});					
			},
			
			error: function(){ alert ('에러 발생!'); }
		});
	});
};

youngjin.outbound.memorandumUpdate = function(target){
	var parents = target.parents().parents().parents().parents('.memorandum_form_content_wrap');
	var type = parents.attr('data-type');
	var subject = $('#memorandum_subject').val();
	var comment = $('#memorandum_comment').val();
	var articleComment = $('#memorandum_article_comment').val();
	var chiefOfOffice = $('#memorandum_chief_of_office').val();
	var officeInfo = $('#memorandum_office_info').val();
	var areaDirector = $('#memorandum_area_director').val();
	var articles = $('#memorandum_articles').val();
	var gblSeq = parents.attr('data-seq');
	
	if( articles == undefined || articles == '' || articles == null){
		articles = '';
	}
	
	var url = contextPath + '/outbound/' + gblSeq + '/memorandum/memorandumUpdate.json';
	var json = {
		'type' : type,
		'subject' : subject,
		'comment' : comment,
		'articles' : articles,
		'articleComment' : articleComment,
		'chiefOfOffice' : chiefOfOffice,
		'officeInfo' : officeInfo,
		'areaDirector' : areaDirector,
		'gblSeq' : gblSeq
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				var url = contextPath + '/outbound/' + gblSeq + '/memorandum/';
				
				parent.$.smartPop.close();

				parent.$.smartPop.open({
					width: 600,
					height: 350,
					url : url
				});					
			},
			
			error: function(){ alert ('에러 발생!'); }
		});
	});
};

youngjin.outbound.memorandumBack = function(target){
	var seq = $('.memorandum_table').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/preparation';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 400,
		height: 500,
		url : url
	});
};



youngjin.outbound.dd619Pop = function(target){
	var seq = target.parents('.gbl_preparation_list').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/dd619List';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 900,
		height: 400,
		url : url
	});
	
};

youngjin.outbound.dd619Add = function(target){
	var seq = target.parents('.dd619_list_wrap').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/dd619Add';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width : 700,
		height : 900,
		url : url
	});
};

youngjin.outbound.dd619Back = function(target){
	var seq = target.parents('.dd619_list_wrap').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/preparation';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 400,
		height: 500,
		url : url
	});
};

youngjin.outbound.dd619AddSubmit = function(target){
	var table = target.parents().parents().parents().parents('.dd619_add_table');
	var gblSeq = table.attr('data-seq');
	var gblNo = $('#gblNo').val();
	var date = $('#date').val();
	var name = $('#name').val();
	var ssn = $('#ssn').val();
	var rank = $('#rank').val();
	var originOfShipment = $('#originOfShipment').val();
	var destination = $('#destination').val();
	var orderingActivityName = $('#orderingActivityName').val();
	var carrierName = $('#carrierName').val();
	var agentName = $('#agentName').val();
	var signature = $('#signature').val();
	var carrierShipmentReference = $('#carrierShipmentReference').val();
	var code = $('#code').val();
	var other = $('#other1').val() + ',' + $('#other2').val() + ',' + $('#other3').val();
	var total = $('#total1').val() + ',' + $('#total2').val() + ',' + $('#total3').val();
	var officerMaterial = $('#officerMaterial').val();
	var officerSignature = $('#officerSignature').val();
	var officerDate = $('#officerDate').val();
	var rankAndName = $('#rankAndName').val();
	var transportationDate = $('#transportationDate').val();
	var remark = $('#remark').val();
	var writeUser = $('#writeUser').val();
	
	var url = contextPath + '/outbound/' + gblSeq + '/dd619/' + 'add.json';
	var json = {
		'gblSeq' : gblSeq,
		'gblNo' : gblNo,
		'date' : date,
		'name' : name,
		'ssn' : ssn,
		'rank' : rank,
		'originOfShipment' : originOfShipment,
		'destination' : destination,
		'orderingActivityName' : orderingActivityName,
		'carrierName' : carrierName,
		'agentName' : agentName,
		'signature' : signature,
		'carrierShipmentReference' : carrierShipmentReference,
		'code' : code,
		'other' : other,
		'total' : total,
		'officerMaterial' : officerMaterial,
		'officerSignature' : officerSignature,
		'officerDate' : officerDate,
		'officerDate' : officerDate,
		'rankAndName' : rankAndName,
		'transportationDate' : transportationDate,
		'remark' : remark,
		'writeUser' : writeUser
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){				
				var url = contextPath + '/outbound/' + gblSeq + '/dd619List';
				
				parent.$.smartPop.close();

				parent.$.smartPop.open({
					width: 900,
					height: 400,
					url : url
				});
				
			}, 
			error: function(){
				alert('에러발생!');
			}
		});
	});
};

youngjin.outbound.dd619Write = function(target){	
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 793.7,
		height: 1122.5,
		url : url
	});
};

youngjin.outbound.weightCertificate = function(target){
	var seq = target.parents('.gbl_preparation_list').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/weightcertificate';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 800,
		height: 900,
		url : url
	});
};