<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="header">
	<h1>
		<c:if test="${empty map }">${blog.title }</c:if>
		<c:if test="${empty blog }">${map.blog.title }</c:if>
	</h1>
	<ul>
	<c:choose>
		<c:when test="${empty authUser }">
			<li><a href="">로그인</a></li>		
		</c:when>
		<c:otherwise>
			<li><a href="">로그아웃</a></li>
			<li><a href="${pageContext.request.contextPath }/${authUser.id }/admin/basic">블로그 관리</a></li>		
		</c:otherwise>
	</c:choose>
	</ul>
</div>