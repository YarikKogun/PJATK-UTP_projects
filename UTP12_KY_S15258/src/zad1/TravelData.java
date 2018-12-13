package zad1;

import java.io.File;
import java.util.List;
import java.util.Locale;

/**
 * Created by yaroslavkohun on 1/20/18.
 */
public class TravelData {
    String[] location;
    String[] country;
    String[] departure;
    String[] arrival;
    String[] place;
    Double[] price;
    String[] currency;

    public TravelData(File data){
        File[] kontrAgentData = data.listFiles();
        
    }


    public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
    }
}
