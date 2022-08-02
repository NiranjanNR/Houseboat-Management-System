package Project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Booking1 {
	String Wifi,breakfast,lunch,dinner,snacks,entertainment;
	String cust_id="";String houseboat_id="";
	Booking1(){
		JFrame f= new JFrame();
		f.getContentPane().setLayout(null);
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		p3.setLayout(null);

		JButton b1=new JButton("Profile");
		JButton b2=new JButton("Houseboats");
		JButton b3=new JButton("Payment");
		JButton b5=new JButton("Logout");
		JLabel l1=new JLabel("HOUSEBOAT MANAGEMENT SYSTEM");
		JLabel l2=new JLabel("BOOKING");
		JLabel l3=new JLabel("Please select the facilities you want :");

		l2.setForeground(new Color(87, 82, 125));
		l2.setFont(new Font("Cambria", Font.BOLD, 22));
		l3.setFont(new Font("Cambria", Font.PLAIN, 18));
		
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("Cambria", Font.BOLD, 22));
		p1.setBounds(0,40,120,600);
		p2.setBounds(0,0,800,40);
		p3.setBounds(40,40,800,800);
	
		
		f.add(p1, BorderLayout.WEST);
		f.add(p2, BorderLayout.NORTH);
		f.add(p3);
		//ComboBox Labels
		//Combo1
		JLabel jl1=new JLabel("Wifi :");
		p3.add(jl1);
		jl1.setBounds(100,110,100,80);
		jl1.setFont(new Font("Cambria", Font.PLAIN, 18));
		String wifi[]={"Yes","No"};
		JComboBox jcb1=new JComboBox(wifi);
		jcb1.setBounds(150,112,100,80);
		p3.add(jcb1);
		
		//Combo2
		JLabel jl2=new JLabel("Breakfast :");
		p3.add(jl2);
		jl2.setBounds(300,110,100,80);
		jl2.setFont(new Font("Cambria", Font.PLAIN, 18));
		String Breakfast[]={"Continental Cuisine","Native Cuisine"};
		JComboBox jcb2=new JComboBox(Breakfast);
		jcb2.setBounds(400,112,180,80);
		p3.add(jcb2);
		
		//Combo3	
		JLabel jl3=new JLabel("Lunch :");
		p3.add(jl3);
		jl3.setBounds(100,160,100,80);
		jl3.setFont(new Font("Cambria", Font.PLAIN, 18));
		String Lunch[]={"Continental Cuisine","Native Cuisine"};
		JComboBox jcb3=new JComboBox(Lunch);
		jcb3.setBounds(200,162,180,80);
		p3.add(jcb3);

		//Combo4	
		JLabel jl4=new JLabel("Dinner :");
		p3.add(jl4);
		jl4.setBounds(400,160,100,80);
		jl4.setFont(new Font("Cambria", Font.PLAIN, 18));
		String Dinner[]={"Continental Cuisine","Native Cuisine"};
		JComboBox jcb4=new JComboBox(Dinner);
		jcb4.setBounds(500,162,180,80);
		p3.add(jcb4);
		
		//Comb05
		JLabel jl5=new JLabel("Snacks :");
		p3.add(jl5);
		jl5.setBounds(100,210,100,80);
		jl5.setFont(new Font("Cambria", Font.PLAIN, 18));
		String Snacks[]={"Biscuits","Potato Chips","Icecream","Tea","Coffee"};
		JComboBox jcb5=new JComboBox(Snacks);
		jcb5.setBounds(200,212,180,80);
		p3.add(jcb5);
		
		//Combo6
		JLabel jl6=new JLabel("Entertainment :");
		p3.add(jl6);
		jl6.setBounds(400,210,150,80);
		jl6.setFont(new Font("Cambria", Font.PLAIN, 18));
		String Entertainment[]={"Dance","Music","Night Fishing"};
		JComboBox jcb6=new JComboBox(Entertainment);
		jcb6.setBounds(550,212,120,80);
		p3.add(jcb6);
		
		//Facilitybutton
		JButton fb1=new JButton("Confirm Booking");
		fb1.setBounds(350,310,120,40);
		p3.add(fb1);
		
		
		
		p1.setBackground(new Color(87, 82, 125));
		p2.setBackground(new Color(46, 42, 82));
		p3.setBackground(new Color(255,255,255));
		p2.add(l1);
		p3.add(l2);
		p3.add(l3);
		l3.setBounds(100,80,350,50);
		l2.setBounds(350,0,350,50);
		p1.add(b1);p1.add(b2);p1.add(b3);p1.add(b5);
		f.setSize(800,600);
	
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
		
		fb1.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				
				Wifi=(String)jcb1.getSelectedItem();
				breakfast=(String)jcb2.getSelectedItem();
				lunch=(String)jcb3.getSelectedItem();
				dinner=(String)jcb4.getSelectedItem();
				snacks=(String)jcb5.getSelectedItem();
				entertainment=(String)jcb6.getSelectedItem();
				try
				{
					Connection c=(Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/Pro","postgres","rogue");
					PreparedStatement st1= c.prepareStatement("SELECT cust_id FROM current");
					PreparedStatement st2= c.prepareStatement("SELECT houseboat_id FROM houseboat_current");
					PreparedStatement st4= c.prepareStatement("insert into facilities values(?,?,?,?,?,?,?,?)");
					PreparedStatement st5= c.prepareStatement("delete from available_houseboat where houseboat_id=?");
					ResultSet rs1=st1.executeQuery();
					ResultSet rs2=st2.executeQuery();
					rs2.next();
					houseboat_id=rs2.getString("houseboat_id");
					rs1.next();
					cust_id=rs1.getString("cust_id");
					st4.setString(1,cust_id);
					st4.setString(2,houseboat_id);
					st4.setString(3,Wifi);
		 			st4.setString(4,breakfast);
		 			st4.setString(5,lunch);
		 			st4.setString(6,dinner);		 			
		 			st4.setString(7,snacks);
		 			st4.setString(8,entertainment);
		 			st4.executeUpdate();
		 			st5.setString(1,houseboat_id);
		 			st5.executeUpdate();
		 			JOptionPane.showMessageDialog(null,"Successfully booked");
		 			f.setVisible(false);
					}

				
				catch(SQLException sqlException)
				{
					JOptionPane.showMessageDialog(null,"A customer can only book one houseboat");
					
					sqlException.printStackTrace();
					}
	}
			
		});
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
		
		
		
		
		
		
	}
}

public class Booking {
	public static void main(String[] args) {
		Booking1 s=new Booking1();
	}
}
