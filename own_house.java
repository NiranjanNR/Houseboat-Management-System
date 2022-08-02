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

interface Owner{
	public void Display_houseboat();//ABSTRACTION
}

abstract class implement_interface implements Owner{
	private String cname;
	public void Display_houseboat() {
		JFrame f= new JFrame();
		f.getContentPane().setLayout(null);
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		p3.setLayout(null);

		JButton b1=new JButton("Profile");
		JButton b2=new JButton("Houseboats");
		JButton b3=new JButton("Staff");
		JButton b5=new JButton("Logout");
		JLabel l1=new JLabel("HOUSEBOAT MANAGEMENT SYSTEM");
		JLabel l2=new JLabel("BOOKED HOUSEBOATS");
		JPanel ptable=new JPanel();
		ptable.setLayout(null);

		b2.setBackground(new Color(46, 42, 82));
		b2.setOpaque(true);
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("Cambria", Font.BOLD, 22));
		p1.setBounds(0,40,120,600);
		p2.setBounds(0,0,800,40);
		p3.setBounds(40,40,800,800);
		
		f.getContentPane().add(p1, BorderLayout.WEST);
		f.getContentPane().add(p2, BorderLayout.NORTH);
		f.getContentPane().add(p3);
		

		p1.setBackground(new Color(87, 82, 125));
		p2.setBackground(new Color(46, 42, 82));
		p3.setBackground(new Color(255,255,255));
		p2.add(l1);
		p3.add(ptable);
		p3.add(l2);
		l2.setFont(new Font("Cambria", Font.BOLD, 22));
		l2.setBounds(285,0,350,50);
		p1.add(b1);p1.add(b2);p1.add(b3);p1.add(b5);
		f.setSize(800,600);
		
		String[] col= {"CUSTOMER ID","HOUSEBOAT ID","HOUSEBOAT NAME","CAPACITY","TYPE","NO OF ROOMS"};
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
		jt.getColumnModel().getColumn(5).setPreferredWidth(10);

		jt.setRowHeight(30);

		JScrollPane scroll= new JScrollPane(jt);
		
		
		scroll.setBounds(0,0,530,250);
		ptable.add(scroll);
		ptable.setBounds(150,145,530,250);
		
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(true);
	
		
		
		String s1="";String s2="";String s3="";String s4="";String s5="";
		
		try
		{
			Connection c=(Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/Pro","postgres","rogue");
	 		PreparedStatement st1= c.prepareStatement("select * from current");
	 		ResultSet rs1=st1.executeQuery();
	 		rs1.next();
			PreparedStatement statement= c.prepareStatement("SELECT houseboat_id,houseboat_name,houseboat_capacity,no_of_rooms,typeof_boat FROM booked_houseboat where owner_id=?");
			
			statement.setString(1,rs1.getString("cust_id"));
			ResultSet resultset= statement.executeQuery();
			

			while(resultset.next())
			{
				PreparedStatement st2= c.prepareStatement("SELECT customer_id FROM facilities where houseboat_id=?");
				st2.setString(1,resultset.getString("houseboat_id"));
				ResultSet rs2= st2.executeQuery();
				rs2.next();
				cname=rs2.getString("customer_id");
				s5=resultset.getString("houseboat_id");
				s1=resultset.getString("houseboat_name");
				s2=resultset.getString("houseboat_capacity");
				s3=resultset.getString("typeof_boat");
				s4=resultset.getString("no_of_rooms");
				
				m.addRow(new Object[]{cname,s5,s1,s2,s3,s4});
				
			}	

		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
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
				ownerportal c=new ownerportal();
	}
			
		});
		b3.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				f.setVisible(false);
				Show_staff s=new Show_staff();
		    }
			
		});
		}
		
		
		
		
	}
	

//INHERITANCE
class extend_class extends implement_interface{
		
}
	


public class own_house {
	
	public static void main(String[] args) {
		extend_class t=new extend_class();
		t.Display_houseboat();
	}

}
