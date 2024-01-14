package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class bill_details extends JFrame {
    String meter;
    bill_details(String meter){

        this.meter = meter;
    setSize(700,650);
    setLocation(400,150);
    getContentPane().setBackground(Color.WHITE);
    setLayout(null);
    setVisible(true);
      JTable table = new JTable();
      try{
        database c = new database();
        String query_bill = "select * from bill where meter_no ='"+meter+"'";
          ResultSet resultSet = c.statement.executeQuery(query_bill);
           table .setModel(DbUtils.resultSetToTableModel(resultSet));




      }catch(Exception e){
          e.printStackTrace();
      }

      JScrollPane sp = new JScrollPane(table);
      sp.setBounds(0,0,700,650);
      add(sp);






           }
     public static void main(String[] args) {
        new bill_details("");
    }
}
