import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class FileManager  {

    private final String DIR;
    private String targetPath;
    private String newDirPath;


    public FileManager() {
        this.DIR = FileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        this.targetPath = DIR;
        this.newDirPath = targetPath;
    }


    public String getDIR() {
        return DIR;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPATH) {
        this.targetPath = targetPATH;
    }

    public void setNewDirPath(String newDirPath) {
        this.newDirPath = newDirPath;
    }

    public synchronized boolean checkFileExists(String path) {
        File tempDir = new File(getTargetPath() + path);

        if (tempDir.exists()){
            return true;
        }
        else {
            return false;
        }
    }


    public synchronized void writeToFile(List<String> list, String file) {
        String path = FileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        try {
            Path out = Paths.get(file);
            Files.write(out, list, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public synchronized List<String> readFromFile(String path) {
        List<String> wordList = null;
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(path));
            String line = buffer.readLine();

            if (line != null) {
                while (line != null) {
                    wordList.add(line);
                    // Read next line
                    line = buffer.readLine();
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        return wordList;
    }



    public synchronized void moveFiles(String fromPath, String toPath){

    }


    public synchronized void makeDirectory(String path) throws Exception {
        File f = new File(getTargetPath()+ path);
        if(!f.exists()){
            if(f.mkdir()){
                setNewDirPath(f.getPath());
                System.out.println("Created directory " + f.getPath());
            }
            else{
                System.out.println("Failed to create directory " + f.getPath());
            }
        }
    }

    public String timeStamper(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd.H.m.s");
        return format.format(new Date());
    }

}

