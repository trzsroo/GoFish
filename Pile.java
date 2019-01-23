package Project4;
import Project4.DoublyLinkedList.Node;

public class Pile implements CardListInterface {
    private DoublyLinkedList <Card> pile;
    private int numberOfEntries;
    
    //constructors
    public Pile () {
        pile = new DoublyLinkedList <Card> ();
        numberOfEntries = 0;
    }
    
    public Pile(DoublyLinkedList <Card> p) {
    	pile = p;
    	numberOfEntries = p.getLength();
    }
    
    public void createDeck() {
    	Card[] pile = new Card[52];
    	
    	//creates an array of ranks
    	Rank[] ranks = new Rank[13];
    	ranks[0] = Rank.ACE;
    	ranks[1] = Rank.TWO;
    	ranks[2] = Rank.THREE;
    	ranks[3] = Rank.FOUR;
    	ranks[4] = Rank.FIVE;
    	ranks[5] = Rank.SIX;
    	ranks[6] = Rank.SEVEN;
    	ranks[7] = Rank.EIGHT;
    	ranks[8] = Rank.NINE;
    	ranks[9] = Rank.TEN;
    	ranks[10] = Rank.JACK;
    	ranks[11] = Rank.QUEEN;
    	ranks[12] = Rank.KING;
    	
    	//creates an array of suits
    	Suit[] suits = new Suit[4];
    	suits[0] = Suit.SPADE;
    	suits[1] = Suit.CLUB;
    	suits[2] = Suit.DIAMOND;
    	suits[3] = Suit.HEART;
    	
    	int counter = 0;
    	for(int i = 0; i < 4; i++) {
    		for(int j = 0; j < 13; j++) {
    			Card c = new Card(ranks[j], suits[i]);
    			pile[counter] = c;
    			counter++;
    		}
    	}
    	
    	for(int k = 0; k < 52; k++) {
    		add(pile[k]);
    	}
    }
    
    public void shuffle(int numShuffled) {
    	//alternate
    	if(numShuffled <= 0) {
    		throw new IndexOutOfBoundsException();
    	}
    	int halfed = numberOfEntries/2;
    	for(int i = 0; i < numShuffled + 1; i++) {
    		for(int j = 0; j < halfed; j++) {
    			Card curr = getEntry(j);
    			Card replaced  = replace(numberOfEntries- 1 - j, curr);
    			replace(j, replaced);
    		}
    	}
    		
    	// crazy shuffling
    	int r = (int) (Math.random()* 500);
    	int k = 0;
    	while(k < r) {
    		int ran1 = (int) (Math.random()* numberOfEntries);
    		int ran2 = (int) (Math.random()* numberOfEntries);
    			
    		while(ran1 == ran2) {
    			ran1 = (int) (Math.random()* numberOfEntries);
        		ran2 = (int) (Math.random()* numberOfEntries);
    		}
    			
    		Card curr = getEntry(ran1);
        	Card replaced  = replace(ran2, curr);
        	replace(ran1, replaced);
        	k++;
   		}
   	}
    	
    
    public void union(Pile other) {
    	Card[] otherArray = other.toArray();
    	for(int i = 0; i < otherArray.length; i++) {
    		add(otherArray[i]);
    		other.remove(otherArray[i]);
    	}
    	
    }
    
    //method declarations
    @Override
    public void add (Card anEntry) {
      pile.add(anEntry);
      numberOfEntries++;
    }
    
    public Pile copyPile() {
    	Card[] c = toArray();
    	Pile copy = new Pile();
    	
    	for(int i = 0; i < c.length; i++) {
    		copy.add(c[i]);
    	}
    	return copy;
    }
    
    public DoublyLinkedList<Card> copy() {
    	DoublyLinkedList <Card> copy = new DoublyLinkedList<Card>();
    	@SuppressWarnings("rawtypes")
		Node curr = pile.getNodeAt(0);
    	while(curr != null) {
    		copy.add((Card)curr.getData());
    		curr = curr.getNext();
    	}
    	
    	return copy;
    }
    
    public Card[] toArray() {
		Card[] result = new Card[numberOfEntries];
		@SuppressWarnings("rawtypes")
		Node curr = pile.getNodeAt(0);
        for (int idx = 0; idx < numberOfEntries; idx ++) {
            result[idx] = (Card) curr.getData();
            curr = curr.getNext();
        }
        return result;
    }
    
    public Card remove () {
    	if(isEmpty()) {
    		throw new IndexOutOfBoundsException();    		
    	}
    	Card result = pile.remove(--numberOfEntries);
    	return result;
    }

    public boolean remove (Card anEntry) {
    	for(int i = 0; i < pile.getLength(); i++) {
    		Card c = pile.getEntry(i);
    		if(anEntry.equals(c)) {
    			pile.remove(i);
    			numberOfEntries--;
    			return true;
    		}
    	}
    	return false;
    }

    public void clear() {
    	pile.clear();
    	numberOfEntries = 0;
    }

    /**
     * Replaces an entry at given position with a new one
     * @param givenPosition
     * @param newEntry
     * @return the replaced entry
     * @throws IndexOutOfBoundsException if either givenPosition < 0 or
     * givenPosition >= getLength()
     */
    public Card replace (int givenPosition, Card newEntry) {
    	if(givenPosition < 0 || givenPosition >= numberOfEntries) {
    		throw new IndexOutOfBoundsException();
    	}
    	Card c = pile.replace(givenPosition, newEntry);
    	return c;
    };
    
    /**
     * Retrieves an entry at given position
     * @param givenPosition
     * @return the entry at givenPosition
     * @throws IndexOutOfBoundsException if either givenPosition < 0 or
     * givenPosition >= getLength()
     */
    public Card getEntry (int givenPosition) {
    	if(givenPosition < 0 || givenPosition >= numberOfEntries) {
    		throw new IndexOutOfBoundsException();
    	}
    	return pile.getEntry(givenPosition);
    }
    
   /** Retrieves all entries that are in this pile in the order they occur in the pile.
   /* @ return a newly allocated array of all the entries in the pile
   /* If the pile is empty, the returned array is empty
    public Card[] toArray();
   
    
   /**
   /* Sees whether the pile contains the given entry
   /* @param anEntry the object: desired entry
   /* @return true if the pile contains anEntry; false if not.
   */
    public boolean contains (Card anEntry) {
    	for(int i = 0; i < pile.getLength(); i++) {
    		Card c = pile.getEntry(i);
    		if(anEntry.equals(c)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
    /* Gets the length of this pile
    /* @return the integer number of entries currently in the pile
    */
    public int getLength() {
    	return numberOfEntries;
    }
    
    /** 
    /* Sees whether this pile is empty
    /* return true if the pile is empty, false if not
    */
    public boolean isEmpty() {
    	if(numberOfEntries == 0) {
    		return true;
    	}
    	return false;
    }
    
    public void printList() {
    	for(int i = 0; i < numberOfEntries; i++) {
    		Card curr = pile.getEntry(i);
    		System.out.println(curr.getRank() + ", " + curr.getSuit());
    	}
    }
    
    //deals 7 cards to person
    public DoublyLinkedList<Card> deal(){
    	DoublyLinkedList <Card> hand = new DoublyLinkedList <Card> ();
    	for(int i = 0; i < 8; i++)
    		hand.add(remove());
    	return hand;
    }
    
    public String printCard(Card c) {
    	return (c.getRank() + ", " + c.getSuit());
    }
    
    public boolean hasRank(Card c) {
    	return (askedFor(c) != null);
    }
    
    public Card askedFor(Card c) {
    	Card[] cArray = toArray();
    	for(int i = 0; i < cArray.length; i++) {
    		if(c.sameRank(cArray[i])) {
    			return cArray[i];
    		}
    	}
    	return null;
    }
    
    //returns removed card that it's given
    public Card removePair(Card c) {
    	Card r = null;
    	if(hasRank(c)) {
    		Card[] cArray = toArray();
        	for(int i = 0; i < cArray.length; i++) {
        		if(c.sameRank(cArray[i]) && !c.equals(cArray[i])) {
        			r = cArray[i];
        			remove(r);
        			remove(c);
        			break;
        		}
        	}
    	}
    	return r;
    }
    
    /*public void removePairs() {
    	Card[] cArray = toArray();
    	for(int i = 0; i < cArray.length; i++) {
    		for(int j = 0; j < cArray.length; j++) {
    			if(i != j && cArray[i].sameRank(cArray[j])) {
    				removePair(cArray[i]);
    			}
    		}
    	}
    }*/
}
