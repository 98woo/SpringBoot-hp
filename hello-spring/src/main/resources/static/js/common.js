$().ready(function () {
	$("a.deleteMe").on("click", function () {
		$.get("/member/delete-me", function (response) {
			var next = response.date.next;
			location.href = next;
		});
	});
});