
public class RestaurantSearch implements Searchable{
	
	private int radius = 5;
	
	public Results search(Location location){
		Results results;
		boolean allHaveRestaurants;
		
		do{
			allHaveRestaurants = true;
			radius += 5;
			results = googleStationSearch(location, radius);			//if we store stations and their info in a database,
			for(int i = 0; i < results.size(); i++){						//can do database search first, then google search if not enough found
				if(results.getStation(i).getBathroomRating() == null){
					allHaveRestaurants = false;
					break;
				}
			}
		}while(!allHaveRestaurants && results.size() == 10);
		radius = 5;
		
		//if we store stations and their info in a database, can get rid of set functions
		setStationPrices(results);
		setRestaurants(results);
		
		sort(results);
		return results;
	}
	
	public Results sort(Results results){
		//put most restaurants first in results
		return results;
	}
}