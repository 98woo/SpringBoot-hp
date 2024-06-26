$().ready(function () {
	$("#uploadExcelfile").on("click", function () {
		$("#excelfile").click();
	});
	
	/* 파일이 선택되면 수행해라 */
	$("#excelfile").on("change", function () {
		// 선택된 파일의 정보를 출력
		var file = $(this)[0].files[0]; // Input 파일 타입의 선택된 첫 번째 
		var filename = file.name;
		
		if (!filename.endsWith(".xlsx")) {
			alert("엑셀 파일을 선택해주세요!!");
			// 엑셀파일을 선택하지 않았을 경우
			// 함수 실행을 종료 시킨다.
			return;
		}
		
		// 파일을 서버로 전송 시킨다.
		var formData = new FormData();
		// formData 에 파일 정보를 첨부시킨다.
		formData.append("excelFile", file); // ServiceImpl 에 파라미터로 등록한 서버 이름
		
		// 파일 전송은 $.post 로 할 수 없다.
		// $.post();
		$.ajax({
			url: "/ajax/board/excel/write", // 요청을 보낼 주소
			type: "POST", // 요청을 보낼 HttpMethod
			data: formData, // 요청을 보낼 데이터 (FormData)
			processData: false,
			contentType: false,
			success: function(response) {
				var data = response.data;
				if (data.result && data.next) {
					location.href = data.next;
				}
			},
		});
	});
});