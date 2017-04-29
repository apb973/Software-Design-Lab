
public class RestaurantSearch extends Queryable implements Searchable{
	
	public Results search(Location location){
		Results finalResults = initialQuery(location);
		Station finalResult;
		int finalSize = finalResults.size();
		
		for(int i = 0; i < finalSize; i++){
			finalResult = finalResults.getStation(i);
			finalResult.setRestaurants(googleRestaurants(finalResult.getLocation()));
		}
		
		finalResults = sort(finalResults);
		
		for(int i = 5; i < finalSize; i++){
			finalResults.removeStation(i);
		}
		
		return finalResults;
	}
	
	public Results sort(Results results){
		//put most restaurants first in results
		Results sorted = new Results();
		Station next;
		while(results.size() > 0){
			next = results.getStation(0);
			for(int i = 1; i < results.size(); i++){
				if(next.getRestaurants().size() < results.getStation(i).getRestaurants().size()){
					next = results.getStation(i);
				}
			}
			sorted.addStation(next);
			results.removeStation(next);
		}
		return sorted;
	}
}
