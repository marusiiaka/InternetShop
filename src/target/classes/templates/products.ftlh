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
<h2>Товары</h2>
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
        <p><b>${product.category}&nbsp;</b>${product.description} &nbsp; Производитель:  ${product.manufacturer} &nbsp; Цена:  ${product.price} руб. &nbsp; Размер:  ${product.size}  <a href="/product/${product.id}">Подробно</a></p>
    </div>
    <#else>
    <h3>Товаров нет</h3>
</#list>
<br>
<h2>Моя корзина</h2>
<#list buckets as bucket>
<div>
    <p><b>${bucket.category}&nbsp;</b>${bucket.description} &nbsp; Производитель:  ${bucket.manufacturer} &nbsp; Цена:  ${bucket.price} руб. &nbsp; Размер:  ${bucket.size} Количество: ${bucket.quantity}
    <form action="/bucket/delete/${bucket.productId}" method="post">
        <input type="submit" value="Удалить товар из корзины"/>
    </form>
    </p>
</div>
<#else>
<h3>Товаров нет</h3>
</#list>
</body>
</html>