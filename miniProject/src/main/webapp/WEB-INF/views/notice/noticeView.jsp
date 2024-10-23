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
<title>Coding Bamboo</title>

<!-- header 부분 -->
<%@ include file="/WEB-INF/inc/header.jsp"%>

<style type="text/css">
	.Notice-container{
		width: 1200px;
	}
	
	.notice{
		background-color: #EEEEEE;
	}
	
	.notice-name{
		border-radius: 5px;
		background-color: red;
		color: white;
		padding: 5px;
	}
</style>
</head>
<body id="page-top">

	<!-- navigation 부분 -->
	<%@ include file="/WEB-INF/inc/top.jsp"%>

	<!-- Contact Section-->
	<section class="page-section" id="contact">
		<!-- 부트스트랩으로 padding-top 을 좀 주고자 한다. -->
		<div class="container pt-5">
			<!-- Contact Section Heading-->
			<div style="height: 200px;"></div>
			<h2
				class="page-section-heading text-center text-uppercase text-secondary mb-0">공지사항
				게시판</h2>
			<!-- Contact Section Form-->
			<div class="mt-5 ">
				<div class="Notice-container col-lg-8 col-xl-7">
					<!-- 게시글 그리기 -->
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col" colspan="1" class="text-center">글번호</th>
								<th scope="col" colspan="2" class="text-center">제목</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${keyNoticeList.size() != 0 }">
								<!-- 공지 -->
								<c:forEach items="${keyGetNoticeTopList }" var="noticeDTO">
									<c:if test="${noticeDTO.noTop == 1 }">
										<tr class="notice">
											<td class="text-center"><span class="notice-name">공지</span></td>
											<td colspan="2"><a class="text-decoration-none d-flex text-danger fw-bold"
												href="${pageContext.request.contextPath }/noticeDetailView?no=${noticeDTO.noNo }">
													${noticeDTO.noTitle } </a></td>
											<td class="text-center"><span>시스템 관리자</span></td>
										</tr>
									</c:if>
								</c:forEach>
								<c:forEach items="${keyNoticeList }" var="noticeDTO">
									<tr>
										<td colspan="1" class="text-center">${noticeDTO.noNo }</td>
										<td class="text-center" colspan="2"><a class="text-decoration-none d-flex"
											href="${pageContext.request.contextPath }/noticeDetailView?no=${noticeDTO.noNo }">
												${noticeDTO.noTitle } </a></td>
										<td colspan="1" class="text-center">시스템 관리자</td>
									</tr>
								</c:forEach>
							</c:if>
							
							<!-- keyNoticeList 의 사이즈가 0이면 검색 결과를 찾을 수 없습니다. -->
							<c:if test="${keyNoticeList.size() == 0 }">
								<c:forEach items="${keyGetNoticeTopList }" var="NoticeDTO">
									<c:if test="${NoticeDTO.noTop == 1 }">
										<tr class="notice">
											<td class="text-center"><span class="notice-name">공지</span></td>
											<td colspan="2"><a class="text-decoration-none d-flex text-danger fw-bold notice"
												href="${pageContext.request.contextPath }/noticeDetailView?no=${NoticeDTO.noNo }">
													${NoticeDTO.noTitle } </a></td>
											<td class="text-center"><span>시스템 관리자</span></td>
										</tr>
									</c:if>
								</c:forEach>
								<tr>
									<td colspan="4">검색 결과를 찾을 수 없습니다.</td>
								</tr>
							</c:if>

						</tbody>
					</table>

					<!-- 공지사항 작성 버튼 -->
					<c:if test="${sessionScope.login.userId == 'admin' }">
						<div class="d-flex justify-content-end">
							<button id="writeBtn" class="btn btn-primary">공지사항 등록</button>
						</div>
					</c:if>

					<!-- 페이지 부분 -->
					<!-- 페이징 -->
					<c:if test="${keyNoticeList.size() != 0 }">
						<div class="d-flex justify-content-center">
							<nav aria-label="Page navigation example">
								<ul class="pagination">
									<!-- 이전 페이지 -->
									<li
										class="page-item ${keySearch.firstPage == 1 ? 'disabled' : '' }">
										<c:if test="${searchWord == null }">
											<a class="page-link"
												href="${pageContext.request.contextPath }/noticeView?pageNo=${keySearch.firstPage - 1 }"
												aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
											</a>
										</c:if> <c:if test="${searchWord != null }">
											<a class="page-link"
												href="${pageContext.request.contextPath }/noticeView?pageNo=${keySearch.firstPage - 1 }&searchOption=${keySearch.searchOption}&searchWord=${keySearch.searchWord}"
												aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
											</a>
										</c:if>
									</li>

									<!-- 중간 페이지 번호 부분 -->
									<c:forEach begin="${keySearch.firstPage }"
										end="${keySearch.lastPage }" var="num">
										<li
											class="page-item ${keySearch.pageNo == num ? 'active' : ''}">
											<c:if test="${keySearch.searchWord == null }">
												<a class="page-link"
													href="${pageContext.request.contextPath }/noticeView?pageNo=${num }">${num }</a>
											</c:if> <c:if test="${keySearch.searchWord != null }">
												<a class="page-link"
													href="${pageContext.request.contextPath }/noticeView?pageNo=${num }&searchOption=${keySearch.searchOption}&searchWord=${keySearch.searchWord}">${num }</a>
											</c:if>
										</li>
									</c:forEach>

									<!-- 다음 페이지 -->
									<li
										class="page-item ${keySearch.lastPage == keySearch.finalPage ? 'disabled' : '' }">
										<c:if test="${keySearch.searchWord == null }">
											<a class="page-link"
												href="${pageContext.request.contextPath }/noticeView?pageNo=${keySearch.lastPage + 1 }"
												aria-label="Next"> <span aria-hidden="true">&raquo;</span>
											</a>
										</c:if> <c:if test="${keySearch.searchWord != null }">
											<a class="page-link"
												href="${pageContext.request.contextPath }/noticeView?pageNo=${keySearch.lastPage + 1 }&searchOption=${keySearch.searchOption}&searchWord=${keySearch.searchWord}"
												aria-label="Next"> <span aria-hidden="true">&raquo;</span>
											</a>
										</c:if>
									</li>

								</ul>
							</nav>
						</div>
					</c:if>

					<!-- 검색바 -->
					<div>
						<form class="d-flex justify-content-center"
							action="${pageContext.request.contextPath }/noticeView"
							method="GET">
							<select class="form-select w-25" name="searchOption">
								<option value="title">제목</option>
								<option value="content">내용</option>
								<option value="name">작성자</option>
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


				</div>
			</div>
		</div>
	</section>

	<!-- footer 부분 -->
	<%@ include file="/WEB-INF/inc/footer.jsp"%>

	<script type="text/javascript">
		document.getElementById('writeBtn').addEventListener('click',()=>{
			location.href = "${pageContext.request.contextPath}/noticeWriteView";
		});
	</script>
</body>
</html>

