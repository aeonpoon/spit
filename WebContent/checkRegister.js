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

function chkcreditcard(){
	var ccnum = document.getElementById("ccnum").value;
	var ccname = document.getElementById("ccname").value;
	var cctype = document.getElementById("cctype").value;
	var expdateM = document.getElementById("expdateM").value;
	var expdateY = document.getElementById("expdateY").value;
	var cvcnum = document.getElementById("cvcnum").value;
	
	var chkccname = "([A-Za-z])";
	var visaccnum = "(^4[0-9]{12}(?:[0-9]{3}))";
	var masterccnum = "(^5[1-5][0-9]{14})";
	var expressccnum = "(^3[47][0-9]{13})";
	var dinersccnum = "(^3(?:0[0-5]|[68][0-9])[0-9]{11})";
	//var jcbccnum = "((?:2131|1800|35\d{3})\d{11})";
	var chkexpdate = "[0-9]{2}";
	var chkcvcnum = "[0-9]{3}";
	
	if(ccnum.length == 0){
		document.getElementById('msg').innerText = "Please enter your credit card number!";
		return false;
	}else if(ccname.length == 0){
		document.getElementById('msg').innerText = "Please enter your credit card name!";
		return false;
	}else if(!ccname.match(chkccname)){
		document.getElementById('msg').innerText = "Please enter characters for your credit card name!";
		return false;
	}else if(cctype == "Select Card"){
		document.getElementById('msg').innerText = "Please select your credit card type!";
		return false;
	}else if(cctype == "Visa" && !ccnum.match(visaccnum)){	//4716720302472071	//4556775631456024
		document.getElementById('msg').innerText = "Incorrect Visa credit card number!";
		return false;
	}else if(cctype == "MasterCard" && !ccnum.match(masterccnum)){	//5513167270411966	//5420522274536709
		document.getElementById('msg').innerText = "Incorrect MasterCard credit card number!";
		return false;
//	}else if(cctype == "JCB" && !ccnum.match(jcbccnum)){	//3528695749256354 //3528257491298629
//		document.getElementById('msg').innerText = "Incorrect JCB credit card number!";
//		return false;
	}else if(cctype == "America Express" && !ccnum.match(expressccnum)){	//378400806836967	//374176457489926
		document.getElementById('msg').innerText = "Incorrect America Express credit card number!";
		return false;
	}else if(cctype == "Diners Club" && !ccnum.match(dinersccnum)){	//36367606693266	//36092824308725
		document.getElementById('msg').innerText = "Incorrect Diners Club credit card number!";
		return false;
	}else if(expdateM.length == 0){
		document.getElementById('msg').innerText = "Please enter your credit card expiry month!";
		return false;
	}else if(!expdateM.match(chkexpdate)){
		document.getElementById('msg').innerText = "Please enter only 2 digits for your credit card expiry month!";
		return false;
	}else if(expdateY.length == 0){
		document.getElementById('msg').innerText = "Please enter your credit card expiry year!";
		return false;
	}else if(!expdateY.match(chkexpdate)){
		document.getElementById('msg').innerText = "Please enter only 2 digits your credit card expiry year!";
		return false;
	}else if(cvcnum.length == 0){
		document.getElementById('msg').innerText = "Please enter your credit card CVV number!";
		return false;
	}else if(!cvcnum.match(chkcvcnum)){
		document.getElementById('msg').innerText = "Please enter your correct CVV number!";
		return false;
	}
	
}