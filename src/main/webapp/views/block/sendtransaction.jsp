<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>人保集团区块链浏览器</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <!--[if IE 8]>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ie8.css"><![endif]-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/b.core.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/formatJson.js"></script>
</head>
<body>
<div class="caculate">
    <div class="header-wrap">
        <div class="wrap">
            <a href="${pageContext.request.contextPath}/views/block/index.jsp"><img class="logo"
                                                                                    src="${pageContext.request.contextPath}/images/logo1.png"/></a>
            <form class="searchbar" name="searchForm" method="get" action="" onsubmit="return doSearch();">
                <input class="search" type="text" name="searchStr" id="searchStr" placeholder="请输入交易hash、地址或区块高度"
                       autocomplete="off" value=""/>
                <input class="submit" type="submit" value="搜 索"/>
            </form>
        </div>
    </div>

    <script type="text/javascript">
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
    </script>

    <div class="wrap">
        <form class="searchbar" name="searchForm" method="get" action="" onsubmit="return doSearch();">
            <input class="search" type="text" name="searchStr" id="searchStr" placeholder="请输入发送方地址" autocomplete="off"
                   value=""/>
            <input class="search" type="text" name="searchStr" id="searchStr" placeholder="请输入接收方地址" autocomplete="off"
                   value=""/>
            <input class="search" type="text" name="searchStr" id="searchStr" placeholder="请输入发送数量" autocomplete="off"
                   value=""/>
            <input class="submit" type="submit" value="发 送"/>
        </form>
    </div>
</div>
<footer class="footer">
    <p class="info">Copyright © THE PEOPLE'S INSURANCE COMPANY(GROUP) OF CHINA　LIMITED All Rights Reserved.<br>
        中国人民保险集团股份有限公司 版权所有
    </p>
</footer>
</body>
</html>
