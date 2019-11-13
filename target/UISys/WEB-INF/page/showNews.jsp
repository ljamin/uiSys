<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qjq
  Date: 2019/3/4
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="./static/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="./static/js/script.js"></script>
    <script type="text/javascript" src="./static/lib/layui/layui.js"></script>
    <script type="text/javascript" src="./static/lib/layui/layui.all.js"></script>
    <link rel="stylesheet" href="./static/lib/layui/css/layui.css" media="all">
    <script type="text/javascript" src="./static/js/xadmin.js"></script>
    <link rel="stylesheet" href="./static/css/qzone.css">
    <link rel="stylesheet" href="./static/css/font.css">
    <link rel="stylesheet" href="./static/css/xadmin.css">
    <link rel="stylesheet" href="./static/css/bootstrap.css">
</head>
<body>
<a id="refresh" class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
   href="javascript:location.replace(location.href);" title="刷新">
    <i class="layui-icon" style="line-height:30px">刷新</i>
</a>

<div id="list" userID="${userID}" headPhoto="${user.headPhoto}" totalPage="${page.totalPage}" roleID="${roleID}">
    <c:forEach var="newsVo" items="${newsVoList}" begin="${page.begin}" end="${page.begin+page.pageSize-1}">
        <div class="box clearfix" id="${newsVo.id}" userID="${newsVo.user.id}">
            <a class="close" href="javascript:;">×</a>
            <img class="head" src=".${newsVo.user.headPhoto}" alt="./img/head/default.jpg"/>
            <div class="content">
                <div class="main">
                    <p class="txt" style="word-break:break-all;overflow:auto; ">
                        <span class="user">${newsVo.user.name}:</span><br/><b>#${newsVo.title}</b>
                    <p>${newsVo.content}</p>
                    </p>
                    <c:if test="${newsVo.picPath!=''}">
                        <img class="pic" id="newsImg" src=".${newsVo.picPath}" alt=""/>
                    </c:if>
                </div>
                    <%--onclick="against(${newsVo.id})"--%>
                <div class="info clearfix">
                    <span class="time">${newsVo.createtime}</span>
                    <span style="padding-left: 28px">评论</span>（<span class="commentsNum"
                                                                     Num="${newsVo.commentsnum}">${newsVo.commentsnum}</span>）
                    <a class="against" href="javascript:;">举报</a>（<span class="againstNum"
                                                                        Num="${newsVo.againstnum}">${newsVo.againstnum}</span>）
                    <a class="praise" href="javascript:;">赞</a>
                </div>
                <div class="praises-total" total="${newsVo.likesnum}">${newsVo.likesnum}个人觉得很赞</div>
                <div class="comment-list">
                    <c:forEach var="comments" items="${newsVo.comments}">
                        <c:choose>
                            <c:when test="${comments.fromUserId==userID}">     <%--当评论的人是自己时，可以删除--%>
                                <div class="comment-box clearfix" user="self">
                                    <img class="myhead" src=".${user.headPhoto}" alt="./img/head/default.jpg"/>
                                    <div class="comment-content">
                                        <p class="comment-text" style="word-break:break-all;overflow:auto;"><span
                                                class="user">我：</span>${comments.content}</p>
                                        <p class="comment-time">
                                                ${comments.createtime}
                                            <a href="javascript:;" class="comment-operate" id="${comments.id}">删除</a>
                                        </p>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="comment-box clearfix" user="other">
                                    <div class="comment-content">
                                        <p class="comment-text"><span
                                                class="user">${comments.fromUserId}:</span>${comments.content}</p>
                                        <p class="comment-time">
                                                ${comments.createtime}
                                        </p>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
                    <%--每条消息评论不可超过20条--%>
                <div class="text-box">
                    <textarea class="comment" autocomplete="off">评论…</textarea>
                    <c:if test="${newsVo.commentsnum<20}">
                        <button class="btn ">评 论</button>
                        <span class="word"><span class="length">0</span>/140</span>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>
    <div id="more" style="width: 100%;text-align: center;font-size: 20px;background-color: rgb(247, 247, 247)">
        <a href="javascript:;" onclick="pageAction()" id="get_more">点击加载更多</a>
    </div>
</div>


<script type="text/javascript" language="javascript">

    var currentPage = 1;
    var totalPage = document.getElementById("list").getAttribute("totalPage");
    if (currentPage == totalPage) {
        document.getElementById("more").innerText = "已无更多数据";
        document.getElementById("more").removeChild(document.getElementById("more").childNodes[0]);
    }
    function pageAction() {
        $.ajax({
            type: 'post',
            url: './changePage.action',
            data: {
                'currentPage': currentPage + 1
            },
            success: function (data) {
                var s = ''
                var headPhoto = document.getElementById("list").getAttribute("headPhoto");
                $.each(data.data, function (k, v) {
                    s = '        <div class="box clearfix" id="'+v.id+'"userID="'+v.user.id+'" >' +
                        '            <a class="close" href="javascript:;">×</a>' +
                        '            <img class="head" src=".'+v.user.headPhoto+'" alt="./img/head/default.jpg" />' +
                        '            <div class="content">' +
                        '                <div class="main" >' +
                        '                    <p class="txt" style="word-break:break-all;overflow:auto; ">' +
                        '                        <span class="user">'+v.user.name+':</span><br/><b>#'+v.title+'</b>' +
                        '                        <p >'+v.content+'</p>' +
                        '                    </p>'
                    if (v.picPath != '') {
                        s += ' <img class="pic" id="newsImg" src=".'+v.picPath+'" alt=""/>'
                    }
                    s += '                </div>' +
                        '                <div class="info clearfix">' +
                        '                    <span class="time">'+v.createtime+'</span>' +
                        '                    <span style="padding-left: 28px">评论</span>（<span class="commentsNum" Num="'+v.commentsnum+'">'+v.commentsnum+'</span>）' +
                        '                    <a class="against" href="javascript:;" >举报</a>（<span class="againstNum" Num="'+v.againstnum+'">'+v.againstnum+'</span>）' +
                        '                    <a class="praise" href="javascript:;">赞</a>' +
                        '                </div>' +
                        '                <div class="praises-total" total="'+v.likesnum+'" >'+v.likesnum+'个人觉得很赞</div>' +
                        '                <div class="comment-list">'
                    $.each(v.comments, function (k, c) {
                        if (c.fromUserId == document.getElementById("list").getAttribute("userID")) {
                            s += '                                <div class="comment-box clearfix" user="self">' +
                                '                                    <img class="myhead" src=".'+headPhoto+'" alt="./img/head/default.jpg"/>' +
                                '                                    <div class="comment-content">' +
                                '                                        <p class="comment-text" style="word-break:break-all;overflow:auto;" ><span class="user">我：</span>'+c.content+'</p>' +
                                '                                        <p class="comment-time">' + c.createtime+
                                '                                            <a href="javascript:;" class="comment-operate" id="'+c.id+'">删除</a>' +
                                '                                        </p>' +
                                '                                    </div>' +
                                '                                </div>';
                        }
                        else {
                            s += '                                <div class="comment-box clearfix" user="other">' +
                                '                                    <div class="comment-content">' +
                                '                                        <p class="comment-text"><span class="user">'+c.fromUserId+':</span>'+c.content+'</p>' +
                                '                                        <p class="comment-time">' + c.createtime +
                                '                                        </p>' +
                                '                                    </div>' +
                                '                                </div>';
                        }
                    });

                    s += '                </div>' +
                        '                <div class="text-box">' +
                        '                    <textarea class="comment" autocomplete="off" >评论…</textarea>'
                    if (v.commentsnum < 20) {
                        s += '                        <button class="btn " >评 论</button>' +
                            '                        <span class="word"><span class="length">0</span>/140</span>';
                    }
                    s += '                </div>' +
                        '            </div>' +
                        '        </div>'
                    $('#more').before(s);
                    s = '';
                });

                currentPage++;
                if (currentPage == totalPage) {
                    document.getElementById("more").innerText = "已无更多数据";
                    document.getElementById("more").removeChild(document.getElementById("more").childNodes[0]);
                }
                load();

            }
        });
    }

</script>
</body>

</html>
