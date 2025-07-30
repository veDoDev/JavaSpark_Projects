import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Add extends JFrame
{
	private Container c;
	private JLabel labRno, labName, labMarks1, labMarks2, labMarks3;
	private JTextField txtRno, txtName, txtMarks1, txtMarks2, txtMarks3;
	private JButton btnSave, btnBack;

	public Add()
	{
		c = getContentPane();
		c.setBackground(new Color(210, 230, 255));
		setLayout(new FlowLayout());

		labRno = new JLabel("Enter Roll no");
		txtRno = new JTextField(20);
		labName = new JLabel("Enter Name");
		txtName = new JTextField(20);
		labMarks1 = new JLabel("Enter Marks1");
		txtMarks1 = new JTextField(20);
		labMarks2 = new JLabel("Enter Marks2");
		txtMarks2 = new JTextField(20);
		labMarks3 = new JLabel("Enter Marks3");
		txtMarks3 = new JTextField(20);
		btnSave = new JButton("Save");
		btnBack = new JButton("Back");

		Font f = new Font("Calibri", Font.BOLD, 40);
		labRno.setFont(f);
		txtRno.setFont(f);
		labName.setFont(f);
		txtName.setFont(f);
		txtMarks1.setFont(f);
		labMarks1.setFont(f);
		txtMarks2.setFont(f);
		labMarks2.setFont(f);
		txtMarks3.setFont(f);
		labMarks3.setFont(f);
		btnSave.setFont(f);
		btnBack.setFont(f);

		ActionListener save = (ae)->
		{
			int rno = Integer.parseInt(txtRno.getText());
			String name = txtName.getText();
			int marks1 = Integer.parseInt(txtMarks1.getText());
			int marks2 = Integer.parseInt(txtMarks2.getText());
			int marks3 = Integer.parseInt(txtMarks3.getText());
			
			try{
				//load Driver
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
				//url
				String url = "jdbc:mysql://localhost:3306/sms30july25";
				
				//connection
				Connection con = DriverManager.getConnection(url, "root", "RootPassword@123");

				//sql
				//					rno, name, m1, m2, m3
				String sql = "insert into student values(?, ?, ?, ?, ?)";
				PreparedStatement pst = con.prepareStatement(sql);

				pst.setInt(1, rno);
				pst.setString(2, name);
				pst.setInt(3, marks1);
				pst.setInt(4, marks2);
				pst.setInt(5, marks3);

				pst.executeUpdate();

				JOptionPane.showMessageDialog(c, "thank you");

				txtRno.setText("");
				txtName.setText("");
				txtMarks1.setText("");
				txtMarks2.setText("");
				txtMarks3.setText("");

				txtRno.requestFocus();			
			}
			catch(SQLException e)
			{
				JOptionPane.showMessageDialog(c,"Issue " + e);
			}
		};
		btnSave.addActionListener(save);

		ActionListener back = (ae)->
		{
			SMS main = new SMS();
			dispose();
		};
		btnBack.addActionListener(back);
	
		c.add(labRno);
		c.add(txtRno);
		c.add(labName);
		c.add(txtName);
		c.add(labMarks1);
		c.add(txtMarks1);
		c.add(labMarks2);
		c.add(txtMarks2);
		c.add(labMarks3);
		c.add(txtMarks3);
		c.add(btnSave);
		c.add(btnBack);

		setVisible(true);	
		setTitle("Add details");
		setSize(700,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
	}
}