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

class Find_total_amount{//POLYMORPHISM
	int find(int a1,int a2) {
		int a3=a1*a2;
		return a3;
	}
	void find() {
		JOptionPane.showMessageDialog(null,"Houseboat not booked for payment!");

	}
	
}
class Payment extends Find_total_amount
{
	JFrame f=null;
	JPanel p1=null;
	JPanel p2=null;
	JPanel p3=null;
	String s;
	Payment(){
		JTextField jt1=new JTextField();
		JTextField jt2=new JTextField();
		
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
		JButton b6=new JButton("Check Amount");

		JLabel l1=new JLabel("HOUSEBOAT MANAGEMENT SYSTEM");
		JLabel l2=new JLabel("Payment");
		JLabel l3=new JLabel("Hope you have enjoyed your trip, to view your bill please click the \"check amount\" button below");
		JLabel l4=new JLabel("Name:");
		JLabel l5=new JLabel("Total Amount:");

		l5.setFont(new Font("Cambria", Font.PLAIN, 20));
		l4.setFont(new Font("Cambria", Font.PLAIN, 20));
		
		l2.setForeground(new Color(87, 82, 125));
		l2.setFont(new Font("Cambria", Font.BOLD, 22));
		
		b3.setBackground(new Color(46, 42, 82));
		b3.setOpaque(true);
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("Cambria", Font.BOLD, 22));
		p1.setBounds(0,40,120,600);
		p2.setBounds(0,0,800,40);
		p3.setBounds(40,40,800,800);
	
		f.add(p1, BorderLayout.WEST);
		f.add(p2, BorderLayout.NORTH);
		f.add(p3);
		

		p1.setBackground(new Color(87, 82, 125));
		p2.setBackground(new Color(46, 42, 82));
		p3.setBackground(new Color(255,255,255));
		p2.add(l1);
		p3.add(l2);
		p3.add(l3);
		p3.add(b6);
		p3.add(jt1);
		p3.add(jt2);
		jt2.setBounds(300,230,150,40);
		jt1.setBounds(200,180,150,40);

		p3.add(l5);
		p3.add(l4);
		l4.setBounds(100,100,200,200);
		l5.setBounds(100,150,200,200);
		
		b6.setBounds(320,80,150,30);
		l2.setBounds(350,0,350,50);
		l3.setBounds(110,40,650,50);
		p1.add(b1);p1.add(b2);p1.add(b3);p1.add(b5);
		f.setSize(800,500);
	
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
		
		b6.addActionListener(new ActionListener()
		{
			
			String s1,s2;
			int a1,a2,a3;
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				try {
				Connection c=(Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/Pro","postgres","rogue");
				PreparedStatement st1= c.prepareStatement("select cust_id,name from current");
				ResultSet rs1=st1.executeQuery();
				rs1.next();
				s1=rs1.getString("cust_id");
				PreparedStatement st2= c.prepareStatement("select houseboat_id from facilities where customer_id=?");
				st2.setString(1,s1);
				ResultSet rs2=st2.executeQuery();
				rs2.next();
				s2=rs2.getString("houseboat_id");
				PreparedStatement st3= c.prepareStatement("select * from booked_houseboat where houseboat_id=?");
				PreparedStatement st4= c.prepareStatement("insert into available_houseboat values (?,?,?,?,?,?,?)");

				st3.setString(1,s2);
				ResultSet rs3=st3.executeQuery();
				rs3.next();
				st4.setString(1,rs3.getString("houseboat_id"));
				st4.setString(2,rs3.getString("owner_id"));
				st4.setString(3,rs3.getString("houseboat_name"));
				st4.setInt(4,rs3.getInt("houseboat_capacity"));
				st4.setInt(5,rs3.getInt("no_of_rooms"));
				st4.setString(6,rs3.getString("typeof_boat"));
				st4.setInt(7,rs3.getInt("price_per_head"));
				
				PreparedStatement st5= c.prepareStatement("delete from booked_houseboat where houseboat_id=?");
				PreparedStatement st6= c.prepareStatement("delete from facilities where houseboat_id=?");
				PreparedStatement st7= c.prepareStatement("delete from houseboat_current where houseboat_id=?");
				
				PreparedStatement st8= c.prepareStatement("select no_of_passengers from customer where customer_id=?");
				st8.setString(1,s1);
				ResultSet rs5=st8.executeQuery();
				rs5.next();
				a1=rs5.getInt("no_of_passengers");
				a2=rs3.getInt("price_per_head");
				jt1.setText(rs1.getString("name"));
				
				
				a3=find(a1,a2);
				String result=String.valueOf(a3);
				jt2.setText(result);
				
				
				st7.setString(1,rs3.getString("houseboat_id"));
				st6.setString(1,rs3.getString("houseboat_id"));
				st5.setString(1,rs3.getString("houseboat_id"));
				st6.executeUpdate();
				st7.executeUpdate();
				st5.executeUpdate();
	 			st4.executeUpdate();
	 			
				

				
				JOptionPane.showMessageDialog(null,"Thank you for sailing!");


				}
				catch(SQLException sqlException)
				{
					find();
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
		
	}

	
	
}
public class Cust_Payment {
	public static void main(String[] args) {
		Payment s=new Payment();
	}

}
