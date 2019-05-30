<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/blog/includes/header.jsp"/>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${requestScope.map.main.title }</h4>
					<p>
						${requestScope.map.main.contents }
					<p>
				</div>
				<ul class="blog-list">
					<c:if test='${requestScope.map.title == "none" }'>
						<div style="text-align: center">전체 포스트 목록</div>
						<div style="text-align: center">--------------------------</div>
					</c:if>
					<c:if test='${requestScope.map.title == "exist" }'>
						<div style="text-align: center">${requestScope.map.main.categoryName } 포스트 목록</div>
						<div style="text-align: center">--------------------------</div>
					</c:if>
					<c:forEach items="${requestScope.map.post }" var="post">
						<li><a href="${pageContext.request.contextPath }/${post.blogId }/${post.categoryNo }/${post.no }">${post.title }</a> <span>${post.regDate }</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>
									
		<div id="extra">
			<div class="blog-logo">
				<c:choose>
					<c:when test='${map.blog.logo == "images/basic" || empty map.blog.logo}'>
      					<img src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">	
      				</c:when>
					<c:otherwise>
						<img src="${pageContext.request.contextPath}/assets/jblog/${requestScope.map.blog.logo }">
					</c:otherwise>
				</c:choose>
				
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${requestScope.map.category }" var="category" >
					<li><a href="${pageContext.request.contextPath }/${category.blogId }/${category.no }">${category.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>