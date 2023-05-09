<!DOCTYPE html>
<html>
<head>
    <title>Добавление отзыва</title>
</head>
<body>
<h1>Добавление отзыва  ${shoe.description}</h1><hr>
<form action="/product/createReview/${shoe.id}" method="post" >
    Описание <input type="text" name="description"/><br>
    Оценка (от 1 до 5) <input type="number" min="1" max="5" name="mark"/><br>
    <input type="submit" value="Добавить отзыв"/>
</form>

</body>
</html>