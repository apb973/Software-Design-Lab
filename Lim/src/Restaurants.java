import java.util.ArrayList;

public class Restaurants {
	private ArrayList<String> restaurantNames;
	
	public Restaurants(){
		restaurantNames = new ArrayList<String>();
	}
	
	public String getRestaurant(int index){
		return restaurantNames.get(index);
	}
	
	public void addRestaurant(String name){
		restaurantNames.add(name);
	}
	
	public void removeRestaurant(String name){
		restaurantNames.remove(name);
	}
	
	public int size(){
		return restaurantNames.size();
	}	
}
