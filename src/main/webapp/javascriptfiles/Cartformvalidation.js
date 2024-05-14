$(document).ready(function () {
    $('#submitButton').click(function(event) {
		
		event.preventDefault();
		
		var isValid = true;
		
		let productid = document.getElementById("productid").value;
		let customerid = document.getElementById("customerid").value;
		let requiredquantity = document.getElementById("rquantity").value;
		
		var cartData = {
                productid: productid,
                customerid: customerid,
                requiredquantity: requiredquantity,
            };
            
    //    var inputData = JSON.stringify(cartData);
        
    //    console.log(typeof inputData);
    //    console.log(inputData);
        
        $.ajax({
            url: '/makecart', 
            method: 'POST', 
            data: JSON.stringify(cartData),
            dataType: 'text', 
            contentType: 'application/json', 
            success: function(response) {
                // Handle successful response
                document.getElementById("cartsuccessmessage").innerHTML = "Item Added to Cart";
             
            },
            error: function(xhr, status, error) {
                // Handle error
             //   console.error("xxxxxx");
             console.log(xhr);
             console.log(status);
             console.log(error);
                console.error('Error:', error);
            }
        });
    });
});
