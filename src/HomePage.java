
//import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HomePage extends JFrame {
	public HomePage() {
		setTitle("Hair with a Flair. Your Client-Based Management System.");
		setLayout(new GridBagLayout());
		
		JPanel shown = new JPanel();
		
		JButton create_a_new_client_visit = new JButton("Create a New Client Visit");
		JButton create_a_new_client_profile = new JButton("Create a New Client Profile");
		JButton view_list_of_clients = new JButton("View List of Clients");
		JButton view_list_of_recent_clients = new JButton("View List of Recent Clients");
		
		create_a_new_client_visit.setAlignmentX(Component.CENTER_ALIGNMENT);
		create_a_new_client_profile.setAlignmentX(Component.CENTER_ALIGNMENT);
		view_list_of_clients.setAlignmentX(Component.CENTER_ALIGNMENT);
		view_list_of_recent_clients.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		shown.add(create_a_new_client_visit);
		shown.add(create_a_new_client_profile);
		shown.add(view_list_of_clients);
		shown.add(view_list_of_recent_clients);

		add(shown);

		create_a_new_client_profile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		view_list_of_clients.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		view_list_of_recent_clients.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		//setVisible(true);
		pack();
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		JFrame home_page = new HomePage();
		home_page.setVisible(true);
		// main_frame.setIconImage(new ImageIcon(imgURL).getImage());
	}
}
