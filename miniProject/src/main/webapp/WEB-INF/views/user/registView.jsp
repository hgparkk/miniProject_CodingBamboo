<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>회원가입</title>
<!-- Favicon-->
       
    <!-- header part -->
	<%@ include file="/WEB-INF/inc/header.jsp"%>
</head>
<body id="page-top">
	<!-- top part -->
    <%@ include file="/WEB-INF/inc/top.jsp"%>
    
    <!-- Contact Section-->
    <section class="page-section" id="contact">
        <div class="container pt-5">
            <!-- Contact Section Heading-->
            <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">회원가입</h2>
            <!-- Icon Divider-->
            <div class="divider-custom">
                <div class="divider-custom-line"></div>
                <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                <div class="divider-custom-line"></div>
            </div>
            <!-- Contact Section Form-->
            <div class="row justify-content-center">
                <div class="col-lg-8 col-xl-7">
                    <form id="contactForm" action="${pageContext.request.contextPath }/registDo" method="POST">
                        <!-- 아이디 input-->
                        <div class="form-floating mb-3">
                        	<!-- inputId의 값이 id=value 형태로 전송되어야 함 -->
                        	<!-- 키값으로 사용될 id를 name 속성에 넣어주어야 함 -->
                            <input class="form-control" id="inputId" type="text" name="userId" placeholder="아이디"  />	
                            <label for="userId">아이디</label>
                        </div>
                        
                        <!-- 아이디 중복체크 -->
                        <div class="mb-3">
                        	<button type="button" id="dupCheckBtn" class="btn btn-secondary">중복확인</button>
                        	<span class="ms-3" id="dupCheck"></span>
                        </div>

                        <!-- 비밀번호 input-->
                        <div class="form-floating mb-3">
                        	<!-- inputPw의 값이 pw=value 형태로 전송되어야 함. pw를 name속성에 넣어주기 -->
                            <input class="form-control" id="inputPw" type="password" name="userPw" placeholder="비밀번호" />
                            <label for="userPw">비밀번호</label>
                        </div>

                        <!-- 닉네임 input-->
                        <div class="form-floating mb-3">
                            <input class="form-control" id="inputName" type="text" name="userName" placeholder="이름"  />	
                            <label for="inputName">이름</label>
                        </div>     
                        
                        <!-- 이메일 input -->
                        <div class="form-floating mb-3">
                            <input class="form-control" id="inputEmail" type="email" name="userEmail" placeholder="이메일"  />	
                            <label for="inputEmail">이메일</label>
                        </div> 
                        
                        <!-- 관리자 여부 -->
						<div class="adminCheck">
							<input class="form-check-input" type="hidden" name="adminCheck" value = "0"> 
						</div> 
						  
                        
                        <!-- Submit Button-->
                        <button class="btn btn-primary btn-xl" id="submitButton" type="button">회원가입</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
    
    <!-- footer part -->
	<%@ include file="/WEB-INF/inc/footer.jsp"%>

	<script type="text/javascript">
		const v_inputName = document.getElementById("inputName")
		const v_inputEmail = document.getElementById("inputEmail")
		//아이디 중복 여부
		let v_dupCheck = false
		
		//
		const v_inputId = document.getElementById("inputId")
		
		// 가입버튼
		const v_registBtn = document.getElementById("submitButton")
		
		//Id중복 여부 결과
		const v_dupCheckSpan = document.getElementById("dupCheck")
		
		// ID 체크 쿼리 Url
		const v_dupCheckUrl = "<c:url value='/idDupCheck' />"
		
			// ID 입력할 때 중복 체크 풀기
			v_inputId.addEventListener("input",()=>{
				v_dupCheckSpan.classList.remove("text-success")
				v_dupCheckSpan.classList.remove("text-danger")
				v_dupCheck = false
				v_dupCheckSpan.innerHTML = ""

			})
			
			// ID 중복 체크 확인 함수
		document.getElementById("dupCheckBtn").addEventListener("click",()=>{
			let v_inputIdValue = v_inputId.value
			 
			$.ajax({
				type:'POST',
				url: v_dupCheckUrl,
				data: { "inputId": v_inputIdValue},
				
				success: function(result){
					if(result){
						v_dupCheckSpan.classList.add("text-danger")
						v_dupCheckSpan.innerHTML = "중복되는 아이디가 존재합니다."
						v_dupCheck = false

					}else{
						v_dupCheckSpan.classList.add("text-success")
						v_dupCheckSpan.innerHTML = "사용가능한 아이디 입니다."
						v_dupCheck = true
					}
				}
			})
		})
		
		// 회원 가입
	v_registBtn.addEventListener("click",()=>{
		if(v_dupCheck && v_inputName.value && v_inputEmail.value){
			document.getElementById("contactForm").submit();
		}else{
			alert("모든 항목이 채워지지 않았습니다\n모든 항목을 채우고 다시 해주세요.");
			return;
		}
	})
		
		
	</script>

</body>
</html>

