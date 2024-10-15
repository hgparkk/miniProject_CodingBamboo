<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>CodingBamboo</title>
        
        <%@ include file="/WEB-INF/inc/header.jsp" %>
        
    </head>
    <body id="page-top">
        
        <%@ include file="/WEB-INF/inc/top.jsp" %>
        
        <!-- Masthead-->
        <header class="masthead bg-primary text-white text-center">
            <div class="container d-flex align-items-center flex-column">
                <!-- Masthead Avatar Image-->
                <img class="masthead-avatar mb-5" src="assets/img/bamboo.png" alt="..." />
                <!-- Masthead Heading-->
                <h1 class="masthead-heading text-uppercase mb-0">탄소 배출량 계산 시스템</h1>
                <!-- Icon Divider-->
                <div class="divider-custom divider-light">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- Masthead Subheading-->
                <p class="masthead-subheading font-weight-light mb-0">Coding Bamboo</p>
            </div>
        </header>

        <!-- About Section-->
        <section class="page-section bg-primary text-white mb-0" id="about">
            <div class="container">
                <!-- About Section Heading-->
                <h2 class="page-section-heading text-center text-uppercase text-white">소개</h2>
                <!-- Icon Divider-->
                <div class="divider-custom divider-light">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                    <div class="divider-custom-line"></div>
                </div>
                <!-- About Section Content-->
                <div class="row">
                    <div class="col-lg-6 mx-auto my-auto"><p class="lead">탄소 배출량 및 권장 배출량을 예측하고 건축 시 예상되는 탄소 배출량과 건물 내 에너지 사용량을 바탕으로 예상 탄소배출량을 확인 하여 계산한 결과를 바탕으로 탄소배출량 저감을 유도하고자 합니다.</p></div>
                </div>

            </div>
        </section>

        <%@ include file="/WEB-INF/inc/footer.jsp" %>
        
    </body>
</html>
