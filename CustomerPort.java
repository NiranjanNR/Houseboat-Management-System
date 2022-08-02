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
class customer
{
	JFrame f=null;
	JPanel p1=null;
	JPanel p2=null;
	JPanel p3=null;
	private String s;


	void Customer_Port(){
		JFrame f= new JFrame();
		f.getContentPane().setLayout(null);
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		p3.setLayout(null);

		ImageIcon ic = new ImageIcon(new ImageIcon("/Users/navneeth/eclipse-workspace/op/src/Project/hi.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
		JLabel l=new JLabel(ic);
		JLabel l2=new JLabel("Name:");
		JLabel l3=new JLabel("Phone no:");
		JLabel l4=new JLabel("City:");
		
		l.setBounds(70,-10,200,200);
		l2.setForeground(Color.BLACK);
		l2.setFont(new Font("Cambria", Font.PLAIN, 20));
		l3.setFont(new Font("Cambria", Font.PLAIN, 20));
		l4.setFont(new Font("Cambria", Font.PLAIN, 20));


		JButton b1=new JButton("Profile");
		JButton b2=new JButton("Houseboats");
		JButton b3=new JButton("Payment");
		JButton b5=new JButton("Logout");
		JLabel l1=new JLabel("HOUSEBOAT MANAGEMENT SYSTEM");
		
		b1.setBackground(new Color(46, 42, 82));
		b1.setOpaque(true);
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("Cambria", Font.BOLD, 22));
		p1.setBounds(0,40,120,600);
		p2.setBounds(0,0,700,40);
		p3.setBounds(40,40,800,800);
	
		
		f.getContentPane().add(p1, BorderLayout.WEST);
		f.getContentPane().add(p2, BorderLayout.NORTH);
		f.getContentPane().add(p3);
		
		p3.add(l);		

		p1.setBackground(new Color(87, 82, 125));
		p2.setBackground(new Color(46, 42, 82));
		p3.setBackground(new Color(255,255,255));
		p2.add(l1);
		p3.add(l2);
		p3.add(l3);
		p3.add(l4);
		l2.setBounds(100,100,200,200);
		l3.setBounds(100,150,200,200);
		l4.setBounds(100,200,200,200);

		p1.add(b1);p1.add(b2);p1.add(b3);p1.add(b5);
		f.setSize(700,500);
	
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);

		JTextField jt1=new JTextField();
		JTextField jt2=new JTextField();
		JTextField jt3=new JTextField();
		try
		{
			Connection c=(Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/Pro","postgres","rogue");
			PreparedStatement statement= c.prepareStatement("SELECT cust_id,name,city,contact_no FROM current");
			ResultSet resultset= statement.executeQuery();

			if(resultset.next())
			{
				s=getString("cust_id");
				jt1.setText(resultset.getString("name"));
				jt1.setBounds(200,180,150,40);
				p3.add(jt1);
						
				jt2.setText(resultset.getString("contact_no"));
				jt2.setBounds(200,230,150,40);
				p3.add(jt2);
						
				jt3.setText(resultset.getString("city"));
				jt3.setBounds(200,280,150,40);
				p3.add(jt3);
						
				PreparedStatement s2= c.prepareStatement("delete from current");
				//s2.executeUpdate();
			}

		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}

			

		b5.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				f.setVisible(false);
				page1 s=new page1();
				try {
				Connection c=(Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/Pro","postgres","rogue");
				PreparedStatement s2= c.prepareStatement("delete from current");
				PreparedStatement st5= c.prepareStatement("delete from houseboat_current");
	 			st5.executeUpdate();
				s2.executeUpdate();
				}
				catch(SQLException sqlException)
				{
					sqlException.printStackTrace();
				}
				JOptionPane.showMessageDialog(b1,"You have successfully logged out!");
	}
			
		});
		b2.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				f.setVisible(false);
				customer_houseboat s=new customer_houseboat();
				s.show_houseboats();
	}
			
		});
		b3.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				f.setVisible(false);
				Payment s=new Payment();
	}
			
		});
	}

	private String getString(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
public class CustomerPort {
	public static void main(String[] args) {
		customer i=new customer();
		i.Customer_Port();
	}

}
