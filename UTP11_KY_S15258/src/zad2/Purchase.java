/**
 *
 *  @author Kohun Yaroslav S15258
 *
 */

package zad2;


import java.beans.*;
import java.io.Serializable;

public class Purchase implements Serializable {
    private String product;
    private String data;
    private double price;
    private VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public Purchase(String product, String data, double price) {
        setProduct(product);
        setData(data);
        this.price = price;
    }

    public synchronized void setProduct(String product) {
        String oldProd =  this.product;
        this.product = product;
        propertyChangeSupport.firePropertyChange("data", oldProd, product);
    }

    public synchronized void setData(String data) {
        String oldData =  this.data;
        this.data = data;
        propertyChangeSupport.firePropertyChange("data", oldData, data);
    }

    public synchronized void setPrice(double price) throws PropertyVetoException {
        Double oldPrice = this.price;
        vetoableChangeSupport.fireVetoableChange("price", oldPrice, price);
        this.price = price;
        propertyChangeSupport.firePropertyChange("price", oldPrice, price);
    }

    public synchronized void AddVetoableChangeListener(VetoableChangeListener vetoableChangeListener){
        vetoableChangeSupport.addVetoableChangeListener(vetoableChangeListener);
    }

    public synchronized void AddPropertyChangeListener(PropertyChangeListener propertyChangeListener){
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }
    @Override
    public String toString() {
        return "Purchase [prod="+product+", data="+data+", price="+price+"]";
    }
}
