package com.androidtutorialpoint.googlemapsretrofit;

public class Station {
	private Restaurants restaurants;
	private double price;
	private Location location;
	private String name;
	private String address;
	
	public Station(){
		restaurants = new Restaurants();
		price = 0;
		location = new Location();
		name = "";
		address = "";
	}

	public Station(double price, String address) {
		restaurants = new Restaurants();
		this.price = price;
		location = new Location();
		name = "";
		this.address = address;
	}

	public String getName(){
		return name;
	}
	
	public String getAddress(){
		return address;
	}
	
	public Restaurants getRestaurants(){
		return restaurants;
	}
	
	public double getPrice(){
		return price;
	}
	
	public Location getLocation(){
		return location;
	}
	
	public void setRestaurants(Restaurants toChange){
		restaurants = toChange;
	}
	
	public void setPrice(double toChange){
		price = toChange;
	}
	
	public void setLocation(Location toChange){
		location = toChange;
	}
	
	public void setName(String str){
		name = str;
	}
	
	public void setAddress(String str){
		address = str;
	}
	
	@Override
	public boolean equals(Object other){
		if(!this.getClass().equals(other.getClass())){
			return false;
		}
		return restaurants.equals(((Station) other).getRestaurants()) && name.equals(((Station) other).getName()) && location.equals(((Station) other).getLocation())
				&& price == ((Station) other).getPrice();
	}
}
