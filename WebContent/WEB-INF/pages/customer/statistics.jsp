<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>统计页面</title>
</head>
<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="height:400px"></div>
 </body>   
     <!-- ECharts单文件引入 -->
    <script src="${pageContext.request.contextPath}/images/echarts/build/dist/echarts.js"></script>
    <script src="${pageContext.request.contextPath}/images/jquery.js"></script>
     <script type="text/javascript">
     var gradeName=new Array();
     gradeName[0]="青铜";
     gradeName[1]="白银";
     gradeName[2]="黄金";
     gradeName[3]="铂金";
     gradeName[4]="钻石";
     gradeName[5]="超级VIP";
     var arr=new Array();
     $.ajax({
 		  url:"${pageContext.request.contextPath}/customer/statistics.do",
 		  type:"post",
 		  dataType:"json",
 		  success:function(data){
 			  for(var i=0;i<data.length;i++){
 				  arr[i]={"name":gradeName[i],"value":data[i].gradeNum};
 			  }
 			 statistics();
 		  }
 	});
     function statistics(){
        // 路径配置
        require.config({
            paths: {
                echarts: '${pageContext.request.contextPath}/images/echarts/build/dist'
            }
        });
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/pie',   // 饼形图
                'echarts/chart/funnel' // 漏斗图
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                option = {
                	    title : {
                	        text: '统计数据',
                	        subtext: '纯属虚构',
                	        x:'center'
                	    },
                	    tooltip : {
                	        trigger: 'item',
                	        formatter: "{a} <br/>{b} : {c} ({d}%)"
                	    },
                	    legend: {
                	        orient : 'vertical',
                	        x : 'left',
                	        data:gradeName
                	    },
                	    toolbox: {
                	        show : true,
                	        feature : {
                	            mark : {show: true},
                	            dataView : {show: true, readOnly: false},
                	            magicType : {
                	                show: true, 
                	                type: ['pie', 'funnel'],
                	                option: {
                	                    funnel: {
                	                        x: '25%',
                	                        width: '50%',
                	                        funnelAlign: 'left',
                	                        max: 1548
                	                    }
                	                }
                	            },
                	            restore : {show: true},
                	            saveAsImage : {show: true}
                	        }
                	    },
                	    calculable : true,
                	    series : [
                	        {
                	            name:'统计数据',
                	            type:'pie',
                	            radius : '55%',
                	            center: ['50%', '60%'],
                	            data:arr
                	        }
                	    ]
                	};
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
     }
    </script>
</html>