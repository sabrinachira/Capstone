
//import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HomePage extends JFrame {
	public HomePage() {
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
				NewClientProfile go_to_new_client_profile = new NewClientProfile();
				go_to_new_client_profile.setVisible(true);
			}
		});
		create_a_new_client_visit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectClientForNewVisit go_to_new_client_visit = new SelectClientForNewVisit();
				go_to_new_client_visit.setVisible(true);
			}
		});
		view_list_of_clients.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewListOfClients go_to_view_list_of_clients = new ViewListOfClients();
				go_to_view_list_of_clients.setVisible(true);
			}
		});
		view_list_of_recent_clients.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewListOfRecentClients go_to_view_list_of_recent_cleints = new ViewListOfRecentClients();
				go_to_view_list_of_recent_cleints.setVisible(true);
			}
		});

		pack();
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		JFrame home_page = new HomePage();
		home_page.setVisible(true);
		// main_frame.setIconImage(new ImageIcon(imgURL).getImage());
	}
}
