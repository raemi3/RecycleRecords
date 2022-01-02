package FinalProject.RecycleRecords.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import FinalProject.RecycleRecords.Entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	@Query("SELECT r FROM Role r WHERE r.role_name = ?1")
	public Role findByName(String role_name);

}