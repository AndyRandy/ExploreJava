import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by Andy on 15.11.2015.
 *
 * You can get some file information by using java libs
 */
public class FileInfo {

    public static void main(String[] args) {

        //get path
        String homeDir = System.getProperty("user.home");
        Path filePath  = Paths.get(homeDir, "data.csv");

        getFileInfo(homeDir, filePath);
    }

    public static void getFileInfo(String homeDir, Path filePath) {

        System.out.println("Path with file: " + filePath);

        try {
            // under windows use DosFileAttributes and
            // under linux or osx PosixFileAttributes
            BasicFileAttributes bfa = Files.readAttributes(filePath, BasicFileAttributes.class);
            System.out.println("File size: " + bfa.size() + " bytes");
            System.out.println("is dir:    " + bfa.isDirectory());
            System.out.println("is file:   " + bfa.isRegularFile());
            // creation/ last modified time using file time
            ftPrint("Created:   ", bfa.creationTime());
            ftPrint("Last mod : ", bfa.lastModifiedTime());

        } catch (IOException e) {
            System.out.println("IO error:\n" + e.getMessage());
        }

    }

    private static void ftPrint(String s, FileTime fileTime) {

        Instant ins = fileTime.toInstant();
        LocalDateTime t = LocalDateTime.ofInstant(ins, ZoneId.systemDefault());

        System.out.printf(
                "%s: %td.%tm.%tY %tH:%tM:%tS%n",
                s, t,t,t,t,t,t
        );
    }

}
