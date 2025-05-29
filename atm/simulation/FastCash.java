package atm.simulation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener{
    Connection con;
    Statement st;
    ResultSet rec;
    
    
    JFrame frm;
    ImageIcon ic1,ic2;
    Image i1;
    JLabel bgimage,l1;
    JButton b1,b2,b3,b4,b5,b6,b7;
    String pinnumber;
//    Allocating memory to elements
    FastCash(String pinnumber){
        this.pinnumber=pinnumber;
        frm=new JFrame("");
        ic1= new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        i1=ic1.getImage().getScaledInstance(900,830,Image.SCALE_DEFAULT);
        ic2=new ImageIcon(i1);
        bgimage= new JLabel(ic2);
        
        l1=new JLabel("Select Withdrawal Amount");
        
        b1=new JButton("100");
        b2=new JButton("500");
        b3=new JButton("1000");
        b4=new JButton("2000");
        b5=new JButton("5000");
        b6=new JButton("10000");
        b7=new JButton("Back");
    }
    
//    Setting up Page Layout
    public void setUpFastCash(){
        frm.setLayout(null);
        frm.setSize(900,830);
        frm.setLocation(300,0);
        bgimage.setBounds(0,0,900,830);
        
        l1.setBounds(225,270,700,35);
        l1.setForeground(Color.white);
        l1.setFont(new Font("System",Font.BOLD,16));
        
        b1.setBounds(160,385,130,27);
        b2.setBounds(380,385,130,27);
        b3.setBounds(160,416,130,27);
        b4.setBounds(380,416,130,27);
        b5.setBounds(160,447,130,27);
        b6.setBounds(380,447,130,27);
        b7.setBounds(380,478,130,27);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        
        frm.add(bgimage);
        bgimage.add(l1);
        bgimage.add(b1);
        bgimage.add(b2);
        bgimage.add(b3);
        bgimage.add(b4);
        bgimage.add(b5);
        bgimage.add(b6);
        bgimage.add(b7);
        
        frm.setUndecorated(true); 
        frm.setVisible(true);
    }
        
    
//    Establishing connection
    public void getConn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","Root@123");
        }catch(Exception e){
            System.out.println(e);
        }
    }
  
//  Setting functionalities of buttons
    public void actionPerformed(ActionEvent ae){
            
         if(ae.getSource()==b7){
             frm.setVisible(false);
             Transactions obj=new Transactions(pinnumber);
             obj.setUpTransactions();
         }
         else {
             int balance=0;
             String amt=((JButton)ae.getSource()).getText();
             getConn();
             Date date= new Date();
            try{
                st=con.createStatement();
                rec=st.executeQuery("select * from bank where cardpin= '"+pinnumber+"'");
                while(rec.next()){
                    if(rec.getString("type").equals("Deposit")){
                        balance= balance + Integer.parseInt(rec.getString("amount"));
                    }
                    else {
                        balance =balance - Integer.parseInt(rec.getString("amount"));
                    }
                }
                if(ae.getSource()!= b7 && balance< Integer.parseInt(amt)){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }
                else{
                    String query="insert into bank values('"+pinnumber+"','"+date+"','Withdraw','"+amt+"')";
                    st.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Rs '"+amt+"' Debited Successfully");
                    
                    frm.setVisible(false);
                    Transactions obj =new Transactions(pinnumber);
                    obj.setUpTransactions();
                }
            }
            catch(Exception e){
                 System.out.println(e);
            }
             
         }
    }
}

