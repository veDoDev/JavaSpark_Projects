import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class SMS extends JFrame
{
	private Container c;
	private JButton btnAdd, btnView, btnUpdate, btnDelete;
	public SMS()
	{
		c = getContentPane();
		c.setBackground(new Color(230, 255, 230)); 
		c.setLayout(new GridLayout(2, 2, 20, 20)); 
		//(new FlowLayout());
	
		btnAdd = new JButton("Add");
		btnView = new JButton("View");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");

		Font f = new Font("Calibri", Font.BOLD, 30);
		btnAdd.setFont(f);
		btnView.setFont(f);
		btnUpdate.setFont(f);
		btnDelete.setFont(f);

		ActionListener add = (ae)->
		{
			Add a = new Add();
			dispose();
		};

		ActionListener view = (ae)->
		{
			View v = new View();
			dispose();
		};
		ActionListener update = (ae)->
		{
			Update u = new Update();
			dispose();
		};
		ActionListener delete = (ae)->
		{
			Delete d = new Delete();
			dispose();
		};

		btnAdd.addActionListener(add);
		btnView.addActionListener(view);
		btnUpdate.addActionListener(update);
		btnDelete.addActionListener(delete);

		c.add(btnAdd);
		c.add(btnView);
		c.add(btnUpdate);
		c.add(btnDelete);	
		
		setVisible(true);
		setSize(360, 355);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Home");
	}
}