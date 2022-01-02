package FinalProject.RecycleRecords.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import FinalProject.RecycleRecords.Entities.Users;
import FinalProject.RecycleRecords.Services.MyUserDetailsService;

@Controller
@RequestMapping("/register")
public class UsersRegistrationController {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	//DISPLAYS REGISTER FORM FOR CREATING NEW USER
	@GetMapping
    public String showRegisterForm(Model model){	
		
		model.addAttribute("users", new Users());
        return "register";
    }
	
	//SAVES NEW USERS DETAILS & REDIRECTS THEM TO SIGN IN
    @PostMapping
    public String processRegister(Users users) {
    	
    	myUserDetailsService.registerUser(users);
         
        return "redirect:/joinin";
    }

}//class