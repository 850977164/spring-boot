<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>api</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
</head>
<body>
<div style="margin:0 auto;">
    <div id="menu" style="width:20%;height:100%;background-color:silver;float:left;border: 1px solid;">
        <%--<ul class="nav-first">--%>
        <%--<li><a id="item-1">调用方法</a>--%>
        <%--<ul class="nav-second fold ">--%>
        <%--<li><a href="${pageContext.request.contextPath}/webapp/transcation/getTransInfo" target="main"><<<<<1>>>>>></a>></li>--%>
        <%--<li><a href="${pageContext.request.contextPath}/webapp/transcation/getTransInfo/1" target="main">交易信息查询(全部)</a></li>--%>
        <%--<li><a>区块信息查询</a></li>--%>
        <%--</ul>--%>
        <%--</li>--%>
        <%--</ul>--%>
    </div>
    <div style="width:79%;height:100%;background-color:silver;float:left;border: 1px solid">
        <iframe name="main" style="width: 100%;height: 100%;">
        </iframe>
    </div>
    <div style="clear:both;"></div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/webapp/menu/getMenuInfo",
            async: true,
            success: function (data) {
                var json_obj = data;
                var html = "<div><ul class=\"nav-first\"><li><a href='javascript:void(0)' onclick='toggle(this);'>" + json_obj.name + "</a>" +
                        "<ul class=\"nav-second \">";
                $.each(json_obj.method, function (i, obj) {
                    html = html + "<li><a href=\"${pageContext.request.contextPath}" + obj.url.trim() + "\" target='main'>" + obj.urlName + "</a></li>"
                });
                html = html + "</ul></li></ul></div>";
                var html2 = html + "<div><ul class=\"nav-first\"><li><a href='javascript:void(0)' onclick='toggle(this);'>" + json_obj.projectName + "</a>" +
                        "<ul class=\"nav-second \">";
                $.each(json_obj.project, function (i, obj) {
//                    alert(Object.prototype.toString.call(obj.titleProject));
                    if (Object.prototype.toString.call(obj.titleProject) == "[object String]") {
                        html2 = html2 + "<li><a href=\"${pageContext.request.contextPath}" + obj.titleProject.trim() + "\" target='main'>" + obj.titlename + "</a></li>"
                    }
                    if (Object.prototype.toString.call(obj.titleProject) == "[object Array]") {
                        html2 = html2 + "<li><a href='javascript:void(0)' onclick='toggle(this);'>" + obj.titlename + "</a>" +
                                "<ul class=\"nav-second \">";
                        $.each(obj.titleProject, function (i, obj) {
                            html2 = html2 + "<li><a href=\"${pageContext.request.contextPath}" + obj.url.trim() + "\" target='main'>" + obj.name + "</a></li>"
                        });
                        html2 = html2 + "</ul></li>";
                    }
                });
                html2 = html2 + "</ul></li></ul></div>";
                $("#menu").html(html2);
            }
        })
    });
    function toggle(obj) {
        $(obj).parent().find(".nav-second").slideToggle(500);
    }

    function load(url) {
        document.getElementsByTagName("iframe")[0].src = url;
    }
</script>
</html>
