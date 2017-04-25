package test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class GetPrices {

    public static void main(String[] args) {

        Document doc;
        int zipcode = 78705;
        String city = "Austin";
        try {
            doc = Jsoup.connect("http://www.autoblog.com/" + zipcode + "-gas-prices/").get();
            Elements addlist = doc.select("div");
            int counter2 = 0;
            int i = 0;
            for (Element listObject : addlist) {
            	if (!(listObject.text().isEmpty())){
            		String[] printThis = listObject.text().split("123456798");
            		printThis = printThis[0].split("Name PPG Address Location DistanceDist.");
            		if(counter2==62){
            			String[] parsingArray = new String[100];
                		for (String something : printThis){
                			something = something.trim();
                			if (!something.isEmpty()){
                        			parsingArray = something.split("\\$");
                        			for (int k = 1; k<11; k++){
                        				parsingArray[k] = parsingArray[k].replaceAll(",.*$" , "");
                        				parsingArray[k] = parsingArray[k].replaceAll(" " + city, "");
                        				}                        			
                        			for (int k = 1; k<11; k++) {
                        				//System.out.println(parsingArray[k]);
                        				String[] newStation = new String[2];
                        				newStation = parsingArray[k].split(" ", 2);
                            			for (String print : newStation){
                            				System.out.println(print);
                            			}                        				
                        			}                
                			} 
                		}
                		System.out.print("\n");
            		}
            		counter2++;
            	}
            	i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
