package atm.simulation;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class SignUpTwo extends JFrame implements ActionListener{
    Connection con;
    Statement st;
    String  reg="",rel="",cat="",income="",education="",occupation="",pan="",aadhar="",scitizen="",existacc="";
    JFrame frm; 
    JLabel l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
    JTextField t1,t2;
    JComboBox cb1,cb2,cb3,cb4,cb5;
    JButton b1,b2,b3;
    JRadioButton rb1,rb2,rb3,rb4;
    ButtonGroup bg1,bg2;
    SignUpTwo(String reg){
        this.reg=reg;
        frm=new JFrame("SignUp Page2");
//        l1=new JLabel("Application Form No.");
        l2=new JLabel("Page 2: Personal Details");
        l3=new JLabel("Religion:");
        l4=new JLabel("Category:");
        l5=new JLabel("Income:");
        l6=new JLabel("Educational:");
        l7=new JLabel("Qualification:");
        l8=new JLabel("Occupation:");
        l9=new JLabel("PAN Number:");
        l10=new JLabel("Aadhar Number:");
        l11=new JLabel("Senior Citizen:");
        l12=new JLabel("Existing Account:");
        t1=new JTextField();
        t2=new JTextField();
        
        String valReligion[]={"Hindu","Muslim","Sikh","Christian","Others"};
        cb1=new JComboBox(valReligion);
        String valCategory[]={"General","OBC","SC","ST","Others"};
        cb2=new JComboBox(valCategory);
        String valIncome[]={"Null","<1,50,000","1,50,000-3,50,000","3,50,000-5,00,000","5,00,000-8,00,000",">8,00,000"};
        cb3=new JComboBox(valIncome);
        String valEducation[]={"Non-Graduate","Graduate","Post-Graduate","Doctrate","Others"};
        cb4=new JComboBox(valEducation);
        String valOccupation[]={"Salaried","Self-Employed","Business","Student","Retired","Others"};
        cb5=new JComboBox(valOccupation);
        
        b1=new JButton("Next");
        b2=new JButton("Previous"); 
        b3=new JButton("Cancel"); 
        
        rb1=new JRadioButton("Yes");
        rb2=new JRadioButton("No");
        rb3=new JRadioButton("Yes");
        rb4=new JRadioButton("No");
        
        bg1=new ButtonGroup();
        bg2=new ButtonGroup();
        
    }
    
    // Setting up Form Layout
    public void setUpSignUpTwo(){
        frm.setLayout(null);
        frm.setSize(650,650);
        frm.setLocation(500,100);
        frm.getContentPane().setBackground(Color.white);
//        l1.setBounds(130,10,700,40);
//        l1.setFont(new Font("Raleway",Font.BOLD,30));
        
        l2.setBounds(205,55,400,30);
        l2.setFont(new Font("Raleway",Font.BOLD,19));

        l3.setBounds(80,110,150,30);
        l3.setFont(new Font("Raleway",Font.BOLD,17));
        cb1.setBounds(240,115,250,25);
        cb1.setBackground(Color.white);

        l4.setBounds(80,150,150,30);
        l4.setFont(new Font("Raleway",Font.BOLD,17));
        cb2.setBounds(240,155,250,25);
        cb2.setBackground(Color.white);

        l5.setBounds(80,190,150,30);
        l5.setFont(new Font("Raleway",Font.BOLD,17));
        cb3.setBounds(240,195,250,25);
        cb3.setBackground(Color.white);

        l6.setBounds(80,240,150,30);
        l6.setFont(new Font("Raleway",Font.BOLD,17));
        l7.setBounds(80,260,150,30);
        l7.setFont(new Font("Raleway",Font.BOLD,17));
        cb4.setBackground(Color.white);
        cb4.setBounds(240,250,250,25);


        l8.setBounds(80,310,150,30);
        l8.setFont(new Font("Raleway",Font.BOLD,17));
        cb5.setBackground(Color.white);
        cb5.setBounds(240,315,250,25);
       

        l9.setBounds(80,350,150,30);
        t1.setBounds(240,355,250,25);
        l9.setFont(new Font("Raleway",Font.BOLD,17));

        l10.setBounds(80,390,150,30);
        t2.setBounds(240,395,250,25);
        l10.setFont(new Font("Raleway",Font.BOLD,17));

        l11.setBounds(80,430,150,30);
        l11.setFont(new Font("Raleway",Font.BOLD,17));
        rb1.setBounds(240,435,100,25);
        rb2.setBounds(350,435,100,25);
        rb1.setBackground(Color.white);
        rb2.setBackground(Color.white);
        bg1.add(rb1);
        bg1.add(rb2);

        l12.setBounds(80,470,150,30);
        l12.setFont(new Font("Raleway",Font.BOLD,17));
        rb3.setBounds(240,475,100,25);
        rb4.setBounds(350,475,250,25);
        rb3.setBackground(Color.white);
        rb4.setBackground(Color.white);
        bg2.add(rb3);
        bg2.add(rb4);
        
        b2.setBounds(200,510,100,30);
        b2.addActionListener(this);
        b1.setBounds(330,510,100,30);
        b1.addActionListener(this);
        b3.setBounds(260,550,100,30);
        b3.addActionListener(this);

//        frm.add(l1);
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
        frm.add(cb1);
        frm.add(cb2);
        frm.add(cb3);
        frm.add(cb4);
        frm.add(cb5);

        frm.add(b1);
        frm.add(b2);
        frm.add(b3);
        
        frm.add(rb1);
        frm.add(rb2);
        frm.add(rb3);
        frm.add(rb4);
        frm.setVisible(true);
        
    }
    
    
//    Fetching Values form Form
    public void getValue(){
        rel=(String) cb1.getSelectedItem();
        cat=(String) cb2.getSelectedItem();
        income=(String) cb3.getSelectedItem();
        education=(String) cb4.getSelectedItem();
        occupation=(String) cb5.getSelectedItem();
        pan=t1.getText();
        aadhar=t2.getText();
        scitizen=null;
        if(rb1.isSelected()){
            scitizen="Yes";
        }else if(rb2.isSelected()){
            scitizen="No";
        }
        existacc=null;
        if(rb3.isSelected()){
            existacc="Yes";
        }else if(rb4.isSelected()){
            existacc="No";
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
    
    
//    Inserting Values into Database
    public void putData(){
        try{
            String query="insert into signuptwo values('"+reg+"','"+rel+"','"+cat+"','"+income+"','"+education+"','"+occupation+"','"+pan+"','"+aadhar+"','"+scitizen+"','"+existacc+"')";
            st=con.createStatement();
            st.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
//    Deleting values from table
    public void delData(){
        try{
            String str="SET SQL_SAFE_UPDATES = 0";
            String query="delete from signup where formno="+reg;
            st=con.createStatement();
            st.executeUpdate(str);
            st.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    public void actionPerformed(ActionEvent ae){
        getValue();
        if(ae.getSource()==b1){
            if(rel.equals("")||cat.equals("")||income.equals("")||education.equals("")||occupation.equals("")||pan.equals("")||aadhar.equals("")||scitizen.equals("")||existacc.equals("")){
                JOptionPane.showMessageDialog(null,"Please Enter All Details Correctly");
            }
            else{
                getConn();
                putData();
                frm.setVisible(false);
                SignUpThree obj=new SignUpThree(reg);
                obj.setUpSignUpThree();
            }
        }
        else if(ae.getSource()==b2){
            getConn();
            delData();
            frm.setVisible(false);
            SignUpOne obj=new SignUpOne();
            obj.setUpSignUpOne();
        }
        
        else if(ae.getSource()==b3){
            getConn();
            delData();
            frm.setVisible(false);
            Login obj=new Login();
            obj.setUpLogin();
        }
    }
    
}
