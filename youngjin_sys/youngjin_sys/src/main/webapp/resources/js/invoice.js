if (typeof youngjin.invoice == 'undefined') {
	youngjin.invoice = {};
}

$(function() {	
	youngjin.invoice.sync();
});

youngjin.invoice.sync = function(){
	$('.basic_rate_input').unbind('click');
	$('.basic_rate_input').bind('click', function(){
		var rate = $(this).val();
		$(this).attr('data-rate', rate);
	});
	
	$('.basic_rate_input').focusout(function(){
		youngjin.invoice.basicRateInput($(this));
	});
	
	$('.container_rate_input').focusout(function(){
		youngjin.invoice.containerRateInput($(this));
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