package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by yaroslavkohun on 10/7/17.
 */
public class CountryTable extends JTable{
    private String countriesFilename;
    String[] columnNames;
    Object[][] data;

    public CountryTable(String countriesFileName) throws IOException{
        this.countriesFilename = countriesFileName;

        ArrayList<String> list = new ArrayList<>();
        Scanner file = new Scanner(new File(this.countriesFilename));
        while (file.hasNext())
            list.add(file.nextLine());
        file.close();

        columnNames = list.get(0).split("\\t");


        data = new Object[list.size()-1][];

        for(int i = 1; i < list.size(); i++){
            String[] dataRow = list.get(i).split("\\t");
            data[i-1] = new Object[dataRow.length];
            int populationInt = Integer.parseInt(dataRow[dataRow.length-1].toString().replaceAll(" ",""))*1000;
            for(int j = 0; j < dataRow.length-1; j++){
                data[i-1][j] = dataRow[j];
            }
            data[i-1][dataRow.length-1] = populationInt;

        }


    }
    public JTable create (){
        JTable table = new JTable(data, columnNames){
            public TableCellRenderer getCellRenderer(int row, int column) {
                long k = (long)table.getValueAt(row,2);
                if(k> 20000000 && column == 2){
                    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                    renderer.setBackground(Color.red);
                    return renderer;
                }
                return super.getCellRenderer(row, column);
            }
        };
        return table;
    }

}
