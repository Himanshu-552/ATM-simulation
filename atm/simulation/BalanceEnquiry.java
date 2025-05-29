package atm.simulation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {
    Connection con;
    Statement st;
    ResultSet rec;
    
    JFrame frm;
    ImageIcon ic1,ic2;
    Image i1;
    JLabel img,l1;
    JButton b1;
    
    String pinnumber;
    
//    Allocating memory
    BalanceEnquiry(String pinnumber){
        this.pinnumber=pinnumber;
        frm=new JFrame();
        ic1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        i1=ic1.getImage().getScaledInstance(900,830,Image.SCALE_DEFAULT);
        ic2=new ImageIcon(i1);
        img=new JLabel(ic2);
        
        b1=new JButton("Back");
    }
    
    
//    Setting Page Layout
    public void setUpBalanceEnquiry(){
        frm.setSize(900,830);
        frm.setLocation(300,0);
        frm.setLayout(null);
        img.setBounds(0,0,900,830);
        
        b1.setBounds(380,478,130,27);
        b1.addActionListener(this);
        
        getConn();
        int balance=0;
        try{
            st=con.createStatement();
            rec=st.executeQuery("select * from bank where cardpin='"+pinnumber+"' ");
            while(rec.next()){
                if(rec.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(rec.getString("amount"));
                }
                else if(rec.getString("type").equals("Withdraw")){
                    balance -= Integer.parseInt(rec.getString("amount"));
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        l1=new JLabel("Your Current Account Balance is Rs "+ balance);
        l1.setForeground(Color.white);
        l1.setFont(new Font("Railway",Font.BOLD,15));
        l1.setBounds(170,290,400,35);
        
        
        frm.add(img);
        img.add(b1);
        img.add(l1);
        frm.setUndecorated(true);
        frm.setVisible(true);
    }
    
    
//    Establishing Connection
    public void getConn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","Root@123");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
//   Adding functionality to button
    public void actionPerformed(ActionEvent ae){
        frm.setVisible(false);
        Transactions obj=new Transactions(pinnumber);
        obj.setUpTransactions();
    }
}
