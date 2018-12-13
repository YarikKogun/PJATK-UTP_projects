package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by yaroslavkohun on 11/26/17.
 */
public class ProgLang {
    private List<String> progsList = new ArrayList<>();
    private List<String> langsList = new ArrayList<>();
    private Map<String, List<String>> LangsMap = new LinkedHashMap<>();
    private Map<String, List<String>> ProgsMap = new LinkedHashMap<>();

    public ProgLang(String filePath) throws Exception{
        BufferedReader TSVFile = new BufferedReader(new FileReader(filePath));

        String dataRow = TSVFile.readLine();
        while (dataRow != null){
            String[] dataArray = dataRow.split("\t");
            List<String> tempProgsList = new ArrayList<>();
            for (int i = 1; i<dataArray.length; i++){
                if(!tempProgsList.contains(dataArray[i]))
                    tempProgsList.add(dataArray[i]);
            }
            LangsMap.put(dataArray[0], tempProgsList);
            langsList.add(dataArray[0]);

            for(int i = 1; i<dataArray.length; i++) {
                if (ProgsMap.containsKey(dataArray[i])){
                    List<String> tmpLangsList = ProgsMap.get(dataArray[i]);
                    if(!tmpLangsList.contains(dataArray[0]))
                        tmpLangsList.add(dataArray[0]);
                    ProgsMap.replace(dataArray[i],tmpLangsList);
                }
                else {
                    List<String> tmpLangsList = new ArrayList<>();
                    tmpLangsList.add(dataArray[0]);
                    ProgsMap.put(dataArray[i], tmpLangsList);
                }
            }

            for (int i = 1; i <dataArray.length; i++){
                if(!progsList.contains(dataArray[i]))
                    progsList.add(dataArray[i]);
            }
            dataRow = TSVFile.readLine();
        }
    }

    public Map<String, List<String>> getLangsMap() {
        return LangsMap;
    }

    public Map<String, List<String>> getProgsMap() {
        return ProgsMap;
    }

    public Map<String, List<String>> getLangsMapSortedByNumOfProgs() {
        return sorted(LangsMap, (Map.Entry<String, List<String>> e1, Map.Entry<String, List<String>> e2) -> {
            int rezVal = Integer.compare(e1.getValue().size(), e2.getValue().size());
            if (rezVal != 0) {
                return rezVal * -1;
            }
            return e1.getKey().compareTo(e2.getKey());
        });
    }
    public Map<String, List<String>> getProgsMapSortedByNumOfLangs() {
        return sorted(ProgsMap, (Map.Entry<String, List<String>> e1, Map.Entry<String, List<String>> e2) -> {
            int rezVal = Integer.compare(e1.getValue().size(), e2.getValue().size());
            if (rezVal != 0) {
                return rezVal * -1;
            }
            return e1.getKey().compareTo(e2.getKey());
        });
    }


    public Map<String, List<String>> getProgsMapForNumOfLangsGreaterThan(int n) {
        Map<String, List<String>> NewProgsMap = new LinkedHashMap<>();

        for(String programmer: progsList){
            if(ProgsMap.get(programmer).size()>n){
                NewProgsMap.put(programmer, ProgsMap.get(programmer));
            }
        }

        return NewProgsMap;
    }

    public static <T, V> Map<T, V> sorted(Map<T, V> map, Comparator<Map.Entry<T, V>> func){
        List<Map.Entry<T, V>> list = new LinkedList<>(map.entrySet().stream().sorted(func).collect(Collectors.toList()));
        Map<T, V> result = new LinkedHashMap<>();
        for (Map.Entry<T, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static <T, V> Map<T, V> filtered(Map<T, V> map, Predicate<Map.Entry<T, V>> pred){
        List<Map.Entry<T, V>> list = new LinkedList<>(map.entrySet().stream().filter(pred).collect(Collectors.toList()));
        Map<T, V> result = new LinkedHashMap<>();
        for (Map.Entry<T, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}

