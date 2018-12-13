/**
 *
 *  @author Kohun Yaroslav S15258
 *
 */

package zad1;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.function.BiFunction;

public class Calc {
    HashMap<String, BiFunction<BigDecimal, BigDecimal, BigDecimal>> biFunctionHashMap;
    String[] args;

    public Calc() {
        biFunctionHashMap = new HashMap<>();
        biFunctionHashMap.put("+", BigDecimal::add);
        biFunctionHashMap.put("-", BigDecimal::subtract);
        biFunctionHashMap.put("*", BigDecimal::multiply);
        biFunctionHashMap.put("/", (bigDecimal, divisor) -> bigDecimal.divide(divisor, 7, BigDecimal.ROUND_HALF_UP));
    }

    public String doCalc(String cmd) {
        String res = "";

        args = cmd.split("\\s+");

        try {
            BigDecimal a = new BigDecimal(args[0]);
            BigDecimal b = new BigDecimal(args[2]);
            BiFunction<BigDecimal, BigDecimal, BigDecimal> operator = biFunctionHashMap.get(args[1]);

            res = operator.apply(a, b).stripTrailingZeros().toString();

        } catch (Exception e) {
            res = "Invalid command to calc";
        } finally {
            return res;
        }
    }
}
