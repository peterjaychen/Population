/**
 * Class that implements compare that makes comparisions on the city
 * objects based on the population in the city object. 
 * The city compareTo is used.
 * 
 * @author Peter Chen
 * @since 1/17/23
 * 
 */
import java.util.Comparator;

public class PopulationGreatestToLeast implements Comparator<City>
{
	/**
	 * Compares two city objects
	 * @param c1	1st city object to be compared
	 * @param c2	2nd city object to be compared
	 * 
	 * @return 		If the populations are not the same, returns the population
	 * 				difference. If not, returns the lexigraphical difference
	 * 				between the 2 state strings in the city object. If the state 
	 * 				names are the same, returns lexigraphical difference of the 
	 * 				city names. 
	 */ 
	public int compare(City c1, City c2)
	{
		return c2.compareTo(c1);
	}
}
