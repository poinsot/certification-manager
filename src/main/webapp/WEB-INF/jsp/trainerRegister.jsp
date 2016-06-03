
<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trainer Register</title>

<link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>
	<h2>Trainer Register</h2>
	<div class="warning"></div>
 <form id="form" th:action="@{/login}" th:object="${trainer}" method="post" onsubmit="return checkform();">
        <table>
            <tr><td>First Name: </td><td class="firstname"><input id="firstname" name="firstname" th:field="*{firstname}" placeholder="minimum 1 characer"></td></tr>
            <tr><td>Last Name: </td><td class="lastname"><input id="lastname" name="lastname" th:field="*{lastname}" placeholder="minimum 1 characer"></td></tr>
            <tr><td>E-mail: </td><td class="mail"><input id="mail" name="mail" th:field="*{mail}" placeholder="yourname@email.com" ></td></tr>
            <tr><td>Password: </td><td class="pwd"><input id="pwd" name="pwd" type="password" th:field="*{pwd}"></td></tr>
            <tr><td>--password: at least 8 characters(letter or number or _ ),at least :1 capital letter and 1 number</td><td>
            <tr><td>Birthday: </td><td class="birthday"><input id="birthday" name="birthday" th:field="birthday" placeholder="JJ/MM/YYYY"> </td></tr>
            <tr><td><input name="submit" type="submit" value="Submit" ></td></tr>
        
            	
        </table>
    </form>
<script src="/javascript/trainerregister.js"></script>
</body>

</html>