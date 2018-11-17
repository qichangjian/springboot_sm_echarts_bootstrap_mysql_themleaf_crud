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
        'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
    ],
    function drewEcharts(ec) {
        // 基于准备好的dom，初始化echarts图表
        myChart = ec.init(document.getElementById('main1'));
        var option = {
            backgroundColor: '#2c343c',

            title: {
                text: 'Customized Pie',
                left: 'center',
                top: 20,
                textStyle: {
                    color: '#ccc'
                }
            },

            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },

            visualMap: {
                show: false,
                min: 80,
                max: 600,
                inRange: {
                    colorLightness: [0, 1]
                }
            },
            series : [
                {
                    name:'访问来源',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '50%'],
                    data:(function(){
                            var arr=[];
                            $.ajax({
                                type : "get",
                                async : false, //同步执行
                                url : "/users/json1",
                                data : {},
                                dataType : "json", //返回数据形式为json
                                success : function(result) {
                                    if (result) {
                                        for(var i=0;i<result.length;i++){
                                            console.log(result[i].name);
                                            console.log(result[i].age);
                                            arr.push({"value": result[i].age,"name":result[i].name});
                                        }
                                    }
                                },
                                error : function(errorMsg) {
                                    alert("不好意思，图表请求数据失败啦!");
                                    myChart.hideLoading();
                                }
                            })
                            return arr;
                        })(function (a, b) { return a.value - b.value; }),
                    roseType: 'radius',
                    label: {
                        normal: {
                            textStyle: {
                                color: 'rgba(255, 255, 255, 0.3)'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            lineStyle: {
                                color: 'rgba(255, 255, 255, 0.3)'
                            },
                            smooth: 0.2,
                            length: 10,
                            length2: 20
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#c23531',
                            shadowBlur: 200,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    },

                    animationType: 'scale',
                    animationEasing: 'elasticOut',
                    animationDelay: function (idx) {
                        return Math.random() * 200;
                    }
                }
            ]
        };
        // 为echarts对象加载数据
        myChart.setOption(option);
    }
);