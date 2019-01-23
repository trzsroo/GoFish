package Project4;


public class DoublyLinkedList <T> implements ListInterface <T>  {
	
	//creates Node class
	public class Node{
		private T data;
		private Node next;
		private Node prev;
		public Node(T data) {
			this.data = data;
			next = null;
			prev = null;
		}
		
		public Node() {
			data = null;
			next = null;
			prev = null;
		}
		
		public T getData() {
			return data;
		}
		
		public void setData(T data) {
			this.data=data;
		}
		
		public Node getNext() {
			return next;
		}
		
		public void setNext(Node n) {
			next=n;
		}
		
		public Node getPrev() {
			return prev;
		}
		
		public void setPrev(Node n) {
			prev=n;
		}
	}
	
	private Node firstNode;
	
	public DoublyLinkedList () {
	}
	
	public T getEntry(int i) {
		Node newN = getNodeAt(i);
		return newN.getData();
		
	}
	
	public Node getNodeAt(int givenPosition) {
		   assert (givenPosition >= 0 && givenPosition < getLength());
		 
		   Node currentNode = firstNode;
		   if(givenPosition == 0) {
			   return firstNode;
		   }
		   for (int idx = 0; idx < givenPosition; idx ++) {
		       currentNode = currentNode.getNext();
		   }
		   return currentNode;
		 }

	
	@Override
	public void add(T newEntry) {
	    Node newNode = new Node(newEntry);
        
	    if (firstNode == null)
	        firstNode = newNode;
	    else {
	        boolean end = false;
	        Node nextNode = firstNode.getNext();
	        Node curr = firstNode;
	            
	        while (!end) {
	            end = (nextNode == null);
	            if (!end){
	            	curr = nextNode;
	            	assert (nextNode != null);
	            	nextNode = nextNode.getNext();
	            }
	        }
	     assert (curr != null);
	     curr.setNext(newNode);
	     newNode.setPrev(curr);
	   }   

		
	}

	@Override
	public void add(int newPosition, T newEntry) {
		// TODO Auto-generated method stub
		if (newPosition < 0 || newPosition > getLength())
		      throw new IndexOutOfBoundsException();
		        
		   Node newNode = new Node(newEntry);
		        
		   if (newPosition == 0) {// the only value for the empty list
			  firstNode.setPrev(newNode);
		      newNode.setNext(firstNode);  
		      firstNode = newNode;
		   }
		   else { // traverse the chain
		      int idx = 0; boolean found = false;
		      Node after = firstNode, before = null;
		      do {
		        if (idx == newPosition) {
		          found = true;
		          assert (before != null);
		          before.setNext(newNode);
		          newNode.setPrev(before);
		          newNode.setNext(after);
		         }

		        else {
		          before = after;
		          after = after.getNext();
		          idx ++;
		         }
		       } while (!found);
		    }

	}
	
	public int getLength(){
	    int numEntries = 0;
	    Node curr;
	    for (curr = firstNode; curr != null;
	                               curr = curr.getNext())
	       numEntries ++;  
	    return numEntries;
	}

	
	public T remove (int givenPosition){
	    if (isEmpty())
	       throw new NullPointerException();
	    if (givenPosition < 0 || givenPosition >= getLength())
	       throw new IndexOutOfBoundsException();
	    T dataItem = firstNode.getData();
	            
	    if (givenPosition == 0)
	       firstNode = firstNode.getNext();
	    else {
	       int idx = 0;
	       Node nextNode = firstNode;
	       for (Node curr = firstNode; nextNode != null; 
	                                     curr = nextNode) {
	          idx ++;
	          nextNode = curr.getNext();
	          if (idx == givenPosition ){ // nextNode is to be removed 
	             assert (nextNode != null);
	             dataItem = nextNode.getData();
	             curr.setNext(nextNode.getNext());
	             (nextNode).setPrev(curr);
	             break;
	            } 
	         }
	     }
	    return dataItem;
	}
	
	public boolean isEmpty() {
		if(firstNode==null) {
			return true;
		}
		return false;
	}


	@Override
	public boolean remove(T anEntry) {
		// TODO Auto-generated method stub
		int i=0;
		
	    Node nextNode = firstNode;
	    for (Node curr = firstNode; nextNode != null; 
	                                     curr = nextNode) {
	          if(curr.data.equals(anEntry)) {
	        	  remove(i);
	        	  return true;
	          }
	          
	          nextNode = curr.getNext();
	          i++; 
	     }
	     
		return false;
	}

	@Override
	public void clear() {
		
		while(!isEmpty()) {
			@SuppressWarnings("unused")
			Node curr = firstNode;
			firstNode=firstNode.next;
			curr = null;
		}
		
	}
	
	public T replace (int givenPosition, T newEntry) {
		
		T curr = remove(givenPosition);
		add(givenPosition, newEntry);
		return curr;
	}

	@Override
	public boolean contains(T anEntry) {
		// TODO Auto-generated method stub
		Node curr;
		
		for (curr = firstNode; curr != null;
                curr = curr.getNext())
			if((curr.getData()).equals(anEntry)) 
				return true;
		return false;
	}


}
