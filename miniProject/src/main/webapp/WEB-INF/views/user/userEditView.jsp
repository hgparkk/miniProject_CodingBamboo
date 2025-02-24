<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Coding Bamboo</title>
<!-- Favicon-->

    <!-- header 부분 -->
	<%@ include file="/WEB-INF/inc/header.jsp"%>
	<style type="text/css">
		.profile-box{
			width:300px;
			height:300px;
			border-radius:150px;
			overflow:"hidden";
			display: flex;
			justify-content:center;
			align-items:center;
			
			
		}
		.profile-img{
			
			cursor: pointer;
		}
	</style>
</head>
<body id="page-top">

    <!-- top part -->
    <%@ include file="/WEB-INF/inc/top.jsp"%>
    
    	<div style="height: 150px;"></div>
    
    <!-- Contact Section-->
    <section class="page-section" id="contact">
    	<!-- 부트스트랩으로 padding-top 을 좀 주고자 한다. -->
        <div class="container pt-5">
            <!-- Contact Section Heading-->
            <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">회원수정</h2>
            <!-- Contact Section Form-->
            <div class="row justify-content-center">
                <div class="col-lg-8 col-xl-7">
                <!-- 프로필이미지 -->
                    <form id="userEditForm" action="${pageContext.request.contextPath }/userEditDo" method="POST">
                        <!-- 아이디 input-->
                        <div class="form-floating mb-3">
                            <input class="form-control" id="inputId" type="text" name="userId" value="${sessionScope.login.userId }" readonly/>	
                            <label for="inputId">아이디</label>
                        </div>

                        <!-- 비밀번호 input-->
                        <div class="form-floating mb-3">
                        	<!-- inputPw의 값이 pw=value 형태로 전송되어야 함. pw를 name속성에 넣어주기 -->
                            <input class="form-control" id="inputPw" type="password" name="userPw" />
                            <label for="inputPw">비밀번호</label>
                        </div>
                        
                        <!-- 비밀번호 확인 input -->
					    <div class="form-floating mb-3">
                            <input class="form-control" id="inputCheckPw" type="password"/>
                            <label for="inputCheckPw">비밀번호 확인</label>
                        </div>
                        
                        <!-- 비밀번호 확인 button -->
						<div class="mb-3">
							<button type="button" id="pwChecKBtn" class="btn btn-secondary">비밀번호확인</button>
							<span class="ms-3" id="pwCheck"></span>
						</div>
                        
                        <!-- 닉네임 input-->
                        <div class="form-floating mb-3">
                            <input class="form-control" id="inputName" type="text" name="userName" value="${sessionScope.login.userName }" />	
                            <label for="inputName">닉네임</label>
                        </div>     
                        
                        <!-- email input -->                                                   
                        <div class="form-floating mb-3">
                            <input class="form-control" id="inputEmail" type="email" name="userEmail" value="${sessionScope.login.userEmail }"/>	
                            <label for="inputEmail">Email</label>
                        </div> 
                        
                        <!--관리자 확인 input -->
                        <div class="form-floating mb-3">
                            <input class="form-control" id="inputIsmaster" type="hidden" name="userIsmaster" value="${sessionScope.login.userIsmaster }" />	
                        </div>     
                        
                        
                        
                        
                    </form>
				<div class="form-floating mb-3">
				    <form id="userEditForm" action="${pageContext.request.contextPath }/userEditDo" method="POST">
				        <div class="d-flex flex-column align-items-center">
				            <!-- 수정 버튼 -->
				            <button class="btn btn-primary mb-3" id="userEditBtn" type="button" style="width: 70%;">수정</button>
				        </div>
				    </form>
				    <form id="userDelForm" action="${pageContext.request.contextPath }/userDelDo" method="POST">
				        <div class="d-flex flex-column align-items-center">
				            <!-- 회원탈퇴 버튼 -->
				            <button class="btn" id="userDelBtn" type="button" style="background: none; border: none; text-decoration: none; cursor: pointer; color: black;">회원탈퇴</button>
				        </div>
				    </form>
				    
				    
				    
				</div>
                    
                    
					
                    
                </div>
            </div>
        </div>
    </section>
    
    	<div style="height: 150px;"></div>
    
    <!-- footer 부분 -->
	<%@ include file="/WEB-INF/inc/footer.jsp"%>
	<script type="text/javascript">
		
	let v_userId = '${sessionScope.login.userId}';

	document.getElementById("userEditBtn").addEventListener("click", () => {
	    // 비밀번호 일치 확인
	    if (!v_pwCheck) {
	        alert("비밀번호가 일치하지 않습니다.");
	        return;
	    }
	    
	    // 비밀번호 확인 후 수정 폼 제출
	    let result = confirm("회원 정보를 수정하시겠습니까?");
	    if (result) {
	        // action=/userEditDo인 form 태그의 submit 실행
	        document.getElementById("userEditForm").submit();
	    } else {
	        alert("수정이 취소되었습니다.");
	    }
	});
	
	document.getElementById("userDelBtn").addEventListener("click", () => {
	    // 사용자에게 아이디 입력 요청
	    let v_input = prompt('정말로 삭제하시겠습니까? 삭제를 원하시면 아이디를 입력해주세요.');
	    
	    // 입력된 값이 null (취소 버튼 클릭)인지 확인
	    if (v_input === null) {
	        alert('회원 탈퇴가 취소되었습니다.'); // 취소할 경우 알림
	        return;
	    }

	    // 입력받은 아이디가 로그인 중인 아이디와 일치하는지 확인
	    if (v_input.trim() === v_userId.trim()) {
	        // 삭제 확인
	        let result = confirm('정말로 삭제하시겠습니까?');
	        if (result) {
	            // action=/userDelDo인 form 태그의 submit 실행
	            document.getElementById("userDelForm").submit();
	        } else {
	            alert('회원 탈퇴가 취소되었습니다.'); // 확인을 누르지 않았을 때 알림
	        }
	    } else {
	        alert('입력하신 아이디가 일치하지 않습니다.'); // 아이디가 틀릴 경우 알림
	    }
	});
		
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
		document.getElementById("pwChecKBtn").addEventListener("click",()=>{
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
		
		
		
		
	
	</script>

		

</body>
</html>

