package zad2;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 * Created by yaroslavkohun on 1/1/18.
 */
public class NewVetoableChangeListener implements VetoableChangeListener {
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        if(evt.getPropertyName().equals("price")&&(Double)evt.getNewValue()<1000.00)
            throw new PropertyVetoException("Price change to: "+evt.getNewValue()+" not allowed", evt);
    }
}
