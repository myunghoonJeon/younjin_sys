if (typeof youngjin.invoice == 'undefined') {
	youngjin.invoice = {};
}

$(function() {	
	youngjin.invoice.sync();
});

youngjin.invoice.sync = function(){
	youngjin.invoice.rateSync();
	
	youngjin.invoice.invoiceListSync();
	
	youngjin.invoice.invoiceCollectionSync();
	
	youngjin.invoice.backSync();
};

youngjin.invoice.invoiceListSync = function(){
	$('.invoice_add_button').unbind('click');
	$('.invoice_add_button').bind('click', function(){
		youngjin.invoice.invoiceListAddPop($(this), 'outbound');
	});
	
	$('.inboundInvoice_add_button').unbind('click');
	$('.inboundInvoice_add_button').bind('click', function(){
		youngjin.invoice.invoiceListAddPop($(this), 'inbound');
	});
	
/*	$('input#invoice_add_start_date').datepicker({dateFormat: 'yymmdd'});
	$('input#invoice_add_end_date').datepicker({dateFormat: 'yymmdd'});*/
	
	$('.invoice_gbl_addButton').unbind('click');
	$('.invoice_gbl_addButton').bind('click', function(){
		youngjin.invoice.invoiceListAddSubmit($(this), 'outbound');
	});
	
	$('.inbound_invoice_gbl_addButton').unbind('click');
	$('.inbound_invoice_gbl_addButton').bind('click', function(){
		youngjin.invoice.invoiceListAddSubmit($(this), 'inbound');
	});
	
	$('.invoice_gbl_list_table tbody tr').unbind('click');
	$('.invoice_gbl_list_table tbody tr').bind('click', function(){
		youngjin.invoice.invoiceGblContentPop($(this), 'outbound');
	});
	
	$('.inbound_invoice_gbl_list_table tbody tr').unbind('click');
	$('.inbound_invoice_gbl_list_table tbody tr').bind('click', function(){
		youngjin.invoice.invoiceGblContentPop($(this), 'inbound');
	});
	
	$('.invoice_table tr').unbind('click');
	$('.invoice_table tr').bind('click', function(){
		if($(this).attr('data-click') == 'yes'){
			$(this).attr('data-click', 'no');
		} else {
			youngjin.invoice.invoiceList($(this), 'outbound');
		}
	});
	
	$('.inbound_invoice_table tr').unbind('click');
	$('.inbound_invoice_table tr').bind('click', function(){
		if($(this).attr('data-click') == 'yes'){
			$(this).attr('data-click', 'no');
		} else {
			youngjin.invoice.invoiceList($(this), 'inbound');
		}
	});	
	
	$('.invoice_gbl_print').unbind('click');
	$('.invoice_gbl_print').bind('click', function(){
		youngjin.invoice.invoiceGblPrint($(this), 'outbound');
	});
	
	$('.inbound_invoice_gbl_print').unbind('click');
	$('.inbound_invoice_gbl_print').bind('click', function(){
		youngjin.invoice.invoiceGblPrint($(this), 'inbound');
	});
	
	$('.invoice_all_delete').unbind('click');
	$('.invoice_all_delete').bind('click', function(){
		youngjin.invoice.inboundGbDelete($(this), 'outbound');
		
		$(this).parents('tr').attr('data-click', 'yes');
	});
	
	$('.inbound_invoice_all_delete').unbind('click');
	$('.inbound_invoice_all_delete').bind('click', function(){
		youngjin.invoice.inboundGbDelete($(this), 'inbound');
		
		$(this).parents('tr').attr('data-click', 'yes');
	});
};

youngjin.invoice.rateSync = function(){

	$('.basic_rate_input').unbind('click');
	$('.basic_rate_input').bind('click', function(){
		var rate = $(this).val();
		$(this).attr('data-rate', rate);
	});

	$('.container_rate_input').unbind('click');
	$('.container_rate_input').bind('click', function(){
		var rate = $(this).val();
		$(this).attr('data-rate', rate);
	});
	
	$('.sit_rate_input').unbind('click');
	$('.sit_rate_input').bind('click', function(){
		var rate = $(this).val();
		$(this).attr('data-rate', rate);
	});	
	
	$('.other_rate_input').unbind('click');
	$('.other_rate_input').bind('click', function(){
		var rate = $(this).val();
		$(this).attr('data-rate', rate);
	});	
	
	$('.basic_rate_input').focusout(function(){
		youngjin.invoice.basicRateInput($(this));
	});
	
	$('.comprate_input').focusout(function(){
		youngjin.invoice.compRateInput($(this));
	});
	
	$('.container_rate_input').focusout(function(){
		youngjin.invoice.containerRateInput($(this));
	});
	
	$('.sit_rate_input').focusout(function(){
		youngjin.invoice.sitInput($(this));
	});
	
	$('.other_rate_input').focusout(function(){
		youngjin.invoice.otherInput($(this));
	});
	
	$('.invoice_gbl_list_tr').unbind('click');
	$('.invoice_gbl_list_tr').bind('click', function(){
		if($(this).find('input').attr('checked') != 'checked')
			$(this).find('input').attr('checked', 'checked');
		else 
			$(this).find('input').removeAttr('checked');	
	});

	$('input#startDate').datepicker({dateFormat: 'yymmdd'});
	$('input#endDate').datepicker({dateFormat: 'yymmdd'});
	
	$('select#tsp').unbind('change');
	$('select#tsp').bind('change', function(){
		var form = document.forms['invoiceFilter'];
		form.method = 'post';
		form.submit();
	});
	
	$('input#startDate').unbind('change');
	$('input#startDate').bind('change', function(){
		var form = document.forms['invoiceFilter'];
		form.method = 'post';
		form.submit();
	});
	
	$('input#endDate').unbind('change');
	$('input#endDate').bind('change', function(){
		var form = document.forms['invoiceFilter'];
		form.method = 'post';
		form.submit();
	});
	
	$('input#gblNo').unbind('change');
	$('input#gblNo').bind('change', function(){
		var form = document.forms['invoiceFilter'];
		form.method = 'post';
		form.submit();
	});
};

youngjin.invoice.invoiceCollectionSync = function(){
/*	$('.collection_net').focusout(function(){
		youngjin.invoice.inputCollectionNet($(this));
	});*/
	
	$('.collection_plus').unbind('click');
	$('.collection_plus').bind('click', function(){
		$(this).parents().parents().parents().parents('tr').attr('data-click', 'yes');
		youngjin.invoice.inputCollectionFlowTable($(this));
	});
	
	$('.collection_delete').unbind('click');
	$('.collection_delete').bind('click', function(){
		$(this).parents().parents().parents().parents('tr').attr('data-click', 'yes');

		if($(this).parents('.collection_flow_wrap').find('.collection_flow_table').attr('data-flowSeq') == undefined){
			$('.collection_flow_table:last').parents('li').remove();
			
			if($('.collection_flow_table').html() == undefined){
				$(this).remove();
				$('.collection_save').remove();
			}
		} else {
			if($(this).parents().parents('ul').find('.collection_flow_table:last').attr('data-flowSeq') != undefined)
				youngjin.invoice.collectionDelete($(this));
			else {
				$(this).parents().parents('ul').find('.collection_flow_table:last').parents('li').remove();
				
				if($(this).parents().parents('ul').find('.collection_flow_table:last').html() != undefined && $(this).parents().parents('ul').find('.collection_flow_table:last').attr('data-flowSeq') != undefined){
					$(this).parents().children('.collection_save').remove();
				}				
			}
		}
	});
	
	$('.collection_flow_table tr').unbind('click');
	$('.collection_flow_table tr').bind('click', function(){
		$(this).parents().parents().parents().parents().parents('tr').attr('data-click', 'yes');
		$(this).attr('data-click', 'yes');
	});
	
	$('.collection_flow_table tr td').unbind('click');
	$('.collection_flow_table tr td').bind('click', function(){
		$(this).parents().parents().parents().parents().parents().parents('tr').attr('data-click', 'yes');
		$(this).parents('tr').attr('data-click', 'yes');
	});
	
	$('.collection_flow_table tr td input').unbind('click');
	$('.collection_flow_table tr td input').bind('click', function(){
		$(this).parents().parents().parents().parents.parents().parents().parents('tr').attr('data-click', 'yes');
		$(this).parents().parents('tr').attr('data-click', 'yes');
	});
	
	$('.collection_flow_table tr td select').unbind('click');
	$('.collection_flow_table tr td select').bind('click', function(){
		$(this).parents().parents().parents().parents.parents().parents().parents('tr').attr('data-click', 'yes');
		$(this).parents().parents('tr').attr('data-click', 'yes');
	});
	
	$('.collection_flow_table tr td textarea').unbind('click');
	$('.collection_flow_table tr td textarea').bind('click', function(){
		$(this).parents().parents().parents().parents.parents().parents().parents('tr').attr('data-click', 'yes');
		$(this).parents().parents('tr').attr('data-click', 'yes');
	});
	
	$('.collection_save').unbind('click');
	$('.collection_save').bind('click', function(){
		$(this).parents().parents().parents().parents('tr').attr('data-click', 'yes');
		youngjin.invoice.collectionSave($(this));
	});
	
	$('.inbound_invoice_collection_table tbody tr').unbind('click');
	$('.inbound_invoice_collection_table tbody tr').bind('click', function(){
		if($(this).attr('data-click') == 'yes'){
			$(this).attr('data-click', 'no');
		} else {
			youngjin.invoice.invoiceCollectionGbl($(this), 'inbound');
		}
	});
	
	$('.invoice_collection_table tbody tr').unbind('click');
	$('.invoice_collection_table tbody tr').bind('click', function(){
		if($(this).attr('data-click') == 'yes'){
			$(this).attr('data-click', 'no');
		} else {
			youngjin.invoice.invoiceCollectionGbl($(this), 'outbound');
		}
	});
	
	$('.collection_gbl_plus').unbind('click');
	$('.collection_gbl_plus').bind('click', function(){
		$(this).parents().parents().parents().parents('tr').attr('data-click', 'yes');
		youngjin.invoice.inputGblCollectionFlowTable($(this));
	});
	
	$('.collection_gbl_delete').unbind('click');
	$('.collection_gbl_delete').bind('click', function(){
		$(this).parents().parents().parents().parents('tr').attr('data-click', 'yes');

		if($(this).parents('.collection_gbl_flow_wrap').find('.collection_flow_table').attr('data-flowSeq') == undefined){
			$('.collection_flow_table:last').parents('li').remove();
			
			if($('.collection_flow_table').html() == undefined){
				$(this).remove();
				$('.collection_save').remove();
			}
		} else {
			if($(this).parents().parents('ul').find('.collection_flow_table:last').attr('data-flowSeq') != undefined)
				youngjin.invoice.collectionGblDelete($(this));
			else {
				$(this).parents().parents('ul').find('.collection_flow_table:last').parents('li').remove();
				
				if($(this).parents().parents('ul').find('.collection_flow_table:last').html() != undefined && $(this).parents().parents('ul').find('.collection_flow_table:last').attr('data-flowSeq') != undefined){
					$(this).parents().children('.collection_save').remove();
				}				
			}
		}
	});
	
	$('.collection_gbl_save').unbind('click');
	$('.collection_gbl_save').bind('click', function(){
		$(this).parents().parents().parents().parents('tr').attr('data-click', 'yes');
		youngjin.invoice.collectionGblSave($(this));
	});
	
	$('.invoice_gbl_collection_list_table tr').unbind('click');
	$('.invoice_gbl_collection_list_table tr').bind('click', function(){
		if($(this).attr('data-click') == 'yes'){
			$(this).attr('data-click', 'no');
		}else {
			youngjin.invoice.invoiceGblCollectionContent($(this), 'outbound');
		}
	});
	
	$('.inbound_invoice_gbl_collection_list_table tr').unbind('click');
	$('.inbound_invoice_gbl_collection_list_table tr').bind('click', function(){
		if($(this).attr('data-click') == 'yes'){
			$(this).attr('data-click', 'no');
		}else {
			youngjin.invoice.invoiceGblCollectionContent($(this), 'inbound');
		}
	});
	
	$('.collection_remark').focusout(function(){
		youngjin.invoice.inputGblCollectionRemark($(this));
	});
	
	$('.invoice_gbl_content_list_pdf').unbind('click');
	$('.invoice_gbl_content_list_pdf').bind('click',function(){
		youngjin.invoice.gblCollectionPdf($(this), 'outbound');
	});
	
	$('.inbound_invoice_gbl_content_list_pdf').unbind('click');
	$('.inbound_invoice_gbl_content_list_pdf').bind('click',function(){
		youngjin.invoice.gblCollectionPdf($(this), 'inbound');
	});
};

youngjin.invoice.backSync = function(){
	$('.invoice_gbl_list_back').unbind('click');
	$('.invoice_gbl_list_back').bind('click', function(){
		youngjin.invoice.invoiceGblListBack($(this));
	});
	
	$('.invoice_gbl_content_list_back').unbind('click');
	$('.invoice_gbl_content_list_back').bind('click', function(){
		youngjin.invoice.invoiceGblContentBack($(this), 'outbound');
	});
	
	$('.inbound_invoice_gbl_content_list_back').unbind('click');
	$('.inbound_invoice_gbl_content_list_back').bind('click', function(){
		youngjin.invoice.invoiceGblContentBack($(this), 'inbound');
	});
};

youngjin.invoice.basicRateInput = function(target){
	var parent = target.parents('td');
	var tsp = parent.attr('data-tsp');
	var code = parent.attr('data-code');
	var process = parent.attr('data-process');
	var writeYear = parent.attr('data-writeYear');
	var obType;
	var rate = target.val();
	
	if( parent.attr('data-obType') != 'undefined' && parent.attr('data-obType') != ''){
		obType = parent.attr('data-obType');
	} else {
		obType = null;
	}
	
	var url = contextPath + '/admin/rate/basic/insert.json';
	var json = {
		'tsp' : tsp,
		'code' : code,
		'process' : process,
		'obType' : obType,
		'writeYear' : writeYear,
		'rate' : rate
	};
	
	if(target.attr('data-rate') != rate ){
		$.postJSON(url, json, function(){
			return jQuery.ajax({
				success : function(){
					alert("변경 완료");
				},
				error : function(){
					alert("에러 발생");
				}
				
			});
		});
	}
};

youngjin.invoice.compRateInput = function(target){
	var title = target.attr('data-title');
	var writeYear = parent.attr('data-writeYear');
	var rate = target.val();
	
	var url = contextPath + '/admin/rate/etc/insert.json';
	
	var json = {
		'title' : title,
		'writeYear' : writeYear,
		'rate' : rate,
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				alert('변경 완료');
			},
			error : function(){
				alert('에러 발생');
			}
		});
	});
};

youngjin.invoice.containerRateInput = function(target){
	var parent = target.parents('td');
	var tsp = parent.attr('data-tsp');
	var status = parent.attr('data-status');
	var writeYear = parent.attr('data-writeYear');
	var rate = target.val();
	
	var url = contextPath + '/admin/rate/container/insert.json';
	var json = {
		'tsp' : tsp,
		'containerRate' : rate,
		'writeYear' : writeYear,
		'containerStatus' : status
	};
	
	if(target.attr('data-rate') != rate ){
		$.postJSON(url, json, function(){
			return jQuery.ajax({
				success : function(){
					alert("변경 완료");
				},
				error : function(){
					alert("에러 발생");
				}
				
			});
		});
	}
	
};

youngjin.invoice.sitInput = function(target){
	var parent = target.parents('td');
	var seq = parent.attr('data-seq');
	var rate = target.val();
	
	var url = contextPath + '/admin/rate/sit/insert.json';
	var json = {
		'seq' : seq,
		'rate' : rate
	};
	
	if(target.attr('data-rate') != rate){
		$.postJSON(url, json, function(){
			return jQuery.ajax({
				success : function(){
					alert("변경 완료");
				},
				error : function(){
					alert("에러 발생");
				}				
			});
		});		
	}
};

youngjin.invoice.otherInput = function(target){
	var parent = target.parents('td');
	var seq = parent.attr('data-seq');
	var rate = target.val();
	
	var url = contextPath + '/admin/rate/other/insert.json';
	var json = {
		'seq' : seq,
		'rate' : rate
	};
	
	if(target.attr('data-rate') != rate){
		$.postJSON(url, json, function(){
			return jQuery.ajax({
				success : function(){
					alert("변경 완료");
				},
				error : function(){
					alert("에러 발생");
				}				
			});
		});		
	}	
};

youngjin.invoice.invoiceListAddPop = function(target, process){
	var url = contextPath + '/' + process + '/invoice/invoiceAddSetting';
	
	$.smartPop.open({
		width: 1000,
		height: 700,
		url : url
	});	
};

youngjin.invoice.invoiceListAddSubmit = function(target, process){
	var gblSeq = '';
	
	$(':checkbox:checked').each(function(){
		gblSeq = gblSeq + $(this).val() + ",";
	});
	
	var url = contextPath + '/' + process + '/invoice/invoiceListAdd.json';
	
	var json = {
			'seqList' : gblSeq
	};
	
	$.postJSON(url, json, function(invoice){
		return jQuery.ajax({
			success : function(){
				var goUrl = contextPath + '/' + process + '/invoice/' + invoice.seq + '/invoiceGblList'; 
				
				parent.$.smartPop.close();
				
				parent.$.smartPop.open({
					width : 800,
					height : 500,
					url : goUrl
				});
			},
			
			error : function(){
				alert('에러 발생!');
			}
		});
	});
};

youngjin.invoice.invoiceGblListBack = function(target){
	if(confirm('뒤로 가실경우, 지금까지 작성 했던 invoice가 모두 삭제 됩니다. 뒤로 가시겠습니까?')){
		var seq = $('.invoice_gbl_list_table').attr('data-seq');
		
		var url = contextPath + '/outbound/invoice/invoiceDelete.json';
		
		$.postJSON(url, {'seq' : seq}, function(){
			return jQuery.ajax({
				success: function(){
					var beforeUrl = contextPath + '/outbound/invoice/invoiceAddSetting';
					
					parent.$.smartPop.close();
					
					parent.$.smartPop.open({
						width : 1000,
						height : 700,
						url : beforeUrl
					});					
				},
				error : function(){
					alert('에러 발생!');
				}
			});
		});
	}
};

youngjin.invoice.invoiceGblContentPop = function(target, process){
	var seq = '';
	if( process == 'outbound'){
		seq = $('.invoice_gbl_list_table').attr('data-seq');
	} else if( process == 'inbound'){
		seq = $('.inbound_invoice_gbl_list_table').attr('data-seq');
	}
	var invoiceGblSeq = target.attr('data-invoiceGblSeq');
	var gblSeq = target.attr('data-gblSeq');
	
	var url = contextPath + '/' + process + '/invoice/' + seq + '/' + invoiceGblSeq + '/' + gblSeq + '/invoiceGblContent';
	
	parent.$.smartPop.close();
	
	parent.$.smartPop.open({
		width : 800,
		height : 900,
		url : url
	});
};

youngjin.invoice.invoiceList = function(target, process){
	var seq = target.attr('data-seq');
	
	var url = contextPath + '/' + process + '/invoice/' + seq + '/invoiceGblListCommon'; 
	
	parent.$.smartPop.close();
	
	parent.$.smartPop.open({
		width : 800,
		height : 500,
		url : url
	});	
};

youngjin.invoice.invoiceGblPrint = function(target, process){
	var invoiceSeq = $('.invoice_gbl_list_table').attr('data-seq');
	
	if(process == 'inbound'){
		invoiceSeq = $('.inbound_invoice_gbl_list_table').attr('data-seq');
	}
	
	var url = contextPath + '/' + process + '/invoice/' + invoiceSeq + '/invoicePrint';
	
	window.open(url, 'invoiceGblPrint', 'width=1263, height=892, status=no');
};

youngjin.invoice.invoiceGblContentBack = function(target, process){
	var seq = target.attr('data-seq');
	
	var url = contextPath + '/' + process + '/invoice/' + seq + '/invoiceGblListCommon'; 
	
	parent.$.smartPop.close();
	
	parent.$.smartPop.open({
		width : 800,
		height : 500,
		url : url
	});		
};

youngjin.invoice.inboundGbDelete = function(target, process){
	var seq = target.parents('tr').attr('data-seq');
	
	var url = contextPath + '/' + process + '/invoice/' + seq + '/invoiceDelete';
	
	location.href = url;
};

youngjin.invoice.inputCollectionNet = function(target){
	var net = target.val();
	var invoiceAmount = target.parents().children('.invoice_amount').children('.invoice_amountValue').val();
	var state = '';
	var difference = 0;
	var url = contextPath + '/outbound/inputCollectionNet';
	if(net != ''){
		if( net == invoiceAmount ){
			state = 'COMPLETE';
		} else if ( net < invoiceAmount ){
			state = 'RESENT';
		}
		
		difference = net - invoiceAmount;
		
		alert(difference);
		
		var json = {
			'invoiceSeq' : target.parents('tr').attr('data-seq'),
			'net' : net,
			'state' : state,
			'difference' : difference
		};
		
		$.postJSON(url, json, function(){
			return jQuery.ajax({
				success : function(){
					location.href = contextPath + '/outbound/invoiceCollection';
				},
				error : function(){
					alert('에러 발생');
				}
			});
		});
	}
};

youngjin.invoice.inputCollectionFlowTable = function(target){
	var html = '<li>' + 
					'<table class="collection_flow_table">' + 
						'<tr>' + 
							'<td class="flow_state">' +
								'<select name="flow_state">' + 
									'<option value="DEPOSIT">DEPOSIT</option>' +
									'<option value="ACCEPT">ACCEPT</option>' +
									'<option value="CLAIM">CLAIM</option>' +
								'</select>' +
							'</td>' + 
							'<td>' + todayDate + '</td>' + 
							'<td>AMOUNT</td>' + 
							'<td class="flow_amount"><input name="amount" type="text" /></td>' +
							'<td>REMARK</td>' + 
							'<td><textarea name="remark"></textarea></td>' +
						'</tr>' + 
					'</table>' + 
				'</li>';
	
	target.parents('.collection_flow_wrap').children('ul').children('li').children('.collection_plus').parents('li').before(html);
	
	if(target.parents('.collection_button').children('.collection_delete').html() == undefined){
		var button = '<div class="collection_delete"><img src="' + contextPath + '/resources/images/collection_delete.png" /></div>';
		target.parents('.collection_button').append(button);
	}
	
	if(target.parents('.collection_button').children('.collection_save').html() == undefined){
		var button = '<div class="collection_save"><img src="' + contextPath + '/resources/images/collection_save.png" /></div>';
		target.parents('.collection_button').append(button);
	}
	
	youngjin.invoice.sync();
};

youngjin.invoice.collectionSave = function(target){
	var tr = target.parents().parents().parents().parents('tr');
	var invoiceAmount = tr.children('.invoice_amount').children('input').val();
	
	var ul = target.parents().parents('ul');
	var table = ul.find('.collection_flow_table:last');
	
	var net = table.find('.flow_amount input').val();
	var difference = 0;
	var state = '';
	var url = contextPath + '/outbound/inputCollectionNet.json';
	
	var ul = target.parents().parents('ul');
	
	var flowState = ul.find('select:last option:selected').val();
	var flowRemark = ul.find('textarea').val();
	
	if( net != '' ){
		if( net == invoiceAmount || flowState == 'ACCEPT' ){
			state = 'COMPLETE';
		} else if ( net < invoiceAmount ){
			state = 'RESENT';			
			difference = net - invoiceAmount;
		}
		
		var json = {
			'invoiceSeq' : target.parents().parents().parents().parents('tr').attr('data-seq'),
			'net' : net,
			'state' : state,
			'amount' : invoiceAmount,
			'difference' : String(difference),
			'flowAmount' : net,
			'flowState' : flowState,
			'flowRemark' : flowRemark
		};
		
		$.postJSON(url, json, function(){
			return jQuery.ajax({
				success : function(){
					location.href = contextPath + '/outbound/invoiceCollection';
				},
				error : function(){
					alert('에러 발생');
				}
			});
		});	
	}	
};

youngjin.invoice.collectionDelete = function(target){
	var invoiceSeq = target.parents().parents().parents().parents('tr').attr('data-seq');
	
	var ul = target.parents().parents('ul');
	var table = ul.find('.collection_flow_table:last');
	var flowSeq = table.attr('data-flowSeq');
	var collectionSeq = table.attr('data-collectionSeq');
	var count = table.attr('data-count');
	
	var state = table.find('select option:selected').val();
	var amount = table.find('.collection_amount').text();
	
	var url = contextPath + '/outbound/invoiceCollectionFlowDelete.json';	
	
	var json = {
			'flowSeq' : flowSeq,
			'invoiceSeq' : invoiceSeq,
			'collectionSeq' : collectionSeq,
			'count' : count,
			'state' : state,
			'amount' : amount
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				location.href = contextPath + '/outbound/invoiceCollection';
			},
			error : function(){
				alert('에러 발생');
			}
		});
	});
};

youngjin.invoice.invoiceCollectionGbl = function(target, process){
	var seq = target.attr('data-seq');
	
	var url = contextPath + '/' + process + '/invoice/' + seq + '/invoiceCollectionGbl'; 
	
	parent.$.smartPop.close();
	
	parent.$.smartPop.open({
		width : 1000,
		height : 500,
		url : url
	});	
};

youngjin.invoice.inputGblCollectionFlowTable = function(target){	
	var ul = target.parents().parents('ul');
	var table = ul.find('.collection_flow_table:last');
	var count = table.attr('data-count');
	var html = '<li>' + 
					'<table class="collection_flow_table" data-count="' + count + '">' + 
						'<tr>' + 
							'<td class="flow_state">' +
								'<select name="flow_state">' + 
									'<option value="DEPOSIT">DEPOSIT</option>' +
									'<option value="ACCEPT">ACCEPT</option>' +
									'<option value="CLAIM">CLAIM</option>' +
								'</select>' +
							'</td>' + 
							'<td>' + todayDate + '</td>' + 
							'<td>AMOUNT</td>' + 
							'<td class="flow_amount"><input name="amount" type="text" /></td>' +
							'<td>REMARK</td>' + 
							'<td><textarea name="remark"></textarea></td>' +
						'</tr>' + 
					'</table>' + 
				'</li>';
	
	target.parents('.collection_gbl_flow_wrap').children('ul').children('li').children('.collection_gbl_plus').parents('li').before(html);
	
	if(target.parents('.collection_button').children('.collection_gbl_delete').html() == undefined){
		var button = '<div class="collection_gbl_delete"><img src="' + contextPath + '/resources/images/collection_delete.png" /></div>';
		target.parents('.collection_button').append(button);
	}
	
	if(target.parents('.collection_button').children('.collection_gbl_save').html() == undefined){
		var button = '<div class="collection_gbl_save"><img src="' + contextPath + '/resources/images/collection_save.png" /></div>';
		target.parents('.collection_button').append(button);
	}
	
	youngjin.invoice.sync();
};

youngjin.invoice.collectionGblSave = function(target){
	var tr = target.parents().parents().parents().parents('tr');
	var invoiceAmount = tr.children('.invoice_amount').children('input').val();
	
	var ul = target.parents().parents('ul');
	var table = ul.find('.collection_flow_table:last');
	
	var net = table.find('.flow_amount input').val();
	var difference = 0;
	var state = '';
	var url = contextPath + '/outbound/inputGblCollectionNet.json';
	
	var ul = target.parents().parents('ul');
	
	var flowState = ul.find('select:last option:selected').val();
	var flowRemark = ul.find('textarea').val();
	
	var invoiceGblSeq = target.parents().parents().parents().parents('tr').attr('data-invoiceGblSeq');
	var invoiceSeq = $('.invoice_gbl_collection_list_table').attr('data-seq');
	var gblSeq = target.parents().parents().parents().parents('tr').attr('data-gblSeq');
	var process =$('.invoice_gbl_collection_list_table').attr('data-process');
	
	var count = table.attr('data-count');
	
	if(count == undefined){
		count = '0';
	}
	
	if( net != '' ){
		if( net == Number(invoiceAmount) || flowState == 'ACCEPT' ){
			state = 'COMPLETE';
		} else if ( Number(net) < Number(invoiceAmount) ){
			state = 'RESENT';			
			difference = net - invoiceAmount;
		}
		
		var json = {
			'invoiceSeq' : invoiceGblSeq,
			'net' : net,
			'state' : state,
			'count' : count,
			'amount' : invoiceAmount,
			'difference' : String(difference),
			'flowAmount' : net,
			'flowState' : flowState,
			'flowRemark' : flowRemark,
			'invoiceNormalSeq' : invoiceSeq,
			'gblSeq' : gblSeq
		};
		
		$.postJSON(url, json, function(){
			return jQuery.ajax({
				success : function(){
					location.href = contextPath + '/' + process +'/invoice/' + invoiceSeq + '/invoiceCollectionGbl';
				},
				error : function(){
					alert('에러 발생');
				}
			});
		});	
	}	
};

youngjin.invoice.collectionGblDelete = function(target){	
	var ul = target.parents().parents('ul');
	var table = ul.find('.collection_flow_table:last');
	var flowSeq = table.attr('data-flowSeq');
	var collectionSeq = table.attr('data-collectionSeq');
	var count = table.attr('data-count');
	
	var state = table.find('select option:selected').val();
	var amount = table.find('.collection_amount').text();
	
	var invoiceGblSeq = target.parents().parents().parents().parents('tr').attr('data-invoiceGblSeq');
	var invoiceSeq = $('.invoice_gbl_collection_list_table').attr('data-seq');
	var process =$('.invoice_gbl_collection_list_table').attr('data-process');
	
	var url = contextPath + '/outbound/invoiceGblCollectionFlowDelete.json';	
	
	var json = {
			'flowSeq' : flowSeq,
			'invoiceSeq' : invoiceGblSeq,
			'collectionSeq' : collectionSeq,
			'count' : count,
			'state' : state,
			'amount' : amount,
			'invoiceNormalSeq' : invoiceSeq
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				location.href = contextPath + '/' + process + '/invoice/' + invoiceSeq + '/invoiceCollectionGbl';
			},
			error : function(){
				alert('에러 발생');
			}
		});
	});
};

youngjin.invoice.invoiceGblCollectionContent = function(target, process){
	var seq = $('.invoice_gbl_collection_list_table').attr('data-seq');
	var invoiceGblSeq = target.attr('data-invoiceGblSeq');
	var gblSeq = target.attr('data-gblSeq');
	
	var url = contextPath + '/' + process + '/invoice/' + seq + '/' + invoiceGblSeq + '/' + gblSeq + '/invoiceGblCollectionContent';
	
	parent.$.smartPop.close();
	
	parent.$.smartPop.open({
		width : 800,
		height : 900,
		url : url
	});	
};

youngjin.invoice.inputGblCollectionRemark = function(target){
	var invoiceGblSeq = $('.invoice_gbl_content_list_pdf').attr('data-invoiceGblSeq');
	var remark = target.val();
	
	var url = contextPath + '/outbound/invoice/collectionRemarkInput.json';
	
	if(invoiceGblSeq == undefined){
		invoiceGblSeq = $('.inbound_invoice_gbl_content_list_pdf').attr('data-invoiceGblSeq');
	}
	
	var json = {
			'seq' : invoiceGblSeq,
			'remark' : remark
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				
			},
			error : function(){
				alert('에러발생!');
			}
		});
	});
};

youngjin.invoice.gblCollectionPdf = function(target, process){
	var seq = target.attr('data-seq');
	var invoiceGblSeq = target.attr('data-invoiceGblSeq');
	var gblSeq = target.attr('data-gblSeq');	
	
	var url = contextPath + '/' + process + '/invoice/' + seq + '/' + invoiceGblSeq + '/' + gblSeq + '/invoiceContentPdfView';
	
	parent.$.smartPop.close();
	
	parent.$.smartPop.open({
		width : 800,
		height : 900,
		url : url
	});	
};
