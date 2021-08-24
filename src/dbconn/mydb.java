package dbconn;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class mydb extends JFrame {

	private JPanel contentPane;
	private JTextField tName;
	private JTextField tSurname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mydb frame = new mydb();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mydb() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(78, 39, 222, 22);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Get data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/cmpe372","Melissa","Melissa");
					Statement stmn = conn.createStatement();
					String sql="Select * From users";
					ResultSet rs = stmn.executeQuery(sql);
					if (rs.next()) { //testing
					while (rs.next()) { 
						comboBox.addItem(rs.getString("Name")+ "" + 	rs.getString("Surname")+ " " +	rs.getString("mail"));
					}
						
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(78, 184, 89, 23);
		contentPane.add(btnNewButton);
		
		tName = new JTextField();
		tName.setBounds(78, 83, 222, 20);
		contentPane.add(tName);
		tName.setColumns(10);
		
		tSurname = new JTextField();
		tSurname.setBounds(78, 114, 222, 20);
		contentPane.add(tSurname);
		tSurname.setColumns(10);
		
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String x = comboBox.getSelectedItem().toString();
				String[] y = x.split(" ");
				tName.setText(y[0]);
				tSurname.setText(y[1]);
				
				
			}
		});
		
	}
}
