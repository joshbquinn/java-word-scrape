import org.junit.jupiter.api.*;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Word Fetcher Test")
class WordFetcherTest {

    WordFetcher wordFetcher = new WordFetcher();
    String stringUrl = "https://www.rte.ie/news";


    @BeforeEach
    void setUp() throws Exception {

    }


    @Test
    void wordFetchReturnsAStringTest() throws  Exception {
        String webContents = wordFetcher.wordFetch(new URL(stringUrl));
        assertNotNull(webContents, "The Object should contain a string of all of a URL's contents");

    }

    @AfterEach
    void tearDown(){
        wordFetcher = null;
        assertNull(wordFetcher);

    }
}