import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NewClientProfile extends JFrame {
	public static ConnectionHandler connection_to_database;
	static JFrame NewClientProfile;
	static Connection connection;
	static Statement statement;

	public NewClientProfile() {
		setTitle("Hair with a Flair. Your Client-Based Management System.");

		JPanel panel_new_client_profile = new JPanel();

		JLabel first_name_prompt = new JLabel("First Name: ");
		JTextField first_name_input = new JTextField(20);
		JLabel last_name_prompt = new JLabel("Last Name: ");
		JTextField last_name_input = new JTextField(20);
		JLabel phone_number_prompt = new JLabel("Phone Number: ");
		JTextField phone_number_input = new JTextField(20);
		JLabel address_prompt = new JLabel("Address: ");
		JTextField address_input = new JTextField(20);
		JLabel birthday_prompt = new JLabel("Birthday: ");
		JTextField birthday_input = new JTextField(20);
		JLabel email_prompt = new JLabel("Email: ");
		JTextField email_input = new JTextField(20);

		JButton save_profile = new JButton("SAVE");
		JButton cancel = new JButton("CANCEL");
		panel_new_client_profile.add(first_name_prompt);
		panel_new_client_profile.add(first_name_input);
		panel_new_client_profile.add(last_name_prompt);
		panel_new_client_profile.add(last_name_input);
		panel_new_client_profile.add(phone_number_prompt);
		panel_new_client_profile.add(phone_number_input);
		panel_new_client_profile.add(address_prompt);
		panel_new_client_profile.add(address_input);
		panel_new_client_profile.add(birthday_prompt);
		panel_new_client_profile.add(birthday_input);
		panel_new_client_profile.add(email_prompt);
		panel_new_client_profile.add(email_input);

		panel_new_client_profile.add(save_profile);
		panel_new_client_profile.add(cancel);

		add(panel_new_client_profile);

		String first_name = first_name_input.getText();
		String last_name = last_name_input.getText();
		String phone_number= phone_number_input.getText();;
		String address =  address_input.getText();;
		String birthday =  birthday_input.getText();;
		String email = email_input.getText();;
		
		
		
		save_profile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to save the creation of a new client profile?", "Save?",
						JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					try {
						ConnectionHandler.add_client_profile_to_database(first_name, last_name,
								phone_number, address, birthday, email);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// yes. saves to database, says it was saved, and returns to
					// home page.
				} else {
					HomePage return_to_home_page = new HomePage();
					NewClientProfile.dispose();
					return_to_home_page.setVisible(true);
				}
			}
		});
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to cancel the creation of a new client profile?", "Cancel?",
						JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					HomePage return_to_home_page = new HomePage();
					return_to_home_page.setVisible(true);
					NewClientProfile.dispose();

				} else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});

		pack();
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		NewClientProfile = new NewClientProfile();
		NewClientProfile.setVisible(true);
		connection_to_database = new ConnectionHandler();
		connection = null;
		try {
			statement = connection.createStatement();
			connection_to_database.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
