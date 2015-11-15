import java.io.IOException;
import java.nio.file.*;

/**
 * Created by Andy on 15.11.2015.
 *
 * Create, modify and delete files using operations
 * that work on every OS
 */
public class FileOperations {

    public static void main(String[] args) {

        String homeDir = System.getProperty("user.home");
        Path dirPath  = Paths.get(homeDir);

        //init some samples
        Path dataOriginal  = Paths.get(homeDir, "data.csv");
        Path dataCopy      = Paths.get(homeDir, "dataCopy.csv");
        Path dataNew       = Paths.get(homeDir, "dataNew.csv");
        //create subDir
        Path subDir  = Paths.get(homeDir, "subDir");
        Path dataSub  = Paths.get(homeDir, "subDir","dataCopy.csv");

        printList(dirPath, "data*");

        try {

            // check if the file exists
            if(Files.exists(dataOriginal)) {
                //make a copy
                Files.copy(dataOriginal, dataCopy, StandardCopyOption.REPLACE_EXISTING);
                printList(dirPath, "data*");
                //rename or move a file
                Files.move(dataCopy, dataNew, StandardCopyOption.REPLACE_EXISTING);
                printList(dirPath, "data*");
                //delete a file
                Files.delete(dataNew);
                printList(dirPath, "data*");
            }

            //check if subDir exists
            if(!Files.exists(subDir)) {
                Files.createDirectory(subDir);
                printList(dirPath, "data*");
                Files.copy(dataOriginal, dataSub, StandardCopyOption.REPLACE_EXISTING);
                printList(subDir, "data*");
                Files.delete(dataSub);
                printList(subDir, "data*");
                Files.delete(subDir);
                printList(dirPath, "data*");
            }

        } catch(IOException e) {
            System.out.println("IO error:\n" +e.getMessage());
        }
    }

    public static void printList(Path path, String data) {
        //traverse though dir
        try(DirectoryStream<Path> dirContent = Files.newDirectoryStream(path, data)) {

            for(Path input: dirContent) {
                System.out.println(""+input);
            }
            System.out.println("--------------------------------------------------");

        } catch(IOException e) {
            System.out.println("IO error:\n" + e.getMessage());
        }
    }
}
