import java.util.List;

public class Seller extends User {
	public Seller(String username, String email, String id) {
		super(username, email, id);
	}
	
	public UserType getUserType() {
		return UserType.SELLER;
	}

	public void deleteAuction(Auction auction, List<Auction> auctions) {
		auctions.remove(auction);
	}
	
	public void addauction(Auction auction, List<Auction> auctions) {
		auctions.add(auction);
	}

}

