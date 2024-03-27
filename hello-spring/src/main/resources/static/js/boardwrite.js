/*
	자바스크립트 인터폴레이션. 안전하게 쓰려면 jstl을 사용한다. 
	internal 일때만 jstl를 css에서 사용할 수도 있다. 
	body 와 자바스크립트에서도 사용할 수 있다.
    에러가 없을 때는 자바스크립트를 보여주지 않는다.
*/
$().ready(function () {
	var dialog = $(".alert-dialog");
	if (dialog.length > 0) {
		dialog[0].showModal();
	}
})