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
<!-- Favicon-->

<!-- header part -->
<%@ include file="/WEB-INF/inc/header.jsp"%>
</head>
<body id="page-top">
	<!-- top part -->
	<%@ include file="/WEB-INF/inc/top.jsp"%>

	<div style="height: 150px;"></div>

	<!-- Contact Section-->
	<section class="page-section" id="contact">
		<div class="container pt-5">
			<!-- Contact Section Heading-->
			<h2
				class="page-section-heading text-center text-uppercase text-black"
				style="margin-bottom: 100px;">회원가입</h2>
			<!-- Contact Section Form-->
			<div class="row justify-content-center">
				<div class="col-lg-8 col-xl-7">
					<form id="contactForm"
						action="${pageContext.request.contextPath }/registDo"
						method="POST">
						<!-- 아이디 input-->
						<div class="d-flex">
							<div style="width: 550px">
								<div class="form-floating mb-3">
									<!-- inputId의 값이 id=value 형태로 전송되어야 함 -->
									<!-- 키값으로 사용될 id를 name 속성에 넣어주어야 함 -->
									<input class="form-control" id="inputId" type="text"
										name="userId" placeholder="아이디" /> <label for="inputId">아이디</label>
								</div>

								<!-- 아이디 중복체크 -->
								<div class="mb-3">
									<span id="dupCheck"></span>
								</div>

								<!-- 비밀번호 input-->
								<div class="form-floating mb-3">
									<!-- inputPw의 값이 pw=value 형태로 전송되어야 함. pw를 name속성에 넣어주기 -->
									<input class="form-control" id="inputPw" type="password"
										name="userPw" placeholder="비밀번호" /> <label for="inputPw">비밀번호</label>
								</div>

								<!-- 비밀번호 확인 input -->
								<div class="form-floating mb-3">
									<input class="form-control" id="inputCheckPw" type="password"
										placeholder="비밀번호" /> <label for="userPw">비밀번호 확인</label>
								</div>

								<!-- 비밀번호 확인 button -->
								<div class="mb-3">
									<span id="pwCheck"></span>
								</div>

								<!-- 닉네임 input-->
								<div class="form-floating mb-3">
									<input class="form-control" id="inputName" type="text"
										name="userName" placeholder="이름" /> <label for="inputName">이름</label>
								</div>

								<!-- 이메일 input -->
								<div class="form-floating mb-3">
									<input class="form-control" id="inputEmail" type="email"
										name="userEmail" placeholder="이메일" /> <label for="inputEmail">이메일</label>
									<button class="btn btn-dark" type="button" id="sendEmailBtn"
										style="margin-top: 10px;">인증 메일 보내기</button>
								</div>

								<!-- 이메일 인증 input -->
								<div class="form-floating mb-3">
									<input class="form-control" id="emailCheckCode" type="text"
										name="emailCheckCode" placeholder="이메일 인증코드" required /> <label
										for="emailCheckCode">이메일 인증코드</label>
								</div>

								<!-- 관리자 여부 -->
								<div class="adminCheck">
									<input type="hidden" name="adminCheck" value="0">
								</div>

							</div>
							<div style="width: 200px;">
								<div class="mt-4">
									<button type="button" id="dupCheckBtn"
										class="ms-4 btn btn-dark">중복확인</button>
								</div>
							</div>
						</div>
						<!-- Submit Button-->
						<div class="d-flex justify-content-center">
							<button class="btn btn-dark btn-xl" id="submitButton"
								type="button">회원가입</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

	<div style="height: 150px;"></div>

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
		if(v_dupCheck && v_inputName.value && v_inputEmail.value && v_pwCheck){
			document.getElementById("contactForm").submit();
		}else{
			alert("모든 항목이 채워지지 않았습니다\n모든 항목을 채우고 다시 해주세요.");
			return;
		}
	})
		
		//비밀번호 확인
		// PW 중복 여부
		let v_pwCheck = false
		
		// PW 입력창
		const v_inputPw = document.getElementById("inputPw")
		const v_inputCheckPw = document.getElementById("inputCheckPw")
		
		// PW 확인 결과
		const v_pwCheckSpan = document.getElementById("pwCheck")
		
		// PW 입력할 때 PW 일치 풀기
		function pwCheckInvalid(){
			v_pwCheckSpan.classList.remove("text-success")
			v_pwCheckSpan.classList.remove("text-danger")
			v_pwCheck = false
			v_pwCheckSpan.innerHTML = ""
		}
		
		v_inputPw.addEventListener("input",pwCheckInvalid);
		v_inputCheckPw.addEventListener("input",pwCheckInvalid);
		
		// PW 일치 확인 함수
		document.getElementById("inputPw").addEventListener("change",()=>{
			if(document.getElementById("inputCheckPw").value != null && document.getElementById("inputCheckPw").value != ""){
				let v_inputPwValue = v_inputPw.value
				let v_inputCheckPwValue = v_inputCheckPw.value
				
				if(v_inputPwValue == v_inputCheckPwValue){
					v_pwCheck = true;
					v_pwCheckSpan.classList.add("text-success")
					v_pwCheckSpan.innerHTML = "비밀번호가 일치합니다."
				}else{
					v_pwCheck = false;
					v_pwCheckSpan.classList.add("text-danger")
					v_pwCheckSpan.innerHTML = "비밀번호가 일치하지 않습니다."
				}
			}
		})
		
		// PW 일치 확인 함수
		document.getElementById("inputCheckPw").addEventListener("change",()=>{
			let v_inputPwValue = v_inputPw.value
			let v_inputCheckPwValue = v_inputCheckPw.value
			
			if(v_inputPwValue == v_inputCheckPwValue){
				v_pwCheck = true;
				v_pwCheckSpan.classList.add("text-success")
				v_pwCheckSpan.innerHTML = "비밀번호가 일치합니다."
			}else{
				v_pwCheck = false;
				v_pwCheckSpan.classList.add("text-danger")
				v_pwCheckSpan.innerHTML = "비밀번호가 일치하지 않습니다."
			}
		})
	
		document.getElementById("sendEmailBtn").addEventListener("click", () => {
    let inputEmailValue = v_inputEmail.value;

    $.ajax({
        type: 'POST',
        url: '${pageContext.request.contextPath}/sendEmail',
        data: { "inputEmail": inputEmailValue },

        success: function(result) {
            console.log(result);
            alert("인증 메일이 성공적으로 발송되었습니다."); // 이메일 발송 알림
        },
        error: function(xhr, status, error) {
            console.error("Ajax 요청 실패:", error);
            alert("이메일 발송 중 오류가 발생했습니다. 나중에 다시 시도해 주세요.");
        }
    });
});
	</script>

</body>
</html>

