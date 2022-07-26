let replyObject = {

	init: function() {
		let _this = this;

		$("#btn-insertReply").on("click", () => {
			_this.insertReply();
		});

		$("#btn-deleteReply").on("click", () => {
			_this.deleteReply();
		});


	},

	insertReply: function () {
		alert("덧글 등록 요청됨");

		let reply = {
			content: $("#content").val(),
		}
		let postId = $("#postId").val();

		$.ajax({
			type: "POST",
			url: `/post/${postId}/insertReply`,
			data: JSON.stringify(reply),
			contentType: "application/json; charset=utf-8",
			dataType: "text"
		}).done(function (response) {
			alert(response);
			location = `/post/${postId}`;
		});
	},

	deleteReply: function (postId, replyId) {
		alert("덧글 삭제 요청됨");
		// let reply = {
		// 	postId: $("#postId").val(),
		// 	replyId: $("#replyId").val()
		// }

		$.ajax({
			type: "DELETE",
			url: `/post/${postId}/deleteReply/${replyId}`,
			// data: JSON.stringify(reply),
			dataType: "text"
		}).done(function (response) {
			alert(response);
			location = `/post/${postId}`;
		});
	},

}

replyObject.init();