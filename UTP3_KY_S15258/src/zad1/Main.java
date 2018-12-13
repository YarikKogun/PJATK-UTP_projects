/**
 *
 *  @author Kohun Yaroslav S15258
 *
 */

package zad1;


import java.util.*;

public class Main {

  static List<String> getPricesInPLN(List<String> destinations, double xrate) {
    return ListCreator.collectFrom(destinations)
            .when(el -> {
                      String[] str = el.split("\\s+");
                      if(str[0].equals("WAW"))
                          return true;
                      else
                          return false;
                    }
            )
            .mapEvery( p -> {

                      String[] str = p.split("\\s+");
                      String rate = str[2];
                      int newRate = (int) (new Integer(rate)*xrate);
                      rate = Integer.toString(newRate);
                      str[2] = rate;
                      p ="to "+str[1]+" - price in PLN:   "+str[2];
                      return p;
                    }
            );
  }

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
    for (String r : result) System.out.println(r);
  }
}
