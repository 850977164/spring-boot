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
        $(function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/webapp/accounts/getTransactionCount/${pageContext.request.getParameter("searchStr")}",
                async: true,
                success: function (data) {
                    if (data.length == 0) {
                        location.href = "error.jsp?searchStr=${pageContext.request.getParameter("searchStr")}";
                    } else {
                        $("#main").css("display", "block");
                        $("#account_address").text(data.address);
                        $("#account_transCount").text(data.count);
                        var html = "";
                        if (data.list.length > 0) {
                            $.each(data.list, function (i, obj) {
                                html = html + "<li class='list-item second-row'>" +
                                        "<ul class='item-wrap'><li class='item first-col'>" + obj.blockNumber +
                                        "</li><li class='item second-col'>" + obj.hash + "</li></ul>"

                            })
                        }
                        $("#Canvas").html(html);
                    }
                }, error: function (result) {
                    location.href = "error.jsp?searchStr=${pageContext.request.getParameter("searchStr")}";
                }
            });
            $.ajax({
                url: "${pageContext.request.contextPath}/webapp/accounts/getbalance/${pageContext.request.getParameter("searchStr")}",
                async: true,
                success: function (data) {
                    $("#balance_address").text(data);

                }
            });
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
    </script>

    <div class="wrap trade-det" id="main" style="display: none;">
        <div class="hd"><h2 class="tit2">账户详情</h2></div>
        <div class="trade-cnt">
            <ul class='info-group'>
                <li>
                    <label class="item-name">账户地址：</label>
                    <span class="item-info det-link" id="account_address"></span>
                </li>
                <li>
                    <label class="item-name">资产情况：</label>
                    <span class="item-info det-link" id="balance_address"></span>
                </li>
                <li>
                    <label class="item-name">发起交易笔数：</label>
                    <span class="item-info det-link" id="account_transCount"></span>
                </li>
                <span class="btn show-code J-showCode">浏览代码 <i class="iconfont icon-xia"></i></span>
            </ul>
            <div class="code fold">
                <span class="tip">当前浏览代码为：账户详情</span>
                <div class="wrap table-cnt new-trade">
                    <ul class="list-group">
                        <li class="list-item first-row">
                            <ul class="item-wrap">
                                <li class="item first-col">交易存储区块</li>
                                <li class="item second-col">交易hash</li>
                            </ul>
                        </li>
                        <div id="Canvas"></div>
                    </ul>
                </div>

            </div>
        </div>
    </div>
</div>
<footer class="footer">
    <p class="info">Copyright © THE PEOPLE'S INSURANCE COMPANY(GROUP) OF CHINA　LIMITED All Rights Reserved.<br>
        中国人民保险集团股份有限公司 版权所有
    </p>
</footer>
</body>
</html>

<script type="text/javascript">
</script>
