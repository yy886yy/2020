<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询快递信息</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/layui/css/layui.css"
media="all">
	<style type="text/css">
	.layui-form-item {
		margin-left: 50px;
	}
	#number{
		width:300px;
	}
</style>
</head>
<body>
<form class="layui-form layui-form-pane">
		<div class="layui-form-item">
			<label class="layui-form-label">快递名称</label>
			<div class="layui-input-inline">
				<select name="orderlogistics" id="orderlogistics">
					<option value=""></option>
					<option value="圆通">圆通</option>
					<option value="申通">申通</option>
					<option value="中通">中通</option>
					<option value="韵达">韵达</option>
					<option value="顺丰">顺丰</option>
					<option value="顺丰">EMS</option>
					<option value="百世汇通">百世汇通</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">快递单号</label>
			<div class="layui-input-inline">
				<input type="text" name="orderlogisticsId" id="orderlogisticsId" autocomplete="off"
					class="layui-input" value="${orderlogisticsId}"/>
			</div>
		</div>
		<div class="layui-form-item" style="text-align: center;">
			<input type="button" id="submit" class="layui-btn layui-btn-warm" value="查询"/>
		</div>
	</form>
	<div id="content" style="width:100%;height:300px; border:1px solid red"></div>
	<script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
	<script src="${pageContext.request.contextPath}/images/jquery.js"></script>
	<script type="text/javascript">
		$("#submit").click(function(){
			var orderlogisticsId=$("#orderlogisticsId").val();
			var orderlogistics=$("#orderlogistics").val();
			if(!orderlogistics){
				return layer.msg("快递名称不能为空,请选择!!!");
			}
			$.ajax({
				url:"${pageContext.request.contextPath}/order/findOrderlogistics.do",
				data:{"orderlogistics":orderlogistics,"orderlogisticsId":orderlogisticsId},
				type:"post",
				dataType:"json",
				success:function(res){
					if(res.message=="ok"){
						$("#content").empty();
						for(var i=0;i<res.data.length;i++){
							var text="<p>开始时间:"+res.data[i].time+"结束时间:"+res.data[i].ftime+"快递消息:"+res.data[i].context+"</p>";
							$("#content").append(text);
						}
					}else{
						$("#content").empty();
						$("#content").html(res.mssage);
					}
				}
			});
		});
	</script>
</body>
</html>