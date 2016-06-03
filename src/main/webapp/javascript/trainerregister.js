const URL = '';


function checkform(){
	
	if (validator()) {return true;} 
	else {return false;} 	
	
	}




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
	var errors = document.getElementsByClassName('error');
	for (var i = 0; i < errors.length; i++) {
	    if (errors[i].className.toLowerCase() == "error") {
	        errors[i].parentNode.removeChild(errors[i]);
	    }
	}
	document.querySelector('.warning').innerHTML = "";
}


function validateEmail(email) 
{
var re = /\S+@\S+\.\S+/;
return re.test(email);
}

function validatePassWord(password) 
{ 
	if(password == "")	 {	 return false;		  }
	  re = /^\w+$/;
	if(!re.test(password)) { return false;		  }
	if(password != "" ) {
	    if(password.length < 6) {return false;	  }
	    re = /[0-9]/;
	    if(!re.test(password)){return false;	  }
	    re = /[A-Z]/;
	    if(!re.test(password)) {return false; }
	    re = /[a-z]/;
	    if(!re.test(password)) {return false; }
	   
	  } 

}

function validateDate(date) 
	{
	var re = /^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;
	return re.test(date);
	}

function validator(){
	
	cleanWarningAndErrorMessages();
	a=true;
	if (document.getElementById('firstname').value == "") {
		addErrorMessage("firstname is empty", ".firstname"); a=false;
		
	}else{addErrorMessage("", ".firstname"); }
	
	if (document.getElementById('lastname').value == "") {
		addErrorMessage("lastname is empty", ".lastname"); a=false;
	}else{addErrorMessage("", ".lastname"); }
	
	
	if (document.getElementById('mail').value  == "" ) {
		addErrorMessage("e-mail is empty", ".mail"); a=false;
	}else if (validateEmail(document.getElementById('mail').value) == false ) {
		addErrorMessage("e-mail format  is not correct", ".mail");return a=false;
     }else{addErrorMessage("", ".mail"); }
	if (document.getElementById('pwd').value  == "") {
		addErrorMessage("Password is empty", ".pwd"); a=false;
   }else if ( validatePassWord(document.getElementById('pwd').value) == false) {
		addErrorMessage("Password format is not correct", ".pwd");	return a=false;
   }else{addErrorMessage("", ".pwd"); }
			
	if (document.getElementById('birthday').value == "") {
		addErrorMessage("birthday is empty", ".birthday"); a=false;
	}else if (validateDate(document.getElementById('birthday').value) == false ) {
		addErrorMessage("birthday format is not correct", ".birthday"); a=false;
	
     }else{addErrorMessage("", ".birthday"); }
	
	return a;
}

