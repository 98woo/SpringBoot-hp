$().ready(function () {
	$("#btn-login").on("click", function () {
		
		// div가 클릭할 때 마다 반복되는 문제. 
		// 클릭이 발생할 때 error 클래스가 부여된 div를 삭제한다.
		$(".error").remove();
		$("div.grid").removeAttr("style");
		
		$.post("/member/login", {
			email: $("#email").val(),
			password: $("#password").val(),
		},
		function (response) {
			
			// 서버의 응답 가져오기 
			// 화면의 에러 메세지는 동적으로 추가하기
			var errors = response.data.errors;
			var errorMessage = response.data.errorMessage;
			var next = response.data.next;
			
			// 파라미터 유효성 검사에 실패 했을 경우. 
			if (errors != undefined) {
				
				/*
					var arr = [1, 2, 3, 4];
					for (var i in arr) {
						// i 에는 인덱스가 할당됨.
					}
				*/
				
				// 객체 리터럴
				for (var key in errors) {
					// 객체리터럴의 key가 할당된다. (배열과 객체리터럴 둘 다 사용할 수 있다.)
					// console.log(key);
					
					// key 라는 이름의 프로퍼티를 가져온다. key 를 직접 할당하면 key에 해당하는 value를 가져온다ㅣ.
					// console.log(errors.key);
					
					// 데이터를 가져올 때는 배열을 사용해야 한다.
					// console.log(errors[key]);
					var errorDiv = $("<div></div>");
					errorDiv.addClass("error");
					// 만들어지는 결과. <div class="error"></div>
					// div를 만들고 생성된 모든 에러 메서지를 div에 넣는다.
					
					var values = errors[key];
					for (var i in values) {
						var errorValue = values[i];
						
						var error = $("<div></div>");
						error.text(errorValue); 
						
						errorDiv.append(error);
					} // 이렇게 가져온 데이터를 jsp 화면에 보내준다.
					
					$("input[name="+key+"]").after(errorDiv);
				}	
				
				if (errors.email && errors.password) {
					// $("div.grid").addClass("validator-fail-both");
					var emailFailCount = errors.email.length;
					var passwordFailCount = errors.password.length;
					
					//if (emailFailCount > 0 || passwordFailCount > 0) {
						// css를 강제로 적용. Inline-Style 로 지정함. (addClass는 클래스를 부여)
						$("div.grid").css({
							"grid-template-rows": "28px " + 21 * emailFailCount + "px 28px " + 21 * passwordFailCount + "px 1fr",
						});
					//}
				}
				else if(errors.email) {
					// $("div.grid").addClass("validator-fail-email");
					var emailFailCount = errors.email.length;
					$("div.grid").css({
						"grid-template-rows": "28px " + 21 * emailFailCount + "px 28px 1fr",
					})
				}
				else if(errors.password) {
					// $("div.grid").addClass("validator-fail-password");
					var passwordFailCount = errors.password.length;
					$("div.grid").css({
						"grid-template-rows": "28px 28px " + 21 + passwordFailCount + "px 1fr",
					})
				}
			}
			
			// 파라미터 유효성 검사는 패스함.
			// 이메일이나 패스워드가 잘못된 경우
			if (errorMessage) {
				var errorDiv = $("<div></div>");
				errorDiv.addClass("error");
				errorDiv.text(errorMessage);
				
				$("#loginForm").after(errorDiv);
			}
			
			// 정상적으로 로그인에 성공한 경우.
			if (next) {
				console.log(next);
				location.href = next;
			}
			/* 예상되는 결과 
				response = {
					response: {
						error: {
							"email": []
						},
						errorMessage: ""
						next: "/board/list"
					}
				}
			 */
		});
	});
});