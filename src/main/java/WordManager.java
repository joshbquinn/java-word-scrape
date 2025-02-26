import java.util.*;

public class WordManager {

    private List<String> verbs;
    private List<String> exclusions;
    private List<String> scraped;
    private LinkedHashMap<String, Integer> keywords;

    WordManager(){

    }

    public void setVerbs(List<String> inclusions) {
        this.verbs = inclusions;
    }
    public void setExclusions(List<String> exclusions) {
        this.exclusions = exclusions;
    }
    public void setScraped(List<String> scraped) {
        this.scraped = scraped;
    }
    public void setKeywords(LinkedHashMap<String, Integer> keywords){
        this.keywords = keywords;
    }

    public List<String> getVerbs() {
        return verbs;
    }
    public List<String> getExclusions() {
        return exclusions;
    }
    public List<String> getScraped() {
        return scraped;
    }
    public LinkedHashMap<String, Integer> getKeywords() {
        return keywords;
    }

    public void appendExclusions(List<String> moreExclusions){
        this.exclusions.addAll(moreExclusions);
    }
    public void appendInclusions(List<String> moreInclusions){
        this.verbs.addAll(moreInclusions);
    }


    public List<String> stringToList(String webPageContents) {
        return new ArrayList<>(Arrays.asList(webPageContents.split(" ")));
    }

    public List<String> removeFourLetterWords(List<String> words){
        words.removeIf(a -> (a.length() < 4));
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

    public Map<String, Integer> wordFrequency(List<String> words){
        if(words != null) {
            Map<String, Integer> frequencyMap = new HashMap<>();
            for (String s : words) {
                if (!frequencyMap.containsKey(s)) {

                    frequencyMap.put(s, 1);
                } else {

                    frequencyMap.put(s, frequencyMap.get(s) + 1);

                }

            }
            return frequencyMap;

        }
        System.out.println("Word list empty!");
        return null;
    }

    public LinkedHashMap<String, Integer> sortMapDescending(final Map<String, Integer> unsortedMap) {
       LinkedHashMap<String, Integer> sorted = new LinkedHashMap<>();
       unsortedMap.entrySet()
               .stream()
               .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
               .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));

       return sorted;
    }

}
