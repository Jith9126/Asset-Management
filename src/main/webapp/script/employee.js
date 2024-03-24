/**
 * 
 */

var user;
var host = "localhost"

//window.onlo
 function session(){
	var res;
	
	const xhr = new XMLHttpRequest();
    xhr.open('POST', `http://${host}:8080/AssetManagement/SessionServ`, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            
			res = JSON.parse(xhr.responseText);
			console.log(res);
			
			if(res["status"] == 200){
				user = JSON.parse(res.user);
			}
			else{
				window.location.href = "Login.html";
			}
			
			
        } 
    };
    
    xhr.send("{}");
}


function getAsset(){
	var res;
	
	const xhr = new XMLHttpRequest();
    xhr.open('POST', `http://${host}:8080/AssetManagement/AssetServ`, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {  
			res = JSON.parse(xhr.responseText);
			
            if(res.status == 200){
                user.asset = JSON.parse(res["assets"]);
            }
        } 
    };
    
    xhr.send(JSON.stringify(user));
}

function insertAsset() {
    var asstBox = document.getElementById("Asset");
    for (const obj of user.asset) {
        var asset = document.createElement("p");
        asset.innerText = obj["assetName"] + " :";
        asset.innerText += obj["asseDetails"]+"";
        asstBox.insertAdjacentElement("afterbegin", asset);
    }
}

function gasst() {
    fetch(`http://${host}:8080/AssetManagement/AssetServ`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user)
    })
    .then(function(res) {
        if (res.ok) {
            return res.json();
        }
        throw new Error('Network response was not ok.');
    })
    .then(function(servResponce) {
        if (servResponce.status == 200) {
            user.asset = JSON.parse(servResponce["assets"]);
        }
    })
}

function sendRequest(){
	var report = {};
	report.title;
	report.notes;
	report.ReportType;
	report.asset;
	report.user = user.Id;


	
}












