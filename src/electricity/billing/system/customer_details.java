package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;

public class customer_details extends JFrame implements ActionListener {
    Choice searchMeterCho,searchNameCho;
    JTable table;
    JButton search,print,close;
    customer_details(){
        super("Customer Details");
        getContentPane().setBackground(new Color(184, 178, 241));

        JLabel searchMeter = new JLabel("Search By Meter Number");
        searchMeter.setBounds(20,20,150,20);
        add(searchMeter);

        searchMeterCho = new Choice();
        searchMeterCho.setBounds(180,20,150,20);
        add(searchMeterCho);


        try{
            database c= new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer");
            while(resultSet.next()){
                searchMeterCho.add(resultSet.getString("meter_no"));
            }

        }catch(Exception E){
            E.printStackTrace();
        }

        JLabel searchName = new JLabel("Search By Name");
        searchName.setBounds(400,20,100,20);
        add(searchName);
        searchNameCho = new Choice();
        searchNameCho.setBounds(520,20,150,20);
        add(searchNameCho);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer");
            while(resultSet.next()){
                searchNameCho.add(resultSet.getString("name"));
            }

        }catch (Exception E){
            E.printStackTrace();
        }

        table = new JTable();
        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer");

            table.setModel(DbUtils.resultSetToTableModel(resultSet));


        }catch(Exception E){
            E.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,700,500);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane);


      search = new JButton("Search");
     search.setBackground(Color.WHITE);
     search.setBounds(20,70,80,20);
     search.addActionListener(this);
     add(search);

        print = new JButton("Print");
        print.setBackground(Color.WHITE);
        print.setBounds(120,70,80,20);
        print .addActionListener(this);
        add(print);


        close = new JButton("Close");
        close.setBackground(Color.WHITE);
        close.setBounds(600,70,80,20);
        close.addActionListener(this);
        add(close);




        setLayout(null);
        setSize(700,500);
        setLocation(400,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== search){
            String query_search = "select * from new_customer where meter_no ='"+searchMeterCho.getSelectedItem()+"' and name = '"+searchNameCho.getSelectedItem()+"'";
            try{
             database c = new database();
             ResultSet resultSet = c.statement.executeQuery(query_search);

             table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch(Exception E){
                E.printStackTrace();
            }
        }
        else if(e.getSource()== print){
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new customer_details();
    }
}