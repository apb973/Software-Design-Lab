
public class Station {
	private BathroomRating bathroom;
	private Restaurants restaurants;
	private Price price;
	private Location location;
	
	public Station(){
		bathroom = null;
		restaurants = null;
		price = null;
		location = null;
	}
	
	public BathroomRating getBathroomRating(){
		return bathroom;
	}
	
	public Restaurants getRestaurants(){
		return restaurants;
	}
	
	public Price getPrice(){
		return price;
	}
	
	public Location getLocation(){
		return location;
	}
	
	public void setBathroomRating(BathroomRating rating){
		bathroom = rating;
	}
	
	public void getRestaurants(Restaurants toChange){
		restaurants = toChange;
	}
	
	public void getPrice(Price toChange){
		price = toChange;
	}
	
	public void getLocation(Location toChange){
		location = toChange;
	}
}
