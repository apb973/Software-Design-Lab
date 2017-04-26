import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class Queryable {

    public Results nearbyGoogleStations(Location location) {
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

            	String placeholder = addrElements.get(i).text();
            	placeholder = placeholder.replaceAll(",.*$" , "");
            	placeholder = placeholder.replaceAll(" Blvd$", " Boulevard");
            	placeholder = placeholder.replaceAll(" Rd$", " Road");
            	placeholder = placeholder.replaceAll(" St$", " Street");
            	placeholder = placeholder.replaceAll(" Pkwy$", " Parkway");
				
            	placeholder = placeholder.replaceAll(" N " , " North ");
            	placeholder = placeholder.replaceAll(" W " , " West ");
            	placeholder = placeholder.replaceAll(" S " , " South ");
            	placeholder = placeholder.replaceAll(" E " , " East ");
            	toAdd.setAddress(placeholder);
            	toAdd.setName(nameElements.get(i).text());
            	names.addStation(toAdd);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;

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
    
    public GasQueryInput gasPriceQueryInfo(Location location){
    	Document doc;
    	GasQueryInput toReturn = new GasQueryInput();
        try {
            doc = Jsoup.connect("https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location=" + location.getLattitude() + ",+" + location.getLongitude() + "&radius=500&type=restaurant&key=AIzaSyDvb8h33wTteNR1NbHN9f0m1Y2HfgdwkwU").get();
            Elements nameElements = doc.select("reference");
            String id = nameElements.get(0).text();
            doc = Jsoup.connect("https://maps.googleapis.com/maps/api/place/details/xml?reference=" + id + "&key=AIzaSyDvb8h33wTteNR1NbHN9f0m1Y2HfgdwkwU").get();
            Elements zipCodeElements = doc.select("adr_address");
            String[] zipCodePassOne = zipCodeElements.get(0).text().split("e\">");
            String[] zipCodePassTwo = zipCodePassOne[1].split("[^0-9]");
            toReturn.setZipCode(Integer.parseInt(zipCodePassTwo[0]));
            String[] cityPassOne = zipCodeElements.get(0).text().split("y\">");
            String[] cityPassTwo = cityPassOne[1].split("<");
            toReturn.setCity(cityPassTwo[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }
    
    public Restaurants googleRestaurants(Location location){
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
    
	public Results FindGasPrice(int zipcode, String city){
		Results results = new Results();
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
	                    				parsingArray[k] = parsingArray[k].replaceAll(",.*$" , "");
	                    				parsingArray[k] = parsingArray[k].replaceAll(" " + city, "");
	                    				parsingArray[k] = parsingArray[k].replaceAll(" Blvd", " Boulevard");
	                    				parsingArray[k] = parsingArray[k].replaceAll(" Rd", " Road");
	                    				parsingArray[k] = parsingArray[k].replaceAll(" St", " Street");
	                    				parsingArray[k] = parsingArray[k].replaceAll(" Fwy", " Freeway");
	                    				parsingArray[k] = parsingArray[k].replaceAll(" Ave", " Avenue");
	                    				parsingArray[k] = parsingArray[k].replaceAll(" Cir", " Circle");
	                    				parsingArray[k] = parsingArray[k].replaceAll(" Expy", " Expressway");
	                    				parsingArray[k] = parsingArray[k].replaceAll(" Pkwy", " Parkway");
	                    				parsingArray[k] = parsingArray[k].replaceAll(" N " , " North ");
	                    				parsingArray[k] = parsingArray[k].replaceAll(" W " , " West ");
	                    				parsingArray[k] = parsingArray[k].replaceAll(" S " , " South ");
	                    				parsingArray[k] = parsingArray[k].replaceAll(" E " , " East ");
	                    				}                        			
	                    			for (int k = 1; k<11; k++) {
	                    				//System.out.println(parsingArray[k]);
	                    				String[] newStation = new String[2];
	                    				newStation = parsingArray[k].split(" ", 2);
	                    				
	                    				Station placeholder = new Station();
	                    				newStation[0] = newStation[0].trim();
	                    				newStation[1] = newStation[1].trim();
	                    				double price = Double.parseDouble(newStation[0]);
	                    				placeholder.setPrice(price);
	                    				placeholder.setAddress(newStation[1]);
	                    				results.addStation(placeholder);
	                    			}                
	            			} 
	            		}
	        		}
	        		counter2++;
	        	}
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return results;
	}
	
	public Results initialQuery(Location location){
		Results googleResults = nearbyGoogleStations(location);
		GasQueryInput input = gasPriceQueryInfo(location);
		
		
		Results priceResults = FindGasPrice(input.getZipCode(), input.getCity());
/*	
		System.out.println(input.getZipCode() + " " + input.getCity());
		for(int w = 0; w < priceResults.size(); w++){
			System.out.println(priceResults.getStation(w).getAddress());
		}
		for(int w = 0; w < googleResults.size(); w++){
			System.out.println(googleResults.getStation(w).getAddress());
		}
*/		
		Results finalResults = new Results();
		Station googleResult;
		Station priceResult;
		int googleSize = googleResults.size();
		int priceSize = priceResults.size();
		
		for(int i = 0; i < googleSize; i++){
			googleResult = googleResults.getStation(i);
			for(int j = 0; j < priceSize; j++){
				priceResult = priceResults.getStation(j);
				if(googleResult.getAddress().equals(priceResult.getAddress())){
					googleResults.getStation(i).setPrice(priceResults.getStation(j).getPrice());
					finalResults.addStation(googleResult);
					break;
				}
			}
		}
/*		
		System.out.println(finalResults.size());
		for(int w = 0; w < finalResults.size(); w++){
			System.out.println(finalResults.getStation(w).getAddress());
			System.out.println(finalResults.getStation(w).getPrice());
		}*/
		return finalResults;
	}
}