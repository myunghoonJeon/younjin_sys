if (typeof youngjin.invoice == 'undefined') {
	youngjin.invoice = {};
}

$(function() {	
	youngjin.invoice.sync();
});

youngjin.invoice.sync = function(){
	youngjin.invoice.rateSync();
	
	youngjin.invoice.invoiceListSync();
	
	youngjin.invoice.backSync();
};

youngjin.invoice.invoiceListSync = function(){
	$('.invoice_add_button').unbind('click');
	$('.invoice_add_button').bind('click', function(){
		youngjin.invoice.invoiceListAddPop($(this));
	});
	
/*	$('input#invoice_add_start_date').datepicker({dateFormat: 'yymmdd'});
	$('input#invoice_add_end_date').datepicker({dateFormat: 'yymmdd'});*/
	
	$('.invoice_add_submit_button').unbind('click');
	$('.invoice_add_submit_button').bind('click', function(){
		youngjin.invoice.invoiceListAddSubmit($(this));
	});
	
	$('.invoice_gbl_list_table tbody tr').unbind('click');
	$('.invoice_gbl_list_table tbody tr').bind('click', function(){
		youngjin.invoice.invoiceGblContentPop($(this));
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
};

youngjin.invoice.backSync = function(){
	$('.invoice_gbl_list_back').unbind('click');
	$('.invoice_gbl_list_back').bind('click', function(){
		youngjin.invoice.invoiceGblListBack($(this));
	});
};

youngjin.invoice.basicRateInput = function(target){
	var parent = target.parents('td');
	var tsp = parent.attr('data-tsp');
	var code = parent.attr('data-code');
	var process = parent.attr('data-process');
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
	var rate = target.val();
	
	var url = contextPath + '/admin/rate/etc/insert.json';
	
	var json = {
		'title' : title,
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
	var rate = target.val();
	
	var url = contextPath + '/admin/rate/container/insert.json';
	var json = {
		'tsp' : tsp,
		'containerRate' : rate,
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

youngjin.invoice.invoiceListAddPop = function(target){
	var url = contextPath + '/outbound/invoice/invoiceAddSetting';
	
	$.smartPop.open({
		width : 600,
		height : 180,
		url : url
	});
};

youngjin.invoice.invoiceListAddSubmit = function(target){
	var tsp = $('#invoice_add_select').val();
	var startDate = $('#invoice_add_start_date').val();
	var endDate = $('#invoice_add_end_date').val();
	
	var url = contextPath + '/outbound/invoice/invoiceListAdd.json';
	
	var json = {
			'tsp' : tsp,
			'startDate' : startDate,
			'endDate' : endDate
	};
	
	$.postJSON(url, json, function(invoice){
		return jQuery.ajax({
			success : function(){
				if(invoice != '' && invoice != undefined){
					var goUrl = contextPath + '/outbound/invoice/' + invoice.seq + '/invoiceGblList'; 
					
					parent.$.smartPop.close();
					
					parent.$.smartPop.open({
						width : 800,
						height : 500,
						url : goUrl
					});
				} else {
					alert('해당 조건에 GBL이 존재하지 않습니다!');
					var beforeUrl = contextPath + '/outbound/invoice/invoiceAddSetting';
					
					parent.$.smartPop.close();
					
					parent.$.smartPop.open({
						width : 600,
						height : 180,
						url : beforeUrl
					});
				}
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
						width : 600,
						height : 180,
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

youngjin.invoice.invoiceGblContentPop = function(target){
	var seq = $('.invoice_gbl_list_table').attr('data-seq');
	var invoiceGblSeq = target.attr('data-invoiceGblSeq');
	var gblSeq = target.attr('data-gblSeq');
	
	var url = contextPath + '/outbound/invoice/' + seq + '/' + invoiceGblSeq + '/' + gblSeq + '/invoiceGblContent';
	
	parent.$.smartPop.close();
	
	parent.$.smartPop.open({
		width : 800,
		height : 900,
		url : url
	});
};