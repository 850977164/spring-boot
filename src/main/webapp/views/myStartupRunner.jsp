<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>初始化交易信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <style type="text/css">
        .main {
            word-break: break-all;
        }

        .main p {
            word-break: break-all;
        }
    </style>
</head>
<body>
<div>
    <div id="main">
        <span><label><h3>初始化详情</h3></label></span>
        <p>
            通过@Component标签定义一个项目启动过程中的组件,@Order(value = 1)来标记其执行的顺序.该组件内容
            为
        </p>
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
