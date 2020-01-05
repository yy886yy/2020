<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>微商营销管理系统</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"/>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">微商营销管理系统</div>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
       		   ${user.userName}
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="${pageContext.request.contextPath}/logOut.do">退出</a></dd>
        </dl>
      </li>
    </ul>
  </div>
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed"><a href="javascript:;">系统管理</a>
          <dl class="layui-nav-child">
            <dd><a href="${pageContext.request.contextPath}/user/returnList.do" target="iframe">管理员列表</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="javascript:;">客户管理</a>
          <dl class="layui-nav-child">
            <dd><a href="${pageContext.request.contextPath}/customer/returnStatistics.do" target="iframe">客户类型统计</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="javascript:;">产品管理</a>
        	<dl class="layui-nav-child">
           	 <dd><a href="${pageContext.request.contextPath}/product/returnProductList.do" target="iframe">产品信息列表</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="javascript:;">订单管理</a>
        	<dl class="layui-nav-child">
            	<dd><a href="${pageContext.request.contextPath}/order/returnOrderList.do" target="iframe">订单信息列表</a></dd>
           </dl>
        </li>
        <li class="layui-nav-item"><a href="javascript:;">统计管理</a>
        	<dl class="layui-nav-child">
            <dd><a href="${pageContext.request.contextPath}/statistics/returnYear.do" target="iframe">年统计</a></dd>
            <dd><a href="${pageContext.request.contextPath}/statistics/returnQuarter.do" target="iframe">季度统计</a></dd>
            <dd><a href="${pageContext.request.contextPath}/statistics/returnMonth.do" target="iframe">月统计</a></dd>
          </dl>
        </li>
      </ul>
    </div>
  </div>
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <iframe name="iframe" id="iframe" src="${pageContext.request.contextPath}/user/returnList.do" width="100%" height="100%" 
    		frameborder="no" border="0" scrolling="auto"></iframe>
  </div>
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});
</script>
</body>
</html>