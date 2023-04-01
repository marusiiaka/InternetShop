<!DOCTYPE html>
<html>
<head>
    <title>Обувной интернет магазин</title>
</head>
<body>
<h1>Обувной интернет магазин</h1><hr>
<h4>Подробная информация о товаре</h4>
<#list images as img>
    <img src="/images/${img.id}" height="60px"/><br><br>
</#list>
<b>Категория  </b>${product.category}<br>
<b>Описание  </b>${product.description}<br>
<b>Производитель  </b>${product.manufacturer}<br>
<b>Цена  </b>${product.price}&nbsp; руб.<br>
<b>Размер  </b>${product.size}<br>
<hr>
<form action="/product/delete/${product.id}" method="post">
    <input type="submit" value="Удалить товар"/>
</form>
</body>
</html>