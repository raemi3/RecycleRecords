//package FinalProject.RecycleRecords;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//
//import FinalProject.RecycleRecords.Controllers.CartItemRepository;
//
//@DataJpaTest
//@AutoConfigureTestDatabase
//@Rollback(false)
//public class ShoppingCartTest {
//	
//	@Autowired
//	private CartItemRepository cartRepository;
//	
//	@Autowired
//	private TestEntityManager entityManager;
//	
//	@Test
//	public void testAddOneCartItem() {
//		Vinyl vinyl = entityManager.find(Vinyl.class, 3);
//		Users users = entityManager.find(Users.class, 2);
//		
//		CartItem newItem = new CartItem();
//		newItem.setVinyl(vinyl);
//		newItem.setUsers(users);
//		newItem.setQuantity(1);
//		
//		CartItem savedCartItem = cartRepository.save(newItem);
//		
//		assertTrue(savedCartItem.getCart_id() < 0);
//	}
//	
//	@Test
//	public void testFindByCustomer() {
//		Integer customerId = 10;
//		List<CartItem> listItems = repo.findByCustomer(new Customer(customerId));
//		
//		listItems.forEach(System.out::println);
//		
//		assertThat(listItems.size()).isEqualTo(2);
//	}
//
//}