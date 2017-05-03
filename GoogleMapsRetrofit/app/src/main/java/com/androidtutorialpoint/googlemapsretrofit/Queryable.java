package com.androidtutorialpoint.googlemapsretrofit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class Queryable {

	private Results googleResults, priceResults;
	private Location location;
	private GasQueryInput input;
	private Restaurants names;
	
    public class nearbyGoogleStations extends Thread{
    	public void run(){
    		Document doc;
    		googleResults = new Results();
            try {
                doc = Jsoup.connect("https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location=" + location.getLattitude() + ",+" + location.getLongitude() + "&rankby=distance&type=gas_station&key=AIzaSyDvb8h33wTteNR1NbHN9f0m1Y2HfgdwkwU").get();
                Elements nameElements = doc.select("name");
                Elements locElements = doc.select("location");
                Elements latElements = locElements.select("lat");
                Elements longElements = locElements.select("lng");
                Elements addrElements = doc.select("vicinity");
                int size = nameElements.size();
                
                for (int i = 0; i < size; i++) {
                	Station toAdd = new Station();
                	toAdd.setLocation(new Location(Double.parseDouble(latElements.get(i).text()), Double.parseDouble(longElements.get(i).text())));
                	toAdd.setAddress(fixAbbreviations(addrElements.get(i).text()));
                	toAdd.setName(nameElements.get(i).text());
                	googleResults.addStation(toAdd);
                }
            } catch (Exception e) {
            }
    	}
        
    }
    
    private String fixAbbreviations(String abbreviated){
    	abbreviated = abbreviated.replaceAll(",.*$" , "");
    	abbreviated = abbreviated.replaceAll(" Blvd$", " Boulevard");
    	abbreviated = abbreviated.replaceAll(" Rd$", " Road");
    	abbreviated = abbreviated.replaceAll(" St$", " Street");
    	abbreviated = abbreviated.replaceAll(" Pkwy$", " Parkway");
		
    	abbreviated = abbreviated.replaceAll(" N " , " North ");
    	abbreviated = abbreviated.replaceAll(" W " , " West ");
    	abbreviated = abbreviated.replaceAll(" S " , " South ");
    	abbreviated = abbreviated.replaceAll(" E " , " East ");
    	return abbreviated;
    }
    
    public Station specificGoogleStation(String address, Location location){
    	Document doc;
        try {
            doc = Jsoup.connect("https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location=" + location.getLattitude() + ",+" + location.getLongitude() + "&rankby=distance&type=gas_station&key=AIzaSyDvb8h33wTteNR1NbHN9f0m1Y2HfgdwkwU&keyword" + address).get();
            Elements nameElements = doc.select("name");
            Elements locElements = doc.select("location");
            Elements latElements = locElements.select("lat");
            Elements longElements = locElements.select("lng");
            Elements addrElements = doc.select("vicinity");
            

            int size = nameElements.size();
            
            for (int i = 0; i < size; i++) {
            	if(address.equalsIgnoreCase(addrElements.get(i).text())){
	            	Station toAdd = new Station();
	            	toAdd.setLocation(new Location(Double.parseDouble(latElements.get(i).text()), Double.parseDouble(longElements.get(i).text())));
	            	toAdd.setAddress(addrElements.get(i).text());
	            	toAdd.setName(nameElements.get(i).text());
	            	return toAdd;
            	}
            }
        } catch (Exception e) {
        	return new Station();
        }
        return new Station();
    }
    
    public class gasPriceQueryInfo extends Thread{
    	public void run(){
        	Document doc;
        	input = new GasQueryInput();
            try {
                doc = Jsoup.connect("https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location=" + location.getLattitude() + ",+" + location.getLongitude() + "&radius=500&type=restaurant&key=AIzaSyDvb8h33wTteNR1NbHN9f0m1Y2HfgdwkwU").get();
                Elements nameElements = doc.select("reference");
                String id = nameElements.get(0).text();
                doc = Jsoup.connect("https://maps.googleapis.com/maps/api/place/details/xml?reference=" + id + "&key=AIzaSyDvb8h33wTteNR1NbHN9f0m1Y2HfgdwkwU").get();
                Elements zipCodeElements = doc.select("adr_address");
                String[] zipCodePassOne = zipCodeElements.get(0).text().split("e\">");
                String[] zipCodePassTwo = zipCodePassOne[1].split("[^0-9]");
                input.setZipCode(Integer.parseInt(zipCodePassTwo[0]));
                String[] cityPassOne = zipCodeElements.get(0).text().split("y\">");
                String[] cityPassTwo = cityPassOne[1].split("<");
                input.setCity(cityPassTwo[0]);
            } catch (Exception e) {
            }
    	}
    }
    
    public class googleRestaurants extends Thread{
    	public void run(){
       	 Document doc;
       	 names = new Restaurants();
   	        try {
   	            doc = Jsoup.connect("https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location=" + location.getLattitude() + ",+" + location.getLongitude() + "&radius=400&type=restaurant&key=AIzaSyDvb8h33wTteNR1NbHN9f0m1Y2HfgdwkwU").get();
   	            Elements nameElements = doc.select("name");
   	            names = new Restaurants();
   	            int size = nameElements.size();
   	            
   	            for (int i = 0; (i < size); i++) {
   	            	names.addRestaurant(nameElements.get(i).text());
   	            }
   	        } catch (Exception e) {
   	        }
    	}
    }
    
    public Restaurants getRestaurants(Location loc){
    	location = loc;
    	Thread t1 = new googleRestaurants();
    	t1.start();
    	try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return names;
    }
    
	public class FindGasPrice extends Thread{
		public void run(){
			priceResults = new Results();
			int zipcode = input.getZipCode();
			String city = input.getCity();
		    Document doc;
		    try {
		        doc = Jsoup.connect("http://www.autoblog.com/" + zipcode + "-gas-prices/").get();
		        Elements addlist = doc.select("div");
		        int counter2 = 0;
		        for (Element listObject : addlist) {
		        	if (!(listObject.text().isEmpty())){
		        		String[] printThis = listObject.text().split("123456798");
		        		printThis = printThis[0].split("Name PPG Address Location DistanceDist.");
		        		if(counter2==62){
		        			String[] parsingArray = new String[100];
		            		for (String something : printThis){
		            			something = something.trim();
		            			if (!something.isEmpty()){
		                    			parsingArray = something.split("\\$");
		                    			for (int k = 1; k<11; k++){
		                    				parsingArray[k] = parsingArray[k].replaceAll(" " + city, "");
		                    				parsingArray[k] = fixAbbreviations(parsingArray[k]);
		                    				}                        			
		                    			for (int k = 1; k<11; k++) {
		                    				String[] newStation = new String[2];
		                    				newStation = parsingArray[k].split(" ", 2);
		                    				
		                    				Station placeholder = new Station();
		                    				newStation[0] = newStation[0].trim();
		                    				newStation[1] = newStation[1].trim();
		                    				double price = Double.parseDouble(newStation[0]);
		                    				placeholder.setPrice(price);
		                    				placeholder.setAddress(newStation[1]);
		                    				priceResults.addStation(placeholder);
		                    			}                
		            			} 
		            		}
		        		}
		        		counter2++;
		        	}
		        }
		    } catch (Exception e) {
		    }
		}

	}
	
	public Results initialQuery(Location loc){
		if(loc == null){
			throw(new NullPointerException());
		}
		location = loc;
		Thread t1 = new nearbyGoogleStations();
		t1.start();
		Thread t2 = new gasPriceQueryInfo();
		t2.start();
		Thread t3 = new FindGasPrice();
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t3.start();
		
		try {
			t1.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Results finalResults = new Results();
		Station googleResult;
		Station priceResult;
		int googleSize = googleResults.size();
		int priceSize;
		
		for(int i = 0; i < googleSize; i++){
			googleResult = googleResults.getStation(i);
			priceSize = priceResults.size();
			for(int j = 0; j < priceSize; j++){
				priceResult = priceResults.getStation(j);
				if(googleResult.getAddress().equalsIgnoreCase(priceResult.getAddress())){
					googleResults.getStation(i).setPrice(priceResults.getStation(j).getPrice());
					finalResults.addStation(googleResult);
					priceResults.removeStation(priceResult);
					break;
				}
			}
		}
		
		return finalResults;
	}
}
