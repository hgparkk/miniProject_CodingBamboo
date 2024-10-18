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
		.board-title{
			font-weight: bold;
			font-size: 22px;
			margin-bottom: 10px;
		}
		
		.board-name{
			font-size: 20px;
		}
		
		.board-date{
			font-size: 20px;
		}
		
		.board-head{
			border-bottom: 1px solid #1abc9c;
		}
		
		.board-body{
			padding-top: 10px;
			min-height: 300px;
		}
		
		.reply-box{
			border-top: 2px solid black;
			border-bottom: 1px solid #DDDDDD;
			margin-top: 10px;
			margin-bottom: 10px;
		}
		
		.reply-box img{
			cursor: pointer;
		}
		
	</style>
</head>
<body id="page-top">

	<!-- navigation 부분 -->
	<%@ include file="/WEB-INF/inc/top.jsp"%>

	<div style="height:100px;"></div>
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
						<div class="board-body">
							${keyBoard.quContent} 
						</div>
					</div>
					<div class="d-flex justify-content-end">
						<%-- <c:if test="${sessionScope.login.userId eq keyBoard.userId and sessionScope.login.userId ne null}"> --%>
							<!-- POST 요청을 위해 버튼을 form 태그로 감싼다 -->
							<form action="${pageContext.request.contextPath }/boardEditView" method="POST">
								<!-- 현재 페이지의 글번호를 /boardEditView에 같이 보냄 -->
								<input type="hidden" value="${keyBoard.quNo }" name="no">
								<button class="btn btn-warning me-2" type="submit">수정</button>
							</form>
							
							<form id="delForm" action="${pageContext.request.contextPath }/boardDelDo" method="POST">
								<!-- 현재 페이지의 글번호를 /boardDeleteDo에 같이 보냄 -->
								<input type="hidden" value="${keyBoard.quNo }" name="no">
								<!-- 삭제버튼 클릭시 삭제확인 메시지를 띄움 -->
								<button id="delBtn" class="btn btn-danger" type="button">삭제</button>
							</form>
					</div>
					
					<!-- 답변 -->
					<div>
						<div class="answer-box">
							<c:forEach items="${keyAnswerList }" var="answerDTO">
								<div class="row pt-2 pb-2">
									<input type="hidden" value="${answerDTO.awNo }">
									<div class="col-7">${answerDTO.awContent }</div>
									<div class="col-1">
										<!-- 현재 로그인한 사람의 아이디와 해당 댓글(answerDTO)의
											 memId값이 같으면 삭제버튼 표기 -->
										<%-- <c:if test="${sessionScope.login.memId != null && sessionScope.login.memId == replyDTO.memId }">										
											<img src="${pageContext.request.contextPath }/assets/img/close.png" width="20" onclick="f_delete()">
										</c:if> --%>
									</div>
								</div>
							</c:forEach>
						</div>
						<div>
							<form class="row" id="answerForm" action="${pageContext.request.contextPath }/answerWriteDo" method="POST">
								<%-- <input type="hidden" name="userId" value="${sessionScope.login.memId }"> --%>
								<input type="hidden" name="quNo" value="${keyBoard.quNo }">
								<div class="col-10">
									<input id="inputAnswer" class="form-control" type="text" name="awContent">
								</div>
								<button id="answerBtn" class="btn btn-primary col-2" type="button">등록</button>
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
		
		// 답변 입력 창 클릭 이벤트
		document.getElementById('inputAnswer').addEventListener('click', ()=>{
			// 로그인이 안되어 있으면 실행
			if(!v_userName){
				location.href = "${pageContext.request.contextPath}/loginView";
			}	
		});
		
		let v_answerBox = document.querySelector('.answer-box');
		
		// 등록버튼 클릭 이벤트
		document.getElementById('answerBtn').addEventListener('click', ()=>{
			let v_input = document.getElementById('inputAnswer').value;
			
			if(!v_input){
				return;
			}
			
			// form 태그를 가져온 후 serialize 적용
			let v_formData = $('#answerForm').serialize();
			let v_url = $('#answerForm').attr('action');
			
			console.log(v_formData);
			
			$.ajax({
				type: 'POST',
				url: v_url,
				data: v_formData,
				success: function(resp){
					console.log(resp);  // JSON 객체 (jQuery에서 자동으로 parse 해줌)
					
					// 답변 입력창 비우기
					document.getElementById('inputAnswer').value = "";
					
					// 답변 한줄에 대한 html 코드 생성 (문자열)
					let v_answer = '<div class="row pt-2 pb-2">';
						v_answer += '  <input type="hidden" value="' + resp['awNo'] + '">';
						v_answer += '  <div class="col-7">' + resp['awContent'] + '</div>';
						v_answer += '  <div class="col-1">';
						v_answer += '    <button onclick="f_delete()">' + 삭제 + '</button>';
						v_answer += '  </div>';
						v_answer += '</div>';
						
					v_answerBox.innerHTML += v_answer;
				}
			});
			
		});
	</script>
</body>
</html>

