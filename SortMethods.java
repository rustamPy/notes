/*

Rustam Karimov
ID: 17915
Algorithms and Data.

Assignment 2.

*/

package Sorts;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

interface SortOperations{
	public void add(int val);
	public void display_queue();
	public Boolean isEmpty();
}
//-------------------------------Heap Sort ------------------------------------

class HeapSort extends SortMethods implements SortOperations{
		
		
		public void add(int val) {
			user_seq.add(val);
		}
		
		public void sort() {
	        int num = user_seq.size(); //number of nodes in BinaryTree
	 
	        // Build heap (rearrange array)
	        for (int i = num / 2 - 1; i >= 0; i--)	//i=num/2-1; "i" is the maximum height of Heap Tree (i=9/2-1=3 is height), ceiling(log(n-1))
	            heap_sort(user_seq, num, i);			   	//call heap_sort (heapify) function with the array representation, the height of tree and and root index; why Array of Binary Tree, see Documentation.
	 
	        // Extract one by one.
	        for (int i = num - 1; i > 0; i--) {
	           
	            int temp = user_seq.get(0);
	            user_seq.set(0, user_seq.get(i));
	            user_seq.set(i, temp);
	 
	            // call max heap_sort on the reduced part
	            heap_sort(user_seq, i, 0);
	        }
	    }
		
		
		//Recursive heap_sort function
		
		void heap_sort(ArrayList arr, int num, int i) { 
		    int largest = i; // The largest value is root for now
		    //Interesting part; because this is the complete binary tree, we can represent it by simple array.
		    
		    //The left value's index of the root is 2*i+1
		    //The right value's index of the root is 2*i+2
		    
		    int l = 2 * i + 1; // left = 2*i + 1; for example, the root index is 0
		    int r = 2 * i + 2; // right = 2*i + 2

		    // If left child is larger than root
		    if (l < num && (int) arr.get(l) > (int) arr.get(largest))
		        largest = l;

		    // If right child is larger than largest so far
		    if (r < num && (int) arr.get(r) > (int) arr.get(largest))
		        largest = r;

		    // If largest is not root
		    if (largest != i) {

		        
		        int swap = (int) arr.get(i);
	            arr.set(i, arr.get(largest));
	            arr.set(largest, swap);
	 

		        // Recursively heapify the affected sub-tree
		        heap_sort(arr, num, largest);
		    }
		}
		
		public void display_queue() {
			if (user_seq.size()>25) System.out.print(user_seq.subList(0, 25)+ " <-- first 25 sorted values");
			else System.out.print(user_seq+" <-- whole list of sorted values");
		}
		
		public Boolean isEmpty() {
			return user_seq.isEmpty();
		}
}

//-------------------------------Selection Sort ------------------------------------

class SelectionSort extends SortMethods implements SortOperations{
	
	public void add(int val) {
		user_seq.add(val); // Time Complexity for 'add' is O(1). Because we add every value at the end of the list.
	}
	
	public void select_sort() { //Time Complexity for 'select_sort' is O(n^2). See documentation - option 3.
		
		
			
			for (int i=0; i<user_seq.size(); i++) {
				int min=i;
				for (int j=i+1; j<user_seq.size(); j++ ) {
					if (user_seq.get(min)> user_seq.get(j)) {
						min=j;
					}
				}
				int temp=user_seq.get(i);
				user_seq.set(i,user_seq.get(min));
				user_seq.set(min, temp);
			}
		
	}
	
	
	public void display_queue() {
		if (user_seq.size()>25) System.out.print(user_seq.subList(0, 25) + " <-- first 25 sorted values");
		else System.out.print(user_seq+" <-- whole list of sorted values");
	}

	public Boolean isEmpty() {
		return user_seq.isEmpty();
	}
	
}

//-------------------------------Insertion Sort ------------------------------------


class InsertionSort extends SortMethods implements SortOperations {
	
	public void add(int val) {		//Time Complexity for 'add' is O(n^2). See documentation - option 3.
		if (user_seq.isEmpty()) user_seq.add(val);
		else if (user_seq.size()==1) {
			if (user_seq.get(0)<val) user_seq.add(val);
			else user_seq.add(0,val);
		}
		
		else {
			for (int i=0; i<user_seq.size(); i++) {
				if (val<user_seq.get(i)) {
					user_seq.add(i, val);
					return;
				}
			}
			
			user_seq.add(val);
		}
	}
	
	public void insert_sort() {
		//Nothing to do
	}
	
	
	public void display_queue() {
		if (user_seq.size()>25) System.out.print(user_seq.subList(0, 25)+ " <-- first 25 sorted values");
		else System.out.print(user_seq+" <-- whole list of sorted values");
		
	}
	
	public Boolean isEmpty() {
		return user_seq.isEmpty();
	}
	
}


//-------------------------------Driver ------------------------------------

public class SortMethods {
	
	protected ArrayList<Integer> user_seq = new ArrayList<Integer>();
	
	
	public static void main (String[] args) {
		int counter=0;
		Docs documentation = new Docs();
		Random rand=new Random();
		Scanner scan =new Scanner(System.in);
		HeapSort hsor_obj = new HeapSort();
		SelectionSort ssor_obj=new SelectionSort();
		InsertionSort isor_obj=new InsertionSort();
		System.out.print("The script is prepared by Rustam Karimov. ID: 17915. \nFree to use, copy and share.\n\n");
		System.out.print("Welcome to the Selection, Insertion and Heap sort algorithms.\nHere you can check the actual time consumed on all 3 sort mechanisms."
				+ "\n\nChoose the next step (1 - Test random numbers, 2 - Manual inputs, 3 - Compare all, 4 - Documentation): ");
		
		int step=scan.nextInt();
		
		//-------------------------------Documentation ------------------------------------
		if (step==4) {
			System.out.print(documentation);
			return;
		}
		//-------------------------------Compare Sort ------------------------------------
		else if (step==3) {
			System.out.print("How many numbers would you like to test: ");
			int numbers=scan.nextInt();
			System.out.print("The maximum value for random number: ");
			int max_rand=scan.nextInt();
			
			for (int i=0; i<numbers; i++) {
				ssor_obj.add(rand.nextInt(max_rand));
			}
			
			long start_time_sel = System.nanoTime();
			ssor_obj.select_sort();
			long end_time_sel = System.nanoTime();
			double difference_sel = (end_time_sel - start_time_sel) / (1e9);
			
			long start_time_insert = System.nanoTime();
			for (int i=0; i<numbers; i++) {
				isor_obj.add(rand.nextInt(max_rand));
			}
			long end_time_insert = System.nanoTime();
			double difference_insert = (end_time_insert - start_time_insert) / (1e9);
			
			long start_time_heap = System.nanoTime();
			for (int i=0; i<numbers; i++) {
				hsor_obj.add(rand.nextInt(max_rand));
				}
			hsor_obj.sort();
			long end_time_heap = System.nanoTime();
			double difference_heap = (end_time_heap - start_time_heap) / (1e9);
					
			System.out.print("--------------------------------------------------------------+"
					+ "\n\t\t\tSort methods - Compare\t\t      |"
					+"\n--------------------------------------------------------------+"
					+ "\nNumber of random positive integers used: "+numbers+"\t\t      |"
					+"\n--------------------------------------------------------------+"
					+ "\nSelection sort: "+difference_sel+ " second(s).\t\t\t      |"
					+ "\nInsertion sort: "+difference_insert+ " second(s).\t\t\t      |"
					+ "\nHeap sort: "+difference_heap+" second(s).\t\t\t\t      |"
					+"\n--------------------------------------------------------------+\n");
			hsor_obj.display_queue();
			
					
			return;
			
		}
		
		
		//-------------------------------Test (automate random inputs in defined amount) ------------------------------------
		
		if (step==1) {
				System.out.print("Please, choose the sort method you want use (1 - Selection Sort, 2 - Insertion Sort, 3 - Heap Sort): ");
				int option=scan.nextInt();
		
				if (option==1) {
					System.out.print("How many numbers would you like to test: ");
					int numbers=scan.nextInt();
		
					System.out.print("The maximum value for random number: ");
					int max_rand=scan.nextInt();
					
					
					
					for (int i=0; i<numbers; i++) {
							ssor_obj.add(rand.nextInt(max_rand));
						}
					long start_time = System.nanoTime();
					ssor_obj.select_sort();
					long end_time = System.nanoTime();
					double difference = (end_time - start_time) / (1e9);
					
					System.out.print("\n\nSELECTION SORT was used!\n\nThe amount of random numbers: "+numbers+"\nThe maximum value for random number:  "
							+ max_rand+"\nThe time (in seconds) spent on SELECTION sorting method is: "+difference+"\nThe whole list or the first 25 values of the Sorted values' list\n");
					ssor_obj.display_queue();
				}
				
				
				
				else if (option==2) {
					
					System.out.print("How many numbers would you like to test: ");
					int numbers=scan.nextInt();
		
					System.out.print("The maximum value for random number: ");
					int max_rand=scan.nextInt();
					
					
					
					long start_time = System.nanoTime();
					for (int i=0; i<numbers; i++) {
						isor_obj.add(rand.nextInt(max_rand));
					}
					long end_time = System.nanoTime();
					double difference = (end_time - start_time) / (1e9);
					
					System.out.print("\n\nINSERTION SORT was used!\n\nThe amount of random numbers: "+numbers+"\nThe maximum value for random number:  "
							+ max_rand+"\nThe time (in seconds) spent on INSERTION sorting method is: "+difference+"\nThe whole list or the first 25 values of the Sorted values' list\n");
					isor_obj.display_queue();
				}
				
				else if (option==3) {
					
					
					System.out.print("How many numbers would you like to test: ");
					int numbers=scan.nextInt();
		
					System.out.print("The maximum value for random number: ");
					int max_rand=scan.nextInt();
					
					long start_time = System.nanoTime();
					
					for (int i=0; i<numbers; i++) {
							hsor_obj.add(rand.nextInt(max_rand));
						}
					hsor_obj.sort();
					long end_time = System.nanoTime();
					double difference = (end_time - start_time) / (1e9);
					
					System.out.print("\n\nHEAP SORT was used!\n\nThe amount of random numbers: "+numbers+"\nThe maximum value for random number:  "
							+ max_rand+"\nThe time (in seconds) spent on HEAP sorting method is: "+difference+"\nThe whole list or the first 25 values of the Sorted values' list\n");
					hsor_obj.display_queue();
				}
				
				else {
					System.out.print("Wrong input");
				}
				
				
		}
		
		//-------------------------------Manual inputs by user (one-by-one; -1 to quit) ------------------------------------
		
		else if (step==2) {
			
				System.out.print("Please, choose the sort method you want use (1 - Selection Sort, 2 - Insertion Sort, 3 - Heap Sort): ");
				int option=scan.nextInt();
					if (option==1) {
						
						while (true) {
							System.out.print("You input (-1 to quit): ");
							int input=scan.nextInt();
							
							if (input==-1) {
								if (ssor_obj.isEmpty()) {
									System.out.print("The list is empty. Nothing to sort!");
									return;
								}
								else {
									
									long start_time = System.nanoTime();
									ssor_obj.select_sort();
									long end_time = System.nanoTime();
									double difference = (end_time - start_time) / (1e9);
									System.out.print("\n\nSELECTION SORT was used!\n\nYou used: "+counter+" "
											+ "numbers\nThe time (in seconds) spent on SELECTION sorting method is: "+difference+""
											+ "\nThe whole list or the first 25 values of the Sorted values' list\n");
									ssor_obj.display_queue();
									return;
								}
							}
							
							ssor_obj.add(input);
							counter++;
							
							
							
						}
					}
					
					else if(option==2) {
						double time=0;
						
						while (true) {
							System.out.print("You input (-1 to quit): ");
							int input=scan.nextInt();
							
							if (input==-1) {
								if (isor_obj.isEmpty()) {
									System.out.print("The list is empty. Nothing to sort!");
									return;
								}
								else {
									
									
									
									System.out.print("\n\nINSERTION SORT was used!\n\nYou used: "+counter+" "
											+ "numbers\nThe time (in seconds) spent on INSERTION sorting method is: "+time
											+ "\nThe whole list or the first 25 values of the Sorted values' list\n");
									isor_obj.display_queue();
									
									return;
								}
							}
							long start_time = System.nanoTime();
							isor_obj.add(input);
							long end_time = System.nanoTime();
							
							time+=(end_time-start_time)/(1e9);
							counter++;
							
							
							
						}
						
					}
					
					else if (option==3) {
						
						
						
						while (true) {
							
							System.out.print("You input (-1 to quit): ");
							int input=scan.nextInt();
							
							if (input==-1) {
								if (hsor_obj.isEmpty()) {
									System.out.print("The list is empty. Nothing to sort!");
									return;
								}
								else {
									long start_time = System.nanoTime();
									hsor_obj.sort();
									long end_time = System.nanoTime();
									double difference = (end_time - start_time) / (1e9);
									System.out.print("\n\nHEAP SORT was used!\n\nYou used: "+counter+" "
											+ "numbers\nThe time (in seconds) spent on HEAP sorting method is: "+difference+""
											+ "\nThe whole list or the first 25 values of the Sorted values' list\n");
									hsor_obj.display_queue();
									return;
								}
							}
							
							hsor_obj.add(input);
							counter++;
							
							
							
						}
					}
					
					else System.out.print("Wrong input");
		}
		
		
		//-------------------------------Quit if wrong input ------------------------------------
		
		else System.out.print("Wrong input");
	
	}
}

