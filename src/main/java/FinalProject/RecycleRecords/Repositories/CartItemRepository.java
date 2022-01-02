package FinalProject.RecycleRecords.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import FinalProject.RecycleRecords.Entities.CartItem;
import FinalProject.RecycleRecords.Entities.Users;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	
	public List<CartItem> findByUsers(Users users);

//	public CartItem findByUsersAndVinyl(Users users, Vinyl vinyl);
//	
//	public List<CartItem> getCartByuserId(@Param("user_id")int user_id);
//	
//	public List<CartItem> getCartByUser(Users users);
//	
//	public Optional<CartItem> getCartByProductIdAnduserId(Users users,@Param("vinyl_id")Long vinyl_id);
	
}//class