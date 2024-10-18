<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coding Bamboo</title>
<%@ include file="/WEB-INF/inc/header.jsp"%></head>
<body>

	<%@ include file="/WEB-INF/inc/top.jsp"%>

	<div style="height: 200px;"></div>

	<div class="mx-auto" style="width: 500px;">
		<div class="border rounded-4 d-flex flex-column align-items-center mt-5">
			<div class="mt-3">사용 전력량 대비 탄소 배출량 계산</div>
			<div class="mt-3">
				<span>사용 전력량(소비 기준)</span> <input id="inputElectric" type="number" onchange="calcMyEmission()"> <span>kWh</span>
			</div>
			<div class="mt-5">면적당 평균 전력량과 탄소배출량 계산</div>
			<div class="mt-3 mb-3">
				<span>면적</span> <input id="inputArea" type="number" onchange="calcAvgEmission()"> <span>㎡</span>
			</div>
		</div>
		<div class="d-flex flex-column align-items-center mt-5">
			<div class="mt-3">
				<span>면적당 평균 전력 사용량</span> <span id="avgElectricUsage">0</span><span> kWh/㎡</span>
			</div>
			<div class="mt-3">
				<span>평균 전력 사용량에 따른 예상 탄소배출량</span> <span id="avgElectricEmission">0</span><span> kgCO2</span>
			</div>
			<div class="mt-3">
				<span>사용 전력 사용량에 따른 예상 탄소 배출량</span> <span id="myElectricEmission">0</span><span> kgCO2</span>
			</div>
			<div class="mt-3 mb-3">
				<span>평균과</span> <span id="calDiff">0</span><span> 차이</span>
			</div>
		</div>
	</div>
	
	<div style="height: 200px;"></div>

	<%@ include file="/WEB-INF/inc/footer.jsp"%>

	<script>
		// 소수점 다섯째 자리까지 보이게 하기
		function showFiveDecimalPlaces(number) {
			return number.toString().match(/^-?\d+(?:\.\d{0,5})?/)[0];
		}

		function calcMyEmission() {

			// 입력한 전력량 불러오기
			let myElectric = document.getElementById("inputElectric").value

			// 입력한 전력량을 탄소 배출량 계산하기
			let myEmission = myElectric * ${electricEmissionCoefficient}

			// 탄소 배출량 보이기
			document.getElementById("myElectricEmission").innerHTML = showFiveDecimalPlaces(myEmission)

			// 평균 탄소 배출량과 차이 계산
			let avgEmission = document.getElementById("avgElectricEmission").innerHTML
			document.getElementById("calDiff").innerHTML = showFiveDecimalPlaces(avgEmission
					- myEmission)
		}

		function calcAvgEmission() {

			// 입력한 면적 불러오기
			let myArea = document.getElementById("inputArea").value

			// 입력한 면적에 따른 평균 전력 사용량 계산
			let avgElectric = myArea * ${avgAreaElectricCoefficient}

			// 평균 전력량 보이기
			document.getElementById("avgElectricUsage").innerHTML = avgElectric

			// 평균 전력량으로 탄소 배출량 계산
			let avgEmission = avgElectric * ${electricEmissionCoefficient}

			// 예상 탄소 배출량 계산
			document.getElementById("avgElectricEmission").innerHTML = showFiveDecimalPlaces(avgEmission)

			// 내 전력량과 차이 계산
			myEmission = document.getElementById("myElectricEmission").innerHTML
			document.getElementById("calDiff").innerHTML = showFiveDecimalPlaces(avgEmission
					- myEmission)
		}
	</script>
</body>
</html>