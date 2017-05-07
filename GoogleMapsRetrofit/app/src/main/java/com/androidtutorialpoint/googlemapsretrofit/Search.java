package com.androidtutorialpoint.googlemapsretrofit;

public class Search {
	private Searchable search;
	private Results results;
	private Location location;
	
	private static Search singleton;
	
	//TODO: add constructors, searches, and setter using google places
	
	public static Search getInstance(){
		if(singleton == null){
			singleton = new Search();
		}
		return singleton;
	}
	
	private Search(){
		search = new NoFilterSearch();
		location = null;
		results = null;
	}

	public void setFiltertoPrice()
	{
		search = new PriceSearch();
	}

	public void setFiltertoRestaurants()
	{
		search = new RestaurantSearch();
	}

	public void setFilterDist(){search = new NoFilterSearch();}

	//list of gas stations
	public Results getResults(){
		return results;
	}
	
	public void setLocation(Location loc){
		location = loc;
	}

	// long and lat
	public Location getLocation(){
		return location;
	}
	
	public void search(){
		results = search.search(location);
	}
}
