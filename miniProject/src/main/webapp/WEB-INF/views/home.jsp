<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>CodingBamboo</title>

<%@ include file="/WEB-INF/inc/header.jsp"%>

<style>
	.home-title{
		background-image: linear-gradient(rgba(0,0,0,0.4),rgba(0,0,0,0.4)),url('assets/img/main-bg.jpg');
		width: 100%;
		min-width:1340px;
	}
</style>

</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>
	<div class="home-title">
		<div style="height: 150px"></div>
		<div style="width: 1340px; margin: auto;">
			<!-- Masthead-->
			<div class="text-white text-center">
				<div class="container d-flex align-items-center justify-content-center" style="margin-top:100px; margin-bottom:100px;">
					<img class="mb-5" src="assets/img/bamboo.png" alt="..." />
					<div style="margin-right: 100px;">
						<p class="text-uppercase mb-3 fw-bold" style="font-size:80px; width:846.3px;">탄소 배출량 계산 시스템</p>
						<p class="font-weight-light mb-0">Coding Bamboo</p>
					</div>
				</div>
			</div>

			<!-- About Section-->
			<div class="text-white" style="margin-bottom:100px;">
				<div class="container">
					<div class="row">
						<div class="mx-auto my-auto d-flex align-items-center flex-column">
							<div class="mb-3" style="width:800px;">탄소 배출량 및 권장 배출량을 예측하고 건축 시 예상되는 탄소 배출량과 건물 내 에너지 사용량을 바탕으로 예상 탄소배출량을 확인 하여 계산한 결과를 바탕으로 탄소배출량 저감을 유도 합니다.</div>
							<div style="width:800px;">사용 전력량을 통해 예상 탄소 배출량을 계산합니다. 건물의 면적에 따른 평균 탄소 배출량과 비교하여 탄소배출량 저감을 유도 합니다.</div>
						</div>
					</div>

				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/inc/footer.jsp"%>
	</div>
</body>
</html>
