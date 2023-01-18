import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Peter Chen
 *	@since	12/5/2022
 */

public class SortMethods {
	
	public void bubbleSort(List<City> arr) {
		bubbleSort(arr, new PopulationLeastToGreatest());
	}

	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(List<City> arr, Comparator<City> compClass) {
		for(int outer = arr.size() - 1; outer > 0; outer--) {
			for(int inner = 0; inner < outer; inner++) {
				if(compClass.compare(arr.get(inner), arr.get(inner + 1)) > 0) {
					swap(arr, inner, inner+1);
				}
			}
		}
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City> arr, int x, int y) {
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
		/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(List<City> arr) {
		// By default, if you don't specify a specific kind of sort, you get the population sort specified in compareTo
		selectionSort(arr, new PopulationLeastToGreatest());
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(List<City> arr, Comparator<City> compClass) {
		int largestIndex = 0; 
        for(int i = arr.size() - 1; i > 0; i--) {
			largestIndex = i;
            for(int j = 0; j < i; j++) {
                
                if(compClass.compare(arr.get(j), arr.get(largestIndex)) > 0) {
					largestIndex = j;
				}
			}
			swap(arr, largestIndex, i);
        }
	}
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(List<City> arr) {
		// By default, if you don't specify a specific kind of sort, you get the population sort specified in compareTo
		insertionSort(arr, new PopulationLeastToGreatest());
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(List<City> arr, Comparator<City> compClass) {
		for (int i = 0; i < arr.size(); i++)
		{
			for (int insertLocation = 0; insertLocation < i; insertLocation++) {
				if (compClass.compare(arr.get(i), arr.get(insertLocation)) < 0) {
					City temp = arr.remove(i);
					arr.add(insertLocation, temp);
				}
			}
		}
	}
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSort(List<City> arr) {
		// By default, if you don't specify a specific kind of sort, you get the population sort specified in compareTo
		mergeSort(arr, new PopulationLeastToGreatest());
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 * 	@param compClass	
	 */
	public void mergeSort(List<City> arr, Comparator<City> compClass) {
		int length = arr.size();
		recursiveSort(arr, compClass, 0, length - 1);
	}
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param array		array of Integer objects to sort
	 * 	@param compClass	Class that implements the compare of 2 cities
	 * 	@param beg			Begining index of the array
	 * 	@param end 			End index of the array
	 */
	public void recursiveSort(List<City> array, Comparator<City> compClass, int beg, int end) {
		if(end - beg < 2) {
			if(end > beg && compClass.compare(array.get(end), array.get(beg)) < 0) {
				City temp = array.get(end);
				array.set(end, array.get(beg));
				array.set(beg, temp);
			}
		} else {
			int middle = (beg + end)/ 2;
			recursiveSort(array, compClass, beg, middle);
			recursiveSort(array, compClass, middle + 1, end);
			merge(array, compClass, beg, middle, end);
		}
	}
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param array		array of Integer objects to sort
	 * 	@param compClass	Class that implements the compare of 2 cities
	 * 	@param beg			Begining index of the array
	 * 	@param middle 		Middle index of the search (beg+end)/2
	 * 	@param end 			End index of the array
	 * 
	 */
	public void merge(List<City> array, Comparator<City> compClass, int beg, int middle, int end) {
		
		List <City> temp = new ArrayList<City>(array.size());
		
		int i = beg;
		int j = middle + 1;
		
		while(i <= middle && j <= end) {
			if(compClass.compare(array.get(i), array.get(j)) < 0) {
				temp.add(array.get(i));
				i++;
			} else {
				temp.add(array.get(j));
				j++;
			}
		}
		
		while(i <= middle) {
			temp.add(array.get(i));
			i++;
		}
		
		while(j <= end) {
			temp.add(array.get(j));
			j++;
		}
		
		int k = beg;
		for(City c:temp) {
			array.set(k, c);
			k++;
		}
			
	}
}
