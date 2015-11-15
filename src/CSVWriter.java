import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 * Created by Deus on 15.11.2015.
 */
public class CSVWriter {

    public static void main(String[] args) {

        String nameHomeDir = System.getProperty("user.home");
        Path filePath = Paths.get(nameHomeDir, "data.csv");

        //we want to create a .csv file that can be accessed from excel or the like
        int a = 2;
        double x = 3.5;
        String[] str = {"Madrid in Spain", "Paris in France", "Rom in Italy"};
        LocalDateTime time = LocalDateTime.of(2016,12,19,16,35,48);

        //write a file
        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath)) {

            for (int i=0; i<3; i++){

                //create a formula
                String formel = String.format("=B%d+0,1", i + 1);

                //setup writer line with all input
                String line = String.format(
                        "%d;%f;%s;%s;%td.%tm.%tY %tH:%tM:%tS%n",
                        a,x,formel,str[i],time,time,time,time,time,time);

                bufferedWriter.write(line);

                a += 10;
                x += 2.8;
                time = time.plusDays(3);
            }

        } catch (IOException e) {
            System.out.println("IO error: \n" + e.getMessage());
        }
    }

}
