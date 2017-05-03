import static org.junit.Assert.*;

import org.junit.Test;

public class EqualsTest {

	@Test
	public void RestaurantsEqualsTest() {
		Restaurants one = new Restaurants();
		Restaurants two = new Restaurants();
		assertEquals(one,two);
		
		one.addRestaurant("Hello");
		assertNotEquals(one,two);
		two.addRestaurant("Hello");;
		assertEquals(one,two);
		two.addRestaurant("World");
		assertNotEquals(one,two);
		two.removeRestaurant("world");
		assertNotEquals(one,two);
		two.removeRestaurant("World");
		assertEquals(one,two);
		
		one = null;
		Throwable e = null;
		try{
			one.equals(two);
		}catch(NullPointerException ex){
			e = ex;
		}
		assertNotNull(e);
		e = null;
		try{
			two.equals(one);
		}catch(NullPointerException ex){
			e = ex;
		}
		assertNotNull(e);
		e = null;
		try{
			one.equals(null);
		}catch(NullPointerException ex){
			e = ex;
		}
		assertNotNull(e);	
		
		assertNotEquals(two,new Object());
	}
	
	@Test
	public void ResultsEqualsTest() {
		Station stationOne = new Station();
		stationOne.setAddress("Hello");
		stationOne.setLocation(new Location(10,10));
		stationOne.setName("World");
	    stationOne.setPrice(10);
		Restaurants oneRest = new Restaurants();
		stationOne.setRestaurants(oneRest);
		oneRest.addRestaurant("!");
		Station stationTwo = new Station();
		stationTwo.setAddress("Hell");
		stationTwo.setLocation(new Location(-10,-10));
		stationTwo.setName("Worl");
	    stationTwo.setPrice(-10);
		Restaurants twoRest = new Restaurants();
		stationTwo.setRestaurants(oneRest);
		twoRest.addRestaurant("?");
		Results one = new Results();
		Results two = new Results();
		assertEquals(one,two);
		
		one.addStation(stationOne);
		assertNotEquals(one,two);
		two.addStation(stationOne);
		assertEquals(one,two);
		two.addStation(stationTwo);
		assertNotEquals(one,two);
		two.removeStation(stationTwo);
		assertEquals(one,two);
		
		one = null;
		Throwable e = null;
		try{
			one.equals(two);
		}catch(NullPointerException ex){
			e = ex;
		}
		assertNotNull(e);
		e = null;
		try{
			two.equals(one);
		}catch(NullPointerException ex){
			e = ex;
		}
		assertNotNull(e);
		e = null;
		try{
			one.equals(null);
		}catch(NullPointerException ex){
			e = ex;
		}
		assertNotNull(e);	
		
		assertNotEquals(two,new Object());
	}
	
	@Test
	public void LocationEqualsTest() {
		Location one = new Location();
		Location two = new Location();
		assertEquals(one,two);
		
		one.setLattitude(10);
		assertNotEquals(one,two);
		two.setLattitude(10);
		assertEquals(one,two);
		one.setLongitude(10);
		assertNotEquals(one,two);
		two.setLongitude(10);
		assertEquals(one,two);
		
		one = null;
		Throwable e = null;
		try{
			one.equals(two);
		}catch(NullPointerException ex){
			e = ex;
		}
		assertNotNull(e);
		e = null;
		try{
			two.equals(one);
		}catch(NullPointerException ex){
			e = ex;
		}
		assertNotNull(e);
		e = null;
		try{
			one.equals(null);
		}catch(NullPointerException ex){
			e = ex;
		}
		assertNotNull(e);	
		
		assertNotEquals(two,new Object());
	}
	
	@Test
	public void StationEqualsTest() {
		Station one = new Station();
		Station two = new Station();
		assertEquals(one,two);
		
		one.setAddress("Hello");
		assertEquals(one,two);
		assertNotEquals(one,two);
		assertEquals(one,two);
		one.setLocation(new Location(10,10));
		assertNotEquals(one,two);
		two.setLocation(new Location(10,10));
		assertEquals(one,two);
		one.setName("World");
		assertNotEquals(one,two);
		two.setName("World");
		assertEquals(one,two);
		one.setPrice(10);
		assertNotEquals(one,two);
		two.setPrice(10);
		assertEquals(one,two);
		Restaurants oneRest = new Restaurants();
		Restaurants twoRest = new Restaurants();
		one.setRestaurants(oneRest);
		two.setRestaurants(twoRest);
		oneRest.addRestaurant("!");
		assertNotEquals(one,two);
		twoRest.addRestaurant("!");
		assertEquals(one,two);
		
		one = null;
		Throwable e = null;
		try{
			one.equals(two);
		}catch(NullPointerException ex){
			e = ex;
		}
		assertNotNull(e);
		e = null;
		try{
			two.equals(one);
		}catch(NullPointerException ex){
			e = ex;
		}
		assertNotNull(e);
		e = null;
		try{
			one.equals(null);
		}catch(NullPointerException ex){
			e = ex;
		}
		assertNotNull(e);	
		
		assertNotEquals(two,new Object());
	}
}
