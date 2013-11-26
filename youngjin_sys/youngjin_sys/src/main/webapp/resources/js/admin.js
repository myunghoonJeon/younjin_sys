if (typeof youngjin.admin == 'undefined') {
	youngjin.admin = {};
}

$(function() {
	youngjin.admin.sync();
	
});

youngjin.admin.user_no = 1;

youngjin.admin.sync = function(){
	$('#admin_user_addButton span').unbind('click');
	$('#admin_user_addButton span').bind('click', function(){
		youngjin.admin.user_add();
	});
	
	$('.admin_user_list_delete a').unbind('click');
	$('.admin_user_list_delete a').bind('click', function(){
		if(confirm("Do you want to delete a user?")){
			youngjin.admin.user_delete($(this));
		} else {
			alert("취소 되었습니다.");
		}
	});
	
	$('.admin_user_list_password').unbind('click');
	$('.admin_user_list_password').bind('click', function(){
		youngjin.admin.user_password_change($(this));
	});
	
	youngjin.admin.user_no = parseInt($('#admin_user_managementDiv .yj_table tbody tr').last().children('.admin_user_list_no').html()) + 1;
	
	
};

youngjin.admin.user_add = function(){
	var url = contextPath + '/admin/add.json';
	
	$.postJSON(url, {}, function(user){
		var html = '<tr class="admin_user_list_tr" data-seq="' + user.seq + '">'
			+			'<td class="admin_user_list_no">' + youngjin.admin.user_no + '</td>'
			+			'<td class="admin_user_list_username">' + user.username + '</td>'
			+			'<td class="admin_user_list_password">' + user.password + '</td>'
			+			'<td class="admin_user_list_firstName">&nbsp;</td>'
			+			'<td class="admin_user_list_familyName">&nbsp;</td>'
			+			'<td class="admin_user_list_auth">' + user.authStr + '</td>'
			+			'<td class="admin_user_list_area">' + user.area + '</td>'
			+			'<td class="admin_user_list_enabled">' + user.enabled + '</td>'
			+			'<td>&nbsp;</td>'
			+			'<td>&nbsp;</td>'
			+			'<td class="admin_user_list_delete"><a href="#">x</href></td>'
			+		'</tr>';
		
		if( $('#admin_user_managementDiv .yj_table tbody #yj_user_none').html() != undefined){
			$('#admin_user_managementDiv .yj_table tbody').html(html);
		} else {
			$('#admin_user_managementDiv .yj_table tbody').append(html);	
		}
		
		youngjin.admin.sync();
		
		return jQuery.ajax({
            success: function() {
            	alert("추가 완료!");
            },
            
            error: function(){
            	alert("error");
            }
		});
	});
};

youngjin.admin.user_delete = function(target){
	var url = contextPath + '/admin/delete.json';
	var deleteContentTr = target.parents().parents('.admin_user_list_tr');
	var username = deleteContentTr.children('.admin_user_list_username').html();
	
	var json = { 
			'username' : username
	};
	$.postJSON(url, json, function(){
		deleteContentTr.fadeOut("slow");
		
		youngjin.admin.sync();
		
		return jQuery.ajax({		            
            error: function(){
            	alert("error");
            }
		});
	});	
};

youngjin.admin.user_password_change = function(target){
	var parent = target.parents();
	var seq = parent.attr('data-seq');
	
	$.postJSON();
};