package FinalProject.RecycleRecords.Entities;

import java.util.*;
import javax.persistence.*;

@Entity
@Table (name = "users", schema = "recycle_records", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Users {
	
	@Id
	@Column
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int user_id;
	
	@Column(nullable = false, length = 10)
	private String account_type;
	
	@Column(nullable = false, length = 25)
	private String f_name;
	
	@Column(nullable = false, length = 25)
	private String l_name;
	
	@Column(nullable = false, unique = true, length = 60)
	private String email;
	
	@Column(length = 20)
	private String phone;
	
	@Column(nullable = false, length = 100)
	private String address1;
	
	@Column(length = 50)
	private String address2;
	
	@Column(nullable = false, length = 25)
	private String town;
	
	@Column(nullable = false, length = 25)
	private String county;
	
	@Column(length = 10)
	private String eircode;
	
	@Column(nullable = false, length = 100)
	private String pass_word;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
		            name = "user_id", referencedColumnName = "user_id"),
			inverseJoinColumns = @JoinColumn(
				            name = "role_id", referencedColumnName = "role_id"))
	
	private Set<Role> roles = new HashSet<>();
	
	//used to assign role to user during registration
	public void addRole(Role role) {
		this.roles.add(role);
	}
	
	@OneToMany(mappedBy = "users")
	private List<Vinyl> vinyl;
	
//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(
//			name = "users_roles",
//			joinColumns = @JoinColumn(
//		            name = "user_id", referencedColumnName = "user_id"),
//			inverseJoinColumns = @JoinColumn(
//				            name = "role_id", referencedColumnName = "role_id"))
//	
//	private Collection<Role> roles;
	
	//CONSTRUCTORS
	public Users() {
		
	}
	
	public Users(int user_id) {
		this.user_id = user_id;
	}
	
	public Users(String account_type, String f_name, String l_name, String email, String phone, String address1,
			String address2, String town, String county, String eircode, String pass_word) {
		super();
		this.account_type = account_type;
		this.f_name = f_name;
		this.l_name = l_name;
		this.email = email;
		this.phone = phone;
		this.address1 = address1;
		this.address2 = address2;
		this.town = town;
		this.county = county;
		this.eircode = eircode;
		this.pass_word = pass_word;
	}
	
	//GETTERS & SETTERS
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getUser_id() {
		return user_id;
	}
	
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getAccount_type() {
		return account_type;
	}
	
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getF_name() {
		return f_name;
	}
	
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	public String getL_name() {
		return l_name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone() {
		return phone;
	}
	
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress1() {
		return address1;
	}
	
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress2() {
		return address2;
	}
	
	public void setTown(String town) {
		this.town = town;
	}
	public String getTown() {
		return town;
	}
	
	public void setCounty(String county) {
		this.county = county;
	}
	public String getCounty() {
		return county;
	}
	
	public void setEircode(String eircode) {
		this.eircode = eircode;
	}
	public String getEircode() {
		return eircode;
	}
	
	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}
	public String getPass_word() {
		return pass_word;
	}

	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Vinyl> getVinyl() {
		return vinyl;
	}

	public void setVinyl(List<Vinyl> vinyl) {
		this.vinyl = vinyl;
	}
    
}//class