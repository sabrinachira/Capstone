import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class How_To extends JFrame {

	public How_To() throws ClassNotFoundException {
		try {
			ConnectionHandler.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) screenSize.getHeight();
		int width = (int) screenSize.getWidth();

		setTitle("Hair with a Flair. Your Client-Based Management System | How To");
		// JPanel how_to_panel = new JPanel();

		JPanel middlePanel = new JPanel();
		String title = "Help | How To";
		Font font = new Font("Bookman Old Style", Font.PLAIN, 20);
		TitledBorder border = new TitledBorder(new LineBorder(new Color(255, 255, 255)));
		border.setTitleFont(border.getTitleFont().deriveFont(Font.BOLD));
		border.setTitle(title);
		border.setTitleColor(Color.white);
		border.setTitleFont(font);
		middlePanel.setBorder(border);

		JTextArea display = new JTextArea(width, height);
		display = new JTextArea(
				"Welcome to your new client based management system!\n\n\n\n\u2022 To delete the whole client database (this cannot be undone):\n\n          1) Select 'Options' in the top left corner\n\n          2) Hover over 'Delete'\n\n          3) Select 'Whole Table'\n\n\u2022 To add a new client profile:\n\n          1) Select 'Options' in the top left corner\n\n          2) Select 'Add New Client Profile'\n\n\u2022 To add a new visit for a client:\n\n          1) Double click the client in the client table on the View List of Clients page\n\n          2) Then select 'New Visit'\n\n\u2022 To view the list of clients from pages other than the client list page:\n\n          1) Select 'Options' in the top left corner\n\n          2) Then select 'View List of Clients'\n\n\u2022 To edit a client's profile:\n\n          1) Double click the client in the client table on the View List of Clients page\n\n          2) Then select 'Edit Profile'\n\n\u2022 To view a client's visit history:\n\n          1) Double click the client in the client table on the View List of Clients page\n\n          2) Then select 'View Visit History'\n\n\u2022 To view a client's specific visit:\n\n          1) Double click the client in the client table on the View List of Clients page\n\n          2) Then select 'View Visit History'\n\n          3) Then double click the visit you would like to view");
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
		display.setPreferredSize(new Dimension((width), (height+160)));
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
