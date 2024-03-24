



var host = "localhost";


/*
function login(){
	var logObj = {
		userID:document.getElementById("userName").value,
		passwd:document.getElementById("passWord").value
	}
	var res;
	
	const xhr = new XMLHttpRequest();
    xhr.open('POST', `http://${host}:8080/AssetManagement/servlets.LoginServ`, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            // Handle successful login
			res = JSON.parse(xhr.responseText);
			console.log(res);
			res.userObj = JSON.parse(res.userObj);
			console.log(res);
			if(res.status == 200){
				console.log(res.userObj.Role);
				if(res.userObj.Role == "Employee")window.location.href = "EmpHomePage.html";
				else if (res.userObj.Role == "Vendor")window.location.href = "adminHome.html";
			}
        } 
    };
    
    const data = JSON.stringify(logObj);
    xhr.send(data);
}
*/

function login() {
    var logObj = {
        userID: document.getElementById("userName").value,
        passwd: document.getElementById("passWord").value
    };

    const data = JSON.stringify(logObj);
    const url = `http://${host}:8080/AssetManagement/servlets.LoginServ`;

    fetch(url, {
        method: 'POST', // or 'PUT'
        headers: {
            'Content-Type': 'application/json',
        },
        body: data,
        credentials: 'include' // if you need to handle cookies/CORS
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(res => {
        console.log(res);
        res.userObj = JSON.parse(res.userObj); // Assuming res.userObj is a string that needs parsing.
        console.log(res);
        if(res.status == 200){
            console.log(res.userObj.Role);
            //if(res.userObj.Role === "Employee") {
              //  window.location.href = "EmpHomePage.html";
            //} else if (res.userObj.Role === "Vendor") {
              //  window.location.href = "adminHome.html";
           // }
        }
    })
    .catch((error) => {
        console.error('There has been a problem with your fetch operation:', error);
    });
}



//credentials: 'include',