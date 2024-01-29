package union_find;

/***Given a system of size N*N with random open sites with respect to a given probability, find whether the system percolates.
 * 
 */
public class PercolationSimulation{
	
	/***
	 * The probability determines whether a site is open or not. if the site is open, we check its neighbours on top,left,right and bottom
	 * if any of the neighbour sites is open, we connect them. This will happen for each site on the system.
	 * 
	 * we then iterate through the first and bottom row to check whether their is a site connection, if ther is then the system percolates if not
	 * it doesn't percolate.
	 * 
	 * to accomplish this we need a 2D array for the grid, a parent array to track connections between sites, and the quick union algorithm for
	 * connecting open sites and finding whether sites are connected.
	 */
	
	
	private int [][] grid;  //for the grid
	
	private int[] parent; // for tracking the connected id's
	
	private int gridSize; //size of the grid
	
	
	
		// constructor
	 PercolationSimulation(int size) {
		 
		 gridSize = size;
		 grid = new int[size][size];
		 parent = new int[size*size];
		 
		 
		 for(int i= 0; i<size*size; i++) {
			 
			 parent[i] = i; // each site has its unique id that corresponds to its index  on the array
		 }
	}
	 

	 //method 1 - opening a site

	 public void openSite(double probability) {

		/**iterate over the rows and columns, set the probability and assign value 1 to mark an open site.
		 * Note this is random, so if the probability is low, then the site is likely to remain closed and viceversa
		 */
		 
		 for (int i = 0; i < gridSize * gridSize; i++) {
			    int currentSiteRow = i / gridSize;  // Calculate row index
			    int currentSiteCol = i % gridSize;  // Calculate column index

			    if((double)Math.random()<probability) {
			    	
			    	grid[currentSiteRow][currentSiteCol] = 1;
			    	connectNeighbours(currentSiteRow,currentSiteCol);
			    }
			}
	 }
		 
		

	 //method 2 - checks if the site is open
	 
	 public boolean isOpen(int row, int column) {

		/**logic is if the site is open then its value is 1 */
		 
		 return grid[row][column]==1;
	 }

	 //method 3 - checks whether a row and column are valid
	 
	 public boolean isValid(int row, int column) {
		 
		 return row>=0 && row<gridSize && column>=0 && column<gridSize;
	 }

	 //method 4 - connect the neighbours
	 
	 public void connectNeighbours(int row, int column) {
		 
		 //check left neighbor
		 
		 connectIfOpen(row,column,row,column-1);
		 
		 //check right neighbor
		 
		 connectIfOpen(row,column,row,column+1);
		 
		 //check top
		 
		 connectIfOpen(row,column,row+1,column);
		 
		 //check bottom
		 
		 connectIfOpen(row,column,row-1,column);
		 
		 
	 }
	 
	 //method 5 - connects only open sites

	 private void connectIfOpen(int currentrow, int currentcolumn, int neighbourRow, int neighbourColumn) {
		
		 //connection happens if isValid and isOpen
		 
		 if(isValid(neighbourRow, neighbourColumn) && isOpen(neighbourRow, neighbourColumn)) {
			 
				 
				 //convert sites to 1D array coordinates
				 
				 int currentSite = currentrow * grid.length + currentcolumn; 
				 int neighboursite = neighbourRow * grid.length + neighbourColumn;
				 
				 
				 union(currentSite,neighboursite); //quick union method
			 }
		 }
		
	
	 //method 6 - quick find

	private int find(int site) {

		/***connected sites will have the same value for site == 1 */
		
		while(site != parent[site]) {
			
			parent[site] = parent[parent[site]];
			site = parent[site];
		}
		return site;
	}

	//method 7 - does the system percolate?
	
	private boolean percolates() {

		/***logic - if it does then atleast one of the points on the top row should connect with one of the points on the bottom row 
		 * iterate both top and bottom at the same time while comparing each point
		*/
		
		for (int i = 0; i<gridSize; i++) {
			
			int top = i;
			int bottom = (gridSize*gridSize) - i - 1;
			
			if(isConnected(top,bottom)) {
				
				return true;
			}
		}
		
		return false;
	}
	

	//method 8 - given two sites are they connected?

	private boolean isConnected(int i, int j) {

		/**logic if they are the find method should return same value for both */
		
		return find(i) == find(j);
	}

	//method 9 - quick union algorithm

	private void union(int currentSite, int neighboursite) {
		
		int root_current_site = find(currentSite);
		int root_neighbour_site = find(neighboursite);
		
		
		parent[root_neighbour_site] = root_current_site;
		
	}


	//method 10 - Test
	public static void main(String[] args) {
		 
		 PercolationSimulation sm = new PercolationSimulation(20);
		 
		 sm.openSite(0.6);
		 
		 
		 
		 if(sm.percolates()) {
			 
			 System.out.println("System percolates");
		 }
		 else {
			 
			 System.out.println("System does not percolate");
		 }
		 
		 
		 
		 for (int i = 0; i<sm.grid.length;i++) {
			 
			 for (int j = 0; j<sm.grid.length;j++) {
				 
				 System.out.print(sm.grid[i][j] + " ");
			 }
			 System.out.println();
		 }
		 
		 
		 
		 
	 }
	 
	
	 
	 
	 
	 
	 
	 	 
	 
	 
}