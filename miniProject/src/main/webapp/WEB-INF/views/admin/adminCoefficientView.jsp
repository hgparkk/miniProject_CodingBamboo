<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Coding Bamboo</title>
<%@ include file="/WEB-INF/inc/header.jsp"%>
</head>
<body>

	<%@ include file="/WEB-INF/inc/top.jsp"%>

	<div style="height: 200px;"></div>

	<div class="mx-auto" style="width: 800px;">
		<div class="border rounded-4 d-flex flex-column mt-5 p-5">
			<table>
				<tbody>
					<c:forEach items="${coefficientList}" var="coefficient">
						<tr>
							<c:if test="${coefficient.aecNo == 1}">
								<td><span>주거용 건물의 건물 면적(㎡)당 탄소배출량 계수</span></td>
								<td><input id="residential" type="number" value="${coefficient.aecCoefficient}"> <span>kgCO2eq</span></td>
								<td><button class="mt-2 mb-2" onclick="updateCoefficient(1)">수정</button></td>
							</c:if>
							<c:if test="${coefficient.aecNo == 2}">
								<td><span>상업용 건물의 건물 면적(㎡)당 탄소배출량 계수</span></td>
								<td><input id="commercial" type="number" value="${coefficient.aecCoefficient}"> <span>kgCO2eq</span></td>
								<td><button class="mt-2 mb-2" onclick="updateCoefficient(2)">수정</button></td>
							</c:if>
							<c:if test="${coefficient.aecNo == 3}">
								<td><span>공공용 건물의 건물 면적(㎡)당 탄소배출량 계수</span></td>
								<td><input id="public" type="number" value="${coefficient.aecCoefficient}"> <span>kgCO2eq</span></td>
								<td><button class="mt-2 mb-2" onclick="updateCoefficient(3)">수정</button></td>
							</c:if>
							<c:if test="${coefficient.aecNo == 4}">
								<td><span>면적(㎡)당 평균 전력 사용량 계수</span></td>
								<td><input id="avgAreaElectric" type="number" value="${coefficient.aecCoefficient}"> <span>kWh</span></td>
								<td><button class="mt-2 mb-2" onclick="updateCoefficient(4)">수정</button></td>
							</c:if>
							<c:if test="${coefficient.aecNo == 5}">
								<td><span>전력 사용량(kWh) 대비 탄소 배출량 계수</span></td>
								<td><input id="electricEmission" type="number" value="${coefficient.aecCoefficient}"><span>kgCO2eq</span></td>
								<td><button class="mt-2 mb-2" onclick="updateCoefficient(5)">수정</button></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<form id="updateCoefficientForm" action="<c:url value='/updateCoefficient' />">
		<input id="aecNoForForm" type="hidden" name="aecNo">
		<input id="aecCoefficientForForm" type="hidden" name="aecCoefficient">
	</form>

	<div style="height: 200px;"></div>

	<script>
		function updateCoefficient(aecNo){
			
			document.getElementById("aecNoForForm").value = aecNo
			
			if(aecNo == 1){
				document.getElementById("aecCoefficientForForm").value = document.getElementById("residential").value
			}else if(aecNo == 2){
				document.getElementById("aecCoefficientForForm").value = document.getElementById("commercial").value
			}else if(aecNo == 3){
				document.getElementById("aecCoefficientForForm").value = document.getElementById("public").value
			}else if(aecNo == 4){
				document.getElementById("aecCoefficientForForm").value = document.getElementById("avgAreaElectric").value
			}else if(aecNo == 5){
				document.getElementById("aecCoefficientForForm").value = document.getElementById("electricEmission").value
			}
			
			document.getElementById("updateCoefficientForm").submit();
		}
	</script>

	<%@ include file="/WEB-INF/inc/footer.jsp"%>
</body>
</html>