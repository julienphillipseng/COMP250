package assignment2;

public class Warehouse{

	protected Shelf[] storage;
	protected int nbShelves;
	public Box toShip;
	public UrgentBox toShipUrgently;
	static String problem = "problem encountered while performing the operation";
	static String noProblem = "operation was successfully carried out";
	
	public Warehouse(int n, int[] heights, int[] lengths){
		this.nbShelves = n;
		this.storage = new Shelf[n];
		for (int i = 0; i < n; i++){
			this.storage[i]= new Shelf(heights[i], lengths[i]);
		}
		this.toShip = null;
		this.toShipUrgently = null;
	}
	
	public String printShipping(){
		Box b = toShip;
		String result = "not urgent : ";
		while(b != null){
			result += b.id + ", ";
			b = b.next;
		}
		result += "\n" + "should be already gone : ";
		b = toShipUrgently;
		while(b != null){
			result += b.id + ", ";
			b = b.next;
		}
		result += "\n";
		return result;
	}
	
 	public String print(){
 		String result = "";
		for (int i = 0; i < nbShelves; i++){
			result += i + "-th shelf " + storage[i].print();
		}
		return result;
	}
	
 	public void clear(){
 		toShip = null;
 		toShipUrgently = null;
 		for (int i = 0; i < nbShelves ; i++){
 			storage[i].clear();
 		}
 	}
 	
 	/**
 	 * initiate the merge sort algorithm
 	 */
	public void sort(){
		mergeSort(0, nbShelves -1);
	}
	
	/**
	 * performs the induction step of the merge sort algorithm
	 * @param start
	 * @param end
	 */
	protected void mergeSort(int start, int end){
		//ADD YOUR CODE HERE
		if (start < end) {
			int mid = (start + end)/2;
			mergeSort(start,mid);
			mergeSort(mid+1,end);
			merge(start, mid, end);	
		}
		return;
	}
	
	/**
	 * performs the merge part of the merge sort algorithm
	 * @param start
	 * @param mid
	 * @param end
	 */
	protected void merge(int start, int mid, int end){
		//ADD YOUR CODE HERE
		Shelf [] helperArray = new Shelf[nbShelves];
		for (int i = 0; i < nbShelves; i++) {
			helperArray[i] = storage[i];
		}
		int x = start;
		int y = mid + 1;
		int z = start;
		while (x <= mid && y <= end) {
			if (helperArray[y].height >= helperArray[x].height) {
				storage[z] = helperArray[x];
				x++;
			}
			else {
				storage[z] = helperArray[y];
				y++;
			}
			z++;	
		}
		while(x <= mid) {
			storage[z] = helperArray[x];
			z++;
			x++;
		}
		return;
	}
	
	/**
	 * Adds a box is the smallest possible shelf where there is room available.
	 * Here we assume that there is at least one shelf (i.e. nbShelves >0)
	 * @param b
	 * @return problem or noProblem
	 */
	public String addBox (Box b){
		//ADD YOUR CODE HERE
		for(int i = 0 ; i < nbShelves ; i++) {
			if(storage[i].height >= b.height && storage[i].availableLength >= b.length) {
				storage[i].addBox(b);
				return noProblem;
			}
		}	
		return problem;
	}
	
	/**
	 * Adds a box to its corresponding shipping list and updates all the fields
	 * @param b
	 * @return problem or noProblem
	 */
	public String addToShip (Box b){
		//ADD YOUR CODE HERE
		if(b instanceof UrgentBox) { //checking if the box is an urgent box
			if(toShipUrgently == null) { //to ship urgently list is empty
				toShipUrgently = (UrgentBox) b; //need to type cast box b to an urgent box, and adds to list by making b equal to toShipUrgently
			}
			else { //to ship urgently list already has a box in it
				b.next = toShipUrgently; //fixing pointers
				toShipUrgently.previous = b; //fixing pointers
				toShipUrgently = (UrgentBox) b; //adds to list
			}
			return noProblem;
		}
		else if (b instanceof Box) { //checking if the box is just a box, ie not urgent
			if(toShip == null) { // to ship list is empty
				toShip = b; //Adds to list by making box toShip be equal to b
			}
			else { // to ship list already has a box in it
				b.next = toShip; //fixing pointers
				toShip.previous = b; //fixing pointers
				toShip = b;
			}
			return noProblem;
		}
		else {
			return problem;
		}
	}
	
	/**
	 * Find a box with the identifier (if it exists)
	 * Remove the box from its corresponding shelf
	 * Add it to its corresponding shipping list
	 * @param identifier
	 * @return problem or noProblem
	 */
	public String shipBox (String identifier){
		//ADD YOUR CODE HERE
		for(int i=0 ; i < nbShelves ; i++ ) {
			Box presentBox = storage[i].firstBox; //starting at the first box then iterating through all boxes on shelf
			while(presentBox != null) { 
				if(presentBox.id.equals(identifier)) {
					storage[i].removeBox(identifier); //remove box from shelf
					addToShip(presentBox); //add it to shipping list
					return noProblem;
				}
				else {
					presentBox = presentBox.next; //iterates through boxes on a shelf
				}
			}
		}
		return problem;
	}
	
	/**
	 * if there is a better shelf for the box, moves the box to the optimal shelf.
	 * If there are none, do not do anything
	 * @param b
	 * @param position
	 */
	public void moveOneBox (Box b, int position){
		//ADD YOUR CODE HERE
		for(int i = 0; i < position ; i++) {
			if(b.height <= storage[i].height && b.length <= storage[i].availableLength) {
				storage[position].removeBox(b.id); //removes box from its current shelf position
				addBox(b); //re adds the box to the lowest possible shelf
				return;
			}
		}
	}
	
	/**
	 * reorganize the entire warehouse : start with smaller shelves and first box on each shelf.
	 */
	public void reorganize (){
		//ADD YOUR CODE HERE
		for (int i = 0; i < nbShelves; i++) {
			Box presentBox;
			presentBox = storage[i].firstBox;		
			while(presentBox != null) {
				Box boxAfterPresBox = presentBox.next; 	//need the temp box boxAfterPresBox because when you move a box the pointers to other boxes get removed
				moveOneBox(presentBox , i);			
				presentBox = boxAfterPresBox;
			}
		}
		return;
	}
}
