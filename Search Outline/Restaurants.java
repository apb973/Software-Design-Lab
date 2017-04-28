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
	
	@Override
	public boolean equals(Object other){
		if(!this.getClass().equals(other.getClass())){
			return false;
		}
		int size = restaurantNames.size();
		if(size != ((Restaurants) other).size()){
			return false;
		}
		for(int i = 0; i < size; size++){
			if(!restaurantNames.get(i).equals(((Restaurants) other).getRestaurant(i))){
				return false;
			}
		}
		return true;
	}
}
