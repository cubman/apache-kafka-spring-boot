<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Поставщик данных</title>
    </head>
    <body>
        <form action="" method=POST>
            Текст отправки: <input type = "text"  name = "message" value = "Введите текст"> <br>
            <input  type = "submit" name = "submit" value = "Отправить" >
            <h3>Последнее сообщение: ${message}</h3>
        </form>

        <form action="history/" method=GET>
            <input  type = "submit" name = "submit" value = "История" >
        </form>
    </body>
</html>
