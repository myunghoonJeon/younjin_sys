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