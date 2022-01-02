package FinalProject.RecycleRecords.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FinalProject.RecycleRecords.Entities.CartItem;
import FinalProject.RecycleRecords.Entities.Users;
import FinalProject.RecycleRecords.Repositories.CartItemRepository;

@Service
public class ShoppingCartServices {

	@Autowired
	private CartItemRepository cartRepository;
	
//	@Autowired
//	private VinylRepository vinylRepository;
	
	public List<CartItem> findByUsers(Users users) {
		return cartRepository.findByUsers(users);
	}
	
//ATTEMPTS AT ADDING CART ITEMS
//	List<CartItem> getCartByuserId(@Param("user_id")int user_id){
//		return cartRepository.getCartByuserId(user_id);
//	}
//	
//	public List<CartItem> addCartbyUserIdAndProductId(Users users, long vinyl_id) throws Exception {
//		try {
//			
//			if(cartRepository.getCartByProductIdAnduserId(users, vinyl_id).isPresent()){
//				throw new Exception("Product is already exist.");
//			}
//			
//			CartItem obj = new CartItem();
//			
//			obj.setUsers(users);
//			
//			Vinyl vinyl = vinylRepository.getById(vinyl_id);
//			obj.setVinyl(vinyl); 
//			
//
//			cartRepository.save(obj);	
//			
//			return this.addCartbyUserIdAndProductId(users, vinyl_id);
//			
//			}
//		catch(Exception e) {
//				e.printStackTrace();
//			}
//		 
//	}
	
//	public Long addVinylToCart(Long vinyl_id, Users users) {
//		
//		Long addItem;
//		
//		Vinyl vinyl = vinylRepository.findById(vinyl_id).get();
//		
//		CartItem cartItem = cartRepository.findByUsersAndVinyl(users, vinyl);
//
//			cartItem = new CartItem();
//			//cartItem.setQuantity(1);
//			cartItem.setVinyl(vinyl);
//			cartItem.setUsers(users);
//		
//		cartRepository.save(cartItem);
//		
//		return cartItem;	
//	}
	
	
	
}//class