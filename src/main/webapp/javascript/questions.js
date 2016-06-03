var questionJSON = {"text": "",
		"responses": []
};

var answerJSON = {"text": "", "is_correct": ""};

var saveQuestionButton = document.querySelector('#savequestion');

saveQuestionButton.addEventListener('click', function(e) {
	e.preventDefault();
	console.log('save');
	saveQuestion();
});

function getTextQuestionValue() {
	if (typeof tinyMCE == 'undefined') {
		return document.getElementById("textQuestion").value;
	} else {
		return tinyMCE.get("textQuestion").getContent();
	}
}

function setTextQuestion(str) {
	if (typeof tinyMCE == 'undefined') {
		document.getElementById("textQuestion").value = str;
	} else {
		tinyMCE.get("textQuestion").setContent(str);
	}
}

function buildQuestionJSON() {
	questionJSON.text = getTextQuestionValue();
	var answers = document.querySelectorAll('.answer');
	for(var i = 0; i < answers.length; i++) {
		answerJSON = {"text": "", "is_correct": ""};
		var id = answers[i].id.substring(6);
		answerJSON.text = document.getElementById("answertext" + id).value;
		if (document.getElementById("answeristrue" + id).checked) {
			answerJSON.is_correct = 1;
		} else {
			answerJSON.is_correct = 0;
		}
		questionJSON.responses.push(answerJSON);
	}
}

function isAllWhitespace(content) {
	if (typeof tinyMCE != 'undefined') {
		console.log("Test Question in progress")
			content = tinymce.get('textQuestion').getContent();
			return content.trim(content) == '';
	}
	return !(/[^\t\n\r ]/.test(content));
}

function isAnswerAllWhitespace(content) {
	return !(/[^\t\n\r ]/.test(content));
}

function questionValidator() {
	console.log("BEGIN questionValidator()");
	cleanWarningAndErrorMessages();
	if (isAllWhitespace(getTextQuestionValue())) {
		console.log("Question is empty.");
		addErrorMessage("Question is empty.", ".text");
	}
	var answers = document.querySelectorAll('.answer');
	for(var i = 0; i < answers.length; i++) {
		var id = answers[i].id.substring(6);
		if (isAnswerAllWhitespace(document.getElementById("answertext" + id).value)) {
			console.log("Answer text " + id + " is empty");
			addErrorMessage("Answers should not be empty.", ".answers");
			return;
		}
	}
}

function saveQuestion() {
	questionJSON = {"text": "",
			"responses": []
	};
	questionValidator();
	errors = document.getElementsByClassName('error');
	if (errors.length == 0){
		buildQuestionJSON();
		console.log(JSON.stringify(questionJSON));
		certificationJSON.questions.push(questionJSON);
		console.log(JSON.stringify(certificationJSON));
		setTextQuestion("");
		var answers = document.getElementsByClassName('answers')[0];
		answers.innerHTML = '<div id="answer1" class="answer"><span class="answernumber"></span>  ';
		answers.innerHTML += '<input name="answertext1" id="answertext1" type="text"> ';
		answers.innerHTML += '<input name="answeristrue1" id="answeristrue1" type="checkbox"> ';
		answers.innerHTML += '<a href="" class="removeanswer">Remove</a></div>';
		answers.innerHTML += '<div id="answer2" class="answer"><span class="answernumber"></span>  ';
		answers.innerHTML += '<input name="answertext2" id="answertext2" type="text"> ';
		answers.innerHTML += '<input name="answeristrue2" id="answeristrue2" type="checkbox"> ';
		answers.innerHTML += '<a href="" class="removeanswer">Remove</a></div>';
		printAllQuestions();
	} else {
		console.log("Error: question or answers");
	}
	
}