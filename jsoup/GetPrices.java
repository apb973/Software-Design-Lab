package test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class GetPrices {

    public static void main(String[] args) {

        Document doc;
        try {

            // need http protocol
            doc = Jsoup.connect("http://www.austingasprices.com/").get();

            // get all tr 
            Elements list = doc.select("th");
            for (Element listObject : list) {

                // get the value from tr attribute
            	if (listObject.text().startsWith(".", 1)){
            		
            		String[] printThis = listObject.text().split("Austin");
            		printThis = printThis[0].split("update");
            		
            		System.out.print("listObject : ");
            		for (String something : printThis){
            			something = something.trim();
                        System.out.print(something);
            		}
            		System.out.print("\n");
                    
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
