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
	
	$('.gbl_process_input').unbind('click');
	$('.gbl_process_input').bind('click', function(){
		youngjin.outbound.inputPop($(this));
	});
	
	$('.gbl_modify_submit_button').unbind('click');
	$('.gbl_modify_submit_button').bind('click', function(){
		youngjin.outbound.gblkModifySubmit($(this));
	});
	
	$('.gbl_process_preparation').unbind('click');
	$('.gbl_process_preparation').bind('click', function(){
		youngjin.outbound.preperationPop($(this));
	});
	
	$('.document_upload_button').unbind('click');
	$('.document_upload_button').bind('click', function(event){
		youngjin.outbound.uploadPage($(this));
	});
	
	$('.document_power_of_attorney_button').unbind('click');
	$('.document_power_of_attorney_button').bind('click', function(){
		youngjin.outbound.powerOfAttorney($(this));
	});
	
	$('.document_view').unbind('click');
	$('.document_view').bind('click', function(){
		youngjin.outbound.fileView();
	});
	
	$('#uploadBtn').unbind('click');
	$('#uploadBtn').bind('click', function(){
		youngjin.outbound.uploadSubmit();
	});
	
	$('.gbl_process_delivery').unbind('click');
	$('.gbl_process_delivery').bind('click', function(){
		parent.$.smartPop.close();
		parent.location.href = contextPath + '/outbound/delivery/main';
	});
	
	$('.gbl_prearation_complete').unbind('click');
	$('.gbl_prearation_complete').bind('click', function(){
		youngjin.outbound.complete($(this));
	});
	
	youngjin.outbound.preMoveSurveySync();
	
	youngjin.outbound.memorandumSync();
	
	youngjin.outbound.dd619Sync();
	
	youngjin.outbound.weightCertificateSync();
	
	youngjin.outbound.additionDecideSync();
	
	youngjin.outbound.backButtonSync();
};

youngjin.outbound.preMoveSurveySync = function(){
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

youngjin.outbound.memorandumSync = function(){	
	$('.gbl_preparation_memorandum').unbind('click');
	$('.gbl_preparation_memorandum').bind('click', function(){
		youngjin.outbound.memorandum($(this));
	});
	
	$('.memorandum_all_addButton').unbind('click');
	$('.memorandum_all_addButton').bind('click', function(){
		youngjin.outbound.addMemorandum($(this));
	});
	
	$('.memorandum_all_table tbody tr td').unbind('click');
	$('.memorandum_all_table tbody tr td').bind('click', function(){
		if($(this).attr('data-check') != 'delete'){
			youngjin.outbound.goToMemorandum($(this));
		}
	});
	
	$('.memorandum_list_delete').unbind('click');
	$('.memorandum_list_delete').bind('click', function(){
		youngjin.outbound.memorandumAllListDelete($(this));
	});
	
/*	$('.memorandum_table tr').unbind('click');
	$('.memorandum_table tr').bind('click', function(){
		youngjin.outbound.memorandumPop($(this));
	});*/
	
	$('.memorandum_input_subButton').unbind('click');
	$('.memorandum_input_subButton').bind('click', function(){
		youngjin.outbound.memorandumPop($(this));
	});
	
	$('.memorandum_modify_subButton').unbind('click');
	$('.memorandum_modify_subButton').bind('click', function(){
		youngjin.outbound.memorandumModify($(this));
	});	
	
	$('.memorandum_delete_subButton').unbind('click');
	$('.memorandum_delete_subButton').bind('click', function(){
		youngjin.outbound.memorandumDelete($(this));
	});
	
	$('.memorandum_name input').unbind('click');
	$('.memorandum_name input').bind('click',function(){
		$(this).attr('data-value', $(this).val());
		
		//if( $(this).parents('tr').children('.memorandum_type').children('input').attr('checked') == 'checked'){
		//	youngjin.outbound.memorandumInvoiceModify($(this));
		//}
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
};

youngjin.outbound.dd619Sync = function(){
	$('.gbl_preparation_dd619_write').unbind('click');
	$('.gbl_preparation_dd619_write').bind('click', function(){
		youngjin.outbound.dd619Pop($(this));
	});
	
	$('.dd619_addButton').unbind('click');
	$('.dd619_addButton').bind('click', function(){
		youngjin.outbound.dd619Add($(this));
	});
	
	$('.dd619_add_submit_button').unbind('click');
	$('.dd619_add_submit_button').bind('click', function(){
		youngjin.outbound.dd619AddSubmit($(this));
	});
	
	$('.dd619_modify_submit_button').unbind('click');
	$('.dd619_modify_submit_button').bind('click', function(){
		youngjin.outbound.dd619Modify($(this));
	});
	
	$('.dd619_form_print').unbind('click');
	$('.dd619_form_print').bind('click', function(){
		youngjin.outbound.dd619Print($(this));
	});
	
	$('.dd619_table tr').unbind('click');
	$('.dd619_table tr').bind('click', function(){
		youngjin.outbound.dd619($(this));
	});
};

youngjin.outbound.weightCertificateSync = function(){
	$('.gbl_preparation_weight_certificate').unbind('click');
	$('.gbl_preparation_weight_certificate').bind('click', function(){
		youngjin.outbound.weightCertificate($(this));
	});
	
	$('.gbl_plus_Box_td').unbind('click');
	$('.gbl_plus_Box_td').bind('click', function(){
		youngjin.outbound.weightCertificateColumnAdd($(this));
	});
	
	$('input[name=grossKg]').unbind('change');
	$('input[name=grossKg]').bind('change', function(){
		youngjin.outbound.weightFromKgToLbs($(this));
	});
	
	$('.weightcertificate_write').unbind('click');
	$('.weightcertificate_write').bind('click', function(){
		youngjin.outbound.weightCertificateSubmit($(this));
	});
};

youngjin.outbound.additionDecideSync = function(){
	$('.gbl_preparation_addtional_decide').unbind('click');
	$('.gbl_preparation_addtional_decide').bind('click', function(){
		youngjin.outbound.additionalDecide($(this));
	});
	
	$('.gbl_plus_Box').unbind('click');
	$('.gbl_plus_Box').bind('click', function(){
		youngjin.outbound.addDecideColumn($(this));
	});
	
	$('.addition_complete_btn').unbind('click');
	$('.addition_complete_btn').bind('click', function(){
		youngjin.outbound.additionComplete($(this));
	});
};

youngjin.outbound.backButtonSync = function(){
	//back
	$('.pre_back_button').unbind('click');
	$('.pre_back_button').bind('click', function(){
		youngjin.outbound.preBack($(this));
	});
	
	$('.memo_back_button').unbind('click');
	$('.memo_back_button').bind('click', function(){
		youngjin.outbound.memorandumBack($(this));
	});
	
	$('.memorandum_all_back').unbind('click');
	$('.memorandum_all_back').bind('click', function(){
		youngjin.outbound.memorandumAllBack($(this));
	});
	
	$('.dd619_form_back').unbind('click');
	$('.dd619_form_back').bind('click', function(){
		youngjin.outbound.dd619FormBack($(this));
	});
	
	$('.final_back').unbind('click');
	$('.final_back').bind('click', function(){
		youngjin.outbound.finalBack($(this));
	});
	
	$('.weight_certificate_back').unbind('click');
	$('.weight_certificate_back').bind('click', function(){
		youngjin.outbound.weightCertificateBack($(this));
	});
	
	$('.memorandum_print').unbind('click');
	$('.memorandum_print').bind('click', function(){
		youngjin.outbound.memorandumPrint($(this));
	});
	
	$('.dd619_back').unbind('click');
	$('.dd619_back').bind('click', function(){
		youngjin.outbound.dd619Back($(this));
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
	
	parent.$.smartPop.close();
};

youngjin.outbound.getGblProcess = function(target){
	var seq =  target.attr('data-seq');
	var url = contextPath + '/outbound/' + seq;
	
	$.smartPop.open({
		width : 1000,
		height : 521,
		url : url
	});			
	
};

youngjin.outbound.complete = function(target){
	var seq = target.parents('.gbl_preparation_list').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq;
	
	parent.$.smartPop.close();
	
	parent.$.smartPop.open({
		width : 1000,
		height : 521,
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

youngjin.outbound.powerOfAttorney = function(target){
	var seq = target.parents().attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/powerOfAttorney';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 930.7,
		height: 1122.5,
		url : url
	});	
};

youngjin.outbound.fileView = function(){
	var target = $('input[name=selectFileList]:checked');
	
	var parent = target.parents();
	var seq = target.val();
	var no = parent.parents('.selectFileTr').attr('data-no');
	
	location.href= contextPath + '/outbound/file/' + seq + '/' + no + '/';	
};

youngjin.outbound.inputPop = function(target){
	var seq = target.parents().parents('.gbl_process').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/modify';
	
	parent.$.smartPop.close();
	
	parent.$.smartPop.open({
		width : 900,
		height : 500,
		url : url
	});
};

youngjin.outbound.gblkModifySubmit = function(target){
	var form = document.forms['gbl'];
	
	var seq = form.seq.value;
	var no = form.no.value;
	var name = form.customerName.value;
	var rank = form.rank.value;
	var code = form.code.value;
	var scac = form.scac.value;
	var originGBlock = form.originGBlock.value;
	var destGBlock = form.destGBlock.value;
	var pud = form.pud.value;
	var ssn = form.ssn.value;
	var rdd = form.rdd.value;
	var pod = form.pod.value;
	var poe = form.poe.value;
	var area = form.areaLocal.value;
	var address = form.originAddress.value;
	var usNo = form.usNo.value;
	var destPort = form.destPort.value;
	var originPort = form.originPort.value;
	var originCity = form.originCity.value;
	var milSVC = form.milSVC.value;
	var etd = form.etd.value;
	var eta = form.eta.value;
	var blNo = form.blNo.value;
	
	var url = contextPath + '/outbound/gblModify.json';
	var json = {
			'seq' : seq,
			'no' : no,
			'customerName' : name,
			'rank' : rank,
			'code' : code,
			'scac' : scac,
			'originGBlock' : originGBlock,
			'destGBlock' : destGBlock,
			'pud' : pud,
			'ssn' : ssn,
			'rdd' : rdd,
			'pod' : pod,
			'poe' : poe,
			'areaLocal' : area,
			'originAddress' : address,
			'usNo' : usNo,
			'destPort' : destPort,
			'originPort' : originPort,
			'originCity' : originCity,
			'milSVC' : milSVC,
			'etd' : etd,
			'eta' : eta,
			'blNo' : blNo
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){				
				var goUrl = contextPath + '/outbound/' + seq;
				
				parent.$.smartPop.close();

				parent.$.smartPop.open({
					width : 1000,
					height : 521,
					url : goUrl
				});					
			}, 
			error : function(){
				alert('에러발생!');
			}
		});
	});
};

youngjin.outbound.preperationPop = function(target){	
	var seq = target.parents().parents('.gbl_process').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/preparation';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 350,
		height: 460,
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
		width: 450,
		height: 350,
		url : url
	});
	
	//window.open(url, 'preperationPop', 'width=350, height=600, screenX=' + x + ',screenY=' + y + ', status=no, scrollbars=no');
	
	//location.href = url;
};

youngjin.outbound.preBack = function(target){
	var form = document.forms['preMoveSurvey'];
	var seq = form.seq.value;	
	
	var url = contextPath + '/outbound/' + seq + '/preparation';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 350,
		height: 460,
		url : url
	});	
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
					width: 350,
					height: 460,
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
					width: 350,
					height: 460,
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
	
	var url = contextPath + '/outbound/' + seq + '/memorandumList';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 700,
		height: 400,
		url : url
	});	
};

youngjin.outbound.addMemorandum = function(target){
	var seq = $('.memorandum_all_table').attr('data-seq');
	
	var url = contextPath + '/outbound/addMemorandumAndDd619.json';
	var json = {
		'gblSeq' : seq
	};
	
	$.postJSON(url, json, function(memorandum){
		return jQuery.ajax({
			success : function(){
				var html = '<tr data-list="' + memorandum.seq + '">' + 
								'<td class="memorandum_list_count">' + ($('.memorandum_list_count:last').html() != undefined ? (Number($('.memorandum_list_count:last').html()) + 1) : 1) + '</td>' +
								'<td>' + memorandum.writeDate + '</td>' + 
								'<td data-check="delete"><img class="memorandum_icon memorandum_icon memorandum_list_delete" src="' + contextPath + '/resources/images/gbl/memorandum_delete.png" /></td>' +
							'</tr>';
				
				if($('.memorandum_list_none').html() != undefined){
					$('.memorandum_list_none').parents('tbody').html(html);
				} else {
					$('.memorandum_all_table').children('tbody').append(html);
				}
				
				youngjin.outbound.memorandumSync();
			},
			error : function(){
				alert('에러 발생!');
			}
		});
	});
};

youngjin.outbound.goToMemorandum = function(target){
	var seq = $('.memorandum_all_table').attr('data-seq');
	var memorandumListSeq = target.parents('tr').attr('data-list');
	var url = contextPath + '/outbound/' + seq + '/' + memorandumListSeq + '/memorandum';
	
	parent.$.smartPop.close();
	
	parent.$.smartPop.open({
		width: 700,
		height: 400,
		url : url
	});
};

youngjin.outbound.memorandumAllListDelete = function(target){
	var parents = target.parents().parents('tr');
	var memorandumSeq = parents.attr('data-list');
	var seq = $('.memorandum_all_table').attr('data-seq');
	
	var url = contextPath + '/outbound/deleteMemorandumAllList.json';
	
	var json = {
			'seq' : memorandumSeq,
			'gblSeq' : seq
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				var beforeUrl = contextPath + '/outbound/' + seq + '/memorandumList';
				
				parent.$.smartPop.close();
				
				parent.$.smartPop.open({
					width: 700,
					height: 400,
					url : beforeUrl
				});				
			},
			error : function(){
				alert('에러 발생');
			}
		});
	});
};

youngjin.outbound.memorandumPop = function(target){	
	var seq = $('.memorandum_table').attr('data-seq');
	var memorandumSeq = $('.memorandum_table').attr('data-memorandumSeq');
	
	var checkbox = target.parents().parents().parents().parents().children('.memorandum_type').children('input');
	var type = checkbox.val();
	
	var url = contextPath + '/outbound/' + seq + '/' + memorandumSeq + '/memorandum/' + type;	
	
	if( type == '02' ){
		var article = target.parents().parents().parents().parents().children('.memorandum_name').children('input');
		
		if(article.val() == '' || article.val() == null){
			youngjin.outbound.sync();
			article.focus();
			return;
		}
		
		url = contextPath + '/outbound/' + seq + '/' + memorandumSeq + '/memorandum/' + type + '/' + article.val();
	}
	
	if( target.parents().parents().parents().parents().children('.memorandum_type').children('input').attr('checked') != 'checked'){
			
		if((type == '01' || type == '02' || type == '03')){
			checkbox.attr('checked', 'checked');
			
			parent.$.smartPop.close();
		
			parent.$.smartPop.open({
				width: 650,
				height: 900,
				url : url
			});	
		} else if( type == '04' || type == '05' || type == '06' || type == '07'|| type == '08' ){
			var inputValue = target.parents().parents().parents().parents().children('.memorandum_name').children('input');
			
			if(inputValue.val() == '' || inputValue.val() == null){
				youngjin.outbound.sync();
				inputValue.focus();
				return;
			}
			
			var json = {
					'memorandumSeq' : memorandumSeq,
					'gblSeq' : seq,
					'type' : type,
					'invoiceValue' : inputValue.val()
			};
			
			url = contextPath + '/outbound/memorandum/invoice/' + inputValue.val() + '/insert.json';

			$.postJSON(url, json, function(){
				return jQuery.ajax({
					success : function(){
						var url = contextPath + '/outbound/' + seq + '/' + memorandumSeq + '/memorandum';
						
						parent.$.smartPop.close();
						
						parent.$.smartPop.open({
							width: 700,
							height: 400,
							url : url
						});
					},
					error: function(){alert("에러 발생!");}
				});
			});		
		}
	} /*else if( type == '01' || type == '02' || type == '03') {
		if(confirm('선택을 해제 하시겠습니까?(yes : 해체 및 삭제)')){
			if( type == '02'){
				target.children('.memorandum_name').children('input').val('');
				checkbox.removeAttr('checked');
			} else {
				checkbox.removeAttr('checked');				
			}
			
			url = contextPath + '/outbound/' + seq + '/' + memorandumSeq + '/memorandum/' + type + '/delete.json';
			
			$.postJSON(url, {}, function(){
				return jQuery.ajax({
					error: function(){alert("에러 발생!");}
				});
			});
		} else {
			if(confirm('수정하시겠습니까?')){	
				parent.$.smartPop.close();
				
				parent.$.smartPop.open({
					width: 650,
					height: 900,
					url : url
				});	
			} else {
				alert("취소 하였습니다.");
			}
		}
	} else if( type == '04' || type == '05' || type == '06' || type == '07'|| type == '08' ){
			if(confirm('선택을 해제 하시겠습니까?(yes : 해체 및 삭제)')){
				if( type == '02'){
					target.children('.memorandum_name').children('input').val('');
					checkbox.removeAttr('checked');
				} else {
					checkbox.removeAttr('checked');				
				}
				
				url = contextPath + '/outbound/' + seq + '/' + memorandumSeq + '/memorandum/' + type + '/delete.json';
				
				$.postJSON(url, {}, function(){
					return jQuery.ajax({
						error: function(){alert("에러 발생!");}
					});
				});
			} else {
				if(confirm('수정하시겠습니까?')){	
					
				}
			}		
	}*/
};

youngjin.outbound.memorandumModify = function(target){
	var seq = $('.memorandum_table').attr('data-seq');
	var memorandumSeq = $('.memorandum_table').attr('data-memorandumSeq');
	
	var checkbox = target.parents().parents().parents().parents().children('.memorandum_type').children('input');
	var type = checkbox.val();
	
	var url = contextPath + '/outbound/' + seq + '/' + memorandumSeq + '/memorandum/' + type;	
	
	if( type == '02' ){
		var article = target.parents().parents().parents().parents().children('.memorandum_name').children('input');
		
		if(article.val() == '' || article.val() == null){
			youngjin.outbound.sync();
			article.focus();
			return;
		}
		
		url = contextPath + '/outbound/' + seq + '/' + memorandumSeq + '/memorandum/' + type + '/' + article.val();
	}	
	
	if(confirm('수정하시겠습니까?')){	
		if( type == '01' || type == '02' || type == '03') {
			parent.$.smartPop.close();
			
			parent.$.smartPop.open({
				width: 650,
				height: 900,
				url : url
			});	
		} else if( type == '04' || type == '05' || type == '06' || type == '07'|| type == '08' ){
			var inputValue = target.parents().parents().parents().parents().children('.memorandum_name').children('input');
			
			var json = {
					'memorandumSeq' : memorandumSeq,
					'gblSeq' : seq,
					'type' : type,
					'invoiceValue' : inputValue.val()
			};
			
			url = contextPath + '/outbound/memorandum/invoice/' + inputValue.val() + '/modify.json';
			
			if(inputValue != target.attr('data-value')){
				$.postJSON(url, json, function(){
					return jQuery.ajax({
						success : function(){
							var url = contextPath + '/outbound/' + seq + '/' + memorandumSeq + '/memorandum';
							
							parent.$.smartPop.close();
							
							parent.$.smartPop.open({
								width: 700,
								height: 400,
								url : url
							});
						},
						error: function(){alert("에러 발생!");}
					});
				});					
			}
		}
	} else {
		alert("취소 하였습니다.");
	}
};

youngjin.outbound.memorandumDelete = function(target){
	var seq = $('.memorandum_table').attr('data-seq');
	var memorandumSeq = $('.memorandum_table').attr('data-memorandumSeq');
	
	var checkbox = target.parents().parents().parents().parents().children('.memorandum_type').children('input');
	var type = checkbox.val();
	
	if( type == '02' ){
		var article = target.parents().parents().parents().parents().children('.memorandum_name').children('input');
		
		if(article.val() == '' || article.val() == null){
			youngjin.outbound.sync();
			article.focus();
			return;
		}
		
		url = contextPath + '/outbound/' + seq + '/' + memorandumSeq + '/memorandum/' + type + '/' + article.val();
	}		
	
	if(confirm('삭제 하시겠습니까?')){
		if( type == '02'){
			target.children('.memorandum_name').children('input').val('');
			checkbox.removeAttr('checked');
		} else {
			checkbox.removeAttr('checked');				
		}
		
		url = contextPath + '/outbound/' + seq + '/' + memorandumSeq + '/memorandum/' + type + '/delete.json';
		
		$.postJSON(url, {}, function(){
			return jQuery.ajax({
				success: function(){
					var url = contextPath + '/outbound/' + seq + '/' + memorandumSeq + '/memorandum';
					
					parent.$.smartPop.close();
					
					parent.$.smartPop.open({
						width: 700,
						height: 400,
						url : url
					});
				},
				error: function(){alert("에러 발생!");}
			});
		});		
	} else {
		alert("취소 하였습니다.");
	}
};

youngjin.outbound.memorandumFormBack = function(target){
	var parents = target.parents().parents().parents().parents('.memorandum_form_content_wrap');
	var seq = parents.attr('data-seq');
	var memorandumSeq = parents.attr('data-memorandumSeq');
	
	var url = contextPath + '/outbound/' + seq + '/' + memorandumSeq + '/memorandum/';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 600,
		height: 248,
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
	var memorandumSeq = parents.attr('data-memorandumSeq');
	
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
		'gblSeq' : gblSeq,
		'memorandumSeq' : memorandumSeq
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				var url = contextPath + '/outbound/' + gblSeq + '/' + memorandumSeq + '/memorandum/';
				
				parent.$.smartPop.close();

				parent.$.smartPop.open({
					width: 600,
					height: 248,
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
	var memorandumSeq = parents.attr('data-memorandumSeq');
	
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
		'gblSeq' : gblSeq,
		'memorandumSeq' : memorandumSeq
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				var url = contextPath + '/outbound/' + gblSeq + '/' + memorandumSeq + '/memorandum/';
				
				parent.$.smartPop.close();

				parent.$.smartPop.open({
					width: 600,
					height: 248,
					url : url
				});					
			},
			
			error: function(){ alert ('에러 발생!'); }
		});
	});
};

youngjin.outbound.memorandumAllBack = function(target){
	var seq = $('.memorandum_all_list_wrap').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/preparation';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 400,
		height: 500,
		url : url
	});
};

youngjin.outbound.memorandumBack = function(target){
	var seq = $('.memorandum_table').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/memorandumList';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 700,
		height: 400,
		url : url
	});
};

youngjin.outbound.dd619Pop = function(target){
	var seq = target.parents('.gbl_preparation_list').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/dd619List';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 1000,
		height: 400,
		url : url
	});
	
};

youngjin.outbound.dd619 = function(target){
	var seq = target.parents().parents('.dd619_table').attr('data-seq');
	var listSeq = target.attr('data-list');
	
	var url = contextPath + '/outbound/' + seq + '/' + listSeq + '/dd619Modify';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width : 700,
		height : 900,
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

youngjin.outbound.dd619FormBack = function(target){
	var seq = $('.dd619_add_table').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/dd619List';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 1000,
		height: 400,
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
					width: 1000,
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

youngjin.outbound.dd619Modify = function(target){
	var table = target.parents().parents().parents().parents('.dd619_add_table');
	var seq = table.attr('data-dd619Seq');
	var memorandumSeq = table.attr('data-memorandumSeq');
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
	
	var count = $('#dd619Count').val();
	
	var invoiceMemorandumType = $('input[name="invoiceMemorandumType"]').eq(0).val();
	for ( var i = 1 ; i < count ; i ++ ){
		invoiceMemorandumType += ',' + $('input[name="invoiceMemorandumType"]').eq(i).val();
	}
	
	var invoiceMemorandumValue = $('input[name="invoiceMemorandumValue"]').eq(0).val();
	for ( var i = 1 ; i < count ; i ++ ){
		invoiceMemorandumValue += ',' + $('input[name="invoiceMemorandumValue"]').eq(i).val();
	}
	
	var url = contextPath + '/outbound/' + gblSeq + '/dd619/' + 'modify.json';
	var json = {
		'seq' : seq,
		'gblSeq' : gblSeq,
		'memorandumListSeq' : memorandumSeq,
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
		'rankAndName' : rankAndName,
		'transportationDate' : transportationDate,
		'remark' : remark,
		'writeUser' : writeUser,
		'invoiceMemorandumType' : invoiceMemorandumType,
		'invoiceMemorandumValue' : invoiceMemorandumValue,
		'count' : count
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){				
				var url = contextPath + '/outbound/' + gblSeq + '/dd619List';
				
				parent.$.smartPop.close();

				parent.$.smartPop.open({
					width: 1000,
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
		width: 1360,
		height: 1760,
		url : url
	});
};

youngjin.outbound.dd619Print = function(target){
	var seq = $('.dd619_add_table').attr('data-seq');
	var dd619Seq = $('.dd619_add_table').attr('data-dd619Seq');
	
	var url = contextPath + '/outbound/' + seq + '/dd619/' + dd619Seq + '/print';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 930.7,
		height: 1122.5,
		url : url
	});	
};

youngjin.outbound.weightCertificate = function(target){
	var seq = target.parents('.gbl_preparation_list').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/weightcertificate';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 1000,
		height: 900,
		url : url
	});
};

youngjin.outbound.weightCertificateBack = function(target){
	var seq = target.parents('.weight_button_list').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/preparation';	
	
	parent.$.smartPop.close();
	
	parent.$.smartPop.open({
		width: 400,
		height: 500,
		url : url
	});	
};

youngjin.outbound.weightCertificateColumnAdd = function(target){
	var count = $('.weightcertificate_table_wrap').attr('data-count');
	var table = $('.weightcertificate_table_wrap table');
	var tbody = table.children('tbody');
	
	count = Number(count) + 1;
		
	var html = '<tr>'+
					'<td class="piece_td"><input name="piece" type="text" /></td>'+
					'<td class="type_td"><input name="type" type="text" /></td>'+
					'<td class="status_td"><input name="status" type="text" /></td>'+
					'<td class="gross_td"><input name="grossKg" type="text" /></td>'+
					'<td class="gross_td"><input name="gross" type="text" /></td>'+
					'<td class="tare_td"><input name="tare" type="text" /></td>'+
					'<td class="net_td"><input name="net" type="text" /></td>'+
					'<td class="cuft_td"><input name="cuft" type="text" /></td>'+
					'<td class="remark_td"><input name="remark" type="text" /></td>'+
					'<td class="gbl_plus_Box_td" style="border-top: 0; border-bottom: 0; border-right: 0;" data-count="0"><div class="gbl_weight_plus_Box"></div></td>' + 
				'</tr>';
	
	tbody.append(html);
	$('.weightcertificate_table_wrap').attr('data-count', count);
	target.css('display', 'none');
	
	youngjin.outbound.sync();
};

youngjin.outbound.weightFromKgToLbs = function(target){
	var kg = target.val();
	var lbsF = Number(kg) * 2.20462262;
	var lbs = roundXL(lbsF, 2);
	
	target.parents().parents('tr').children().children('input[name=gross]').val(lbs);
};	

youngjin.outbound.weightCertificateSubmit = function(target){
	var seq = target.parents('.weight_button_list').attr('data-seq');
	var count = $('.weightcertificate_table_wrap').attr('data-count');
	var form = $('#weightcertificate_form');
	var piece = form.find('input[name="piece"]').eq(0).val();
	var type = form.find('input[name="type"]').eq(0).val();
	var status = form.find('input[name="status"]').eq(0).val();
	var grossKg = form.find('input[name=grossKg]').eq(0).val();
	var gross = form.find('input[name="gross"]').eq(0).val();
	var tare = form.find('input[name="tare"]').eq(0).val();
	var net = form.find('input[name="net"]').eq(0).val();
	var cuft = form.find('input[name="cuft"]').eq(0).val();
	var remark = form.find('input[name="remark"]').eq(0).val();
	
	var progear = $('#weightcertificate_progear').val();
	var sealNo = $('#weightcertificate_seal_no').val();
	var lbs = $('#weightcertificate_lbs').val();
	
	var date = $('#weightcertificate_date').val();
	
	for( var i = 1 ; i < count ; i ++ ){	
		piece += ',' + form.find('input[name="piece"]').eq(i).val();
		type += ',' + form.find('input[name="type"]').eq(i).val();
		status += ',' + form.find('input[name="status"]').eq(i).val();
		grossKg += ',' + form.find('input[name=grossKg]').eq(i).val();
		gross += ',' + form.find('input[name="gross"]').eq(i).val();
		tare += ',' + form.find('input[name="tare"]').eq(i).val();
		net += ',' + form.find('input[name="net"]').eq(i).val();
		cuft += ',' + form.find('input[name="cuft"]').eq(i).val();
		remark += ',' + form.find('input[name="remark"]').eq(i).val();
	}
	
	var url = contextPath + '/outbound/' + seq + '/weightcertificate/add.json';
	var json = {
		"piece" : piece,
		"type" : type,
		"status" : status,
		"grossKg" : grossKg,
		"gross" : gross,
		"tare" : tare,
		"net" : net,
		"cuft" : cuft,
		"remark" : remark,
		"gblSeq" : seq,
		"date" : date,
		"count" : count
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				var goUrl = contextPath + '/outbound/' + seq + '/preparation';
				
				parent.$.smartPop.close();
				
				parent.$.smartPop.open({
					width: 400,
					height: 500,
					url : goUrl
				});
			},
			
			error: function(){
				alert("에러 발생!");
			}
		});
	});
};

youngjin.outbound.finalBack = function(target){
	var table = $('#addition_table');
	var seq = table.attr('data-seq');		
	var url = contextPath + '/outbound/' + seq + '/preparation';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 350,
		height: 460,
		url : url
	});
};

youngjin.outbound.additionalDecide = function(target){
	var seq = target.parents('.gbl_preparation_list').attr('data-seq');
	
	var url = contextPath + '/outbound/' + seq + '/additional';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 400,
		height: 500,
		url : url
	});	
};

youngjin.outbound.addDecideColumn = function(target){
	var tbody = target.parents().parents().parents('tbody');
	
	var html = '<tr>' + 
					'<td><input name="type" type="text" /></td>' +
					'<td><input name="cost" type="text" /> $ </td>' + 
					'<td class="gbl_plus_Box_td" style="border-top: 0; border-bottom: 0; border-right: 0;"><div class="gbl_plus_Box"></div></td>'+
				'</tr>';
	
	target.parents('.gbl_plus_Box_td').css('display', 'none');
	
	tbody.append(html);
	
	youngjin.outbound.sync();
};

youngjin.outbound.additionComplete = function(target){
	var table = $('#addition_table');
	var seq = table.attr('data-seq');
	
	var type = $('input[name=type]').eq(0).val();
	var cost = $('input[name=cost]').eq(0).val();
	
	for( var i = 1 ; i < $('input[name=type]').length ; i ++ ){
		type += ',' + $('input[name=type]').eq(i).val();
		cost += ',' + $('input[name=cost]').eq(i).val();
	}
	
	var url = contextPath + '/outbound/additionComplete.json';
	
	var json = {
			'title' : type,
			'price' : cost,
			'gblSeq' : seq
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){				
				var goUrl = contextPath + '/outbound/' + seq + '/preparation';
				
				parent.$.smartPop.close();

				parent.$.smartPop.open({
					width: 350,
					height: 460,
					url : goUrl
				});
				
			},
			
			error : function(){
				alert("에러 발생!");
			}
		});
	});
};

youngjin.outbound.memorandumPrint = function(target){
	var seq = target.attr('data-seq');
	var type = target.attr('data-type');
	var article = target.attr('data-article');
	var memorandumSeq = target.attr('data-memorandumSeq');
	
	var url;
	
	if( article == null || article == '' || article == 'undefined'){
		url = contextPath + '/outbound/' + seq + '/' + memorandumSeq + '/memorandum/' + type + '/print';
	}	else {
		url = contextPath + '/outbound/' + seq + '/' + memorandumSeq + '/memorandum/' + type + '/' + article + '/print';
	}
	
	window.open(url ,'bookingListPrintPop', 'width=1263, height=892, status=no');	
};