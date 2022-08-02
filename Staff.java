package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class Show_staff{
	 Show_staff() {
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
		JLabel l2=new JLabel("STAFF");
		JPanel ptable=new JPanel();
		ptable.setLayout(null);

		b3.setBackground(new Color(46, 42, 82));
		b3.setOpaque(true);
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
		ptable.setBounds(100,145,620,250);

		p3.add(l2);
		l2.setFont(new Font("Cambria", Font.BOLD, 22));
		l2.setBounds(365,0,350,50);
		p1.add(b1);p1.add(b2);p1.add(b3);p1.add(b5);
		f.setSize(800,600);
		
		String[] col= {"STAFF ID","STAFF NAME","JOB","GENDER"};
		DefaultTableModel m= new DefaultTableModel();
		m.setColumnIdentifiers(col);
		
		JTable jt=new JTable();
		jt.setModel(m);
		jt.setFont(new Font("Arial",Font.PLAIN,15));
		
		jt.getColumnModel().getColumn(0).setPreferredWidth(10);
		jt.getColumnModel().getColumn(1).setPreferredWidth(10);
		jt.getColumnModel().getColumn(2).setPreferredWidth(10);
		jt.getColumnModel().getColumn(3).setPreferredWidth(10);
		

		jt.setRowHeight(30);

		JScrollPane scroll= new JScrollPane(jt);
		
		
		scroll.setBounds(0,0,620,250);
		ptable.add(scroll);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(true);
	
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
		
		String s1="";String s2="";String s3="";String s5;

		try
		{
			Connection c=(Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/Pro","postgres","rogue");
	 		PreparedStatement st1= c.prepareStatement("select * from current");
	 		ResultSet rs1=st1.executeQuery();
	 		rs1.next();
	 		PreparedStatement statement= c.prepareStatement("SELECT staff_id,staff_name,job,gender FROM staff where owner_id=?");			
			statement.setString(1,rs1.getString("cust_id"));
			ResultSet resultset= statement.executeQuery();
			
			while(resultset.next())
			{
				
				s5=resultset.getString("staff_id");
				s1=resultset.getString("staff_name");
				s2=resultset.getString("job");
				s3=resultset.getString("gender");
				
				m.addRow(new Object[]{s5,s1,s2,s3});
				
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
				extend_class t=new extend_class();
				t.Display_houseboat();
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
		}
}
public class Staff {

	public static void main(String[] args) {
		Show_staff s=new Show_staff();
		
	}
}
