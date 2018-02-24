<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>区块信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
</head>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/webapp/block/getBlockNumber",
            async: false,
            success: function (data) {
                blockNumber = data;
                $("#blockNumber").find("p").text("最新区块：" + blockNumber);
            }
        });
        $.ajax({
            url: "${pageContext.request.contextPath}/webapp/block/getBlockInfo/" + blockNumber,
            async: true,
            success: function (data) {
                $("#example_text").val(JSON.stringify(data, null, 2));
            }
        });
    });
    function reload() {
        location.reload();
    }
</script>
<body>
<div>
    <div id="main">
        <div id="method">
            <span><label><h3>区块信息详情</h3></label></span>
                   <span>
                      URL：<a href="javascript:reload();">根目录/webapp/block/getBlockInfo/{blockNumber}</a>
            返回指定区块的详情.
                   </span>
        </div>
        <div id="param">
            <span><label><h3>传递参数</h3></label></span>
            <p>
                1.参数名称：blockNumber,Number, - 区块编号
            </p>
            <code>返回：JSONObject对象</code>
            <p>
            </p>
        </div>
        <div id="return">
            <span><label><h3>返回对象</h3></label></span>
            <ul>
                <li>difficulty: BigNumber - 设置当前区块的难度,如果难度过大,cpu挖矿就很难.</li>
                <li>extraData: String - 附加信息,随便填,可以填你的个性信息.</li>
                <li>gasLimit: Number - 该值设置对GAS的消耗总量限制.</li>
                <li>gasUsed: Number - 此区块的上的交易总消耗功能.</li>
                <li>String: 32 Bytes - 区块hash值.</li>
                <li>logsBloom: String, 256 Bytes - 区块日志</li>
                <li>miner: String, 20 Bytes - 挖矿受益人地址</li>
                <li>nonce: String, 8 Bytes - 随机数，用于挖矿</li>
                <li>number: Number - 块号.</li>
                <li>parentHash: String, 32 Bytes - 上一个区块的hash值.</li>
                <li>sha3Uncles: String, 32 Bytes - 此块中数据的sha3值.</li>
                <li>size: Number - 区块长度.</li>
                <li>stateRoot: String, 32 Bytes - the root of the final state trie of the block.</li>
                <li>timestamp: Number - 时间戳.</li>
                <li>totalDifficulty: BigNumber - 截止到此区块的难度.</li>
                <li>totalDifficulty: BigNumber - 截止到此区块的难度.</li>
                <li>transactions: Array - 该区块的交易列表.</li>
                <li>transactionsRoot: String, 32 Bytes - the root of the transaction trie of the block.</li>
                <li>uncles: Array - Array of uncle hashes.</li>
            </ul>
        </div>
        <div id="Example">
            <div id="blockNumber">
                <p></p>
            </div>
            <textarea id="example_text" readonly style="width: 70%;height: 50%;
                    max-height: 70%;max-width: 50%;background-color: rgba(255, 237, 246, 0.18)">

            </textarea>
        </div>
    </div>
</div>
</body>
</html>
