/*
$(document).ready(function () {
    $('#submitButton').click(function(event) {
        event.preventDefault(); // Prevent form submission
        
        var selectedProducts = [];

        $('input[type="checkbox"]:checked').each(function() {
            var $row = $(this).closest('tr');
            var productData = {
                productname: $row.find('input[name^="productname"]').val(),
                productprice: $row.find('input[name^="productprice"]').val(),
                availablequantity: $row.find('input[name^="productquantity"]').val(),
                requiredquantity: $row.find('input[type="number"]').val(),
                productid: $(this).val(),
                customerid: $row.find('input[name^="customerid"]').val(),
            };
            selectedProducts.push(productData);
            
        });
        
        console.log(selectedProducts);
    });
    
    $.ajax({
            url: '/makeorder', // Specify the URL to which you want to send the data
            method: 'POST', // Specify the HTTP method
            data: JSON.stringify(selectedProducts),
            dataType: 'json', // Convert selectedProducts array to JSON string
            contentType: 'application/json', // Set content type to JSON
            success: function(response) {
                // Handle successful response
                console.log('Success:', response);
            },
            error: function(xhr, status, error) {
                // Handle error
                console.error('Error:', error);
            }
        });
    
});
*/
/*
$(document).ready(function () {
    $('#submitButton').click(function(event) {
        event.preventDefault(); // Prevent form submission
        
        var selectedProducts = [];

        $('input[type="checkbox"]:checked').each(function() {
            var $row = $(this).closest('tr');
            var productData = {
                productname: $row.find('input[name^="productname"]').val(),
                productprice: $row.find('input[name^="productprice"]').val(),
                availablequantity: $row.find('input[name^="productquantity"]').val(),
                requiredquantity: $row.find('input[type="number"]').val(),
                productid: $(this).val(),
                customerid: $row.find('input[name^="customerid"]').val(),
            };
            selectedProducts.push(productData);
            
        });
        
        console.log(selectedProducts);
        
        var inputData = JSON.stringify(selectedProducts);
        console.log(typeof inputData);
    });
    
    $.ajax({
            url: '/makeorder', // Specify the URL to which you want to send the data
            method: 'POST', // Specify the HTTP method
            data: inputData,
            dataType: 'json', // Convert selectedProducts array to JSON string
            contentType: 'application/json', // Set content type to JSON
            success: function(response) {
                // Handle successful response
                console.log('Success:', response);
            },
            error: function(xhr, status, error) {
                // Handle error
                console.error("xxxxxxyyyyyzzzzz");
                console.error('Error:', error);
            }
        });
    
});
*/

$(document).ready(function () {
    $('#submitButton').click(function(event) {
        event.preventDefault(); // Prevent form submission
        
         
        
        var selectedProducts = [];
        var isValid = true;
        
        var checkedCount = $('input[type="checkbox"]:checked').length;
        
        if (checkedCount === 0) {
    // No checkboxes are checked
    		document.getElementById("emptycheckboxerror").innerHTML = "Please Choose a Product";
			isValid=false;
            return false;
}
        
        $('input[type="checkbox"]:checked').each(function() {
			
			var $row = $(this).closest('tr');
            var requiredquantity = $row.find('input[name^="productquantity"]').val();
            
            if(requiredquantity == "")
            {
            	document.getElementById("requiredquantityerror").innerHTML = "Please Select the Required Quantity";
				isValid=false;
            	return false;
            }

	});
	
	if(!isValid)
	{
		return false;
	}
        $('input[type="checkbox"]:checked').each(function() {         
            var $row = $(this).closest('tr');
            var productData = {
                productname: $row.find('input[name^="productname"]').val(),
                productprice: $row.find('input[name^="productprice"]').val(),
                availablequantity: $row.find('input[name^="productquantity"]').val(),
                requiredquantity: $row.find('input[type="number"]').val(),
                productid: $(this).val(),
                customerid: $row.find('input[name^="customerid"]').val(),
            };
            selectedProducts.push(productData);
            
        });
        
        console.log(selectedProducts);
        
        var inputData = JSON.stringify(selectedProducts);
        console.log(typeof inputData);
        console.log(inputData);
        
        // Move the AJAX call inside the click event handler
        $.ajax({
            url: '/makeorder', // Specify the URL to which you want to send the data
            method: 'POST', // Specify the HTTP method
            data: inputData,
            dataType: 'text', // Convert selectedProducts array to JSON string
            contentType: 'application/json', // Set content type to JSON
            success: function(response) {
                // Handle successful response
                document.getElementById("ordersuccessmessage").innerHTML = "Order Placed Successfully";
             
            },
            error: function(xhr, status, error) {
                // Handle error
             //   console.error("xxxxxx");
                console.error('Error:', error);
            }
        });
    });
});


    
    
