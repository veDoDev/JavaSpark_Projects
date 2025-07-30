import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Delete extends JFrame
{
	private Container c;
	private JLabel labRno, labName;
	private JTextField txtRno;
	private JButton btnDelete, btnBack;

	public Delete()
	{
		c = getContentPane();
		c.setBackground(new Color(210, 230, 255));
		setLayout(new FlowLayout());

		labRno = new JLabel("Enter Roll no");
		txtRno = new JTextField(20);
		btnDelete = new JButton("Delete");
		btnBack = new JButton("Back");

		Font f = new Font("Calibri", Font.BOLD, 40);
		labRno.setFont(f);
		txtRno.setFont(f);
		btnDelete.setFont(f);
		btnBack.setFont(f);

		ActionListener save = (ae)->
		{
			int rno = Integer.parseInt(txtRno.getText());
			
			try{
				//load Driver
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
				//url
				String url = "jdbc:mysql://localhost:3306/sms30july25";
				
				//connection
				Connection con = DriverManager.getConnection(url, "root", "RootPassword@123");

				//sql
				String sql = "DELETE FROM student WHERE rno = ?";
				PreparedStatement pst = con.prepareStatement(sql);

				pst.setInt(1, rno);
				pst.executeUpdate();

				JOptionPane.showMessageDialog(c, "thank you");

				txtRno.setText("");
				txtRno.requestFocus();			
			}
			catch(SQLException e)
			{
				JOptionPane.showMessageDialog(c,"Issue " + e);
			}
		};
		btnDelete.addActionListener(save);

		ActionListener back = (ae)->
		{
			SMS main = new SMS();
			dispose();
		};
		btnBack.addActionListener(back);
	
		c.add(labRno);
		c.add(txtRno);
		c.add(btnDelete);
		c.add(btnBack);

		setVisible(true);	
		setTitle("Update details");
		setSize(700,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
	}
}