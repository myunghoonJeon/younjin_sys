function webBrowserCheck() {

	if (navigator.userAgent.indexOf('MSIE') > 0) {
		return 'MS';
	} else if (navigator.userAgent.indexOf("Chrome") > 0) {
		return 'C';
	} else if (navigator.userAgent.indexOf("Firefox") > 0) {
		return 'FF';
	} else if (navigator.userAgent.indexOf("Safari") > 0) {
		return 'S';
	} else {
		return 'Any';
	}
}

function roundXL(n, digits) {
	  if (digits >= 0) return parseFloat(n.toFixed(digits)); // 소수부 반올림

	  digits = Math.pow(10, digits); // 정수부 반올림
	  var t = Math.round(n * digits) / digits;

	  return parseFloat(t.toFixed(0));
}

function calcDate(date){
	var year = date.getFullYear();
	var month = Number(date.getMonth()) + 1;
	var dateData = date.getDate();

	if(month < 10){
		month = '0' + month;
	}
	
	if(dateData < 10){
		dateData = '0' + dateData;
	}
	
	return year + '-' + month + '-' + dateData;
}

/**
 * 폼생성
 * @param frmName {폼이름}
 * @param frmMethod {get post)
 * @param frmAction {액션 주소)
 * @param frmTarget {타겟)
 */
 function createForm(frmName, frmMethod, frmAction) {
   var frm = document.createElement("form");
   frm.name  = frmName;
   frm.method  = frmMethod;
   frm.action  = frmAction;
   return frm;
 }
 /**
 * 히든추가
 * @param frmName {폼이름}
 * @param hiddenName {히튼이름)
 * @param hiddenValue {히든 값)
 */
  function addHidden(hiddenName, hiddenValue) {
   var item = document.createElement("input");  
   item.type = "hidden";
   item.name = hiddenName;
   item.value = hiddenValue;
   return item;
}
/**
  * 폼 서브밋
  */
function fromSubmit(){
 var form = createForm("popForm", "post", "thekziel.html", "kizel");
 document.body.appendChild(popForm);
 addHidden(form, "method", "pop");
 form.submit();
 document.body.removeChild(popForm);
}