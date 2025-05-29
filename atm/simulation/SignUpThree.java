package atm.simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class SignUpThree extends JFrame implements ActionListener{
    JFrame frm;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
    JRadioButton rb1,rb2,rb3,rb4;
    ButtonGroup bg;
    JCheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7;
    JButton b1,b2,b3;
    String accType="",cardNum="",cPin="",facility="",reg="",TandC="";
    Random ran;
    
    Connection con;
    Statement st;
    
    SignUpThree( String reg){
        this.reg=reg;
        
        frm= new JFrame("SignUp Page3");
        l1=new JLabel("Page 3: Account Details");
        l2=new JLabel("Account Type:");
        l3=new JLabel("Card Number:");
        l4=new JLabel("XXXX-XXXX-XXXX-5228");
        l5=new JLabel("(Your 16 Digit Card Number)");
        l6=new JLabel("PIN:");
        l7=new JLabel("XXXX");
        l8=new JLabel("(Your 4 Digit Password)");
        l9=new JLabel("Services Required:");
        
        rb1=new JRadioButton("Saving Account");
        rb2=new JRadioButton("Fixed Deposit Account");
        rb3=new JRadioButton("Current Account");
        rb4=new JRadioButton("Recurring Deposit Account");
        
        bg=new ButtonGroup();
        
        cb1=new JCheckBox("ATM Card");
        cb2=new JCheckBox("Internet Banking");
        cb3=new JCheckBox("Mobile Banking");
        cb4=new JCheckBox("Email & SMS Alerts");
        cb5=new JCheckBox("Cheque Book");
        cb6=new JCheckBox("E-Statement");
        cb7=new JCheckBox("I hereby declares that the above entered details are correct to the best of my knowledge");
        
        b1=new JButton("Submit");
        b2=new JButton("Cancel");
        b3=new JButton("Previous");
        
        ran=new Random();
        
    }
    
//  Setting Form Layout
    public void setUpSignUpThree(){
        frm.setSize(650,650);
        frm.setLayout(null);
        frm.setLocation(500,100);
        frm.getContentPane().setBackground(Color.white);
        
        l1.setBounds(205,30,400,30);
        l1.setFont(new Font("Raleway",Font.BOLD,19));
        
        l2.setBounds(80,90,200,30);
        l2.setFont(new Font("Raleway",Font.BOLD,17));
        rb1.setBounds(110,120,180,30);
        rb2.setBounds(290,120,180,30);
        rb3.setBounds(110,150,180,30);
        rb4.setBounds(290,150,180,30);
        rb1.setBackground(Color.white);
        rb2.setBackground(Color.white);
        rb3.setBackground(Color.white);
        rb4.setBackground(Color.white);
        bg.add(rb1);
        bg.add(rb2);
        bg.add(rb3);
        bg.add(rb4);
        
        l3.setBounds(80,190,200,30);
        l3.setFont(new Font("Raleway",Font.BOLD,17));
        l4.setBounds(290,190,250,30);
        l4.setFont(new Font("Raleway",Font.BOLD,17));
        l5.setBounds(80,208,200,30);
        l5.setFont(new Font("Raleway",Font.BOLD,10));
        
        l6.setBounds(80,245,200,30);
        l6.setFont(new Font("Raleway",Font.BOLD,17));
        l7.setBounds(290,245,250,30);
        l7.setFont(new Font("Raleway",Font.BOLD,17));
        l8.setBounds(80,263,200,30);
        l8.setFont(new Font("Raleway",Font.BOLD,10));
        
        l9.setBounds(80,305,200,30);
        l9.setFont(new Font("Raleway",Font.BOLD,17));
        cb1.setBounds(110,335,180,30);
        cb2.setBounds(290,335,180,30);
        cb3.setBounds(110,365,180,30);
        cb4.setBounds(290,365,180,30);
        cb5.setBounds(110,395,180,30);
        cb6.setBounds(290,395,180,30);
        cb1.setBackground(Color.white);
        cb2.setBackground(Color.white);
        cb3.setBackground(Color.white);
        cb4.setBackground(Color.white);
        cb5.setBackground(Color.white);
        cb6.setBackground(Color.white);
        cb1.setFont(new Font("Raleway",Font.BOLD,14));
        cb2.setFont(new Font("Raleway",Font.BOLD,14));
        cb3.setFont(new Font("Raleway",Font.BOLD,14));
        cb4.setFont(new Font("Raleway",Font.BOLD,14));
        cb5.setFont(new Font("Raleway",Font.BOLD,14));
        cb6.setFont(new Font("Raleway",Font.BOLD,14));
        
        cb7.setBounds(50,450,600,30);
        cb7.setBackground(Color.white);
        cb7.setFont(new Font("Raleway",Font.BOLD,12));
        
        b3.setBounds(190,490,100,34);
        b3.addActionListener(this);
        b1.setBounds(330,490,100,34);
        b1.addActionListener(this);
        b2.setBounds(260,535,100,34);
        b2.addActionListener(this);
        
        frm.add(l1);
        frm.add(l2);
        frm.add(l3);
        frm.add(l4);
        frm.add(l5);
        frm.add(l6);
        frm.add(l7);
        frm.add(l8);
        frm.add(l9);
        
        frm.add(rb1);
        frm.add(rb2);
        frm.add(rb3);
        frm.add(rb4);
        
        frm.add(cb1);
        frm.add(cb2);
        frm.add(cb3);
        frm.add(cb4);
        frm.add(cb5);
        frm.add(cb6);
        frm.add(cb7);
        
        frm.add(b1);
        frm.add(b2);
        frm.add(b3);
        frm.setVisible(true);
    }
    
    
//    Taking Values from Form
    public void getData(){
        cardNum="" +Math.abs((ran.nextLong()%90000000L)+5040936000000000L);
        cPin="" +Math.abs((ran.nextLong()%9000L)+1000L);
        
        if(rb1.isSelected()){
            accType="Saving Account";
        }
        else if(rb2.isSelected()){
            accType="Fixed Deposit Account";
        }
        else if(rb3.isSelected()){
            accType="Current Account";
        }
        else if(rb4.isSelected()){
            accType="Recurring Deposit Account";
        }
        
        
        if(cb1.isSelected()){
            facility= facility+" ATM Card";
        }
        if(cb2.isSelected()){
            facility= facility+" Internet Banking";
        }
        if(cb3.isSelected()){
            facility= facility+" Mobile Banking";
        }
        if(cb4.isSelected()){
            facility= facility+" Email & SMS Alerts";
        }
        if(cb5.isSelected()){
            facility= facility+" Cheque Book";
        }
        if(cb6.isSelected()){
            facility= facility+" E-Statement";
        }
        
        if(cb7.isSelected()){
            TandC="yes";
        }
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
    
//   Putting values into Database
    public void putData(){
        try{
            String query="insert into signupthree values('"+reg+"','"+accType+"','"+cardNum+"','"+cPin+"','"+facility+"')";
            String query2="insert into login values('"+reg+"','"+cardNum+"','"+cPin+"',)";
            st=con.createStatement();
            st.executeUpdate(query);
            st.executeUpdate(query2);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    
//    Deleting values from Table
    public void delData(){
        try{
            st=con.createStatement();
            String str="delete from signuptwo where formno="+reg;
            String mode="SET SQL_SAFE_UPDATES = 0";
            st.executeUpdate(mode);
            st.executeUpdate(str);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    
//    Deleting values from all Tables
    public void delAllData(){
        try{
            st=con.createStatement();
            String str1="delete from signup where formno="+reg;
            String str2="delete from signuptwo where formno="+reg;
            String mode="SET SQL_SAFE_UPDATES = 0";
            st.executeUpdate(mode);
            st.executeUpdate(str1);
            st.executeUpdate(str2);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    
//    Adding functionality to buttons
    public void actionPerformed(ActionEvent ae){
        getData();
        if(ae.getSource()==b1){
            if(accType.equals("")||cardNum.equals("")||cPin.equals("")||facility.equals("")){
                JOptionPane.showMessageDialog(null,"Please Enter All Details Correctly");
            }
            else if(TandC.equals("")){
                JOptionPane.showMessageDialog(null,"Please mark Terms and Condition");
            }
            else{
                getConn();
                putData();
                JOptionPane.showMessageDialog(null,"Card Number: "+cardNum+"\nPin: "+cPin);
                frm.setVisible(false);
                Deposit obj=new Deposit(cPin);
                obj.setUpDeposit();
            }
        }
        else if(ae.getSource()==b2){
            getConn();
            delAllData();
            frm.setVisible(false);
            Login obj=new Login();
            obj.setUpLogin();
            
        }
        
        else if(ae.getSource()==b3){
            getConn();
            delData();
            frm.setVisible(false);
            SignUpTwo obj=new SignUpTwo(reg);
            obj.setUpSignUpTwo();
        }
        
    }
    
    
}
