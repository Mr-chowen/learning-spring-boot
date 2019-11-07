<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="user" method="post" action="${pageContext.request.contextPath }/user/save">
		<fieldset>
			<legend>添加一个用户</legend>
			<p>
				<label>用户名：</label>
				<form:input path="username"/>
			</p>
			<p>
				<label>爱好：</label>
				<form:checkboxes items="${hobbys }" path="hobby"/>
			</p>
			<p>
				<label>朋友：</label>
				<form:checkbox path="friends" value="春风"/>春风
				<form:checkbox path="friends" value="夏阳"/>夏阳
				<form:checkbox path="friends" value="秋雨"/>秋雨
				<form:checkbox path="friends" value="冬雪"/>冬雪
			</p>
			<p>
				<label>职业：</label>
				<form:select path="carrer">
					<option/>请选择职业
					<form:options items="${carrers }"/>
				</form:select>
			</p>
			<p>
				<label>户籍：</label>
				<form:select path="houseRegister">
					<option/>请选择户籍
					<form:options items="${houseRegisters }"/>
				</form:select>
			</p>
			<p>
				<label>个人描述：</label>
				<form:textarea path="remark" rows="5"/>
			</p>
			<p id="button">
				<input id="reset" type="reset"/>
				<input id="submit" type="submit" value="添加"/>
			</p>
		</fieldset>
	</form:form>
</body>
</html>