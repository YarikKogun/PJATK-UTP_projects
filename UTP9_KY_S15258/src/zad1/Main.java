/**
 *
 *  @author Kohun Yaroslav S15258
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class Main {
    static Map<String, List<String>> anagram = new HashMap<>();
    static int max = 0;
    static String globalkey;

    public static void main(String[] args) {
        BufferedReader reader;
        List<String> word = new ArrayList<>();
        try {
            URL site = new URL("http://www.puzzlers.org/pub/wordlists/unixdict.txt");
            reader = new BufferedReader(new InputStreamReader(site.openStream()));
            String str = reader.readLine();
            while (str != null) {
                word.add(str);
                str = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        word.stream().forEach(oneWord -> {
            String key = sort(oneWord);
            if (!anagram.containsKey(key)) {
                List<String> anList = new ArrayList<>();
                anagram.put(key, anList);
            }
            anagram.get(key).add(oneWord);
            if (anagram.get(key).size() > max) {
                max = anagram.get(key).size();
                globalkey = key;
            }
        });

        Set<String> keySet = anagram.keySet();
        for (String key : keySet) {
            if (anagram.get(key).size() == max) {
                for (String w : anagram.get(key)) {
                    System.out.print(w + " ");
                }
                System.out.println();
            }
        }

    }


    public static String sort(String p) {
        char[] arr = p.toString().toCharArray();
        Arrays.sort(arr);
        return String.copyValueOf(arr);
    }
}
