
//import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class Home_Page extends JFrame {
	static JFrame home_page;
	static JFrame go_to_new_client_profile;
	static JFrame go_to_view_list_of_clients;
	static JFrame go_to_new_client_visit;
	static JFrame client_history_frame;
	static JFrame update_profile;

	public Home_Page() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		
		setTitle("Hair with a Flair. Your Client-Based Management System | Home Page");
		
		JMenuBar menu_bar = new JMenuBar();
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

		setJMenuBar(menu_bar);
		
		
		
		
		JPanel panel_home_page = new JPanel();

		JButton create_a_new_client_profile = new JButton("Create a New Client Profile");
		JButton view_list_of_clients = new JButton("View List of Clients");

		create_a_new_client_profile.setPreferredSize(new Dimension(300, 75));
		view_list_of_clients.setPreferredSize(new Dimension(300, 75));

		create_a_new_client_profile.setAlignmentX(Component.CENTER_ALIGNMENT);
		view_list_of_clients.setAlignmentX(Component.CENTER_ALIGNMENT);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		panel_home_page.add(create_a_new_client_profile, gbc);
		panel_home_page.add(view_list_of_clients, gbc);

		panel_home_page.setBackground(Color.decode("#660033"));

		add(panel_home_page);
		getContentPane().setBackground(Color.decode("#660033"));

		// setting the button colors
		create_a_new_client_profile.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		view_list_of_clients.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

		create_a_new_client_profile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				go_to_new_client_profile = new New_Client_Profile();
				go_to_new_client_profile.setVisible(true);
				home_page.dispose();
			}
		});

		view_list_of_clients.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				go_to_view_list_of_clients = new View_List_Of_Clients();
				go_to_view_list_of_clients.setVisible(true);
				home_page.dispose();

			}
		});
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) screenSize.getHeight();
		int width = (int) screenSize.getWidth();
		pack();
		setResizable(false);
		setSize(width, height - 80);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		home_page = new Home_Page();
		home_page.setVisible(true);
		try {
			ConnectionHandler.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
