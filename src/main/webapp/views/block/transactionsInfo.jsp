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
                url: "${pageContext.request.contextPath}/webapp/transcation/getTransInfoByHash/${pageContext.request.getParameter("searchStr")}",
                async: true,
                success: function (data) {
                    if (data.length == 0) {
                        location.href = "error.jsp?searchStr=${pageContext.request.getParameter("searchStr")}";
                    } else {
                        $("#main").css("display", "block");
                        $("#trans_hash").text(data.hash);
                        $("#trans_index").text(data.id);
                        $("#trans_from").text(data.from);
                        if (data.to == '' || data.to == 'null') {
                            $("#item1").attr("style", "dispaly:block");
                            $("#item1_from").text(data.from);
                            $("#item1_trans_info").val(JSON.stringify(data, null, 2));
                        } else {
                            $("#item2").attr("style", "dispaly:block");
//						$("#item2_value").text(data.value);
                            $("#item2_value").text(parseInt(data.input.substr(-64), 16));
                            $("#item2_from1").text(data.from);
                            $("#item2_from2").text(data.from);
                            $(".addr-from").text(data.from);
                            $(".addr-from").attr("href", "${pageContext.request.contextPath}/views/block/accountsInfo.jsp?searchStr=" + data.from);
                            $(".addr-to").text("0x"+data.input.substr(-104,40));
                            $("#item2_trans_info").val(JSON.stringify(data, null, 2));
                        }
                    }
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
        <div class="hd"><h2 class="tit2">交易详情</h2></div>
        <ul class='info-group pb28'>
            <li>
                <label class="item-name">交易hash：</label>
                <span class="item-info" id="trans_hash"></span>
            </li>
            <li>
                <label class="item-name">交易序号：</label>
                <span class="item-info" id="trans_index"></span>
            </li>
            <li>
                <label class="item-name">交易发起方地址：</label>
                <span class="item-info" id="trans_from"></span>
            </li>
        </ul>
        <div class="trade-cnt">
            <div id="item1" style="display: none">
                <h3 class="tit3">创建账户</h3>
                <ul class='info-group info-group-striped'>
                    <li>
                        <label class="item-name">账户地址：</label>
                        <span class="item-info" id="item1_from"></span>
                    </li>
                </ul>
                <div class="code">
                    <span class="tip">当前浏览代码为：创建账户</span>
                    <div id="Canvas">
	            		<textarea id="item1_trans_info" readonly style="width: 780px;height: 600px;
                    max-height:780px;max-width: 600px;background-color: rgba(255, 237, 246, 0.18)">
					</textarea>
                    </div>
                </div>
            </div>
            <div id="item2" style="display: none">
                <h3 class="tit3">转账</h3>
                <ul class='info-group info-group-striped'>
                    <li>
                        <label class="item-name">转账资产数量：</label>
                        <span class="item-info" id="item2_value"></span>
                    </li>
                    <li>
                        <label class="item-name">资产名称：</label>
                        <span class="item-info" id="item2_from1"></span>
                    </li>
                    <li>
                        <label class="item-name">资产发行方地址：</label>
                        <span class="item-info" id="item2_from2"><a class="det-link" target="_blank"></a></span>
                    </li>
                </ul>
                <div class="addr">
                    <a class="addr-from" target="_blank"></a>
                    <i class="iconfont icon-rarrow"></i>
                    <a class="addr-to" target="_blank"></a>
                </div>
                <div class="code">
                    <span class="tip">当前浏览代码为：转账</span>
                    <div id="Canvas">
                        <div id="Canvas">
	            		<textarea id="item2_trans_info" readonly style="width: 780px;height: 400px;
                    max-height:780px;max-width: 600px;background-color: rgba(255, 237, 246, 0.18)">
					</textarea>
                        </div>
                    </div>
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

