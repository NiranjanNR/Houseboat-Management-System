package Project;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

class page1{
	JFrame f;
	JRadioButton a = null;
	JRadioButton b = null;
	JRadioButton c = null;
	JButton b1 = null;
	JPanel jp=null;
    page1() {
        f = new JFrame("Please Select");
        f.setLayout(null);
        JRadioButton a = new JRadioButton(" Customer");
        JRadioButton c = new JRadioButton("Houseboat Owner");
        JButton b1= new JButton("Proceed");
        
        JPanel jp1=new JPanel();
        JPanel jp3=new JPanel();
        
        JLabel l2=new JLabel("SEAGULL LANDINGS HOUSEBOAT MANAGEMENT SYSTEM");
        l2.setForeground(new Color(87, 82, 125));
		l2.setFont(new Font("Cambria", Font.BOLD, 18));
		l2.setBounds(70,20,550,50);
        f.add(l2);
		jp1.setBounds(170,100,150,150);
        ImageIcon ic = new ImageIcon(new ImageIcon("/Users/navneeth/eclipse-workspace/op/src/Project/hi.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
		JLabel l=new JLabel(ic);
		jp1.add(l);
		f.add(jp1);
        f.add(b1);
        
        jp3.setBounds(330,100,150,150);
        ic = new ImageIcon(new ImageIcon("/Users/navneeth/eclipse-workspace/op/src/Project/hu.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
		l=new JLabel(ic);
		jp3.add(l);
		f.add(jp3);
		
        b1.setBounds(230, 300, 200, 30);
        
        ButtonGroup bg=new ButtonGroup();
        bg.add(a);bg.add(c);
        JPanel radiobutton=new JPanel();
        radiobutton.add(a);
        radiobutton.add(c);

        f.add(radiobutton);
        
        
        radiobutton.setBounds(60,260,550,30);
        f.setSize(650, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setBackground(new Color(0, 0, 0));
        f.setResizable(false);
        b1.addActionListener(new ActionListener()
        {
        	@Override
            public void actionPerformed(ActionEvent e)
            {
        		if(a.isSelected()) {
        			f.setVisible(false);
            		Login_1 r= new Login_1();
        		}
        	
        		else {
        			f.setVisible(false);
            		Login_3 r= new Login_3();
        		}
            }
        });
    }      
}
public class Firstpage {
    public static void main(String[] args) {
    	page1 s=new page1();

    }
}