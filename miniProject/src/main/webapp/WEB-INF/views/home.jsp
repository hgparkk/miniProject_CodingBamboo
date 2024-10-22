<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>CodingBamboo</title>

<%@ include file="/WEB-INF/inc/header.jsp"%>

</head>
<body id="page-top">

	<%@ include file="/WEB-INF/inc/top.jsp"%>

	<div style="height: 150px;"></div>

	<div style="width: 1340px; margin:auto;">
		<!-- Masthead-->
		<header class="masthead bg-white text-black text-center">
			<div class="container d-flex align-items-center justify-content-center">
				<!-- Masthead Avatar Image-->
				<img class="masthead-avatar mb-5" src="assets/img/bamboo.png" alt="..." />
				<!-- Masthead Heading-->
				<div style="margin-right: 200px;">
					<h1 class="masthead-heading text-uppercase mb-3">탄소 배출량 계산 시스템</h1>
					<!-- Masthead Subheading-->
					<p class="masthead-subheading font-weight-light mb-0">Coding Bamboo</p>
				</div>
			</div>
		</header>

		<!-- About Section-->
		<section class="page-section bg-white text-black mb-0" id="about">
			<div class="container">
				<div class="row">
					<div class="col-6 mx-auto my-auto">
						<div class="lead mb-3">탄소 배출량 및 권장 배출량을 예측하고 건축 시 예상되는 탄소 배출량과 건물 내 에너지 사용량을 바탕으로 예상 탄소배출량을 확인 하여 계산한 결과를 바탕으로 탄소배출량 저감을 유도 합니다.</div>
						<div class="lead">사용 전력량을 통해 예상 탄소 배출량을 계산합니다. 건물의 면적에 따른 평균 탄소 배출량과 비교하여 탄소배출량 저감을 유도 합니다.</div>
					</div>
				</div>

			</div>
		</section>
	</div>

	<%@ include file="/WEB-INF/inc/footer.jsp"%>

</body>
</html>
