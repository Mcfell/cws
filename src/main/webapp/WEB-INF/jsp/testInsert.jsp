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
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=115f73b389dc5b5261c23481467da3c6"></script>
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<style type="text/css">
	
</style>
</head>
<body>
	
	<div class="map" id="r-map" style="min-width:768px;height:500px;margin:0 auto;">
	
</div>

<form action="insertParks" method="post">
	<h4>添加停车场</h4>
	<table>
	<tr>
		<th>停车场名</th>
		<th>经度</th>
		<th>纬度</th>
		<th>停车场图片</th>
		<th>城市</th>
		<th>区域</th>
		<th>地址</th>
		<th>白天价格</th>
		<th>晚上价格</th>
	</tr>
	<tr>
		<td><input type="text" name="pName"/></td>
		<td><input type="text" name="lng" /></td>
		<td><input type="text" name="lat" ></input></td>
		<td><input type="text" name="pic"></input></td>
		<td><input type="text" name="city" ></input></td>
		<td><input type="text" name="area" ></input></td>
		<td><input type="text" name="addr" ></input></td>
		<td><input type="text" value="2.5" name="dayPrice"></input></td>
		<td><input type="text" value="1" name="nightPrice"></input></td>
		<td><button type="submit">添加</button></td>
	</tr>
	
</form>


<form action="insertLocks" method="post">
	<table id="locks">
	<tr>
		<th>所属用户</th>
		<th>停车场Id</th>
		<th>是否出租</th>
		<th>使用状态</th>
		<th>故障状态</th>
		<th>蓝牙Id</th>
		<th>蓝牙密钥</th>
	</tr>
	<tr class="lock">
			<td><input name="Carlocks[0].uId" type="text" ></input></td>
			<td><input name="Carlocks[0].pId" type="text" ></input></td>
			<td><input name="Carlocks[0].isRent" type="text" ></input></td>
			<td><input name="Carlocks[0].useStat" type="text"></input></td>
			<td><input name="Carlocks[0].faultStat" type="text"></input></td>
			<td><input name="Carlocks[0].blueId" type="text" ></input></td>
			<td><input name="Carlocks[0].bulePwd" type="text"></input></td>
			<td><button id="add">添加</button></td>
	</tr>
	</table>
</form>
</body>

<script type="text/javascript">
	
	$(document).ready(function () {

		map = new BMap.Map("r-map");    // 创建Map实例
		map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
		map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
		//map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
		map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
		var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_LARGE}); 
		map.addControl(top_right_navigation);  //右上角，仅包含平移和缩放按钮
		
		$("#add").click(function(){
			var lock = $(".lock").clone();
			$("#locks").append(lock);
		})
    	map.addEventListener("click",addMarker);  
    	geoc = new BMap.Geocoder();    
    	map.addEventListener("click", function(e){        
    		var pt = e.point;
    		geoc.getLocation(pt, function(rs){
    			var marker = new BMap.Marker(pt);
    			var addComp = rs.addressComponents;
    			var lng=pt.lng;
    			var lat=pt.lat;
    			//alert(""+lng+lat);
    			var addr = addComp.province +  addComp.city  + addComp.district  + addComp.street + addComp.streetNumber
    			$("[name='pName']").val(addComp.district  + addComp.street + addComp.streetNumber+'停车场');
    			$("[name='lng']").val(lng);
    			$("[name='lat']").val(lat);
    			$("[name='city']").val(addComp.city);
    			$("[name='area']").val(addComp.district);
    			$("[name='addr']").val(addr);
    			
    			var opts = {
    					  position : pt,    // 指定文本标注所在的地理位置
    					  offset   : new BMap.Size(-60, -50)    //设置文本偏移量
    					}
    			var label = new BMap.Label(addr, opts);  // 创建文本标注对象
    			label.setStyle({
    							 color : "red",
    							 fontSize : "12px",
    							 height : "20px",
    							 width:'auto',
    							 lineHeight : "20px",
    							 fontFamily:"微软雅黑"
    						 });
    			map.addOverlay(label); 
    		});        
    	});
    	function enableDragend(marker){
 	    	marker.addEventListener("dragend", function(e){   
 			    map.clearOverlays();
 			    var pt = e.point;
 	    		geoc.getLocation(pt, function(rs){
 	    			var marker = new BMap.Marker(pt);
 	    			var addComp = rs.addressComponents;
 	    			var addr = addComp.province +  addComp.city  + addComp.district  + addComp.street + addComp.streetNumber
 	    			$("[name='CSAddr']").val(addr);
 	    			$("[name='CSCity']").val(addr);
 	    			var opts = {
 	    					  position : pt,    // 指定文本标注所在的地理位置
 	    					  offset   : new BMap.Size(-60, -50)   //设置文本偏移量
 	    					}
 	    			var label = new BMap.Label(addr, opts);  // 创建文本标注对象
 	    			label.setStyle({
 	    							 color : "red",
 	    							 fontSize : "12px",
 	    							 height : "20px",
 	    							 width:'auto',
 	    							 lineHeight : "20px",
 	    							 fontFamily:"微软雅黑"
 	    						 });
 	    			map.addOverlay(label); 
 	    			map.addOverlay(marker);
 	    			enableDragend(marker); 
 	    		}); 
           });  	
 	    }
    	function addMarker(e){
	    	  map.clearOverlays();
			  var point = new BMap.Point(e.point.lng, e.point.lat);
			  var marker = new BMap.Marker(point);
			  map.addOverlay(marker);
			  marker.enableDragging();
			  enableDragend(marker); 	
		}
	})
</script>
</html>