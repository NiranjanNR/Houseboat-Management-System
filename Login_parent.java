package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

abstract class Login{//GENERALISATION
	String username="";
	String password="";
	String customer_contact=null;
	String customer_name=null;
	String city=null;
}

