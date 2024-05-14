/*
$(document).ready(function () {
    $('#submitButton').click(function(event) {
        event.preventDefault(); // Prevent form submission
        
        var selectedProducts = [];
$('tr').each(function() {         
    var $row = $(this);
    var cartData = {
        requiredquantity: $row.find('input[name^="requiredquantity"]').val(),
        product: $row.find('input[name^="productid"]').val(),
        customer: $row.find('input[name^="customerid"]').val(),
    };
    selectedProducts.push(cartData);
});

		console.log(selectedProducts);
        
        var inputData = JSON.stringify(selectedProducts);
        console.log(typeof inputData);
        console.log(inputData);

});

});
*/

$(document).ready(function () {
    $('#submitButton').click(function(event) {
        event.preventDefault(); // Prevent form submission
        
        var selectedProducts = [];
$('#myForm tbody tr:not(:last-child)').each(function() {         
    var $row = $(this);
    var cartData = {
        requiredquantity: $row.find('input[name^="requiredquantity"]').val(),
        customerid: $row.find('input[name^="customerid"]').val(),
        productid: $row.find('input[name^="productid"]').val(),      
    };
    selectedProducts.push(cartData);
});

		console.log(selectedProducts);
		console.log(selectedProducts.length);
		
        
        var inputData = JSON.stringify(selectedProducts);
        console.log(typeof inputData);
        console.log(inputData);
        
        $.ajax({
            url: '/makeorderfromcart', // Specify the URL to which you want to send the data
            method: 'POST', // Specify the HTTP method
            data: inputData,
            dataType: 'text', // Convert selectedProducts array to JSON string
            contentType: 'application/json', // Set content type to JSON
            success: function(response) {
                // Handle successful response
                document.getElementById("ordersuccessmessage").innerHTML = "Order Placed Successfully";
                document.getElementById("submitButton").style.display = "none";

             
            },
            error: function(xhr, status, error) {
                // Handle error
             //   console.error("xxxxxx");
                console.error('Error:', error);
            }
        });
        
        
});

});