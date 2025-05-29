package atm.simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PinChange extends JFrame implements ActionListener{
    Connection con;
    Statement st;
    
    JFrame frm;
    ImageIcon ic1,ic2;
    Image i1;
    JLabel img,l1,l2,l3;
    JTextField t1,t2;
    JButton b1,b2;
    String pinnumber="",npin="",rpin="";
    
//    Allocating Memory to component
    PinChange(String pinnumber){
        this.pinnumber=pinnumber;
        frm=new JFrame();
        ic1= new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        i1=ic1.getImage().getScaledInstance(900,830,Image.SCALE_DEFAULT);
        ic2=new ImageIcon(i1);
        img= new JLabel(ic2);
        
        l1=new JLabel("Change Your PIN");
        l2=new JLabel("Enter New PIN:");
        l3=new JLabel("Re-Enter New PIN:");
        t1= new JTextField();
        t2= new JTextField();
        
        b1=new JButton("Change");
        b2=new JButton("Back");
        
    }
    
//   Setting Page layout
    public void setUpPinChange(){
        frm.setSize(900,830);
        frm.setLocation(300,0);
        frm.setLayout(null);
        img.setBounds(0,0,900,830);
        
        l1.setBounds(260,265,400,35);
        l1.setForeground(Color.white);
        l1.setFont(new Font("System",Font.BOLD,19));
        
        l2.setBounds(170,310,200,35);
        l2.setForeground(Color.white);
        l2.setFont(new Font("Railway",Font.BOLD,15));
        t1.setBounds(330,315,170,22);
        t1.setFont(new Font("Railway",Font.BOLD,15));
        
        l3.setBounds(170,345,200,35);
        l3.setForeground(Color.white);
        l3.setFont(new Font("Railway",Font.BOLD,15));
        t2.setBounds(330,348,170,22);
        t2.setFont(new Font("Railway",Font.BOLD,15));
        
        b1.setBounds(380,447,130,27);
        b1.addActionListener(this);
        b2.setBounds(380,478,130,27);
        b2.addActionListener(this);
        
        frm.add(img);
        img.add(l1);
        img.add(l2);
        img.add(l3);
        img.add(t1);
        img.add(t2);
        img.add(b1);
        img.add(b2);
        
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
    
    public void getData(){
        npin=t1.getText();
        rpin=t2.getText();
    }
//    Updating new PIN in Database

    public void updatePin(){
        try{
            st=con.createStatement();
            String query1="update bank set cardpin='"+npin+"' where cardpin='"+pinnumber+"' ";
            String query2="update signupthree set cardpin='"+npin+"' where cardpin='"+pinnumber+"' ";
            String query3="update login set cardpin='"+npin+"' where cardpin='"+pinnumber+"' ";
            st.executeUpdate(query1);
            st.executeUpdate(query2);
            st.executeUpdate(query3);
            
            JOptionPane.showMessageDialog(null,"PIN Changed Successfully");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
//    Adding Functionalities to Button
    public void actionPerformed(ActionEvent ae){
        getData();
        if(ae.getSource()==b1){
            if(npin.equals("")){
                JOptionPane.showMessageDialog(null,"Please Enter New PIN");
                return;
            }
            if(rpin.equals("")){
                JOptionPane.showMessageDialog(null,"Please Re-Enter New PIN");
                return;
            }
            if(!npin.equals(rpin)){
                JOptionPane.showMessageDialog(null,"Entered PIN does not match");
                return;
            }
            
            getConn();
            updatePin();
            frm.setVisible(false);
            Transactions obj= new Transactions(npin);
            obj.setUpTransactions();
        }
        
        else if(ae.getSource()==b2){
            frm.setVisible(false);
            Transactions obj= new Transactions(pinnumber);
            obj.setUpTransactions();
        }
    }
}
