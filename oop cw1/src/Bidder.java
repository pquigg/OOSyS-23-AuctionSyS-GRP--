
public class Bidder extends User {
	public Bidder(String name, String id, String email) {
		super(name, id, email);
	}

	
	public UserType getUserType() {
		return UserType.BIDDER;
	}

}
