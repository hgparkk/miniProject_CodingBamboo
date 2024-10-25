<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Coding Bamboo</title>

<script
	src="${pageContext.request.contextPath }/nse/js/HuskyEZCreator.js"></script>


<!-- header 부분 -->
<%@ include file="/WEB-INF/inc/header.jsp"%>

<style type="text/css">

p {
	margin: 0;
}

.container {
	width: 1200px;
}

.board-title {
	font-weight: bold;
	font-size: 22px;
	margin-bottom: 10px;
}

.board-name {
	font-size: 20px;
}

.qna-box {
	width: 850px;
	border: 1px solid #CCCCCC;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
	border-bottom-left-radius: 5px;
	border-bottom-right-radius: 5px;
}

.board-head {
	border-bottom: 1px solid #CCCCCC;
	background-color: #DDDDDD;
	padding: 7px;
}

.qu-title{
	font-size: 20px;
}

.user-name{
	font-size: 15px;
}

.board-body {
	padding-top: 7px;
	padding-left: 7px;
	min-height: 300px;
	overflow-y: auto;
}

.answer-box {
	border-top: 1px solid #CCCCCC;
	border-bottom: 0px solid #DDDDDD;
	height: 300px;
	overflow-y: auto;
}

.answerDelForm {
	justify-content: space-between;
	padding: 0px;
	margin: 0px;
}

.answer-content {
	padding-top: 7px;
	padding-left: 7px;
}

.delAnswerBtn {
	margin-bottom: 10px;
}

.regist-box {
	width: 850px;
	height: 40px;
	justify-content: space-between;
	padding-left: 0px;
	padding-right: 0px;
}
</style>
</head>
<body id="page-top">

	<!-- navigation 부분 -->
	<%@ include file="/WEB-INF/inc/top.jsp"%>

	<div style="height: 100px;"></div>
	<!-- Contact Section-->
	<section class="page-section" id="contact">
		<!-- 부트스트랩으로 padding-top 을 좀 주고자 한다. -->
		<div class="container pt-5">

			<!-- Contact Section Form-->
			<div class="d-flex flex-column align-items-center">
				<div class="qna-box">
					<!-- 제목, 작성자, 작성일, 내용 -->
					<div>
						<div class="board-head d-flex justify-content-between">
							<div class="qu-title mb-1">${keyBoard.quTitle }</div>
							<div class="user-name pt-1">작성자: ${keyBoard.userName }</div>
						</div>

						<!-- pre 태그에 적용했던 폰트 스타일은 제거 -->
						<div class="board-body">${keyBoard.quContent}</div>
					</div>

					<!-- 답변 -->
					<c:if test="${keyAnswerList.size() != 0}">
						<div class="answer-box">
							<c:if test="${keyAnswerList[0]['awNo'] != null}">
								<div class="answer-content">${keyAnswerList[0]['awContent']}</div>
							</c:if>
						</div>
					</c:if>
				</div>
				<div class="regist-box">
					<!-- 답변 등록 -->
					<div class="d-flex mt-2 justify-content-end">
						<c:if test="${keyAnswerList[0]['awNo'] == null }">
							<c:if
								test="${sessionScope.login.userId eq keyBoard.userId and sessionScope.login.userId ne null}">
								<form action="${pageContext.request.contextPath }/boardEditView"
									method="POST">
									<!-- 현재 페이지의 글번호를 /boardEditView에 같이 보냄 -->
									<input type="hidden" value="${keyBoard.quNo }" name="no">
									<c:if test="${keyBoard.quIsread != 1}">
										<button class="btn btn-warning me-2" type="submit">질문
											수정</button>
									</c:if>
								</form>
							</c:if>
							<c:if
								test="${sessionScope.login.userId eq 'admin' or sessionScope.login.userId eq keyBoard.userId}">
								<form id="delForm"
									action="${pageContext.request.contextPath }/boardDelDo"
									method="POST">
									<!-- 현재 페이지의 글번호를 /boardDeleteDo에 같이 보냄 -->
									<input type="hidden" value="${keyBoard.quNo }" name="no">
									<input type="hidden" value="${keyAnswerList[0]['awNo'] }"
										name="awNo">
									<!-- 삭제버튼 클릭시 삭제확인 메시지를 띄움 -->
									<button id="quDelBtn" class="btn btn-danger me-2" type="button">질문
										삭제</button>
								</form>
							</c:if>
							<c:if test="${sessionScope.login.userId == 'admin'}">
								<form id="answerForm"
									action="${pageContext.request.contextPath }/answerWriteView"
									method="POST">
									<input type="hidden" value="${keyBoard.quNo}" name="quNo">
									<input type="hidden" value="${keyAnswerList[0]['awNo']}"
										name="awNo">
									<button id="answerBtn" class="btn btn-secondary ms-2"
										type="submit">답변 등록</button>
								</form>
							</c:if>
						</c:if>
					</div>
					<c:if test="${keyAnswerList[0]['awNo'] != null }">
						<c:if test="${sessionScope.login.userId == 'admin'}">
							<div class="d-flex mt-2 justify-content-end">
								<form id="editForm"
									action="${pageContext.request.contextPath }/answerEditView"
									method="POST">
									<input type="hidden" value="${keyBoard.quNo}" name="quNo">
									<button id="editBtn" class="btn btn-secondary me-2"
										type="submit">답변 수정</button>
								</form>
								<form id="answerDelForm"
									action="${pageContext.request.contextPath }/delAnswerDo"
									method="POST">
									<input type="hidden" value="${keyAnswerList[0]['quNo']}"
										name="quNo"> <input type="hidden"
										value="${keyAnswerList[0]['awNo']}" name="awNo">
									<button id="ansDelBtn" class="btn btn-danger ms-2"
										type="button">답변 삭제</button>
								</form>
							</div>
						</c:if>
					</c:if>
				</div>
			</div>
		</div>
	</section>

	<!-- footer 부분 -->
	<%@ include file="/WEB-INF/inc/footer.jsp"%>

	<script type="text/javascript">
		// 질문 삭제
		document.getElementById('quDelBtn').addEventListener('click', ()=>{
			// 삭제 확인 메시지를 띄움
			if(confirm("정말로 삭제하시겠습니까?")){
				document.getElementById('delForm').submit();
			}
		});
		
		let v_answerBox = document.querySelector('.answer-box');
		
		// 답변 삭제
		document.getElementById('ansDelBtn').addEventListener('click', ()=>{
			if(confirm("정말로 삭제하시겠습니까?")){
				document.getElementById('answerDelForm').submit();
			}
		});
		
	</script>
</body>
</html>

