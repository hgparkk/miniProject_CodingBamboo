<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>CodingBamboo</title>

<%@ include file="/WEB-INF/inc/header.jsp"%>

</head>
<body>
	<%@ include file="/WEB-INF/inc/top.jsp"%>

	<div style="height: 200px;"></div>

	<div class="d-flex justify-content-center">
		<select id="largefield" onchange="setMiddleField()">
			<option value="대분류">대분류</option>
			<option value="에너지">에너지</option>
			<option value="산업공정">산업공정</option>
			<option value="농업">농업</option>
			<option value="LULUCF">LULUCF</option>
			<option value="폐기물">폐기물</option>
			<option value="별도항목">별도항목</option>
		</select> <select id="middlefield" onchange="setSmallfield()">
			<option>중분류</option>
		</select> <select id="smallfield" onchange="setPart()">
			<option>소분류</option>
		</select> <select id="part">
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
			</select> <span>~</span> <select id="yearTo">
			</select>
		</div>

		<span></span>

		<button id="resultSearchButton" type="button">검색</button>
	</div>
	<div id="searchResult" class="overflow-scroll" style="height: 700px;"></div>

	<%@ include file="/WEB-INF/inc/footer.jsp"%>

	<script type="text/javascript">
	
	setYearTo()
	
	document.getElementById("yearFrom").addEventListener("change",setYearTo)
	
	function setYearTo(){
		let yearFrom = parseInt(document.getElementById("yearFrom").value)
		
		let yearToString = ""
		
		for(let i = yearFrom; i <= 2021; i++){
			yearToString += '<option value="' + i + '">' + i + '</option>'
		}
		
		document.getElementById("yearTo").innerHTML = yearToString
	}
    	
    let url = "${pageContext.request.contextPath }"
    
   	function setMiddleField(){
   		let largefield = document.getElementById("largefield").value;
   		
   		let path = "/getMiddleField"
   		
   		$.ajax({
   			type:'POST',
   			url: url + path,
   			data: { "peLargeField" : largefield},
   			success: function(result){
   				document.getElementById("middlefield").innerHTML = "<option>중분류</option>"
   				document.getElementById("smallfield").innerHTML = "<option>소분류</option>"
   				document.getElementById("part").innerHTML = "<option>분야</option>"
   				for(let i = 0; i< result.length; i++){
   					document.getElementById("middlefield").innerHTML += '<option value="' +result[i] +'">'
   					+result[i] + '</option>'
   				}
   				if(result.length == 1){
   					setSmallfield()
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
	   		data: { "peLargeField" : largefield , "peMiddleField" : middlefield },
	   		success: function(result){
	   			document.getElementById("smallfield").innerHTML = "<option>소분류</option>"
	   			document.getElementById("part").innerHTML = "<option>분야</option>"
	   			for(let i = 0; i< result.length; i++){
	   				document.getElementById("smallfield").innerHTML += '<option value="' +result[i] +'">'
	   				+result[i] + '</option>'
	   			}
	   			if(result.length == 1){
	   				setPart()
   				}
	   		}
	   	})
   	}
   	
   	function setPart(){
   		let largefield =  document.getElementById("largefield").value;
   		let middlefield = document.getElementById("middlefield").value;
   		let smallfield = document.getElementById("smallfield").value;
   		
		let path = "/getPart"
   	   		
   	   	$.ajax({
   	   		type:'POST',
   	   		url: url + path,
   	   		data: { "peLargeField" : largefield , "peMiddleField" : middlefield , "peSmallField" : smallfield },
   	   		success: function(result){
   	   			document.getElementById("part").innerHTML = "<option>분야</option>"
   	   			for(let i = 0; i< result.length; i++){
   	   				document.getElementById("part").innerHTML += '<option value="' +result[i] +'">'
   	   				+result[i] + '</option>'
   	   			}
   	   		}
   	   	})
   	}
   	
   	document.getElementById("resultSearchButton").addEventListener("click",()=>{
   		let largefield =  document.getElementById("largefield").value;
   		let middlefield = document.getElementById("middlefield").value;
   		let smallfield = document.getElementById("smallfield").value;
   		let part = document.getElementById("part").value;
   		let yearFrom = parseInt(document.getElementById("yearFrom").value);
   		let yearTo = parseInt(document.getElementById("yearTo").value);
   		
   		if(largefield == "대분류"){
   			largefield = ""
   		}
   		
   		if(middlefield == "중분류"){
   			middlefield = ""
   		}
   		
   		if(smallfield == "소분류"){
   			smallfield = ""
   		}
   		
   		if(part == "분야"){
   			part = ""
   		}
   		
   		let path = "/getResult"
   		
   		yearLength = yearTo - yearFrom + 1
   	   		
   	   	$.ajax({
   	   	   type:'POST',
   	   	   	url: url + path,
   	   	   	data: { "peLargeField" : largefield , "peMiddleField" : middlefield , "peSmallField" : smallfield ,
   	   	   		"pePart" : part , "yearFrom" : yearFrom, "yearTo" : yearTo},
   	   	   	success: function(result){
   	   	  		document.getElementById("searchResult").innerHTML = ""
   	   	  		let makeTable = '<table class="table table-bordered" style="table-layout: fixed; width:' + (1200 + yearLength*120) +'px;">'
   	   	  		+ '<thead>'
   	   	  		+ '<th scope="col" style="width:100px;">대분류</th>'
   	   	  		+ '<th scope="col" style="width:300px;">중분류</th>'
				+ '<th scope="col" style="width:400px;">소분류</th>'
				+ '<th scope="col" style="width:400px;">분야</th>'
				for(let j = 0; j < yearLength; j++){
					makeTable += '<th style="width:120px;" scope="col">'+(yearFrom+j)+'</th>'
	   	  		}
				+ '</thead>' + '<tbody>'
   	   	  		for(let i = 0; i < result.length; i = i+yearLength){
   	   	  			let oneLine = '<tr><td>'+ result[i]["peLargeField"] +'</td>'
   	   	  			+ '<td>' + result[i]["peMiddleField"] + '</td>'
   	   	  			+ '<td>' + result[i]["peSmallField"] + '</td>'
   	   	  			+ '<td>' + result[i]["pePart"] + '</td>'
   	   	  			for(let j = 0; j < yearLength; j++){
   	   	  				oneLine += '<td>' + result[i+j]["peEmission"] + '</td>'
   	   	  			}
   	   	  			oneLine += '</tr>'
   	   	  		    makeTable += oneLine
   	   	  		}
				makeTable += '</tbody> </table>'
				document.getElementById("searchResult").innerHTML = makeTable
   	   	   	}
   	   	})
   	})
   	
    </script>
</body>
</html>