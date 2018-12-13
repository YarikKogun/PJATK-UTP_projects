/**
 *
 *  @author Kohun Yaroslav S15258
 *
 */

package zad1;


import java.util.ArrayList;
import java.util.List;

public class ListCreator<T> {

    protected List<T> list;

    public ListCreator(List<T> list) {
        this.list = new ArrayList<T>();
        for (T t : list) {
            this.list.add(t);
        }
    }

    public List mapEvery(Mapper map) {
        List<T> resList;
        int i = 0;
        resList = new ArrayList<T>();

        while (i < list.size()) {
            resList.add((T)map.map(list.get(i)));
            i++;
        }
        return resList;
    }

    public static <T> ListCreator<T> collectFrom(List<T> lst) {
        return new ListCreator<>(lst);
    }

    public ListCreator<T> when(Selector<T> sel) {
        for (int i = 0; i < list.size(); i++) {
            if (!sel.select(list.get(i))) {
                list.remove(i);
                i--;
            }
        }
        return this;
    }
}

