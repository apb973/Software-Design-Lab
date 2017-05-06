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
	
	public void setFilter(String filter){
		switch(filter){
		case "price":
			search = new PriceSearch();
			break;
		case "restaurants":
			search = new RestaurantSearch();
			break;
		default:
			search = new NoFilterSearch();
			break;
		}
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
	
	public void search(Location loc){
		setLocation(loc);
		results = search.search(location);
	}
	
	public void search(String filter){
		setFilter(filter);
		results = search.search(location);
	}
	
	public void search(String filter, Location loc){
		setLocation(loc);
		setFilter(filter);
		results = search.search(location);
	}
	
	public void search(Location loc, String filter){
		setLocation(loc);
		setFilter(filter);
		results = search.search(location);
	}
}
