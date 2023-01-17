import java.util.Comparator;

public class PopulationGreatestToLeast implements Comparator<City>
{
	public int compare(City c1, City c2)
	{
		return c2.compareTo(c1);
	}
}