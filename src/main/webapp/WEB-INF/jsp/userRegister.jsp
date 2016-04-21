<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>

<form action="ajaxUserRegister.do" method="post">
	<label value="登录系统"></label>
	<input type="text" name="nickname" lable="帐号"></input>
	<input name="password" type="password" lable="密码"></input>
	<input name="email" type="email" lable="email"></input>
	<button value="登录" type="submit">登录</button>
</form>
</body>
</html>