
public class PriceSearch extends Queryable implements Searchable {
	
	private int radius = 5;
	
	public Results search(Location location){
		Results results;
		
		do{
			radius += 5;
			results = googleStationSearch(location, radius);	//if we store stations and their info in a database,
		}while(results.size() == 10);								//can do database search first, then google search if not enough found
		radius = 5;
		
		//if we store stations and their info in a database, can get rid of set functions
		setStationPrices(results);
		setRestaurants(results);
		
		results = sort(results);
		return results;
	}
	
	public Results sort(Results results){
		Results sorted = new Results();
		Station next;
		while(results.size() > 0){
			next = results.getStation(0);
			for(int i = 1; i < results.size(); i++){
				if(next.getPrice() > results.getStation(i).getPrice()){
					next = results.getStation(i);
				}
			}
			sorted.addStation(next);
			results.removeStation(next);
		}
		return sorted;
	}
}
