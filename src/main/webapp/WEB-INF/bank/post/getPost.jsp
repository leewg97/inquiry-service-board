<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container p-3 my-3 border">
	<div>
		<h4>${post.title }</h4>
		<div>${post.content }</div>
	</div>
	<br>
	<div>
		포스트 번호 : <span id="id"><i>${post.id }</i></span><br>
		작성자 : <span><i>${post.user.username }</i></span>
	</div>

	<hr>
		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<c:if test="${post.user.username == principal.username }">
		<a href="/post/updatePost/${post.id }" id="btn-edit" class="btn btn-warning">수정하기</a>
		<button id="btn-delete" class="btn btn-danger" type="button">삭제하기</button>
	</c:if>
	<br>
	<br>
		<div>
			<div class="container mt-3">
				<c:if test="${post.replys.size() != 0 }">
					<table class="table table-hover">
						<thead>
							<tr class="reply_menu">
								<td style="font-weight: 600; width: 80%;">내용</td>
								<td style="font-weight: 600; width: 10%;">작성자</td>
								<td style="font-weight: 600; width: 10%;">삭제</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="reply" items="${post.replys}">
								<tr id="reply-${reply.id}">
									<td>${reply.content}</td>
									<td>${reply.user.username}</td>
									<td>
										<input type="hidden" id="replyId" value="${reply.id}">
										<c:if test="${reply.user.username == principal.username}">
											<button onclick="replyObject.deleteReply('${post.id}', '${reply.id}')" class="btn btn-sm btn-dark">삭제</button>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	<form>
		<div class="container mt-3">
			<input type="hidden" id="postId" value="${post.id }">
			<table class="table">
				<tbody>
				<tr align="right">
					<td>
						<textarea id="content" rows="2" class="form-control"></textarea>
						<button id="btn-insertReply" class="btn btn-secondary" type="button">덧글등록</button>
					</td>
				</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>

<script src="/js/reply.js"></script>
<script src="/js/post.js"></script>
<%@ include file="../layout/footer.jsp"%>
