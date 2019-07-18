import org.junit.jupiter.api.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

class WordManagerTest {

    WordManager wm = new WordManager();
    String[] words = {"Check","in", "this","list", "for", "four", "less", "than", "four", "letter", "words"};

    @BeforeEach
    void setUp(){
        wm.setVerbs(wm.stringToList("Check this string too"));
        wm.setExclusions(wm.stringToList("Check this string"));
    }


    @Test
    void appendExclusionsTest() {
        List<String> testList = new ArrayList<>(Arrays.asList("Check","this","string", "for", "four", "less", "than", "four", "letter", "words"));
        List<String> before = wm.getExclusions();
        wm.appendExclusions(testList);
        List<String> after = wm.getExclusions();
        assertEquals(after.size(), before.size());
    }

    @Test
    void appendInclusionsTest() {
        List<String> testList = new ArrayList<>(Arrays.asList("Check","this","string", "for", "four", "less", "than", "four", "letter", "words"));
        List<String> before = wm.getVerbs();
        wm.appendInclusions(testList);
        List<String> after = wm.getVerbs();
        assertTrue(after.size() == before.size());
    }


    @Test
    void stringToList() {
        // List<String> testList = new ArrayList<>(Arrays.asList("Check","this","string"));
        String testString = "Check this string";
        assertLinesMatch(wm.stringToList(testString), wm.getExclusions() , "Running : Testing that list matches another list made with stringToList() method.");
    }

    @Test
    void removeFourLetterWordsTest() {
        List<String> testList1 = new ArrayList<>(Arrays.asList(words));
        List<String> testList2 = wm.removeFourLetterWords(new ArrayList<>(Arrays.asList(words)));
        assertTrue(testList1.size() > testList2.size(), "Running : Testing that words less than or equal to four are removed.");
    }

    @Test
    void removeExclusionWords() {

    }

    @AfterEach
    void afterAll() {
        System.out.println("Running: tearDown");
        wm = null;
        assertNull(wm);

        words = null;
        assertNull(words);

    }

}