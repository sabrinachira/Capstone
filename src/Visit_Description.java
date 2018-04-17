import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Visit_Description extends JFrame {
	Statement statement = null;
	DefaultTableModel model = null;
	JTable table = null;
	Connection connection1 = null;

	public Visit_Description() throws ClassNotFoundException {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));

		try {
			ConnectionHandler.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		setTitle("Hair with a Flair. Your Client-Based Management System | History Description");
		JMenuBar menu_bar = new JMenuBar();
		JMenu Options = new JMenu("Options");
		Options.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

		table = new JTable();

		JMenuItem view_client_list = new JMenuItem("View List of Clients");
		view_client_list.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

		menu_bar.add(Options);

		JMenuItem how_to = new JMenuItem("'How To' Help");
		how_to.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		JMenuItem about = new JMenuItem("About");
		about.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		
		JMenu help = new JMenu("Help");
		help.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		menu_bar.add(help);
		help.add(how_to);
		help.add(new JSeparator());
		help.add(new JSeparator());
		help.add(new JSeparator());
		help.add(about);
		setJMenuBar(menu_bar);
		how_to.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					String how_to_message = "\u2022To navigate around the system use the 'Options' button at the top left of the windows. If you select the 'x' button, the system will close. \n\u2022To delete the whole client database (this cannot be undone):\n          1) Select 'Options' in the top left corner\n          2) Hover over 'Delete'\n          3) Select 'Whole Table'\n\u2022 To delete a client (this cannot be undone):\n          1) Double click the client in the list of clients.\n          2) Select 'Delete Client' \n          3) There will be 2 prompts that will ask if you are sure you want to delete the client, as this cannot be undone. Click 'Yes' to both.\n\u2022 To add a new client profile:\n          1) Select 'Options' in the top left corner\n          2) Select 'Add New Client Profile'\n\u2022 To add a new visit for a client:\n          1) Double click the client in the client table on the View List of Clients page\n          2) Then select 'New Visit'\n\u2022 To view the list of clients from pages other than the client list page:\n          1) Select 'Options' in the top left corner\n          2) Then select 'View List of Clients'\n\u2022 To edit a client's profile:\n          1) Double click the client in the client table on the View List of Clients page\n          2) Then select 'Edit Profile'\n\u2022 To view a client's visit history:\n          1) Double click the client in the client table on the View List of Clients page\n          2) Then select 'View Visit History'\n\u2022 To view a client's specific visit:\n          1) Double click the client in the client table on the View List of Clients page\n          2) Then select 'View Visit History'\n          3) Then double click the visit you would like to view";
					JOptionPane.showMessageDialog(null, how_to_message, "How-To", JOptionPane.PLAIN_MESSAGE);
			}
		});

		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String about_message = "Welcome to your new client based management system!\nHair With a Flair allows hairstylists to keep track of their clients' visits and personal information.\nThis allows them to be able to stay in contact with clients, as well as keep track of what was performed on the clients' hair during each visit.\nHairstylists have the option to add hairstyles, haircuts, formulas, products purchased by the client, and notes & preferences specific to each client and visit.";
				JOptionPane.showMessageDialog(null, about_message, "About", JOptionPane.PLAIN_MESSAGE);
			}
		});

		Options.add(view_client_list);

		setJMenuBar(menu_bar);
		JPanel description = new JPanel();

		view_client_list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Home_Page.go_to_view_list_of_clients = new View_List_Of_Clients();
				Home_Page.go_to_view_list_of_clients.setVisible(true);
				Home_Page.client_history_frame.dispose();
			}
		});

		Box vertical_box = Box.createVerticalBox();

		// create the labels
		JLabel visit_date = new JLabel("Date: " + Client_History.get_date());
		visit_date.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		visit_date.setForeground(Color.white);
		JLabel visit_stylist = new JLabel("Stylist: " + Client_History.get_stylist());
		visit_stylist.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		visit_stylist.setForeground(Color.white);
		JLabel visit_hairstyle = new JLabel("Hairstyle: " + Client_History.get_hairstyle());
		visit_hairstyle.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		visit_hairstyle.setForeground(Color.white);
		JLabel visit_haircut = new JLabel("Haircut: " + Client_History.get_haircut());
		visit_haircut.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		visit_haircut.setForeground(Color.white);
		JLabel visit_products = new JLabel("Products: " + Client_History.get_products());
		visit_products.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		visit_products.setForeground(Color.white);
		JLabel visit_formula = new JLabel("Formula: " + Client_History.get_formula());
		visit_formula.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		visit_formula.setForeground(Color.white);
		JLabel visit_notes = new JLabel("Notes and Preferences: " + Client_History.get_notes());
		visit_notes.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		visit_notes.setForeground(Color.white);
		JLabel visit_other = new JLabel("Other: " + Client_History.get_other());
		visit_other.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		visit_other.setForeground(Color.white);

		vertical_box.add(visit_date);
		vertical_box.add(Box.createVerticalStrut(20));
		vertical_box.add(visit_stylist);
		vertical_box.add(Box.createVerticalStrut(20));
		vertical_box.add(visit_hairstyle);
		vertical_box.add(Box.createVerticalStrut(20));
		vertical_box.add(visit_haircut);
		vertical_box.add(Box.createVerticalStrut(20));
		vertical_box.add(visit_products);
		vertical_box.add(Box.createVerticalStrut(20));
		vertical_box.add(visit_formula);
		vertical_box.add(Box.createVerticalStrut(20));
		vertical_box.add(visit_notes);
		vertical_box.add(Box.createVerticalStrut(20));
		vertical_box.add(visit_other);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		description.add(vertical_box, gbc);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) screenSize.getHeight();
		int width = (int) screenSize.getWidth();

		add(description);
		description.setBackground(Color.decode("#660033"));
		getContentPane().setBackground(Color.decode("#660033"));
		pack();
		setResizable(false);
		setSize(width, height - 80);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

}
