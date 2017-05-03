package com.androidtutorialpoint.googlemapsretrofit;

public class GasQueryInput{
	    	private int zipCode;
	    	private String city;
	    	
	    	public void setZipCode(int postalCode){
	    		zipCode = postalCode;
	    	}
	    	
	    	public void setCity(String town){
	    		city = town;
	    	}
	    	
	    	public int getZipCode(){
	    		return zipCode;
	    	}
	    	
	    	public String getCity(){
	    		return city;
	    	}
	    }
