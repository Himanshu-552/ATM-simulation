package atm.simulation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JFrame frm;
    Image i1;
    JLabel l1,l2,l3,l4,l5;
    JTextField t1,t2;
    JButton b1,b2,b3;
    ImageIcon ic1,ic2;
    Connection con;
    Statement st;
    ResultSet rec;
    
    public Login(){
        frm= new JFrame("Login Page");
        ic1= new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        i1=ic1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ic2=new ImageIcon(i1);
        l1= new JLabel(ic2);
        l2=new JLabel("Welcome to ATM");
        l3=new JLabel(ic2);
        l4=new JLabel("Card Number:");
        l5=new JLabel("Pin:");
        t1=new JTextField();
        t2=new JTextField();
        b1= new JButton("Sign In");
        b2= new JButton("Sign Up");
        b3= new JButton("Clear");
    }
    public void setUpLogin(){
        frm.setLayout(null);
        frm.setSize(800,480);
        frm.setLocation(400,200);
        frm.getContentPane().setBackground(Color.white);
        l1.setBounds(150,20,100,80);
        l3.setBounds(520,20,100,80);
        
        l2.setBounds(260,20,250,80);
        l2.setFont(new Font("Osward",Font.BOLD,30));
        
        l4.setBounds(180,150,250,40);
        l4.setFont(new Font("Raleway",Font.BOLD,20));
        t1.setBounds(400,150,250,35);
        t1.setFont(new Font("Raleway",Font.PLAIN,15));
        
        l5.setBounds(180,200,250,40);
        l5.setFont(new Font("Raleway",Font.BOLD,20));
        t2.setBounds(400,205,250,35);
        t2.setFont(new Font("Raleway",Font.PLAIN,15));
        
        b1.setBounds(280,270,100,40);
        b2.setBounds(410,270,100,40);
        b3.setBounds(350,330,100,40);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        frm.add(l1);
        frm.add(l2);
        frm.add(l3);
        frm.add(l4);
        frm.add(l5);
        frm.add(t1);
        frm.add(t2);
        frm.add(b1);
        frm.add(b2);
        frm.add(b3);
        
        frm.setVisible(true);
        
    }
    
//    Establishing Connection
    public void getCon(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","Root@123");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==b1){
            getCon();
            String a1=t1.getText();
            String a2=t2.getText();
            String str="select * from login where cardnum='"+a1+"' && cardpin='"+a2+"'";
            try{
                st=con.createStatement();
                rec=st.executeQuery(str);
                if(rec.next()){
                    frm.setVisible(false);
                    Transactions obj=new Transactions(a2);
                    obj.setUpTransactions();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Incorrect Card Number or PIN");
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
            
        }
        else if(ae.getSource()==b2){
            frm.setVisible(false);
            SignUpOne obj=new SignUpOne();
            obj.setUpSignUpOne();
        }
        else if(ae.getSource()==b3){
            t1.setText("");
            t2.setText("");
        }
    }
}
