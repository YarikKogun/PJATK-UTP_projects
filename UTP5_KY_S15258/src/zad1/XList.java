package zad1;

/**
 * Created by yaroslavkohun on 11/13/17.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends LinkedList<T> {

    public XList(T... type) {super(new ArrayList<>(Arrays.asList(type)));}

    public XList(Collection<T> type) {super((type));}

    public XList() {super();}

    private static XList<String> split(String str, String split) {
        String[] wrt = str.split(split);
        XList<String> res = new XList<>(wrt);
        return res;
    }

    public static <T> XList<T> of(T... args) {
        T[] r = args;
        return new XList<>(r);
    }

    public static <Type> XList<Type> of(Collection<Type> set) {
        Collection<Type> r = set;
        return new XList<>(r);
    }

    public static XList<String> charsOf(String str) {
        XList list = split(str, "");
        return new XList<>( list);
    }

    public static XList<String> tokensOf(String... str) {
        if (str.length -1  == 0) {
            return new XList<>(split(str[0], " "));
        }
        int size = str.length;
        String res = "";
        for (int n = 0; n <= size-1; n++) {
            res += str[n];
        }

        XList list = split(res, str[str.length - 1]);
        return new XList<>(list);
    }

    public XList<T> union(Collection<T> coll) {
        XList<T> res = new XList(this);
        Collection<T> col = coll;
        res.addAll(col);
        return res;
    }

    public XList<T> union(T... args) {
        XList<T> tmp = new XList(this);
        for (T j : args)
            tmp.add(j);

        return tmp;
    }


    public XList<T> diff(Collection<T> args) {
        XList<T> list = new XList<>(this);
        Collection<T> colect = args;
        list.removeAll(colect);
        return list;
    }

    public XList<T> unique() {return new XList<>(new LinkedHashSet<>(this));}


    public static <T> XList<XList<T>> Combine(XList args) {
        XList<XList<T>> res = new XList<>();
        int size = args.size()-1;

        if(args.getFirst() instanceof Collection){
            XList<T> head;
            int headSize;
            head = new XList((Collection) args.get(size));
            headSize = head.size();

            if(size == 0){
                for(T el:head)res.add(new XList<T>(el));
                return res;
            }

            args.remove(size);
            XList<XList<T>> data = Combine(args);
            int dataSize = data.size();

            for( int Head = 0; Head < headSize; Head++){
                for(int Data = 0; Data < dataSize;Data++){
                    res.add(data.get(Data).union(head.get(Head)));
                }
            }
        }
        return res;
    }


    public XList<XList<T>> combine(){return Combine(this);}

    public void forEachWithIndex(BiConsumer<T, Integer> cons) {
        XList<T> el = this;
        for(int n = 0; n < this.size(); n++){
            cons.accept(el.get(n), n);
        }
    }

    public String join(String string) {
        XList<T> el = this;
        String s = el.stream().map(lista -> lista.toString()).collect(Collectors.joining(string));

        return s;
    }

    public String join() {
        XList<T> el = this;
        String s = el.stream().map(e -> e.toString()).collect(Collectors.joining(""));
        return s;
    }

    public <Type, Type1> XList<Type> collect(Function<T, Type1> args){
        XList<Type1> res = new XList<>();
        int length = this.size();
        XList<T> el = this;

        for(int i =0; i < length; i++){
            res.add(args.apply(el.get(i)));
        }
        return (XList<Type>) res;
    }
}
