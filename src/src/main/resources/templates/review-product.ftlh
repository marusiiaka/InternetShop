<!DOCTYPE html>
<html>
<head>
    <title>Обувной интернет магазин</title>
</head>
<body>
<h1>Отзывы о товаре</h1><hr>
<#list reviews as review>
<p><b>${review.description}&nbsp;</b> &nbsp; Оценка:  ${review.mark} &nbsp;  </p>
</#list>
</body>
</html>