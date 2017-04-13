package test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Testerjsoup {

    public static void main(String[] args) {

        Document doc;
        try {

            // need http protocol
            doc = Jsoup.connect("http://www.austingasprices.com/").get();

            // get all tr 
            Elements list = doc.select("tr");
            for (Element listObject : list) {

                // get the value from tr attribute
            	if (listObject.text().startsWith(".", 1)){
                    System.out.println("listObject : " + listObject.text());
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
