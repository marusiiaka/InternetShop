<!DOCTYPE html>
<html>
<head>
    <title>Обувной интернет магазин</title>
</head>
<body>
<h1>Обувной интернет магазин | <a href="/login">Войти</a></h1><hr>
<form action="/logout" method="post">
    <#--     <input type="hidden" name="_csrf" value="${_csrf.token}">-->
    <input type="submit" value="Выйти">
</form>
<h4>Товары</h4>
<form action="/" method="get">
    Поиск по категории:
    <p>
        <select name="category">
            <option>Все</option>
            <option>Детские</option>
            <option>Женские</option>
            <option>Мужские</option>
            <option>Спортивные</option>
        </select>
    </p>
    <input type="submit" value="Поиск">
</form>
<#list products as product>
<div>
    <p><b>${product.category}&nbsp;</b>${product.description} &nbsp; Производитель:  ${product.manufacturer} &nbsp; Цена:  ${product.price} руб. &nbsp; Размер:  ${product.size}  <a href="product/${product.id}">Подробно</a></p></p>
</div>
<#else>
<h3>Товаров нет</h3>
</#list>
<hr>
<h3>Создать новый товар</h3>
<form action="/product/create" method="post" enctype="multipart/form-data">
    Категория <input type="text" name="category"/><br>
    Описание <input type="text" name="description"/><br>
    Производитель <input type="text" name="manufacturer"/><br>
    Цена <input type="number step=0.01" name="price"/><br>
    Размер <input type="number step=0.5" name="size"/><br>
    Первая фотография: <input type="file" name="file1"/><br><br>
    Вторая фотография: <input type="file" name="file2"/><br><br>
    Третья фотография: <input type="file" name="file3"/><br><br>
    <input type="submit" value="Добавить товар"/>
</form>
<br>
<a href="/admin/reviews">Список новых отзывов о товаре на одобрение</a>
</body>
<br>
<a href="/admin/blogs">Добавить информацию в блог </a>
</body>
</html>