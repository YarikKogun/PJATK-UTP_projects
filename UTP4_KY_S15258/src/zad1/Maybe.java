package zad1;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by yaroslavkohun on 10/27/17.
 */
public class Maybe<T> {
    public T value;
    public static<T> Maybe<T> of(T x) {
        Maybe<T> temp = new Maybe<T>();
        temp.value = x;
        return temp;
    }

    public void ifPresent(Consumer<? super T> cons) {
        if(value != null)
            cons.accept(value);
    }

    public <U> Maybe<U> map(Function<? super T, ? extends U> func) {
        Objects.requireNonNull(func);
        if(value != null){
            Maybe<U> temp = new Maybe<U>();
            temp.value = func.apply(value);
            return temp;
        }
        else{
            Maybe<U> t = new Maybe<>();
            return t;
        }
    }

    public T get() {
        if (value == null)
            throw new NoSuchElementException("maybe is empty");
        return value;
    }


    public boolean isPresent(){
        if(this.value != null)
            return true;
        else
            return false;
    }

    public T orElse(T defVal) {
        if(value !=null)
            return value;
        else
            return defVal;
    }

    public Maybe<T> filter(Predicate<? super T > pred) {
        if(pred.test(value) || value == null)
            return Maybe.this;
        else {
            Maybe<T> t = new Maybe<>();
            return t;
        }
    }

    @Override
    public String toString() {
        if(value != null)
            return "Maybe has value " + value.toString();
        else
            return "Maybe is empty";
    }

}
