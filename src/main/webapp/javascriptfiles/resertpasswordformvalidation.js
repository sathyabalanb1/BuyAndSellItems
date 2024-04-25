/**
 * 
 */

 function checkPassword(event) {
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirm-password").value;
            var errorElement = document.getElementById("passwordError");

            if (password !== confirmPassword) {
                errorElement.textContent = "Passwords do not match";
                event.preventDefault(); // Prevent form submission by default
            }else{
            	errorElement.textContent = "";
            } 
        }