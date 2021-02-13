var textPattern = /^[0-9a-zA-Z]+$/;
var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{3,129}$/;
var emailPattern = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
var mobileNoPattern = /^[0-9].{9}$/;
var employeeIdPattern = /^[A-Za-z0-9]*([( ) -_ /][A-Za-z0-9]*[( ) -_ /])$/;

function userIdFunction() {
	var userId = document.forms["addUserForm"]["userId"].value;
	if (userId == "") {
		document.getElementById("userIdEmptyError").style.display = 'block';
	}
	else {
		document.getElementById("userIdEmptyError").style.display = 'none';
		if (!userId.match(textPattern)) {
			document.getElementById("userIdPatternError").style.display = 'block';
			return false;
		}
		else {
			document.getElementById("userIdPatternError").style.display = 'none';
		}
	}
}

function passwordFunction() {
	var password = document.forms["addUserForm"]["password"].value;
	if (password == "") {
		document.getElementById("passwordEmptyError").style.display = 'block';
	}
	else {
		document.getElementById("passwordEmptyError").style.display = 'none';
		if (!password.match(passwordPattern)) {
			document.getElementById("passwordPatternError").style.display = 'block';
			return false;
		}
		else {
			document.getElementById("passwordPatternError").style.display = 'none';
		}
	}
}

function emailsFunction() {
	var emails = document.forms["addUserForm"]["emailId"].value;
	if (emails == "") {
		document.getElementById("emailEmptyError").style.display = 'block';
	}
	else {
		document.getElementById("emailEmptyError").style.display = 'none';
		if (!emails.match(emailPattern)) {
			document.getElementById("emailPatternError").style.display = 'block';
		}
		else {
			document.getElementById("emailPatternError").style.display = 'none';
		}
	}
}

function mobileNoFunction() {
	var mobileNo = document.forms["addUserForm"]["mobileNo"].value;
	if (mobileNo == "") {
		document.getElementById("mobileNoEmptyError").style.display = 'block';
	}
	else {
		document.getElementById("mobileNoEmptyError").style.display = 'none';
		if (!mobileNo.match(mobileNoPattern)) {
			document.getElementById("mobileNoPatternError").style.display = 'block';
		}
		else {
			document.getElementById("mobileNoPatternError").style.display = 'none';
		}
	}
}

function firstNameFunction() {
	var firstName = document.forms["addUserForm"]["firstName"].value;
	if (firstName == "") {
		document.getElementById("firstNameEmptyError").style.display = 'block';
	}
	else {
		document.getElementById("firstNameEmptyError").style.display = 'none';
		if (!firstName.match(textPattern)) {
			document.getElementById("firstNamePatternError").style.display = 'block';
		}
		else {
			document.getElementById("firstNamePatternError").style.display = 'none';
		}
	}
}

function lastNameFunction() {
	var lastName = document.forms["addUserForm"]["lastName"].value;
	if (lastName == "") {
		document.getElementById("lastNameEmptyError").style.display = 'block';
	}
	else {
		document.getElementById("lastNameEmptyError").style.display = 'none';
		if (!lastName.match(textPattern)) {
			document.getElementById("lastNamePatternError").style.display = 'block';
		}
		else {
			document.getElementById("lastNamePatternError").style.display = 'none';
		}
	}
}

function employeeIdFunction() {
	var employeeId = document.forms["addUserForm"]["employeeId"].value;
	if (employeeId == "") {
		document.getElementById("employeeIdEmptyError").style.display = 'block';
	}
	else {
		document.getElementById("employeeIdEmptyError").style.display = 'none';
		if (!employeeId.match(employeeIdPattern)) {
			document.getElementById("employeeIdPatternError").style.display = 'block';
		}
		else {
			document.getElementById("employeeIdPatternError").style.display = 'none';
		}
	}
}

function userTypeValidate() {
	var e = document.getElementById("userType");
	if (e.selectedIndex == 0) {
		document.getElementById("userTypeEmptyError").style.display = 'block';
	}
	else {
		document.getElementById("userTypeEmptyError").style.display = 'none';
	}
}

function validateForm(formType) {
	var formTypes=formType;
	var userId = document.forms[formTypes]["userId"].value;
	if (userId == "") {
		document.getElementById("userIdEmptyError").style.display = 'block';
		 return false;
	}
	else {
		document.getElementById("userIdEmptyError").style.display = 'none';
		if (!userId.match(textPattern)) {
			document.getElementById("userIdPatternError").style.display = 'block';
			return false;
		}
		else {
			document.getElementById("userIdPatternError").style.display = 'none';
		}
	}
	var password = document.forms[formTypes]["password"].value;
	
	if (password == "") {
		document.getElementById("passwordEmptyError").style.display = 'block';
		 return false;
	}
	else {
		document.getElementById("passwordEmptyError").style.display = 'none';
		if(!(formTypes=="updateUserForm" && document.getElementById('password').value.length >=15)){
		if (!password.match(passwordPattern)) {
			document.getElementById("passwordPatternError").style.display = 'block';
			return false;
		}
		else {
			document.getElementById("passwordPatternError").style.display = 'none';
		}
		}
	}
	
	var emails = document.forms[formTypes]["emailId"].value;
	if (emails == "") {
		document.getElementById("emailEmptyError").style.display = 'block';
		 return false;
	}
	else {
		document.getElementById("emailEmptyError").style.display = 'none';
		if (!emails.match(emailPattern)) {
			document.getElementById("emailPatternError").style.display = 'block';
			 return false;
		}
		else {
			document.getElementById("emailPatternError").style.display = 'none';
		}
	}
	var mobileNo = document.forms[formTypes]["mobileNo"].value;
	if (mobileNo == "") {
		document.getElementById("mobileNoEmptyError").style.display = 'block';
		 return false;
	}
	else {
		document.getElementById("mobileNoEmptyError").style.display = 'none';
		if (!mobileNo.match(mobileNoPattern)) {
			document.getElementById("mobileNoPatternError").style.display = 'block';
			 return false;
		}
		else {
			document.getElementById("mobileNoPatternError").style.display = 'none';
		}
	}
	var firstName = document.forms[formTypes]["firstName"].value;
	if (firstName == "") {
		document.getElementById("firstNameEmptyError").style.display = 'block';
		 return false;
	}
	else {
		document.getElementById("firstNameEmptyError").style.display = 'none';
		if (!firstName.match(textPattern)) {
			document.getElementById("firstNamePatternError").style.display = 'block';
			 return false;
		}
		else {
			document.getElementById("firstNamePatternError").style.display = 'none';
		}
	}
	var lastName = document.forms[formTypes]["lastName"].value;
	if (lastName == "") {
		document.getElementById("lastNameEmptyError").style.display = 'block';
		 return false;
	}
	else {
		document.getElementById("lastNameEmptyError").style.display = 'none';
		if (!lastName.match(textPattern)) {
			document.getElementById("lastNamePatternError").style.display = 'block';
			 return false;
		}
		else {
			document.getElementById("lastNamePatternError").style.display = 'none';
		}
	}

	var employeeId = document.forms[formTypes]["employeeId"].value;
	if (employeeId == "") {
		document.getElementById("employeeIdEmptyError").style.display = 'block';
		 return false;
	}
	else {
		document.getElementById("employeeIdEmptyError").style.display = 'none';
		if (!employeeId.match(employeeIdPattern)) {
			document.getElementById("employeeIdPatternError").style.display = 'block';
			 return false;
		}
		else {
			document.getElementById("employeeIdPatternError").style.display = 'none';
		}
	}
	var e = document.getElementById("userType");
	if (e.selectedIndex == 0) {
		document.getElementById("userTypeEmptyError").style.display = 'block';
		 return false;
	}
	else {
		document.getElementById("userTypeEmptyError").style.display = 'none';
	}
	if(formTypes=="updateUserForm"){
		if(document.getElementById('password').value.length >=15)
		document.getElementById('confModal').style.display='block'
		else
		{
			var password = document.getElementById('password');
		var hashObj = new jsSHA("SHA-512", "TEXT", { numRounds: 1 });
		hashObj.update(password.value);
		var hash = hashObj.getHash("HEX");
		password.value = hash;
		document.getElementById('confModal').style.display='block'
		}
		}
		else{
			var password = document.getElementById('password');
		var hashObj = new jsSHA("SHA-512", "TEXT", { numRounds: 1 });
		hashObj.update(password.value);
		var hash = hashObj.getHash("HEX");
		password.value = hash;
		document.getElementById('addUserForm').submit();
		}
}