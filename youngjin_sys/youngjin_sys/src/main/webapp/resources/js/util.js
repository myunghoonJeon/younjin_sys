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