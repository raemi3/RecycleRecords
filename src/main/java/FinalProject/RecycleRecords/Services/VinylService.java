package FinalProject.RecycleRecords.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FinalProject.RecycleRecords.Entities.Users;
import FinalProject.RecycleRecords.Entities.Vinyl;
import FinalProject.RecycleRecords.Repositories.VinylRepository;

@Service
public class VinylService {
	
	@Autowired
	private VinylRepository vinylRepository;
	
	public VinylService(VinylRepository vinylRepository) {
		super();
		this.vinylRepository = vinylRepository;
	}
	
	//List method for searching vinyl in database by 'keyword'
	public List<Vinyl> listAll(String keyword) {
		
        if (keyword != null) {
            return vinylRepository.search(keyword);
        }
        
        return vinylRepository.findAll();
    }

	//SHOW ALL VINYL
	public List<Vinyl> getAllVinyl() {

		return vinylRepository.findAll();
	}
	//SAVE VINYL
	public Vinyl saveVinyl(Vinyl vinyl) {
		
		return vinylRepository.save(vinyl);
	}
	//GET VINYL BY ID
	public Vinyl getVinylById(Long vinyl_id) {
		
		return vinylRepository.findById(vinyl_id).get();
	}
	//EDIT VINYL
	public Vinyl editVinyl(Vinyl vinyl) {
		
		return vinylRepository.save(vinyl);
	}
	//DELETE VINYL
	public void deleteVinylById(Long vinyl_id) { //Integer
		
		vinylRepository.deleteById(vinyl_id);
	}
	//LISTS VINYL BY USERS FOREIGN KEY
	public List<Vinyl> listVinylUsers(Users users) {
		
		return vinylRepository.getVinylByUsers(users);
				
	}
	
//	//ATTEMPT AT LISTING VINYL BY USER ID FK
//	public List<Vinyl> listVinylByUser(int user_id_fk) {
//		
//		return vinylRepository.getVinylByUser_id_fk(user_id_fk);
//	}
	
//	//ATTEMPT AT DISPLAYING VINYL BY USER ID FK
//	public int findUserIDFKUserID(Long vinyl_id, int user_id_fk) {
//		
//		
//		return vinylRepository.findById(vinyl_id).get().getUsers().getUser_id();
//    }
	
}//class