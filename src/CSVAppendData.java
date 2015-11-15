import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

/**
 * Created by Deus on 15.11.2015.
 */
public class CSVAppendData {

    public static void main(String[] args) {

        String nameHomeDir = System.getProperty("user.home");
        Path filePath = Paths.get(nameHomeDir, "save.csv");

        StandardOpenOption soo;
        if(Files.exists(filePath)) soo = StandardOpenOption.APPEND;
        else                       soo = StandardOpenOption.CREATE;

        //write a file
        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath, soo)) {

            String line = String.format("%s;%s%n", "foo", "bar");
            bufferedWriter.write(line);

        } catch (IOException e) {
            System.out.println("IO error: \n" + e.getMessage());
        }
    }
}
