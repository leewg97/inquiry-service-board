let postObject = {

	// init() 함수 선언 
	init: function() {
		let _this = this;

		// "#btn-save" 버튼에 "click" 이벤트가 발생하면 insertPost() 함수를 호출한다. 
		$("#btn-insert").on("click", () => {
			_this.insertPost();
		});

		$("#btn-update").on("click", () => {
			_this.updatePost();
		});

		$("#btn-delete").on("click", () => {
			_this.deletePost();
		});

		// $("#btn-insertReply").on("click", () => {
		// 	_this.insertReply();
		// });
		//
		// $("#btn-deleteReply").on("click", () => {
		// 	_this.deleteReply();
		// });


	},

	insertPost: function() {
		alert("1:1 문의 등록 요청됨");

		let post = {
			title: $("#title").val(),
			content: $("#content").val()
		}

		// Ajax를 이용한 비동기 호출
		$.ajax({
			type: "POST", // 요청 방식
			url: "/post/insertPost", // 요청 path
			data: JSON.stringify(post), // post Object를 JSON으로 변환
			// HTTP 바디에 설정되는 데이터의 마임타입설정 
			contentType: "application/json; charset=utf-8"
			// done() : 요청 처리에 성공했을 때 실행될 코드를 작성한다.
			// 응답으로 들어온 JSON 데이터를 response로 받는다. 
		}).done(function(response) {
			// 메인 페이지로 이동한다.
			alert(response);
			location = "/";
		});

	},

	updatePost: function () {
		alert("1:1 문의 수정 요청됨");

		let post = {
			id: $("#id").val(),
			title: $("#title").val(),
			content: $("#content").val(),
		}

		$.ajax({
			type: "PUT",
			url: "/post/updatePost",
			data: JSON.stringify(post),
			contentType: "application/json; charset=utf-8"
		}).done(function (response) {
			alert(response);
			location = "/";
		});
	},

	deletePost : function() {
		alert("1:1 문의 삭제 요청됨");

		let id = $("#id").text();

		$.ajax({
			type: "DELETE",
			url: "/post/deletePost/" + id,
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			alert(response);
			location = "/";
		});
	},

	// insertReply: function () {
	// 	alert("덧글 등록 요청됨");
	//
	// 	let reply = {
	// 		content: $("#content").val(),
	// 	}
	// 	let postId = $("#postId").val();
	//
	// 	$.ajax({
	// 		type: "POST",
	// 		url: `/post/${postId}/insertReply`,
	// 		data: JSON.stringify(reply),
	// 		contentType: "application/json; charset=utf-8",
	// 		dataType: "text"
	// 	}).done(function (response) {
	// 		alert(response);
	// 		location = `/post/${postId}`;
	// 	});
	// },
	//
	// deleteReply: function () {
	// 	alert("덧글 삭제 요청됨");
	//
	// 	let postId = $("#postId").val();
	// 	let replyId = $("#replyId").val();
	//
	// 	$.ajax({
	// 		type: "DELETE",
	// 		url: `/post/${postId}/deleteReply/${replyId}`,
	// 		dataType: "json",
	// 	}).done(function (response) {
	// 		alert(response);
	// 		location = `/post/${postId}`;
	// 	});
	// },

}

postObject.init();