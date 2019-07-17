import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordManager {

    private List<String> inclusions;
    private List<String> exclusions;
    private List<String> scraped;

    WordManager(){

    }


    public void setInclusions(List<String> inclusions) {
        this.inclusions = inclusions;
    }

    public void setExclusions(List<String> exclusions) {
        this.exclusions = exclusions;
    }

    public void setScraped(List<String> scraped) {
        this.scraped = scraped;
    }

    public List<String> getInclusions() {
        return inclusions;
    }

    public List<String> getExclusions() {
        return exclusions;
    }

    public List<String> getScraped() {
        return scraped;
    }

    public void appendExclusions(List<String> moreExclusions){
        this.exclusions.addAll(moreExclusions);
    }

    public void appendInclusions(List<String> moreInclusions){
        this.inclusions.addAll(moreInclusions);
    }

    public List<String> removeDuplicateWords(List<String> webPageWords){
        return null;
    }

    //Method to create a string from  - should probably make this into a interface
    public List<String> stringToList(String webPageContents) {
        return new ArrayList<>(Arrays.asList(webPageContents.split(" ")));

    }

    public List<String> removeFourLetterWords(List<String> words){
        words.removeIf(a -> (a.length() <= 4));
        return words;
    }

    public void removeExclusionWords(List<String> webPageWords){

        if (webPageWords.removeAll(exclusions)) {
            setScraped(webPageWords);
            System.out.println("**Exclusion Words were removed**.");
        }
        else{
            System.out.println("**Nothing Removed From List**");
        }

    }



}
