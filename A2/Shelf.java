package assignment2;

public class Shelf {
	
	protected int height;
	protected int availableLength;
	protected int totalLength;
	protected Box firstBox;
	protected Box lastBox;

	public Shelf(int height, int totalLength){
		this.height = height;
		this.availableLength = totalLength;
		this.totalLength = totalLength;
		this.firstBox = null;
		this.lastBox = null;
	}
	
	protected void clear(){
		availableLength = totalLength;
		firstBox = null;
		lastBox = null;
	}
	
	public String print(){
		String result = "( " + height + " - " + availableLength + " ) : ";
		Box b = firstBox;
		while(b != null){
			result += b.id + ", ";
			b = b.next;
		}
		result += "\n";
		return result;
	}
	
	/**
	 * Adds a box on the shelf. Here we assume that the box fits in height and length on the shelf.
	 * @param b
	 */
	public void addBox(Box b){
		//ADD YOUR CODE HERE
		if(firstBox == null && lastBox == null) { //if the shelf has nothing on it
			lastBox = b;
			firstBox = b;
		}
		else { //if there is already at least one box on the shelf
			lastBox.next = b;//changing pointer first
			b.previous = lastBox;  //changing pointer first
			lastBox = b; //changing the last box
			b.next = null; 
		}
		availableLength = availableLength - b.length;	
		return;
	}
	
	/**
	 * If the box with the identifier is on the shelf, remove the box from the shelf and return that box.
	 * If not, do not do anything to the Shelf and return null.
	 * @param identifier
	 * @return
	 */
	public Box removeBox(String identifier){
		//ADD YOUR CODE HERE
		Box presentBox = firstBox;	
		while(presentBox != null) {
			if(presentBox.id.equals(identifier)) {
				if(presentBox.equals(lastBox) && presentBox.equals(firstBox)){
					lastBox=null; //adjusting pointers in linked list
					firstBox=null; //adjusting pointers in linked list
				}
				else if(presentBox.equals(firstBox)) {
					firstBox = presentBox.next; //adjusting pointers in linked list
					firstBox.previous = null; //adjusting pointers in linked list
				}
				else if(presentBox.equals(lastBox)) {
					lastBox = presentBox.previous; //adjusting pointers in linked list
					lastBox.next = null; //adjusting pointers in linked list
				}
				else {
					presentBox.next.previous = presentBox.previous;
					presentBox.previous.next = presentBox.next;
				}
				presentBox.next = null; //need to take off the pointers on the box you are removing
				presentBox.previous = null; // need to take off the pointers on the box you are removing	
				availableLength = availableLength + presentBox.length; //updating available length on shelf
				return presentBox;
			}
			presentBox = presentBox.next; //iterate to next box on the shelf		
		}	
		return null;
	}
	
}
