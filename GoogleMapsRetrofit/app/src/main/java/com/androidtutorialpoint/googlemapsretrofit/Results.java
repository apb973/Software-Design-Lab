package com.androidtutorialpoint.googlemapsretrofit;

import java.util.ArrayList;

public class Results {
	private ArrayList<Station> results;

	public Results(){
		results = new ArrayList<Station>();
	}
	
	public Station getStation(int index){
		return results.get(index);
	}
	
	public void addStation(Station station){
		results.add(station);
	}
	
	public void removeStation(int index){
		results.remove(index);
	}
	
	public void removeStation(Station toRemove){
		results.remove(toRemove);
	}
	
	public int size(){
		return results.size();
	}
	
	@Override
	public boolean equals(Object other){
		if(!this.getClass().equals(other.getClass())){
			return false;
		}
		int size = results.size();
		if(size != ((Results) other).size()){
			return false;
		}
		for(int i = 0; i < size; i++){
			if(!results.get(i).equals(((Results) other).getStation(i))){
				return false;
			}
		}
		return true;
	}
}
