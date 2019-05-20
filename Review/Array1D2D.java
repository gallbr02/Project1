
public class Array1D2D {

	//Store a rectangular 2D array in a 1D array
	/*
	 * 		0	1	2	3 < columns
	 *   0	a	b	c	d
	 *   1	e	f	g	h
	 *   2	i	j	k	l
	 *   ^ rows
	 *   
	 *   0 1 2 3 4 5 6 7 8 9 10 11  < index
	 *   a b c d e f g h i j k  l
	 */
	
	public static final int ROWS = 3;
	public static final int COLS = 4;
	
	public static void main(String[] args) {
		//create the 1D array
		int[] arr = new int[ROWS*COLS];
		
		//treat the data as if it is a 2D array
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				//translate i and j into an index
				int index = i*COLS + j;
				arr[index] = index;
			}
		}
		
		//print them by going through the 1d array
		for(int i = 0; i < arr.length; i++){
			//print row, column and the value in the array
			//translate i into r and c
			int r = i / COLS;  //integer division
			int c = i % COLS;  //remainder
			System.out.printf("(%d, %d):%d\t", r, c, arr[i]);
			
			//insert a new line after every row
			if(c == COLS - 1){
				System.out.println();
			}
				
		}

	}

}