import org.omg.CORBA.DATA_CONVERSION;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;


public class FileManager  {

    private String targetPath;
    private String newDirPath;


    public FileManager() {
        this.targetPath = FileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }

    public String getTargetPath() {
        return targetPath;
    }

    public String getNewDirPath() {
        return newDirPath;
    }

    public void setNewDirPath(String dirPath){
        this.newDirPath = dirPath;
    }



    public synchronized boolean checkFileExists(String path) {
        File tempDir = new File(path);
        return tempDir.exists();
    }

    public synchronized void writeListToFile(List<String> list, String file) {
        String path = FileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        try {
            Path out = Paths.get(file);
            Files.write(out, list, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void writeMapToFile(LinkedHashMap<String, Integer> frequencyMap, String file) throws Exception{
        String path = FileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        Path out = Paths.get(file);
        Files.write(out, () -> frequencyMap.entrySet().stream()
                .<CharSequence>map(e -> e.getKey() + " : "+  e.getValue() + " Occurrences")
                .iterator());
    }

    public synchronized List<String> readFromFile(String path) throws NullPointerException {
        List<String> wordList = new ArrayList<>();
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

    public synchronized void makeDirectory(String dir) throws Exception {
        File f = new File(getTargetPath()+ dir);
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

    public String removeIllegalDirChars(String urlString){
        urlString = Pattern.compile("[^A-Za-z0-9]+").matcher(urlString).replaceAll(" ");
        return urlString;
    }

}

