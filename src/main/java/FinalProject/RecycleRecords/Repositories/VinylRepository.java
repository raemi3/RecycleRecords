package FinalProject.RecycleRecords.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import FinalProject.RecycleRecords.Entities.Users;
import FinalProject.RecycleRecords.Entities.Vinyl;

public interface VinylRepository extends JpaRepository<Vinyl, Long> {
	
	//SEARCH FUNCTIONALITY FOR VINYL
	@Query("SELECT v FROM Vinyl v WHERE CONCAT(v.title, ' ', v.artist, ' ', v.genre, ' ', v.price) LIKE %?1%") 
	public List<Vinyl> search(String keyword);
	
	//ATTEMPT AT VIEWING VINYL BY USER ID
	//public List<Vinyl> getVinylByUser_id_fk(@Param("user_id_fk") int user_id_fk);
	
	//LISTS VINYL BY USERS ID
	public List<Vinyl> getVinylByUsers(Users users);

}