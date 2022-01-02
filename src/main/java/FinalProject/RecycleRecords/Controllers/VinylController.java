package FinalProject.RecycleRecords.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import FinalProject.RecycleRecords.Entities.Users;
import FinalProject.RecycleRecords.Entities.Vinyl;
import FinalProject.RecycleRecords.Services.MyUserDetails;
import FinalProject.RecycleRecords.Services.UsersService;
import FinalProject.RecycleRecords.Services.VinylService;

@Controller
public class VinylController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private VinylService vinylService;
	
	public VinylController(VinylService vinylService) {
		super();
		this.vinylService = vinylService;
	}
	
	//LIST ALL AVAILABLE VINYL & INCLUDES SEARCH FUNCTIONALITY
	@GetMapping("/vinyl")
	public String searchVinyl(Model model, @Param("keyword") String keyword) {
		 		
	        model.addAttribute("vinyl", vinylService.listAll(keyword));
	        model.addAttribute("keyword", keyword);
	         
	        return "vinyl";
	}
	
	//ADD VINYL
	@GetMapping("/addvinyl")
	public String addVinyl(Model model) {
		//create vinyl model to hold new vinyl data
		Vinyl vinyl = new Vinyl();
		model.addAttribute("vinyl", vinyl);
		return "addvinyl";
	}
	
	//SAVES NEW VINYL TO DATABASE INCLUDING USER FOREIGN KEY
	@PostMapping("/addvinyl")
	public String saveNewVinyl(@ModelAttribute("vinyl") Vinyl vinyl, 
								@RequestParam("fileImage") MultipartFile multipartFile,
									@AuthenticationPrincipal MyUserDetails loggedUser) throws IOException {
		
		//Assign current user id as foreign key before saving vinyl
		int user_id = loggedUser.getUser_id();
		Users users = usersService.getUserById(user_id);
		vinyl.setUsers(users);
		
		//Store image name in database & store image in folder to be displayed later by referencing id & name in database
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		vinyl.setCover(fileName);
		Vinyl savedVinyl = vinylService.saveVinyl(vinyl);
		
		String uploadDir = "./vinyl-photos/" + savedVinyl.getVinyl_id();
		
		Path uploadPath = Paths.get(uploadDir);
		
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		}
		catch (IOException e) {
			throw new IOException("Could not save file: " + fileName);
		}
		
		return "redirect:/vinyl";
	}
	
	//EDIT VINYL
	//SHOWS OPTIONS FOR EDIT
	@GetMapping("/editvinyl")
	public String listVinylToEdit(Users users, @AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		
		int user_id = loggedUser.getUser_id();
		users = usersService.getUserById(user_id);
		
		model.addAttribute("vinyl", vinylService.listVinylUsers(users));
		
		//ATTEMPT AT LISTING BY USER FK
		//int currentUser = loggedUser.getUser_id(); - NOT 2
		//model.addAttribute("vinyl", vinylService.listVinylByUser(currentUser)); - NOT 2
		
		//model.addAttribute("vinyl", vinylService.getAllVinyl()); - WORKING
		return "editvinyl1";
	}
	
//	//FIRST ATTEMPT AT LISTING VINYL BY USER ID FOR EDITING
//	@GetMapping("/editvinyl/{user_id}")
//	public String listUsersVinyl(@PathVariable int user_id, Model model, @AuthenticationPrincipal MyUserDetails loggedUser) {
//		
//				user_id = loggedUser.getUser_id();
//				//Users users = usersService.getUserById(user_id);
//				//Long vinyl_id = loggedUser.
//			
//		model.addAttribute("vinyl", vinylService.findByUser(user_id));
//		return "editvinyl";
//	}
	
	//SHOWS EDIT FORM
	@GetMapping("/editvinyl/edit/{vinyl_id}")
	public String editVinylForm(@PathVariable Long vinyl_id, Model model) {
		
		model.addAttribute("vinyl", vinylService.getVinylById(vinyl_id));
		return "editvinyl2";
	}
	
	//SAVES EDIT FORM UPDATES
	@PostMapping("/editvinyl/{vinyl_id}")
	public String updateVinyl(@PathVariable Long vinyl_id, @ModelAttribute("vinyl") Vinyl vinyl, Model model) {
		
		//get vinyl from database using id
		Vinyl existingVinyl = vinylService.getVinylById(vinyl_id);
		
		existingVinyl.setVinyl_id(vinyl_id);
		existingVinyl.setTitle(vinyl.getTitle());
		existingVinyl.setArtist(vinyl.getArtist());
		existingVinyl.setGenre(vinyl.getGenre());
		existingVinyl.setPrice(vinyl.getPrice());
		existingVinyl.setState(vinyl.getState());
		existingVinyl.setAvailable(vinyl.getAvailable());
		
		//save updated vinyl object
		vinylService.editVinyl(existingVinyl);
		
		return "redirect:/editvinyl";
	}
	
	//VIEW VINYL
	@GetMapping("/viewvinyl")
	public String listVinylToView(Users users, @AuthenticationPrincipal MyUserDetails loggedUser, Model model) {
		
		int user_id = loggedUser.getUser_id();
		users = usersService.getUserById(user_id);
		
		model.addAttribute("vinyl", vinylService.listVinylUsers(users));
		
		return "viewvinyl1";
	}
	
	//VIEW DISPLAYS CHOOSEN VINYL BY ID
	@GetMapping("/viewvinyl/{vinyl_id}")
	public String viewVinyl(@PathVariable Long vinyl_id, @ModelAttribute("vinyl") Vinyl vinyl, Model model) {
		
		//get vinyl from database using vinyl id
		model.addAttribute("vinyl", vinylService.getVinylById(vinyl_id));
		return "viewvinyl2";
	}
	
	//DISPLAYS DELETE VINYL OPTIONS
	@GetMapping("/deletevinyl")
	public String listVinylToDelete(Users users, @AuthenticationPrincipal MyUserDetails loggedUser,Model model) {
		
		int user_id = loggedUser.getUser_id();
		users = usersService.getUserById(user_id);
		
		model.addAttribute("vinyl", vinylService.listVinylUsers(users));
		
		return "deletevinyl1";
	}
	
	//DELETES VINYL
	@GetMapping("/deletevinyl/{vinyl_id}")
	public String deleteVinyl(@PathVariable Long vinyl_id) {
		
		vinylService.deleteVinylById(vinyl_id);
		return "redirect:/deletevinyl";
	}
	
//	//ATTEMPTS AT SAVING IMAGE FOR VINYL
//	@PostMapping("/addvinyl")
//	public String saveNewVinyl(@ModelAttribute(name = "vinyl") Vinyl vinyl, 
//								@RequestParam("image") MultipartFile multipartFile) throws IOException {
//		
//		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//		vinyl.setImage(fileName);
//		  
//		Vinyl savedVinyl = vinylService.saveVinyl(vinyl);
//		
//		String uploadDir = "./vinyl-photos/" + savedVinyl.getVinyl_id();
//		
//		Path uploadPath = Paths.get(uploadDir);
//		
//			if(Files.exists(uploadPath)) {
//				Files.createDirectories(uploadPath);
//			}
//			try (InputStream inputStream = multipartFile.getInputStream()) {
//				Path filePath = uploadPath.resolve(fileName);
//				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//			}
//			catch (IOException e) {
//				throw new IOException("Could not save file: " + fileName);
//			}
//		
//		return "redirect:/vinyl";
//	
////		
////		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
////        vinyl.setImage(fileName);
////         
////        Vinyl savedVinyl = vinylService.saveVinyl(vinyl);
//// 
////        String uploadDir = "./vinyl-photos/" + savedVinyl.getVinyl_id();
//// 
////        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
////         
////        return new RedirectView("/vinyl", true);
//		
//	}//saveVinyl

////PREVIOUS POST METHOD USED WHEN 'IMAGE' TYPE WAS MEDIUMBLOB / BYTE	
//	@PostMapping("/addvinyl")//@RequestBody Vinyl vinyl)
//    public String saveVinyl(
//    		@RequestParam("title") String title,
//    		@RequestParam("artist") String artist,
//    		@RequestParam("genre") String genre,
//    		@RequestParam("price") double price,
//    		@RequestParam("state") String state,
//    		@RequestParam("available") String available,
//    		@RequestParam("image") MultipartFile image) {// @RequestParam("userInForm") Users userInForm 
//			
//	//vinylService.saveVinyl(vinyl);
//		
//	//Vinyl vinyl = new Vinyl();
//		
//		    	vinylService.saveVinylToDB(title, artist, genre, price, state, available, image);// userInForm
//		    	
//		    	//Users users = usersService.findById(vinyl.getUsers().getUser_id());
//		    	//users.add(vinyl);
//		    	
//		    	return "redirect:/vinyl"; //image is saved as medium blob
//    }
	
//  ATTEMPT FOR SAVING USER FK
//	public String addVinyl(Vinyl vinyl) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Users users = (Users) authentication.getPrincipal();
//        System.out.println(users.getId());
//        vinyl.setUsers(users);
//        vinylService.save(vinyl);
//	
//        return "redirect:/vinyl";

}//class