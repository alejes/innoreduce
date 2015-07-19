function dump(obj) {
    var out = "";
    if(obj && typeof(obj) == "object"){
        for (var i in obj) {
            out += i + ": " + obj[i] + "\n";
        }
    } else {
        out = obj;
    }
    alert(out);
}

function sendRequest(){
		inputScript.disabled = true;
		sendingLabel.style.display="";
		xhttp=new XMLHttpRequest();
		xhttp.onreadystatechange=function(){
		if (xhttp.readyState==4 && xhttp.status==200){
				sendingLabel.style.display="none";
				answerLabel.style.display="";
				document.getElementById("answerLabel").innerHTML  = xhttp.responseText;
			}
		}
		xhttp.open("GET","actions?action=sendRequest&request="+document.getElementById('inputScript').value,true);
		xhttp.send();
	}

function array_unique( array ) {	// Removes duplicate values from an array
	// 
	// +   original by: Carlos R. L. Rodrigues

	var p, i, j;
	for(i = array.length; i;){
		for(p = --i; p > 0;){
			if(array[i] === array[--p]){
				for(j = p; --p && array[i] === array[p];);
				i -= array.splice(p + 1, j - p).length;
			}
		}
	}

	return true;
}

var serverIp = new Array("127.0.0.1", "127.0.0.2", "127.0.0.11");	
function serversShow(){
	insertServers.innerHTML = "";
	for (var ip in serverIp){
			insertServers.innerHTML += "<div class='row'>\
						<div class='col-lg-6'>\
							<div class='input-group'>\
								<span class='input-group-addon'>\
									<input type='checkbox' aria-label='...'>\
								</span>\
								<li class='list-group-item list-group-item-success'>" + serverIp[ip] + "</li>\
								<div class='input-group-addon' aria-expanded='false'>\
									<button type='button' class='btn btn-default dropdown-toggle'><span class='glyphicon glyphicon-trash'></span> </button>\
								</div>\
							</div><!-- /input-group -->\
						</div>\
					</div>";
	}
}

function addServer(){
	alert(addip.value);
	serverIp.push(addip.value);
	array_unique(serverIp);
	serverIp.sort();
	serversShow();
}
