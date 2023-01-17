import java.util.Comparator;

public class NameAToZ implements Comparator<City>
{
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