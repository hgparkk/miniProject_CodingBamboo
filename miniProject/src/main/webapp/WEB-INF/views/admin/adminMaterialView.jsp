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
	
	.material-plus{
		border: 1px solid black;
		border-radius: 5px;
		margin-top: 5px;
		margin-bottom: 5px;
	}
}
</style>
<%@ include file="/WEB-INF/inc/header.jsp" %>
</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp" %>
	
	<div style="height: 200px;"></div>

	<div class="admin-container">
		<div>
			<!-- 검색바 -->
			<form class="d-flex justify-content-center mb-3"
				action="${pageContext.request.contextPath }/adminView" method="GET">
				<input type="text" class="form-control w-25" name="searchWord">
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
						<c:forEach items="${keyMaterialList}" var="materialDTO">
							<tr>
								<td scope="row">${materialDTO.meNo}</td>
								<td scope="row">${materialDTO.meName}</td>
								<td>
									<!-- 자재 수정 -->
									<form action="${pageContext.request.contextPath }/updateMaterial" method="POST">
										<input type="hidden" value="${materialDTO.meNo}" name="meNo">
										<input type="text" value="${materialDTO.meEmission}" name="meEmission">
										<button type="button" class="btn btn-frimary" onclick="f_update()">수정</button>
									</form>
								</td>
								<td>
									<!-- 자재 삭제 -->
									<form action="${pageContext.request.contextPath }/deleteMaterial" method="POST">
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
		<div class="material-plus">
			<!-- 자재 추가 -->
			<form class="row" id="replyForm" <%-- action="${pageContext.request.contextPath }/insertMaterial" --%> method="POST">
				<div class="col-10 align-items-center">
					자재명:<input class="me-5 ms-2" type="text" name="meName"> kg당 탄소 배출량:<input class="me-5 ms-2" type="text" name="meEmission">
					<button id="materialBtn" class="btn btn-primary col-2">등록</button>
				</div>
			</form>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/inc/footer.jsp"%>
	<script type="text/javascript">
	function f_update(){
		if(confirm("정말로 수정하시겠습니까?")){
			event.target.parentElement.submit();
		}
	};

	function f_del(){
		if(confirm("정말로 삭제하시겠습니까?")){
			event.target.parentElement.submit();
		}
	};
	
	v_materialBtn = document.getElemtById('materialBtn')
	v_meEmission = document.querySelector('.meEmission').value
	
	v_materialBtn.addEventListener('click', ()=>{
		if(!isNaN(v_meEmission)){
			confirm("탄소 배출량에는 숫자만 입력할 수 있습니다.")
		}
		event.target.submit();
	});
</script>
</body>

</html>