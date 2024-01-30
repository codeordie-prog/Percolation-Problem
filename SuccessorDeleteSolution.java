package union_find;

import java.util.Arrays;

/**Given an array S with N elements, write an algorithm with a performance of O(log N) which accomplishes the following:
 * 
 * 1. deletion of element x and finds the successor of x such that the successor is equal or greater than x.
 */

public class SuccessorDeleteSolution {
	
	int [] roots;
	int [] actualList;
	int [] sz;
	int index;
	int successor;
	
	//constructor

	SuccessorDeleteSolution(int [] array){
		
		int length = array.length;
		
		roots = new int[length]; //has the roots of the elements in array with x

		actualList = new int[length]; // has the actualarray the one we have to remove x and find successor

		sz = new int[length]; //monitors the growth of connected elements
		

		/**initiate the arrays. Note actualList will be initiated with actual values of the array.
		 * roots has the roots starting from 0 to length -1.
		 * sz has the sizes initiated as 1.
		 */
		for(int i = 0; i<length;i++) {
			
			roots[i] = i;
			sz[i] = 1;
			actualList[i] = array[i];
		}
		
	}
	
	//method 0 - get index of the given number

	public int getIndex(int x) {
		
		index = 0;
		
		while(actualList[index]!=x) {
			
			index++;
		}
		
		return index;
	}
	
	//method 1 - get the root of x

	public int root(int x) {
		
		int i = getIndex(x); //root of x corresponds to index of x in the roots array.
		
		while(i != roots[i]) { //chase the parents
			
			roots[i] = roots[roots[i]];
			
			i = roots[i];
		}
		
		return i; //root
	}
	

	//method 2 - quick union algorithm

	public void union(int x, int y) {
		
		int rootX = root(x);
		int rootY = root(y);
		
		int indexX = getIndex(x);
		int indexY = getIndex(y);
		
		if(sz[rootX]>sz[rootY]) {
			
			roots[rootY] = rootX;
			sz[rootX] += sz[rootY];
			
			actualList[indexY] = actualList[indexX]; //if you changed root also delete x by changing it to the next number

			successor = actualList[indexX]; // get the successor
		}
		
		else {
			
			roots[rootX] = rootY;
			sz[rootY]+=sz[rootX];
			actualList[indexX] = actualList[indexY];
			successor = actualList[indexY];
		}
	}
	
	//method 3 - delete

	public void delete(int x) {
		
		//get index of x
		
		int indexX = getIndex(x);
		
		
		//next number 
		if((indexX+1)<actualList.length) {
	
			int next_number = actualList[indexX+1];
		
			union(x,next_number);
		}
		else {
			throw new IndexOutOfBoundsException(); 
		}
	}
	
	//method 4 - get the successor after deleting x

	public int findSuccessor(int x) {
		
		delete(x);
		
		return successor;
	}
	

	//test the logic
	public static void main(String[] args) {
		
		int [] initialArray = {24,67,73,75,76,80,81,82,86,90};
		
		SuccessorDeleteSolution sd =  new SuccessorDeleteSolution(initialArray);
		
		System.out.println(Arrays.toString(sd.actualList));
		
		System.out.println(Arrays.toString(sd.roots));
		
		System.out.println(Arrays.toString(sd.sz));
		
		
		System.out.println(sd.getIndex(80));
		
		System.out.println(sd.root(80));
		
		System.out.println(sd.actualList[6]);
		sd.delete(73);
		
		sd.union(80, 81);
		
		System.out.println(Arrays.toString(sd.actualList));
		
		System.out.println(Arrays.toString(sd.roots));
		
		System.out.println(Arrays.toString(sd.sz));
		
		System.out.println("The successor will be : " + sd.findSuccessor(67));
		
		
		
		
		
		
		
		
	}

















}