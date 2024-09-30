import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HtmlRead {

    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
    }

    public HtmlRead() {

        try {
            System.out.println();
            System.out.print("hello \n");
            URL url = new URL("https://www.milton.edu/");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {
                if (line.contains("href")) {
                    int start = line.indexOf("https");
                    //task: chop off the html before the https
                    System.out.println(line.substring(start));
                    String link = line.substring(start);
                    int end = link.indexOf("\"");
                    System.out.println("chop start:"+ link);
                    System.out.println("link "+ link.substring(7,end));
                    //HW is to print out all of the links - put assignment on github
                }
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
            System.out.println("There's an error somewhere try again");
        }

    }

}
