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
    <form th:action="@{/emissions/search}" method="post">
    
        <label for="mainCategory">대분류</label>
        <select id="mainCategory" name="mainCategory">
            <option value="에너지">에너지</option>
            <option value="산업공정">산업공정</option>
            <option value="농업">농업</option>
            <option value="LULUCF">LULUCF</option>
            <option value="폐기물">폐기물</option>
            <option value="별도항목">별도항목</option>
        </select>

        <label for="subCategory">중분류</label>
        <select id="subCategory" name="subCategory">
            <option value="연료연소">연료연소</option>
            <!-- 중분류 옵션 추가 -->
        </select>
        
        <label for="subCategory">소분류</label>
        <select id="subCategory" name="subCategory">
            <option value="연료연소">에너지 산업</option>
            <!-- 소분류 옵션 추가 -->
        </select>
        
        <label for="subCategory">분야</label>
        <select id="subCategory" name="subCategory">
            <option value="연료연소">공공전기 및 열 생간</option>
            <!-- 분야 옵션 추가 -->
        </select>
        
        <label for="yearFrom">From</label>
        <select id="yearFrom" name="yearFrom">
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

        <label for="yearTo">To</label>
        <select id="yearTo" name="yearTo">
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

        <button type="submit">검색</button>
    </form>

<%--     <table>
        <thead>
            <tr>
                <th>대분류</th>
                <th>중분류</th>
                <th>소분류</th>
                <th>분야</th>
                <th>배출량</th>
            </tr>
        </thead>
        <tbody>
            <tr th:each="data : ${results}">
                <td th:text="${data.mainCategory}"></td>
                <td th:text="${data.subCategory}"></td>
                <td th:text="${data.specificCategory}"></td>
                <td th:text="${data.field}"></td>
                <td th:text="${data.amount}"></td>
            </tr>
        </tbody>
    </table> --%>
</body>
</html>