<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script type="text/javascript" src="./static/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="./static/lib/layui/layui.js"></script>
    <link rel="stylesheet" href="./static/lib/layui/css/layui.css"  media="all">
</head>
<body>
<div id="outside-title" style="text-align: center;font-size:26px;">
    <span style="margin-bottom: 15px">我发布的</span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>

</div>
<table class="layui-hide" id="myNews" lay-filter="myNews"></table>
<!-- 表格放置的地方id lay-filter-->
<script id="toolbar" type="text/html">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#myNews'
            ,url:'./myNews.action'      //获取数据的action
            ,cols: [[
                 {type: 'numbers', title: '', width: 60 , fixed: 'left' }
                ,{field:'id', title:'ID', width:180}
                ,{field:'title', title:'标题', width:320,templet: '<div><a href="javascript:void(0)" ' +
                        'onclick="detail(\'{{d.id}}\')" class="layui-table-link">{{d.title}}</a></div>'}
                ,{field:'createtime', title:'发布时间', width:200,sort: true}
                ,{field:'likesnum', title:'点赞数', width:100}
                ,{field:'commentsnum', title:'评论数', width:100}
                ,{field:'againstnum', title:'举报数', width:100}
                ,{field:'right', title:'操作', width:80,toolbar: '#toolbar'}
            ]]
            ,success:function(res){
                if(res.code=="500"){
                    alert(res.msg);
                    window.location.href = res.url;
                }
            }
            ,page: true
            ,skin:  'nob'    //去掉表格线
            ,initSort: {
                field: 'createtime' //排序字段，对应 cols 设定的各字段名
                ,type: 'desc' //排序方式 : 降序
            }
        });
        //监听事件
        table.on('tool(myNews)', function(obj){ //注：tool是工具条事件名
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            if(layEvent === 'del'){ //删除
                layer.confirm('真的要删除吗？', function(index){
                    layer.close(index);
                    //向服务端发送删除指令
                    $.ajax({
                        type: 'post',
                        url: './deleteNews.action',
                        data: {
                            'id': data.id,
                        },
                        dataType: 'json',
                        success: function (data) {
                            var code = data.code;
                            if (code == "0") {
                                layer.msg(data.msg, {time: 3000, icon:1,anim: 6});
                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存

                            } else  {
                                layer.msg("删除失败",{time: 3000, icon:2,anim: 6})
                            }
                        }
                    });
                })
            }
        });
    });
    function detail(id){
        //通过ajax获得news详情
        $(function() {
            $.ajax({
                type : "get",
                url : "getNewsDetail.action?id="+id,
                dataType : 'json',
                success : function(result) {
                    var ncontent=result.content;
                    var date=result.createtime;
                    var allContent='<div style="word-break:break-all; overflow:auto; padding: 50px; ' +
                        'line-height: 22px; background-color: #89bdb4; color: #fff; font-weight: 300;">'
                        +ncontent
                    if(result.picPath!=""){
                        allContent=allContent+'<br/><br/><img src=".'+result.picPath+'"style="width: 200px;height: 200px"/>'
                    }
                    layer.open({
                        type: 1
                        ,title:  result.title //标题栏
                        ,closeBtn: false
                        ,area: '600px;'
                        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                        ,btn: ['知道了']
                        ,btnAlign: 'c'
                        ,moveType: 1 //拖拽模式，0或者1
                        ,content: allContent+'</div>'
                        ,success: function(layero){
                            var btn = layero.find('.layui-layer-btn');
                        }
                    });
                },
                error : function() {
                    alert("请求news失败");
                }
            });
        });
    }
</script>

</body>
</html>
