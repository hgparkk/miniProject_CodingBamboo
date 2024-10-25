<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Coding Bamboo</title>
<style type="text/css">
.admin-container {
	margin: auto;
	width: 1200px;
}

.material-container {
	border: 2px solid black;
	height: 800px;
	overflow-y: scroll;
}
</style>
<%@ include file="/WEB-INF/inc/header.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>

	<div style="height: 200px;"></div>

	<div class="admin-container">
		<div class="d-flex justify-content-between">
			<!-- 검색바 -->
			<form class="d-flex mb-3"
				action="${pageContext.request.contextPath }/adminMaterialView"
				method="GET">
				<input type="text" placeholder="자재 검색" style="width:300px;" class="form-control ms-4" name="searchWord">
				<button class="btn btn-secondary ms-2" type="submit">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
						fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
					  <path
							d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
					</svg>
				</button>
			</form>

			<!-- 자재 추가 버튼 -->
			<button type="button" class="btn btn-secondary me-5 mb-3"
				data-bs-toggle="modal" data-bs-target="#inputModal">자재 등록</button>
		</div>
		<div class="material-container mb-5">
			<div class="material-box">
				<table class="table table-hover mb-0">
					<thead>
						<tr>
							<th scope="col">자재 번호</th>
							<th scope="col">자재명</th>
							<th scope="col">kg당 탄소 배출량</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${keyMaterialList}" var="materialDTO">
							<tr>
								<td scope="row">${materialDTO.meNo}</td>
								<td scope="row">${materialDTO.meName}</td>
								<td>
									<!-- 자재 수정 -->
									<form
										action="${pageContext.request.contextPath }/updateMaterial"
										method="POST">
										<input type="hidden" value="${materialDTO.meNo}" name="meNo">
										<input type="number" step="0.00001"
											value="${materialDTO.meEmission}" name="meEmission">
										<button type="button" class="btn btn-frimary"
											onclick="f_update()">수정</button>
									</form>
								</td>
								<td>
									<!-- 자재 삭제 -->
									<form
										action="${pageContext.request.contextPath }/deleteMaterial"
										method="POST">
										<input type="hidden" value="${materialDTO.meNo}" name="meNo">
										<button type="button" class="btn btn-danger" onclick="f_del()">삭제</button>
									</form>
								</td>
							</tr>
						</c:forEach>

						<c:if test="${keyMaterialList.size() == 0 }">
							<tr>
								<!-- 4개의 td를 하나의 td로 나타냄 -->
								<td colspan="3">검색 결과를 찾을 수 없습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>

		<!-- 자재 추가 모달 -->
		<div class="modal fade" id="inputModal" tabindex="-1"
			aria-labelledby="inputModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="inputModalLabel">자재 등록</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form class="row" id="inputMaterialForm"
							action="${pageContext.request.contextPath }/insertMaterial"
							method="POST">
							<table>
								<tbody>
									<tr>
										<td><span class="ms-3">자재명</span></td>
										<td><input type="text" name="meName"></td>
									</tr>
									<tr>
										<td><span class="ms-3">kg당 탄소 배출량</span></td>
										<td><input type="number" step="0.00001" name="meEmission"></td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn text-white"
							data-bs-dismiss="modal" style="background-color:#6C757D">취소</button>
						<button id="inputMaterial" type="button" class="btn btn-secondary">자재
							등록</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/inc/footer.jsp"%>
	<script type="text/javascript">
	
		// 자재 수정
		function f_update() {
			if (confirm("정말로 수정하시겠습니까?")) {
				event.target.parentElement.submit();
			}
		};

		// 자재 삭제
		function f_del() {
			if (confirm("정말로 삭제하시겠습니까?")) {
				event.target.parentElement.submit();
			}
		};
		
		document.getElementById("inputMaterial").addEventListener("click",()=>{
			document.getElementById("inputMaterialForm").submit();
		})
	</script>
</body>

</html>