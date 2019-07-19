import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class WordScraperApplication {

    public static void main(String[] args) throws Exception {

        WordManager wordManager = new WordManager();
        WordFetcher wordFetcher = new WordFetcher();
        PageContentCleaner contentCleaner = new PageContentCleaner();
        FileManager fileManager = new FileManager();

        setList(wordManager, wordFetcher, fileManager, "exclusions.txt","https://raw.githubusercontent.com/joshbquinn/englishwords/master/commonwords.txt");
        setList(wordManager, wordFetcher, fileManager, "verbs.txt","https://www.scrapmaker.com/data/wordlists/language/Verbs(4,874).txt");

        cleanWebPageContent(contentCleaner, wordFetcher, args[0], wordManager);
        checkWordFrequency(wordManager);
        fileWriter(wordManager, fileManager, args[0]);


    }

    private static void setList(WordManager wordManager, WordFetcher wordFetcher, FileManager fileManager, String fileName, String url) throws MalformedURLException {
        if(wordManager.getExclusions() == null) {

            if (fileManager.checkFileExists(fileName)) {
                List<String> exclusionList = fileManager.readFromFile(fileName);
                wordManager.setExclusions(exclusionList);
            }
        else {
                URL exclusionURL = new URL(url);
                String exclusionString = wordFetcher.wordFetch(exclusionURL);
                List<String> exclusionList = wordManager.stringToList(exclusionString);
                wordManager.setExclusions(exclusionList);
                fileManager.writeListToFile(wordManager.getExclusions(), fileName);
            }
        }
    }

    private static void cleanWebPageContent(PageContentCleaner cleaner, WordFetcher fetch, String url,  WordManager wordManager) throws Exception {

        String allPageContents = fetch.wordFetch(new URL(url));
        String paragraphContent = cleaner.keepParagraphContent(allPageContents);
        String clean = cleaner.removeUnwantedHTML(paragraphContent);
        wordManager.removeExclusionWords(wordManager.removeFourLetterWords(wordManager.stringToList(clean)));
    }

    private static void checkWordFrequency(WordManager wordManager){
        Map<String, Integer> keyWords = wordManager.wordFrequency(wordManager.getScraped()); // Return a Map from the scraped words that collects the word frequency
        wordManager.setKeywords(wordManager.sortMapDescending(keyWords)); // Return a sorted LinkedHasMap of descending values from the keyWords Map
    }

    private static void fileWriter(WordManager wM, FileManager fM, String url) throws Exception {

        String cleanURL =  fM.removeIllegalDirChars(url).replace(" ", "_") + fM.timeStamper(); // Remove illegal characters from the URL string so it can be legally saved as a file name.
        fM.writeListToFile(wM.getScraped(), cleanURL + "-ALL_WORDS.txt");  // Write the list to a file and save file with a unique name (cleaned URL string + time stamp)
        fM.writeMapToFile(wM.getKeywords(), cleanURL + "-KEY_WORDS.txt");

    }


}