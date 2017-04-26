import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class GoogleJSoup {
	    public static Results nearbyGoogleStations(Location location) {
	        Document doc;
	        Results names = new Results();
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
	            	toAdd.setAddress(addrElements.get(i).text());
	            	toAdd.setName(nameElements.get(i).text());
	            	names.addStation(toAdd);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return names;

	    }
	    
	    public static Station specificGoogleStation(String address, Location location){
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
	            	if(address.equals(addrElements.get(i).text())){
		            	Station toAdd = new Station();
		            	toAdd.setLocation(new Location(Double.parseDouble(latElements.get(i).text()), Double.parseDouble(longElements.get(i).text())));
		            	toAdd.setAddress(addrElements.get(i).text());
		            	toAdd.setName(nameElements.get(i).text());
		            	return toAdd;
	            	}
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return new Station();
	    }
	    
	    public static GasQueryInput gasPriceQueryInfo(Location location){
	    	Document doc;
	    	GasQueryInput toReturn = new GasQueryInput();
	        try {
	            //need http protocol
	            doc = Jsoup.connect("https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location=" + location.getLattitude() + ",+" + location.getLongitude() + "&radius=500&type=restaurant&key=AIzaSyDvb8h33wTteNR1NbHN9f0m1Y2HfgdwkwU").get();
	            Elements nameElements = doc.select("reference");
	            String id = nameElements.get(0).text();
	            doc = Jsoup.connect("https://maps.googleapis.com/maps/api/place/details/xml?reference=" + id + "&key=AIzaSyDvb8h33wTteNR1NbHN9f0m1Y2HfgdwkwU").get();
	            Elements zipCodeElements = doc.select("adr_address");
	            String[] zipCodePassOne = zipCodeElements.get(0).text().split("e\">");
	            String[] zipCodePassTwo = zipCodePassOne[1].split("[^0-9]");
	            toReturn.setZipCode(zipCodePassTwo[0]);
	            String[] cityPassOne = zipCodeElements.get(0).text().split("y\">");
	            String[] cityPassTwo = cityPassOne[1].split("<");
	            toReturn.setCity(cityPassTwo[0]);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return toReturn;
	    }
	    
	    public static Restaurants googleRestaurants(Location location){
	    	 Document doc;
	    	 Restaurants names = new Restaurants();
		        try {
		            // need http protocol
		            doc = Jsoup.connect("https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location=" + location.getLattitude() + ",+" + location.getLongitude() + "&radius=440&type=restaurant&key=AIzaSyDvb8h33wTteNR1NbHN9f0m1Y2HfgdwkwU").get();
		            Elements nameElements = doc.select("name");
		            names = new Restaurants();
		            int size = nameElements.size();
		            
		            for (int i = 0; ((i < size) || (i < 5)); i++) {
		            	names.addRestaurant(nameElements.get(i).text());
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        return names;
	    }
	    
	    public static void main(String[] args){
	    	System.out.println(gasPriceQueryInfo(new Location(30.295379,-97.743541)).getZipCode());
	    }

	}
