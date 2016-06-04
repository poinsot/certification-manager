const URL = 'http://localhost:8080/candidate/258/take/35/listQuest';


var listQuestJSON = {};
var listAnswerJSON = {};
var idCurrentQuest = 1;
var saveButton = document.querySelector('#save');
var nextButton = document.querySelector('#next');
var previousButton = document.querySelector('#previous');
var divQuest = document.querySelector("#quest");
var divAnswer = document.querySelector("#answer");
var intervId;
var duration = 0;
appelAjaxJSON('GET', URL, function(err, getListQuestJSON) {
	listQuestJSON = getListQuestJSON;
    duration = parseInt(listQuestJSON.duration, 10) +1;//10;
    initListAnswerJSON();
    console.log(listQuestJSON);
	if(err) {
		console.log("oups erreur est survenue...");
		return;
	}
	console.log("appelAjaxJSON");
	
	divQuest.innerHTML = buildQuestHTML(listQuestJSON.questions[idCurrentQuest-1], idCurrentQuest);
	divAnswer.innerHTML = buildAnswersHTMLTable(listQuestJSON.questions[idCurrentQuest-1]);
	ListenerOnCheckBox();

});
intervId = setInterval(timer, 1000);
function initListAnswerJSON(){
	listAnswerJSON = listQuestJSON;
	for(var i = 0; i < listAnswerJSON.questions.length; i++){
		for(var j = 0; j < listAnswerJSON.questions.length; j++){
			listAnswerJSON.questions[i].responses[j].is_correct = 0;
		}
	}
}

previousButton.style.display = 'none';
saveButton.style.display = 'none';



function buildAnswersHTMLTable(question){
	var html = '';
	html += '<div id="answers">';
	for(var i = 0; i< question.responses.length; i++) {
		html += buildAnswersHTMLTableRow(question.responses[i], i);
	}
	html += "</div>";
	return html;
}

function buildAnswersHTMLTableRow(answer, i){
	console.log('answer.is_correct = ' + answer.is_correct == 1);
    var html = '<div>';
    if(answer.is_correct == 1) {
        html += '<input name="answer-' + i + '" id="answer-' + i + '" type="checkbox" checked >';
    }
    else{
        html += '<input name="answer-' + i + '" id="answer-' + i + '" type="checkbox" >';
    }

	html += answer.text;
	html += '</div>';
	
	return html;
}

function buildQuestHTML(question, id_quest) {
	console.log("question = " + question);
	var html = "<h1>";
	html += 'Question ' + id_quest;
	html += '/' + listQuestJSON.questions.length + ' : '; 
	html += question.text;
	html += '</h1>';
	return html;
}


function ListenerOnCheckBox() {
	var links = document.querySelectorAll("#answers input");
	for(var i = 0; i < links.length; i++) {
		links[i].addEventListener('change', function(e) {
			e.preventDefault();
			var tab = this.id.split('-');
			var id = tab[1];
			console.log(id);
			console.log("checked : " + this.checked);
			if(this.checked){
				listAnswerJSON.questions[idCurrentQuest-1].responses[id].is_correct = 1;
			}
			else{
				listAnswerJSON.questions[idCurrentQuest-1].responses[id].is_correct = 0;
			}
		});
	}
}



saveButton.addEventListener('click', function(e) {
	e.preventDefault();
	console.log('save');
	saveOnServer();
});

nextButton.addEventListener('click', function(e) {
	e.preventDefault();
	idCurrentQuest++;
	console.log('nextButton => ' + idCurrentQuest);
	divQuest.innerHTML = buildQuestHTML(listQuestJSON.questions[idCurrentQuest-1], idCurrentQuest);
	divAnswer.innerHTML = buildAnswersHTMLTable(listQuestJSON.questions[idCurrentQuest-1]);
	ListenerOnCheckBox();
	if(idCurrentQuest == listQuestJSON.questions.length){
		nextButton.style.display = 'none';
		saveButton.style.display = 'initial';
	}
	previousButton.style.display = 'initial';
	
});

previousButton.addEventListener('click', function(e) {
	e.preventDefault();
	idCurrentQuest--;
	console.log('nextButton => ' + idCurrentQuest);
	divQuest.innerHTML = buildQuestHTML(listQuestJSON.questions[idCurrentQuest-1], idCurrentQuest);
	divAnswer.innerHTML = buildAnswersHTMLTable(listQuestJSON.questions[idCurrentQuest-1]);
	if(idCurrentQuest == 1){
		previousButton.style.display = 'none';
	}
	ListenerOnCheckBox();
	nextButton.style.display = 'initial';
	saveButton.style.display = 'none';
});


function appelAjaxJSON(method, url, callback){
	var xhr = new XMLHttpRequest();
	xhr.open(method, url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				callback(null, JSON.parse(xhr.responseText));
			} else {
				callback(new Error(xhr), null);
			}
		}
	};
	xhr.send(null);
}




function saveOnServer() {
	console.log(JSON.stringify(listAnswerJSON));
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("POST", window.location.href);
	xmlhttp.setRequestHeader("Content-Type", "application/json");
	xmlhttp.send(JSON.stringify(listAnswerJSON));
}


function timer() {
    duration--;

    var oElem = document.getElementById("timer");
    oElem.firstElementChild.textContent = timeLeft(duration);
    if(duration == 0){
        clearInterval(intervId);
        saveOnServer();
    }
}

function timeLeft(time){
    var m;
    var h;
    var html = 'Time left : ';
    if(time>59)
    {
        m=Math.floor(time/60);
        time=time-m*60
    }
    if(m>59)
    {
        h=Math.floor(m/60);
        m=m-h*60
    }
    if(h>0) {
        html += h + "hours ";
    }
    if(m > 0) {
        html += m + "min ";
    }
    html += time + "sec";
    return html;
}