package FinalProject.RecycleRecords.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import FinalProject.RecycleRecords.Entities.CartItem;
import FinalProject.RecycleRecords.Entities.Users;
import FinalProject.RecycleRecords.Services.MyUserDetails;
import FinalProject.RecycleRecords.Services.ShoppingCartServices;
import FinalProject.RecycleRecords.Services.UsersService;

@Controller
public class ShoppingCartController {

	@Autowired
	private ShoppingCartServices cartServices;
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/shoppingcart")
	public String showShoppingCart(Model model, @AuthenticationPrincipal MyUserDetails loggedUser) {
		
		if (loggedUser == null) { // if no one is logged in redirected to sign in
			return "joinin";
		}
		
		//gets current user id
		int user_id = loggedUser.getUser_id();
		Users users = usersService.getUserById(user_id);
		
		//lists items in current users shopping cart
		List<CartItem> cartItems = cartServices.findByUsers(users);
		
		model.addAttribute("cartItems", cartItems);
		
		return "shoppingcart";
	}

//	@PostMapping("/shoppingcart")
//	public String showCheckout(Model model, @AuthenticationPrincipal MyUserDetails loggedUser) {
//		
//		if (loggedUser == null) { // if no one is logged in redirected to sign in
//			return "joinin";
//		}
//		
//		int user_id = loggedUser.getUser_id();
//		Users users = usersService.getUserById(user_id);
//		
//		List<CartItem> cartItems = cartServices.findByUsers(users);
//		
//		model.addAttribute("cartItems", cartItems);
//		
//		return "checkout";
//		
//	}
	
}//class