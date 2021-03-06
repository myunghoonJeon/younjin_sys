if (typeof youngjin.top == 'undefined') {
	youngjin.top = {};
}

$(function() {
	
	youngjin.top.home();
	
	youngjin.top.form();
	
	youngjin.top.formSelect();
	
	youngjin.top.goToAdminPage();
	
	youngjin.top.goToGblockPage();
	
	youngjin.top.leftDeliveryMenu();
});

youngjin.top.home = function(){
	$('.home_wrap').unbind('click');
	$('.home_wrap').bind('click',function(){
		location.href=contextPath + "/";
	});
	
};

youngjin.top.form = function(){
	
	$('.top_menu_form').unbind('click');
	$('.top_menu_form').bind('click', function(event){
		x = screen.width / 2 - 150;
		y = screen.height / 2 - 100;
		
		var win = window.open(contextPath + '/form/', 'form_pop', 'width=350, height=180, screenX=' + x + ',screenY=' + y + ', status=no, scrollbars=no');
	});
};

youngjin.top.formSelect = function(){
	$('#form_inventory').bind('click', function(){
		alert("!");
	});
};

youngjin.top.goToAdminPage = function(){
	$('.admin_service_wrap').bind('click', function(){
		location.href = contextPath + '/admin/admin/';
	});
};

youngjin.top.goToGblockPage = function(){
	$('.gblock_service_wrap').bind('click', function(){
		location.href = contextPath + '/gblock/gblock/main/';
	});
};

youngjin.top.leftDeliveryMenu = function(){
	$('.outbound_delivery').click(function(){
		if ($('.outbound_delivery_item_list').css('display') == 'none'){			
			$('.outbound_delivery_item_list').slideDown();
			$('.outbound_delivery_item_list').css('display', 'inline-block');
		} else if($('.outbound_delivery_item_list').css('display') == 'inline-block'){
			$('.outbound_delivery_item_list').slideUp();
		}
	});

	$('.outbound_invoice').click(function(){
		if ($('.outbound_invoice_item_list').css('display') == 'none'){			
			$('.outbound_invoice_item_list').slideDown();
			$('.outbound_invoice_item_list').css('display', 'inline-block');
		} else if($('.outbound_invoice_item_list').css('display') == 'inline-block'){
			$('.outbound_invoice_item_list').slideUp();
		}
	});

	$('.inbound_custom').click(function(){
		if ($('.inbound_custom_item_list').css('display') == 'none'){			
			$('.inbound_custom_item_list').slideDown();
			$('.inbound_custom_item_list').css('display', 'inline-block');
		} else if($('.inbound_custom_item_list').css('display') == 'inline-block'){
			$('.inbound_custom_item_list').slideUp();
		}
	});

	$('.inbound_onHand').click(function(){
		if ($('.inbound_onHand_item_list').css('display') == 'none'){			
			$('.inbound_onHand_item_list').slideDown();
			$('.inbound_onHand_item_list').css('display', 'inline-block');
		} else if($('.inbound_onHand_item_list').css('display') == 'inline-block'){
			$('.inbound_onHand_item_list').slideUp();
		}
	});

	$('.basic').click(function(){
		if ($('.basic_item_list').css('display') == 'none'){			
			$('.basic_item_list').slideDown();
			$('.basic_item_list').css('display', 'inline-block');
		} else if($('.basic_item_list').css('display') == 'inline-block'){
			$('.basic_item_list').slideUp();
		}
	});

	$('.inbound_invoice').click(function(){
		if ($('.inbound_invoice_item_list').css('display') == 'none'){			
			$('.inbound_invoice_item_list').slideDown();
			$('.inbound_invoice_item_list').css('display', 'inline-block');
		} else if($('.inbound_invoice_item_list').css('display') == 'inline-block'){
			$('.inbound_invoice_item_list').slideUp();
		}
	});
	
};