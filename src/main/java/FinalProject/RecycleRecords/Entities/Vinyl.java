package FinalProject.RecycleRecords.Entities;

import javax.persistence.*;

@Entity
@Table (name = "vinyl")
public class Vinyl {
	
	@Id
	@Column
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long vinyl_id; //Integer
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Column(nullable = false, length = 100)
	private String artist;
	
	@Column(nullable = false, length = 25)
	private String genre;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false, length = 10)
	private String state;
	
	@Column(nullable = false, length = 10)
	private String available;
	
	@Column(nullable = true, length = 45)
	private String cover;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id_fk", referencedColumnName = "user_id") //nullable = false, - null allowed until I figure out user_id
	private Users users;
	
	public Vinyl() {
		
	}
	
	public Vinyl(String title) {
		this.title=title;
	}
	
	public Vinyl(String title, String artist, String genre, double price, String state, String available) {
		super();
		this.title=title;
		this.artist=artist;
		this.genre=genre;
		this.price=price;
		this.state=state;
		this.available=available;
	}
	
	//GETTERS & SETTERS
	public void setVinyl_id(Long vinyl_id) { //Integer
		this.vinyl_id = vinyl_id;
	}
	
	public Long getVinyl_id() { //Integer
		return vinyl_id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getState() {
		return state;
	}
	
	public void setAvailable(String available) {
		this.available = available;
	}
	
	public String getAvailable() {
		return available;
	}
	
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Users getUsers() {		
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
	@Transient //calls view of stored image for vinyl by their vinyl_id & image name
	public String getCoverImagePath() {
		if (cover == null || vinyl_id == null) return null;
			
		return "/vinyl-photos/" + vinyl_id + "/" + cover;
	}

}//class