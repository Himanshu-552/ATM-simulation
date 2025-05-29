package atm.simulation;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.toedter.calendar.*;

public class SignUpOne extends JFrame implements ActionListener {
    Connection con;
    Statement st;
    long num;
    String reg="",name="",fname="",dob="",gen="",mail="",marital="",add="",city="",state="",pinc="";
    JFrame frm; 
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    JButton b1,b2;
    JRadioButton rb1,rb2,rb3,rb4,rb5;
    ButtonGroup bg1,bg2;
    JDateChooser dc;
    
    
    SignUpOne(){
        Random ran=new Random();
        num=Math.abs(ran.nextLong()%9000L + 1000L);
        frm=new JFrame("SignUp Page1");
        l1=new JLabel("Application Form No."+num);
        l2=new JLabel("Page 1: Personal Details");
        l3=new JLabel("Name:");
        l4=new JLabel("Father's Name:");
        l5=new JLabel("Date of Birth:");
        l6=new JLabel("Gender:");
        l7=new JLabel("Email Address:");
        l8=new JLabel("Marital Status:");
        l9=new JLabel("Address:");
        l10=new JLabel("City:");
        l11=new JLabel("State:");
        l12=new JLabel("PinCode:");
        t1=new JTextField();
        t2=new JTextField();
        t3=new JTextField();
        t4=new JTextField();
        t5=new JTextField();
        t6=new JTextField();
        t7=new JTextField();
        dc= new JDateChooser(); 
        
        b1=new JButton("Next");
        b2=new JButton("Previous");
        
        rb1=new JRadioButton("Male");
        rb2=new JRadioButton("Female");
        rb3=new JRadioButton("Others");
        rb4=new JRadioButton("Single");
        rb5=new JRadioButton("Married");
        
        bg1=new ButtonGroup();
        bg2=new ButtonGroup();
        
    }
    
// Setting up Form Layout
    public void setUpSignUpOne(){
        frm.setLayout(null);
        frm.setSize(650,650);
        frm.setLocation(500,100);
        frm.getContentPane().setBackground(Color.white);
        l1.setBounds(130,10,700,40);
        l1.setFont(new Font("Raleway",Font.BOLD,30));
        
        l2.setBounds(205,55,400,30);
        l2.setFont(new Font("Raleway",Font.BOLD,19));

        l3.setBounds(80,110,150,30);
        t1.setBounds(240,110,250,25);
        l3.setFont(new Font("Raleway",Font.BOLD,17));

        l4.setBounds(80,150,150,30);
        t2.setBounds(240,150,250,25);
        l4.setFont(new Font("Raleway",Font.BOLD,17));

        l5.setBounds(80,190,150,30);
        l5.setFont(new Font("Raleway",Font.BOLD,17));
        
        dc.setBounds(240,190,250,25);

        l6.setBounds(80,230,150,30);
        l6.setFont(new Font("Raleway",Font.BOLD,17));
        rb1.setBackground(Color.white);
        rb2.setBackground(Color.white);
        rb3.setBackground(Color.white);
        rb1.setBounds(240,230,80,25);
        rb2.setBounds(330,230,80,25);
        rb3.setBounds(420,230,80,25);
        bg1.add(rb1);
        bg1.add(rb2);
        bg1.add(rb3);

        l7.setBounds(80,270,150,30);
        t3.setBounds(240,270,250,25);
        l7.setFont(new Font("Raleway",Font.BOLD,17));

        l8.setBounds(80,310,150,30);
        l8.setFont(new Font("Raleway",Font.BOLD,17));
        rb4.setBackground(Color.white);
        rb5.setBackground(Color.white);
        rb4.setBounds(240,310,100,25);
        rb5.setBounds(350,310,100,25);
        bg2.add(rb4);
        bg2.add(rb5);

        l9.setBounds(80,350,150,30);
        t4.setBounds(240,350,250,25);
        l9.setFont(new Font("Raleway",Font.BOLD,17));

        l10.setBounds(80,390,150,30);
        t5.setBounds(240,390,250,25);
        l10.setFont(new Font("Raleway",Font.BOLD,17));

        l11.setBounds(80,430,150,30);
        t6.setBounds(240,430,250,25);
        l11.setFont(new Font("Raleway",Font.BOLD,17));

        l12.setBounds(80,470,150,30);
        t7.setBounds(240,470,250,25);
        l12.setFont(new Font("Raleway",Font.BOLD,17));
        
        b2.setBounds(200,520,100,30);
        b2.addActionListener(this);
        b1.setBounds(330,520,100,30);
        b1.addActionListener(this);

        frm.add(l1);
        frm.add(l2);
        frm.add(l3);
        frm.add(l4);
        frm.add(l5);
        frm.add(l6);
        frm.add(l7);
        frm.add(l8);
        frm.add(l9);
        frm.add(l10);
        frm.add(l11);
        frm.add(l12);
        frm.add(t1);
        frm.add(t2);
        frm.add(t3);
        frm.add(t4);
        frm.add(t5);
        frm.add(t6);
        frm.add(t7);
        frm.add(b1);
        frm.add(b2);
        frm.add(dc);
        
        frm.add(rb1);
        frm.add(rb2);
        frm.add(rb3);
        frm.add(rb4);
        frm.add(rb5);
        frm.setVisible(true);
        
    }
    
//  Getting values from Form
    public void getValue(){
        reg=""+num;
        name=t1.getText();
        fname=t2.getText();
        mail=t3.getText();
        add=t4.getText();
        city=t5.getText();
        state=t6.getText();
        pinc=t7.getText();
        dob=((JTextField)dc.getDateEditor().getUiComponent()).getText();
        gen=null;
        if(rb1.isSelected()){
            gen="Male";
        }
        else if(rb2.isSelected()){
            gen="Female";
        }
        else if(rb3.isSelected()){
            gen="Others";
        }
        marital=null;
        if(rb4.isSelected()){
            marital="Single";
        }
        if(rb5.isSelected()){
            marital="Married";
        }
    }
    
//    Establishing connection
    public void getConn(){
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","Root@123");
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    
// Putting data into Database 
    public void putData(){
        try{
        String query="insert into signup values('"+reg+"','"+name+"','"+fname+"','"+dob+"','"+gen+"','"+mail+"','"+marital+"','"+add+"','"+city+"','"+state+"','"+pinc+"')";
        st=con.createStatement();
        st.executeUpdate(query);
        }
        catch(Exception ex){
            System.out.println(ex);
          
        }
    }
    
    
//  Using Action Listener    
    public void actionPerformed(ActionEvent ae){
        getValue();
        if(ae.getSource()==b1){
            if(name.equals("")||fname.equals("")||mail.equals("")||add.equals("")||city.equals("")||state.equals("")||pinc.equals("")||dob.equals("")||gen.equals("")||marital.equals("")){
                JOptionPane.showMessageDialog(null,"Please Enter All Details");
            }
            else{
                getConn();
                putData();
                frm.setVisible(false);
                SignUpTwo obj=new SignUpTwo(reg);
                obj.setUpSignUpTwo();
            }
         
        }
        else if(ae.getSource()==b2){
            frm.setVisible(false);
            Login obj=new Login();
            obj.setUpLogin();
        }
    }
}
