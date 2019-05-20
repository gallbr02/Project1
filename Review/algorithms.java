import java.util.Arrays;

public class algorithms {
	
	public static final int ARRAY_SIZE = 100000;
	public static final int MAX_VALUE = 10*ARRAY_SIZE;
	public static final int TRIALS = 100000;

	public static void main(String[] args) {
		int[] testData = {6, 5, 3, 7, 2, 4, 1};
		selectionSort(testData);
		
		//print it
		for(int i = 0; i < testData.length; i++){
			System.out.printf("%d ", testData[i]);
		}
		System.out.println();
		
		System.out.println("Testing...");
		//Test the duration of search algorithms

		//Generate data
		int[] data = generateData();
		long tMinus0 = System.currentTimeMillis();
		//sort it from low to high
		//Arrays.sort(data);
		selectionSort(data);
		
		//count how many are found
		int found = 0;
		
		//look at the clock
		long tMinus1 = System.currentTimeMillis();
		
		//repeatedly search for something
		for(int i = 0; i < TRIALS; i++){
			// pick a number
			int r = (int)(MAX_VALUE*Math.random());
			// look for it
			//int index = linearSearch(r, data);
			int index = binarySearch(r, data);
			if(index >= 0){
				found++;
			}
		} //end for
		
		long tMinus2 = System.currentTimeMillis();
		
		//calculate the change in time
		double sec = (tMinus2 - tMinus1)/1000.0;
		
		System.out.printf("Sort time %f seconds.\n", (tMinus1 - tMinus0)/1000.0);
		System.out.printf("Found %d out of %d in %f seconds.\n", 
				found, TRIALS, sec);
	}// end main
	
	public static int linearSearch(int value, int[] data){
		//go through each item
		for(int i = 0; i < data.length; i++){
			if(data[i] == value){
				//found it!
				return i;
			}
		}
		
		//didn't find it
		return -1;
	}
	
	public static int binarySearch(int value, int[] data){
		int low = 0;
		int high = data.length - 1;
		
		//keep going as long as low is still <= to high
		while(low <= high){
			//find the middle index
			int mid = (low + high)/2;
			
			//did I find it
			if(value == data[mid]){
				//found it!
				return mid; //return the location (index) of the value
			}
			else if(value < data[mid]){
				high = mid -1;
			}
			else {  //if(value > data[mid])
				low = mid + 1;
			}
		}
		//did not find it
		return -1;
	}
	
	
	public static int[] generateData(){
		int[] result = new int[ARRAY_SIZE];
		
		for(int i = 0; i < result.length; i++){
			result[i] = (int)(MAX_VALUE*Math.random());
		}
		
		return result;
	}
	
	public static void selectionSort(int[] data){
		//for each index in data
		//   find the minimum index
		//   swap value into place
		
		//stop at second to last one
		for(int i = 0; i < data.length - 1; i++){
			int minIdx = findMinimumIndex(data, i);
			if(i != minIdx){
				//must swap
				swap(data, i, minIdx);
			}
		}
	}

	//find the index of the smallest value
	//parameters:
	//  data: array of data
	//  start: index to begin the search
	public static int findMinimumIndex(int[] data, int start){
		//store the current minimum's location
		//  first one is start
		int index = start; 
		
		for(int i = start+1; i < data.length; i++){
			if(data[i] < data[index]){
				index = i;
			} //end if
		} //end for
		return index;
	}
	
	//swap
	//parameters:
	//  data: the array of ints
	//  a, b: index of values to swap
	public static void swap(int[] data, int a, int b){
		//this does not work
		//data[a] = data[b];
		//data[b] = data[a];
		int temp = data[a];
		data[a] = data[b];
		data[b] = temp;
	}
	
}