import java.util.Comparator;

public class PopulationLeastToGreatest implements Comparator<City>
{
	public int compare(City c1, City c2)
	{
		return c1.compareTo(c2);
	}
}
