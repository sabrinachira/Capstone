import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
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
	int database_size_before_add;
	int database_size_after_add;
	static int which_method_to_go_into;

	public NewClientProfile() {
		which_method_to_go_into = 0;
		database_size_before_add = 0;
		database_size_after_add = 0;
		setTitle("Hair with a Flair. Your Client-Based Management System.");
		Box vertical_box = Box.createVerticalBox();
		Box first_horizontal_box = Box.createHorizontalBox();
		Box last_horizontal_box = Box.createHorizontalBox();
		Box phone_horizontal_box = Box.createHorizontalBox();
		Box address_horizontal_box = Box.createHorizontalBox();
		Box birthday_horizontal_box = Box.createHorizontalBox();
		Box email_horizontal_box = Box.createHorizontalBox();
		Box buttons_horizontal_box = Box.createHorizontalBox();

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

		first_horizontal_box.add(first_name_prompt);
		first_horizontal_box.add(first_name_input);
		last_horizontal_box.add(last_name_prompt);
		last_horizontal_box.add(last_name_input);
		phone_horizontal_box.add(phone_number_prompt);
		phone_horizontal_box.add(phone_number_input);
		address_horizontal_box.add(address_prompt);
		address_horizontal_box.add(address_input);
		birthday_horizontal_box.add(birthday_prompt);
		birthday_horizontal_box.add(birthday_input);
		email_horizontal_box.add(email_prompt);
		email_horizontal_box.add(email_input);
		buttons_horizontal_box.add(save_profile);
		buttons_horizontal_box.add(cancel);

		vertical_box.add(first_horizontal_box);
		vertical_box.add(last_horizontal_box);
		vertical_box.add(phone_horizontal_box);
		vertical_box.add(address_horizontal_box);
		vertical_box.add(birthday_horizontal_box);
		vertical_box.add(email_horizontal_box);
		vertical_box.add(buttons_horizontal_box);
		panel_new_client_profile.add(vertical_box);

		save_profile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to save the creation of a new client profile?", "Save?",
						JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					try {
						String first_name = first_name_input.getText();
						String last_name = last_name_input.getText();
						String phone_number = phone_number_input.getText();
						String address = address_input.getText();
						String birthday = birthday_input.getText();
						String email = email_input.getText();
						ConnectionHandler.add_client_profile_to_database(first_name, last_name, phone_number, address,
								birthday, email);
						which_method_to_go_into = 1;
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					which_method_to_go_into = 1;
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
					which_method_to_go_into = 1;
				} else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});

		add(panel_new_client_profile);
		pack();
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void dispose_current_open_new() {
		NewClientProfile.dispose();
		HomePage return_to_home_page = new HomePage();
		return_to_home_page.setVisible(true);
	}

	public static void main(String[] args) {
		NewClientProfile = new NewClientProfile();
		NewClientProfile.setVisible(true);
		if (which_method_to_go_into == 1) {
			dispose_current_open_new();
		}
		try {
			ConnectionHandler.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
