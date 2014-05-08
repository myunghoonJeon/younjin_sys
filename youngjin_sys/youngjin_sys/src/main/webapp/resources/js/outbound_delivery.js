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
		if($(this).attr('data-merge') != 'merge'){
			if(confirm("분할 하시겠습니까?"))
				youngjin.outbound.delivery.getWeightCertificateList($(this));
		} else {
			$(this).removeAttr('data-merge');
		}
	});
	
	$('.truck_gbl_addButton').unbind('click');
	$('.truck_gbl_addButton').bind('click', function(){
		youngjin.outbound.delivery.addTruckMenu($(this));
	});
	
	$('.truck_seperate_addButton').unbind('click');
	$('.truck_seperate_addButton').bind('click', function(){
		youngjin.outbound.delivery.seperateGbl($(this));
	});	
	
	$('.seperate_merge').unbind('click');
	$('.seperate_merge').bind('click', function(){
		$('.truck_gbl_list_tr').attr('data-merge', 'merge');
		if(confirm('다시 합치시겠습니까?')){
			youngjin.outbound.mergeGbl($(this));
		}
	});
	
	$('.truck_manifast_deleteButton').unbind('click');
	$('.truck_manifast_deleteButton').bind('click', function(){
		$(this).parents('tr').attr('data-delete', 'delete');
		youngjin.outbound.deleteManifast($(this));
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
	
	$('.powerofattorny_list_content').unbind('click');
	$('.powerofattorny_list_content').bind('click', function(){
		youngjin.outbound.delivery.powerofattornyPrint($(this));
	});
	
	$('.declaration_list_content').unbind('click');
	$('.declaration_list_content').bind('click', function(){
		youngjin.outbound.delivery.declarationList($(this));
	});
	
	$('.booking_deleteButton img').unbind('click');
	$('.booking_deleteButton img').bind('click', function(){
//		alert(confirm('삭제 하시겠습니까?'));
		if(confirm('삭제 하시겠습니까?')==true){
			youngjin.outbound.delivery.deleteBookingList($(this));
		}		
	});
	
	$('.truck_manifast_form').unbind('click');
	$('.truck_manifast_form').bind('click', function(){
		if($(this).attr('data-delete') != 'delete'){
			youngjin.outbound.delivery.truckManifastPrint($(this));
		} else {
			$(this).removeAttr('data-delete');
		}
	});
	
	youngjin.outbound.delivery.houseSync();
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
	
	var url = contextPath + '/outbound/delivery/' + seq + '/truckSeperateSetting';
	
	parent.$.smartPop.close();
	
	parent.$.smartPop.open({
		width : 700,
		height : 400,
		url : url
	});
};

youngjin.outbound.delivery.addTruckMenu = function(target){
	
	var truckdate = document.getElementById("truckdate").value;
	
	var gblSeq = '';
	$(':checkbox:checked').each(function(){
		gblSeq = gblSeq + $(this).val() + ",";
	});	
	
	var url = contextPath + '/outbound/delivery/truckAdd.json';
	var json = {
			'gblSeq' : gblSeq,
			'truckdate' : truckdate
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

youngjin.outbound.delivery.seperateGbl = function(target){
	var seq = target.attr('data-seq');
	
	var weightSeqList = $('input:checked');
	var weightSeqCommaList = weightSeqList.eq(0).val();
	
	for(var i = 1 ; i < weightSeqList.length ; i ++){
		weightSeqCommaList += ',' + weightSeqList.eq(i).val();
	}
	
	var url = contextPath + '/outbound/delivery/truckManifast/seperate.json';
	var json = {
			'seq' : seq,
			'weightSeqCommaList' : weightSeqCommaList
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				var url = contextPath + '/outbound/delivery/truckManifastGblList';
				
				parent.$.smartPop.close();
				
				parent.$.smartPop.open({
					width: 1000,
					height: 700,
					url : url
				});
				
			},
			error : function(){
				alert('에러 발생!');
			}
		});
	});
};

youngjin.outbound.mergeGbl = function(target){
	var seq = target.parents('tr').attr('data-seq');
	var no = target.parents('tr').children('.truck_gbl_no').html();
	
	var url = contextPath + '/outbound/delivery/truckManifast/merge.json';
	
	var json = {
		'seq' : seq,
		'no' : no
 	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				var goUrl = contextPath + '/outbound/delivery/truckManifastGblList';
				
				parent.$.smartPop.close();
				
				parent.$.smartPop.open({
					width: 1000,
					height: 700,
					url : goUrl
				});
				
			},
			error : function(){
				
			}
		});
	});
};

youngjin.outbound.deleteManifast = function(target){
	var seq = target.parents('tr').attr('data-seq');
	
	var url = contextPath + '/outbound/delivery/' + seq + '/deleteTruckManifast';
	
	if(confirm('삭제 하시겠습니까?')){
		location.href = url;
	}
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
	var seq = target.parents('tr').attr('data-bookSeq');
	var url = contextPath + '/outbound/delivery/' + seq + '/bookingListPrint';
	window.open(url ,'bookingListPrintPop', 'width=1263, height=892, status=no');
};

youngjin.outbound.delivery.powerofattornyPrint = function(target){
	var seq = target.parents('tr').attr('data-bookSeq');
	var url = contextPath + '/outbound/delivery/' + seq + '/powerOfAttornyPrint';
	window.open(url ,'powerOfAttornyPop', 'width=1263, height=892, status=no');
};

youngjin.outbound.delivery.declarationList = function(target){
	var seq = target.parents('tr').attr('data-bookSeq');
	var url = contextPath + '/outbound/delivery/' + seq + '/declarationList';
	
	window.open(url ,'bookingListPrintPop', 'width=1263, height=892, status=no');	
};

youngjin.outbound.delivery.deleteBookingList = function(target){
	var seq = target.parents('tr').attr('data-bookSeq');
	
	var json = {
		'seq' : seq	
	};
	
	var url = contextPath + '/outbound/delivery/bookingListDelete.json';
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				location.href = contextPath + '/outbound/delivery/bookingList';
			},
			error : function(){
				alert("에러 발생!");
			}
		});		
	});
};

youngjin.outbound.delivery.truckManifastPrint = function(target){
	var seq = target.attr('data-seq');
	var url = contextPath + '/outbound/delivery/' + seq + '/truckManifastPrint';
	
	window.open(url, 'truckManifastPrintPop', 'width=1263, height=892, status=no');
};

youngjin.outbound.delivery.houseSync = function(){
	$('.house_button').unbind('click');
	$('.house_button').bind('click', function(){
		var url = contextPath + '/outbound/delivery/house/gblSelect';
		
		$.smartPop.open({
			width: 1000,
			height: 700,
			url : url
		});
		
	});
	
	$('.house_gbl_list_tr input[type=checkbox]').unbind('click');
	$('.house_gbl_list_tr input[type=checkbox]').bind('click', function(){
		if($(this).attr('data-merge') != 'merge'){
			if(confirm("분할 하시겠습니까?"))
				youngjin.outbound.delivery.getBookWeightCertificateList($(this));
			else 
				if($(this).find('input').attr('checked') != 'checked')
					$(this).find('input').attr('checked', 'checked');
				else 
					$(this).find('input').removeAttr('checked');
		} else {
			$(this).removeAttr('data-merge');
		}
	});
	
	$('.house_gbl_addButton').unbind('click');
	$('.house_gbl_addButton').bind('click', function(){
		youngjin.outbound.delivery.addHouseMenu($(this));
	});
	
	$('.house_seperate_addButton').unbind('click');
	$('.house_seperate_addButton').bind('click', function(){
		youngjin.outbound.delivery.houseSeperateGbl($(this));
	});	
	
	$('.house_seperate_merge').unbind('click');
	$('.house_seperate_merge').bind('click', function(){
		$('.house_gbl_list_tr').attr('data-merge', 'merge');
		if(confirm('다시 합치시겠습니까?')){
			youngjin.outbound.houseMergeGbl($(this));
		}
	});
	
	$('.house_seperate_back').unbind('click');
	$('.house_seperate_back').bind('click', function(){
		var url = contextPath + '/outbound/delivery/house/gblSelect';
		
		parent.$.smartPop.close();
		
		parent.$.smartPop.open({
			width: 1000,
			height: 700,
			url : url
		});		
	});
	
	$('.house_delete').unbind('click');
	$('.house_delete').bind('click', function(){
		youngjin.outbound.delivery.houseDelete($(this));
	});
	
	$('.house_list_tr').unbind('click');
	$('.house_list_tr').bind('click', function(){
		youngjin.outbound.delivery.housePop($(this));
	});
};

youngjin.outbound.delivery.getBookWeightCertificateList = function(target){
	var seq = target.val();
	
	var url = contextPath + '/outbound/delivery/' + seq + '/houseSeperateSetting';
	
	parent.$.smartPop.close();
	
	parent.$.smartPop.open({
		width : 700,
		height : 400,
		url : url
	});
};

youngjin.outbound.delivery.houseSeperateGbl = function(target){
	var seq = target.attr('data-seq');
	
	var weightSeqList = $('input:checked');
	var weightSeqCommaList = weightSeqList.eq(0).val();
	
	for(var i = 1 ; i < weightSeqList.length ; i ++){
		weightSeqCommaList += ',' + weightSeqList.eq(i).val();
	}
	
	var url = contextPath + '/outbound/delivery/house/seperate.json';
	var json = {
			'seq' : seq,
			'weightSeqCommaList' : weightSeqCommaList
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				var url = contextPath + '/outbound/delivery/house/gblSelect';
				
				parent.$.smartPop.close();
				
				parent.$.smartPop.open({
					width: 1000,
					height: 700,
					url : url
				});
				
			},
			error : function(){
				alert('에러 발생!');
			}
		});
	});
};

youngjin.outbound.houseMergeGbl = function(target){
	var seq = target.parents('tr').attr('data-seq');
	var no = target.parents('tr').children('.house_gbl_no').html();
	
	var url = contextPath + '/outbound/delivery/house/merge.json';
	
	var json = {
		'seq' : seq,
		'no' : no
 	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				var goUrl = contextPath + '/outbound/delivery/house/gblSelect';
				
				parent.$.smartPop.close();
				
				parent.$.smartPop.open({
					width: 1000,
					height: 700,
					url : goUrl
				});
				
			},
			error : function(){
				
			}
		});
	});
};

youngjin.outbound.delivery.addHouseMenu = function(target){
	var gblSeq = '';
	var vessel = '';
	var voyage = '';
	var company = '';
	$(':checkbox:checked').each(function(){
		gblSeq = gblSeq + $(this).val() + ",";
		
		var houseInfoTd = $(this).parents().parents('tr').find('.house_gbl_info_td');
		vessel = vessel + houseInfoTd.find('#vessel').val() + ',';
		voyage = voyage + houseInfoTd.find('#voyage').val() + ',';
		company = company + houseInfoTd.find('#company').val() + ',';		
	});	
	
	var contNo = $('#contNo').val();
	var sealNo = $('#sealNo').val();
	var carrierBookingNo = $('#carrierBookingNo').val();
	
	var url = contextPath + '/outbound/delivery/house/add.json';
	var json = {
			'gblSeq' : gblSeq,
			'contNo' : contNo,
			'sealNo' : sealNo,
			'vessel' : vessel,
			'voyage' : voyage,
			'company' : company,
			'carrierBookingNo' : carrierBookingNo
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				parent.location.href = contextPath + '/outbound/delivery/house';
				parent.$.smartPop.close();
			},
			error : function(){
				alert("에러 발생!");
			}
		});
	});
};

youngjin.outbound.delivery.houseDelete = function(target){
	var seq = target.attr('data-seq');
	
	var url = contextPath + '/outbound/delivery/house/delete.json';
	var json = {
		'seq' : seq	
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success : function(){
				location.href = contextPath + '/outbound/delivery/house';				
			},
			error : function(){
				alert("에러 발생!");
			}
		});
	});
};

youngjin.outbound.delivery.housePop = function(target){
	var seq = target.attr('data-seq');
	
	var url = contextPath + '/outbound/delivery/house/' + seq + '/housePop';
	
	$.smartPop.open({
		width: 900,
		height: 1000,
		url : url
	});
};