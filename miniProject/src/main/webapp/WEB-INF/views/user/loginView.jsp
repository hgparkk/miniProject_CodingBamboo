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
	<section class="page-section" id="contact">

		<div class="container pt-5">
			<!-- Contact Section Heading-->
			<h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">로그인</h2>
			<!-- Icon Divider-->
			<div class="divider-custom">
				<div class="divider-custom-line"></div>
				<div class="divider-custom-icon">
					<i class="fas fa-star"></i>
				</div>
				<div class="divider-custom-line"></div>
			</div>
			<!-- Contact Section Form-->
			<div class="row justify-content-center">
				<div class="col-lg-8 col-xl-7">
					<!-- type=submit인 버튼 클릭시 form 태그의 action 링크가 실행됨 -->
					<form id="contactForm" action="${pageContext.request.contextPath }/loginDo" method="POST">
					<!-- /login으로 쏠때, /loginView를 요청했었던 URL주소를 같이 보냄 -->
					<input type="hidden" name="from" value>
					
						<!-- 아이디 input-->
					<div class="form-floating mb-3">
						<input class="form-control" id="id" type="text" name="userId"
							 data-sb-validations="required" value="${cookie.rememberId.value }" />
						<label for="name">아이디</label>
					</div>

						<!-- 비밀번호 input-->
						<div class="form-floating mb-3">
							<!-- inputPw의 값이 pw=value 형태로 전송되어야 함. pw를 name속성에 넣어주기 -->
							<input class="form-control" id="userPw" type="password" name="userPw" />
							<label for="userPw">비밀번호</label>
						</div>
						<!-- 아이디 기억하기 인풋 -->
						<div "class="form-check mb-3">
							<label class="form-check-label" for="inputRememberId" style="color:white; font-size:20px">아이디기억하기</label>
							<input class="form-check-input" type="checkbox" id="inputRememberId" type="checkbox" name="rememberId">
						</div>
						<!-- Submit Button-->
						<button class="btn btn-primary btn-xl" id="submitButton" type="submit">로그인</button>
					</form>
				</div>
			</div>
		</div>
	</section>

	<!-- footer part -->
<%@ include file="/WEB-INF/inc/footer.jsp"%>
</body>
</html>

