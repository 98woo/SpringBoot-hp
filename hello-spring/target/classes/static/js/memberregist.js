$().ready(function () {
	$("#btn-login").on("click", function () {
		$.post("/member/login", {
			email: $("#email").val(),
			password: $("#password").val(),
		},
		function (response) {
			console.log(response);
			/*
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