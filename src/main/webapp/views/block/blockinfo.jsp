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
            var param =${pageContext.request.getParameter("searchStr")};
            $.ajax({
                url: "${pageContext.request.contextPath}/webapp/block/getBlockInfo/" + param,
                async: true,
                success: function (data) {
                    if (data.length == 0) {
                        location.href = "error.jsp?searchStr=" + param;
                    } else {
                        $("#main").css("display", "block");
                        $("#block_hash").text(data.hash);
                        $("#block_number").text(data.number);
                        $("#block_parenthash").text(data.parentHash);
                        $("#block_stateRoot").text(data.stateRoot);
                        $("#block_size").text(data.size);
                        $("#block_transactionsRoot").text(data.transactionsRoot);
                        $("#block_timestamp").text(data.timestamp);
                        $("#block_info").val(JSON.stringify(data, null, 4));
                    }
                }, error: function (result) {
                    location.href = "error.jsp?searchStr=" + param;
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

    <div class="wrap trade-det block-det" id="main" style="display:none;">
        <div class="hd"><h2 class="tit2">区块详情</h2></div>
        <ul class='info-group pb28'>
            <li>
                <label class="item-name">本hash：</label>
                <span class="item-info" id="block_hash"></span>
            </li>
            <li>
                <label class="item-name">上一区块hash：</label>
                <span class="item-info" id="block_parenthash">
                </span>
            </li>
            <li>
                <label class="item-name">账户树hash：</label>
                <span class="item-info" id="block_stateRoot"></span>
            </li>
            <li>
                <label class="item-name">交易集合hash：</label>
                <span class="item-info" id="block_transactionsRoot"></span>
            </li>
            <li>
                <label class="item-name">区块高度：</label>
                <span class="item-info" id="block_number"></span>
            </li>
            <li>
                <label class="item-name">区块长度：</label>
                <span class="item-info" id="block_size"></span>
            </li>
            <li>
                <label class="item-name">生成时间：</label>
                <span class="item-info" id="block_timestamp"></span>
            </li>
            <div class="code">
                <span class="tip">当前浏览代码为：区块详情</span>
                <div id="Canvas" style="word-wrap: break-word;word-break: normal;">
					<textarea id="block_info" readonly style="width: 780px;height: 600px;
                    max-height:780px;max-width: 600px;background-color: rgba(255, 237, 246, 0.18)">

					</textarea>
                </div>
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
