package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Assign{//AGGREGATION
	
	String s1,s2,s3,s4,s5,e6,s7;
	int s6;
	
	public Assign(String S1, String S2, String S3, String S4, String S5, String S6, String S7){  
	    
		this.s1=S1;
		this.s2=S2;
		this.s3=S3;
		this.s4=S4;
		this.s5=S5;
		this.e6=S6;
		this.s6=Integer.valueOf(e6);
		this.s7=S7;
	}  
	
	
}

class sign_in{
    JFrame f=null;
    JLabel l1= null;
	JTextField t1= null;
	JLabel l2= null;
	JTextField t2= null;
    JLabel l3= null;
	JLabel l4= null;
	JTextField t4= null;
    JLabel l5= null;
	JTextField t5= null;
    JLabel l6= null;
	JTextField t6= null;
    JLabel l7=null;
    JPasswordField p1=null;
    JButton b1= null;
    PreparedStatement st4;
    JComboBox cb;
    Assign assign1;
    void Sign_up(){
        f=new JFrame("New User Registration");
        String country[]={"Albania","Afghanistan","Algeria","Andorra","Angola","Antigua and Barbuda","Argentina","Armenia","Australia","Austria","Azerbaijan"};
        l1= new JLabel("NAME : ");
	    t1= new JTextField();
	    l2= new JLabel("CONTACT : ");
	    t2= new JTextField();
        l3= new JLabel("COUNTRY : ");
        l4= new JLabel("CITY : ");
        t4= new JTextField();
        cb =new JComboBox(country);
        l5=new JLabel("CUSTOMER ID: ");
        t5=new JTextField();
        l6=new JLabel("NO OF PASSENGERS: ");
        t6=new JTextField();
        l7=new JLabel("PASSWORD: ");
        p1=new JPasswordField();
        b1=new JButton("Proceed");

        l1.setBounds(30,80,90,20);
        t1.setBounds(180,80,190,20);
        l2.setBounds(30,120,90,20);
        t2.setBounds(180,120,190,20);
        l3.setBounds(30,160,190,20);
        cb.setBounds(180,160,190,20);
        l4.setBounds(30,200,190,20);
        t4.setBounds(180,200,190,20);
        l5.setBounds(30,240,190,20);
        t5.setBounds(180,240,190,20);
        l6.setBounds(30,280,190,20);
        t6.setBounds(180,280,190,20);
        l7.setBounds(30,320,190,20);
        p1.setBounds(180,320,190,20);
        b1.setBounds(110,400,190,20);

        f.add(cb);
        f.add(l1);f.add(l2);f.add(l3);f.add(l4);f.add(l5);f.add(l6);f.add(l7);
        f.add(t1);f.add(t2);f.add(t4);f.add(t5);f.add(t6);f.add(p1);
        f.add(b1);


        f.setLayout(null);
        f.setVisible(true);
        f.setSize(400,500);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       

        b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t1.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Name is Empty");
					
				}
				
				else if(t2.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Contact No is Empty");
				
				}
			
				else if(t4.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "City is Empty");
					
				}
				else if(t5.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Customer ID is Empty");
					
				}
				else if(t6.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No. of passenegers is Empty");
					
				}
                else if(p1.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Password is Empty");
					
				}
				
				else {
					
					assign1=new Assign(t1.getText(),t2.getText(),(String)cb.getSelectedItem(),t4.getText(),t5.getText(),t6.getText(),p1.getText());  	
					
					try {
						Connection c=(Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/Pro","postgres","rogue");
						st4= c.prepareStatement("insert into customer values(?,?,?,?,?,?,?)");
						st4.setString(2,assign1.s1);
						st4.setString(5,assign1.s2);
						st4.setString(4,assign1.s3);
						st4.setString(3,assign1.s4);
						st4.setString(1,assign1.s5);
						st4.setInt(6,assign1.s6);		 			
						st4.setString(7,assign1.s7);
						st4.executeUpdate();
						
					}
					catch(SQLException sqlException) {
						
						sqlException.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "you have successfully registered");
                    f.setVisible(false);
					new Login_1();
				}
			}
		});

    }
}

public class Signup{
	public static void main(String[] args) {
		sign_in e=new sign_in();
		e.Sign_up();
	}
	
}