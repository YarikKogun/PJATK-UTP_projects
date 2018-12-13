/**
 *
 *  @author Kohun Yaroslav S15258
 *
 */

package zad2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CustomersPurchaseSortFind {
    public static ArrayList<Purchase> clients = new ArrayList<>();

    public void readFile(String fname) {
        try {
            Scanner scanner = new Scanner(new File(fname));

            while (scanner.hasNextLine())
                clients.add(new Purchase(scanner.nextLine()));

            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println();
        }
    }

    public void showSortedBy(String string) {
        ArrayList<Purchase> clientsBuffer = new ArrayList<>();
        clientsBuffer.addAll(clients);
        if(string.equals("Nazwiska")){
            Collections.sort(clientsBuffer, Purchase.nameComparator);

            System.out.println("Nazwiska");

            for(Purchase i : clientsBuffer)
                System.out.println(i.toString());

        }
        else if(string.equals("Koszty")){
            Collections.sort(clientsBuffer, Purchase.priceComparator);

            System.out.println("Koszty");

            for(Purchase i : clientsBuffer)
                System.out.println(i.toString()+"(koszt:"+i.getPrice()*i.getAmount()+")");
        }

        System.out.println();

    }

    public void showPurchaseFor(String id) {
        System.out.println("Klient " + id);
        for(Purchase i : clients)
            if(i.getId().equals(id))System.out.println(i.toString());

        System.out.println();


    }
}
