<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="./static/css/font.css">
    <link rel="stylesheet" href="./static/css/xadmin.css">
    <script type="text/javascript" src="./static/js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="./static/lib/layui/layui.js"></script>
    <script type="text/javascript" src="./static/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="x-body layui-anim layui-anim-up" >
    <form id="fixed-information" class="layui-form">
        <div class="layui-form-item">
            <label for="headPhoto" class="layui-form-label">
                头像：
            </label>
            <div class="layui-input-inline">
                <img src=".${user.headPhoto}" id="headPhoto" alt="/" style="width: 150px">
            </div>
            <div class="layui-form-mid layui-word-aux">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="id" class="layui-form-label">
                学号：
            </label>
            <div class="layui-input-inline">
                <input type="text" id="id" name="id" class="layui-input"
                       readonly="readonly" value="${user.id}" style="outline: none">
            </div>
            <div class="layui-form-mid layui-word-aux">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="name" class="layui-form-label">
                姓名：
            </label>
            <div class="layui-input-inline">
                <input type="text" id="name" name="name" class="layui-input"
                       readonly="readonly" height="38px" value="${user.name}" >
            </div>
            <div class="layui-form-mid layui-word-aux">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="institute" class="layui-form-label">
                学院：
            </label>
            <div class="layui-input-inline">
                <input type="text" id="institute" name="institute" class="layui-input"
                       readonly="readonly" height="38px" value="${user.institute}">
            </div>
            <div class="layui-form-mid layui-word-aux">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="psw-no-eidt" class="layui-form-label">
                密码：
            </label>
            <div class="layui-input-inline">
                <input type="password" id="psw-no-eidt" name="psw-no-eidt" class="layui-input"
                       readonly="readonly" height="38px" value="11111111">
            </div>
            <div class="layui-form-mid layui-word-aux" >
                <a href="javascript:void(0)" onclick="editPSW()" style="color: #00A680">编辑</a>
            </div>
        </div>
    </form>
    <form id="editPSW" class="layui-form" style="display:none;" >
        <div class="layui-form-item">
            <label for="old_pass" class="layui-form-label">
                <span class="x-red">*</span>原密码：
            </label>
            <div class="layui-input-inline">
                <input type="password" id="old_pass" name="pass" required="" lay-verify="old_pass"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_pass" class="layui-form-label">
                <span class="x-red">*</span>新密码：
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_pass" name="pass" required="" lay-verify="pass"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                至少8位
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
                <span class="x-red">*</span>确认密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_repass" name="repass" required="" lay-verify="repass"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                至少8位
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
            </label>
            <button class="layui-btn" onclick="" lay-submit="" style="width: 30%">
                修改密码
            </button>
        </div>
    </form>
    <from>
        <div class="layui-form-item">
            <label for="signature" class="layui-form-label">
                个性签名：
            </label>
            <div class="layui-input-inline">
                <textarea id="signature" class="layui-textarea" rows="8" value="" onchange="changeSignature()">${user.signature}</textarea>
            </div>
            <div class="layui-form-mid layui-word-aux" >
                <a href="javascript:void(0)"id="save" onclick="saveSignature()" style="display: none;color: #00A680">保存</a>
            </div>
        </div>
    </from>
</div>

<script>

    layui.use(['form', 'layer','upload'], function () {
        var $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer
            , upload = layui.upload;

        //自定义验证规则
        form.verify({
            old_pass:function(value){
                if(value=="")
                    return '原密码不能为空'
            }
            ,pass: [/(.+){8}$/, '密码必须8位以上']
            , repass: function (value) {
                if ($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            }
        });
        upload.render({
            elem: '#headPhoto'
            , url: './upload.action?type=headPhoto'
            , done: function (res) {
                //上传完毕回调
               $('#headPhoto').attr('src','.'+res.data.src);
                $.ajax({
                    type: 'post',
                    url: './changeHeadPhoto.action',
                    data: {
                        'headPhoto': res.data.src,
                    },
                    dataType: 'json',
                    success: function (data) {
                        var code = data.code;
                        if (code == "0") {
                            layer.alert(data.msg, {icon: 6}, function () {
                                // 获得frame索引
                                var index = parent.layer.getFrameIndex(window.name);
                                //关闭当前frame
                                parent.layer.close(index);
                                reloadUser();
                            });
                        } else  {
                            layer.alert("头像更换异常！", {icon: 5});
                        }
                    }
                });
            }
        });

        //监听提交
        $('#editPSW').submit(function()  {
            $.ajax({
                type: 'post',
                url: './changePassword.action',
                data: {
                    'oldPass': $("#old_pass").val(),
                    'newPass': $("#L_pass").val(),
                },
                dataType: 'json',
                success: function (data) {
                    var code = data.code;
                    if (code == "0") {
                        layer.alert(data.msg, {icon: 6}, function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index);
                            reloadUser();
                        });
                    } else if (code == "200" || code == "500") {
                        $("#old_pass").val("").focus();
                        $("#L_pass").val("");
                        $("#L_repass").val("");
                        layer.alert(data.msg, {icon: 5});
                    }
                }
            });
            return false;
        });
    });
    /*修改密码编辑*/
    function editPSW() {
        var display=document.getElementById("editPSW").style.display;
        if (display==""){
            document.getElementById("editPSW").style.display="none"
        }
        else
            document.getElementById("editPSW").style.display=""
    }
    /*编辑签名后才显示保存按钮*/
    function changeSignature() {
            document.getElementById("save").style.display=""
    }
    function saveSignature(){
        var signature=$('#signature').val();
        $.ajax({
            type: 'post',
            url: './changeSignature.action',
            data: {
                'signature': signature,
            },
            dataType: 'json',
            success: function (data) {
                layer.alert(data.msg, {icon: 6}, function () {
                    // 获得frame索引
                    var index = parent.layer.getFrameIndex(window.name);
                    //关闭当前frame
                    parent.layer.close(index);
                    reloadUser();

                });
            }
        });
    }
    $(function () {
        var userId=<%=session.getAttribute("userID") %>;
        if(userId==""||userId==null)
        {
            alert("当前没有登录，请前往登录");
            window.location.href="./login.action"
        }
        else{
            reloadUser();
        }
    });
    function reloadUser() {
        var userId=<%=session.getAttribute("userID") %>;
        $.ajax({
            type: 'post',
            url: './refreshUser.action',
            data: {
                'userId': userId,
            },
            dataType: 'json',
            success: function (data) {
            }
        });
    }
</script>
</body>

</html>