
<%@ page language="java" contentType="text/html; UTF-8"
pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Candidate Register</title>
</head>
<body>
    <h2>Candidate Register</h2>
 <form action=""  method="post">
        <table>
            <tr><td>Last Name: </td><td><input name="lastname" ></td></tr>
            <tr><td>First Name: </td><td><input name="firstname"></td></tr>
            <tr><td>Identity Card Number: </td><td><input name="id_card_number"  placeholder="000000000000" ></td></tr>
            <tr><td>E-mail: </td><td><input name="mail" placeholder="yourname@email.com" ></td></tr>
         
            <tr><td>Password: </td><td><input name="pwd" type="password" ></td></tr>
            <tr><td>--password: at least 8 characters(letter or number or _ ),at least :1 capital letter and 1 number</td><td>
            
            <tr><td>Birthday: </td><td><input name="birthday" placeholder="JJ/MM/YYYY"> </td></tr>
            <tr><td colspan="2" align="right"><input type="submit" value="Submit" ></td></tr>
        </table>
    </form>

</body>
</html>