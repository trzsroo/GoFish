package Project4;


public interface CardListInterface {
	
	public void createDeck();
	
	public void shuffle(int numShuffled);
	
    /**
     * Add new entry at the end of the list
     * @param newEntry 
     */
    public void add (Card newEntry);
    
    /**
     * Removes an entry at end of pile
     * @return the removed entry 
     * @throws IndexOutOfBoundsException if either givenPosition < 0 or
     * givenPosition >= getLength()
     */
    public Card remove ();

    /**
     * Removes the specified entry from the list
     * @param anEntry
     * @return true if the entry was in the list; false otrherwise
     */
    public boolean remove ( Card anEntry);
 
    /**
    /* Removes all entries from the list
   */
    public void clear();
    






    /**
     * Replaces an entry at given position with a new one
     * @param givenPosition
     * @param newEntry
     * @return the replaced entry
     * @throws IndexOutOfBoundsException if either givenPosition < 0 or
     * givenPosition >= getLength()
     */
    public Card replace (int givenPosition, Card newEntry);
    
    /**
     * Retrieves an entry at given position
     * @param givenPosition
     * @return the entry at givenPosition
     * @throws IndexOutOfBoundsException if either givenPosition < 0 or
     * givenPosition >= getLength()
     */
    public Card getEntry (int givenPosition);
    
   /** Retrieves all entries that are in this list in the order they occur in the list.
   /* @ return a newly allocated array of all the entries in the list
   /* If the list is empty, the returned array is empty
    public T[] toArray();
   
    
   /**
   /* Sees whether the list contains the given entry
   /* @param anEntry the object: desired entry
   /* @return true if the list contains anEntry; false if not.
   */
    public boolean contains (Card anEntry);
    
    /**
    /* Gets the length of this list
    /* @return the integer number of entries currently in the list
    */
    public int getLength();
    
    /** 
    /* Sees whether this list is empty
    /* return true if the list is empty, false if not
    */
    public boolean isEmpty();
}
