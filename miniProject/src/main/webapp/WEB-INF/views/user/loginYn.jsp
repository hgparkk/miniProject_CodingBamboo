<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



	<script type="text/javascript">
	
		let v_userId = '${sessionScope.login.userId}';
		if(v_userId == null || v_userId === ""){
			alert("로그인에 실패하였습니다. 로그인 페이지로 이동합니다");
			window.location.href = '/miniproject/loginView';
		}else{
		
			window.location.href = '/miniproject';
		}
	
	</script>

</body>
	


</html>