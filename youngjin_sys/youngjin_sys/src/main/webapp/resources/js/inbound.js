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
	
	$('.inbound_gbl_process_delivery').unbind('click');
	$('.inbound_gbl_process_delivery').bind('click', function(){
		youngjin.inbound.goOnHandList($(this));
	});
	
	$('.inbound_gbl_process_preparation').unbind('click');
	$('.inbound_gbl_process_preparation').bind('click', function(){
		youngjin.inbound.preparation($(this));
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
};

youngjin.inbound.freightAddSubmit = function(){
	var form = document.forms['gbl'];
	form.submit();	
};

youngjin.inbound.freightProcess = function(target){
	var seq =  target.attr('data-seq');
	var url = contextPath + '/inbound/freight/' + seq;
	
	$.smartPop.open({
		width : 1000,
		height : 521,
		url : url
	});	
	
};

youngjin.inbound.uploadPage = function(target){
	var parent = target.parents();
	var seq = parent.attr('data-seq');
	var url = contextPath + '/inbound/freight/' + seq + '/upload';
	
	window.open(url, 'uploadPop', 'width=350, height=180, status=no, scrollbars=no');
	
};

youngjin.inbound.bl = function(target){
	var seq = $('.gbl_process').attr('data-seq');
	
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

youngjin.inbound.preparation = function(target){
	var seq = target.parents().parents('.gbl_process').attr('data-seq');
	
	var url = contextPath + '/inbound/freight/' + seq + '/preparation';
	
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
	
	var url = contextPath + '/inbound/freight/' + seq + '/preparation';
	
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
