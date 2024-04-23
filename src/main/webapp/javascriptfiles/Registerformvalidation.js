let element = document.getElementById('myForm');

if (element) {
	element.addEventListener('submit', function(event) {
		event.preventDefault();

		document.getElementById('nameError').textContent = '';
		document.getElementById('emailError').textContent = '';
		document.getElementById('passwordError').textContent = '';

		let name = document.getElementById('name').value;
		let email = document.getElementById('email').value;
		let password = document.getElementById('password').value;

		let isValid = validateForm(name, email, password);

		if (isValid) {
			// Proceed with form submission
			console.log('Form is valid. Submitting...');
			// Add your form submission logic here
			element.submit();
		}
	});
} else {
	console.error("Element Not Found");
}

function validateForm(name, email, password) {
	let isValid = true;

	if (name.trim() === '') {
		document.getElementById('nameError').textContent = 'Name is required.';
		isValid = false;
	} else if (!isValidName(name)) {
		document.getElementById('nameError').textContent = "Alphabets and Spaces only allowed";
		isValid = false;
	}

	if (email.trim() === '') {
		document.getElementById('emailError').textContent = "Email is required";
		isValid = false;
	} else if (!isValidEmail(email)) {
		document.getElementById('emailError').textContent = "Invalid email Format";
		isValid = false;
	}

	if (password.trim() === '') {
		document.getElementById('passwordError').textContent = "Password is required";
		isValid = false;
	} else if (!isValidPassword(password)) {
		document.getElementById('passwordError').textContent = "Password must contain at least 8 characters, including at least one uppercase letter, one lowercase letter, one digit, and one special character (!@#$%^&*)";
		isValid = false;
	}

	return isValid;
}

function isValidName(name) {
	var nameregex = new RegExp("^[A-Za-z\\s]+$");
	return nameregex.test(name);
}

function isValidEmail(email) {
	const emailregex = new RegExp(
		"^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})+$");
	return emailregex.test(email);
}

function isValidPassword(password) {
	var strongregex = new RegExp(
		"^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
	return strongregex.test(password);
}

