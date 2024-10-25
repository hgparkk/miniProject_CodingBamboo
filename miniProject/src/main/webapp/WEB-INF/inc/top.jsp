<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Navigation-->
<nav class="navbar navbar-expand-lg text-uppercase bg-black fixed-top flex-column" id="mainNav">
	<div class="container">
		<a class="navbar-brand" href="${pageContext.request.contextPath }/">Coding Bamboo</a>
		<c:if test="${sessionScope.login == null}">
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="${pageContext.request.contextPath }/loginView">로그인</a></li>
				</ul>
			</div>
		</c:if>
		<c:if test="${sessionScope.login != null}">
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="${pageContext.request.contextPath }/userEditView">${sessionScope.login.userName} 님</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="${pageContext.request.contextPath }/logoutDo">로그아웃</a></li>
				</ul>
			</div>
		</c:if>
	</div>
	<c:if test="${sessionScope.login == null or sessionScope.login.userId != 'admin'}">
		<div class="d-flex justify-content-center">
			<ul class="navbar-nav ms-auto">
				<li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="${pageContext.request.contextPath }/">페이지 소개</a></li>
				<li class="nav-item dropdown"><a class="nav-link dropdown-toggle py-3 px-0 px-lg-3 rounded" role="button" data-bs-toggle="dropdown" aria-expanded="false"> 계산 시스템 </a>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="${pageContext.request.contextPath }/materialCalculation">자재별 탄소 배출량 계산</a></li>
						<li><a class="dropdown-item" href="${pageContext.request.contextPath }/electricCalculation">전력 사용량 대비 배출량 계산</a></li>
					</ul></li>
				<li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="${pageContext.request.contextPath }/partView">탄소배출량 현황</a></li>
				<li class="nav-item dropdown"><a class="nav-link dropdown-toggle py-3 px-0 px-lg-3 rounded" role="button" data-bs-toggle="dropdown" aria-expanded="false"> 게시판 </a>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="${pageContext.request.contextPath }/noticeView">공지사항</a></li>
						<li><a class="dropdown-item" href="${pageContext.request.contextPath }/boardView">질문 게시판</a></li>
					</ul></li>
			</ul>
		</div>
	</c:if>
	<c:if test="${sessionScope.login != null and sessionScope.login.userId == 'admin'}">
		<div class="d-flex justify-content-center">
			<ul class="navbar-nav ms-auto">
				<li class="nav-item dropdown"><a class="nav-link dropdown-toggle py-3 px-0 px-lg-3 rounded" role="button" data-bs-toggle="dropdown" aria-expanded="false"> 계산 시스템 관리 </a>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="${pageContext.request.contextPath }/adminMaterialView">자재 관리</a></li>
						<li><a class="dropdown-item" href="${pageContext.request.contextPath }/adminCoefficientView">계수 관리</a></li>
					</ul></li>
				<li class="nav-item dropdown"><a class="nav-link dropdown-toggle py-3 px-0 px-lg-3 rounded" role="button" data-bs-toggle="dropdown" aria-expanded="false"> 게시판 </a>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="${pageContext.request.contextPath }/noticeView">공지사항</a></li>
						<li><a class="dropdown-item" href="${pageContext.request.contextPath }/boardView">질문 게시판</a></li>
					</ul></li>
			</ul>
		</div>
	</c:if>
</nav>