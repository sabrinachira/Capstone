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
public class Create_New_Visit extends JFrame {

	public Create_New_Visit() throws ClassNotFoundException {
		try {
			ConnectionHandler.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("Hair with a Flair. Your Client-Based Management System.");
		Box vertical_box = Box.createVerticalBox();
		Box vertical_box2 = Box.createVerticalBox();
		Box buttons_vertical_box = Box.createVerticalBox();
		Box vertical_box_horizontal = Box.createHorizontalBox();

		// from New_Client_Profile
		Box first_horizontal_box = Box.createHorizontalBox();
		Box last_horizontal_box = Box.createHorizontalBox();
		Box phone_horizontal_box = Box.createHorizontalBox();
		Box address_horizontal_box = Box.createHorizontalBox();
		Box email_horizontal_box = Box.createHorizontalBox();

		JLabel first_name_prompt = new JLabel("First Name: ");
		JTextField first_name_input = new JTextField(20);
		first_name_input.setText(View_List_Of_Clients.get_first_name());
		JLabel last_name_prompt = new JLabel("Last Name: ");
		JTextField last_name_input = new JTextField(20);
		last_name_input.setText(View_List_Of_Clients.get_last_name());
		JLabel phone_number_prompt = new JLabel("Phone Number: ");
		JTextField phone_number_input = new JTextField(20);
		phone_number_input.setText(ConnectionHandler.select_info("clients", "Phone"));
		JLabel address_prompt = new JLabel("Address: ");
		JTextField address_input = new JTextField(20);
		address_input.setText(ConnectionHandler.select_info("clients", "Address"));
		JLabel email_prompt = new JLabel("Email: ");
		JTextField email_input = new JTextField(20);
		email_input.setText(ConnectionHandler.select_info("clients", "Email"));

		first_horizontal_box.add(first_name_prompt);
		first_horizontal_box.add(first_name_input);
		last_horizontal_box.add(last_name_prompt);
		last_horizontal_box.add(last_name_input);
		phone_horizontal_box.add(phone_number_prompt);
		phone_horizontal_box.add(phone_number_input);
		address_horizontal_box.add(address_prompt);
		address_horizontal_box.add(address_input);
		email_horizontal_box.add(email_prompt);
		email_horizontal_box.add(email_input);

		vertical_box.add(first_horizontal_box);
		vertical_box.add(last_horizontal_box);
		vertical_box.add(phone_horizontal_box);
		vertical_box.add(address_horizontal_box);
		vertical_box.add(email_horizontal_box);
		// for new visit
		Box stylist_horizontal_box = Box.createHorizontalBox();
		Box hairstyle_horizontal_box = Box.createHorizontalBox();
		Box haircut_horizontal_box = Box.createHorizontalBox();
		Box products_horizontal_box = Box.createHorizontalBox();
		Box formula_horizontal_box = Box.createHorizontalBox();
		Box buttons_horizontal_box = Box.createHorizontalBox();
		Box notes_and_preferences_horizontal_box = Box.createHorizontalBox();
		Box other_horizontal_box = Box.createHorizontalBox();

		JPanel panel_new_visit = new JPanel();

		JLabel stylist_prompt = new JLabel("Stylist: ");
		JTextField stylist_input = new JTextField(20);
		stylist_input.setText(ConnectionHandler.select_info("clients", "Stylist"));
		JLabel hairstyle_prompt = new JLabel("Hairstyle: ");
		JTextField hairstyle_input = new JTextField(20);
		JLabel haircut_prompt = new JLabel("Haircut Length: ");
		JTextField haircut_input = new JTextField(20);
		JLabel products_purchased_prompt = new JLabel("Products Purchased: ");
		JTextField products_purchased_input = new JTextField(20);
		JLabel formula_prompt = new JLabel("Formula: ");
		JTextField formula_input = new JTextField(20);
		JLabel notes_and_preferences_prompt = new JLabel("Notes & Preferences: ");
		JTextField notes_and_preferences_input = new JTextField(20);
		JLabel other_prompt = new JLabel("Other: ");
		JTextField other_input = new JTextField(20);
		JButton save_profile = new JButton("SAVE");
		JButton cancel = new JButton("CANCEL");

		stylist_horizontal_box.add(stylist_prompt);
		stylist_horizontal_box.add(stylist_input);
		hairstyle_horizontal_box.add(hairstyle_prompt);
		hairstyle_horizontal_box.add(hairstyle_input);
		haircut_horizontal_box.add(haircut_prompt);
		haircut_horizontal_box.add(haircut_input);
		products_horizontal_box.add(products_purchased_prompt);
		products_horizontal_box.add(products_purchased_input);
		formula_horizontal_box.add(formula_prompt);
		formula_horizontal_box.add(formula_input);
		notes_and_preferences_horizontal_box.add(notes_and_preferences_prompt);
		notes_and_preferences_horizontal_box.add(notes_and_preferences_input);
		other_horizontal_box.add(other_prompt);
		other_horizontal_box.add(other_input);

		buttons_horizontal_box.add(save_profile);
		buttons_horizontal_box.add(cancel);
		buttons_vertical_box.add(buttons_horizontal_box);

		vertical_box2.add(stylist_horizontal_box);
		vertical_box2.add(hairstyle_horizontal_box);
		vertical_box2.add(haircut_horizontal_box);
		vertical_box2.add(products_horizontal_box);
		vertical_box2.add(formula_horizontal_box);
		vertical_box2.add(notes_and_preferences_horizontal_box);
		vertical_box2.add(other_horizontal_box);

		vertical_box_horizontal.add(vertical_box);
		vertical_box_horizontal.add(vertical_box2);

		panel_new_visit.add(vertical_box_horizontal);
		panel_new_visit.add(buttons_vertical_box);

		// display user profile at the top.

		save_profile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to save the creation of a new client visit?", "Save?",
						JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					try {
						String hairstyle = hairstyle_input.getText();
						String haircut = haircut_input.getText();
						String products_purchased = products_purchased_input.getText();
						String formula = formula_input.getText();
						String notes_and_preferences = notes_and_preferences_input.getText();
						String other = other_input.getText();

						// verify that there isn't a person with that name
						// already and address already
						System.out.println("before adding");
						ConnectionHandler.add_visit_to_client_table(hairstyle, haircut, products_purchased, formula,
								notes_and_preferences, other);
						System.out.println("after adding");

						Home_Page.client_history_frame = new Client_History();
						Home_Page.client_history_frame.setVisible(true);
						// New_Client_Profile.dispose();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
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
					Home_Page.go_to_new_client_visit.dispose();
				} else {
				}
			}
		});

		add(panel_new_visit);
		pack();
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
