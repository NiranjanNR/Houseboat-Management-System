package Project;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
class customer_houseboat{
	JFrame f=null;
	JButton button=new JButton("Book");
	String s=null;
	String l=null;
	String lbl;
	void show_houseboats() {
		JFrame f= new JFrame();
		f.getContentPane().setLayout(null);
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		p3.setLayout(null);
		JPanel ptable=new JPanel();
		ptable.setLayout(null);

		ImageIcon ic = new ImageIcon(new ImageIcon("/Users/navneeth/eclipse-workspace/op/src/Project/houseboat.png").getImage().getScaledInstance(224, 150, Image.SCALE_SMOOTH));
		JLabel l=new JLabel(ic);
		JLabel l3=new JLabel("After your trip you can go ahead and see the total amount you have spent for the trip in the payment tab.");

		l.setBounds(120,10,200,200);

		JButton b1=new JButton("Profile");
		JButton b2=new JButton("Houseboats");
		JButton b3=new JButton("Payment");
		JButton b5=new JButton("Logout");
		JLabel l4=new JLabel("LIST OF AVAILABLE HOUSEBOATS");
		JLabel l1=new JLabel("HOUSEBOAT MANAGEMENT SYSTEM");
		JLabel l5=new JLabel("Please click the houseboat id you want to book :");
		
		b2.setBackground(new Color(46, 42, 82));
		b2.setOpaque(true);
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("Cambria", Font.BOLD, 22));
		p1.setBounds(0,40,120,900);
		p2.setBounds(0,0,900,40);
		p3.setBounds(40,40,900,900);
	
		
		f.getContentPane().add(p1, BorderLayout.WEST);
		f.getContentPane().add(p2, BorderLayout.NORTH);
		f.add(ptable);
		f.getContentPane().add(p3);
		
		p3.add(l);	

		p1.setBackground(new Color(87, 82, 125));
		p2.setBackground(new Color(46, 42, 82));
		p3.setBackground(new Color(255,255,255));
		p2.add(l1);
		p3.add(l4);
		p3.add(l5);
		p3.add(l3);
		l5.setBounds(100,200,350,20);
		l3.setBounds(100,495,750,20);

		l4.setBounds(420,70,350,50);
		l4.setFont(new Font("Cambria", Font.PLAIN, 20));
		
		
		p1.add(b1);p1.add(b2);p1.add(b3);p1.add(b5);
		f.setSize(900,600);
		
		String[] col= {"HOUSEBOAT ID","HOUSEBOAT NAME","CAPACITY","TYPE","NO OF ROOMS"};
		DefaultTableModel m= new DefaultTableModel();
		m.setColumnIdentifiers(col);

		JTable jt=new JTable();
		jt.setModel(m);
		jt.setFont(new Font("Arial",Font.PLAIN,15));
		
		jt.getColumnModel().getColumn(0).setPreferredWidth(10);
		jt.getColumnModel().getColumn(1).setPreferredWidth(10);
		jt.getColumnModel().getColumn(2).setPreferredWidth(10);
		jt.getColumnModel().getColumn(3).setPreferredWidth(10);
		jt.getColumnModel().getColumn(4).setPreferredWidth(10);
		jt.setRowHeight(30);

		JScrollPane scroll= new JScrollPane(jt);
		
		scroll.setBounds(0,0,720,250);
		ptable.add(scroll);
		ptable.setBounds(150,275,720,250);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
	
		
		
		String s1="";String s2="";String s3="";String s4="";String s5="";
		
		try
		{
			Connection c=(Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/Pro","postgres","rogue");
			PreparedStatement statement= c.prepareStatement("SELECT houseboat_id,houseboat_name,houseboat_capacity,no_of_rooms,typeof_boat FROM available_houseboat");
			ResultSet resultset= statement.executeQuery();
			while(resultset.next())
			{
				s5=resultset.getString("houseboat_id");
				s1=resultset.getString("houseboat_name");
				s2=resultset.getString("houseboat_capacity");
				s3=resultset.getString("typeof_boat");
				s4=resultset.getString("no_of_rooms");
				
				m.addRow(new Object[]{s5,s1,s2,s3,s4});
				
			}	

		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		jt.getSelectionModel().addListSelectionListener( new ListSelectionListener()
	    {
	    	@Override
			public void valueChanged(ListSelectionEvent e) 
	    		{

	             String s=jt.getValueAt(jt.getSelectedRow(), 0).toString();
	             JOptionPane.showMessageDialog(null,s);
	             String s1="";String s2="";String s3="";String s5="";String s6="";
	 	 		int a,b,s7;
	 	    	 try
	 	 		{
	 	 			Connection c=(Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/Pro","postgres","rogue");
	 	 			PreparedStatement st1= c.prepareStatement("select * from available_houseboat where houseboat_id=?");
	 	 			st1.setString(1,s);
	 	 			ResultSet resultset=st1.executeQuery();
	 	 			resultset.next();
	 	 			s1=resultset.getString("houseboat_id");
	 	 			s2=resultset.getString("owner_id");
	 				s3=resultset.getString("houseboat_name");
	 				a=resultset.getInt("houseboat_capacity");
	 				b=resultset.getInt("no_of_rooms");
	 				s6=resultset.getString("typeof_boat");
	 				s7=resultset.getInt("price_per_head");

	 	 			PreparedStatement st2= c.prepareStatement("insert into booked_houseboat values(?,?,?,?,?,?,?)");
	 	 			PreparedStatement st3= c.prepareStatement("insert into houseboat_current values(?)");
	 	 			st3.setString(1,s1);
	 	 			st3.executeUpdate();
	 	 			st2.setString(1,s1);
	 	 			st2.setString(2,s2);
	 	 			st2.setString(3,s3);
	 	 			st2.setInt(4,a);
	 	 			st2.setInt(5,b);
	 	 			st2.setString(6,s6);
	 	 			st2.setInt(7,s7);

	 	 			st2.executeUpdate();
	 	 			Booking1 d=new Booking1();
	 	 			

	 	 		}
	 	 		catch(SQLException sqlException)
	 	 		{
	 	 			sqlException.printStackTrace();
	 	 		}
	 	        
	 	        
	 	      
	             
	           
	    		}

	    });
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		
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
		b1.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				f.setVisible(false);
				customer s=new customer();
				s.Customer_Port();
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
	
	
	
}

public class Real_cust_house{
	public static void main(String[] args) {
		customer_houseboat u=new customer_houseboat();
		u.show_houseboats();
	}

}
