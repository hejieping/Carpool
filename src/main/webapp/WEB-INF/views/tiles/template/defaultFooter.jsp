<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/6
  Time: 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%--<link rel="stylesheet" type="text/css" href="/static/style/home.css">--%>

<%--<footer id="" class="ui-footer ui-border-t hidden-md hidden-lg" style="z-index:2">--%>
    <%--<ul class="clearfix">--%>
        <%--<li>--%>
            <%--<a href="/home" class="on"><i class="ui-icon-home"></i>--%>
                <%--<p>首页</p></a>--%>
        <%--</li>--%>
        <%--<li>--%>
            <%--<a href="car.html"><i class="ui-icon-search" style="font-size:42px;"></i>--%>
                <%--<p>约车</p></a>--%>
        <%--</li>--%>
        <%--<li>--%>
            <%--<a href="user.html"><i class="ui-icon-personal"></i>--%>
                <%--<p>我的</p></a>--%>
        <%--</li>--%>
    <%--</ul>--%>
<%--</footer>--%>
<div class="row text-center footer hidden-md hidden-lg navbar-fixed-bottom" style="z-index:2; background: white !important;border: 0 solid lightgray;border-top-width: 1px;">
    <div class="container">
        <ul class="clearfix" style="">
            <li>
                <a href="/home" class="on"><i class="fa fa-home"></i>
                    <p>首页</p></a>
            </li>
            <li>
                <a href="/room/create"><i class="fa fa-plus"></i>
                    <p>新建</p></a>
            </li>
            <li>
                <a href="/room/select"><i class="fa fa-search"></i>
                    <p>加入</p></a>
            </li>
            <li>
                <a href=""><i class="fa fa-user"></i>
                    <p>我的</p></a>
            </li>
        </ul>
    </div>
</div>
<style type="text/css">
    .container .clearfix li
    {
        padding-top: 18px;
        display: table-cell;
        width: 723px;
        text-align: center;
        vertical-align: middle;
    }
</style>