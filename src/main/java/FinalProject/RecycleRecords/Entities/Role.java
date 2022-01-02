package FinalProject.RecycleRecords.Entities;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int role_id;
	
	@Column(nullable = false, length = 10)
	private String role_name;
	
	public Role() {
		
	}

	public Role(String role_name) {
		super();
		this.role_name = role_name;
	}

	public Role(int role_id, String role_name) {
		super();
		this.role_id = role_id;
		this.role_name = role_name;
	}

	public Role(int role_id) {
		super();
		this.role_id = role_id;
	}

	@Override
	public String toString() {
    	return this.role_name;
	}

	//GETTERS & SETTERS
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
}//class