package test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class RetrieveBodyofHTML {

    public static void main(String[] args) {

        Document doc;
        int zipcode = 78705;
        try {

            // need http protocol
            doc = Jsoup.connect("http://www.autoblog.com/" + zipcode + "-gas-prices/").get();

            // get page title
            Element title = doc.body();
            System.out.println("body : " + title);

            // get all links
            Elements links = doc.select("a[href]");
            for (Element link : links) {

               // get the value from href attribute
               // System.out.println("\nlink : " + link.attr("href"));
               // System.out.println("text : " + link.text());

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("whoops");
        }

    }

}
