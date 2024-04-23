$(document).ready(function () {
    $('#submitButton').click(function(event) {
        event.preventDefault(); // Prevent form submission
       
        
        var requiredquantity = document.getElementById('rquantity').value;
        
        if(requiredquantity == "")
        {
			document.getElementById("requiredquantityerror").innerHTML = "Please select the Required Quantity.";
    		return false;
		}
        
        
	});
});