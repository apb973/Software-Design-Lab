//will be stores in a database with station, if we implement


public class BathroomRating{
	double rating;
	int rates;
	
	public BathroomRating(){
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
