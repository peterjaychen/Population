import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Peter Chen
 *	@since	1/12/23
 */
public class Population {
	
	// List of cities
	private List<City> cities; // array that contains all the city objects
							// read in from the textfile
	private int choice; // choice the user has made in the menu
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	public Population() {
		cities = new ArrayList<City>();
	}
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("\n1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	public static void main(String [] args) {
		Population pop = new Population();
		pop.popRun();
	}
	
	public void popRun() {
		printIntroduction();
		while(true)
		{
			cities.clear();
			getTextFile();
			printMenu();
			getInput();
		}
	}
	/**
	 * Reads text file and stores information into cities array. 
	 */
	public void getTextFile() {
		Scanner populationReader = FileUtils.openToRead(DATA_FILE);
        populationReader.useDelimiter("[\t\n]");
        while(populationReader.hasNext())
        {
			String stateName = populationReader.next();
			
			String cityName = populationReader.next();
			String type = populationReader.next(); 
			// System.out.println("Processing " + cityName);
			int population = populationReader.nextInt();

			City c = new City(cityName, stateName, type, population);
			cities.add(c);
		}
	}
	/**
	 * Gets the user's input from the terminal and stores the choice into 
	 * variable choice. 
	 */
	public void getInput() {
		boolean validInput = false;
		while(!validInput) {
			choice = Prompt.getInt("Choose from the menu");
			if(choice >= 1 && choice <= 6 || choice == 9) {
				validInput = true;
			}
		}
		if(choice == 1) 
			sort1();
		else if(choice == 2)
			sort2();
		else if(choice == 3)
			sort3();
		else if(choice == 4)
			sort4();
		else if(choice == 5)
			sort5();
		else if(choice == 6)
			sort6();
		else if(choice == 9)
			quit();
	}
	
		
	
	/**
	 * Sort with the least populous cities 
	 */
	public void sort1() {
		System.out.println("50 least populous cities");
		long startMillisec = System.currentTimeMillis();
		SortMethods sm = new SortMethods();
		sm.selectionSort(cities);
		long endMillisec = System.currentTimeMillis();
		print50Cities();
		System.out.println("Time elapsed: " + (endMillisec - startMillisec) + " milliseconds");
	}
	/**
	 * Sort with most populus cities
	 */
	public void sort2() {
		System.out.println("Fifty most populous cities");
		long startMillisec = System.currentTimeMillis();
		SortMethods sm = new SortMethods();
		sm.mergeSort(cities, new PopulationGreatestToLeast());
		long endMillisec = System.currentTimeMillis();
		print50Cities();
		System.out.println("Time elapsed: " + (endMillisec - startMillisec) + " milliseconds");
	}
	/**
	 * Sort cities by name
	 */
	public void sort3() {
		System.out.println("Fifty cities sorted by name");
		long startMillisec = System.currentTimeMillis();
		SortMethods sm = new SortMethods();
		sm.insertionSort(cities, new NameAToZ());
		long endMillisec = System.currentTimeMillis();
		print50Cities();
		System.out.println("Time elapsed: " + (endMillisec - startMillisec) + " milliseconds");
	}
	/**
	 * Sort cities by name descending 
	 */
	public void sort4() {
		System.out.println("Fifty cities sorted by name descending");
		long startMillisec = System.currentTimeMillis();
		SortMethods sm = new SortMethods();
		sm.mergeSort(cities, new NameZToA());
		long endMillisec = System.currentTimeMillis();
		print50Cities();
		System.out.println("Time elapsed: " + (endMillisec - startMillisec) + " milliseconds");
	}
	/**
	 * Most populous cities given a state
	 */
	public void sort5() {
		boolean validInput = false;
		while(!validInput)
		{
			String stateNameIn = Prompt.getString("Enter state name");
			if(searchState(cities, stateNameIn) == -1) {
				validInput = false;
				System.out.println("ERROR: " + stateNameIn + " is not valid");
			} else {
				System.out.println("Fifty most populous cities in " + stateNameIn);
				long startMillisec = System.currentTimeMillis();
				validInput = true;
				for(int i = 0; i < cities.size(); i++)
				{	
					if(!cities.get(i).getStateName().equals(stateNameIn))
					{
						cities.remove(i);
						i--;
					}
					else
					{
						i++;
					}
				}
				SortMethods sm = new SortMethods();
				sm.mergeSort(cities, new PopulationGreatestToLeast());
				long endMillisec = System.currentTimeMillis();
				print50Cities();
				System.out.println("Time elapsed: " + (endMillisec - startMillisec) + " milliseconds");
			}
		}
	}
	/**
	 * Given city, sort population
	 */
	public void sort6() {
		boolean validInput = false;
		while(!validInput)
		{
			String cityNameToMatch = Prompt.getString("Enter city name");
			long startMillisec = System.currentTimeMillis();
			if(searchCity(cities, cityNameToMatch) == -1) {
				validInput = false;
				System.out.println("ERROR: " + cityNameToMatch + " is not valid");
			} else {
				
				validInput = true;

				for(int i = 0; i < cities.size(); i++)
				{	
					String currentCity = cities.get(i).getCityName();

					if(!(cityNameToMatch.equals(currentCity)))
					{
						cities.remove(i);
 						i--;
					}
				}
				long endMillisec = System.currentTimeMillis();
				System.out.println("City " + cityNameToMatch + " by population");
				print50Cities();
				System.out.println("Time elapsed: " + (endMillisec - startMillisec) + " milliseconds");
			}
		}
	}

	/**
	 * Print up to 50 Cities, or the size of the Cities List, whichever is smaller
	 * smaller needed for choices (5 and 6)
	 */
	public void print50Cities() {
		
		System.out.printf("    %-22s %-22s %-12s %12s\n", "State", "City", "Type", "Population");
		for(int i = 1; i <= Math.min(50, cities.size()); i++) {
			System.out.printf("%2d: ", i);
			System.out.println(cities.get(i - 1));
		}
	}
	
	/**
	 * Methods that searches for the index of the state
	 * 
	 * @param  arr 		Array that contains list of city objects
	 * @param  search	String of the stateName that is being searched 
	 * 
	 * @return index of the state in arr if found, it not, returns -1
	 */
	public int searchState(List <City> arr, String search) {
		int left = 0;
		int right = arr.size()-1;
		
		while(left <= right)
		{
			int mid = (left + right)/2;
			int compare = search.compareTo(arr.get(mid).getStateName());

			if(compare == 0 )
				return mid;
			else if(compare < 0)
				right = mid - 1;
			else 
				left = mid + 1;
		}	
		return -1; // not found			
	}
	
	/**
	 * Methods that searches for the index of the city
	 * 
	 * @param  arr 		Array that contains list of city objects
	 * @param  search	String of the cityName that is being searched 
	 * 
	 * @return index of the city in arr if found, it not, returns -1
	 */
	public int searchCity(List <City> arr, String search) {
		int left = 0;
		int right = arr.size()-1;
		
		while(left <= right)
		{
			SortMethods sm = new SortMethods();
			sm.mergeSort(cities, new NameAToZ());
			int mid = (left + right)/2;
			int compare = search.compareTo(arr.get(mid).getCityName());

			if(compare == 0)
				return mid;
			else if(compare < 0)
				right = mid - 1;
			else 
				left = mid + 1;
		}	
		return -1; // not found			
	}
	
	public void quit() {
		System.out.println("Thanks for using population!");
		System.exit(0);
	}
}
