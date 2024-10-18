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
<title>로그인</title>
<!-- Favicon-->
<!-- header part -->
<%@ include file="/WEB-INF/inc/header.jsp"%>

<style type="text/css">
.error-msg {
	color: red;
}
</style>
</head>
<body id="page-top">

	<!-- top part -->
	<%@ include file="/WEB-INF/inc/top.jsp"%>

	<!-- Contact Section-->
	<section class="page-section">
		<div class="container pt-5">
			<div class="row justify-content-center">
				<jsp:include page="/WEB-INF/inc/top.jsp">
					<jsp:param value="ID/PW찾기" name="title" />
					<jsp:param value="false" name="borderShow" />
				</jsp:include>
				<div class="col-lg-8 col-xl-7 mt-5">

					<!-- ID 찾기 폼 -->
					<form id="idFindForm" action="<c:url value="/idFindDo" />" method="POST">

						<!-- Name 입력창 -->
						<div class="form-floating mb-3">
							<input class="form-control" id="inputNameForId" type="text" name="userName" placeholder="이름" />
							<label for="inputNameForId">이름</label>
						</div>

						<!-- email 입력창 -->
						<div class="form-floating mb-3">
							<input class="form-control" id="inputEmailForId" type="text" name="userEmail" placeholder="이메일" />
							<label for="inputNameForId">이메일( '@' 포함 )</label>
						</div>
					</form>
					
					<div class="d-flex flex-column align-items-center mb-5">
						<button id="idFindBtn" class="btn btn-primary btn-lg" type="button">아이디 찾기</button>
					</div>

					<!-- 비밀번호 찾기 폼 -->
					<form id="pwFindForm" action="<c:url value="/pwFindDo" />" method="POST">

						<!-- ID 입력창 -->
						<div class="form-floating mb-3 d-flex">
							<input class="form-control" id="inputIdForPw" type="text" name="userId" placeholder="아이디" />
							<label for="inputIdForPw">아이디</label>
						</div>

						<!-- Name 입력창 -->
						<div class="form-floating mb-3">
							<input class="form-control" id="inputNameForPw" type="text" name="userName" placeholder="이름" />
							<label for="inputNameForPw">이름</label>
						</div>

						<!-- email 입력창 -->
						<div class="form-floating mb-3">
							<input class="form-control" id="inputEmailForPw" type="text" name="userEmail" placeholder="이메일" />
							<label for="inputEmailForPw">이메일( '@' 포함 )</label>
						</div>
					</form>

					<form id="pwResetView" action="<c:url value="/pwResetView" />" method="POST">
						<input id="resetId" type="hidden" name="resetId">
					</form>

					<div class="d-flex flex-column align-items-center mb-5">
						<button id="pwFindBtn" class="btn btn-primary btn-lg" type="button">비밀번호 찾기</button>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- footer part -->
<%@ include file="/WEB-INF/inc/footer.jsp"%>

<script type="text/javascript">
	//아이디 찾기
	document.getElementById("idFindBtn").addEventListener("click",()=>{
		let v_idFindForm = $("#idFindForm")
		let v_url = v_idFindForm.attr("action")
		let v_data = v_idFindForm.serialize();
		
		$.ajax({
			type:'POST',
			url:v_url,
			data: v_data,
			success: function(data){
				if(data){
					alert("해당 정보와 일치하는 아이디는 "+ data + " 입니다.")
				}else{
					alert("해당 정보와 일치하는 아이디가 없습니다.")
				}
			}
		})
	})
	
	//비번
	document.getElementById("pwFindBtn").addEventListener("click",()=>{
		let v_pwFindForm = $("#pwFindForm")
		let v_url = v_pwFindForm.attr("action")
		let v_data = v_pwFindForm.serialize();
		
		$.ajax({
			type:'POST',
			url:v_url,
			data: v_data,
			success: function(data){
				if(data){
					document.getElementById("resetId").value = data
					document.getElementById("pwResetView").submit()
				}else{
					alert("해당 정보와 일치하는 정보가 없습니다.")
				}
			}
		})
	})


</script>

</body>
</html>
