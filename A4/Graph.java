package assignment4Graph;

public class Graph {
	
	boolean[][] adjacency;
	int nbNodes;
	
	public Graph (int nb){
		this.nbNodes = nb;
		this.adjacency = new boolean [nb][nb];
		for (int i = 0; i < nb; i++){
			for (int j = 0; j < nb; j++){
				this.adjacency[i][j] = false;
			}
		}
	}
	
	public void addEdge (int i, int j){
		// ADD YOUR CODE HERE
		if(i >= 0 && j >= 0 && this.nbNodes > i && this.nbNodes > j) {
			this.adjacency[i][j] = true;
			this.adjacency[j][i] = true;
		}
		else {
			return;
		}
	}
	
	public void removeEdge (int i, int j){
		// ADD YOUR CODE HERE
		if(i >= 0 && j >= 0 && this.nbNodes > i && this.nbNodes > j) {
			this.adjacency[i][j] = false;
			this.adjacency[j][i] = false;
		}
		else {
			return;
		}
	}
	
	public int nbEdges(){
		// ADD YOUR CODE HERE
		int count = 0;
		int i = 0;
		int j = 0;
		for (i = 0; i < this.nbNodes; i++) {
			for(j = i; j < this.nbNodes; j++) {
				if(this.adjacency[i][j]) {
					count++;
				}
			}
		}
		return count; // DON'T FORGET TO CHANGE THE RETURN
	}
	
	public boolean cycle(int start){
		// ADD YOUR CODE HERE
		int[] child = new int[this.nbNodes];
		int[] visited = new int[this.nbNodes];
		child[start] = 1;
		visited[start] = 1;
		
		for(int i=0; i< this.nbNodes; i++) {
			if(this.adjacency[start][i] && start != i) {
				child[i] = 1;
			}	
		}
		
		
		for(int i=0; i< this.nbNodes; i++) {
			if(this.adjacency[start][i] && start != i) {
				if(exploreCyc(i, child,visited)) {
					return true;
				}	
			}	
		}
	
		return false; 
	}
	
	public boolean exploreCyc(int currentNode, int[] child, int[] visited) {
		
		visited[currentNode] = 1;
		
		for(int j = 0; j < this.nbNodes; j++) {
			if(this.adjacency[currentNode][j] && visited[j] == 0 && j != currentNode) {
				if(child[j] == 1) {
					return true;
				}
				else {
					if(exploreCyc(j, child, visited)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	
	public int shortestPath(int start, int end){
		// ADD YOUR CODE HERE
		int[] visited = new int[this.nbNodes];
		
		if(end == start) {
			return 0;
		}
		
		for(int i = 0; i < this.nbNodes; i++) {
			visited[i] = this.nbNodes + 1;
		}

		visited[start] = 0;

		for(int i = 0; i < this.nbNodes; i++) {
			if(this.adjacency[start][i] && i != start) {
				visitSP(i, end, visited, 1);
			}
		}
		
		return visited[end]; // DON'T FORGET TO CHANGE THE RETURN
	}
	
	public void visitSP(int currentNode, int end, int[] visited, int depth) {
		
		visited[currentNode] = depth;
		
		for(int i = 0; i < this.nbNodes; i++) {
			if(this.adjacency[currentNode][i] && i != currentNode) {
				if(1 + depth < visited[i]) {
					int depthAdd = depth + 1;
					visitSP(i, end, visited, depthAdd);
				}
			}

		}
		
	}
	
}
