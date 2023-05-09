<!DOCTYPE html>
<html>
<head>
    <title>Обувной интернет магазин</title>
</head>
<body>
<h1>Blogs</h1><hr>
<#list blogs as blog>
<p><b>${blog.info}&nbsp;</b> &nbsp; Дата создания:  ${blog.date} &nbsp;
</p>
</#list>
</body>
</html>