import static org.junit.Assert.*;

import org.junit.Test;

public class SearchableSearchTest {
	
	@Test
	public void testNull() {
		Throwable e = null;
		Searchable tester = new NoFilterSearch();
		try{
			tester.search(null);
		}catch(NullPointerException ex){
			e = ex;
		}
		assertNotNull(e);
		
		e = null;
		tester = new RestaurantSearch();
		try{
			tester.search(null);
		}catch(NullPointerException ex){
			e = ex;
		}
		assertNotNull(e);
		
		e = null;
		tester = new PriceSearch();
		try{
			tester.search(null);
		}catch(NullPointerException ex){
			e = ex;
		}
		assertNotNull(e);
	}
	
	@Test
	public void testNoFilter(){
		NoFilterSearch tester = new NoFilterSearch();
		Results benchmark = new Results();
		Location loc = new Location();	
		Station one = new Station();
		Station two = new Station();
		Station three = new Station();
		Station four = new Station();
		Station five = new Station();
		
		loc.setLattitude(30.295379);	//apartment
		loc.setLongitude(-97.743541);
		one.setName("Chevron");
		one.setPrice(2.499);
		one.setLocation(new Location(30.2948248, -97.7422602));
		one.setRestaurants(tester.googleRestaurants(one.getLocation()));
		two.setName("Wag-A-Bag #24");
		two.setPrice(2.259);
		two.setLocation(new Location(30.2882178, -97.7450099));
		two.setRestaurants(tester.googleRestaurants(two.getLocation()));
		three.setName("Valero Corner Store");
		three.setPrice(2.559);
		three.setLocation(new Location(30.3040992, -97.7434783));
		three.setRestaurants(tester.googleRestaurants(three.getLocation()));
		four.setName("Valero Corner Store");
		four.setPrice(2.179);
		four.setLocation(new Location(30.3047529, -97.7365121));
		four.setRestaurants(tester.googleRestaurants(four.getLocation()));
		five.setName("Dev's Shell");
		five.setPrice(2.499);
		five.setLocation(new Location(30.3087183, -97.7424385));
		five.setRestaurants(tester.googleRestaurants(five.getLocation()));
		benchmark.addStation(one);
		benchmark.addStation(two);
		benchmark.addStation(three);
		benchmark.addStation(four);
		benchmark.addStation(five);
		assertEquals(benchmark, tester.search(loc));
	}
	
	@Test
	public void testPrice(){
		PriceSearch tester = new PriceSearch();
		Results benchmark = new Results();
		Location loc = new Location();
		Station one = new Station();
		Station two = new Station();
		Station three = new Station();
		Station four = new Station();
		Station five = new Station();
		
		loc.setLattitude(30.295379);	//apartment
		loc.setLongitude(-97.743541);
		one.setName("Chevron");
		one.setPrice(2.499);
		one.setLocation(new Location(30.2948248, -97.7422602));
		one.setRestaurants(tester.googleRestaurants(one.getLocation()));
		two.setName("Wag-A-Bag #24");
		two.setPrice(2.259);
		two.setLocation(new Location(30.2882178, -97.7450099));
		two.setRestaurants(tester.googleRestaurants(two.getLocation()));
		three.setName("STAR STOP 35");
		three.setPrice(2.199);
		three.setLocation(new Location(30.2954581, -97.7198901));
		three.setRestaurants(tester.googleRestaurants(three.getLocation()));
		four.setName("Valero Corner Store");
		four.setPrice(2.179);
		four.setLocation(new Location(30.3047529, -97.7365121));
		four.setRestaurants(tester.googleRestaurants(four.getLocation()));
		five.setName("Dev's Shell");
		five.setPrice(2.499);
		five.setLocation(new Location(30.3087183, -97.7424385));
		five.setRestaurants(tester.googleRestaurants(five.getLocation()));
		benchmark.addStation(four);
		benchmark.addStation(three);
		benchmark.addStation(two);
		benchmark.addStation(one);
		benchmark.addStation(five);
		assertEquals(benchmark, tester.search(loc));
	}
	
	@Test
	public void testRestaurants(){
		RestaurantSearch tester = new RestaurantSearch();
		Results benchmark = new Results();
		Location loc = new Location();
		Station one = new Station();
		Station two = new Station();
		Station three = new Station();
		Station four = new Station();
		Station five = new Station();
		
		loc.setLattitude(30.295379);	//apartment
		loc.setLongitude(-97.743541);
		one.setName("Chevron");
		one.setPrice(2.499);
		one.setLocation(new Location(30.2948248, -97.7422602));
		one.setRestaurants(tester.googleRestaurants(one.getLocation()));
		two.setName("Wag-A-Bag #24");
		two.setPrice(2.259);
		two.setLocation(new Location(30.2882178, -97.7450099));
		two.setRestaurants(tester.googleRestaurants(two.getLocation()));
		three.setName("Valero Corner Store");
		three.setPrice(2.559);
		three.setLocation(new Location(30.3040992, -97.7434783));
		three.setRestaurants(tester.googleRestaurants(three.getLocation()));
		four.setName("Valero Corner Store");
		four.setPrice(2.179);
		four.setLocation(new Location(30.3047529, -97.7365121));
		four.setRestaurants(tester.googleRestaurants(four.getLocation()));
		five.setName("Dev's Shell");
		five.setPrice(2.499);
		five.setLocation(new Location(30.3087183, -97.7424385));
		five.setRestaurants(tester.googleRestaurants(five.getLocation()));
		benchmark.addStation(one);
		benchmark.addStation(two);
		benchmark.addStation(three);
		benchmark.addStation(four);
		benchmark.addStation(five);
		assertEquals(benchmark, tester.search(loc));
	}
	
	@Test
	public void testOutsideUS(){
		Results benchmark = new Results();
		Location loc = new Location(32.645537,-169.348459);
		
		Searchable tester = new NoFilterSearch();
		assertEquals(benchmark, tester.search(loc));
		tester = new PriceSearch();
		assertEquals(benchmark, tester.search(loc));
		tester = new RestaurantSearch();
		assertEquals(benchmark, tester.search(loc));
	}
	
	@Test
	public void noStationsTest(){
		Results benchmark = new Results();
		Location loc = new Location(36.252152,-112.418995);			
		
		Searchable tester = new NoFilterSearch();
		assertEquals(benchmark, tester.search(loc));
		tester = new PriceSearch();
		assertEquals(benchmark, tester.search(loc));
		tester = new RestaurantSearch();
		assertEquals(benchmark, tester.search(loc));
	}
}
