<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>관리자 화면</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
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
</head>
<body>
	<div class="admin-container">
		<div>
			<form class="d-flex justify-content-center"
				action="${pageContext.request.contextPath }/adminView" method="GET">
				<select class="form-select w-25" name="searchOption">
					<option value="material">자재명</option>
					<option value="kg">kg당 탄소 배출량</option>
				</select> <input type="text" class="form-control w-25" name="searchWord">
				<button class="btn btn-primary" type="submit">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
						fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
					  <path
							d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
					</svg>
				</button>
			</form>
		</div>
		<div class="material-container">
			<div class="material-box">
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">자재 번호</th>
							<th scope="col">자재명</th>
							<th scope="col">kg당 탄소 배출량</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${keyMaterialList}" var="adminDTO">
							<tr>
								<td scope="row">${adminDTO.meNo}</td>
								<td scope="row">${adminDTO.meName}</td>
								<td scope="row"><input type="text"
									placeholder="${adminDTO.meEmission}"></td>
								<td><button class="btn">수정</button></td>
								<td><button class="btn btn-danger">삭제</button></td>
							</tr>
						</c:forEach>

						<c:if test="${keyMaterialList.size() == 0 }">
							<tr>
								<!-- 4개의 td를 하나의 td로 나타냄 -->
								<td colspan="4">검색 결과를 찾을 수 없습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div>
			<div>
				<form class="row" id="replyForm" action="${pageContext.request.contextPath }/adminView" method="GET">
					<%-- <input type="hidden" name="memId" value="${sessionScope.login.memId }=== admin">
					<input type="hidden" name="boardNo" value="${keyBoard.boardNo }"> --%>
					<div class="col-10">
						자재명:<input type="text"> kg당 탄소 배출량:<input type="text">
						<button>자재 추가하기</button>
					</div>
					<button id="materialBtn" class="btn btn-primary col-2" type="button">등록</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>