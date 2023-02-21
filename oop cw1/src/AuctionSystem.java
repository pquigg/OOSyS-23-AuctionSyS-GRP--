import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuctionSystem {
	private List<Auction> auctions;
	private List<User> users;
	private User loggedInUser;
	
	public AuctionSystem() {
		auctions = new ArrayList<>();
		users = new ArrayList<>();
		loggedInUser = null;
	}
	
	public void run () {
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		while (running) {
			if (loggedInUser == null) {
				System.out.println("Welcome to the Auction System.");
				System.out.println("Please select an option:");
				System.out.println("1. create an account");
				System.out.println("2. Log in");
				System.out.println("3. Exit");
				String choice = scanner.nextLine();
				switch (choice) {
				case "1":
					createAccount(scanner);
					break;
				case"2":
					logIn(scanner);
					break;
				case"3":
					running = false;
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
					break;
				}
			} else {
				System.out.println("Welcome, " + loggedInUser.getName() + ".");
				if (loggedInUser instanceof Seller) {
				System.out.println("Please select an option:");
				System.out.println("1. view auctions");
				System.out.println("2. create an auction");
				System.out.println("3. Delete an auction");
				System.out.println("4. log out");
				String choice = scanner.nextLine();
				switch (choice) {
				case "1":
					viewAuction();
					break;
				case "2":
					createAuction(scanner);
					break;
				case "3":
					deleteAuction(scanner);
					break;
				case"4":
					loggedInUser = null;
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
					break;
				}
			} else {
				System.out.println("Please select an option: ");
				System.out.println("1. View auctions");
				System.out.println("2. Place a bid");
				System.out.println("3. log out");
				String choice = scanner.nextLine();
				switch(choice) {
				case "1":
					viewAuction();
					break;
				case "2":
					placeBid(scanner);
					break;
				case"3":
					loggedInUser = null;
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
					break;
				}
			}
		}
	}
}
	
	private void createAccount(Scanner scanner) {
		System.out.println("Please enter your name:");
		String name = scanner.nextLine();
		System.out.println("Please enter your email:");
		String email = scanner.nextLine();
		System.out.println("please enter a personal id:");
		String id = scanner.nextLine();
	
		System.out.println("Select user type:");
		System.out.println("1. seller");
		System.out.println("2. Bidder");
		int userTypeOption = scanner.nextInt();
		scanner.nextLine();
		
		UserType userType = null;
		switch (userTypeOption) {
		case 1:
			userType = UserType.SELLER;
			break;
		case 2:
			userType = UserType.BIDDER;
			break;
			default:
				System.out.println("Invalid option.");
				return;
		}
		
		User user = null;
		switch(userType) {
		case SELLER:
			user = new Seller(name, email, id);
			break;
		case BIDDER:
			user = new Bidder(name, email, id);
			break;
		}
		
		users.add(user);
		System.out.println("User created succesfully!");
	}
	private void logIn(Scanner scanner) {
        System.out.println("Please enter your ID:");
        String id = scanner.nextLine();
        for (User user : users) {
            if (user.getId().equals(id)) {
                loggedInUser = user;
                System.out.println("Logged in successfully.");
                return;
            }
        }
        System.out.println("Invalid ID. Please try again.");
    }
	
	private void viewAuction() {
		if (auctions.isEmpty()) {
			System.out.println("There are no auctions available. ");
		} else {
			for (Auction auction : auctions) {
				System.out.println(auction.getItem().getName() + " (current bid: " + auction.getCurrentBid() + ")");
			}
		}
	}
    
	private void createAuction(Scanner scanner) {
		if (loggedInUser instanceof Seller) {
			System.out.println("Please the name of the item:");
			String itemName = scanner.nextLine();
			System.out.println("Please enter the description of the item:");
			String itemDescription = scanner.nextLine();
			System.out.println("Please enter the starting bid for this item:");
			double startingPrice = Integer.parseInt(scanner.nextLine());
			Item item = new Item(itemName, itemDescription);
			auctions.add(new Auction(item, startingPrice, loggedInUser));
			System.out.println("Auction created successfully.");
		}
	}
	
	private void deleteAuction(Scanner scanner) {
		if (loggedInUser instanceof Seller) {
			System.out.println("Please enter the name of the item:");
			String itemName = scanner.nextLine();
			for (Auction auction : auctions) {
				if (auction.getItem().getName().equals(itemName)) {
					((Seller)loggedInUser).deleteAuction(auction, auctions);
					System.out.println("Auction deleted succesfully.");
					return;
				}
			}
			System.out.println("Auction not found.");
		}
	}
	
	private void placeBid(Scanner scanner) {
		if (loggedInUser instanceof Bidder) {
			System.out.println("Please the name of the item:");
			String itemName = scanner.nextLine();
			for (Auction auction : auctions) {
				if (auction.getItem().getName().equals(itemName)) {
					System.out.println("Please enter your bid:");
					int bid = Integer.parseInt(scanner.nextLine());
					if (auction.placeBid(bid, (Bidder)loggedInUser)) {
						System.out.println("Bid placed successfully.");
					} else {
						System.out.println("Bid not placed. Please enter a higher bid.");
					}
					return;
				}
			}
			System.out.println("Auction not found.");
		}
	}
	
	public static void main(String[] args) {
		AuctionSystem auctionSystem = new AuctionSystem();
		auctionSystem.run();
	}
}

