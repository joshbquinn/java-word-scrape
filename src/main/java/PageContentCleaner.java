import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageContentCleaner {

    private Pattern para;
    private Pattern html;
    private Pattern hTags;
    private Pattern heads;
    private Pattern scripts;
    private Pattern style;
    private Pattern specials;
    private Pattern allTags;
    private Pattern numbers;

    public PageContentCleaner(){

        this.para = Pattern.compile("<p.*?>(.*?)</p>");
        this.html = Pattern.compile("<a\\b[^>]*href=\"[^>]*>(.*?)</a>");
        this.hTags = Pattern.compile("<h.*?>(.*?)</.*?h.*?>");
        this.heads = Pattern.compile("<he.*?>(.*?)</.*?he.*?>");
        this.scripts = Pattern.compile("<script.*?/script>*.");
        this.style = Pattern.compile("<style.*?/style>");
        this.specials = Pattern.compile("[^A-Za-z0-9]+");
        this.allTags = Pattern.compile("<.*?>");
        this.numbers = Pattern.compile("(?<!\\S)[+-]?\\d+(?!\\S)");
    }


    public String removeUnwantedHTML(String webPageContents){
        webPageContents = heads.matcher(webPageContents).replaceAll("");
        webPageContents = scripts.matcher(webPageContents).replaceAll("");
        webPageContents = style.matcher(webPageContents).replaceAll("");
        webPageContents = allTags.matcher(webPageContents).replaceAll("");
        webPageContents = specials.matcher(webPageContents).replaceAll(" ");
        webPageContents = numbers.matcher(webPageContents).replaceAll("");
        return webPageContents;
    }



    public String keepParagraphContent(String webPageContents){
        StringBuilder sb = new StringBuilder();
        Matcher matcher = para.matcher(webPageContents);
        while(matcher.find()){
            sb.append(matcher.group());
        }
        return sb.toString();
    }

    public String keepHTagContent(String webPageContents){
        StringBuilder sb = new StringBuilder();
        Matcher matcher = hTags.matcher(webPageContents);
        while(matcher.find()){
            sb.append(matcher.group());
        }
        return sb.toString();
    }

}
