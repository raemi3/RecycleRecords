package FinalProject.RecycleRecords.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import FinalProject.RecycleRecords.Entities.Users;

public class MyUserDetails implements UserDetails {
		 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Users users;
	     
	    public MyUserDetails(Users users) {
	        this.users = users;
	    }
	 
	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	    	Collection<FinalProject.RecycleRecords.Entities.Role> roles = users.getRoles();
	        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	         
	        for (FinalProject.RecycleRecords.Entities.Role role : roles) {
	            authorities.add(new SimpleGrantedAuthority(role.getRole_name()));
	        }
	         
	        return authorities;
	    	
	    }
	 
	    @Override
	    public String getPassword() {
	        return users.getPass_word();
	    }
	 
	    @Override
	    public String getUsername() {
	        return users.getEmail();
	    }
	 
	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }
	 
	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }
	 
	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }
	 
	    @Override
	    public boolean isEnabled() {
	        return true;
	    }
	    
	    //GETTERS & SETTERS FOR SECURITY / PROFILES
	    
	    public int getUser_id() {
	    	return users.getUser_id();
	    }
	    
	    public void setUser_id(int user_id) {
	    	this.users.setUser_id(user_id);
	    }
	     
	    public String getFullName() {
	        return users.getF_name() + " " + users.getL_name();
	    }
	    
	    public String getF_name() {
	    	return users.getF_name();
	    }
	    
	    public void setF_name(String f_name) {
	    	this.users.setF_name(f_name);
	    }
	    
	    public String getL_name() {
	    	return users.getL_name();
	    }
	    
	    public void setL_name(String l_name) {
	    	this.users.setL_name(l_name);
	    }
	    
	    public String getPhone() {
	    	return users.getPhone();
	    }
	    
	    public void setPhone(String phone) {
	    	this.users.setPhone(phone);
	    }
	    
	    public String getAddress1() {
	    	return users.getAddress1();
	    }
	    
	    public void setAddress1(String address1) {
	    	this.users.setAddress1(address1);
	    }
	    
	    public String getAddress2() {
	    	return users.getAddress2();
	    }
	    
	    public void setAddress2(String address2) {
	    	this.users.setAddress2(address2);
	    }
	    
	    public String getTown() {
	    	return users.getTown();
	    }
	    
	    public void setTown(String town) {
	    	this.users.setTown(town);
	    }
	    
	    public String getCounty() {
	    	return users.getCounty();
	    }
	    
	    public void setCounty(String county) {
	    	this.users.setCounty(county);
	    }
	    
	    public String getEircode() {
	    	return users.getEircode();
	    }
	    
	    public void setEircode(String eircode) {
	    	this.users.setEircode(eircode);
	    }

		public Users getUsers() {
			return users;
		}

		public void setUsers(Users users) {
			this.users = users;
		}
	 
}//class