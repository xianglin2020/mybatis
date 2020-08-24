<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xianglin
  Date: 2020/8/13
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <title>字典信息</title>
    <script src="${path}/static/jquery.min.js"></script>
</head>
<body>
<table>
    <caption>字典管理</caption>
    <tr>
        <th>类别名</th>
        <th>字典名</th>
        <th>字典值</th>
        <th>操作[<a href="${path}/dicts/add">新增</a>]</th>
    </tr>
    <c:forEach items="dicts" var="dict">
        <tr id="dict-${dict.id}">
            <td>${dict.code}</td>
            <td>${dict.name}</td>
            <td>${dict.value}</td>
            <td>
                [<a href="${path}/dicts/add?id=${dict.id}">编辑</a>]
                [<a href="javascript:" onclick="deleteById(${dict.id},'${dict.name}')">删除</a>]
            </td>
        </tr>
    </c:forEach>
</table>

<script>
    function deleteById(id, name) {
        let confirm1 = confirm('确定要删除' + name + '吗？');
        if (confirm1) {
            $.ajax({
                url: '${path}/dicts/delete',
                method: 'delete',
                data: {id: id},
                dataType: 'json',
                success: function (json) {
                    if (json.success) {
                        $('dict-' + id).remove();
                    } else {
                        alert(json.msg);
                    }
                }
            })
        }
    }
</script>
</body>
</html>
