import sun.nio.cs.StandardCharsets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class WordFetcher {

    private URL url;

    WordFetcher(){

    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public synchronized String wordFetch(URL url) {

         try {
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            StringBuilder sb = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine + " ");
            }

            in.close();

            return sb.toString();

        } catch(IOException e){
            System.out.println("Error: " + e);
        }

        return null;
    }

}
