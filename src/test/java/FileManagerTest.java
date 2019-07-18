import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    FileManager fm = new FileManager();
    List<String> tempList = new ArrayList<>(Arrays.asList("Test", "List", "Here"));
    File temp = new File("newTempFile.txt");


    @Test
    void checkFileDoesNotExistTest() {
        boolean b = fm.checkFileExists("newTempFile.txt");
        assertFalse(b, "This method should return False if the File does not exist");
    }

    @Test
    void checkFileDoesExistTest() {
        fm.writeListToFile(tempList,"existsFile.txt" );
        boolean b = fm.checkFileExists("existsFile.txt");
        assertTrue(b, "This method should return true if the File does exist");

    }

    @Test
    void checkListIsWrittenToFileTest() {
        fm.writeListToFile(tempList, "anotherTempFile.txt");
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

        temp.delete();
        File f1 = new File("existsFile.txt"); f1.delete();
        File f2 = new File("anotherTempFile.txt"); f2.delete();
        File f3 = new File("newTempFile.txt"); f3.delete();
    }
}