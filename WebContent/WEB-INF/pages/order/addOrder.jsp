<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增订单</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
<style type="text/css">
	.layui-form{
		margin-left: 50px;
	}
	.layui-input{
		width:300px;
	}
	.customer-div{
		position: relative;
	}
	.customer-div #nameul{
		z-index: 1;
		background-color: #fff;	
		position: absolute;
		top:40px;
		left: 0px;
		border: 1px solid #dee9ec;
		display: none;
	}
	#nameul li{
		margin: 0;
		padding:0;
		padding-left:2px;
		width:298px;
		height: 20px;
		line-height: 20px;
		cursor: pointer;
	}
	#nameul li:hover{
		background-color: #dee9ec;
	}
	
</style>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>订单信息</legend>
</fieldset>
<form class="layui-form layui-form-pane">
	<div class="layui-form-item">
	<input type="hidden" id="customerId">
    <label class="layui-form-label">订单编号</label>
    <div class="layui-input-inline">
      <input type="text" name="orderNumber" id="orderNumber" lay-verify="required" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">客户姓名</label>
    <div class="layui-input-inline customer-div">
      <input type="text" name="customerName" id="customerName" lay-verify="required" autocomplete="off" class="layui-input">
      <ul id="nameul">
      </ul>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">联系方式</label>
    <div class="layui-input-inline">
      <input type="text" name="phone" id="phone" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">收货地址</label>
    <div class="layui-input-inline">
    	<input type="text" name="address" id="address" autocomplete="off" class="layui-input">
    </div>
  </div>
</form>	
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>订单详细信息</legend>
</fieldset>
<table class="layui-hide" id="orderinfo" lay-filter="orderinfo"></table>
<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" lay-event="delOrderDetails">批量删除</button>
  </div>
</script>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delOrderDetail">删除</a>
</script>
<!-- 序号格式化模板 -->
<script type="text/html" id="xuhaoTemplet">
	{{d.LAY_TABLE_INDEX+1}}
</script>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>产品详细信息</legend>
</fieldset>
<table class="layui-hide" id="product" lay-filter="product"></table>
<script type="text/html" id="productDemo">
  <a class="layui-btn layui-btn-xs" lay-event="addOrder">加入订单</a>
</script>
<!-- 序号格式化模板 -->
<script type="text/html" id="productXuhao">
	{{d.LAY_TABLE_INDEX+1}}
</script>

<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/images/jquery.js"></script>
<script type="text/javascript">
	$.ajax({
		url:"${pageContext.request.contextPath}/order/getOrderCode.do",
		type:"post",
		dataType:"json",
		success:function(data){
			$("#orderNumber").val(data.code);
		}
	});
</script>
<script type="text/javascript">
layui.use(['form','table'], function(){
  var form = layui.form,table=layui.table;
  var orderCode=$("#orderNumber").val();
  var tableIns=table.render({
	    elem: '#orderinfo'
	    ,url:'${pageContext.request.contextPath}/detail/findOrderDetails.do?orderCode='+orderCode
	    ,toolbar: '#toolbarDemo'
	    ,title: '订单详细列表'
	    ,cols: [[
	      {type: 'checkbox', fixed: 'left'}
	      ,{field:'xuhao', title:'序号', width:60, fixed: 'left', unresize: true,templet:'#xuhaoTemplet'}
	      ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true,style:'display:none'}
	      ,{field:'productName', title:'商品名称', width:100, edit: 'text'}
	      ,{field:'productPrice', title:'商品价格', width:100, edit: 'text'}
	      ,{field:'discountRate', title:'折扣', width:100, edit: 'text'}
	      ,{field:'orderDetailNumber', title:'商品数量', width:150, edit:'text'}
	      ,{field:'subtotal', title:'小计金额',width:150}
	      ,{field:'productMarque', title:'商品型号', width:120}
	      ,{field:'detailStatus', title:'状态', width:120}
	      ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:200}
	    ]]
	    ,page: true
	    //隐藏id属性
	    ,done:function(){
	    	$("[data-field='id']").css("display","none");
	    }
	  });
  	//加载商品信息列表
  table.render({
	    elem: '#product'
	    ,url:'${pageContext.request.contextPath}/product/findProductList.do'
	    ,toolbar: '#productToolbar'
	    ,title: '产品详细列表'
	    ,cols: [[
	      {type: 'checkbox', fixed: 'left'}
	      ,{field:'xuhao', title:'序号', width:60, fixed: 'left', unresize: true,templet:'#productXuhao'}
	      ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true,style:'display:none'}
	      ,{field:'infoName', title:'商品名称', width:100, edit: 'text'}
	      ,{field:'infoCost', title:'成本价', width:100, edit: 'text'}
	      ,{field:'infoPrice', title:'销售价格', width:100, edit: 'text'}
	      ,{field:'infoSpec', title:'商品规格', width:150, edit:'text'}
	      ,{field:'infoBrand', title:'品牌',width:150}
	      ,{field:'infoPostage', title:'快递费', width:120}
	      ,{fixed: 'right', title:'操作', toolbar: '#productDemo', width:200}
	    ]]
	    ,page: true
	    //隐藏id属性
	    ,done:function(){
	    	$("[data-field='id']").css("display","none");
	    }
	  });
  //监听行工具事件
  table.on('tool(product)', function(obj){
    var data = obj.data;
    if(obj.event === 'addOrder'){
    	//拿到订单编号
    	var orderNumber=$("#orderNumber").val();
    	//拿到收货人的姓名
    	var orderConsignee=$("#customerName").val();
    	//拿到收货人的地址
    	var address=$("#address").val();
    	//拿到客户id
    	var customerId=$("#customerId").val();
    	//拿到手机号码
    	var phone=$("#phone").val();
    	var orderJSON={"productId":data.id,"productName":data.infoName,"infoDiscount":data.infoDiscount,
    			       "infoCost":data.infoCost,"infoPrice":data.infoPrice,"infoSpec":data.infoSpec,
    			       "customerId":customerId,"orderNumber":orderNumber,"address":address,
    			       "orderConsignee":orderConsignee,"phone":phone};
    	var orderDetailVO=JSON.stringify(orderJSON);
    	  $.ajax({
    		  url:"${pageContext.request.contextPath}/detail/addOrderDetail.do",
    		  contentType:"application/json;charset=UTF-8",
    		  data:orderDetailVO,
    		  type:"post",
    		  dataType:"text",
    		  success:function(data){
    			  if(data=="ok"){
    				//添加成功后刷新我们的详细订单表格
  					 tableIns.reload({
  					    page: {
  					      curr: 1 //重新从第 1 页开始
  					    }
  					  });
    			  }
    		  }
    	});
    } 
  });
  //头工具栏事件
  table.on('toolbar(orderinfo)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    switch(obj.event){
      case 'delOrderDetails':
        var data = checkStatus.data;
        var ids=new Array();
        for(var i=0;i<data.length;i++){
        	ids.push(data[i].id);
        }
        $.ajax({
  		  url:"${pageContext.request.contextPath}/detail/delOrderDetails.do",
  		  data:{"ids":ids.toString()},
  		  type:"post",
  		  dataType:"text",
  		  success:function(data){
  			  if(data=="ok"){
  				  layer.msg("删除成功！！！");
  	            window.location.reload();// 删除成功后刷新页面
  			  }else{
  				  layer.msg("删除失败！！！");
  			  }
  		  }
  	});
      break;
    };
  });
  //监听行工具事件
  table.on('tool(orderinfo)', function(obj){
    var data = obj.data;
    if(obj.event === 'delOrderDetail'){
    	//根据id删除用户
      layer.confirm('真的删除行么', function(index){
    	  $.ajax({
    		  url:"${pageContext.request.contextPath}/detail/delOrderDetail.do",
    		  data:{"id":data.id},
    		  type:"post",
    		  dataType:"text",
    		  success:function(data){
    			  if(data=="ok"){
    					obj.del();
    				  layer.msg("删除成功！！！");
    			  }else{
    				  layer.msg("删除失败！！！");
    			  }
    		  }
    	});
      });
    }
  });
});
$("#customerName").keyup(function(){
	var name=$("#customerName").val();
	if(!name){
		return;
	}
	$.ajax({
		url:"${pageContext.request.contextPath}/customer/getCustomerNameByStr.do",
		data:{"name":name},
		type:"post",
		dataType:"json",
		success:function(data){
			$("#nameul").empty();
			$("#nameul").css("display","block");
			for(var i=0;i<data.length;i++){
				$("#nameul").append("<li customerId='"+data[i].id+"' phone='"+data[i].phone+"' address='"+data[i].address+"' onclick='addCustomerName(this);'>"+data[i].name+"</li>");
			}
		}
	});
});
function addCustomerName(obj){
	var name=$(obj).text();
	var address=$(obj).attr("address");
	var phone=$(obj).attr("phone");
	var customerId=$(obj).attr("customerId");
	$("#customerName").val(name);
	$("#nameul").css("display","none");
	$("#address").val(address);
	$("#phone").val(phone);
	$("#customerId").val(customerId);
}
</script>
</body>
</html>