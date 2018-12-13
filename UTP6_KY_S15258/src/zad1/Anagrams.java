/**
 *
 *  @author Kohun Yaroslav S15258
 *
 */

package zad1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Anagrams {

    ArrayList<String> wordsList;
    ArrayList<String> secondList;
    List<ArrayList<String>> anagram;

    public Anagrams(String path) throws FileNotFoundException {
        anagram = new ArrayList<>();
        wordsList = new ArrayList<>();

        Scanner scanner = new Scanner(new File(path));

        while(scanner.hasNext())
            wordsList.add(scanner.next());

        scanner.close();
    }

    public List<ArrayList<String>> getSortedByAnQty() {
        for(int i = 0; i<wordsList.size(); i++){

            String word1 = wordsList.get(i);

            if(!word1.equals("used")){
                secondList = new ArrayList<>();
                secondList.add(word1);
                char[] chars1 = word1.toCharArray();
                Arrays.sort(chars1);

                for(int j = i+1; j<wordsList.size(); j++){

                    String word2 = wordsList.get(j);

                    if(!word2.equals("used")){
                        char[] chars2 = word2.toCharArray();

                        Arrays.sort(chars2);

                        if(Arrays.equals(chars1, chars2)){
                            secondList.add(word2);
                            wordsList.set(j, "used");
                        }
                    }
                }
                wordsList.set(i, "used");
                Collections.sort(secondList);
                anagram.add(secondList);

            }
        }

        Collections.sort(anagram,Anagrams.Comparator);
        return anagram;
    }

    public static Comparator<ArrayList<String>> Comparator = new Comparator<ArrayList<String>>() {
        @Override
        public int compare(ArrayList<String> arrList1, ArrayList<String> arrList2) {
            int i =  arrList2.size()-arrList1.size();
            if(i==0) {
                i = arrList2.get(0).compareTo(arrList1.get(0));
            }
            return i;
        }
    };

    public String getAnagramsFor(String word) {
        String result = "";

        for(ArrayList<String> list : anagram){
            if(list.contains(word)){
                list.remove(word);
                result = word + ": " + list;
                break;
            }
        }
        return result;
    }
}

