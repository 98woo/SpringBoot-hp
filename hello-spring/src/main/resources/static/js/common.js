$().ready(function () {
	$("a.deleteMe").on("click", function () {
		$.get("/ajax/member/delete-me", function (response) {
			console.log(response);
			var next = response.data.next;
			location.href = next;
			// console.log(next);
		});
	});
});