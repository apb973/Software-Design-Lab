
public class NoFilterSearch extends Queryable implements Searchable{
	
	private int radius = 5;
	
	public Results search(Location location){
		Results results;
		
		do{
			radius += 5;
			results = googleStationSearch(location, radius); //if we store stations and their info in a database,
		}while(results.size() == 10);							//can do database search first, then google search if not enough found
		radius = 5;
		
		//if we store stations and their info in a database, can get rid of set functions
		setStationPrices(results);
		setRestaurants(results);
		
		sort(results);
		return results;
	}
	
	public Results sort(Results results){
		return results;
	}
}
