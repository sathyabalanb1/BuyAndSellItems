$(document).ready(function () {
    $('#submitButton').click(function(event) {
        event.preventDefault(); // Prevent form submission
       
       if (!compareQuantity()) {
            return false; // Prevent form submission if validation fails
        }
        
        var requiredquantity = document.getElementById('rquantity').value;
        
        if(requiredquantity == "")
        {
			document.getElementById("requiredquantityerror").innerHTML = "Please select the Required Quantity.";
    		return false;
		}
        
        
	});
	
});


function compareQuantity(){
let availableQuantity = parseInt(document.getElementById("aquantity").value);
let requiredQuantity = parseInt(document.getElementById("rquantity").value);
if(requiredQuantity>availableQuantity)
{
document.getElementById("rquantityError").innerHTML = "Required Quantity should be less than or equal to available quantity";
return false;
}
else{
	document.getElementById("rquantityError").innerHTML = "";

}
return true;
}
