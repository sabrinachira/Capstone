
//import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Home_Page extends JFrame {
	static JFrame home_page;
	
	public Home_Page() {
		setTitle("Hair with a Flair. Your Client-Based Management System.");

		JPanel panel_home_page = new JPanel();

		JButton create_a_new_client_visit = new JButton("Create a New Client Visit");
		JButton create_a_new_client_profile = new JButton("Create a New Client Profile");
		JButton view_list_of_clients = new JButton("View List of Clients");
		JButton view_list_of_recent_clients = new JButton("View List of Recent Clients");

		create_a_new_client_visit.setAlignmentX(Component.CENTER_ALIGNMENT);
		create_a_new_client_profile.setAlignmentX(Component.CENTER_ALIGNMENT);
		view_list_of_clients.setAlignmentX(Component.CENTER_ALIGNMENT);
		view_list_of_recent_clients.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel_home_page.add(create_a_new_client_visit);
		panel_home_page.add(create_a_new_client_profile);
		panel_home_page.add(view_list_of_clients);
		panel_home_page.add(view_list_of_recent_clients);

		add(panel_home_page);

		create_a_new_client_profile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				New_Client_Profile go_to_new_client_profile = new New_Client_Profile();
				go_to_new_client_profile.setVisible(true);
			}
		});
		create_a_new_client_visit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Select_Client_For_New_Visit select_a_client = new Select_Client_For_New_Visit();
				select_a_client.setVisible(true);
			}
		});
		view_list_of_clients.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				View_List_Of_Clients go_to_view_list_of_clients = new View_List_Of_Clients();
				go_to_view_list_of_clients.setVisible(true);
			}
		});
		view_list_of_recent_clients.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				View_List_Of_Recent_Clients go_to_view_list_of_recent_cleints = new View_List_Of_Recent_Clients();
				go_to_view_list_of_recent_cleints.setVisible(true);
			}
		});

		pack();
		setSize(900, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		home_page = new Home_Page();
		home_page.setVisible(true);
		//connection_to_database = new ConnectionHandler();
		//ConnectionHandler.connection = null;
		try {
			ConnectionHandler.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
