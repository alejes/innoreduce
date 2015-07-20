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

function file_get_contents( url ) {	// Reads entire file into a string
	// 
	// +   original by: Legaev Andrey
	// %		note 1: This function uses XmlHttpRequest and cannot retrieve resource from different domain.

	var req = null;
	try { req = new ActiveXObject("Msxml2.XMLHTTP"); } catch (e) {
		try { req = new ActiveXObject("Microsoft.XMLHTTP"); } catch (e) {
			try { req = new XMLHttpRequest(); } catch(e) {}
		}
	}
	if (req == null) throw new Error('XMLHttpRequest not supported');

	req.open("GET", url, false);
	req.send(null);

	return req.responseText;
}

//var serverIp = new Array("127.0.0.1", "127.0.0.2", "127.0.0.11");	
function serversShow(){
	var jsonText = file_get_contents('serversList.json?' + Math.random());
	//alert(jsonText);
	var jsonText = JSON.parse(jsonText);
	//alert(jsonText);
	var serverIp =  jsonText.ip;
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
									<button type='button' class='btn btn-default dropdown-toggle' onClick='return deleteServer(\"" + serverIp[ip] + "\");'><span class='glyphicon glyphicon-trash'></span> </button>\
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
	window.location.reload(true);
	setTimeout('window.location.reload(true)', 3000);
}


function deleteServer(ip){
		xhttp=new XMLHttpRequest();
		xhttp.onreadystatechange=function(){
		if (xhttp.readyState==4 && xhttp.status==200){
				serversShow();
				window.location.reload(true);
				setTimeout('window.location.reload(true)', 3000);
			}
		}
		xhttp.open("GET","actions?action=deleteServer&deleteip="+ip,true);
		xhttp.send();	
}