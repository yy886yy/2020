<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>订单列表</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<form class="layui-form layui-form-pane">
 <div class="layui-inline">
    <label class="layui-form-label">客户姓名</label>
    <div class="layui-input-inline">
      <input type="text" id="customerName" name="customerName" lay-verify="required" placeholder="请输入客户名" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-inline">
    <label class="layui-form-label">快递单号</label>
    <div class="layui-input-inline">
      <input type="text" id="orderlogisticsId" name="orderlogisticsId" lay-verify="required" placeholder="请输入单号" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-inline">
    <label class="layui-form-label">订单状态</label>
    <div class="layui-input-inline">
    	<select name="orderStatus" id="orderStatus" lay-verify="required">
    		<option value="未付款">未付款</option>
    		<option value="已付款">已付款</option>
    		<option value="已签收">已签收</option>
    		<option value="退货申请">退货申请</option>
    		<option value="退货中">退货中</option>
    		<option value="已退货">已退货</option>
    		<option value="取消交易">取消交易</option>
    		<option value="交易成功">交易成功</option>
    	</select>
    </div>
  </div>
  <input type="button" id="submit" class="layui-btn layui-btn-normal" value="搜索"/>
 </form>
 <!-- 表格 -->
<table class="layui-hide" id="orderinfo" lay-filter="orderinfo"></table>
 <!-- 模板区域 -->
 <!-- 工具栏区域 -->
<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" lay-event="addorderinfo">新增订单</button>
    <button class="layui-btn layui-btn-sm" lay-event="delorderinfos">批量删除</button>
  </div>
</script>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="look">查看物流</a>
</script>
<!-- 1.序号模板 -->
<script type="text/html" id="xuhaoTemplet">
	{{d.LAY_TABLE_INDEX+1}}
</script>
<script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/images/jquery.js" charset="utf-8"></script>
 
<script>
layui.use(['table','form'], function(){
  var table = layui.table,form=layui.form;
  var tableIns=table.render({
    elem: '#orderinfo'
    ,url:'${pageContext.request.contextPath}/order/findUserList.do'
    ,toolbar: '#toolbarDemo'
    ,title: '订单信息列表'
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      ,{field:'xuhao', title:'序号', width:60, fixed: 'left', unresize: true,templet:'#xuhaoTemplet'}
      ,{field:'id', title:'ID', width:80, fixed: 'left',style:'display:none'}
      ,{field:'orderNumber', title:'订单编号', width:180, edit: 'text'}
      ,{field:'productCount', title:'产品数量', width:90, edit: 'text'}
      ,{field:'orderAmountTotal', title:'付款金额', width:90, edit: 'text'}
      ,{field:'orderlogisticsId', title:'快递单号',width:150}
      ,{field:'phone', title:'电话号码',width:130}
      ,{field:'orderConsignee', title:'客户姓名', width:100}
      ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:190}
    ]]
    ,page: true
    ,done:function(){
    	$("[data-field='id']").css("display","none");
    }
  });
  //头工具栏事件
  table.on('toolbar(orderinfo)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    switch(obj.event){
      case 'addorderinfo':
    	  //弹出层(添加页面)
    	  layer.open({
    		  type: 2, 
    		  title:'新增订单',
    		  area: ['800px', '500px'],//设置宽高
    		  maxmin:true,	  		   //设置可否为最大化
    		  content: '${pageContext.request.contextPath}/order/returnAddOrder.do' //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
    		}); 
        /* var data = checkStatus.data;
        layer.alert(JSON.stringify(data)); */
      break;
      case 'delorderinfos':
        var data = checkStatus.data;
        var ids=new Array();
        for(var i=0;i<data.length;i++){
        	ids.push(data[i].id);
        }
        $.ajax({
  		  url:"${pageContext.request.contextPath}/order/delorderinfoByIds.do",
  		  data:{"ids":ids.toString()},
  		  type:"post",
  		  dataType:"json",
  		  success:function(data){
  			  if(data=="1"){
  				  layer.msg("删除成功！！！");
  	            window.location.reload();// 删除成功后刷新页面
  			  }else{
  				  layer.msg("删除失败！！！");
  			  }
  		  }
  	});
      break;
      case 'isAll':
        layer.msg(checkStatus.isAll ? '全选': '未全选');
      break;
    };
  });
  
  //监听行工具事件
  table.on('tool(orderinfo)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
    	//根据id删除用户
      layer.confirm('真的删除行么', function(index){
    	  $.ajax({
    		  url:"${pageContext.request.contextPath}/order/delorderinfoById.do",
    		  data:{"id":data.id},
    		  type:"post",
    		  dataType:"json",
    		  success:function(data){
    			  if(data=="1"){
    					obj.del();
    				  layer.msg("删除成功！！！");
    			  }else{
    				  layer.msg("删除失败！！！");
    			  }
    		  }
    	});
        layer.close(index);
      });
    } else if(obj.event === 'edit'){
    	layer.open({
    		  type: 2, 
    		  title:'修改信息',
    		  maxmin:true,
    		  area:['850px','500px'],
    		  content: '${pageContext.request.contextPath}/order/editorderinfo.do?id='+data.id //这里content是一个普通的String
    		});
    }else if(obj.event==='look'){
   		layer.open({
	   		  type: 2, 
	   		  title:'查询快递信息',
	   		  area: ['700px', '380px'],//设置宽高
	   		  maxmin:true,	  		   //设置可否为最大化
	   		  content: '${pageContext.request.contextPath}/order/findOrderlogisticsId.do?orderlogisticsId='+data.orderlogisticsId //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
   		});
    }
  });
  //添加搜索条件重新加载页面
  $("#submit").click(function(){
	  tableIns.reload({
		  where: { //设定异步数据接口的额外参数
			  customerName:$("#customerName").val(),        //客户姓名
			  orderlogisticsId:$("#orderlogisticsId").val(),//订单编号
			  orderStatus:$("#orderStatus").val()			//订单状态
		  }
		  ,page: {
		    curr: 1 //重新从第 1 页开始
		  }
		});
  });
});

</script>
</body>
</html>