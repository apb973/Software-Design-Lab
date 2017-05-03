package com.androidtutorialpoint.googlemapsretrofit;

public class Search {
	private Searchable search;
	private Results results;
	private Location location;
	
	private static Search singleton;
	
	//TODO: add constructors, searches, and setter using google places
	
	public Search getInstance(){
		if(singleton == null){
			singleton = new Search();
		}
		return singleton;
	}
	
	public Search(){
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
	
	public Results getResults(){
		return results;
	}
	
	public void setLocation(Location loc){
		location = loc;
	}
	
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
