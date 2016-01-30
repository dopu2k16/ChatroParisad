package com.db.gui;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
class AwtJdbcTest extends Frame implements ActionListener 
{
       /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con;
       PreparedStatement ps1,ps2,ps3,ps4;
       ResultSet rs;
       Label lno,lna,ladd;
       TextField tno,tna,tadd;
       Button bins,bdet,bdel,bupd;

       	
       AwtJdbcTest() 
	  {
	
    	   GridLayout gl;
	gl = new GridLayout(5,2);
	setLayout(gl);
	setSize(500, 400);
	lno=new Label("Number");
	lno.setBackground(Color.RED);
	add(lno);
	tno = new TextField(20);
	add(tno);
	lna = new Label("Name");
	lna.setBackground(Color.GREEN);
	add(lna);
	tna = new TextField(20);
	add(tna);
	ladd = new Label("Address");
	ladd.setBackground(Color.YELLOW);
	add(ladd);
	tadd = new TextField(20);
	add(tadd);
	bins = new Button("INSERT");
	bins.addActionListener(this);
	add(bins);
	bdet = new Button("DETAILS");
	bdet.addActionListener(this);
	add(bdet);
	bdel = new Button("DELETE");
	bdel.addActionListener(this);
	add(bdel);
	bupd = new Button("UPDATE");
	bupd.addActionListener(this);
	add(bupd);
	setVisible(true);
	
	addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
	}
	void dbConnect()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			String q1, q2, q3, q4;
			q1 = "INSERT INTO student VALUES(?, ?, ?)";
			q2 = "SELECT * FROM STUDENT WHERE SNO = ?";
			q3 = "DELETE FROM STUDENT WHERE SNO = ?";
			q4 = "UPDATE STUDENT SET SNAME=?, SADD=? WHERE SNO = ?";
			ps1 = con.prepareStatement(q1);
			ps2 = con.prepareStatement(q2);
			ps3 = con.prepareStatement(q3);
			ps4 = con.prepareStatement(q4);	
	
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent ae) 
	{
		dbConnect();
		try
		{
			int no;
			String name, add;
			no = Integer.parseInt(tno.getText());
			name = tna.getText();
			add = tadd.getText();
			if(ae.getSource() == bins)
			{
			ps1.setInt(1, no);
			ps1.setString(2, name);
			ps1.setString(3, add);
			ps1.executeUpdate();
			}
			else if(ae.getSource() == bdet) 
			{
			ps2.setInt(1, no);
			rs = ps2.executeQuery();
			if(rs.next())
			{
				tna.setText(rs.getString("sname"));
				tadd.setText(rs.getString("sadd"));
			}
			}
			else if(ae.getSource() == bdel)
			{
				ps3.setInt(1, no);
				ps3.executeUpdate();
			}
			else
			{
				ps4.setString(1, name);
				ps4.setString(2, add);
				ps4.setInt(3, no);
				ps4.executeUpdate();
			}
		con.close();	
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}			
	}
	
}