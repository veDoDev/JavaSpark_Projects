import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class QuoteGeneratorApp extends JFrame
{
	private Container c;
	private JButton btnCodingQts, btnHappyQts, btnConfidenceQts, btnWorkQts;
	private JTextArea taDisplayQts;
	private  int btnWidth = 600;
	private  int btnHeight = 100;
	private  int gap = 100;


	public QuoteGeneratorApp()
	{
		//				**make the container**

		c = getContentPane();
		c.setLayout(null);

		// 			    ** Instantiate the elements **

		btnCodingQts = new JButton("Coding Quotes");
		btnHappyQts = new JButton("Happy Quotes");
		btnConfidenceQts = new JButton("Confidence Quotes");
		btnWorkQts = new JButton("Work Quotes");
		taDisplayQts = new JTextArea(5,20);

		// 				**Set the fonts**

		Font f = new Font("Arial", Font.BOLD, 50);
		btnCodingQts.setFont(f);
		btnHappyQts.setFont(f);
		btnConfidenceQts.setFont(f);
		btnWorkQts.setFont(f);
		taDisplayQts.setLineWrap(true);
		taDisplayQts.setWrapStyleWord(true);
		taDisplayQts.setFont(new Font("Serif", Font.PLAIN, 50));

		// 		** Set margins and positions to elements (placing) **

		btnCodingQts.setBounds(100, 50, btnWidth, btnHeight);
		btnHappyQts.setBounds(800, 50, btnWidth, btnHeight);

		btnConfidenceQts.setBounds(100, 200, btnWidth, btnHeight);
		btnWorkQts.setBounds(800, 200, btnWidth, btnHeight);

		taDisplayQts.setBounds(300, 350, 900, 380); 

		// 			** Add elements to container **

		c.add(btnCodingQts);
		c.add(btnHappyQts);
		c.add(btnConfidenceQts);
		c.add(btnWorkQts);
		c.add(taDisplayQts);

		//			** Adding Button Action Listener **
		ActionListener a = (ae) -> {

			//				** Extracting the category **
			String category;

			Object src = ae.getSource();
			if(src == btnCodingQts)			category = "coding";
			else if(src == btnHappyQts)		category = "happy";
			else if(src == btnConfidenceQts)	category = "happy";
			else 					category = "work";

			try{
				//loading the driver
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				//get the url
				String url = "jdbc:mysql://localhost:3306/MotivationalQuotes13Jul25";
				//establish connection
				Connection con = DriverManager.getConnection(url, "root", "RootPassword@123");
				
				// construct the SQL query
				String sql = "select * from quotes where category = ? order by rand() limit 1";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, category);		// placing the positional args

				try (ResultSet rs = pst.executeQuery()) 
				{
					// A hypothtical check for a case where the output does not have any quotes
            				if (rs.next()) 
					{
                				String q = rs.getString("quote");
                				taDisplayQts.setText(q);
            				} 
					else 
					{
                				taDisplayQts.setText("No quotes found for " + category);
            				}
        			}


			}catch(SQLException e){
				JOptionPane.showMessageDialog(c, "Issue : " + e);
			}
		};

		//			** Button-binding with Action Listeners **
		btnCodingQts.addActionListener(a);
		btnHappyQts.addActionListener(a);
		btnConfidenceQts.addActionListener(a);
		btnWorkQts.addActionListener(a);
		


		// 			** Set Container settings **

		setTitle("Quote Generator App");
		setSize(1500,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}


class QuoteGenerator
{
	public static void main(String[] args)
	{
		QuoteGeneratorApp qouteGeneratorApp = new QuoteGeneratorApp();
	}
}