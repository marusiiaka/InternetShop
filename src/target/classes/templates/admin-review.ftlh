<!DOCTYPE html>
<html>
<head>
    <title>Обувной интернет магазин</title>
</head>
<body>
<h1>Отзывы о товарах ждущие одобрения</h1><hr>
<#list reviews as review>
<p><b>${review.description}&nbsp;</b> &nbsp; Оценка:  ${review.mark} &nbsp;
<form action="/admin/allowedReview/${review.id}" method="post">
    <input type="submit" value="Одобрить размещение"/>
</form>
</p>
</#list>
</body>
</html>