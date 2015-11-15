import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Deus on 15.11.2015.
 */
public class ReadFiles {

    public static void main(String[] args) {

        //get user home dir irrespective of the underlying OS
        String nameHomeDir = System.getProperty("user.home");
        System.out.println("Home dir: "+ nameHomeDir);

        //return path of a file
        Path filePath = Paths.get(nameHomeDir, "data.txt");
        System.out.println("Path dir with file: "+ filePath);

        //reading files..
        try(BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {

            String line = bufferedReader.readLine();
            while(line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            System.out.println("IO error:\n" + e.getMessage() + " does not exist.");
        }

    }
}
