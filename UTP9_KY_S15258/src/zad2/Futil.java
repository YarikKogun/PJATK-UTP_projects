package zad2;

/**
 * Created by yaroslavkohun on 12/12/17.
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;


public class Futil {

    static String pathDir;
    static File resultFile;
    static List<String> list;


    public static void processDir(String dirName, String resultFileName) {
        pathDir = dirName;
        resultFile = new File(resultFileName);
        list = new ArrayList<>();
        walkThroughFileTree(pathDir, list);
        writeListInFile(list, resultFile);
    }

    public static void walkThroughFileTree(String pathDir, List<String> outList){
        try (final Stream<Path> pathStream = Files.walk(Paths.get(pathDir), FileVisitOption.FOLLOW_LINKS)) {
            pathStream
                    .filter((p) -> p.toFile().isFile())
                    .forEach(p -> {
                        Charset.defaultCharset();
                        try (Stream<String> stream = Files.lines(p, Charset.forName("Cp1250"))) {
                            stream
                                    .map(String::trim)
                                    .filter(s -> !s.isEmpty())
                                    .forEach(outList::add);
                        } catch (final IOException e) {
                            e.printStackTrace();
                        }
                    });

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void writeListInFile(List<String> list, File resFile){
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resFile), "UTF8"));
            for (String str : list)
                bw.write(str + "\r\n");

            bw.close();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}

