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
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function(){
	
	$(document).on("click",".delete-Btn",function(){
		var a = $(this).data('value');
		var datas = {'no':a};
		console.log(a);
		/* ajax 통신 */
	  	$.ajax({
			url: "${pageContext.servletContext.contextPath}/${authUser.id }/admin/deleteCategory",
			type: "post",
			dataType: "json",
			data: datas,
			success: function(response){
				var html="";
				html+="<tr><th>번호</th>";
				html+="<th>카테고리명</th>";
				html+="<th>포스트 수</th>";
				html+="<th>설명</th>";
				html+="<th>삭제</th></tr>";;
				for (var i=0; i<response.length; i++){
					
					html+="<tr><td>"+response[i].no+"</td>";
					html+="<td>"+response[i].name+"</td>";
					html+="<td>"+response[i].count+"</td>";
					html+="<td>"+response[i].description+"</td>";
					html+="<td>"+"<a data-value='"+response[i].no+"' class='delete-Btn'><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></a></td>";
					html+="</tr>"
				}
				//document.getElementById("category-table").innerHTML=html;
				$('#category-table').html(html);		
			},
			error: function(xhr, error){
				console.error("error: " + error);
			}
		});	
	});
	
	
	$(document).on("click","#addCategoryBtn",function(){
		var a = $('#authId').val();
		var name = $('#categoryName').val();
		var description = $('#description').val();
		console.log(name);
		if(name == '' || description == ''){
			return;
		};
		var datas = {'name':name, 'description':description}
		
		/* ajax 통신 */
		$.ajax({
			url: "${pageContext.servletContext.contextPath}/${authUser.id }/admin/addCategory",
			type: "post",
			dataType: "json",
			data: datas,
			success: function(response){
				var html="";
				html+="<tr><th>번호</th>";
				html+="<th>카테고리명</th>";
				html+="<th>포스트 수</th>";
				html+="<th>설명</th>";
				html+="<th>삭제</th></tr>";;
				for (var i=0; i<response.length; i++){
					
					html+="<tr><td>"+response[i].no+"</td>";
					html+="<td>"+response[i].name+"</td>";
					html+="<td>"+response[i].count+"</td>";
					html+="<td>"+response[i].description+"</td>";
					html+="<td>"+"<a data-value='"+response[i].no+"' class='delete-Btn'><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></a></td>";
					html+="</tr>"
				}
				//document.getElementById("category-table").innerHTML=html;
				$('#category-table').html(html);		
			},
			error: function(xhr, error){
				console.error("error: " + error);
			}
		});	
		
		
	});
	
});
	
	

</script>
</head>
<body>
	
	<div id="container">
		<c:import url="/WEB-INF/views/blog/includes/header.jsp"/>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath }/${authUser.id }/admin/basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath }/${authUser.id }/admin/write">글작성</a></li>
				</ul>
		      	<table id="category-table" class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:forEach items="${map.categoryList }" var="category">
		      		<tr>
						<td>${category.no }</td>
						<td>${category.name }</td>
						<td>${category.count }</td>
						<td>${category.description }</td>
						<td><a data-value='${category.no }' class='delete-Btn'><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a></td>
					</tr>
		      		</c:forEach>
		      						  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" id="categoryName" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" id="description" name="description"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="button" id="addCategoryBtn" value="카테고리 추가"></td>
		      		</tr>      		      
		      			
		      	</table> 
			</div>
			<a value="df" class="asdfBtn">asdf</a>	
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>