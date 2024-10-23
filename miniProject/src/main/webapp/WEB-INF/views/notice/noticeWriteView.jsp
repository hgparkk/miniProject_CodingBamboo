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
	
	<!-- 네이버 스마트 에디터의 js 파일 불러오기 -->
	<script src="${pageContext.request.contextPath }/nse/js/HuskyEZCreator.js"></script>

</head>
<body id="page-top">

	<!-- navigation 부분 -->
	<%@ include file="/WEB-INF/inc/top.jsp"%>

	<!-- Contact Section-->
	<section class="page-section" id="contact">
		<!-- 부트스트랩으로 padding-top 을 좀 주고자 한다. -->
		<div class="container pt-5">
			<div style="height:50px; margin-bottom: 10px;"></div>
			<!-- Contact Section Heading-->
			<h2
				class="page-section-heading text-center text-uppercase text-secondary mb-0">공지 등록하기</h2>
			<!-- Contact Section Form-->
			<div class="row justify-content-center">
		 		<div class="col-lg-8 col-xl-7">
					<c:if test="${sessionScope.login.userId == 'admin'}">
						<form id="noticeWriteForm" action="${pageContext.request.contextPath }/noticeWriteDo" method="POST">
							<!-- 제목 -->
							<div class="mb-3">
								<input class="form-control" type="text" name="noTitle" id="title" placeholder="제목을 입력해주세요" />
							</div>
							
							<!-- 내용 -->
							<div class="mb-3">
								<textarea id="smartEditor" class="form-control" rows="10" name="noContent"></textarea>
							</div>
							
							<!-- 파일 -->
							<div class="mb-3">
								<label for="formFileMultiple" class="form-label">파일 첨부</label>
								<input class="form-control" name="boFile" type="file" id="formFileMultiple" multiple>
							</div>
							
							<!-- 등록 및 취소 -->
							<div class="d-flex justify-content-end">
								<a class="btn btn-secondary me-2" href="${pageContext.request.contextPath }/noticeView">취소</a>
								<button id="writeBtn" class="btn btn-primary" type="button">등록</button>
							</div>
						</form>
					</c:if>
					
				</div>
			</div>
		</div>
	</section>

	<!-- footer 부분 -->
	<%@ include file="/WEB-INF/inc/footer.jsp"%>
	
	<script type="text/javascript">
		var oEditors = [];
		
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors,
			elPlaceHolder : "smartEditor",	// textarea의 id
			sSkinURI : "${pageContext.request.contextPath}/resources/nse/SmartEditor2Skin.html"
		});
		
		v_title = document.getElementById('title');
		v_content = document.getElementById("smartEditor")
		
		// 글 등록 버튼 클릭
		document.getElementById("writeBtn").addEventListener('click', ()=>{
			// 에디터에 작성된 내용을 숨겨진 textarea에 반영
			oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);
			
			v_titleValue = v_title.value
			v_contentValue = v_content.value
			
			v_contentValue = v_contentValue.replaceAll("&nbsp;"," ")
			
			v_title.value = v_titleValue.trim()
			v_content.value = v_contentValue.trim()
			
			if(!v_title.value || !v_content.value){
				alert("입력된 내용이 없습니다")
				return
			}
			
			// form 태그의 submit 실행
			document.getElementById('noticeWriteForm').submit();
		});
	</script>

</body>
</html>

