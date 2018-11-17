/**
 * 实现Ajax查询数据库进行动态Echart数据展示
 */
// 路径配置  
        require.config({  
            paths: {  
                echarts: 'http://echarts.baidu.com/build/dist'  
            }  
        });  
          
        // 使用  
        require(  
            [  
                'echarts', 
                'echarts/chart/line',
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载  
            ],  
            function drewEcharts(ec) {
                // 基于准备好的dom，初始化echarts图表
                myChart = ec.init(document.getElementById('main')); 
                var option = {
                    tooltip: {
                        show: true
                    },
                    toolbox: {
                    	
                        show : true,
                        feature : {
                        	mark : {show: true},
             	            dataView : {show: true, readOnly: false},
             	            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
             	            restore : {show: true},
             	            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    legend: {
                        data:['name']
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : (function(){
                                    var arr=[];
                                        $.ajax({
                                        type : "get",
                                        async : false, //同步执行
                                        url : "/users/json",
                                        data : {},
                                        dataType : "json", //返回数据形式为json
                                        success : function(result) {
                                        if (result) {
                                               for(var i=0;i<result.length;i++){
                                                 	console.log(result[i].name);
                                                   	arr.push(result[i].name);
                                                }    
                                        }
                                    },
                                    error : function(errorMsg) {
                                        alert("不好意思，图表请求数据失败啦!");
                                        myChart.hideLoading();
                                    }
                                   })
                                   return arr;
                                })() 
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"年龄",
                            "type":"bar",
                            "data":(function(){
                                        var arr=[];
                                        $.ajax({
                                        type : "get",
                                        async : false, //同步执行
                                        url : "/users/json",
                                        data : {},
                                        dataType : "json", //返回数据形式为json
                                        success : function(result) {
                                        if (result) {
                                               for(var i=0;i<result.length;i++){
                                                  console.log(result[i].age);
                                                  arr.push(result[i].age);
                                                }  
                                        }
                                    },
                                    error : function(errorMsg) {
                                        alert("不好意思,图表请求数据失败!");
                                        myChart.hideLoading();
                                    }
                                   })
                                  return arr;
                            })()
                            
                        }
                    ]
                };               
                // 为echarts对象加载数据 
                myChart.setOption(option);   
            }
        );  