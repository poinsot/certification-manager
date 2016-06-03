file = document.querySelector('#file');

uploadButton = document.querySelector('#uploadFileAction');
questionField = document.getElementById("textQuestion");

uploadButton.addEventListener('click', function(e) {
	// TODO : setQuestionText with "<img alt="blabla" src="" /> 
	// TODO : getTextQuestionValue() = < img 
	
	e.preventDefault();
	console.log('upload');
	saveMedia();
});


function saveMedia() {
            var formData = new FormData();
            formData.append('file', $('input[type=file]')[0].files[0]);
            console.log("form data " + formData);
            $.ajax({
                url : 'http://localhost:8080/upload/file/ajaxSaveMedia.do',
                data : formData,
                processData : false,
                contentType : false,
                type : 'POST',
                success : function(data) {
                	console.log("upload success" + data)
                	setTextQuestion(data);
                    //alert(data);
                },
                error : function(err) {
                	console.log("upload issues" + err)
                    //alert(err);
                }
           });
}
            
            