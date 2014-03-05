if (typeof youngjin.gblock == 'undefined') {
	youngjin.gblock = {};
}

$(function() {	
	youngjin.gblock.sync();
});

youngjin.gblock.sync = function(){
	var eventString = webBrowserCheck() == 'C' ? 'keydown' : 'keypress';
	
	$('.gblock_addButton').unbind('click');
	$('.gblock_addButton').bind('click', function(){
		youngjin.gblock.addGBlock();
	});

	$('.gblock_dodaac').unbind('click');
	$('.gblock_dodaac').bind('click', function(){
		youngjin.gblock.updateGBlock($(this), 'dodaac');
	});

	$('.gblock_gblock').unbind('click');
	$('.gblock_gblock').bind('click', function(){
		youngjin.gblock.updateGBlock($(this), 'gblock');
	});

	$('.gblock_usNo').unbind('click');
	$('.gblock_usNo').bind('click', function(){
		youngjin.gblock.updateGBlock($(this), 'usNo');
	});

	$('.gblock_remark').unbind('click');
	$('.gblock_remark').bind('click', function(){
		youngjin.gblock.updateGBlock($(this), 'remark');
	});
	
	$('.gblock_input_dodaac').focusout(function(){
		youngjin.gblock.updateGBlock_submit($(this), 'dodaac');
	});
	
	$('.gblock_input_gblock').focusout(function(){
		youngjin.gblock.updateGBlock_submit($(this), 'gblock');
	});
	
	$('.gblock_input_usNo').focusout(function(){
		youngjin.gblock.updateGBlock_submit($(this), 'usNo');
	});
	
	$('.gblock_input_remark').focusout(function(){
		youngjin.gblock.updateGBlock_submit($(this), 'remark');
	});	
	
	$('.gblock_input_dodaac').unbind(eventString);
	$('.gblock_input_dodaac').bind(eventString, function(event){
		if(event.keyCode == 13){
			youngjin.gblock.updateGBlock_submit($(this), 'dodaac');
			$('.gblock_input_dodaac').focusout();
		}
	});
	
	$('.gblock_input_gblock').unbind(eventString);
	$('.gblock_input_gblock').bind(eventString, function(event){
		if(event.keyCode == 13){
			youngjin.gblock.updateGBlock_submit($(this), 'gblock');
			$('.gblock_input_gblock').focusout();
		}
	});
	
	$('.gblock_input_usNo').unbind(eventString);
	$('.gblock_input_usNo').bind(eventString, function(event){
		if(event.keyCode == 13){
			youngjin.gblock.updateGBlock_submit($(this), 'usNo');
			$('.gblock_input_usNo').focusout();
		}
	});
	
	$('.gblock_input_remark').unbind(eventString);
	$('.gblock_input_remark').bind(eventString, function(event){
		if(event.keyCode == 13){
			youngjin.gblock.updateGBlock_submit($(this), 'remark');
			$('.gblock_input_remark').focusout();
		}
	});
};

youngjin.gblock.addGBlock = function(){
	var url = contextPath + '/basic/gblock/gblock/add.json';
	
	var table = $('.yj_table');

	$.postJSON(url, {}, function(gblock){
		return jQuery.ajax({
			success : function(){
				if($('.gblock_state') != undefined){			
					var html = '<tr data-seq="' + gblock.seq + '">'
							+		'<td class="gblock_dodaac"></td>'
							+		'<td class="gblock_gblock"></td>'
							+		'<td class="gblock_usNo"></td>'
							+		'<td class="gblock_remark"></td>';
					table.children('tbody').prepend(html);
					
					youngjin.gblock.sync();
				}
			},
			error : function(){
				alert("에러 발생!");
			}
		});
	});
};

youngjin.gblock.updateGBlock = function(target, column){
	var parents = target.parents();
	
	var width = target.width();
	
	var value = target.html();
	
	var html = '<input type="text" value="' + value + '" class="gblock_input_' + column + '"  style="width : ' + (parseInt(width) - 3) + 'px;" /> ';
	
	if( target.children('.gblock_input_' + column).val() != undefined){
		
	} else {
		target.attr('data-old', value);
		target.html(html);
		target.children('.gblock_input_' + column).focus();
	}	
	
	youngjin.gblock.sync();
};

youngjin.gblock.updateGBlock_submit = function(target, column){
	var parent = target.parents('.gblock_' + column);
	var value = parent.attr('data-old');
	var newValue = target.val();
	
	if(value == newValue){
		parent.html(value);
	} else {
		var url = contextPath + "/basic/gblock/gblock/updateGBlock.json";
		var json = {};
		
		if(column == 'dodaac'){		
			json = {
					'seq' : parent.parents().attr('data-seq'),
					'dodaac' : newValue
			};
		} else if (column == 'gblock'){			
			json = {
					'seq' : parent.parents().attr('data-seq'),
					'gblock' : newValue
			};
		} else if (column == 'usNo'){			
			json = {
					'seq' : parent.parents().attr('data-seq'),
					'usNo' : newValue
			};
		} else if (column == 'remark'){			
			json = {
					'seq' : parent.parents().attr('data-seq'),
					'remark' : newValue
			};
		}
		
		$.postJSON(url, json, function(gblock){
			
			return jQuery.ajax({
				success : function(){					
					if(column == 'dodaac'){		
						parent.parents().children('.gblock_dodaac').html(gblock.dodaac);
					} else if (column == 'gblock'){			
						parent.parents().children('.gblock_gblock').html(gblock.gblock);
					} else if (column == 'usNo'){		
						parent.parents().children('.gblock_usNo').html(gblock.usNo);
					} else if (column == 'remark'){		
						parent.parents().children('.gblock_remark').html(gblock.remark);
					}
				},
				error : function(){
					alert("에러발생!");
				}
			});
		});
	}		
};