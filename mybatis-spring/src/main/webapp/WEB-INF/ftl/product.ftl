<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="/static/bootstrap/css/bootstrap.css" rel="stylesheet">

</head>
<body>
<div class="container-fluid">

    <table class="table table-striped table-bordered">
        <caption><h2>Product List</h2></caption>
        <tr>
            <th class="text-center">prodId</th>
            <th class="text-center">vendId</th>
            <th class="text-center">prodName</th>
            <th class="text-right">prodPrice</th>
            <th>prodDesc</th>
        </tr>

        <#list productList as product>
            <tr>
                <td class="text-center">${product.prodId}</td>
                <td class="text-center">${product.vendId}</td>
                <td class="text-center">${product.prodName}</td>
                <td class="text-right">${product.prodPrice}</td>
                <td>${product.prodDesc}</td>
            </tr>
        </#list>
    </table>
    <nav aria-label="Page navigation" class="text-center">
        <ul class="pagination">
            <#if pageInfo.isFirstPage>
                <li class="disabled"><span><span aria-hidden="true">&laquo;</span></span></li>
            <#else >
                <li><a href="/list/${pageInfo.prePage}" aria-label="Previous"><span
                                aria-hidden="true">&laquo;</span></a></li>
            </#if>
            <#list 1..(pageInfo.pages?number)!1 as i>

                <#if i == pageInfo.pageNum>
                    <li class="disabled"><span><span aria-hidden="true">${i}</span></span></li>
                <#else >
                    <li><a href="/list/${i}" aria-label="${i}"><span aria-hidden="true">${i}</span></a></li>
                </#if>

            </#list>
            <#if pageInfo.isLastPage>
                <li class="disabled"><span><span aria-hidden="true">&raquo;</span></span></li>
            <#else >
                <li><a href="/list/${pageInfo.nextPage}" aria-label="Next"><span
                                aria-hidden="true">&raquo;</span></a></li>
            </#if>
        </ul>
    </nav>
</div>

</body>
</html>