
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
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Home_Page extends JFrame {
	static JFrame home_page;
	static JFrame go_to_new_client_profile;
	static JFrame go_to_view_list_of_clients;
	static JFrame go_to_new_client_visit;
	static JFrame client_history_frame;
	static JFrame go_to_visit_description;
	static JFrame update_profile;
	static JFrame how_to;
	static JFrame about;

	public Home_Page() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		
		setTitle("Hair with a Flair. Your Client-Based Management System | Home Page");
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
