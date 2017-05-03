package com.androidtutorialpoint.googlemapsretrofit;


public class PriceSearch extends Queryable implements Searchable {
	
	public Results search(Location location){
		Results finalResults = initialQuery(location);
		Station finalResult;
		int finalSize = finalResults.size();
		
		finalResults = sort(finalResults);
		
		for(int i = 5; i < finalSize; i++){
			finalResults.removeStation(i);
		}
		
		finalSize = finalResults.size();
		for(int i = 0; i < finalSize; i++){
			finalResult = finalResults.getStation(i);
			finalResult.setRestaurants(googleRestaurants(finalResult.getLocation()));
		}
		return finalResults;
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

