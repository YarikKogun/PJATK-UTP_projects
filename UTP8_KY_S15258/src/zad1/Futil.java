package zad1;

/**
 * Created by yaroslavkohun on 12/3/17.
 */

import static java.nio.file.FileVisitResult.CONTINUE;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;


public class Futil extends SimpleFileVisitor<Object> {

    static Path pathDir;
    static File resultFile;
    static List<String> list;

    public static void processDir(String dirName, String resultFileName){
        pathDir = Paths.get(dirName);
        resultFile = new File(resultFileName);
        list = new ArrayList<>();
        walkThroughFileTree(pathDir, list);
        writeListInFile(list, resultFile);
    }


    public static void walkThroughFileTree(Path pathDir, List<String> outList){
        try {
            Files.walkFileTree(pathDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
                    try {
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(
                                        new FileInputStream(file.toString()), "Cp1250"));

                        String nextString = br.readLine();
                        while (nextString != null) {
                            outList.add(nextString);
                            System.out.println(nextString);
                            nextString = br.readLine();
                        }

                        br.close();
                    } catch (Exception e) {e.printStackTrace();}

                    return CONTINUE;
                }
            });
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public static void writeListInFile(List<String> list, File resFile){
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(resFile), "UTF8"));

            for (String str : list)
                bw.write(str + "\r\n");

            bw.close();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}

