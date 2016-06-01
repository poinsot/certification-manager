<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Create new certification</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>
	<h1>Create new certification</h1>
	<div class="warning"></div>
	<form action="createcertification">
		<div class="title">
			Title (*): <input name="title" id="title" type="text"
				placeholder="3 characters minimum">
		</div>
		<div class="percent_success">
			Passing score (%) (*): <input name="percent_success"
				id="percent_success" type="number" min=0.0 max=100.0>
		</div>
		<div class="nb_question">
			Number of questions (*): <input name="nb_question" id="nb_question"
				type="number" min="1">
		</div>
		<div class="duration">
			Time limit (s) (*): <input name="duration" id="duration"
				type="number" min="30">
		</div>
		<div class="description">
			Description:
			<textarea name="description" id="description"></textarea>
		</div>
		<div>
			<a href="" id="save">Save certification</a>
		</div>
		<div id="questions" class="questions">
			<div class="text">
				<p>Question:</p>
				<textarea name="text" id="textQuestion"></textarea>
			</div>
			<div class="answers">
				<p>Answers:</p>
				<div id="answer1" class="answer">
					<input name="answertext1" id="answertext1" type="text"> <input
						name="answeristrue1" id="answeristrue1" type="checkbox"> <a href="" class="removeanswer">Remove</a>
				</div>
				<div id="answer2" class="answer">
					<input name="answertext2" id="answertext2" type="text"> <input
						name="answeristrue2" id="answeristrue2" type="checkbox"> <a href="" class="removeanswer">Remove</a>
				</div>
			</div>
			<a href="" id="addanswer">Add answer</a>
			<a href="" id="savequestion">Save question</a>
		</div>
	</form>
	<script src="/javascript/createcertification.js"></script>
	<script src="/javascript/questions.js"></script>
	<script src="/javascript/answers.js"></script>
</body>
</html>