package Project4;

public class GoFish {
	private Pile deck = new Pile();
	private Pile yourHand = new Pile();
	private Pile opponentHand = new Pile();
	private Pile yourPairs = new Pile();
	private Pile opponentPairs = new Pile();
	
	public GoFish() {
	}
	
	public Pile getYourHand() {
		return yourHand;
	}
	
	public Pile getOpponentHand() {
		return opponentHand;
	}
	public Pile getYourPairs() {
		return yourPairs;
	}
	
	public Pile getOpponentPairs() {
		return opponentPairs;
	}
	
	public Pile getDeck() {
		return deck;
	}
	
	//returns user's hand
	public Pile setUp() {
		deck.createDeck();
		int n = (int) (Math.random()* 100) + 37;
		deck.shuffle(n);
		for(int i = 0; i < 15; i++) {
			Card r = deck.remove();
			if(i%2==0) {
				opponentHand.add(r);
			}
			else {
				yourHand.add(r);
			}
		}
		return yourHand;
	}
	
	public void checkForPairsUser() {
		Pile c = yourHand.copyPile();
		Card r = null;
		int add = 0;
		for(int i = 0; i < c.getLength(); i++) {
    		for(int j = 0; j < c.getLength(); j++) {
    			if(i != j && c.getEntry(i).sameRank(c.getEntry(j))) {
    				yourPairs.add(c.getEntry(i));
    				r = yourHand.removePair(c.getEntry(i));
    				yourPairs.add(r);
    				c.remove(c.getEntry(j));
    				c.remove(c.getEntry(i));
    				add++;
    			}
    		}
    	}
		System.out.println("You had " + add + " pair(s) added. Now you have a total of " + yourPairs.getLength()/2 + " pairs.");
		System.out.println("\nYour current hand: " );
		yourHand.printList();
	}
	
	public void checkForPairsOpponent() {
		Pile c = opponentHand.copyPile();
		Card r = null;
		int add = 0;
		for(int i = 0; i < c.getLength(); i++) {
    		for(int j = 0; j < c.getLength(); j++) {
    			if(i != j && c.getEntry(i).sameRank(c.getEntry(j)) && j > i) {
    				opponentPairs.add(c.getEntry(i));
    				r = opponentHand.removePair(c.getEntry(i));
    				opponentPairs.add(r);
    				c.remove(c.getEntry(j));
    				c.remove(c.getEntry(i));
    				add++;
    			}
    		}
    	}
		System.out.println("Your opponent had " + add + " pair(s) added. Now your opponent have a total of " + opponentPairs.getLength()/2 + " pairs.");
	}
	
	public boolean userAsk(Card inhand) {
		if(opponentHand.hasRank(inhand)) {
			System.out.println("Your opponent has a(n) " + inhand.getRank());
			userAddPair(inhand);
			System.out.println("You had 1 pair added. Now you have a total of " + yourPairs.getLength()/2 + " pairs.");
			System.out.println("\nYour current hand: " );
			yourHand.printList();
			return true;
			
		}
		else {
			System.out.println("Your opponent does not have a(n) " + inhand.getRank() + ". Go fish!");
			Card newCard = deck.remove();
			yourHand.add(newCard);
			System.out.println(newCard.getRank() + ", " + newCard.getSuit() + " was added to your hand.");
			checkForPairsUser();
			return false;
		}
	}
	
	public void userAddPair(Card c) {
		Card toBeRemoved = opponentHand.askedFor(c);
		yourPairs.add(toBeRemoved);
		yourPairs.add(c);
		yourHand.remove(c);
		opponentHand.remove(toBeRemoved);
	}
	
	public boolean opponentAsk() {
		int r = (int) (Math.random()* opponentHand.getLength());
		Card c = opponentHand.getEntry(r);
		System.out.println("\n" + c.getRank() + ", " + c.getSuit() + " was asked for.");
		if(yourHand.hasRank(c)) {
			opponentAddPair(c);
			return true;
			
		}
		else {
			System.out.println("Go fish! \nOpponent picked up a card.");
			Card newCard = deck.remove();
			opponentHand.add(newCard);
			checkForPairsOpponent();
			System.out.println("\nYour current hand: " );
			yourHand.printList();
			return false;
		}
	}
	
	public void opponentAddPair(Card c) {
		Card toBeRemoved = yourHand.askedFor(c);
		opponentPairs.add(toBeRemoved);
		opponentPairs.add(c);
		opponentHand.remove(c);
		yourHand.remove(toBeRemoved);
		System.out.println("Your opponent took " + toBeRemoved.getRank() + ", " + toBeRemoved.getSuit() + "."); 
		System.out.println("Your opponent had 1 pair added. Now your opponent have a total of " + opponentPairs.getLength()/2 + " pairs.");
		System.out.println("\nYour current hand: ");
		yourHand.printList();
	}
	
	public boolean cardExists(Rank r, Suit s) {
		if(s != null && r != null) {
			return true;
		}
		return false;
	}
	
	public Rank makeRank(String rank) {
		Rank r = null;
		switch(rank.toLowerCase()) {
			case "ace":
				r = Rank.ACE;
				break;
			case "two":
				r = Rank.TWO;
				break;
			case "three":
				r = Rank.THREE;
				break;
			case "four":
				r = Rank.FOUR;
				break;
			case "five":
				r = Rank.FIVE;
				break;
			case "six":
				r = Rank.SIX;
				break;
			case "seven":
				r = Rank.SEVEN;
				break;
			case "eight":
				r = Rank.EIGHT;
				break;
			case "nine":
				r = Rank.NINE;
				break;
			case "ten":
				r = Rank.TEN;
				break;
			case "jack":
				r = Rank.JACK;
				break;
			case "queen": 
				r = Rank.QUEEN;
				break;
			case "king":
				r = Rank.KING;
				break;
			default:
				System.out.println("An invalid rank was entered.");
				break;
		}
		
		return r;
	}
	
	public Suit makeSuit(String suit) {
		Suit s = null;
		switch(suit.toLowerCase()) {
			case "heart":
				s = Suit.HEART;
				break;
			case "club":
				s = Suit.CLUB;
				break;
			case "spade":
				s = Suit.SPADE;
				break;
			case "diamond":
				s = Suit.DIAMOND;
				break;
			default:
				System.out.println("An invalid suit was entered.");
				break;
		}
		return s;
	}
	
	public Card newCardInput(String rank, String suit) {
		Card c = null;
			Rank r = null;
			r = makeRank(rank);
			
			Suit s = null;
			s = makeSuit(suit);
			
			if(cardExists(r, s)) {
				c = new Card(r, s);
				if(getYourHand().contains(c)) {
					return c;
				}
				else {
					System.out.println("There is no such card in your hand. Please input one that is in your hand.");
				}
			}
			else {
				System.out.println("Please input a valid card");
			}
		return c;
	}

}
