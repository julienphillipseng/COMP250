package assignment3;

public class Building {

	OneBuilding data;
	Building older;
	Building same;
	Building younger;
	
	public Building(OneBuilding data){
		this.data = data;
		this.older = null;
		this.same = null;
		this.younger = null;
	}
	
	public String toString(){
		String result = this.data.toString() + "\n";
		if (this.older != null){
			result += "older than " + this.data.toString() + " :\n";
			result += this.older.toString();
		}
		if (this.same != null){
			result += "same age as " + this.data.toString() + " :\n";
			result += this.same.toString();
		}
		if (this.younger != null){
			result += "younger than " + this.data.toString() + " :\n";
			result += this.younger.toString();
		}
		return result;
	}

	public Building addBuilding (OneBuilding b){
		
		if(this.data.yearOfConstruction > b.yearOfConstruction){ //toAdd is older
			if(this.older == null){
				Building toAdd = new Building(b);
				this.older = toAdd;
			}
			else{
				this.older.addBuilding(b);
			}
		}
		
		else if(this.data.yearOfConstruction < b.yearOfConstruction){ //toAdd is younger
			if(this.younger == null){
				Building toAdd = new Building(b);
				this.younger = toAdd;
			}
			else{
				this.younger.addBuilding(b);
			}
		}
		
		else if(this.data.yearOfConstruction == b.yearOfConstruction){ //toAdd is same Age
			if(b.height>this.data.height){ //need to swap the local root and toAdd
				Building toAdd = new Building(b);
				toAdd.same = this.same;
				this.same = toAdd;
				
				//OneBuilding temp = toAdd.data;
				//toAdd.data = this.data;
				//this.data = temp;
				OneBuilding temp = this.data;
				this.data = toAdd.data;
				toAdd.data = temp;	
			}
			else {//toAdd needs to go under the local root
				if(this.same == null){
					Building toAdd = new Building(b);
					this.same = toAdd;
				}
				else{
					this.same.addBuilding(b);
				}
			}
		}
		
		//return the root of the entire tree
		return this; 
	}
	
	public Building addBuildings (Building b){
		
		if(b == null) {
			//Nothing happens
		}
		else {
			this.addBuilding(b.data);
		
			if(b.older != null){
				this.addBuildings(b.older);
			}

			if(b.younger != null){
				this.addBuildings(b.younger);
			}
		
			if(b.same != null){
				this.addBuildings(b.same);
			}
		}
		return this;
	}	
	
	public Building removeBuilding (OneBuilding b){
		
		/*
		//------------------------------------------My Start------------------------------------------------------------------
		Building newRoot = null; //takes in data and connections, should be named newLocalRoot
		Building foundBuilding= null;
		
		// Traverse tree
		if(b.equals(this.data)) // "this" is the one to remove, and happens to be the root of the entire tree, cannot get back into this if statement later
		{
			foundBuilding = this;
			if(foundBuilding.older ==null && foundBuilding.same==null && foundBuilding.younger==null) //this no children
			{
				return newRoot; //root of the entire tree is the one you want to remove, and it's the only element in the tree, tree becomes null
			}
			//What if the first one is what you want to remove and it has stuff attached to it??????????
			//The code goes to SKIP POINT 2, and (foundBuilding=this)
		}
		else if(this.older!=null && b.equals(this.older.data)) //older is the one to remove
		{
			foundBuilding = this.older;
			if(foundBuilding.older ==null && foundBuilding.same==null && foundBuilding.younger==null) //older has no children, if has children --> skip to SKIP POINT
			{
				this.older=null;
				// and root is still "this"
			}
		}
		else if(this.same!=null && b.equals(this.same.data)) //same is the one to remove
		{
			foundBuilding = this.same;
			if(foundBuilding.older ==null && foundBuilding.same==null && foundBuilding.younger==null) //same has no children, if has children --> skip to SKIP POINT
			{
				this.same=null;
				// and root is still "this"
			}
		}
		else if(this.younger!=null && b.equals(this.younger.data)) //younger is the one to remove
		{
			foundBuilding = this.younger;
			if(foundBuilding.older ==null && foundBuilding.same==null && foundBuilding.younger==null) //younger has no children
			{
				this.younger=null;
				// and root is still "this"
			}
		}
		else //traverse tree because "this" or its children aren't what you're looking for
		{
			if(b.yearOfConstruction < this.data.yearOfConstruction && this.older != null) 	//when you go to that child, you know it won't be the one you're looking for
			{																				//so, you'll never get into the very first if statement of this method
				this.older.removeBuilding(b);
			}
			else if(b.yearOfConstruction == this.data.yearOfConstruction && this.same != null)
			{
				this.same.removeBuilding(b);
			}
			else if(b.yearOfConstruction > this.data.yearOfConstruction && this.younger != null)
			{
				this.younger.removeBuilding(b);
			}
		}
		
		
		// foundBuilding isn't null, and it has children
		//TODO rearrange elements on foundBuilding
		
		
		
		//SKIP POINT
		if(this.older!=null && this.older.equals(b))
		{
			this.older = newRoot;
		}
		else if(this.same!=null && this.same.equals(b))
		{
			this.same = newRoot;
		}
		else if (this.younger!=null &&this.younger.equals(b))
		{
			this.younger = newRoot;
		}
		
		//SKIP POINT 2
		if(foundBuilding != null) //if it doesn't have children is already taken care of, FOUNDBUILDING DOES HAVE CHILDREN
		{
			if(foundBuilding.same != null) // if same exists
			{
				newRoot = foundBuilding.same;
				
				newRoot.older = foundBuilding.older;
				newRoot.younger = foundBuilding.younger;
				newRoot.same = foundBuilding.same.same;
			}
			
			else if(foundBuilding.same == null && foundBuilding.older != null) // older exists (younger might exist too)
			{
				newRoot = foundBuilding.older;
				
				if(foundBuilding.younger!= null)
				{
					newRoot = newRoot.addBuildings(foundBuilding.younger);
				}
				
			}
			
			else if(this.same == null && this.older == null) // (older and same will be null) for this to happen
			{
				
				if(this.younger !=null)
				{
					newRoot = foundBuilding.younger;
				}
				else //younger is null, this has no children
				{
					//do nothing		
				}
			}
			return newRoot;
		}
		return this;
	}
	*/
		
	if (b == null) { //useless operation to try to remove nothing from the tree
		return this;
	}
	
	if (this.data == null) { //b/c the whole tree would be null
		return null;
	}
	
	else if (this.data.equals(b)) {
		if(this.same != null) {
			this.data = this.same.data; //make the current Building have data of its same building
			this.same = this.same.same; //adjust the pointers to remove the duplicate building data
		}
		
		else if (this.older != null) {
			this.data = this.older.data;
			this.same = this.older.same;
			this.older = this.older.older;
			 
			Building temp = this.older.younger;
			this.older.younger = null; // set the pointer to null
			this.addBuildings(temp); //reAdd this.older.younger b/c this.younger still exists 
		}
		
		else if(this.younger != null){
			//just make the new root the younger and adjust pointers accordingly
			this.data = this.younger.data;
			this.older = this.younger.older;
			this.same = this.younger.same;
			this.younger = this.younger.younger;
		}
		
		else { // all pointers are null and you're just removing that building, tree becomes null so return null
			data = null;
			return null;
		}
	}
	else { //if its none of the above you are going to have to navigate through the tree.
		
		if(this.older != null && b.yearOfConstruction < data.yearOfConstruction){ //if the building you're trying to remove is older
			this.older = this.older.removeBuilding(b);
		}
		
		else if(this.same != null && b.yearOfConstruction == data.yearOfConstruction && b.height <= data.height){ //if the building you're trying to remove is same age
			this.same = this.same.removeBuilding(b);
		}
		
		else if(this.younger != null && b.yearOfConstruction > data.yearOfConstruction){ // if the building you're trying to remove is younger
			this.younger = this.younger.removeBuilding(b);
		}	
	}
	
	return this; 
}
		
	
	public int oldest(){
	
		int oldestObYear = 0;
		
		oldestObYear = this.data.yearOfConstruction;
		
		if(this.older !=null){
			oldestObYear = this.older.oldest();
		}

		return oldestObYear; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public int highest(){
		
		int rootHeight = this.data.height;
		
		int olderHeight = 0;
		int youngerHeight = 0;
		
		int highest = 0; // will update through recursive steps to always be the highest
		
		if(this.younger != null){
			youngerHeight = this.younger.highest();
		}
		
		if(this.older != null){
			olderHeight = this.older.highest();
		}
		
		if(rootHeight >= youngerHeight && rootHeight >= olderHeight) { //rootHeight is the highest
			highest = rootHeight;
		}
		else if (olderHeight >= youngerHeight){//olderHeight is the highest
			highest = olderHeight;
		}
		else{ //youngerHeight is the highest
			highest = youngerHeight;
		}

		return highest; 
	}
	
	public OneBuilding highestFromYear (int year){
		
		OneBuilding GivenYearsHighest = null; //highest will always be first element you find when traversing the tree though
		
		if (this.data.yearOfConstruction == year){	
			GivenYearsHighest = this.data;
		}
		
		else if (this.older != null && this.data.yearOfConstruction > year){
			GivenYearsHighest = this.older.highestFromYear(year);
		}
		
		else if(this.younger != null && this.data.yearOfConstruction < year){
			GivenYearsHighest = this.younger.highestFromYear(year);
		}
		
		return GivenYearsHighest;
	}
	
	public int numberFromYears (int yearMin, int yearMax){
		
		int totalCt = 0;
		
		int currentCt = 0;
		int youngerCt = 0;
		int sameCt = 0;
		int olderCt = 0;
		
		if(yearMin>yearMax){ // first check otherwise it doesn't make sense
			return 0;
		}
		
		if( this.data.yearOfConstruction>=yearMin && this.data.yearOfConstruction<=yearMax){
			currentCt++;	
		}
		
		if(this.younger != null){
			youngerCt = this.younger.numberFromYears(yearMin, yearMax);
		}
		
		if(this.same != null){
			sameCt = this.same.numberFromYears(yearMin, yearMax);
		}
				
		if(this.older != null){
			olderCt = this.older.numberFromYears(yearMin, yearMax);		
		}
		
		totalCt = currentCt + youngerCt + sameCt +olderCt;
		return totalCt; 
	}
	
	public int[] costPlanning (int nbYears){
		
		int[] repairCosts = new int[nbYears];
		
		for(int i=0; i < nbYears; i++)
		{
			repairCosts[i] = repairCostOfYear(i+2018);
		}

		return repairCosts; 
	}
	
	public int repairCostOfYear(int year)
	{
		int repCostTotal = 0;
		
		int currentRepCost = 0;
		int youngerRepCost = 0;
		int sameRepCost = 0;
		int olderRepCost = 0;
		
		if( this.data.yearForRepair == year){
			currentRepCost += this.data.costForRepair;	
		}
		
		if(this.younger != null){
			youngerRepCost = this.younger.repairCostOfYear(year);
		}
		
		if(this.same != null){
			sameRepCost = this.same.repairCostOfYear(year);
		}
				
		if(this.older != null){
			olderRepCost = this.older.repairCostOfYear(year);	
		}
		
		repCostTotal = currentRepCost + youngerRepCost + sameRepCost + olderRepCost;
		return repCostTotal;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
