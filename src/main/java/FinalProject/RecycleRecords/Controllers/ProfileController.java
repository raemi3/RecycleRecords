package FinalProject.RecycleRecords.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import FinalProject.RecycleRecords.Entities.Users;
import FinalProject.RecycleRecords.Services.MyUserDetails;
import FinalProject.RecycleRecords.Services.UsersService;

@Controller
@RequestMapping()
public class ProfileController {
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/buyerprofile")
    public String showBuyerProfile() 
	{	
        return "buyerprofile";
    }
	
	@GetMapping("/sellerprofile")
    public String showSellerProfile()
	{		
        return "sellerprofile";
    }
	
	//DISPLAYS CURRENT BUYERS PROFILE DETAILS IN  EDIT FORM
	@GetMapping("/buyerprofileedit")
	public String editBuyerProfile(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		
		String email = loggedUser.getUsername();
		Users users = usersService.getByEmail(email);
		model.addAttribute("users", users);

		return "buyerprofileedit";
	}
	
	//UPDATES CURRENT BUYERS PROFILE DETAILS IN DATABASE
	@PostMapping("/buyerprofileedit")
	public String updateBuyerProfile(Users users, @AuthenticationPrincipal MyUserDetails loggedUser) {
		
		usersService.updateAccount(users);
		
		loggedUser.setF_name(users.getF_name());
		loggedUser.setL_name(users.getL_name());
		loggedUser.setPhone(users.getPhone());
		loggedUser.setAddress1(users.getAddress1());
		loggedUser.setAddress2(users.getAddress2());
		loggedUser.setTown(users.getTown());
		loggedUser.setCounty(users.getCounty());
		loggedUser.setEircode(users.getEircode());
		
		return "buyerprofile";
	}
	
	//DISPLAYS CURRENT SELLERS PROFILE DETAILS IN  EDIT FORM
	@GetMapping("/sellerprofileedit")
	public String editSellerProfile(@AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		
		String email = loggedUser.getUsername();
		Users users = usersService.getByEmail(email);
		model.addAttribute("users", users);

		return "sellerprofileedit";
	}
	
	//UPDATES CURRENT SELLERS PROFILE DETAILS IN DATABASE
	@PostMapping("/sellerprofileedit")
	public String updateSellerProfile(Users users, @AuthenticationPrincipal MyUserDetails loggedUser) {
		
		usersService.updateAccount(users);
		
		loggedUser.setF_name(users.getF_name());
		loggedUser.setL_name(users.getL_name());
		loggedUser.setPhone(users.getPhone());
		loggedUser.setAddress1(users.getAddress1());
		loggedUser.setAddress2(users.getAddress2());
		loggedUser.setTown(users.getTown());
		loggedUser.setCounty(users.getCounty());
		loggedUser.setEircode(users.getEircode());
		
		return "sellerprofile";
	}

}//class