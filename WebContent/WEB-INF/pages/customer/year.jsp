<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="height:400px"></div>
    <!-- ECharts单文件引入 -->
    <script src="${pageContext.request.contextPath}/images/echarts/build/dist/echarts.js"></script>
    <script src="${pageContext.request.contextPath}/images/jquery.js"></script>
    <script type="text/javascript">
    var year=new Array();
    var datayear=new Array();
    $.ajax({
		url:"${pageContext.request.contextPath}/statistics/findYear.do",
		type:"post",
		dataType:"json",
		success:function(data){
			for(var i=0;i<data.length;i++){
				year[i]=data[i].years+"年";
				datayear[i]={"value":data[i].price,"name":year[i]}
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
                'echarts/chart/pie',
                'echarts/chart/funnel'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                option = {
                	    title : {
                	        text: '某站点用户访问来源',
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
                	        data:year
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
                	            name:'访问来源',
                	            type:'pie',
                	            radius : '55%',
                	            center: ['50%', '60%'],
                	            data:datayear
                	        }
                	    ]
                	};
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
    }
    </script>
</body>