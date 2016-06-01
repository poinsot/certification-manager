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

function buildQuestionJSON() {
	questionJSON.text = document.getElementById('textQuestion').value;
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

/**
 * Determine whether a node's text content is entirely whitespace.
 *
 * @param nod  A node implementing the |CharacterData| interface (i.e.,
 *             a |Text|, |Comment|, or |CDATASection| node
 * @return     True if all of the text content of |nod| is whitespace,
 *             otherwise false.
 * @author https://developer.mozilla.org/en-US/docs/Web/API/Document_Object_Model/Whitespace_in_the_DOM
 */
function isAllWhitespace(text) {
  // Use ECMA-262 Edition 3 String and RegExp features
  return !(/[^\t\n\r ]/.test(text));
}

function questionValidator() {
	if (isAllWhitespace(document.getElementById("textQuestion").value)) {
		console.log("Question is empty.");
		addErrorMessage("Question is empty.", ".text");
	}
	var answers = document.querySelectorAll('.answer');
	for(var i = 0; i < answers.length; i++) {
		var id = answers[i].id.substring(6);
		if (isAllWhitespace(document.getElementById("answertext" + id).value)) {
			console.log("Answer text " + id + " is empty");
			addErrorMessage("Answers should not be empty.", ".answers");
		}
	}
}

function saveQuestion() {
	questionJSON = {"text": "",
			"responses": []
	};
	cleanWarningAndErrorMessages();
	questionValidator();
	var errors = document.getElementsByClassName('error');
	if (errors.length == 0){
		buildQuestionJSON();
		console.log(JSON.stringify(questionJSON));
		certificationJSON.questions.push(questionJSON);
		console.log(JSON.stringify(certificationJSON));
		document.getElementById("textQuestion").value = "";
		var answers = document.getElementsByClassName('answers')[0];
		answers.innerHTML = '<p>Answers:</p><div id="answer1" class="answer"><input name="answertext1" id="answertext1" type="text"> <input name="answeristrue1" id="answeristrue1" type="checkbox"> <a href="" class="removeanswer">Remove</a></div><div id="answer2" class="answer"><input name="answertext2" id="answertext2" type="text"> <inputname="answeristrue2" id="answeristrue2" type="checkbox"> <a href="" class="removeanswer">Remove</a></div>';
	} else {
		console.log("Error: question or answers");
	}
	
}