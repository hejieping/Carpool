<%--
  Created by IntelliJ IDEA.
  User: qi
  Date: 2016/12/15
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="static/js/jquery-1.8.3.min.js"></script>
    <style type="text/css">
        .div_list_item {
            margin-top: 30px;
        }
    </style>
</head>
<body>
<div>
    <div class="div_list_item">
        <div>
            <a href="http://shiyousan.com/post/635623857208296120" target="_blank">什么是CSRF攻击</a>
        </div>
        <div class="bdsharebuttonbox">
            <a class="bds_more" href="#" data-cmd="more" data-id="635623857208296120"></a>
            <a title="分享到QQ空间" class="bds_qzone" href="#" data-cmd="qzone" data-id="635623857208296120"></a>
            <a title="分享到新浪微博" class="bds_tsina" href="#" data-cmd="tsina" data-id="635623857208296120"></a>
            <a title="分享到腾讯微博" class="bds_tqq" href="#" data-cmd="tqq" data-id="635623857208296120"></a>
        </div>
    </div>
    <div class="div_list_item">
        <div>
            <a href="http://shiyousan.com/post/635581829382059548" target="_blank">win7如何卸载IE11</a>
        </div>
        <div class="bdsharebuttonbox">
            <a class="bds_more" href="#" data-cmd="more" data-id="635581829382059548"></a>
            <a title="分享到QQ空间" class="bds_qzone" href="#" data-cmd="qzone" data-id="635581829382059548"></a>
            <a title="分享到新浪微博" class="bds_tsina" href="#" data-cmd="tsina" data-id="635581829382059548"></a>
            <a title="分享到腾讯微博" class="bds_tqq" href="#" data-cmd="tqq" data-id="635581829382059548"></a>
        </div>
    </div>
    <div class="div_list_item">
        <div>
            <a href="http://shiyousan.com/post/635580331379132445" target="_blank">VS重构重命名的快捷键</a>
        </div>
        <div class="bdsharebuttonbox">
            <a class="bds_more" href="#" data-cmd="more" data-id="635580331379132445"></a>
            <a title="分享到QQ空间" class="bds_qzone" href="#" data-cmd="qzone" data-id="635580331379132445"></a>
            <a title="分享到新浪微博" class="bds_tsina" href="#" data-cmd="tsina" data-id="635580331379132445"></a>
            <a title="分享到腾讯微博" class="bds_tqq" href="#" data-cmd="tqq" data-id="635580331379132445"></a>
        </div>
    </div>
</div>
<h3 style="color:#ff0000">本页案例中分享到腾讯微博有问题，可能和腾讯微博停止相关服务有关，这部分就不多做调整，可以选择其他分享平台进行测试</h3>
<script type="text/javascript">
    //全局变量，动态的文章ID
    var ShareId = "";
    //绑定所有分享按钮所在A标签的鼠标移入事件，从而获取动态ID
    $(function () {
        $(".bdsharebuttonbox a").mouseover(function () {
            ShareId = $(this).attr("data-id");
        });
    });

    /*
     * 动态设置百度分享URL的函数,具体参数
     * cmd为分享目标id,此id指的是插件中分析按钮的ID
     *，我们自己的文章ID要通过全局变量获取
     * config为当前设置，返回值为更新后的设置。
     */
    function SetShareUrl(cmd, config) {
        if (ShareId) {
            config.bdUrl = "http://shiyousan.com/post/" + ShareId;
        }
        return config;
    }

    //插件的配置部分，注意要记得设置onBeforeClick事件，主要用于获取动态的文章ID
    window._bd_share_config = {
        "common": {
            onBeforeClick: SetShareUrl, "bdSnsKey": {}, "bdText": ""
            , "bdMini": "2", "bdMiniList": false, "bdPic": "", "bdStyle": "0", "bdSize": "24"
        }, "share": {}
    };
    //插件的JS加载部分
    with (document) 0[(getElementsByTagName('head')[0] || body)
            .appendChild(createElement('script'))
            .src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='
            + ~(-new Date() / 36e5)];
</script>
</body>
</html>
