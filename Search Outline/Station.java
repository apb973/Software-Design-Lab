
public class Station {
	private Bathroom bathroom;
	private Restaurants restaurants;
	private double price;
	private Location location;
	private String name;
	private String address;
	
	public Station(){
		bathroom = null;
		restaurants = null;
		price = 0;
		location = null;
	}
	
	public Bathroom getBathroom(){
		return bathroom;
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
	
	public void setBathroom(Bathroom restroom){
		bathroom = restroom;
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
		name = str;
	}
}
