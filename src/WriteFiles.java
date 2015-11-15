import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Deus on 15.11.2015.
 */
public class WriteFiles {

    public static void main(String[] args) {

        //get user home dir irrespective of the underlying OS
        String nameHomeDir = System.getProperty("user.home");
        System.out.println("Home dir: "+ nameHomeDir);

        //return path of a file
        Path filePath = Paths.get(nameHomeDir, "data.txt");
        System.out.println("Path dir with file: "+ filePath);

        //write a file
        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath)) {

            bufferedWriter.write("First Line");
            bufferedWriter.newLine();

            //write next 10 lines in a loop
            for(int i=2; i<10; i++) {

                /*
                bufferedWriter.newLine();
                String line = "Line #" + i;
                bufferedWriter.write(line);
                */
                //or the other way
                String line = String.format("Line #%d%n",i);
                bufferedWriter.write(line);
            }

        } catch (IOException e) {
            System.out.println("IO error: \n" + e.getMessage());
        }
    }
}
