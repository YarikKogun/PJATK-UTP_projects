/**
 *
 *  @author Kohun Yaroslav S15258
 *
 */

package zad2;


import java.util.Comparator;

public class Purchase {
    private  String id;
    private  String name;
    private  String product;
    private  double amount;
    private  double price;

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getProduct(){
        return product;
    }

    public double getAmount(){
        return amount;
    }

    public double getPrice(){
        return price;
    }

    @Override
    public String toString(){
        return id+";"+ name +";"+ product +";"+String.valueOf(amount)+";"+String.valueOf(price)+";";
    }

    public Purchase (String str){
        String[] arr = str.split(";");
        id = arr[0];
        name = arr[1];
        product = arr[2];
        amount = Double.valueOf(arr[3]);
        price = Double.valueOf(arr[4]);

    }
    public static Comparator<Purchase> nameComparator = new Comparator<Purchase>() {

        @Override
        public int compare(Purchase e1, Purchase e2) {
            int i = e1.getName().compareTo(e2.getName());

            if(i==0)
                i = e1.getId().compareTo(e2.getId());
            return i;
        }
    };

    public static Comparator<Purchase> priceComparator = new Comparator<Purchase>() {

        @Override
        public int compare(Purchase e1, Purchase e2) {
            double price1 =  e1.getPrice()*e1.getAmount();
            double price2 =  e2.getPrice()*e2.getAmount();
            int i = (int) (price2 - price1);

            if(i==0)
                i = e1.getId().compareTo(e2.getId());
            return i;
        }
    };
}
