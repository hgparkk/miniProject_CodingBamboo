<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>CodingBamboo</title>
        
        <%@ include file="/WEB-INF/inc/header.jsp" %>

</head>
<body>
        <%-- <%@ include file="/WEB-INF/inc/top.jsp" %> --%>
    <div class="d-flex justify-content-center">
        <select id="largefield" onchange="setMiddleField()">
            <option value="에너지">에너지</option>
            <option value="산업공정">산업공정</option>
            <option value="농업">농업</option>
            <option value="LULUCF">LULUCF</option>
            <option value="폐기물">폐기물</option>
            <option value="별도항목">별도항목</option>
        </select>

        <select id="middlefield" onchange="setSmallfield()">
            <option>중분류</option>
        </select>
        
        <select id="smallfield" onchange="setPart()">
            <option>소분류</option>
        </select>
        
        <select id="part">
            <option>분야</option>
        </select>
        </div>
        
        <div class="d-flex justify-content-around">
        <div>
        <select id="yearFrom">
            <option value="1990">1990</option>
            <option value="1991">1991</option>
            <option value="1992">1992</option>
            <option value="1993">1993</option>
            <option value="1994">1994</option>
            <option value="1995">1995</option>
            <option value="1996">1996</option>
            <option value="1997">1997</option>
            <option value="1998">1998</option>
            <option value="1999">1999</option>
            <option value="2000">2000</option>
            <option value="2001">2001</option>
            <option value="2002">2002</option>
            <option value="2003">2003</option>
            <option value="2004">2004</option>
            <option value="2005">2005</option>
            <option value="2006">2006</option>
            <option value="2007">2007</option>
            <option value="2008">2008</option>
            <option value="2009">2009</option>
            <option value="2010">2010</option>
            <option value="2011">2011</option>
            <option value="2012">2012</option>
            <option value="2013">2013</option>
            <option value="2014">2014</option>
            <option value="2015">2015</option>
            <option value="2016">2016</option>
            <option value="2017">2017</option>
            <option value="2018">2018</option>
            <option value="2019">2019</option>
            <option value="2020">2020</option>
            <option value="2021">2021</option>
        </select>

		<span>~</span>

        <select id="yearTo">
            <option value="1990">1990</option>
            <option value="1991">1991</option>
            <option value="1992">1992</option>
            <option value="1993">1993</option>
            <option value="1994">1994</option>
            <option value="1995">1995</option>
            <option value="1996">1996</option>
            <option value="1997">1997</option>
            <option value="1998">1998</option>
            <option value="1999">1999</option>
            <option value="2000">2000</option>
            <option value="2001">2001</option>
            <option value="2002">2002</option>
            <option value="2003">2003</option>
            <option value="2004">2004</option>
            <option value="2005">2005</option>
            <option value="2006">2006</option>
            <option value="2007">2007</option>
            <option value="2008">2008</option>
            <option value="2009">2009</option>
            <option value="2010">2010</option>
            <option value="2011">2011</option>
            <option value="2012">2012</option>
            <option value="2013">2013</option>
            <option value="2014">2014</option>
            <option value="2015">2015</option>
            <option value="2016">2016</option>
            <option value="2017">2017</option>
            <option value="2018">2018</option>
            <option value="2019">2019</option>
            <option value="2020">2020</option>
            <option value="2021">2021</option>
        </select>
        </div>
        
        <span></span>
        
        <button type="button">검색</button>
        </div>
    </form>
    
    <div id="searchResult"></div>
    
    <script type="text/javascript">
    	
    let url = "${pageContext.request.contextPath }"
    
   	function setMiddleField(){
   		let largefield = document.getElementById("largefield").value;
   		
   		let path = "/getMiddleField"
   		
   		$.ajax({
   			type:'POST',
   			url: url + path,
   			data: { "peLargeField" : largefield},
   			success: function(result){
   				console.log(result)
   				document.getElementById("middlefield").innerHTML = ""
   				for(let i = 0; i< result.length; i++){
   					document.getElementById("middlefield").innerHTML += '<option value="' +result[i] +'">'
   					+result[i] + '</option>'
   				}
   			}
   		})
   	}
   	
   	function setSmallfield(){
   		let largefield = document.getElementById("largefield").value;
   		let middlefield = document.getElementById("middlefield").value;
   		
   		let path = "/getSmallField"
   	   		
   	   		$.ajax({
   	   			type:'POST',
   	   			url: url + path,
   	   			data: { "peLargeField" : largefield , "peMiddelField" : middlefield },
   	   			success: function(result){
   	   				console.log(result)
   	   				document.getElementById("smallfield").innerHTML = ""
   	   				for(let i = 0; i< result.length; i++){
   	   					document.getElementById("smallfield").innerHTML += '<option value="' +result[i] +'">'
   	   					+result[i] + '</option>'
   	   				}
   	   			}
   	   		})
   	}
   	
   	function setPart(){
   		document.getElementById("largefield");
   		document.getElementById("middlefield");
   		document.getElementById("smallfield");
   	}
    	
    	
    </script>
</body>
</html>