<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
function checkId(){
	var $check = $('#check-email').val();
	if ($check == 'true'){
		return true;
	}
	alert("아이디 중복검사 안했습니다. 하세요.");
	return false;
}

$(function(){
	$('#blog-id').change(function(){
		$('#btn-checkemail').show();
		$('#img-checkemail').hide();
	});
	
	$('#btn-checkemail').click(function(){
		var blog_id = $('#blog-id').val();
		if(blog_id == ''){
			return;
		};
		alert(blog_id);
		
		/* ajax 통신 */
		$.ajax({
			url: "${pageContext.servletContext.contextPath}/user/checkId",
			type: "get",
			dataType: "json",
			data: {"id":blog_id },
			success: function(response){
				if (response.result != "success"){
					console.error("error:"+response.message);
					console.log(response);
					return;
				}
				
				if (response.data == true){
					alert('이미 존재하는 아이디입니다. \n다른 아이디를 사용해주세요');
					$('#blog_id').val("");
					return;
				}
					$('#check-email').val("true");
					$('#btn-checkemail').hide();
					$('#img-checkemail').show();
					
			},
			error: function(xhr, error){
				console.error("error: " + error);
			}
		});
		//console.log(email);
	});
});

</script>
</head>
<body>
	<div class="center-content">
		<c:import url='/WEB-INF/views/includes/mainHeader.jsp'/>
		
		<form:form modelAttribute="userVo" class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath }/user/join" onsubmit="return checkId()">
			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value="">
			<p style="font-weight: bold; color:red; text-align: left; padding: 0;">
				<form:errors path="name"/>
			</p>
			<label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" type="text"> 
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<p style="font-weight: bold; color:red; text-align: left; padding: 0;">
				<form:errors path="id"/>
			</p>
			<input type="hidden" id="check-email" value="false">
			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />
			<p style="font-weight: bold; color:red; text-align: left; padding: 0;">
				<form:errors path="password"/>
			</p>
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
