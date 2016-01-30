package com.db.gui;

	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	import java.sql.*;
	
	public class AwtJdbcLogin extends Frame implements ActionListener
	{
		
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Label lb1,lb2;
		TextField tf1,tf2;
		Button b1,b2;
		
		public AwtJdbcLogin(String title)
		{
		setTitle(title);
		setLayout(new GridLayout(3,2));
		lb1=new Label("Enter UserName:");
		lb1.setBackground(Color.RED);
		tf1=new TextField(20);
		add(lb1);
		add(tf1);
		lb2=new Label("Enter Password:");
		lb2.setBackground(Color.GREEN);
		tf2=new TextField(20);
		tf2.setEchoChar('*');
		add(lb2);
		add(tf2);
		b1=new Button("Login");
		b1.addActionListener(this);
		b2=new Button("Cancel");
		b2.addActionListener(this);
		add(b1);
		add(b2);
		pack();
		setVisible(true);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==b1)
			{
			String userName=tf1.getText();
			String password=tf2.getText();
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
				PreparedStatement pst=con.prepareStatement("select * from User_Details where userName=? and password=?");
				pst.setString(1,userName);
				pst.setString(2,password);
				ResultSet rs=pst.executeQuery();
				if(rs.next())
				{
				new AwtJdbcTest();
				}
				else
				{
				JOptionPane.showMessageDialog(null,"Invalid UserName Or Password!!Login Failled!!!");
				}
				con.close();	
			}
			catch(Exception e1)
			{
			e1.printStackTrace();
			}
			}
			else
			{
			tf1.setText("");
			tf2.setText("");
			}

		}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AwtJdbcLogin("Login Form");
	}

}
