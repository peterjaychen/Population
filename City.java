/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Peter Chen
 *	@since	1/9/23
 */
public class City implements Comparable<City> {
	
	// fields
	private String cityName;
	private String stateName;
	private String cityType;
	private int population;
	// constructor
	public City() {
		cityName = "";
		stateName = "";
		cityType = "";
		population = 0;
	}

	public City(String cityNIn, String stateNIn, String cityTIn, int popIn) {
		cityName = cityNIn;
		stateName = stateNIn;
		cityType = cityTIn;
		population = popIn;
	}
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City other) {
		if(this.population != other.population) {
			return this.population - other.population;
		} else if(!this.stateName.equals(other.stateName)) {
			return this.stateName.compareTo(other.stateName);
		} else {
			return this.cityName.compareTo(other.cityName);
		}
	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equals(City other) {
		if(this.stateName.equals(other.stateName) && this.cityName.equals(other.stateName))
		{
			return true;
		} else {
			return false;
		}
	}
	/**	Accessor methods */
	/**
	 * @return the cityName from the City object
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @return the stateName from the City object
	 */
	public String getStateName() {
		return stateName;
	}
	/**
	 * @return the cityType from the City object
	 */
	public String getCityType() {
		return cityType;
	}
	/**
	 * @return the population from the City object
	 */ 
	public int getPopulation() {
		return population;
	}
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", stateName, cityName, cityType,
						population);
	}
}
