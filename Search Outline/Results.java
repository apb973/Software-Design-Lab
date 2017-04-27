import java.util.ArrayList;

public class Results {
	private ArrayList<Station> results;
	
	public Results(){
		results = new ArrayList<Station>();
	}
	
	public Station getStation(int index){
		return results.get(index);
	}
	
	public void addStation(Station station){
		results.add(station);
	}
	
	public void removeStation(int index){
		results.remove(index);
	}
	
	public void removeStation(Station toRemove){
		results.remove(toRemove);
	}
	
	public int size(){
		return results.size();
	}	
}
