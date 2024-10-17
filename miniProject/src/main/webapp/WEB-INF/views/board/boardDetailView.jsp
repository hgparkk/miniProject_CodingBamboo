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
	<title>${keyBoard.quTitle }</title>
	

	<!-- header 부분 -->
	<%@ include file="/WEB-INF/inc/header.jsp"%>
	
	<style type="text/css">
		.board-title{
			font-weight: bold;
			font-size: 22px;
			margin-bottom: 10px;
		}
		
		.name-date{
			display: flex;
			justify-content: space-between;
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

	<!-- Contact Section-->
	<section class="page-section" id="contact">
		<!-- 부트스트랩으로 padding-top 을 좀 주고자 한다. -->
		<div class="container pt-5">
			
			<!-- Contact Section Form-->
			<div class="row justify-content-center">
		 		<div class="col-lg-8 col-xl-7">
					<!-- 제목, 작성자, 작성일, 내용 -->
					<div>
						<div class="board-head">
							<div class="qu-title">${keyBoard.quTitle }</div>
							<div class="name-date">
								<div class="user-name">${keyBoard.userName }</div>
							</div>
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
						<%-- </c:if> --%>
					</div>
					
					<!-- 답변 -->
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
				document.getElementById('delForm').submit();  // submit 버튼을 누른것과 동일
			}
		});
		
	</script>
</body>
</html>

