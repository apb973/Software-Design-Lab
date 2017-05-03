import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class QueryableTest {

	@Test
	public void testNearbyGoogleStationssize() {
		Location holder = new Location(30.307182,-97.755996);
		Queryable search = new Queryable();
		Results stationList = new Results();
		stationList = search.nearbyGoogleStations(holder);
		assertTrue(stationList.size() == 20);
	}
	
	@Test
	public void testNearbyGoogleStationsCorrectness() {
		Location holder = new Location(30.307182,-97.755996);
		Queryable search = new Queryable();
		Results stationList = new Results();
		stationList = search.nearbyGoogleStations(holder);
		ArrayList<String> expected = new ArrayList<String> ();
		ArrayList<String> actual = new ArrayList<String> ();
		
		expected.add("2701 Exposition Boulevard");				
		expected.add("3201 North Lamar Boulevard");
		expected.add("3201 North Lamar Boulevard");
		expected.add("3515 North Lamar Boulevard");
		expected.add("3515 North Lamar Boulevard");
		expected.add("4001 Medical Parkway");
		expected.add("2400 Exposition Boulevard");
		expected.add("2400 Exposition Boulevard");
		expected.add("1309 West 45th Street");
		expected.add("1309 West 45th Street");
		expected.add("3909 Guadalupe Street");
		expected.add("2819 Guadalupe Street");
		expected.add("3915 Guadalupe Street");
		expected.add("4413 Guadalupe Street");
		expected.add("2321 West North Loop Boulevard");
		expected.add("2400 Rio Grande Street");
		expected.add("2400 Rio Grande Street");
		expected.add("5721 Burnet Road");
		expected.add("5300 Burnet Road");
		expected.add("5101 North Lamar Boulevard");
		for (int i = 0; i < stationList.size(); i++){
			actual.add(stationList.getStation(i).getAddress());
		}
		for (int i = 0; i < expected.size(); i++){
		}
		assertTrue(expected.equals(actual));
	}


	@Test
	public void testGasPriceQueryInfoCorrectness1() {
		GasQueryInput expected = new GasQueryInput();
		expected.setCity("Austin");
		expected.setZipCode(78703);
		GasQueryInput actual = new GasQueryInput();
		Queryable tester = new Queryable();
		Location holder = new Location(30.307182,-97.755996);
		actual = tester.gasPriceQueryInfo(holder);

		assertTrue(expected.getCity().equals(actual.getCity()));
		assertTrue(expected.getZipCode() == (actual.getZipCode()));
	}
	
	@Test
	public void testGasPriceQueryInfoCorrectness2() {
		GasQueryInput expected = new GasQueryInput();
		expected.setCity("Richardson");
		expected.setZipCode(75081);
		GasQueryInput actual = new GasQueryInput();
		Queryable tester = new Queryable();
		Location holder = new Location(32.9483,-96.7299);
		actual = tester.gasPriceQueryInfo(holder);

		assertTrue(expected.getCity().equals(actual.getCity()));
		assertTrue(expected.getZipCode() == (actual.getZipCode()));
	}


	@Test
	public void testFindGasPrice10() {
		Queryable tester = new Queryable();
		Results actual = new Results();
		Results expected = new Results();
		actual = tester.FindGasPrice(75081, "Richardson");

		assertTrue(actual.size() == 10);
		
	}
	
	@Test
	public void testFindGasPriceRichardson() {
		Queryable tester = new Queryable();
		Results actual = new Results();
		Results expected = new Results();
		
		actual = tester.FindGasPrice(75081, "Richardson");
		
		Station gasStation = new Station();
		gasStation.setAddress("536 Centennial Blvd");
		gasStation.setPrice(2.209);
		expected.addStation(gasStation);
		gasStation.setAddress("540 Centennial Blvd");
		gasStation.setPrice(2.579);
		expected.addStation(gasStation);
		gasStation.setAddress("100 S Greenville Ave");
		gasStation.setPrice(2.289);
		expected.addStation(gasStation);
		gasStation.setAddress("102 E Arapaho Rd");
		gasStation.setPrice(2.239);
		expected.addStation(gasStation);
		gasStation.setAddress("1360 S Plano Rd");
		gasStation.setPrice(2.519);
		expected.addStation(gasStation);
		gasStation.setAddress("1331 S Plano Rd");
		gasStation.setPrice(2.239);
		expected.addStation(gasStation);
		gasStation.setAddress("1921 N Plano Rd");
		gasStation.setPrice(2.799);
		expected.addStation(gasStation);
		gasStation.setAddress("1602 N Plano Rd");
		gasStation.setPrice(2.539);
		expected.addStation(gasStation);
		gasStation.setAddress("236 E Spring Valley Rd");
		gasStation.setPrice(2.399);
		expected.addStation(gasStation);
		gasStation.setAddress("2150 E Belt Line Rd");
		gasStation.setPrice(2.399);
		expected.addStation(gasStation);
		
		expected.equals(actual);
	}
	
	@Test
	public void testFindGasPriceAustin() {
		Queryable tester = new Queryable();
		Results actual = new Results();
		Results expected = new Results();
		
		actual = tester.FindGasPrice(78703, "Austin");
		
		Station gasStation = new Station();
		expected.addStation(new Station(2.729, "2701 Exposition Boulevard"));
		expected.addStation(new Station(2.539, "2620 Lake Boulevard"));
		expected.addStation(new Station(2.469, "2407 Lake Boulevard"));
		expected.addStation(new Station(2.499, "2402 Lake Boulevard"));
		expected.addStation(new Station(2.269, "2400 Rio Grande Street"));
		expected.addStation(new Station(2.599, "1200 North Lamar Boulevard"));
		expected.addStation(new Station(2.199, "2819 Guadalupe Street"));
		
		expected.addStation(new Station(2.499, "2600 Guadalupe Street"));
		expected.addStation(new Station(2.679, "917 North Lamar Boulevard"));
		expected.addStation(new Station(2.529, "1814 Guadalupe Street"));

		
		expected.equals(actual);
		for(int i = 0; i < expected.size(); i++){
			System.out.println(expected.getStation(i).getAddress() + " " + actual.getStation(i).getAddress());
			System.out.println(expected.getStation(i).getPrice() + " " + actual.getStation(i).getPrice());
			assertTrue(expected.getStation(i).getAddress().equals(actual.getStation(i).getAddress()));
			assertTrue(expected.getStation(i).getPrice() == actual.getStation(i).getPrice());
			
		}
	}

	@Test
	public void testInitialQuery() {
		Queryable tester = new Queryable();
		Location holder = new Location(32.9483,-96.7299);
		Results expected = new Results();
		Results actual = new Results();
		Station gasStation = new Station();
		



		actual.addStation(new Station(2.199, "522 West Belt Line Road"));
		actual.addStation(new Station(2.399, "236 East Spring Valley Road"));
		actual.addStation(new Station(2.399, "236 East Spring Valley Road"));
		actual.addStation(new Station(2.239, "102 East Arapaho Road"));
		actual.addStation(new Station(2.239, "102 East Arapaho Road"));
		actual.addStation(new Station(2.579, "540 Centennial Boulevard"));
		actual.addStation(new Station(2.579, "540 Centennial Boulevard"));



		
		expected = tester.initialQuery(holder);
		assertTrue(expected.size() == 7);
		for(int i = 0; i < expected.size(); i++){
			//System.out.println(expected.getStation(i).getAddress() + " " + actual.getStation(i).getAddress());
			//System.out.println(expected.getStation(i).getPrice() + " " + actual.getStation(i).getPrice());
			assertTrue(expected.getStation(i).getAddress().equals(actual.getStation(i).getAddress()));
			assertTrue(expected.getStation(i).getPrice() == actual.getStation(i).getPrice());
			
		}
		
		
	}
	
	
	@Test
	public void testabbreviations() {
		Queryable tester = new Queryable();
		String blvd = " N Blvd";
		String rd = " W Rd";
		String st = " S St";
		String pkwy = " E Pkwy";
		
				
		blvd = tester.fixAbbreviations(blvd);
		rd = tester.fixAbbreviations(rd);
		st = tester.fixAbbreviations(st);
		pkwy = tester.fixAbbreviations(pkwy);
		
		assertTrue(blvd.equals(" North Boulevard"));
		assertTrue(rd.equals(" West Road"));
		assertTrue(st.equals(" South Street"));
		assertTrue(pkwy.equals(" East Parkway"));
		
	}


}
