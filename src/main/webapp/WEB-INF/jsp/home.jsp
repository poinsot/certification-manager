<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CertificationManager</title>
</head>
<body>
	
	<h1> Welcome to Certification Manager</h1>
	
	<div class="home_candidate">
	<h2> Candidate </h2>
	<a href="/candidate/login" > Log In </a>
	<br>
	<a href="/candidate/register" > Create Account </a>
	</div>
	
	<br><br><br>

	<div class="home_trainer">
	<h2> Trainer </h2>
	<a href="/trainer/login" > Log In </a>
	<br>
	<a href="/trainer/register" > Create Account </a>
	</div>
		
</body>
</html>