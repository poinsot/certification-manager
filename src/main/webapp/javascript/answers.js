var addanswerButton = document.querySelector('#addanswer');
var answerNumber = 2;
var minNumberOfAnswers = 1;
putListenerOnRemoveButton('#answer1 a');
putListenerOnRemoveButton('#answer2 a');

addanswerButton.addEventListener('click', function(e) {
	e.preventDefault();
	console.log('Add answer');
	addAnswer();
});

function putListenersOnRemoveAnswerLinks() {
	var links = document.querySelectorAll(".answer a");
	for(var i = 0; i < links.length; i++) {
		links[i].addEventListener('click', function(e) {
			e.preventDefault();
			var id = this.parentNode.id;
			removeAnswer(id);
		});
	}
}

function removeAnswer(id) {
	if (document.querySelectorAll(".answer a").length <= minNumberOfAnswers) {
		console.log("Minimum number of answers: 1");
		return;
	}
	var answerToRemove = document.getElementById(id);
	console.log(id);
	console.log(answerToRemove);
	answerToRemove.parentNode.removeChild(answerToRemove);
}

function putListenerOnRemoveButton(selector) {
	var removeLink = document.querySelector(selector);
	removeLink.addEventListener('click', function(e) {
		e.preventDefault();
		var id = this.parentNode.id;
		removeAnswer(id);
	});
}

function addAnswer() {
	answerNumber += 1;
	var answerNumberValue = "answer" + answerNumber;
	var answersDiv = document.querySelector('.answers');
	var newAnswerDiv = document.createElement('div');
	newAnswerDiv.setAttribute("class", "answer");
	newAnswerDiv.setAttribute("id", answerNumberValue);
	
	var answerInputText = document.createElement('input');
	answerInputText.setAttribute("name", "answertext" + answerNumber);
	answerInputText.setAttribute("id", "answertext" + answerNumber);
	answerInputText.setAttribute("type", "text");
	
	var answerInputCheckbox = document.createElement('input');
	answerInputCheckbox.setAttribute("name", "answeristrue" + answerNumber);
	answerInputCheckbox.setAttribute("id", "answeristrue" + answerNumber);
	answerInputCheckbox.setAttribute("type", "checkbox");
	
	newAnswerDiv.appendChild(answerInputText);
	newAnswerDiv.innerHTML += " ";
	newAnswerDiv.appendChild(answerInputCheckbox);
	answersDiv.appendChild(newAnswerDiv);
	newAnswerDiv.innerHTML += ' <a href="" class="removeanswer">Remove</a>';
	
	putListenerOnRemoveButton("#" + answerNumberValue + " a");
}