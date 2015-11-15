import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Andy on 15.11.2015.
 *
 * traverse through a directory and print its content
 */
public class traverseDir {

    public static void main(String[] args) {

        String homeDir = System.getProperty("user.home");
        Path dirPath  = Paths.get(homeDir);

        //traverse though dir
        try(DirectoryStream<Path> dirContent = Files.newDirectoryStream(dirPath, "*.csv")) {

            for(Path input: dirContent) {
                System.out.println(""+input);
            }

        } catch(IOException e) {
            System.out.println("IO error:\n" + e.getMessage());
        }
    }
}
