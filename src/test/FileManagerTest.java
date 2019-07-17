import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    private FileManager fm = new FileManager();
    private List<String> tempList = new ArrayList<>(Arrays.asList("Test", "List", "Here"));
    private File temp = new File("newTempFile.txt");


    @Test
    void checkFileDoesNotExistTest() {
        boolean b = fm.checkFileExists("newTempFile.txt");
        assertFalse(b, "This method should return False if the File does not exist");
    }

    @Test
    void checkFileDoesExistTest() {
        fm.writeToFile(tempList,"existsFile.txt" );
        boolean b = fm.checkFileExists("existsFile.txt");
        assertTrue(b, "This method should return true if the File does exist");

    }

    @Test
    void checkListIsWrittenToFileTest() {
        fm.writeToFile(tempList, "anotherTempFile.txt");
        assertTrue(fm.checkFileExists("anotherTempFile.txt"),
                "This method should return true if the File does exist");
    }

    @Test
    void moveFilesIOExceptionTest() {
    }

    @AfterEach
    void afterAll() throws Exception {

        fm = null;
        assertNull(fm);


        tempList = null;
        assertNull(tempList);



    }
}