package Project;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
class Login_1 extends Login{	
	JFrame f=null;
	JLabel l1= null;
	JTextField t1= null;
	JLabel l2= null;
	JPasswordField t2= null;
	JButton b1= null;
	JLabel l3= null;
	JButton b2= null;
	JPanel imagePanel =null;
	JPanel p1 =null;
	JPanel p2 =null;
	Login_1(){
		
	f=new JFrame("LOGIN/SIGN-UP");
	
	JPanel jp1 = new JPanel();
	jp1.setBounds(30,20,150,150);
    ImageIcon ic = new ImageIcon(new ImageIcon("/Users/navneeth/eclipse-workspace/op/src/Project/hi.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
	JLabel l=new JLabel(ic);
	jp1.add(l);
	f.add(jp1);
	
	l1= new JLabel("USER ID : ");
	t1= new JTextField();
	l2= new JLabel("PASSWORD : ");
	t2= new JPasswordField();
	b1= new JButton("LOGIN");
	l3= new JLabel("If not registered ");
	b2= new JButton("SIGN-UP");
	l1.setBounds(50,180,90,20);
	f.add(l1);
	t1.setBounds(130,180,190,20);
	f.add(t1);
	l2.setBounds(50,220,90,20);
	f.add(l2);
	t2.setBounds(130,220,190,20);
	f.add(t2);
	b1.setBounds(155,260,100,30);
	f.add(b1);
	l3.setBounds(90,320,200,20);
	f.add(l3);
	b2.setBounds(200,315,100,30);
	f.add(b2);
	f.setSize(400,400);
	f.add(jp1);	
	f.setVisible(true);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	b1.addActionListener(new ActionListener()
	{	
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(t1.getText().isEmpty() ) {
				JOptionPane.showMessageDialog(null, "Customer ID is Empty");
			}
			else if (t2.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Password is Empty");
			}
			else {
					username=t1.getText();
					password=t2.getText();
					customer_contact=null;
					customer_name=null;
					city=null;
				
				try
				{
					Connection c=(Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/Pro","postgres","rogue");
					PreparedStatement statement= c.prepareStatement("select customer_id,customer_password from customer where customer_id=? and customer_password=?");
					PreparedStatement s2= c.prepareStatement("select customer_name,customer_contact,customer_city from customer where customer_id=? and customer_password=?");
					s2.setString(1,username);
					s2.setString(2,password);
					ResultSet rs2=s2.executeQuery();
					statement.setString(1,username);
					statement.setString(2,password);
					ResultSet resultset= statement.executeQuery();
					if(resultset.next())
					{
						rs2.next();
						customer_name=rs2.getString("customer_name");
						customer_contact=rs2.getString("customer_contact");
						city=rs2.getString("customer_city");
						PreparedStatement s3= c.prepareStatement("insert into current values(?,?,?,?)");
						s3.setString(1, username);
						s3.setString(2, customer_name);
						s3.setString(3, city);
						s3.setString(4, customer_contact);
						s3.executeUpdate();
						JOptionPane.showMessageDialog(b1,"Success");
						customer i=new customer();	
						i.Customer_Port();
						f.setVisible(false);
						
					}
					else
					{
						JOptionPane.showMessageDialog(b1,"You are not signed up!");
					}

				}
				catch(SQLException sqlException)
				{
					sqlException.printStackTrace();
				}
				
			}
		}
		
	});
	b2.addActionListener(new ActionListener()
	{
		@Override
	    public void actionPerformed(ActionEvent e)
	    {
			f.setVisible(false);
			sign_in i=new sign_in();
			i.Sign_up();
}
		
	});
}
}

public class Login1{
	public static void main(String[] args) {
		Login_1 f=new Login_1();
	}
}	

	

