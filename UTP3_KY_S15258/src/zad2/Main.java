/**
 *
 *  @author Kohun Yaroslav S15258
 *
 */

package zad2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

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
    List<String> result =   dest.stream()
            .parallel()
            .filter(list -> list.startsWith("WAW"))
            .sequential()
            .map((string) -> {
              String[] str = string.split("\\s+");
              String rate = str[2];
              int newRate = (int) (new Integer(rate)*ratePLNvsEUR);
              rate = Integer.toString(newRate);
              str[2] = rate;
              String newEl = "to " + str[1] + " - price in PLN: " + str[2];
              return newEl;
            })
            .collect(Collectors.toCollection(ArrayList::new));

    for (String r : result) System.out.println(r);
  }
}
