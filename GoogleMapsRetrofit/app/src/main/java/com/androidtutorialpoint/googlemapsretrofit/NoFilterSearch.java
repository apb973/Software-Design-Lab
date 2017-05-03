package com.androidtutorialpoint.googlemapsretrofit;

public class NoFilterSearch extends Queryable implements Searchable{
	
	
	public Results search(Location location){
		Results finalResults = initialQuery(location);
		Station finalResult;
		int finalSize = finalResults.size();
		
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
		//put closest station first
		
		//should already be in order
		return results;
	}
}
