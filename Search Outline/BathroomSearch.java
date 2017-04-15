
public class BathroomSearch implements Searchable{
	
	private int radius = 5;
	
	public Results search(Location location){
		Results results;
		boolean allHaveBathrooms;
		
		do{
			allHaveBathrooms = true;
			radius += 5;
			results = googleStationSearch(location, radius);			//if we store stations and their info in a database,
			for(int i = 0; i < results.size(); i++){						//can do database search first, then google search if not enough found
				if(results.getStation(i).getBathroomRating() == null){
					allHaveBathrooms = false;
					break;
				}
			}
		}while(!allHaveBathrooms && results.size() == 10);
		radius = 5;
		
		//if we store stations and their info in a database, can get rid of set functions
		setStationPrices(results);
		setRestaurants(results);
		
		results = sort(results);
		return results;
	}
	
	public Results sort(Results results){
		//put highest rating first in results
		Results sorted = new Results();
		Station next;
		while(results.size() > 0){
			next = results.getStation(0);
			for(int i = 1; i < results.size(); i++){
				if(next.getBathroom().getRating() < results.getStation(i).getBathroom().getRating()){
					next = results.getStation(i);
				}
			}
			sorted.addStation(next);
			results.removeStation(next);
		}
		return sorted;
	}
}
