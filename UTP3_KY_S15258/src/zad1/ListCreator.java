package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by yaroslavkohun on 10/22/17.
 */
public class ListCreator<String> {
    public List<String> resultList = new ArrayList();
    public List<String> list = new  ArrayList();

    public ListCreator<String> when(Predicate<String> p) {
        ListCreator<String> temp = new ListCreator();
        temp.list = new ArrayList();
        for(String element : list){
            if(p.test(element)){
                temp.resultList.add(element);
            }
        }
        return temp;
    }

    public static <String> ListCreator<String>  collectFrom(List<String> dest) {
        ListCreator<String> temp = new ListCreator();
        temp.list = dest;
        return temp;
    }

    public List<String> mapEvery(Function<String,String> p) {
        List<String> newlist = new ArrayList();
        for(String element : resultList){
            newlist.add(p.apply(element));
        }
        return newlist;
    }

}
