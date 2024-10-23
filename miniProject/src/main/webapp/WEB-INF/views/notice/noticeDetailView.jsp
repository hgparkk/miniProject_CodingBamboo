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
							<div class="qu-title mb-1">${keyNotice.noTitle }</div>
							<div class="user-name">시스템 관리자</div>
						</div>
						
					<!-- 첨부파일 목록 -->
					<div class="mb-3 d-flex">
						<c:forEach items="${attachList }" var="attach">
							<div>
								<a href="<c:url value="/filedownload?fileName=${attach.atchFileName }&fileNoticeName=${attach.atchName}"/>">
									${attach.atchName }(${attach.atchFancySize })
								</a>
							</div>
						</c:forEach>
					</div>

						<!-- pre 태그에 적용했던 폰트 스타일은 제거 -->
						<div class="board-body">${keyNotice.noContent}</div>
					</div>
					<div class="d-flex justify-content-end">
						<c:if test="${sessionScope.login.userId eq 'admin'}">
							<!-- POST 요청을 위해 버튼을 form 태그로 감쌈 -->
							<form action="${pageContext.request.contextPath }/registTopNoticeDo"
								method="POST">
								<!-- 현재 페이지의 글번호를 /noticeEditDo에 같이 보냄 -->
								<input type="hidden" value="${keyNotice.noNo }" name="no">
								<c:if test="${keyNotice.noTop != 1 }">
									<button class="btn btn-warning me-2" type="submit">top 등록</button>
								</c:if>
							</form>
							
							<form action="${pageContext.request.contextPath }/deleteTopNoticeDo"
								method="POST">
								<!-- 현재 페이지의 글번호를 /noticeEditView에 같이 보냄 -->
								<input type="hidden" value="${keyNotice.noNo }" name="no">
								<c:if test="${keyNotice.noTop == 1 }">
									<button class="btn btn-warning me-2" type="submit">top 삭제</button>
								</c:if>
							</form>
							
							<form action="${pageContext.request.contextPath }/noticeEditView"
								method="POST">
								<!-- 현재 페이지의 글번호를 /noticeEditView에 같이 보냄 -->
								<input type="hidden" value="${keyNotice.noNo }" name="no">
								<button class="btn btn-warning me-2" type="submit">수정</button>
							</form>
	
							<form id="delForm"
								action="${pageContext.request.contextPath }/noticeDelDo"
								method="POST">
								<!-- 현재 페이지의 글번호를 /boardDeleteDo에 같이 보냄 -->
								<input type="hidden" value="${keyNotice.noNo }" name="no">
								<!-- 삭제버튼 클릭시 삭제확인 메시지를 띄움 -->
								<button id="delBtn" class="btn btn-danger" type="button">삭제</button>
							</form>
						</c:if>
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
	</script>
</body>
</html>

