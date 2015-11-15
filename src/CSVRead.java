import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Deus on 15.11.2015.
 */
public class CSVRead {

    public static void main(String[] args) {

        String nameHomeDir = System.getProperty("user.home");
        Path filePath = Paths.get(nameHomeDir, "data.csv");

        int a;
        double x;
        LocalDateTime t;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy H:m:s");

        //reading files..
        try(BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {

            String line = bufferedReader.readLine();
            while(line != null) {
                String[] str = line.split(";");

                a = Integer.parseInt(str[0]);
                x = Double.parseDouble(str[1].replace(",","."));
                //str[2] and str[3] are already Strings, therefore no parsing required
                t = LocalDateTime.parse(str[4], dtf);
                System.out.printf(
                        "%d | %f | %s | %s | %td.%tm.%tY %tH:%tM:%tS%n",
                        a,x,str[2],str[3], t,t,t,t,t,t
                );
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            System.out.println("IO error:\n" + e.getMessage() + " does not exist.");
        }

    }
}
