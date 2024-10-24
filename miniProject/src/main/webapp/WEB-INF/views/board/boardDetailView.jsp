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

.board-body {
	padding-top: 10px;
	min-height: 300px;
}

.answer-box {
	border-top: 1px solid #CCCCCC;
	border-bottom: 0px solid #DDDDDD;
	height: 300px;
}

.answer {
	margin: 1px;
	justify-content: space-between;
	padding: 3px;
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
							<div class="user-name">작성자: ${keyBoard.userName }</div>
						</div>

						<!-- pre 태그에 적용했던 폰트 스타일은 제거 -->
						<div class="board-body">${keyBoard.quContent}</div>
					</div>
					<div class="d-flex justify-content-end">
						<c:if
							test="${sessionScope.login.userId eq keyBoard.userId and sessionScope.login.userId ne null}">
							<!-- POST 요청을 위해 버튼을 form 태그로 감싼다 -->
							<form action="${pageContext.request.contextPath }/boardEditView"
								method="POST">
								<!-- 현재 페이지의 글번호를 /boardEditView에 같이 보냄 -->
								<input type="hidden" value="${keyBoard.quNo }" name="no">
								<c:if test="${keyBoard.quIsread != 1}">
									<button class="btn btn-warning me-2" type="submit">수정</button>
								</c:if>
							</form>
						</c:if>
						<c:if
							test="${sessionScope.login.userId ne null and (sessionScope.login.userId eq 'admin' or sessionScope.login.userId eq keyBoard.userId)}">
							<form id="delForm"
								action="${pageContext.request.contextPath }/boardDelDo"
								method="POST">
								<!-- 현재 페이지의 글번호를 /boardDeleteDo에 같이 보냄 -->
								<input type="hidden" value="${keyBoard.quNo }" name="no">
								<!-- 삭제버튼 클릭시 삭제확인 메시지를 띄움 -->
								<c:if test="${keyBoard.quIsread != 1}">
									<button id="delBtn" class="btn btn-danger" type="button">삭제</button>
								</c:if>
								<c:if test="${keyBoard.quIsread == 1}">
									<button id="delBtn" class="btn btn-danger"
										style="display: none;" type="button">삭제</button>
								</c:if>
							</form>
						</c:if>
					</div>

					<!-- 답변 -->
					<c:if test="${keyAnswerList.size() != 0}">
						<div>
							<div class="answer-box">
								<c:forEach items="${keyAnswerList }" var="answerDTO">
									<c:if test="${answerDTO.awNo != null}"></c:if>
									<div class="answer d-flex">
										<input type="hidden" value="${answerDTO.awNo }">
										<div class="col-9">${answerDTO.awContent }</div>
										<div class="col-1">
											<!-- 관리자 계정으로 로그인하면 삭제버튼 표시 -->
											<c:if test="${sessionScope.login.userId == 'admin'}">
												<button onclick="f_delete()"
													class="delAnswerBtn btn btn-danger">삭제</button>
											</c:if>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:if>
				</div>
				<div>
					<!-- 답변 등록 -->
					<form class="row" id="answerForm"
						action="${pageContext.request.contextPath }/answerWriteView"
						method="POST">
						<c:if test="${sessionScope.login.userId == 'admin'}">
							<input type="hidden" value="${keyBoard.quNo}" name="quNo">
							<div class="regist-box d-flex mt-2 justify-content-end">
								<button id="answerBtn" class="btn btn-secondary col-2"
									type="submit">답변 등록</button>
							</div>
						</c:if>
					</form>
				</div>
			</div>
		</div>
	</section>

	<!-- footer 부분 -->
	<%@ include file="/WEB-INF/inc/footer.jsp"%>

	<script type="text/javascript">
		// 질문 삭제
		document.getElementById('delBtn').addEventListener('click', ()=>{
			// 삭제 확인 메시지를 띄움
			if(confirm("정말로 삭제하시겠습니까?")){
				document.getElementById('delForm').submit();
			}
		});
		
		let v_answerBox = document.querySelector('.answer-box');
	</script>
</body>
</html>

