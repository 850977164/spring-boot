<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- 自动刷新设置
    <meta http-equiv="refresh" content="20;url=http://127.0.0.1:18080/ethereum/servlet/StartUpAction">
     -->
    <title>人保集团区块链浏览器</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <!--[if IE 8]>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ie8.css"><![endif]-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/formatJson.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/sockjs.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/stomp.min.js"></script>
</head>
<body>
<div class="caculate">
    <div class="header-wrap">
        <div class="wrap">
            <a href="${pageContext.request.contextPath}/views/block/index.jsp"><img class="logo"
                                                                                    src="${pageContext.request.contextPath}/images/logo1.png"/></a>
            <form class="searchbar" name="searchForm" method="get" target="_blank" action=""
                  onsubmit="return doSearch();">
                <input class="search" type="text" name="searchStr" id="searchStr" placeholder="请输入交易hash、地址或区块高度"
                       autocomplete="off" value=""/>
                <input class="submit" type="submit" value="搜 索"/>
                <%--<input class="type" name="type" type="hidden" value=""/>--%>
            </form>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
//		加载区块信息
            $.ajax({
                url: "${pageContext.request.contextPath}/webapp/block/getBlockInfoByPage/5",
                async: true,
                success: function (data) {
                    var html = "";
                    $.each(data.list, function (i, obj) {
                        html = html + "<li class='list-item second-row'>" +
                                "<ul class='item-wrap'>" +
                                "<li class='item first-col'><a href='${pageContext.request.contextPath}/views/block/blockinfo.jsp?searchStr=" + obj.number + "' target='_blank'>" + obj.number + "</a></li>" +
                                "<li class='item ellipsis second-col'><span class='ellipsis'>" + obj.hash + "</span></li>" +
                                "<li class='item ellipsis third-col'><a class='ellipsis' href='${pageContext.request.contextPath}/views/block/transactions.jsp?searchStr=" + obj.number + "' target='_blank'>" + obj.transactionsRoot + "</a></li>" +
                                "<li class='item fourth-col'>" + obj.timestamp + "</li>"
                                + "</ul></li>";
                    });
                    $("#blockInfo").html(html);
                }
            });
            //加载交易信息
            $.ajax({
                url: "${pageContext.request.contextPath}/webapp/transcation/getTransInfo/5",
                async: true,
                success: function (data) {
                    var html = "";
                    $.each(data.list, function (i, obj) {
                        html = html + "<li class='list-item second-row'>" +
                                "<ul class='item-wrap'>" +
                                "<li class='item first-col'>" + obj.id + "</li>" +
                                "<li class='item second-col'><a class='det-link' href='${pageContext.request.contextPath}/views/block/accountsInfo.jsp?searchStr=" + obj.from + "' target='_blank'>" + obj.from + "</a></li>" +
                                "<li class='item third-col'>" + parseInt(obj.input.substr(-64), 16) + "</li>" +
                                "<li class='item fourth-col'><a class='btn' href='${pageContext.request.contextPath}/views/block/transactionsInfo.jsp?searchStr=" + obj.hash + "' target='_blank'>详情</a></li>"
                                + "</ul></li>";
                    });
                    $("#transcationinfo").html(html);
                }
            });
//		连接websocket服务
            connect();
        });
        function doSearch() {
            var searchStr = $("input[name='searchStr']").val();
            var z = /^[0-9]*$/;
            if (searchStr == "") {
                return false;
            }
            if (z.test(searchStr)) {
                $(".searchbar").attr("action", "blockinfo.jsp")
            } else {
                if (searchStr.length == 66) {
                    $(".searchbar").attr("action", "transactionsInfo.jsp");
                } else if (searchStr.length == 42) {
                    $(".searchbar").attr("action", "accountsInfo.jsp");
                } else {
                    $(".searchbar").attr("action", "error.jsp");
                }
            }
        }
        var stompClient = null;
        function connect() {
            var socket = new SockJS("${pageContext.request.contextPath}/webapp/index-websocket");
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function () {
                stompClient.subscribe("/topic/callback", function (data) {
//				alert(JSON.stringify(JSON.parse(data.body).blocklist),null,2);
                    show(JSON.parse(data.body).blocklist, JSON.parse(data.body).translist);
                })
            })
        }
        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            disconnect();
        }
        function disconnect() {
            if (stompClient != null) {
                stompClient.disconnect();
            }
        }
        function show(blocklist, translist) {
//		alert(blocklist.length+"===="+translist.length);
            var blockhtml = "";
            $.each(blocklist, function (i, obj) {
                blockhtml = blockhtml + "<li class='list-item second-row'>" +
                        "<ul class='item-wrap'>" +
                        "<li class='item first-col'><a href='${pageContext.request.contextPath}/views/block/blockinfo.jsp?searchStr=" + obj.number + "' target='_blank'>" + obj.number + "</a></li>" +
                        "<li class='item ellipsis second-col'><span class='ellipsis'>" + obj.hash + "</span></li>" +
                        "<li class='item ellipsis third-col'><a class='ellipsis' href='${pageContext.request.contextPath}/views/block/transactions.jsp?searchStr=" + obj.number + "' target='_blank'>" + obj.transactionsRoot + "</a></li>" +
                        "<li class='item fourth-col'>" + obj.timestamp + "</li>"
                        + "</ul></li>";
            });
            $("#blockInfo").empty().html(blockhtml);

            var transhtml = "";
            $.each(translist, function (i, obj) {
                transhtml = transhtml + "<li class='list-item second-row'>" +
                        "<ul class='item-wrap'>" +
                        "<li class='item first-col'>" + obj.id + "</li>" +
                        "<li class='item second-col'><a class='det-link' href='${pageContext.request.contextPath}/views/block/accountsInfo.jsp?searchStr=" + obj.from + "' target='_blank'>" + obj.from + "</a></li>" +
                        "<li class='item third-col'>" + parseInt(obj.input.substr(-64), 16) + "</li>" +
                        "<li class='item fourth-col'><a class='btn' href='${pageContext.request.contextPath}/views/block/transactionsInfo.jsp?searchStr=" + obj.hash + "' target='_blank'>详情</a></li>"
                        + "</ul></li>";
            });
            $("#transcationinfo").empty().html(transhtml);
        }
    </script>
    <div class="wrap table-cnt new-block">
        <div class="hd"><h2 class="tit2">区块</h2><span>最新生成的区块</span></div>
        <ul class="list-group">
            <li class="list-item first-row">
                <ul class="item-wrap">
                    <li class="item first-col">区块高度</li>
                    <li class="item second-col">本hash</li>
                    <li class="item third-col">交易集合hash</li>
                    <li class="item fourth-col">生成时间</li>
                </ul>
            </li>
            <li></li>
            <div id="blockInfo">

            </div>
        </ul>
    </div>
    <div class="wrap table-cnt new-trade">
        <div class="hd"><h2 class="tit2">交易</h2><span>最新生成的交易</span></div>
        <ul class="list-group">
            <li class="list-item first-row">
                <ul class="item-wrap">
                    <li class="item first-col">交易序号</li>
                    <li class="item second-col">交易发起方地址</li>
                    <li class="item third-col">交易值</li>
                    <li class="item fourth-col"></li>
                </ul>
            </li>
            <div id="transcationinfo">

            </div>
        </ul>
    </div>
</div>
<footer class="footer">
    <p class="info">Copyright © THE PEOPLE'S INSURANCE COMPANY(GROUP) OF CHINA　LIMITED All Rights Reserved.<br>
        中国人民保险集团股份有限公司 版权所有
    </p>
</footer>
</body>
</html>
