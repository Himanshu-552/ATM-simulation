package atm.simulation;
import java.awt.*;
import javax.swing.*;
import java.sql.*;


public class MiniStatement extends JFrame {
    Connection con;
    Statement st;
    ResultSet rec;
    
    JFrame frm;
    JLabel l1,mini,card,balance;
    
//    Allocating Memory
    MiniStatement(String pinnumber){
        frm=new JFrame("Mini Statement");
        l1= new JLabel("Indian Bank");
        mini=new JLabel();
        card=new JLabel();
        balance=new JLabel();
        
//      Establishing connection
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","Root@123");
            st=con.createStatement();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
//      Getting cardnumber from database
        try{
            rec=st.executeQuery("select * from login where cardpin= '"+pinnumber+"' ");
            while(rec.next()){
                card.setText("Card Number: "+ rec.getString("cardnum").substring(0,4)+ "XXXXXXXX" + rec.getString("cardnum").substring(12));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
  
        
//      Retreiving transaction history
        try{
            rec=st.executeQuery("select * from bank where cardpin= '"+pinnumber+"' ");
            while(rec.next()){
                mini.setText(mini.getText()+ "<html>" + rec.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rec.getString("type") +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rec.getString("amount") + "<br><br> <html>");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
//        Retreiving total balance
        try{
            int bal =0;
            rec=st.executeQuery("select * from bank where cardpin= '"+pinnumber+"' ");
            while(rec.next()){
                if(rec.getString("type").equals("Deposit")){
                    bal += Integer.parseInt(rec.getString("amount"));
                }
                else if(rec.getString("type").equals("Withdraw")){
                    bal -= Integer.parseInt(rec.getString("amount"));
                }
            }
            balance.setText("Your current account balance is Rs "+ bal);
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
    
    
//    Setting page layout
    public void setUpMiniStatement(){
        frm.setSize(450,600);
        frm.setLocation(550,120);
        frm.getContentPane().setBackground(Color.white);
        frm.setLayout(null);
        
        l1.setBounds(170,0,100,40);
        l1.setFont(new Font("Railway",Font.BOLD,17));
        
        card.setBounds(30,45,270,40);
        card.setFont(new Font("Railway",Font.BOLD,13));
        
        mini.setBounds(30,90,400,300);
        mini.setFont(new Font("Railway",Font.BOLD,13));
        
        balance.setBounds(30,400,300,40);
        balance.setFont(new Font("Railway",Font.BOLD,13));
        
        
        frm.add(l1);
        frm.add(card);
        frm.add(mini);
        frm.add(balance);
        
        frm.setVisible(true);   
    }
}
