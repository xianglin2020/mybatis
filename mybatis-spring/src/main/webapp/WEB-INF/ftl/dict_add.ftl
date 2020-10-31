
<html>
<head>
    <title>字典维护</title>
</head>
<body>
<form action="/dicts/add" method="post">
    <input type="hidden" id="id" value="${model.id}">
    <table>
        <#if msg != null>
            <tr>
                <th colspan="2" style="color: red;max-width: 400px;">${msg}</th>
            </tr>
        </#if>
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
        location.href = '/dicts';
    }
</script>
</body>
</html>
