if (typeof youngjin.admin == 'undefined') {
	youngjin.admin = {};
}

$(function() {
	youngjin.admin.sync();
	
});

youngjin.admin.user_no = 1;

youngjin.admin.sync = function(){
	var eventString = webBrowserCheck() == 'C' ? 'keydown' : 'keypress';
	
	$('.admin_user_addButton span').unbind('click');
	$('.admin_user_addButton span').bind('click', function(){
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
	
	$('.admin_user_list_firstName').unbind('click');
	$('.admin_user_list_firstName').bind('click', function(){
		youngjin.admin.user_first_name_input_insert($(this));
	});
	
	$('.admin_user_firstName').unbind(eventString);
	$('.admin_user_firstName').bind(eventString, function(event){
		if(event.keyCode == 13){
			youngjin.admin.user_first_name_input_submit($(this));
			$('.admin_user_firstName').focusout();
		}
	});
	
	$('.admin_user_firstName').focusout(function(){
		youngjin.admin.user_first_name_input_submit($(this));
	});
	
	$('.admin_user_list_familyName').unbind('click');
	$('.admin_user_list_familyName').bind('click', function(){
		youngjin.admin.user_family_name_input_insert($(this));
	});
	
	$('.admin_user_familyName').unbind(eventString);
	$('.admin_user_familyName').bind(eventString, function(event){
		if(event.keyCode == 13){
			youngjin.admin.user_family_name_input_submit($(this));
			$('.admin_user_familyName').focusout();
		}
	});
	
	$('.admin_user_familyName').focusout(function(){
		youngjin.admin.user_family_name_input_submit($(this));
	});
	
	$('.admin_user_list_password').unbind('click');
	$('.admin_user_list_password').bind('click', function(){
		if(confirm('비밀번호를 초기화 하시겠습니까?')){
			youngjin.admin.user_password_clear($(this));
		} else {
			alert("취소 되었습니다.");
		}
	});
	
	$('.admin_user_auth_list_select').unbind('change');
	$('.admin_user_auth_list_select').bind('change', function(){
		youngjin.admin.user_auth_change($(this));
	});
	
	$('.admin_user_area_list_select').unbind('change');
	$('.admin_user_area_list_select').bind('change', function(){
		youngjin.admin.user_area_change($(this));
	});
	
	$('.admin_user_enabled').unbind('change');
	$('.admin_user_enabled').bind('change', function(){
		youngjin.admin.user_enable_change($(this));
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
			+			'<td class="admin_user_list_lastUpdate">&nbsp;</td>'
			+			'<td class="admin_user_list_lastUpdateBy">&nbsp;</td>'
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

youngjin.admin.user_first_name_input_insert = function(target){
	var firstName = target.html();	

	var width = target.width();
	
	var html = '<input type="text" value="' + firstName + '" class="admin_user_firstName" style="width : ' + (parseInt(width) - 3) + 'px;" /> ';
	
	if( target.children('.admin_user_firstName').val() != undefined){
		
	} else {
		target.attr('data-old', firstName);
		target.html(html);
		target.children('.admin_user_firstName').focus();
	}	
	
	youngjin.admin.sync();
};

youngjin.admin.user_first_name_input_submit = function(target){
	var parent = target.parents('.admin_user_list_firstName');
	var firstName = parent.attr('data-old');
	var newFirstName = target.val();
	
	if(firstName == newFirstName){
		parent.html(firstName);
	} else {
		var url = contextPath + "/admin/updateUserInfo.json";
		var json = {
				"seq" : parent.parents().attr('data-seq'),
				"firstName" : newFirstName
		};
		
		$.postJSON(url, json, function(user){
			
			return jQuery.ajax({
				success : function(){
					parent.html(user.firstName);
					parent.parents().children('.admin_user_list_lastUpdate').html(user.lastUpdate);
					parent.parents().children('.admin_user_list_lastUpdateBy').html(user.lastUpdateBy);
				},
				error : function(){
					alert("에러발생!");
				}
			});
		});
	}		
};

youngjin.admin.user_family_name_input_insert = function(target){
	var familyName = target.html();	

	var width = target.width();
	
	var html = '<input type="text" value="' + familyName + '" class="admin_user_familyName" style="width : ' + (parseInt(width) - 3) + 'px;" /> ';
	
	if( target.children('.admin_user_familyName').val() != undefined){
		
	} else {
		target.attr('data-old', familyName);
		target.html(html);
		target.children('.admin_user_familyName').focus();
	}	
	
	youngjin.admin.sync();
};

youngjin.admin.user_family_name_input_submit = function(target){
	var parent = target.parents('.admin_user_list_familyName');
	var familyName = parent.attr('data-old');
	var newFamilyName = target.val();
	
	if(familyName == newFamilyName){
		parent.html(familyName);
	} else {
		var url = contextPath + "/admin/updateUserInfo.json";
		var json = {
				"seq" : parent.parents().attr('data-seq'),
				"familyName" : newFamilyName
		};
		
		$.postJSON(url, json, function(user){
			
			return jQuery.ajax({
				success : function(){
					parent.html(user.familyName);
					parent.parents().children('.admin_user_list_lastUpdate').html(user.lastUpdate);
					parent.parents().children('.admin_user_list_lastUpdateBy').html(user.lastUpdateBy);
				},
				error : function(){
					alert("에러발생!");
				}
			});
		});
	}		
};

youngjin.admin.user_password_clear = function(target){
	var parent = target.parents();
	var seq = target.parents().attr('data-seq');
	
	var url = contextPath + '/admin/clearPassword.json';
	var json = {
			"seq" : seq
	};
	
	$.postJSON(url, json, function(user){
		return jQuery.ajax({
			success : function(){
				target.html((user.password).substring(0, 10) + '...');
				parent.parents().children('.admin_user_list_lastUpdate').html(user.lastUpdate);
				parent.parents().children('.admin_user_list_lastUpdateBy').html(user.lastUpdateBy);
			},
			error : function(){
				alert("에러 발생!");
			}
		});
	});
};
/*
youngjin.admin.user_auth_select_insert = function(target){
	var url = contextPath + "/admin/authList.json";
	
	var auth = target.html();
	
	if(target.children('.admin_user_auth_list_select').val() == undefined){
		$.postJSON(url, {}, function(authList){
			var html = '<select class="admin_user_auth_list_select">';
			for ( var i = 0 ; i < authList.length ; i ++ ){
				html += '<option value="' + parseInt(authList[i].subcode) + '"' + (auth == authList[i].codeName ? "selected=selected" : "") +'>' + authList[i].codeName + '</option>';
			}
			html += '</select>';
			
			target.html(html);
			$('.admin_user_auth_list_select').focus();
		});
	}
	
	youngjin.admin.sync();
};*/
youngjin.admin.user_auth_change = function(target){
	var url = contextPath + "/admin/authChange.json";
	
	var parent = target.parents();
	var seq = parent.parents().attr('data-seq');
	var auth = target.val();
	
	var json = {
			"seq" : seq,
			"auth" : auth
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success: function(){
				alert("변경 성공");
			},
			error : function(){
				alert("에러 발생!");
			}
		});
	});	
	
};

youngjin.admin.user_area_change = function(target){
	var url = contextPath + "/admin/areaChange.json";
	
	var parent = target.parents();
	var seq = parent.parents().attr('data-seq');
	var area = target.val();
	
	var json = {
			"seq" : seq,
			"area" : area
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success: function(){
				alert("변경 성공");
			},
			error : function(){
				alert("에러 발생!");
			}
		});
	});	
	
};

youngjin.admin.user_enable_change = function(target){
	var url = contextPath + "/admin/enabledChange.json";
	
	var parent = target.parents();
	var seq = parent.parents().attr('data-seq');
	var enabled = (target.val() == '0' ? false : true);
	
	var json = {
			"seq" : seq,
			"enabled" : enabled
	};
	
	$.postJSON(url, json, function(){
		return jQuery.ajax({
			success: function(){
				alert("변경 성공");
			},
			error : function(){
				alert("에러 발생!");
			}
		});
	});		
};