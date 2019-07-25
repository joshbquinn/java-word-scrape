package Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordManager2 {

   private Word words;

    public WordManager2(){

    }

    public List<String> removeDuplicateWords(List<String> webPageWords){
        return null;
    }

    public List<String> stringToList(String webPageContents) {
        return new ArrayList<>(Arrays.asList(webPageContents.split(" ")));

    } //Method to create a string from  - should probably make this into a interface

    public List<String> removeFourLetterWords(List<String> words){
        words.removeIf(a -> (a.length() <= 4));
        return words;
    }

    public void removeWords(List<String> webPageWords, Word exclusions, Word scraped){

        if (webPageWords.removeAll(exclusions.getWords())) {
            scraped.setWords(webPageWords);
            System.out.println("**Exclusion Words were removed**.");
        }
        else{
            System.out.println("**Nothing Removed From List**");
        }

    }

}
