
public class Station {
	private Bathroom bathroom;
	private Restaurants restaurants;
	private double price;
	private Location location;
	
	public Station(){
		bathroom = null;
		restaurants = null;
		price = 0;
		location = null;
	}
	
	public Bathroom getBathroom(){
		return bathroom;
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
	
	public void setBathroom(Bathroom restroom){
		bathroom = restroom;
	}
	
	public void getRestaurants(Restaurants toChange){
		restaurants = toChange;
	}
	
	public void getPrice(double toChange){
		price = toChange;
	}
	
	public void getLocation(Location toChange){
		location = toChange;
	}
}
