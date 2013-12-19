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
			if($(this).find('input').attr('checked') != 'checked')
				$(this).find('input').attr('checked', 'checked');
			else 
				$(this).find('input').removeAttr('checked');
	});
	
	$('.truck_gbl_addButton').unbind('click');
	$('.truck_gbl_addButton').bind('click', function(){
		youngjin.outbound.delivery.addTruckMenu($(this));
	});
	
	$('.truck_weight_addButton').unbind('click');
	$('.truck_weight_addButton').bind('click', function(){
			youngjin.outbound.delivery.selectWeight($(this));
	});	

	$('.booking_addButton').unbind('click');
	$('.booking_addButton').bind('click',function(){
		youngjin.outbound.delivery.getBookingGblList($(this));
	});
	
	$('.booking_gbl_addButton').unbind('click');
	$('.booking_gbl_addButton').bind('click', function(){
		youngjin.outbound.delivery.addBookingMenu($(this));
	});
	
	$('.booking_gbl_list_tr').unbind('click');
	$('.booking_gbl_list_tr').bind('click', function(){
		if($(this).find('input').attr('checked') != 'checked')
			$(this).find('input').attr('checked', 'checked');
		else 
			$(this).find('input').removeAttr('checked');	
	});
	
	$('.booking_list_content').unbind('click');
	$('.booking_list_content').bind('click', function(){
		youngjin.outbound.delivery.bookingPrint($(this));
	});
	
	$('.truck_manifast_form').unbind('click');
	$('.truck_manifast_form').bind('click', function(){
		youngjin.outbound.delivery.truckManifastPrint($(this));
	});
};

youngjin.outbound.delivery.getTruckmainifastGblList = function(target){
	var url = contextPath + '/outbound/delivery/truckManifastGblList';
	
	$.smartPop.open({
		width: 1000,
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

youngjin.outbound.delivery.getBookingGblList = function(target){
	var url = contextPath + '/outbound/delivery/bookingGblList';
	
	$.smartPop.open({
		width: 1000,
		height: 700,
		url : url
	});	
};

youngjin.outbound.delivery.addBookingMenu = function(target){
	var gblSeq = '';
	$(':checkbox:checked').each(function(){
		gblSeq = gblSeq + $(this).val() + ",";
	});	
	
	var url = contextPath + '/outbound/delivery/bookingAdd.json';
	var json = {
			'gblSeq' : gblSeq
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				parent.location.href = contextPath + '/outbound/delivery/bookingList';
				parent.$.smartPop.close();
			},
			error : function(){
				alert("에러 발생!");
			}
		});
	});
};

youngjin.outbound.delivery.bookingPrint = function(target){
	var seq = target.attr('data-bookSeq');
	var url = contextPath + '/outbound/delivery/' + seq + '/bookingListPrint';
	
	window.open(url ,'bookingListPrintPop', 'width=1263, height=892, status=no');
};

youngjin.outbound.delivery.truckManifastPrint = function(target){
	var seq = target.attr('data-seq');
	var url = contextPath + '/outbound/delivery/' + seq + '/truckManifastPrint';
	
	window.open(url, 'truckManifastPrintPop', 'width=1263, height=892, status=no');
};