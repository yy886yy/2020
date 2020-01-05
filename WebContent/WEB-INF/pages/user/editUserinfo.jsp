<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改管理员信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css"  media="all">
<style type="text/css">
	.layui-form{
		margin-left: 50px;
	}
	#btn{
		text-align: center;
	}
</style>
</head>
<body>
<form class="layui-form layui-form-pane">
	<input type="hidden" name="id" value="${user.id}"/>
	<div class="layui-form-item">
    <label class="layui-form-label">用户名</label>
    <div class="layui-input-inline">
      <input type="text" value="${user.userName}" name="userName" id="userName" lay-verify="required" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">邮箱</label>
    <div class="layui-input-inline">
      <input type="text" value="${user.email}" name="email" id="email" lay-verify="email" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">性别</label>
    <div class="layui-input-block">
      <input type="radio" name="sex" value="男" title="男" ${user.sex=='男'?'checked':''}/>
      <input type="radio" name="sex" value="女" title="女" ${user.sex=='女'?'checked':''}/>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">电话号码</label>
    <div class="layui-input-inline">
      <input type="text" value="${user.phone}" name="phone" id="phone" lay-verify="required|phone" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">状态</label>
    <div class="layui-input-inline">
    	<select name="state" id="state">
    		<option value="">请选择</option>
    		<option value="1">启用</option>
    		<option value="0">禁用</option>
    	</select>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">联系地址</label>
    <div class="layui-input-inline">
      <select name="province" id="province" lay-filter="province">
        <option value="">请选择省</option>
      </select>
    </div>
    <div class="layui-input-inline" >
      <select name="city" id="city" lay-filter="city">
        <option value="">请选择市</option>
      </select>
    </div>
    <div class="layui-input-inline">
      <select name="area" id="area" lay-filter="area">
        <option value="">请选择县/区</option>
      </select>
    </div>
  </div>
  <div class="layui-form-item">
    <textarea placeholder="请输入详细地址" class="layui-textarea"
	style="width: 588px; height: 100px;" name="address" id="address"></textarea>
  </div>
  <div class="layui-form-item">
	<div class="layui-upload">
	  <button type="button" class="layui-btn" id="image">图片上传</button>
		<blockquote class="layui-elem-quote layui-quote-nm"
			style="margin-top: 10px;">
			预览图：
			<div class="layui-upload-list" id="preview"></div>
		</blockquote>
		<input type="hidden" id="pic" name="pic" value="${user.pic}"/>
	</div> 
  </div>
  <div class="layui-form-item" id="btn">
   <input type="button" lay-submit lay-filter="submit" class="layui-btn layui-btn-normal" value="提交"/>
   <input type="reset" class="layui-btn layui-btn-normal" value="重置"/>
  </div>
</form>	
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/images/jquery.js"></script>
<script type="text/javascript">
	//给状态赋值
	var state="${user.state}";
	if(state==1){
		$("#state option[value=1]").attr("selected",true);
	}else{
		$("#state option[value=0]").attr("selected",true);
	}
	
layui.use(['form','upload'], function(){
  var form = layui.form,
  	  upload=layui.upload;
	//三级联动
	  $.ajax({
		  url:"${pageContext.request.contextPath}/address/findProvince.do",
		  type:"post",
		  dataType:"json",
		  success:function(data){
			//添加省级联动
			for(var i=0;i<data.length;i++){
				$("#province").append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
			}
			form.render();//重新渲染表单
		  }
		});
	  //监听省级下拉框事件
	  form.on('select(province)', function(data){
		 getCity(data.value);
		});
	  function getCity(provincecode){
		  $.ajax({
			  url:"${pageContext.request.contextPath}/address/findCityByCode.do",
			  data:{"provincecode":provincecode},
			  type:"post",
			  dataType:"json",
			  success:function(data){
				$("#city").empty();
				for(var i=0;i<data.length;i++){
					$("#city").append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
				}
				//可以获取到市级数据第一个选项，然后再加载区县的下拉框
				var citycode=$("#city option:first").val();
				getArea(citycode);
			  }
			});
	  }
	  //监听区县级下拉框事件
	  form.on('select(city)', function(data){
		  var citycode=data.value;
		  getArea(citycode);
		});
	  
	  function getArea(citycode){
			$.ajax({
				  url:"${pageContext.request.contextPath}/address/findAreaByCode.do",
				  data:{"citycode":citycode},
				  type:"post",
				  dataType:"json",
				  success:function(data){
					$("#area").empty();
					for(var i=0;i<data.length;i++){
						$("#area").append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
					}
					form.render();//重新渲染表单
					//获取省市区的信息填写到地址栏
					var province=$("#province").find("option:selected").text();
					var city=$("#city").find("option:selected").text();
					var area=$("#area").find("option:selected").text();
					$("#address").text(province+"-"+city+"-"+area);
				  }
				});
		}
	  //获取省市区的信息填写到地址栏	
		form.on('select(area)', function(data){
				var province=$("#province").find("option:selected").text();
				var city=$("#city").find("option:selected").text();
			  	var area=$("#area").find("option:selected").text();
				$("#address").text(province+"-"+city+"-"+area);
				form.render();//重新渲染表单
			});
	  //因为加载问题要用到setTimeout这个函数
	  window.setTimeout(function(){
		  initAddress();
	  },100);
	//给地址三级联动赋值
	function initAddress(){
		var province="${user.province}";
		var city="${user.city}";
		var area="${user.area}";
		//找到省级下拉框包含（province）的给与选中(默认选中)
		$("#province").find("option:contains('"+province+"')").attr("selected",true);
		getCity($("#province").val());
		form.render();//重新渲染表单
		window.setTimeout(function(){
			$("#city").find("option:contains('"+city+"')").attr("selected",true);
			getArea($("#city").val());
			form.render();//重新渲染表单
		},15);
		window.setTimeout(function(){
			$("#area").find("option:contains('"+area+"')").attr("selected",true);
			form.render();//重新渲染表单
		},30);
		window.setTimeout(function(){
			var province=$("#province").find("option:selected").text();
			var city=$("#city").find("option:selected").text();
		  	var area=$("#area").find("option:selected").text();
			$("#address").text(province+"-"+city+"-"+area);
		},40);
	}
	//图片上传
	  upload.render({
	    elem: '#image'
	    ,url: '${pageContext.request.contextPath}/address/uploadPic.do'
	    ,multiple: false
	    ,before: function(obj){
	      //预读本地文件示例，不支持ie8
	      obj.preview(function(index, file, result){
	        $('#preview').append('<img width="100px" height="100px" src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
	      });
	    }
	    ,done: function(res){
	      //上传完毕
	      $("#pic").val(res.data);
	    }
	  });
  //提交表单
  form.on('submit(submit)', function(data){
	  //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
	  var user=data.field;
	  user.province=$("#province").find("option:selected").text();
	  user.city=$("#city").find("option:selected").text();
	  user.area=$("#area").find("option:selected").text();
	  delete user.file;
	 var tbuserinfo=JSON.stringify(user);
	 console.log(tbuserinfo);
	  $.ajax({
		  url:"${pageContext.request.contextPath}/user/updateUserinfo.do",
		  contentType:"application/json;charset=UTF-8",
		  data:tbuserinfo,
		  type:"post",
		  dataType:"json",
		  success:function(data){
			  if(data=="1"){
				  alert("修改成功！！！");
				  window.parent.location.reload(); //退出并刷新父级页面
			  }else{
				  alert("修改失败！！！");
			  }
		  }
		});
	  return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
  //回显图片
  var img="${user.pic}";
  if(img){
	  $('#preview').append('<img width="100px" height="100px" src="/ws/'+img+'" alt="'+img+'" class="layui-upload-img">')
  }
});
</script>
</body>
</html>