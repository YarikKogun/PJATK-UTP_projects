/**
 *
 *  @author Kohun Yaroslav S15258
 *
 */

package zad2;


import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class Main {
  public static void main(String[] args) {

    Purchase purch = new Purchase("komputer", "nie ma promocji", 3000.00);
    System.out.println(purch);

    VetoableChangeListener vetoableChangeListener = new NewVetoableChangeListener();
    PropertyChangeListener propertyChangeListener = new NewPropertyChangeListener();
    purch.AddVetoableChangeListener(vetoableChangeListener);
    purch.AddPropertyChangeListener(propertyChangeListener);

    try {
      purch.setData("w promocji");
      purch.setPrice(2000.00);
      System.out.println(purch);

      purch.setPrice(500.00);

    } catch (PropertyVetoException exc) {
      System.out.println(exc.getMessage());
    }
    System.out.println(purch);

  }
}
