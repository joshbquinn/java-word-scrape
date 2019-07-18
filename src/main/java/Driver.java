import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Driver {

    public static void main(String[] args) throws Exception {

        WordManager wordManager = new WordManager();
        WordFetcher wordFetcher = new WordFetcher();
        UrlCleaner urlCleaner = new UrlCleaner();
        FileManager fileManager = new FileManager();

        String targetPath = fileManager.getTargetPath();



        // Create exclusion word list if doesn't exist
        if(wordManager.getExclusions() == null) {

            if (fileManager.checkFileExists("exclusions.txt")) {
                List<String> exclusionList = fileManager.readFromFile("exclusions.txt");
                wordManager.setExclusions(exclusionList);
            }
        else {
                URL exclusionURL = new URL("https://www.scrapmaker.com/data/wordlists/language/Transitionalwords(72).txt");
                String exclusionString = wordFetcher.wordFetch(exclusionURL);
                List<String> exclusionList = wordManager.stringToList(exclusionString);
                wordManager.setExclusions(exclusionList);
                fileManager.writeListToFile(wordManager.getExclusions(), "exclusions.txt");
            }
        }

        // Create verb list if they doesn't exist
        if(wordManager.getVerbs() == null) {

            if (fileManager.checkFileExists("verbs.txt")) {
                List<String> verbList = fileManager.readFromFile("verbs.txt");
                wordManager.setVerbs(verbList);
            }
            else {
                URL verbURL = new URL("https://www.scrapmaker.com/data/wordlists/language/Verbs(4,874).txt");
                String verbString = wordFetcher.wordFetch(verbURL);
                List<String> verbList = wordManager.stringToList(verbString);
                wordManager.setVerbs(verbList);
                fileManager.writeListToFile(wordManager.getVerbs(),"verbs.txt");
            }
        }

//        if(wordManager.getInclusions() == null || !fileManager.checkFileExists(targetPath +"inclusions.txt")) {
//            wordManager.setInclusions(
//                    wordManager.stringToList(
//                            wordFetcher.wordFetch(
//                                    wordFetcher.stringToUrl("https://www.scrapmaker.com/data/wordlists/language/Nouns(5,449).txt"))));
//            wordManager.appendInclusions(
//                    wordManager.stringToList(
//                            wordFetcher.wordFetch(
//                                    wordFetcher.stringToUrl("https://www.scrapmaker.com/data/wordlists/language/Verbs(4,874).txt"))));
//
//            fileManager.writeListToFile(wordManager.getInclusions(), "inclusions.txt");
//        }


        //Specify URL from cli and Fetch all string contents from the source
        //Return a string from the URL source
        String url = "https://www.rte.ie/news/2019/0718/1063666-lisa-smith/";
        String webContentsString = wordFetcher.wordFetch(new URL(url));

        // Clean the string and keep key elements i.e paragraphs
        String paras = urlCleaner.keepParagraphContent(webContentsString);

        // Remove all unwanted HTML
        // Create a list from the remaining string
        // set Scraped word list
        wordManager.setScraped(wordManager.stringToList(urlCleaner.removeUnwantedHTML(paras)));

        // Get the scraped word list
        // Remove four letter words from scraped list
        // Remove exclusion words from scraped list and internally reset scraped word list
        wordManager.removeExclusionWords(wordManager.removeFourLetterWords(wordManager.getScraped()));

        // Return a Map from the scraped words that collects the word frequency
        Map<String, Integer> keyWords = wordManager.wordFrequency(wordManager.getScraped());

        // Return a sorted LinkedHasMap of descending values from the keyWords Map
        LinkedHashMap sortKeyWords = wordManager.sortMapDescending(keyWords);

        // At first element add the url i.e. the original URL source
        wordManager.getScraped().add(0, url);
        // Remove illegal characters from the URL string so it can be legally saved as a file name.
        String cleanURL =  urlCleaner.removeIllegalDirChars(url).replace(" ", "_");
        // Write the list to a file and save file with a unique name (cleaned URL string + time stamp)
        fileManager.writeListToFile(wordManager.getScraped(), cleanURL + fileManager.timeStamper() + "-word-scrape.txt");

        fileManager.writeMapToFile(sortKeyWords, "SiteKeyWords.txt");
    }


}