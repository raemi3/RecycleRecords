package FinalProject.RecycleRecords.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import FinalProject.RecycleRecords.Entities.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>  {
	
    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    public Users findByEmail(String email);
    
}