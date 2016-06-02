printAllQuestions();

function getQuestions() {
	return certificationJSON.questions;
}

function clearQuestion() {
	var listQuestionsElement = document.getElementById("listquestions");
	if (listQuestionsElement != null) {
		listQuestionsElement.parentNode.removeChild(listQuestionsElement);
	}
}

function printAllQuestions() {
	clearQuestion();
	var questionsDiv = document.getElementById("questions");
	var listQuestionsDiv = document.createElement('div');
	listQuestionsDiv.setAttribute("id", "listquestions");
	var questionsListHead = document.createElement('h2');
	questionsListHead.innerHTML = "Questions:";
	var orderedList =  document.createElement('ul');
	for (i = 0; i < getQuestions().length; i++) {
		var listItem = document.createElement('li');
		listItem.innerHTML = getQuestions()[i].text;
		orderedList.appendChild(listItem);
		orderedList.appendChild(printResponses(i));
	}
	listQuestionsDiv.appendChild(questionsListHead);
	listQuestionsDiv.appendChild(orderedList);
	questionsDiv.insertBefore(listQuestionsDiv, null);
}

function getResponses(i) {
	return getQuestions()[i].responses;
}

function printResponses(i) {
	var responses = getResponses(i);
	var ulResponses =  document.createElement('ul');
	for (i = 0; i < responses.length; i++) {
		var liResponse = document.createElement('li');
		liResponse.innerHTML = responses[i].text;
		ulResponses.appendChild(liResponse);
	}
	return ulResponses;
}