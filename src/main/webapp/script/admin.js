/**
 * 
 */
var host = "localhost"
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