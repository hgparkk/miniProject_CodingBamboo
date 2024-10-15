<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>회원수정</title>
<!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${pageContext.request.contextPath }/css/styles.css" rel="stylesheet" />
    <!-- header 부분 -->

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

    <!-- navigation 부분 -->
    
    <!-- Contact Section-->
    <section class="page-section" id="contact">
    	<!-- 부트스트랩으로 padding-top 을 좀 주고자 한다. -->
        <div class="container pt-5">
            <!-- Contact Section Heading-->
            <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">회원수정</h2>
            <!-- Icon Divider-->
            <div class="divider-custom">
                <div class="divider-custom-line"></div>
                <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                <div class="divider-custom-line"></div>
            </div>
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
                        
                        <!-- Submit Button-->
                        
                        
                    </form>
                    
                    <form id="userEditForm" action="${pageContext.request.contextPath }/userEditDo" method="POST">
	                    <div class ="d-flex justify-content-center">
		                    <button class="btn btn-primary me-2" id="userEditBtn" type="button">수정</button>
	                    </div>
                    </form>
                    <form id="userDelForm" action="${pageContext.request.contextPath }/userDelDo" method="POST">
	                    <div class ="d-flex justify-content-center">
		                    <button class="btn btn-danger" id="userDelBtn" type="button">회원탈퇴</button>
	                    </div>
                    </form>
                    
					
                    
                </div>
            </div>
        </div>
    </section>
    
    <!-- footer 부분 -->

	<script type="text/javascript">
		
		let v_userId = '${sessionScope.login.userId}';
		
		document.getElementById("userDelBtn").addEventListener("click", ()=>{
			let v_input = prompt('정말로 삭제하시겠습니까? 삭제를 원하시면 아이디를 입력해주세요.')
			
			console.log(v_input);
			
			//입력받은 아이디가 로그인 중인 아이디와 일치하는지 확인
			if(v_input == v_userId){
				// action=/userDelDo인 form 태그의 submit 실행
				document.getElementById("userDelForm").submit();
				
			}
		});
		
		
		document.getElementById("userDelBtn").addEventListener("click", ()=>{
			
			
				// action=/memDelDo인 form 태그의 submit 실행
				document.getElementById("userDelForm").submit();
				
		});
		
		document.getElementById("userEditBtn").addEventListener("click", ()=>{
			confirm('정말로 수정하시겠습니까?');
			document.getElementById("userEditForm").submit();
		});
		
		
	
	</script>

		

</body>
</html>

