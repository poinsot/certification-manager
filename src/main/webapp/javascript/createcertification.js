const URL = '';
var errors;

var certificationJSON = {
		"title": "",
		"percent_success": "",
		"nb_question": "",
		"duration": "",
		"description": "",
		"questions": []
};

var saveButton = document.querySelector('#save');

saveButton.addEventListener('click', function(e) {
	e.preventDefault();
	console.log('save');
	saveOnServer();
});

function addErrorMessage(msg, classname) {
	var error = document.createElement('span');
	error.setAttribute("class", "error");
	document.querySelector(classname).appendChild(error);
	document.querySelector(classname).querySelector('.error').innerHTML = " " + msg;
}

function addWarningMessage(msg) {
	document.querySelector('.warning').innerHTML += msg;
}

function cleanWarningAndErrorMessages() {
	errors = document.getElementsByClassName('error');
	for (var i = errors.length; i--;) {
		errors[i].remove();
	}
	document.querySelector('.warning').innerHTML = "";
}

function validator() {
	if (document.getElementById('title').value.length < 3) {
		addErrorMessage("Title of certification must be at least 3 characters", ".title");
	}
	if (document.getElementById('nb_question').value < 1) {
		addErrorMessage("At least one question in the certification", ".nb_question");
	}
	if (document.getElementById('percent_success').value == "") {
		addErrorMessage("Passing score is empty", ".percent_success");
	}
	if (document.getElementById('percent_success').value > 100) {
		addErrorMessage("Passing score should be in 0-100", ".percent_success");
	}
	if (document.getElementById('nb_question').value > certificationJSON.questions.length) {
		addErrorMessage("Please save at least " + document.getElementById('nb_question').value + " questions.", ".nb_question");
	}
}

function buildCertificationJSON() {
	cleanWarningAndErrorMessages();
	validator();
	certificationJSON.title = document.getElementById('title').value;
	certificationJSON.percent_success = document.getElementById('percent_success').value;
	certificationJSON.nb_question = document.getElementById('nb_question').value;
	certificationJSON.duration = document.getElementById('duration').value;
	certificationJSON.description = document.getElementById('description').value;
	if (certificationJSON.duration / certificationJSON.nb_question < 30) {
		addWarningMessage("Reflection time is less than 30 seconds per question.")
		console.log("Reflection time is less than 30 seconds per question.");
	}
}

function saveOnServer() {
	buildCertificationJSON();
	console.log(JSON.stringify(certificationJSON));
	var xmlhttp = new XMLHttpRequest();
	var errors = document.getElementsByClassName('error');
	console.log("" + errors.length);
	if(errors.length == 0){
		xmlhttp.open("POST", window.location.href);
		xmlhttp.setRequestHeader("Content-Type", "application/json");
		xmlhttp.send(JSON.stringify(certificationJSON));
	}
}