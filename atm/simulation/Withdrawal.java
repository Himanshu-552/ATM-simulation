package atm.simulation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class Withdrawal extends JFrame implements ActionListener{
    Connection con;
    Statement st;
    ResultSet rec;

    JFrame frm;
    ImageIcon ic1,ic2;
    Image i1;
    JLabel img,l1;
    JTextField t1;
    JButton b1,b2;
    String pinnumber,amt;
    
//    Allocating memory to componenets
    Withdrawal(String pinnumber){
        this.pinnumber = pinnumber;
        frm=new JFrame();
        ic1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        i1=ic1.getImage().getScaledInstance(900,830,Image.SCALE_DEFAULT);
        ic2= new ImageIcon(i1);
        img=new JLabel(ic2);
        l1=new JLabel("Enter the amount you want to withdraw");
        t1=new JTextField();
        
        b1=new JButton("Withdraw");
        b2=new JButton("Back"); 
        
        
    }
    
//    Setting Page Layout
    public void setUpWithdrawal(){
        frm.setSize(900,830);
        frm.setLocation(300,0);
        frm.setLayout(null);
        img.setBounds(0,0,900,830);
        
        l1.setBounds(190,280,400,35);
        l1.setForeground(Color.white);
        l1.setFont(new Font("System",Font.BOLD,16));
        
        t1.setBounds(190,330,290,25);
        t1.setFont(new Font("Railway",Font.BOLD,18));
        
        b1.setBounds(380,447,130,27);
        b2.setBounds(380,478,130,27);
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        frm.add(img);
        img.add(l1);
        img.add(t1);
        img.add(b1);
        img.add(b2);
        frm.setUndecorated(true);
        frm.setVisible(true);
    }
    
//    Establishing connection
    public void getConn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms","root","Root@123");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
//    Updating values in table
    public void putData(){
        amt=t1.getText();
        Date date= new Date();
        if(amt.equals("")){
            JOptionPane.showMessageDialog(null,"Please enter the amount you want to Withdraw");
        }
        else{
            try{
                st=con.createStatement();
                int balance=0;
                rec=st.executeQuery("select * from bank where cardpin= '"+pinnumber+"'");
                while(rec.next()){
                    if(rec.getString("type").equals("Deposit")){
                        balance= balance + Integer.parseInt(rec.getString("amount"));
                    }
                    else {
                        balance =balance - Integer.parseInt(rec.getString("amount"));
                    }
                }
                
                if(balance< Integer.parseInt(amt)){
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }
                else{
                    String query="insert into bank values('"+pinnumber+"','"+date+"','Withdraw','"+amt+"')";
                    st.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Rs "+amt+" Withdraw Successfully");
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
    
//    Setting Buttons Functionality
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource()==b1){
            getConn();
            putData();
            frm.setVisible(false);
            Transactions obj=new Transactions(pinnumber);
            obj.setUpTransactions();
        }
        else if(ae.getSource()==b2){
            frm.setVisible(false);
            Transactions obj=new Transactions(pinnumber);
            obj.setUpTransactions();
        }
    }
}
