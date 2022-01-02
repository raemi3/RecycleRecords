package FinalProject.RecycleRecords.Services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import FinalProject.RecycleRecords.Entities.Users;
import FinalProject.RecycleRecords.Repositories.UsersRepository;

@Service
@Transactional
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//to display user details in profile
	public Users getByEmail(String email) {
		return usersRepository.findByEmail(email);
	}
	
	//to get user by id
	public Users getUserById(int user_id) {
		return usersRepository.getById(user_id);
	}
	
	//to update user details in profile
	public Users updateAccount(Users userInForm) {
		
		Users userInDB = usersRepository.findById(userInForm.getUser_id()).get();
		
		if (!userInForm.getPass_word().isEmpty()) {
			userInDB.setPass_word(userInForm.getPass_word());
			encodePassword(userInDB);
		}
		
		userInDB.setF_name(userInForm.getF_name());
		userInDB.setL_name(userInForm.getL_name());
		userInDB.setPhone(userInForm.getPhone());
		userInDB.setAddress1(userInForm.getAddress1());
		userInDB.setAddress2(userInForm.getAddress2());
		userInDB.setTown(userInForm.getTown());
		userInDB.setCounty(userInForm.getCounty());
		userInDB.setEircode(userInForm.getEircode());
		
		return usersRepository.save(userInDB);
	}
	
	private void encodePassword(Users users) {
		String encodedPassword = passwordEncoder.encode(users.getPass_word());
		users.setPass_word(encodedPassword);
	}

//	public Users getCurrentlyLoggedInUser(Authentication authentication) {
//		
//		if(authentication == null) return null;
//		
//		Users users = null;
//		Object principal = authentication.getPrincipal();
//		
//		if(principal instanceof MyUserDetails) {
//			users = ((MyUserDetails) principal).getUsers());
//		}
//		else if(principal instanceof MyUserDetailsService) {
//			String email = ((UsersService) principal).getByEmail(email)
//			users = getByEmail(email);
//		}
//		
//		return users;
//	}

}//class