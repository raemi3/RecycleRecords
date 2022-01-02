package FinalProject.RecycleRecords.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	
	@GetMapping("/")
	public String showHome()
	{
		return "/index";
	}
	
	@GetMapping("/about")
    public String showAbout()
    {
    	return "about";
    }

	@GetMapping("/joinin")
    public String showJoinIn() 
	{
    	return "joinin";
    }
	
	@GetMapping("/checkout")
	public String showCheckout()
	{
		return "checkout";
	}

}//class