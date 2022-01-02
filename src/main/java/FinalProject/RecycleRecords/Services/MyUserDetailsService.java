package FinalProject.RecycleRecords.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import FinalProject.RecycleRecords.Entities.Role;
import FinalProject.RecycleRecords.Entities.Users;
import FinalProject.RecycleRecords.Repositories.RoleRepository;
import FinalProject.RecycleRecords.Repositories.UsersRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	 
    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired RoleRepository roleRepository;
    
    //asigns roles to users in registration 
    public void registerUser(Users users) {
    	
    	String assignRole = users.getAccount_type();
    	
    	if(assignRole.equals("buyer") ) {
    		
            Role roleUser = roleRepository.findByName("buyer");
            users.addRole(roleUser);
    	}
    	else if(assignRole.equals("seller") ) {
    		
	        Role roleUser = roleRepository.findByName("seller");
	        users.addRole(roleUser);
    	}
        
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(users.getPass_word());
	    users.setPass_word(encodedPassword);
 
        usersRepository.save(users);
    }
    
    //lists roles
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }
    
    //overrides springsecurity to use email as username for login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        Users users = usersRepository.findByEmail(username);
        
        if (users != null) {
        	return new MyUserDetails(users);
        }
        throw new UsernameNotFoundException("Could not find user with email: " + username);
    }
 
}//class