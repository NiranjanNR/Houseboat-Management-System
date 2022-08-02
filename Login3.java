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
class Login_3 extends Login{
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
	Login_3(){
	f=new JFrame("LOGIN/SIGN-UP");
	JPanel jp1 = new JPanel();
	jp1.setBounds(30,20,150,150);
    ImageIcon ic = new ImageIcon(new ImageIcon("/Users/navneeth/eclipse-workspace/op/src/Project/hu.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
	JLabel l=new JLabel(ic);
	jp1.add(l);
	f.add(jp1);
	
	l1= new JLabel("USER ID : ");
	t1= new JTextField();
	l2= new JLabel("PASSWORD : ");
	t2= new JPasswordField();
	b1= new JButton("LOGIN");
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
	f.setSize(400,400);
	f.add(jp1);	
	f.setVisible(true);
	f.setResizable(false);

	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	b1.addActionListener(new ActionListener()
	{
	

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(t1.getText().isEmpty() ) {
				JOptionPane.showMessageDialog(null, "Owner ID is Empty");
			}
			else if (t2.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Password is Empty");
			}
			else {
				String username=t1.getText();
				@SuppressWarnings("deprecation")
				String password=t2.getText();
				String owner_contact=null;
				String owner_name=null;
				String city=null;
				
				try
				{
					Connection c=(Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/Pro","postgres","rogue");
					PreparedStatement statement= c.prepareStatement("select owner_id,owner_password from owner where owner_id=? and owner_password=?");
					PreparedStatement s2= c.prepareStatement("select owner_name,owner_contact,city from owner where owner_id=? and owner_password=?");
					s2.setString(1,username);
					s2.setString(2,password);
					ResultSet rs2=s2.executeQuery();
					statement.setString(1,username);
					statement.setString(2,password);
					ResultSet resultset= statement.executeQuery();
					if(resultset.next())
					{
						rs2.next();
						owner_name=rs2.getString("owner_name");
						owner_contact=rs2.getString("owner_contact");
						city=rs2.getString("city");
						PreparedStatement s3= c.prepareStatement("insert into current values(?,?,?,?)");
						s3.setString(1, username);
						s3.setString(2, owner_name);
						s3.setString(3, owner_contact);
						s3.setString(4, city);

						s3.executeUpdate();
						JOptionPane.showMessageDialog(b1,"Success");
						ownerportal i=new ownerportal();	
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
	}
}

public class Login3{
	public static void main(String[] args) {
		Login_3 f=new Login_3();
	}
	
}
	

