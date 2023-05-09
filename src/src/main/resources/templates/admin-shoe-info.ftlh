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
<b>Категория  </b>${shoe.category}<br>
<b>Описание  </b>${shoe.description}<br>
<b>Производитель  </b>${shoe.manufacturer}<br>
<b>Цена  </b>${shoe.price}&nbsp; руб.<br>
<b>Размер  </b>${shoe.size}<br>
<hr>
<form action="/product/delete/${shoe.id}" method="post">
    <input type="submit" value="Удалить товар"/>
</form>
</body>
</html>