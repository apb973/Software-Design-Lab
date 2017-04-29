
public class Location {
	private double lattitude;
	private double longitude;
	
	//TODO: add constructor and setter using google places
	
	public Location(){
		lattitude = 0;
		longitude = 0;
	}
	
	public Location(double lat, double lon){
		lattitude = lat;
		longitude = lon;
	}
	
	public double getLattitude(){
		return lattitude;
	}
	
	public double getLongitude(){
		return longitude;
	}
	
	public void setLattitude(double lat){
		lattitude = lat;
	}
	
	public void setLongitude(double lon){
		longitude = lon;
	}
}
