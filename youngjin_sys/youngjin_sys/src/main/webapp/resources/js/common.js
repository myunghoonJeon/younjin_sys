if (typeof youngjin.top == 'undefined') {
	youngjin.top = {};
}

$(function() {
	youngjin.top.form();
});

youngjin.top.form = function(){
	$('#top_form_pop').bind('click', function(event){
		var offset = $(this).offset();
		x = event.clientX - offset.left;
		y = event.clientY - offset.top;
		
		var form_pop_html = $('#form_pop_div').html();
		
		$.smartPop.open({ title:'form', bodyClose: true,  width: 400, height: 170, html: form_pop_html , position: 'fixed', left: x + 'px', top: y + 'px'});
	});
};