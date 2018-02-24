<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取交易详情(分页)</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
</head>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/webapp/transcation/getTransInfo/3",
            async: true,
            success: function (data) {
                $("#example_text").val(JSON.stringify(data, null, 2));
            }
        });
    });
</script>
<body>
<div>
    <div id="main">
        <div id="method">
            <span><label><h3>分页获取交易信息</h3></label></span>
                   <span>
                      URL：<a href="${pageContext.request.contextPath}/webapp/transcation/getTransInfo/3">根目录/webapp/getTransInfo/{pagesize}</a>
            返回pagesize条交易信息.
                   </span>
        </div>
        <div id="param">
            <span><label><h3>传递参数</h3></label></span>
            <p>
                1.参数名称：pagesize,类型：Integer
            </p>
            <code>返回：JSONObject对象</code>
            <p>
            </p>
        </div>
        <div id="return">
            <span><label><h3>返回对象</h3></label></span>
            <ul>
                <li>pageNum: Number - 页数</li>
                <li>pageSize: Number - 每页显示多少条</li>
                <li>size: Number - 查询条数</li>
                <li>orderBy: null</li>
                <li>startRow: Number - 开始条数</li>
                <li>endRow:Number - 结束条数</li>
                <li>total: Number - 总条数</li>
                <li>pages: Number - 总页数</li>
                <li>list: String, - 交易实体列表
                    <ul>
                        <li>hash: String, 32 Bytes - 交易的hash值.</li>
                        <li>nonce: Number - 发送方在此次交易之前所在的交易次数.</li>
                        <li>blockHash: String, 32 Bytes - 此次交易所在区块的hash码.</li>
                        <li>blockNumber: Number - 此次交易所在区块的区块编号.</li>
                        <li>transactionIndex: Number - 此次交易在区块的交易列表中的序列号.</li>
                        <li>from: String, 20 Bytes - 交易发送方</li>
                        <li>to: String, 20 Bytes - 交易接收方</li>
                        <li>value: BigNumber - 此次交易的值,单位：wei</li>
                        <li>gasPrice: BigNumber - 发送方提供的gas的单价.</li>
                        <li>gas: Number - 发送方消耗的gas.</li>
                        <li>input: String - 此次交易发生的其他数据.</li>
                    </ul>
                </li>
                <li>.....</li>
            </ul>
        </div>
        <div id="Example">
            <textarea id="example_text" readonly style="width: 70%;height: 50%;
                    max-height: 70%;max-width: 50%;background-color: rgba(255, 237, 246, 0.18)">

            </textarea>
        </div>
    </div>
</div>
</body>
</html>
