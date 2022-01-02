/*!
* Start Bootstrap - Business Casual v7.0.6 (https://startbootstrap.com/theme/business-casual)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-business-casual/blob/master/LICENSE)
*/

//sign in verification
function signinForm(){
let email = document.forms["signin"]["email"].value;
    if (email==="" || email===null){
    alert("Please enter your email");
    return false;
    }

  	let pass_word = document.forms["signin"]["pass_word"].value;
    	if (pass_word==="" || pass_word===null){
      	alert("Please enter your password");
      	return false;
    }
}//close func

//register verification
function registerForm(){
let email = document.forms["register"]["email"].value;
    if (email==="" || email===null){
      alert("Please enter a email");
      return false;
    }

  	let account_type = document.forms["register"]["account_type"].value;
    	if (account_type==="blank" || account_type===null){
      	alert("Please enter account type");
      	return false;
    }
		  let f_name = document.forms["register"]["f_name"].value;
		    if(f_name==="" || f_name===null){
		    alert("Please enter your first name");
		    return false;
	}
				  let l_name = document.forms["register"]["l_name"].value;
				    if (l_name==="" || l_name===null){
				    alert("Please enter your last name");
				    return false;
    }
						  let add1 = document.forms["register"]["add1"].value;
						    if (add1==="" || add1===null){
						    alert("Please enter address");
						    return false;
    }
								  let town = document.forms["register"]["town"].value;
								    if (town==="" || town===null){
								    alert("Please enter your town");
								    return false;
    }
										let county = document.forms["register"]["county"].value;
											if (county==="blank" || county===null || county==="blank"){
											alert("Please enter your county");
											return false;
    }
												let eircode = document.forms["register"]["eircode"].value;
													if (eircode==="" || eircode===null){
													alert("Please enter your eircode");
													return false;
	}
														let phone = document.forms["register"]["phone"].value;
															if (phone==="" || phone===null){
															alert("Please enter phone number");
															return false;
	}
																let pass_word = document.forms["register"]["pass_word"].value;
																	if (pass_word==="" || pass_word===null){
																	alert("Please enter a password");
																	return false;
	}
}//close func

//vinyl form verification
function vinylForm(){
let title = document.forms["vinylform"]["title"].value;
    if (title==="" || title===null){
    alert("Please enter your title");
    return false;
    }

  	let artist = document.forms["vinylform"]["artist"].value;
    	if (artist==="" || artist===null){
      	alert("Please enter your artist");
      	return false;
    }
		let genre = document.forms["vinylform"]["genre"].value;
	    	if (genre==="" || genre===null){
	      	alert("Please enter your genre");
	      	return false;
	    }
			let price = document.forms["vinylform"]["price"].value;
		    	if (price==="" || price===null){
		      	alert("Please enter your price");
		      	return false;
		    }
				let state = document.forms["vinylform"]["state"].value;
			    	if (state==="blank" || state===null){
			      	alert("Please enter your state");
			      	return false;
			    }
					let available = document.forms["vinylform"]["available"].value;
				    	if (available==="blank" || available===null){
				      	alert("Please enter your available");
				      	return false;
				    }
}//close func