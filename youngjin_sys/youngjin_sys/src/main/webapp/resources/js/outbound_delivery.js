if (typeof youngjin.outbound.delivery == 'undefined') {
	youngjin.outbound.delivery = {};
}

$(function() {	
	youngjin.outbound.delivery.sync();
});


youngjin.outbound.delivery.sync = function(){
	$('.truck_addButton').unbind('click');
	$('.truck_addButton').bind('click',function(){
		youngjin.outbound.delivery.getTruckmainifastGblList($(this));
	});
	
	$('.truck_gbl_list_tr').unbind('click');
	$('.truck_gbl_list_tr').bind('click', function(){
		if(confirm("분할 하시겠습니까?"))
			youngjin.outbound.delivery.getWeightCertificateList($(this));
		else 
			$(this).find('input').attr('checked', 'checked');
	});
	
	$('.truck_gbl_addButton').unbind('click');
	$('.truck_gbl_addButton').bind('click', function(){
		youngjin.outbound.delivery.addTruckMenu($(this));
	});
	
	$('.truck_weight_addButton').unbind('click');
	$('.truck_weight_addButton').bind('click', function(){
			youngjin.outbound.delivery.selectWeight($(this));
	});
	
};

youngjin.outbound.delivery.getTruckmainifastGblList = function(target){
	var url = contextPath + '/outbound/delivery/truckManifastGblList';
	
	$.smartPop.open({
		width: 900,
		height: 700,
		url : url
	});
};

youngjin.outbound.delivery.getWeightCertificateList = function(target){
	var seq = target.attr('data-seq');
	var left = (screen.width - 600) / 2;
	var top = (screen.height - 300) / 2;
	
	var url = contextPath + '/outbound/delivery/' + seq + '/truckWeightList';
	
	window.open(url, 'truckWeightListPop', 'width=600, height=300, left=' + left + ', top=' + top);
};

youngjin.outbound.delivery.addTruckMenu = function(target){
	var gblSeq = '';
	$(':checkbox:checked').each(function(){
		gblSeq = gblSeq + $(this).val() + ",";
	});	
	
	var url = contextPath + '/outbound/delivery/truckAdd.json';
	var json = {
			'gblSeq' : gblSeq
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				parent.location.href = contextPath + '/outbound/delivery/truckManifast';
				parent.$.smartPop.close();
			},
			error : function(){
				alert("에러 발생!");
			}
		});
	});
};

youngjin.outbound.delivery.selectWeight = function(target){
	var weightSeq = '';
	$(':checkbox:checked').each(function(){
		weightSeq = weightSeq + $(this).val() + ",";
	});
	
	alert($(opener.document).find('.truck_gbl_list_tr').attr('data-ws'));
};