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


<!-- header 부분 -->
<%@ include file="/WEB-INF/inc/header.jsp"%>

<style type="text/css">
.board-title {
	font-weight: bold;
	font-size: 22px;
	margin-bottom: 10px;
}

.board-name {
	font-size: 20px;
}

.board-date {
	font-size: 20px;
}

.board-head {
	border: 1px solid #CCCCCC;
	background-color: #DDDDDD;
}

.board-body {
	padding-top: 10px;
	min-height: 300px;
}

.answer-box {
	border-top: 0px solid black;
	border-bottom: 0px solid #DDDDDD;
}

.answer{
	border-radius : 10px;
	border: 1px solid #DDDDDD;
	height: 300px;
	margin: 1px;
	justify-content: space-between;
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
			<div class="row justify-content-center">
				<div class="col-lg-8 col-xl-7">
					<!-- 제목, 작성자, 작성일, 내용 -->
					<div>
						<div class="board-head d-flex justify-content-between">
							<div class="qu-title mb-1">${keyBoard.quTitle }</div>
							<div class="user-name">${keyBoard.userName }</div>
						</div>

						<!-- pre 태그에 적용했던 폰트 스타일은 제거 -->
						<div class="board-body">${keyBoard.quContent}</div>
					</div>
					<div class="d-flex justify-content-end">
						<c:if test="${sessionScope.login.userId eq keyBoard.userId and sessionScope.login.userId ne null}">
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
						<c:if test="${sessionScope.login.userId ne null and (sessionScope.login.userId eq 'admin' or sessionScope.login.userId eq keyBoard.userId)}">
							<form id="delForm"
								action="${pageContext.request.contextPath }/boardDelDo"
								method="POST">
								<!-- 현재 페이지의 글번호를 /boardDeleteDo에 같이 보냄 -->
								<input type="hidden" value="${keyBoard.quNo }" name="no">
								<!-- 삭제버튼 클릭시 삭제확인 메시지를 띄움 -->
								<c:if test="${keyBoard.quIsread != 1}">
									<button id="delBtn" class="btn btn-danger" type="button">삭제</button>
								</c:if>
							</form>
						</c:if>
					</div>

					<!-- 답변 -->
					<div>
						<div class="answer-box">
							<c:forEach items="${keyAnswerList }" var="answerDTO">
								<div class="row pt-2 pb-2 answer">
									<input type="hidden" value="${answerDTO.awNo }">
									<div class="col-8">${answerDTO.awContent }</div>
									<div class="col-2">
										<!-- 관리자 계정으로 로그인하면 삭제버튼 표시 -->
										<c:if test="${sessionScope.login.userId == 'admin'}">
											<button onclick="f_delete()">삭제</button>
										</c:if>
									</div>
								</div>
							</c:forEach>
						</div>
						<div>
							<form class="row" id="answerForm"
								action="${pageContext.request.contextPath }/answerWriteDo"
								method="POST">
								<input type="hidden" name="userId"
									value="${sessionScope.login.userId }"> <input
									type="hidden" name="quNo" value="${keyBoard.quNo }">
								<c:if test="${sessionScope.login.userId == 'admin'}">
									<div class="col-10">
										<input id="inputAnswer" class="form-control" type="text"
											name="awContent">
									</div>
									<button id="answerBtn" class="btn btn-primary col-2"
										type="button">등록</button>
								</c:if>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- footer 부분 -->
	<%@ include file="/WEB-INF/inc/footer.jsp"%>

	<script type="text/javascript">
		
		document.getElementById('delBtn').addEventListener('click', ()=>{
			// 삭제 확인 메시지를 띄움
			if(confirm("정말로 삭제하시겠습니까?")){
				document.getElementById('delForm').submit();
			}
		});
		
		let v_answerBox = document.querySelector('.answer-box');
		
		// 등록버튼 클릭 이벤트
		document.getElementById('answerBtn').addEventListener('click', ()=>{
			let v_input = document.getElementById('inputAnswer').value;
			
			if(!v_input){
				return;
			}
			
			let v_formData = $('#answerForm').serialize();
			let v_url = $('#answerForm').attr('action');
			
			$.ajax({
				type: 'POST',
				url: v_url,
				data: v_formData,
				success: function(resp){
					// 답변 입력창 비우기
					document.getElementById('inputAnswer').value = "";
					
					// 유저 아이디 임시 저장
					let v_userId = "${sessionScope.login.userId}"
					
					// 답변 한줄에 대한 html 코드 생성 (문자열)
					let v_answer = '<div class="row pt-2 pb-2 answer">';
						v_answer += '  <input type="hidden" value="' + resp['awNo'] + '">';
						v_answer += '  <div class="col-8">' + resp['awContent'] + '</div>';
						v_answer += '  <div class="col-2">';
						if (v_userId == "admin"){
							v_answer += '    <button onclick="f_delete()">' + "삭제" + '</button>';
						}
						v_answer += '  </div>';
						v_answer += '</div>';
						
					v_answerBox.innerHTML += v_answer;
				}
			});
			
		});
		
		function f_delete(){
			v_parent = event.target.parentElement.parentElement;
			
			v_awNo = event.target.parentElement.parentElement.children[0].value;
			
			let v_ajax = new XMLHttpRequest();
			
			v_ajax.open('POST', '${pageContext.request.contextPath}/delAnswerDo');
			
			let v_data = "awNo=" + parseInt(v_awNo) + "&quNo="+ parseInt(${keyBoard.quNo });
			
			v_ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			
			v_ajax.onload = ()=>{
				if(v_ajax.response == 'success'){
					alert("답변이 삭제되었습니다.")
					v_parent.remove();
				}
			}
			v_ajax.send(v_data);
		}
	</script>
</body>
</html>

