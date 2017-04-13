//will be stored in a database with station, if we implement


public class Bathroom{
	double rating;
	int rates;
	
	public Bathroom(){
		rating = 0;
		rates = 0;
	}
	
	public double getRating(){
		return rating;
	}
	
	public void rate(int rate){
		rating = (rating * rates + rate) / (rates + 1);
		rates++;
	}

}
