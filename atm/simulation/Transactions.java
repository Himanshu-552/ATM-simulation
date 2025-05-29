package atm.simulation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener{
    JFrame frm;
    ImageIcon ic1,ic2;
    Image i1;
    JLabel bgimage,l1;
    JButton b1,b2,b3,b4,b5,b6,b7;
    String pinnumber;
//    Allocating memory to elements
    Transactions(String pinnumber){
        this.pinnumber=pinnumber;
        frm=new JFrame("");
        ic1= new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        i1=ic1.getImage().getScaledInstance(900,830,Image.SCALE_DEFAULT);
        ic2=new ImageIcon(i1);
        bgimage= new JLabel(ic2);
        
        l1=new JLabel("Please select your transaction");
        
        b1=new JButton("Deposit");
        b2=new JButton("Cash Withdrawal");
        b3=new JButton("Fast Cash");
        b4=new JButton("Mini Statement");
        b5=new JButton("Pin Change");
        b6=new JButton("Balance Enquiry");
        b7=new JButton("Exit");
    }
    
//    Setting up Page Layout
    public void setUpTransactions(){
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
        
//  Setting functionalities of buttons
    public void actionPerformed(ActionEvent ae){
            
         if(ae.getSource()==b7){
             System.exit(0);
         }
         else if(ae.getSource()==b1){
             frm.setVisible(false);
             Deposit obj=new Deposit(pinnumber);
             obj.setUpDeposit();
         }
         else if(ae.getSource()==b2){
             frm.setVisible(false);
             Withdrawal obj=new Withdrawal(pinnumber);
             obj.setUpWithdrawal();
         }
         else if(ae.getSource()==b3){
             frm.setVisible(false);
             FastCash obj=new FastCash(pinnumber);
             obj.setUpFastCash();
         }
         else if(ae.getSource()==b4){
             MiniStatement obj=new MiniStatement(pinnumber);
             obj.setUpMiniStatement();
         }
         else if(ae.getSource()==b5){
             frm.setVisible(false);
             PinChange obj=new PinChange(pinnumber);
             obj.setUpPinChange();
         }
         else if(ae.getSource()==b6){
             frm.setVisible(false);
             BalanceEnquiry obj=new BalanceEnquiry(pinnumber);
             obj.setUpBalanceEnquiry();
         }
    }
}
