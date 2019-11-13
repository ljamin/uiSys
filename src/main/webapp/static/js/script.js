window.onload = function () {
    load();
}
function load(){
    var list = document.getElementById("list");
    var boxs = list.children;
    var timer;
    function remove(node) {
        var roleID=document.getElementById("list").getAttribute("roleID");
        var newsId=node.id;
        if(roleID==1){//用户为管理员则删除此消息及对应评论、赞、举报
            var answer =confirm("你确定要删除这条消息吗?对应的评论、赞、举报也将删除。");
            if(answer){
                $.ajax({
                    type:'post',
                    url:'./deleteNews.action',
                    data:{
                        'newsId':newsId,
                    },
                    success:function (data) {
                        node.parentNode.removeChild(node);
                        layer.msg(data.msg, {time: 3000, icon:1,anim: 6});
                    }
                });
            }
        }else{
            node.parentNode.removeChild(node);
        }

    }
    // 删除评论节点
    function removeCommentNode (node,id) {
        var box=node.parentNode.parentNode.parentNode;
        var newsId=box.id;
        $.ajax({
            type: 'post',
            url: 'deleteComments.action',
            data: {
                'id': id,
                'toNewsId':newsId
            },
            dataType: 'json',
            success: function (data) {
                layer.msg(data.msg, {time: 3000, icon:1,anim: 6});
                var numberNode=box.getElementsByClassName("commentsNum")[0];
                numberNode.innerHTML=parseInt(numberNode.getAttribute("Num"))-1
                numberNode.setAttribute("Num",parseInt(numberNode.getAttribute("Num"))-1)
            }
        });
        node.parentNode.removeChild(node);
    }
    function againstBox(box,el){
        var userId=document.getElementById("list").getAttribute("userID");
        var newsId=box.id;
        var boxId=box.id;
        var toUserId=document.getElementById(boxId).getAttribute("userID");
        var answer = confirm("确定要实名举报吗？举报无法撤销");
        if(answer){
            $.ajax({
                type:'post',
                url:'./againstNews.action',
                data:{
                    'newsId':newsId,
                    'userId':userId,
                    'toUserId':toUserId
                },
                success:function (data) {
                    layer.msg(data.msg, {time: 3000, icon:1,anim: 6});
                    var numberNode=box.getElementsByClassName("againstNum")[0];
                    numberNode.innerHTML=parseInt(numberNode.getAttribute("Num"))+1;
                    numberNode.setAttribute("Num",parseInt(numberNode.getAttribute("Num"))+1);
                }
            });
        }

    }

    // 赞消息
    function pariseBox (box, el) {
        var pariseElement = box.getElementsByClassName("praises-total")[0];
        var oldTotla = parseInt(pariseElement.getAttribute("total"));
        var txt = el.innerHTML;
        var newTotal;
        var fromUserId=document.getElementById("list").getAttribute("userID");
        var newsId=box.id;
        newTotal = oldTotla + 1;
        if (txt == "赞") {
            $.ajax({
                type: 'post',
                url: 'addGreat.action',
                data: {
                    'fromUserId': fromUserId,
                    'newsId':newsId
                },
                dataType: 'json',
                success: function (data) {
                    if(data.code == "200")
                    {
                        newTotal = oldTotla;
                        oldTotla=oldTotla-1;
                        pariseElement.innerHTML = (newTotal == 1) ? "我觉得很赞" : "我和" + oldTotla + "个人觉得很赞"
                    }else{
                        pariseElement.innerHTML = (newTotal == 1) ? "我觉得很赞" : "我和" + oldTotla + "个人觉得很赞"
                    }
                    layer.msg(data.msg, {time: 3000, icon:1,anim: 6});
                }
            });
            el.innerHTML = "取消赞"
        } else {
            newTotal = oldTotla - 1;
            pariseElement.innerHTML = (newTotal == 0) ? "" : newTotal + "个人觉得很赞"
            el.innerHTML = "赞"
            $.ajax({
                type: 'post',
                url: 'deleteGreat.action',
                data: {
                    'fromUserId': fromUserId,
                    'newsId':newsId
                },
                dataType: 'json',
                success: function (data) {
                    layer.msg(data.msg, {time: 3000, icon:1,anim: 6});

                }
            });
        }
        pariseElement.setAttribute("total", newTotal);
        pariseElement.style.display = (newTotal == 0) ? "none" : "block"
    }

    // 格式化时间
    function getTime () {
        var time = new Date();
        var y = time.getFullYear();
        var m = time.getMonth() + 1;
        var d = time.getDate();
        var h = time.getHours();
        var mi = time.getMinutes();
        m = m < 10 ? "0" + m : m
        d = d < 10 ? "0" + d : d
        h = h < 10 ? "0" + h : h
        mi = mi < 10 ? "0" + mi : mi
        return y + "-" + m + "-" + d + " " + h + ":" + mi;
    }

    // 发表评论
    function replayBox (box) {
        var list = box.getElementsByClassName('comment-list')[0];
        var textarea = box.getElementsByClassName('comment')[0];
        var newComment=textarea.value;
        var li = document.createElement('div');
        var fromUserId=document.getElementById("list").getAttribute("userID");
        var headPhoto=document.getElementById("list").getAttribute("headPhoto");
        var createtime=getTime();
        var boxId=box.id;
        var toUserId=document.getElementById(boxId).getAttribute("userID");
        //添加评论到后台
        $.ajax({
            type: 'post',
            url: 'addComments.action',
            data: {
                'content': textarea.value,
                'fromUserId':fromUserId,
                'toNewsId':box.id,
                'toUserId':toUserId,
                'createtime':createtime
            },
            dataType: 'json',
            success: function (data) {
                if(data.code=='0'){
                    li.className = 'comment-box clearfix';
                    li.setAttribute('user', 'self');
                    li.innerHTML =
                        '<img class="myhead" src=".'+headPhoto+'" alt="./img/head/default.jpg""/>' +
                        '<div class="comment-content">' +
                        '<p class="comment-text"><span class="user">我：</span>' + newComment + '</p>' +
                        '<p class="comment-time">' +
                        createtime +
                        '<a href="javascript:;" class="comment-operate" id="'+data.data+'">删除</a>' +
                        '</p>' +
                        '</div>'
                    list.appendChild(li);
                    layer.msg(data.msg, {time: 3000, icon:1,anim: 6});
                    var numberNode=box.getElementsByClassName("commentsNum")[0];
                    numberNode.innerHTML=parseInt(numberNode.getAttribute("Num"))+1
                    numberNode.setAttribute("Num",parseInt(numberNode.getAttribute("Num"))+1)
                }else{
                    layer.msg(data.msg, {time: 3000, icon:2,anim: 6});
                }
            }
        });
        textarea.value = '';
        textarea.onblur();
    }

    // 利用 className 实现每条分享 div 容器事件代理
    for (var i = 0; i < boxs.length; i++) {
        // 点击
        boxs[i].onclick = function (e) {
            e = e || window.event;
            var el = e.srcElement;
            var id=el.id;
            switch (el.className) {
                case "close":
                    remove(el.parentNode);
                    break;
                // 赞
                case "praise":
                    pariseBox(el.parentNode.parentNode.parentNode, el);
                    break;
                // 评论
                case "btn":
                    replayBox(el.parentNode.parentNode.parentNode);
                    break;
                // 举报
                case "against":
                    againstBox(el.parentNode.parentNode.parentNode);
                    break;
                // 删除评论
                case "comment-operate":
                    removeCommentNode(el.parentNode.parentNode.parentNode,id);
                    break;
            }
        }

        // 输入框
        var textarea = boxs[i].getElementsByClassName("comment")[0];

        // 输入框获取焦点事件
        textarea.onfocus = function () {
            this.parentNode.className = "text-box text-box-on";
            this.value = this.value == "评论…" ? "" : this.value;
            this.onkeyup();
        }


        // 输入框失去焦点事件
        textarea.onblur = function () {
            var _this = this;
            if (_this.value == "") {
                timer = setTimeout(function () {
                    _this.parentNode.className = "text-box";
                    _this.value = "评论…";
                }, 300)

            }
        }

        // 键盘事件
        textarea.onkeyup = function (e) {
            var len = this.value.length;
            var p = this.parentNode;
            var btn = p.children[1];
            var word = p.children[2];
            if (len == 0 || len > 140) {
                btn.className = "btn btn-off"
            } else {
                btn.className = "btn";
            }
            word.innerHTML = len + "/140"
        }

    }
}