<%@ page language="java" contentType="text/html; UTF-8"
pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Trainer Loggin</title>
</head>
<body>
    <h2>Trainer Loggin</h2>
 <form action="#" th:action="@{/login}" th:object="${trainer}" method="post">
            <p>E-mail: <input name="mail" th:field="*{mail}" placeholder="yourname@email.com" ></p>
            <p>Password: </td><td><input name="pwd" type="password" th:field="*{pwd}"></p>
            <p><input type="submit" value="Submit" ></p^>
    </form>

</body>
</html>