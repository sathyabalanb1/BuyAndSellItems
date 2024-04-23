/**
 * 
 */

 let element = document.getElementById('myForm');

 if (element) {
	element.addEventListener('submit', function(event) {
		event.preventDefault();

		document.getElementById('nameError').textContent = '';
		document.getElementById('priceError').textContent = '';
		document.getElementById('quantityError').textContent = '';

		let productname = document.getElementById('productname').value;
		let productprice = document.getElementById('price').value;
		let productquantity = document.getElementById('productquantity').value;

		let isValid = validateForm(productname, productprice, productquantity);

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

function validateForm(productname, productprice, productquantity) {
		let isValid = true;
		
		if (productname.trim() === '') {
		document.getElementById('nameError').textContent = 'Product Name is required.';
		isValid = false;
	} else if (!isValidName(productname)) {
		document.getElementById('nameError').textContent = "Alphabets and Spaces only allowed";
		isValid = false;
	}
	
	if (productprice.trim() === '') {
		document.getElementById('priceError').textContent = "Price is required";
		isValid = false;
	} else if (!isValidPrice(productprice)) {
		document.getElementById('priceError').textContent = "Maximum 8  numeric digits only Before Decimal and 2 numeric digits after decimal";
		isValid = false;
	}
	
	if (productquantity.trim() === '') {
		document.getElementById('quantityError').textContent = "Quantity is required";
		isValid = false;
	} else if (!isValidQuantity(productquantity)) {
		document.getElementById('quantityError').textContent = "Please Enter Numeric Input";
		isValid = false;
	}
	
	return isValid;

}

function isValidName(productname) {
	var nameregex = new RegExp("^[A-Za-z\\s]+$");
	return nameregex.test(productname);
}

function isValidPrice(productprice) {
	var priceregex = new RegExp(
		"^\\d{1,8}(?:\\.\\d{1,2})?$");
	return priceregex.test(productprice);
}

function isValidQuantity(productquantity) {
	var quantityregex = new RegExp("^\\d{1,7}$");
	return quantityregex.test(productquantity);
}
