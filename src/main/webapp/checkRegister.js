/**
 * 
 */

function checkRegister(){
	var email = document.getElementById("emailinput").value;
	var password = document.getElementById("passwordinput").value;
	var name = document.getElementById("nameinput").value;
	var dobday = document.getElementById("dobday").value;
	var dobmonth = document.getElementById("dobmonth").value;
	var dobyear = document.getElementById("dobyear").value;
	var address = document.getElementById("addressinput").value;
	var contact = document.getElementById("contactinput").value;
	
	var strongpass = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\W).*$", "g");
		
	if(email.length == 0){
		document.getElementById('msg').innerText = "Please enter your email address!";
		return false;
	}else if(!email.match(/^[A-Za-z\._\-0-9]*[@][A-Za-z]*[\.][a-z]{2,3}$/)){
		document.getElementById('msg').innerText = "Not valid. Please enter a valid email address!";
		return false;
	}else if(password.length == 0){
		document.getElementById('msg').innerText = "Please enter your password!";
		return false;
	}else if(!strongpass.test(password)){
		document.getElementById('msg').innerText = "Password is not strong! Have at least 8 characters with upper/lower case & numbers!";
		return false;
	}else if(name.length == 0){
		document.getElementById('msg').innerText = "Please enter your name!";
		return false;
	}else if($('input[name=gender]:checked').length <= 0){
		document.getElementById('msg').innerText = "Please select your gender!";
		return false;
	}else if(dobday == "Day" || dobmonth == "Month" || dobyear == "Year"){
		document.getElementById('msg').innerText = "Please enter your birthday!";
		return false;
	}else if(dobmonth == "Febuary" && (dobday == "30" || dobday == "31")){
		document.getElementById('msg').innerText = "Invalid birthday in Febuary!";
		return false;
	}else if(dobmonth == "Febuary" && dobday == 29 && dobyear % 4 != 0){
		document.getElementById('msg').innerText = "Not a leap year!";
		return false;
	}else if(address.length == 0){
		document.getElementById('msg').innerText = "Please enter your address!";
		return false;
	}else if(contact.length == 0){
		document.getElementById('msg').innerText = "Please enter your contact!";
		return false;
	}else if(!contact.match(/[6,8,9]{1}[0-9]{7}/)){
		document.getElementById('msg').innerText = "Please enter a valid contact no.!";
		return false;
	}else if($('input[name=subscribe]:checked').length <= 0){
		document.getElementById('msg').innerText = "Please select your subscription!";
		return false;
	}
}

function passwordCheck(){
	var password = document.getElementById("passwordinput").value; 
	
	var strongpass = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\W).*$", "g");
	var mediumpass = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
	var enoughpass = new RegExp("(?=.{6,}).*", "g");
	
	if(strongpass.test(password)){
		document.getElementById('msg').innerText = "Password strength Strong!";
	}else{
		if(mediumpass.test(password)){
			document.getElementById('msg').innerText = "Password strength Medium!";
		}else{
			if(enoughpass.test(password)){
				document.getElementById('msg').innerText = "Password strength Weak!";
				return false;
			}
			document.getElementById('msg').innerText = "Enter your password!";
		}
	}
}