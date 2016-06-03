<%@ page language="java" contentType="text/html; UTF-8"
pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Trainer Loggin</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>
    <h2>Trainer Loggin</h2>
    <div class="warning"></div>
 <form  method="post" onsubmit="return checkform();">
 
       <table>
            <tr><td>E-mail: </td><td class="mail"><input id="mail" name="mail" th:field="*{mail}" placeholder="yourname@email.com" ></td></tr>
            <tr><td>Password: </td><td class="pwd"><input id="pwd" name="pwd" type="password" th:field="*{pwd}"></td></tr>
            <tr><td><input name="submit" type="submit" value="Submit" ></td></tr>
        
            	
        </table>
    </form>
<script src="/javascript/trainerloggin.js"></script>
</body>
</html>