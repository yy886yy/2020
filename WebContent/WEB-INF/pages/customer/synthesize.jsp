<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>综合统计</title>
<style>
	body{
	 background-color: #e0dbdb;
	}
	ul,li{
		margin:0px;
		padding:0px;
		list-style: none;
	}
	 .statis{
		display: inline-block;
		width: 240px;
		height: 150px;
		border-radius:5px;
		margin-top: 10px;
		margin-left: 10px;
		text-align: center;
		
	}
	 .statis .font{
	 	font-size:20px;
	 	font-family:"Microsoft Yahei";
	 	color:#fff;
	 }
	 .money{
	 	color:#ea0725;
	 	font-size:40px;
	 	padding-top:30px;
	 }
</style>
</head>
<body>
<div class="sale">
		<div class="statis day" style="background-color:#FF7F50">
			<div class="font">当天销售额（元）</div>
			<div class="money" id="dayMoney"></div>
			
		</div>
		<div class="statis week" style="background-color:#87CEFA">
			<div  class="font">本周销售额（元）</div>
			<div class="money" id="weekMoney"></div>
		</div>
		<div class="statis month" style="background-color:#DA70D6">
			<div class="font">本月销售额（元）</div>
			<div class="money" id="monthMoney"></div>
		</div>
		<div class="statis quarter" style="background-color:#32CD32">
			<div class="font">本季度销售额（元）</div>
			<div class="money" id="quarterMoney"></div>
		</div>
	</div>
	<div class="consume">
		<div class="statis month" style="background-color:#FF69B4">
			<div  class="font">本月消费之星</div>
			<ul style="font-size:12px;">
				<li>第一名：姓名[张三]，消费：2000000</li>
				<li>第二名：姓名：李四，消费：300</li>
				<li>第三名：姓名：王五，消费：400</li>
			</ul>
		</div>
		<div class="statis quarter" style="background-color:#32CD32">
			<div class="font">本季度消费之星</div>
			<ul style="font-size:12px;">
				<li>第一名：姓名：张三，消费：200</li>
				<li>第二名：姓名：李四，消费：300</li>
				<li>第三名：姓名：王五，消费：400</li>
			</ul>
		</div>
		<div class="statis year" style="background-color:#6495ED">
			<div class="font">本年度消费之星</div>
			<ul style="font-size:12px;">
				<li>第一名：姓名：张三，消费：200</li>
				<li>第二名：姓名：李四，消费：300</li>
				<li>第三名：姓名：王五，消费：400</li>
				
			</ul>
		</div>
	</div>
	<div class="product">
		<div class="statis month" style="background-color:#6495ED">
			<div  class="font">本月畅销产品</div>
		</div>
		<div class="statis quarter" style="background-color:#DA70D6">
			<div class="font">本季度畅销产品</div>
		</div>
		<div class="statis year" style="background-color:#87CEFA">
			<div class="font">本年度畅销产品</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/images/jquery.js" charset="utf-8"></script>
	<script type="text/javascript">
		$.ajax({
			url:"${pageContext.request.contextPath}/statistics/",
			type:"",
			dataType:"",
			success:function(data){
				
			}
		});
	</script>
</body>
</html>