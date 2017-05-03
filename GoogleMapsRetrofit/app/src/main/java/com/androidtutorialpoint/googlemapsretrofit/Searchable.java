package com.androidtutorialpoint.googlemapsretrofit;

public interface Searchable {
	public Results search(Location location);
	
	public Results sort(Results results);
}
