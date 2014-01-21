if (typeof youngjin.inbound == 'undefined') {
	youngjin.inbound = {};
}

$(function() {	
	youngjin.inbound.sync();
});

youngjin.inbound.sync = function(){
	$('.freight_add').unbind('click');
	$('.freight_add').bind('click', function(){
		var url = contextPath + '/inbound/freight/add/';
		$.smartPop.open({
			width : 900,
			height : 500,
			url : url
		});
	});
	
	$('.freight_add_submit_button').unbind('click');
	$('.freight_add_submit_button').bind('click', function(){
		youngjin.inbound.freightAddSubmit();
	});
	
	$('.freight_list').unbind('click');
	$('.freight_list').bind('click', function(){
		youngjin.inbound.freightProcess($(this));
	});
	
	$('input#pud').datepicker();
	$('input#rdd').datepicker();
	
	$('.freight_filter_wrap input#startPud').datepicker({dateFormat: 'yymmdd'});
	$('.freight_filter_wrap input#endPud').datepicker({dateFormat: 'yymmdd'});
	
	$('.freight_filter_wrap select#branch').unbind('change');
	$('.freight_filter_wrap select#branch').bind('change', function(){
		var form = document.forms['inboundFilter'];
		form.submit();
	});
	
	$('.freight_filter_wrap select#carrier').unbind('change');
	$('.freight_filter_wrap select#carrier').bind('change', function(){
		var form = document.forms['inboundFilter'];
		form.submit();
	});
	
	$('.freight_filter_wrap select#code').unbind('change');
	$('.freight_filter_wrap select#code').bind('change', function(){
		var form = document.forms['inboundFilter'];
		form.submit();
	});
	
	$('.freight_filter_wrap input#startPud').unbind('change');
	$('.freight_filter_wrap input#startPud').bind('change', function(){
		var form = document.forms['inboundFilter'];
		form.submit();
	});
	
	$('.freight_filter_wrap input#endPud').unbind('change');
	$('.freight_filter_wrap input#endPud').bind('change', function(){
		var form = document.forms['inboundFilter'];
		form.submit();
	});	
	
	$('.inbound_upload_tfoot .document_upload_button').unbind('click');
	$('.inbound_upload_tfoot .document_upload_button').bind('click', function(event){
		youngjin.inbound.uploadPage($(this));
	});	
	
	$('.document_bl_button').unbind('click');
	$('.document_bl_button').bind('click', function(){
		youngjin.inbound.bl($(this));
	});
	
	$('.inbound_uploadBtn #uploadBtn').unbind('click');
	$('.inbound_uploadBtn #uploadBtn').bind('click', function(){
		youngjin.inbound.uploadSubmit();
	});
	
	$('.inbound_gbl_process_onHand').unbind('click');
	$('.inbound_gbl_process_onHand').bind('click', function(){
		youngjin.inbound.goOnHandList($(this));
	});
	
	$('.inbound_gbl_process_delivery').unbind('click');
	$('.inbound_gbl_process_delivery').bind('click', function(){
		youngjin.inbound.delivery($(this));
	});
	
	$('.inbound_preparation_memorandum').unbind('click');
	$('.inbound_preparation_memorandum').bind('click', function(){
		youngjin.inbound.memorandum($(this));
	});
	
	$('.inbound_preparation_dd619_write').unbind('click');
	$('.inbound_preparation_dd619_write').bind('click', function(){
		youngjin.inbound.dd619Pop($(this));
	});
	
	$('.inbound_preparation_additional_decide').unbind('click');
	$('.inbound_preparation_additional_decide').bind('click', function(){
		
	});
	
	$('.inbound_preparation_complete').unbind('click');
	$('.inbound_preparation_complete').bind('click', function(){
		
	});
	
	$('#inbound_memorandum_all_addButton').unbind('click');
	$('#inbound_memorandum_all_addButton').bind('click', function(){
		youngjin.inbound.addMemorandum($(this));
	});
	
	$('#inbound_memorandum_all_back').unbind('click');
	$('#inbound_memorandum_all_back').bind('click', function(){
		youngjin.inbound.memorandumAllBack($(this));
	});
	
	$('.inbound_memorandum_list_delete').unbind('click');
	$('.inbound_memorandum_list_delete').bind('click', function(){
		youngjin.inbound.memorandumAllListDelete($(this));
	});
	
	$('#inbound_all_table tbody tr td').unbind('click');
	$('#inbound_all_table tbody tr td').bind('click', function(){
		if($(this).attr('data-check') != 'delete'){
			youngjin.inbound.goToMemorandum($(this));
		}
	});
	
	$('#inbound_memo_back_button').unbind('click');
	$('#inbound_memo_back_button').bind('click', function(){
		youngjin.inbound.memorandumBack($(this));		
	});
	
	$('.inbound_memorandum_complete .yj_button').unbind('click');
	$('.inbound_memorandum_complete .yj_button').bind('click', function(){
		youngjin.inbound.memorandumBack($(this));
	});
	
	$('.inbound_memorandum_input_subButton').unbind('click');
	$('.inbound_memorandum_input_subButton').bind('click', function(){
		youngjin.inbound.memorandumPop($(this));
	});
	
	$('.inbound_memorandum_modify_subButton').unbind('click');
	$('.inbound_memorandum_modify_subButton').bind('click', function(){
		youngjin.inbound.memorandumModify($(this));
	});	
	
	$('.inbound_memorandum_delete_subButton').unbind('click');
	$('.inbound_memorandum_delete_subButton').bind('click', function(){
		youngjin.inbound.memorandumDelete($(this));
	});
	
	$('#inbound_memorandum_back').unbind('click');
	$('#inbound_memorandum_back').bind('click', function(){
		youngjin.inbound.memorandumFormBack($(this));
	});
	
	$('#inbound_memorandum_add').unbind('click');
	$('#inbound_memorandum_add').bind('click', function(){
		youngjin.inbound.memorandumAdd($(this));
	});
	
	$('#inbound_memorandum_update').unbind('click');
	$('#inbound_memorandum_update').bind('click', function(){
		youngjin.inbound.memorandumUpdate($(this));
	});
	
	$('#inbound_memorandum_print').unbind('click');
	$('#inbound_memorandum_print').bind('click', function(){
		youngjin.inbound.memorandumPrint($(this));
	});
	
	youngjin.inbound.dd619Sync();
	
	youngjin.inbound.weightSync();
	
	youngjin.inbound.customSync();
};

youngjin.inbound.weightSync = function(){
	$('.weight_plus_Box_td').unbind('click');
	$('.weight_plus_Box_td').bind('click', function(){
		youngjin.inbound.weightColumnAdd($(this));
	});
	
	$('.weight_addButton').unbind('click');
	$('.weight_addButton').bind('click', function(){
		youngjin.inbound.weightAddSubmit($(this));
	});
	
	$('input[name=piece]').focusout(function(){
		var totalPcs = 0;
		var count = Number($('.weight_table').attr('data-count')) + 1;
		for( var i = 0 ; i < count; i ++ ){
			totalPcs += 1;
		}
		
		$('.total_piece_td').html(totalPcs);
	});
	
	$('input[name=gross]').focusout(function(){
		var totalGross = 0;
		var totalGrossKg = 0;
		var totalNet = 0;
		
		var grossKg = 0.45359237 * Number($(this).val());
		$(this).parents().parents('tr').children().children('input[name=grossKg]').val(roundXL(grossKg, 2));
		$(this).parents().parents('tr').children().children('input[name=grossKg]').attr('readonly', 'readonly');
		
		var net = Number($(this).val()) - Number($(this).parents().parents('tr').children().children('input[name=tare]').val());
		$(this).parents().parents('tr').children().children('input[name=net]').val(net);
		$(this).parents().parents('tr').children().children('input[name=net]').attr('readonly', 'readonly');
		
		var count = Number($('.weight_table').attr('data-count')) + 1;
		for( var i = 0 ; i < count ; i ++ ){
			totalGross += Number($('input[name="gross"]').eq(i).val());
			totalGrossKg += roundXL(0.45359237 * Number($('input[name="gross"]').eq(i).val()), 2);
			totalNet += Number($('input[name=net]').eq(i).val());
		}
		
		$('.total_gross_td').html(totalGross);
		$('.total_grossKg_td').html(totalGrossKg);
		$('.total_net_td').html(totalNet);
	});
	
	$('input[name=tare]').focusout(function(){
		var totalTare = 0;
		var totalNet = 0;
		
		var net = Number($(this).parents().parents('tr').children().children('input[name=gross]').val()) - Number($(this).val());
		$(this).parents().parents('tr').children().children('input[name=net]').val(net);
		$(this).parents().parents('tr').children().children('input[name=net]').attr('readonly', 'readonly');
		
		var count = Number($('.weight_table').attr('data-count')) + 1;
		for( var i = 0 ; i < count ; i ++ ){
			totalTare += Number($('input[name="tare"]').eq(i).val());
			totalNet += Number($('input[name=net]').eq(i).val());
		}
		$('.total_tare_td').html(totalTare);
		$('.total_net_td').html(totalNet);
	});
	
	$('input[name=cuft]').focusout(function(){
		var totalCuft = 0;
		var type = '';

		var count = Number($('.weight_table').attr('data-count')) + 1;
		var cuft = $(this).val();
		if(Number(cuft) >= 0 && Number(cuft) <= 124){
			type = 'O/F';
		} else if(Number(cuft) >= 125 && Number(cuft) <= 179 ) {
			type = 'O/F';
		} else if(Number(cuft) >= 180 && Number(cuft) <= 207){
			type = 'typeII';
		}
		
		$(this).parents().parents('tr').children().children('input[name=type]').val(type);
		$(this).parents().parents('tr').children().children('input[name=type]').attr('readonly', 'readonly');
		
		for( var i = 0 ; i < count ; i ++ ){
			totalCuft += Number($('input[name="cuft"]').eq(i).val());
		}
		$('.total_cuft_td').html(totalCuft);
		
	});
};

youngjin.inbound.customSync = function(){
	$('.inbound_invoice_add_button').unbind('click');
	$('.inbound_invoice_add_button').bind('click', function(){
		var url = contextPath + '/inbound/custom/invoice/add';
		
		$.smartPop.open({
			width: 1000,
			height: 700,
			url : url
		});		
	});
	
	$('.inbound_invoice_gbl_list').unbind('click');
	$('.inbound_invoice_gbl_list').bind('click', function(){
		youngjin.inbound.customInoviceAddSetting($(this));
	});
	
	$('.inbound_invoice_add_back').unbind('click');
	$('.inbound_invoice_add_back').bind('click', function(){
		var url = contextPath + '/inbound/custom/invoice/add';
		
		parent.$.smartPop.close();
		parent.$.smartPop.open({
			width: 1000,
			height: 700,
			url : url
		});		
		
	});
	
	$('.inbound_invoice_add_next').unbind('click');
	$('.inbound_invoice_add_next').bind('click', function(){
		youngjin.inbound.invoiceAddNext($(this));
	});
	
	$('.inbound_invoice_select_weight_add').unbind('click');
	$('.inbound_invoice_select_weight_add').bind('click', function(){
		youngjin.inbound.invoiceSelectWeight($(this));
	});
	
	$('.inbound_invoice_tr').unbind('click');
	$('.inbound_invoice_tr').bind('click', function(){
		youngjin.inbound.inboundInvoicePop($(this));
	});
};

youngjin.inbound.dd619Sync = function(){	
	$('.inbound_dd619_back').unbind('click');
	$('.inbound_dd619_back').bind('click', function(){
		youngjin.inbound.dd619Back($(this));
	});
	
	$('.inbound_dd619_table tr').unbind('click');
	$('.inbound_dd619_table tr').bind('click', function(){
		youngjin.inbound.dd619($(this));
	});
	
	$('.inbound_dd619_form_back').unbind('click');
	$('.inbound_dd619_form_back').bind('click', function(){
		youngjin.inbound.dd619FormBack($(this));
	});
	
	$('.inbound_dd619_modify_submit_button').unbind('click');
	$('.inbound_dd619_modify_submit_button').bind('click', function(){
		youngjin.inbound.dd619Modify($(this));
	});
	
	$('.inbound_dd619_form_print').unbind('click');
	$('.inbound_dd619_form_print').bind('click', function(){
		youngjin.inbound.dd619Print($(this));
	});
};

youngjin.inbound.freightAddSubmit = function(){
	var form = document.forms['gbl'];
	form.submit();	
};

youngjin.inbound.freightProcess = function(target){
	var seq = target.attr('data-seq');
	
	var json = {
			'seq' : seq
	};
	
	var url = contextPath + '/inbound/freight/checkWeight.json';
	
	$.postJSON(url, json, function(data){
		return jQuery.ajax({
			success : function(){
				if(data == true){
					var url = contextPath + '/inbound/freight/' + seq;
					
					$.smartPop.open({
						width : 1000,
						height : 521,
						url : url
					});					
				} else {
					var url = contextPath + '/inbound/freight/' + seq + '/weight';
					
					$.smartPop.open({
						width : 700,
						height : 500,
						url : url
					});		
					
				}
			},
			error : function(){
				
			}
		});	
	});
};

youngjin.inbound.uploadPage = function(target){
	var parent = target.parents();
	var seq = parent.attr('data-seq');
	var url = contextPath + '/inbound/freight/' + seq + '/upload';
	
	window.open(url, 'uploadPop', 'width=350, height=180, status=no, scrollbars=no');
	
};

youngjin.inbound.bl = function(target){
	var seq = $('.inbound_gbl_process').attr('data-seq');
	
	var url = contextPath + '/inbound/freight/' + seq + '/bl';
	
	window.open(url ,'declarationList', 'width=1263, height=892, status=no');		
};

youngjin.inbound.uploadSubmit = function(){
	var form = $('form#gbl');
	var seq = $('#uploadBtn').attr('data-seq');		
	
	form.submit();	

	url = contextPath + '/inbound/' + seq;
	
	//window.close();
	/*
	parent.parent.$.smartPop.close();
	
	parent.parent.$.smartPop.open({
		width : 1000,
		height : 521,
		url : url
	});		*/
};

youngjin.inbound.goOnHandList = function(target){
	
};

youngjin.inbound.delivery = function(target){
	var seq = target.parents().parents('.inbound_gbl_process').attr('data-seq');
	
	var url = contextPath + '/inbound/freight/' + seq + '/delivery';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 350,
		height: 310,
		url : url
	});
	
};

youngjin.inbound.memorandum = function(target){
	var seq = target.parents('.gbl_preparation_list').attr('data-seq');
	
	var url = contextPath + '/inbound/freight/' + seq + '/memorandumList';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 700,
		height: 400,
		url : url
	});	
};

youngjin.inbound.addMemorandum = function(target){
	var seq = $('#inbound_all_table').attr('data-seq');
	
	var url = contextPath + '/inbound/freight/addMemorandumAndDd619.json';
	var json = {
		'gblSeq' : seq
	};
	
	$.postJSON(url, json, function(memorandum){
		return jQuery.ajax({
			success : function(){
				var html = '<tr data-list="' + memorandum.seq + '">' + 
								'<td class="memorandum_list_count">' + ($('.memorandum_list_count:last').html() != undefined ? (Number($('.memorandum_list_count:last').html()) + 1) : 1) + '</td>' +
								'<td>' + memorandum.writeDate + '</td>' + 
								'<td data-check="delete"><img class="memorandum_icon inbound_memorandum_list_delete" src="' + contextPath + '/resources/images/gbl/memorandum_delete.png" /></td>' +
							'</tr>';
				
				if($('.memorandum_list_none').html() != undefined){
					$('.memorandum_list_none').parents('tbody').html(html);
				} else {
					$('.memorandum_all_table').children('tbody').append(html);
				}
				
				youngjin.inbound.sync();
			},
			error : function(){
				alert('에러 발생!');
			}
		});
	});
};

youngjin.inbound.memorandumAllBack = function(target){
	var seq = $('.memorandum_all_list_wrap').attr('data-seq');
	
	var url = contextPath + '/inbound/freight/' + seq + '/delivery';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 350,
		height: 310,
		url : url
	});
};

youngjin.inbound.memorandumAllListDelete = function(target){
	var parents = target.parents().parents('tr');
	var memorandumSeq = parents.attr('data-list');
	var seq = $('.memorandum_all_table').attr('data-seq');
	
	var url = contextPath + '/inbound/freight/deleteMemorandumAllList.json';
	
	var json = {
			'seq' : memorandumSeq,
			'gblSeq' : seq
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				var beforeUrl = contextPath + '/inbound/freight/' + seq + '/memorandumList';
				
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

youngjin.inbound.goToMemorandum = function(target){
	var seq = $('.memorandum_all_table').attr('data-seq');
	var memorandumListSeq = target.parents('tr').attr('data-list');
	var url = contextPath + '/inbound/freight/' + seq + '/' + memorandumListSeq + '/memorandum';
	
	parent.$.smartPop.close();
	
	parent.$.smartPop.open({
		width: 700,
		height: 400,
		url : url
	});
};

youngjin.inbound.memorandumBack = function(target){
	var seq = $('.memorandum_table').attr('data-seq');
	
	var url = contextPath + '/inbound/freight/' + seq + '/memorandumList';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 700,
		height: 400,
		url : url
	});
};

youngjin.inbound.memorandumBack = function(target){
	var seq = $('.memorandum_table').attr('data-seq');
	
	var url = contextPath + '/inbound/freight/' + seq + '/memorandumList';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 700,
		height: 400,
		url : url
	});
};

youngjin.inbound.memorandumPop = function(target){	
	var seq = $('.memorandum_table').attr('data-seq');
	var memorandumSeq = $('.memorandum_table').attr('data-memorandumSeq');
	
	var checkbox = target.parents().parents().parents().parents().children('.memorandum_type').children('input');
	var type = checkbox.val();
	
	var url = contextPath + '/inbound/freight/' + seq + '/' + memorandumSeq + '/memorandum/' + type;	
	
	if( type == '02' ){
		var article = target.parents().parents().parents().parents().children('.memorandum_name').children('input');
		
		if(article.val() == '' || article.val() == null){
			youngjin.inbound.sync();
			article.focus();
			return;
		}
		
		url = contextPath + '/inbound/freight/' + seq + '/' + memorandumSeq + '/memorandum/' + type + '/' + article.val();
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
				youngjin.inbound.sync();
				inputValue.focus();
				return;
			}
			
			var json = {
					'memorandumSeq' : memorandumSeq,
					'gblSeq' : seq,
					'type' : type,
					'invoiceValue' : inputValue.val()
			};
			
			url = contextPath + '/inbound/freight/memorandum/invoice/' + inputValue.val() + '/insert.json';

			$.postJSON(url, json, function(){
				return jQuery.ajax({
					success : function(){
						var url = contextPath + '/inbound/freight/' + seq + '/' + memorandumSeq + '/memorandum';
						
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


youngjin.inbound.memorandumModify = function(target){
	alert("!!");
	
	var seq = $('.memorandum_table').attr('data-seq');
	var memorandumSeq = $('.memorandum_table').attr('data-memorandumSeq');
	
	var checkbox = target.parents().parents().parents().parents().children('.memorandum_type').children('input');
	var type = checkbox.val();
	
	var url = contextPath + '/inbound/freight/' + seq + '/' + memorandumSeq + '/memorandum/' + type;	
	
	if( type == '02' ){
		var article = target.parents().parents().parents().parents().children('.memorandum_name').children('input');
		
		if(article.val() == '' || article.val() == null){
			youngjin.inbound.sync();
			article.focus();
			return;
		}
		
		url = contextPath + '/inbound/freight/' + seq + '/' + memorandumSeq + '/memorandum/' + type + '/' + article.val();
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
			
			url = contextPath + '/inbound/freight/memorandum/invoice/' + inputValue.val() + '/modify.json';
			
			if(inputValue != target.attr('data-value')){
				$.postJSON(url, json, function(){
					return jQuery.ajax({
						success : function(){
							var url = contextPath + '/inbound/freight/' + seq + '/' + memorandumSeq + '/memorandum';
							
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

youngjin.inbound.memorandumDelete = function(target){
	var seq = $('.memorandum_table').attr('data-seq');
	var memorandumSeq = $('.memorandum_table').attr('data-memorandumSeq');
	
	var checkbox = target.parents().parents().parents().parents().children('.memorandum_type').children('input');
	var type = checkbox.val();
	
	if( type == '02' ){
		var article = target.parents().parents().parents().parents().children('.memorandum_name').children('input');
		
		if(article.val() == '' || article.val() == null){
			youngjin.inbound.sync();
			article.focus();
			return;
		}
		
		url = contextPath + '/inbound/freight/' + seq + '/' + memorandumSeq + '/memorandum/' + type + '/' + article.val();
	}		
	
	if(confirm('삭제 하시겠습니까?')){
		if( type == '02'){
			target.children('.memorandum_name').children('input').val('');
			checkbox.removeAttr('checked');
		} else {
			checkbox.removeAttr('checked');				
		}
		
		url = contextPath + '/inbound/freight/' + seq + '/' + memorandumSeq + '/memorandum/' + type + '/delete.json';
		
		$.postJSON(url, {}, function(){
			return jQuery.ajax({
				success: function(){
					var url = contextPath + '/inbound/freight/' + seq + '/' + memorandumSeq + '/memorandum';
					
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

youngjin.inbound.memorandumFormBack = function(target){
	var parents = target.parents().parents().parents().parents('.memorandum_form_content_wrap');
	var seq = parents.attr('data-seq');
	var memorandumSeq = parents.attr('data-memorandumSeq');
	
	var url = contextPath + '/inbound/freight/' + seq + '/' + memorandumSeq + '/memorandum/';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 600,
		height: 248,
		url : url
	});	
};

youngjin.inbound.memorandumAdd = function(target){
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
	
	var url = contextPath + '/inbound/freight/' + gblSeq + '/memorandum/memorandumInput.json';
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
				var url = contextPath + '/inbound/freight/' + gblSeq + '/' + memorandumSeq + '/memorandum/';
				
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

youngjin.inbound.memorandumUpdate = function(target){
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
	
	var url = contextPath + '/inbound/freight/' + gblSeq + '/memorandum/memorandumUpdate.json';
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
				var url = contextPath + '/inbound/freight/' + gblSeq + '/' + memorandumSeq + '/memorandum/';
				
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

youngjin.inbound.memorandumPrint = function(target){
	var seq = target.attr('data-seq');
	var type = target.attr('data-type');
	var article = target.attr('data-article');
	var memorandumSeq = target.attr('data-memorandumSeq');
	
	var url;
	
	if( article == null || article == '' || article == 'undefined'){
		url = contextPath + '/inbound/freight/' + seq + '/' + memorandumSeq + '/memorandum/' + type + '/print';
	}	else {
		url = contextPath + '/inbound/freight/' + seq + '/' + memorandumSeq + '/memorandum/' + type + '/' + article + '/print';
	}
	
	window.open(url ,'memorandumPrint', 'width=1263, height=892, status=no');	
};

youngjin.inbound.dd619Pop = function(target){
	var seq = target.parents('.gbl_preparation_list').attr('data-seq');
	
	var url = contextPath + '/inbound/freight/' + seq + '/dd619List';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 1000,
		height: 400,
		url : url
	});
	
};

youngjin.inbound.weightColumnAdd = function(target){
	var column = '<tr>' +
					'<input type="hidden" name="gblSeq" id="gblSeq" value="' + $("#gblSeq").val()  + '" />' +
					'<td><input type="text" id="piece" name="piece"/></td>' +
					'<td><input type="text" id="type" name="type"/></td>' +
					'<td><input type="text" id="gross" name="gross"/></td>' +
					'<td><input type="text" id="grossKg" name="grossKg"/></td>' +
					'<td><input type="text" id="tare" name="tare"/></td>' +
					'<td><input type="text" id="net" name="net"/></td>' +
					'<td><input type="text" id="cuft" name="cuft"/></td>' +
					'<td><input type="text" id="reweight" name="reweight"/></td>' +
					'<td class="weight_remark"><input type="text" id="remark" name="remark"/></td>' +
					'<td class="weight_plus_Box_td" style="border-top: 0; border-bottom: 0; border-right: 0;" data-count="0"><div class="gbl_weight_plus_Box"></div></td>';
				 '</tr>';
	
	if(target.parents('tr').find('#cuft').val() != ''){
		target.parents().parents('tbody').append(column);
		
		target.remove();
		
		var count = $('.weight_table').attr('data-count');
		$('.weight_table').attr('data-count', Number(count) + 1);
		
		var totalPcs = 0;
		var count = Number($('.weight_table').attr('data-count')) + 1;
		for( var i = 0 ; i < count; i ++ ){
			totalPcs += 1;
		}
		
		$('.total_piece_td').html(totalPcs);
	} else {
		alert('값을 모두 입력해야 열 추가가 가능합니다');
	}
	
	youngjin.inbound.sync();
};

youngjin.inbound.weightAddSubmit = function(target){
	 var form = document.forms['weightIb'];

	 form.submit();	 
};

youngjin.inbound.customInoviceAddSetting = function(target){
	var gblSeq = target.attr('data-seq');
	
	var url = contextPath + '/inbound/custom/invoice/' + gblSeq + '/setting';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 600,
		height: 180,
		url : url
	});	
};

youngjin.inbound.invoiceAddNext = function(target){
	var gblNo = $('#inbound_invoice_add_gblNo').val();
	var invoiceDate = $('#inbound_invoice_add_date').val();
	var inboundInvoiceNo = $('#inbound_invoice_add_no').val();
	var gblSeq = target.attr('data-seq');
	
	var url = contextPath + '/inbound/custom/invoice/inboundInvoiceAdd.json';
	
	var json = {
		'gblNo' : gblNo,
		'invoiceDate' : invoiceDate,
		'inboundInvoiceNo' : inboundInvoiceNo,
		'gblSeq' : gblSeq
	};
	
	$.postJSON(url, json, function(data){
		return jQuery.ajax({
			success : function(){
				var url = contextPath + '/inbound/custom/invoice/' + data + '/selectWeight';
				
				parent.$.smartPop.close();
				
				parent.$.smartPop.open({
					width : 700,
					height : 500,
					url : url
				});		
			},
			error : function(){
				alert('에러 발생!');
			}
		});
	});
};

youngjin.inbound.invoiceSelectWeight = function(target){
	var inboundInvoiceSeq = target.attr('data-inboundInvoiceSeq');
	
	var weightSeqList = $('input:checked');
	var count = weightSeqList.length;
	var weightSeqCommaList = weightSeqList.eq(0).val();
	
	for( var i = 1 ; i < count ; i ++ ){
		weightSeqCommaList += ',' + weightSeqList.eq(i).val();
	}
	
	var url = contextPath + '/inbound/custom/invoice/invoiceSelectWeightAdd.json';
	
	var json = {
		'inboundInvoiceSeq' : inboundInvoiceSeq,
		'count' : count,
		'weightSeqCommaList' : weightSeqCommaList
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				alert('입력 완료!');
			},
			error : function(){
				alert('에러발생!');
			}
		});
	});
};

youngjin.inbound.inboundInvoicePop = function(target){
	var inboundInvoiceSeq = target.attr('data-inboundInvoiceSeq');
	var gblSeq = target.attr('data-gblSeq');
	
	var url = contextPath + '/inbound/custom/invoice/checkInboundInvoiceWeight.json';
	
	var json = {
			'seq' : inboundInvoiceSeq,
			'gblSeq' : gblSeq
	};
	
	$.postJSON(url, json, function(data){
		return jQuery.ajax({
			success : function(){
				if(data == false){
					goUrl = contextPath + '/inbound/custom/invoice/' + inboundInvoiceSeq + '/selectWeight';
					
					$.smartPop.open({
						width : 700,
						height : 500,
						url : goUrl
					});		
				} else {
					
				}
			}, 
			error : function(){
				alert('에러 발생!');
			}
		});
	});
};

youngjin.inbound.dd619Back = function(target){
	var seq = target.parents('.inbound_dd619_list_wrap').attr('data-seq');
	
	var url = contextPath + '/inbound/freight/' + seq + '/delivery';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 350,
		height: 310,
		url : url
	});
};

youngjin.inbound.dd619 = function(target){
	var seq = target.parents().parents('.inbound_dd619_table').attr('data-seq');
	var listSeq = target.attr('data-list');
	
	var url = contextPath + '/inbound/freight/' + seq + '/' + listSeq + '/dd619Modify';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width : 700,
		height : 900,
		url : url
	});
};

youngjin.inbound.dd619FormBack = function(target){
	var seq = $('.inbound_dd619_add_table').attr('data-seq');
	
	var url = contextPath + '/inbound/freight/' + seq + '/dd619List';
	
	parent.$.smartPop.close();

	parent.$.smartPop.open({
		width: 1000,
		height: 400,
		url : url
	});
};

youngjin.inbound.dd619Modify = function(target){
	var table = target.parents().parents().parents().parents('.inbound_dd619_add_table');
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
	
	var url = contextPath + '/inbound/freight/' + gblSeq + '/dd619/' + 'modify.json';
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
				var url = contextPath + '/inbound/freight/' + gblSeq + '/dd619List';
				
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

youngjin.inbound.dd619Print = function(target){
	var seq = $('.inbound_dd619_add_table').attr('data-seq');
	var dd619Seq = $('.inbound_dd619_add_table').attr('data-dd619Seq');
	
	var url = contextPath + '/inbound/freight/' + seq + '/dd619/' + dd619Seq + '/print';
	
	window.open(url, 'dd619Print', 'width=1224, height=1584, status=no, scrollbars=no');
};