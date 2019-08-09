import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class WordFetcher {



    public WordFetcher(){

    }

    public String wordFetch(URL url) {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            StringBuilder sb = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine + " ");
            }
            in.close();

            return sb.toString().toLowerCase();

        } catch (IOException e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
}
