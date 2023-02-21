
public class Auction {
	private Item item;
	private double startingPrice;
	private double currentBid;
	private User owner;
	private long timeRemaining;
	public Auction(Item item, double startingPrice, User owner) {
		this.item = item;
		this.startingPrice = startingPrice;
		this.currentBid = startingPrice;
		this.owner = owner;
		
	}
	
	public Item getItem() {
		return item;
	}
	
	public double getStartingPrice() {
		return startingPrice;
	}
	
	public double getCurrentBid() {
		return currentBid;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public boolean placeBid(double bidAmount, User Bidder) {
		if(bidAmount > currentBid) {
			currentBid = bidAmount;
			owner = Bidder;
			return true;
		}
		return false;
	}

}
