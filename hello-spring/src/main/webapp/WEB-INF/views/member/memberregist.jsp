<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style type="text/css">

	div.grid {
		display: grid;
		grid-template-columns: 120px 1fr;
		grid-template-rows: repeat(4, 28px) 1fr;
		row-gap: 10px;
	}
	div.grid > div.btn-group {
		grid-column: 1 / 3;
	}
	div.grid div.right-align {
		text-align: right;
	}
	div.errors {
		background-color: #ff00004a;
		opacity: 0.8;
		padding: 10px;
		color: #333;
	}
	div.errors:last-child {
		margin-botton: 15px;
	}
	label {
		padding-left: 10px;
	}
	button, input {
		padding: 10px;
	}
	input[type=file] {
		padding: 10px;
	}
	.available {
		background-color: #0f03;
	}
	.unusable {
		background-color: #f003;
	}
</style>
<script type="text/javascript" src="/js/lib/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	$().ready(function () {
		var alertDialog = $(".alert-dialog");
		
		// if (undefined, null, 0, "", false) -> 모두 false 이다. 실행되지 않음.

		console.log(alertDialog);
		
		if (alertDialog && alertDialog.lengt > 0) {
			alertDialog[0].showModal();
		}
		
		$("#email").on("keyup", function() {
			// 서버에게 사용할 수 있는 이메일인지 확인 받는다.
			$.get("/member/regist/available", {email:$(this).val() }, function(response) {
				console.log(response);
				var available = response.available;
				if (available) {
					$("#email").addClass("available");
					$("#email").removeClass("unusable");
					$("#btn-regist").removeAttr("disabled");
				} else {
					$("#email").addClass("unusable");
					$("#email").removeClass("available");
					$("#btn-regist").attr("disabled", "disabled");
				}
			})
			
		});
		
	});
</script>
</head>
<body>
	<c:if test="${not empty errorMessage}">
		<dialog class="alert-dialog">
			<h1>${errorMessage}</h1>
		</dialog>
	</c:if>
	
	<h1>회원가입</h1>
	<form action="/member/regist" method="post" enctype="multipart/form-data">
		<!-- 
		<div>
			<form:errors path="email" elemant="div" cssClass="errors" />
			<form:errors path="name" elemant="div" cssClass="errors" />
			<form:errors path="password" elemant="div" cssClass="errors" />
			<form:errors path="confirmPassword" elemant="div" cssClass="errors" />
		</div>
		--> 
		<div class="grid">
			<label for="email">이메일</label>
			<input id="email" type="email" name="email" value="${memberVO.email}" />
			
			<label for="name">이름</label>
			<input id="name" type="text" name="name" />
			
			<label for="password">비밀번호</label>
			<input id="password" type="password" name="password" value="${memberVO.password}" />
		
			<label for="confirmPassword">비밀번호 확인</label>
			<input id="confirmPassword" type="password" name="confirmPassword" value="${memberVO.confirmPassword}" />
		
			<div class="btn-group">
				<div class="right-align">
					<input type="submit" value="등록" id="btn-regist" />
				</div>
			</div>
			
		</div>
	</form>
</body>
</html>