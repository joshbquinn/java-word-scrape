import java.net.URL;

public class Driver {

    public static void main(String[] args) throws Exception {
        WordManager wordManager = new WordManager();
        WordFetcher wordFetcher = new WordFetcher();
        UrlCleaner urlCleaner = new UrlCleaner();
        FileManager fileManager = new FileManager();




        /*Create inclusion and exclusion words if they don't exist*/
        if(wordManager.getExclusions() == null || !fileManager.checkFileExists(fileManager.getTargetPath()+ "exclusions.txt"))
        {
            wordManager.setExclusions(
                    wordManager.stringToList(
                            wordFetcher.wordFetch(
                                    wordFetcher.stringToUrl("https://www.scrapmaker.com/data/wordlists/language/Transitionalwords(72).txt"))));
            fileManager.writeToFile(wordManager.getExclusions(), "exclusions.txt");

        }

        if(wordManager.getInclusions() == null || !fileManager.checkFileExists(fileManager.getTargetPath()+"inclusions.txt")) {
            wordManager.setInclusions(
                    wordManager.stringToList(
                            wordFetcher.wordFetch(
                                    wordFetcher.stringToUrl("https://www.scrapmaker.com/data/wordlists/language/Nouns(5,449).txt"))));
            wordManager.appendInclusions(
                    wordManager.stringToList(
                            wordFetcher.wordFetch(
                                    wordFetcher.stringToUrl("https://www.scrapmaker.com/data/wordlists/language/Verbs(4,874).txt"))));

            fileManager.writeToFile(wordManager.getInclusions(), "inclusions.txt");
        }







        /*Fetch all string contents from URL*/
        String url = "https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html#removeIf-java.util.function.Predicate-";
        String webContentsString = wordFetcher.wordFetch(wordFetcher.stringToUrl(url));






        /*Clean the string and keep key elements i.e paragraphs and header tags*/
        String paras = urlCleaner.keepParagraphContent(webContentsString);







        /*Create a list from the string*/
        wordManager.setScraped(wordManager.stringToList(urlCleaner.removeUnwantedHTML(paras)));







        /*Remove exclusion words from the list*/
        wordManager.removeExclusionWords(wordManager.removeFourLetterWords(wordManager.getScraped()));






        /*Console test to see if correct words are printed*/
//        for(String s: wordManager.getScraped()){
//            System.out.println(s);
//        }





        /*Write list to file*/
        String cleanURL =  urlCleaner.removeIllegalDirChars(url).replace(" ", "_");
        wordManager.getScraped().add(0, cleanURL);
        fileManager.writeToFile(wordManager.getScraped(), cleanURL + fileManager.timeStamper() + "-word-scrape.txt");



    }


}