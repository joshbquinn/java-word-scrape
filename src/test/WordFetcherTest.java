import org.junit.jupiter.api.*;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Word Fetcher Test")
class WordFetcherTest {

    private WordFetcher wordFetcher = new WordFetcher();
    private String stringUrl = "https://www.rte.ie/news";


    @BeforeEach
    void setUp() throws Exception {
        URL testUrl = new URL(stringUrl);
        wordFetcher.setUrl(testUrl);
    }

    @Test
    void urlNotNullTest() throws Exception  {
        URL url = wordFetcher.getUrl();
        assertNotNull(url);
    }

    @Test
    void checkStringIsConvertedToURLTest() throws Exception {
        URL url = wordFetcher.stringToUrl(stringUrl);
        assertNotNull(url, "The Object Should not be null and should contain a url");
    }

    @Test
    void wordFetchReturnsAStringTest(){
        String webContents = wordFetcher.wordFetch(wordFetcher.getUrl());
        assertNotNull(webContents, "The Object should contain a string of all of a URL's contents");
    }

    @AfterEach
    void tearDown(){
        wordFetcher = null;
        assertNull(wordFetcher);

    }
}