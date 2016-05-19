<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.7.1.min.js">
</script>
</head>
<body>
<form action="insertParks" method="post">
	<label value="停车场"></label>
	停车场名<input type="text" name="pName" lable="停车场名"></input><br>
	经度<input name="lng" type="text" lable="经度"></input><br>
	纬度<input name="lat" type="text" lable="纬度"></input><br>
	停车场图片<input type="text" name="pic" lable="停车场图片"></input><br>
	城市<input type="text" name="city" lable="城市"></input><br>
	区域<input type="text" name="area" lable="区域"></input><br>
	白天价格<input type="text" name="dayPrice" lable="白天价格"></input><br>
	晚上价格<input type="text" name="nightPrice" lable="晚上价格"></input><br>
	<button value="登录" type="submit">添加</button>
</form>
<form action="insertLocks" method="post">
	<input type="button" id="add" value="添加"/>
	<table id="locks">
		<tr class="lock">
			<td><input name="Carlocks[0].uId" type="text" lable="经度"></input></td>
			<td><input name="Carlocks[0].pId" type="text" lable="经度"></input></td>
			<td><input name="Carlocks[0].isRent" type="text" lable="经度"></input></td>
			<td><input name="Carlocks[0].useStat" type="text" lable="经度"></input></td>
			<td><input name="Carlocks[0].faultStat" type="text" lable="经度"></input></td>
			<td><input name="Carlocks[0].blueId" type="text" lable="经度"></input></td>
			<td><input name="Carlocks[0].bulePwd" type="text" lable="经度"></input></td>
		</tr>
	</table>
	<button value="" type="submit">添加</button>
</form>
</body>
<script type="text/javascript">
	
	$(document).ready(function () {
		(function($){
			$("#add").click(function(){
					var lock = $(".lock").clone();
					$("#locks").append(lock);
				})
			 })(jQuery); 
	})
</script>
</html>