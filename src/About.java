import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class About extends JFrame {
	Statement statement = null;
	DefaultTableModel model = null;
	JTable table = null;
	Connection connection1 = null;

	public About() throws ClassNotFoundException {
		try {
			ConnectionHandler.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) screenSize.getHeight();
		int width = (int) screenSize.getWidth();

		setTitle("Hair with a Flair. Your Client-Based Management System | About");

		JPanel middlePanel = new JPanel();
		String title = "About";
		Font font = new Font("Bookman Old Style", Font.PLAIN, 20);
		TitledBorder border = new TitledBorder(new LineBorder(new Color(255, 255, 255)));
		border.setTitleFont(border.getTitleFont().deriveFont(Font.BOLD));
		border.setTitle(title);
		border.setTitleColor(Color.white);
		border.setTitleFont(font);
		middlePanel.setBorder(border);

		JTextArea display = new JTextArea(width, height);
		display = new JTextArea(
				"Welcome to your new client based management system!\n\n\nHair With a Flair allows hairstylists to keep track of their clients' visits and personal information.\n\nThis allows them to be able to stay in contact with clients, as well as keep track of what was performed on the clients' hair during each visit.\n\nHairstylists have the option to add hairstyles, haircuts, formulas, products purchased by the client, and notes & preferences specific to each client and visit.");
		display.setFont(font);
		display.setEditable(false); // set textArea non-editable
		display.setForeground(Color.white);
		display.setLineWrap(true);
		display.setEditable(false);
		JScrollPane scroll = new JScrollPane(display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		display.setBackground(Color.decode("#660033"));

		middlePanel.setPreferredSize(new Dimension((width), (height)));
		display.setPreferredSize(new Dimension((width), (height-200)));
		scroll.setPreferredSize(new Dimension((width-200), (height-200)));
		middlePanel.add(scroll);

		add(middlePanel);
		middlePanel.setBackground(Color.decode("#5b002d"));
		getContentPane().setBackground(Color.decode("#5b002d"));
		pack();
		setResizable(false);
		setSize(width, height - 80);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

}
