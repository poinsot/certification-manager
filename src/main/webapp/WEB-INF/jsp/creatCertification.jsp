<!DOCTYPE html>
<html>
<head>
    <meta charset=" utf-8">
    

    <head>
        <title>Certification</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script type="text/javascript">
  
        function  addquestion(){
        	var elemDiv = document.createElement('div');
        	elemDiv.style.cssText = 'position:absolute;width:40%;height:50%;z-index:100;background-color:#ccc';
        	document.body.appendChild(elemDiv);
         	 var button=document.createElement("input");
        	  button.type="button";
        	  button.style.cssText = 'position:absolute;right:0;bottom:0;'
              button.value="save and close";
            elemDiv.appendChild(button);
            button.onclick=function(){
                button.parentNode.remove(button.parentNode);
                addlist();
       
                }
            var  t = document.createTextNode(" Question System");  
            t.fontsize = 17;
            elemDiv.appendChild(t);
            elemDiv.style.textAlign = "center";
            
          
     
              }

      
        
        
    
                   
        function  addlist(){
            var len=main.getElementsByTagName("li").length;
            var oul=main.getElementsByTagName("ul")[0];
            var oli=document.createElement("li");
            var input=document.createElement("input");
            var button=document.createElement("input");
            oli.type="1"
            input.id="id"+len;
            input.type="text";
            button.type="button";
            button.id="bt"+len;
            button.value="Delete";
 
            oli.appendChild(input);
            oli.appendChild(button);
            oul.appendChild(oli);
            button.onclick=function(){
                button.parentNode.remove(button.parentNode); 
            } 
  
            }
       
        
        </script>
    </head>
<body>

<h2> Create Certification</h2>
<h3>Title: <input type="text" /></h3>
<h3>Description:</h3>
<p><textarea name="item3" cols="50" rows="4"></textarea></p>
<div  id="div-1" style="position:absolute;top:3em ; left:30em ;">
<h3> Success percent (% ): <input type="text" /></h3>
<h3>Number of questions: <input type="text" /></h3>
<h3>Max time for passing a certification (/s): <input type="text" /></h3>
</div>
<div id="main">
    <ul>
        
    </ul>
</div>
<input type="button" value="Add New Question" id="bt"  onclick="addquestion()"/>
</body>
</html>