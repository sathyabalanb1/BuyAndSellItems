/**
 * 
 */

function isValidMail(event) {
					
			let email = document.getElementById("email").value;
			
			let errorElement = document.getElementById("mailError");

			
			if(email.trim() === '')
			{
				errorElement.textContent="Email is Required";
			}
			
			return ;            
            
        }
