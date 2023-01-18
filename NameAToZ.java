/**
 * Class that implements compare that makes comparisions on the city
 * objects based on the cityName. The string compareTo is used.
 * 
 * @author Peter Chen
 * @since 1/17/23
 * 
 */
import java.util.Comparator;

public class NameAToZ implements Comparator<City>
{
	/**
	 * Compares two city objects
	 * @param c1	1st city object to be compared
	 * @param c2	2nd city object to be compared
	 * 
	 * @return 		If the city names are the same, returns the population
	 * 				difference. If not, returns the lexigraphical difference
	 * 				between the 2 city strings. 
	 */
	public int compare(City c1, City c2)
	{
		String c1Name = c1.getCityName();
		String c2Name = c2.getCityName();
        if(c1Name.equals(c2Name)) {
            return c2.getPopulation() - c1.getPopulation();
        } else {
            return c1Name.compareTo(c2Name);
        }
	}
}
