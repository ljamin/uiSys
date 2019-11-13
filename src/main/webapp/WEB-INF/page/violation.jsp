<%--
  Created by IntelliJ IDEA.
  User: qjq
  Date: 2019/4/8
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script type="text/javascript" src="./static/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="./static/lib/layui/layui.js"></script>
    <link rel="stylesheet" href="./static/lib/layui/css/layui.css"  media="all">
</head>
<body>
<div id="outside-title" style="text-align: center;font-size:26px;">
    <span style="text-align: center">举报列表</span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">刷新</i></a>
</div>
<div class="search" style="margin-left: 15px">
    搜索消息ID：
    <div class="layui-inline">
        <input class="layui-input" name="newsID" id="searchId" autocomplete="off">
    </div>
    <button class="layui-btn" data-type="reload">搜索</button>
</div>
<table class="layui-hide" id="allNotices" lay-filter="allNotices" style="margin-left: 2px"></table>
<script>
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#allNotices'
            ,page: true
            ,url:'./allNotices.action'      //获取数据的action
            ,cols: [[
                {field:'id', title:'ID', width:200, fixed: 'left', unresize: true, sort: true}
                ,{field:'content', title:'内容', width:100}
                ,{field:'createtime', title:'创建时间', width:200}
                ,{field:'fromUserId', title:'发起人ID', width:100,templet: '<div><a href="javascript:void(0)" ' +
                        'onclick="userDetail(\'{{d.fromUserId}}\')" class="layui-table-link">{{d.fromUserId}}</a></div>'}
                ,{field:'toUserId', title:'被举报人ID', width:130,templet: '<div><a href="javascript:void(0)" ' +
                        'onclick="userDetail(\'{{d.toUserId}}\')" class="layui-table-link">{{d.toUserId}}</a></div>'}
                ,{field:'toNewsId', title:'消息ID', width:200,templet: '<div><a href="javascript:void(0)" ' +
                        'onclick="detail(\'{{d.toNewsId}}\')" class="layui-table-link">{{d.toNewsId}}</a></div>'}
            ]]
            ,done:function(res){
            }
            ,id:'reload'
            ,skin:  'nob'    //去掉表格线
            /* ,initSort: {
                 field: 'createtime' //排序字段，对应 cols 设定的各字段名
                 ,type: 'desc'
             }*/
        });
        var $ = layui.$, active = {
            reload: function(){
                var searchId = $('#searchId');
                //执行重载
                table.reload('reload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        newsID: searchId.val()
                    }
                });
            }
        };

        $('.search .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
    function userDetail(id) {
        //通过ajax获得news详情
        $(function () {
            $.ajax({
                type: "get",
                url: "getUserDetail.action?id=" + id,
                dataType: 'json',
                success: function (result) {
                    var allContent = '<div style="word-break:break-all; overflow:auto; padding: 50px; ' +
                        'line-height: 22px; background-color: #89bdb4; color: #fff; font-weight: 300;">'
                        + '<form id="fixed-information" class="layui-form">' +
                        '    <div class="layui-form-item">' +
                        '        <label for="headPhoto" class="layui-form-label">' +
                        '            头像：' +
                        '        </label>' +
                        '        <div class="layui-input-inline">' +
                        '            <img src=".'+result.headPhoto+'" id="headPhoto" alt="/" style="width: 150px">' +
                        '        </div>' +
                        '    </div>' +
                        '    <div class="layui-form-item">' +
                        '        <label for="id" class="layui-form-label">' +
                        '            学号：' +
                        '        </label>' +
                        '        <div class="layui-input-inline">' +
                        '            <input type="text" id="id" name="id" class="layui-input"' +
                        '                   readonly="readonly" value="'+result.id+'" style="outline: none">' +
                        '        </div>' +
                        '    </div>' +
                        '    <div class="layui-form-item">' +
                        '        <label for="name" class="layui-form-label">' +
                        '            姓名：' +
                        '        </label>' +
                        '        <div class="layui-input-inline">' +
                        '            <input type="text" id="name" name="name" class="layui-input"' +
                        '                   readonly="readonly" height="38px" value="'+result.name+'" >' +
                        '        </div>' +
                        '    </div>' +
                        '    <div class="layui-form-item">' +
                        '        <label for="institute" class="layui-form-label">' +
                        '            学院：' +
                        '        </label>' +
                        '        <div class="layui-input-inline">' +
                        '            <input type="text" id="institute" name="institute" class="layui-input"' +
                        '                   readonly="readonly" height="38px" value="'+result.institute+'">' +
                        '        </div>' +
                        '    </div>' +
                        '</form>'
                    layer.open({
                        type: 1
                        , title: result.title //标题栏
                        , closeBtn: true
                        , area: '600px;'
                        , id: 'LAY_layuipro' //设定一个id，防止重复弹出
                        , btn: ['知道了']
                        , btnAlign: 'c'
                        , moveType: 1 //拖拽模式，0或者1
                        , content: allContent + '</div>'
                        , success: function (layero) {
                            var btn = layero.find('.layui-layer-btn');
                        }
                    });
                },
                error: function () {
                    alert("请求个人信息失败");
                }
            });
        });
    }
    function detail(id){
        $(function () {
            $.ajax({
                type: "get",
                url: "getNewsDetail.action?id=" + id,
                dataType: 'json',
                success: function (result) {
                    var ncontent = result.content;
                    var date = result.createtime;
                    var allContent = '<div style="word-break:break-all; overflow:auto; padding: 50px; ' +
                        'line-height: 22px; background-color: #89bdb4; color: #fff; font-weight: 300;">'
                        + ncontent
                    if (result.picPath != "") {
                        allContent = allContent + '<br/><br/><img src=".' + result.picPath + '"style="width: 200px;height: 200px"/>'
                    }
                    layer.open({
                        type: 1
                        , title: result.title //标题栏
                        , closeBtn: false
                        , area: '600px;'
                        , id: 'LAY_layuipro' //设定一个id，防止重复弹出
                        , btn: ['知道了']
                        , btnAlign: 'c'
                        , moveType: 1 //拖拽模式，0或者1
                        , content: allContent + '</div>'
                        , success: function (layero) {
                            var btn = layero.find('.layui-layer-btn');
                        }
                    });
                },
                error: function () {
                    alert("请求news失败");
                }
            });
        });
    }


    layui.use('upload', function() {
        var $ = layui.jquery
            , upload = layui.upload;
        upload.render({
            elem: '#chooseFile'
            , url: './upload.action'
            , auto: false
            ,accept:'file'
            ,exts:"xlsx|xls"
            , bindAction: '#uploadFile'
            , done: function (res) {
                layer.msg("上传成功，请刷新",{time: 3000, icon:1,anim: 6})
            }
        });
    });

</script>
</body>
</html>
