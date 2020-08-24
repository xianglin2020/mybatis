<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xianglin
  Date: 2020/8/13
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <title>字典维护</title>
</head>
<body>
<form action="${path}/dicts/add" method="post">
    <input type="hidden" id="id" value="${model.id}">
    <table>
        <c:if test="${msg != null}">
            <tr>
                <th colspan="2" style="color: red;max-width: 400px;">${msg}</th>
            </tr>
        </c:if>
        <caption>字典维护</caption>
        <tr>
            <th>类别名</th>
            <td>
                <label>
                    <input type="text" name="code" value="${model.code}">
                </label>
            </td>
        </tr>
        <tr>
            <th>字典名</th>
            <td>
                <label>
                    <input type="text" name="name" value="${model.name}">
                </label>
            </td>
        </tr>
        <tr>
            <th>字典值</th>
            <td>
                <label>
                    <input type="text" name="value" value="${model.value}">
                </label>
            </td>
        </tr>
        <tr>
            <th colspan="2">
                <input type="submit" value="保存">
                <input type="button" onclick="backToList()" value="取消"/>
            </th>
        </tr>
    </table>
</form>

<script>
    function backToList() {
        location.href = '${path}/dicts';
    }
</script>
</body>
</html>
