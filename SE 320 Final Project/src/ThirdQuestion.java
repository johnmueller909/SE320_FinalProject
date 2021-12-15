/*
 Code By John-Patrick Mueller
 12/14/21
 SE 320
 */

public class ThirdQuestion {
	
	public static <E extends Comparable<E>> int linearSearch(E[] list, E value) {
        
		//This loops through the list and returns the first index value, if not found returns -1
		for (int i = 0; i < list.length; i++) {
			
			if (list[i].compareTo(value) == 0) {
				
				return i;
			}
		}
		 return -1;
    } 

	//Main class used for testing
	public static void main(String[] args) {
		
		Integer[] list = { 3, 4, 5, 1, -3, -5, -1 };
		System.out.println(linearSearch(list, 2));
		System.out.println(linearSearch(list, 5));
		
	}
}
