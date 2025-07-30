import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class View extends JFrame
{
	private Container c;
	private JTextArea taData;
	private JButton btnBack;

	public View()
	{
		c = getContentPane();
		c.setBackground(new Color(255, 245, 200));
		setLayout(new FlowLayout());

		taData = new JTextArea(5,25);
		btnBack = new JButton("Back");

		Font f = new Font("Calibri", Font.BOLD, 30);
		taData.setFont(f);
		btnBack.setFont(f);
		
		try{
			//load Driver
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
			//url
			String url = "jdbc:mysql://localhost:3306/sms30july25";
				
			//connection
			Connection con = DriverManager.getConnection(url, "root", "RootPassword@123");

			//sql
			String sql = "select * from student";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			String info = "Rollno.\tName\tMarks1\tMarks2\tMarks3\n";
			while(rs.next())
			{
				info += ""+rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getInt(4)+"\t"+rs.getInt(5)+"\n";
			}
			taData.setText(info);
			pst.close();
			rs.close();
			con.close();			
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(c,"Issue " + e);
		}
		
		ActionListener back = (ae)->
		{
			SMS main = new SMS();
			dispose();
		};
		btnBack.addActionListener(back);

		c.add(taData);
		c.add(btnBack);

		setVisible(true);	
		setTitle("Add details");
		setSize(900,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	
	}
}