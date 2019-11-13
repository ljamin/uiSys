<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UISys | 同校活动互动吧</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

   <!-- <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>-->
    <link rel="stylesheet" href="./static/css/font.css">
    <link rel="stylesheet" href="./static/css/xadmin.css">
    <script type="text/javascript" src="./static/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="./static/lib/layui/layui.js"></script>
    <script type="text/javascript" src="./static/js/xadmin.js"></script>

</head>
<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo"><a>UISys | 同校活动互动吧</a></div>
    <div class="left_open">
        <i title="展开左侧栏" class="iconfont">&#xe699;</i>
    </div>
    <ul class="layui-nav right" >
        <li class="layui-nav-item">
            <a href="javascript:"><span class="account"></span></a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a onclick="x_admin_show('个人信息','toPage.action?url=personalInformation.jsp',500,600)" >个人信息</a></dd>
                <dd><a href="logout.action">退出</a></dd>
            </dl>
        </li>
    </ul>

</div>
<!-- 顶部结束 -->

<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:">
                    <!-- <i class="iconfont">&#xe6b8;</i> -->
                    <cite><img src="./img/icon/同校活动.png" height="7%" width="10%">&nbsp;&nbsp;&nbsp;同校活动</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="showNewsByPage.action?typeId=0">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>社团活动</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="showNewsByPage.action?typeId=1">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>社会实践</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="showNewsByPage.action?typeId=2">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>兼职信息</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="showNewsByPage.action?typeId=3">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>失物招领</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="showNewsByPage.action?typeId=4">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>二手交易</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="showNewsByPage.action?typeId=5">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>校友party</cite>
                        </a>
                    </li>

                </ul>
            </li>
            <li>
                <a _href="toPage.action?url=issue.html">
                    <!-- <i class="iconfont">&#xe723;</i> -->
                    <cite><img src="./img/icon/我要发布.png" height="7%" width="10%">&nbsp;&nbsp;&nbsp;我要发布</cite>
                </a>

            </li>
            <li>
                <a _href="toPage.action?url=myNews.jsp">
                    <!-- <i class="iconfont">&#xe723;</i> -->
                    <cite><img src="./img/icon/我发布的.png" height="7%" width="10%">&nbsp;&nbsp;&nbsp;我发布的</cite>
                </a>
            </li>
            <li>
                <a _href="toPage.action?url=myNotices.jsp">
                    <!-- <i class="iconfont">&#xe723;</i> -->
                    <cite><img src="./img/icon/系统通知.png" height="7%" width="10%">&nbsp;&nbsp;&nbsp;消息通知</cite>
                </a>
            </li>
            <c:if test="${roleID==1}">
                <li>
                    <a href="javascript:;">
                        <!-- <i class="iconfont">&#xe6b8;</i> -->
                        <cite><img src="./img/icon/管理员.png" height="7%" width="10%">&nbsp;&nbsp;&nbsp;系统管理</cite>
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <ul class="sub-menu">
                        <li>
                            <a _href="toPage.action?url=adminBulletin.html">
                                <i class="iconfont">&#xe6a7;</i>
                                <cite>发布公告</cite>
                            </a>
                        </li>
                        <li>
                            <a _href="toPage.action?url=adminStudentMsg.html">
                                <i class="iconfont">&#xe6a7;</i>
                                <cite>学生信息维护</cite>
                            </a>
                        </li>
                        <li>
                            <a _href="toPage.action?url=adminUserMsg.html">
                                <i class="iconfont">&#xe6a7;</i>
                                <cite>用户信息维护</cite>
                            </a>
                        </li>
                        <li>
                            <a _href="toPage.action?url=violation.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                                <cite>违规处理</cite>
                            </a>
                        </li>
                        <li>
                            <a _href="toPage.action?url=statistics.html">
                                <i class="iconfont">&#xe6a7;</i>
                                <cite>消息统计</cite>
                            </a>
                        </li>

                    </ul>
                </li>
            </c:if>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>首页公告栏</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='./toPage.action?url=bulletins.html' frameborder="0" scrolling="yes"
                        class="x-iframe"></iframe>
            </div>
        </div>
    </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
    <div class="copyright">Copyright ©UISys</div>
</div>
<!-- 底部结束 -->
<script>
    window.onload = function () {
        $(function() {
            $.ajax({
                type : "get",
                url : "userInfo.action",
                dataType : "text",
                success : function(result) {
                    document.getElementsByClassName("account")[0].innerHTML=result;
                },
                error : function() {
                    alert("当前没有登录，请前往登录");
                    window.location.href="./login.action"
                }
            });
        });
    }

</script>
</body>
</html>