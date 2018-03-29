import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Create_New_Visit extends JFrame {
	SimpleDateFormat simple_date;

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
		first_name_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		first_name_prompt.setForeground(Color.white);
		JTextField first_name_input = new JTextField(20);
		first_name_input.setText(View_List_Of_Clients.get_first_name());
		JLabel last_name_prompt = new JLabel("Last Name: ");
		last_name_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		last_name_prompt.setForeground(Color.white);
		JTextField last_name_input = new JTextField(20);
		last_name_input.setText(View_List_Of_Clients.get_last_name());
		JLabel phone_number_prompt = new JLabel("Phone Number: ");
		phone_number_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		phone_number_prompt.setForeground(Color.white);
		JTextField phone_number_input = new JTextField(20);
		phone_number_input.setText(ConnectionHandler.select_info("clients", "Phone"));
		JLabel address_prompt = new JLabel("Address: ");
		address_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		address_prompt.setForeground(Color.white);
		JTextField address_input = new JTextField(20);
		address_input.setText(ConnectionHandler.select_info("clients", "Address"));
		JLabel email_prompt = new JLabel("Email: ");
		email_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		email_prompt.setForeground(Color.white);
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
		stylist_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		stylist_prompt.setForeground(Color.white);
		JTextField stylist_input = new JTextField(20);
		stylist_input.setText(ConnectionHandler.select_info("clients", "Stylist"));
		JLabel hairstyle_prompt = new JLabel("Hairstyle: ");
		hairstyle_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		hairstyle_prompt.setForeground(Color.white);
		JTextField hairstyle_input = new JTextField(20);
		JLabel haircut_prompt = new JLabel("Haircut Length: ");
		haircut_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		haircut_prompt.setForeground(Color.white);
		JTextField haircut_input = new JTextField(20);
		JLabel products_purchased_prompt = new JLabel("Products Purchased: ");
		products_purchased_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		products_purchased_prompt.setForeground(Color.white);
		JTextField products_purchased_input = new JTextField(20);
		JLabel formula_prompt = new JLabel("Formula: ");
		formula_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		formula_prompt.setForeground(Color.white);
		JTextField formula_input = new JTextField(20);
		JLabel notes_and_preferences_prompt = new JLabel("Notes & Preferences: ");
		notes_and_preferences_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		notes_and_preferences_prompt.setForeground(Color.white);
		JTextField notes_and_preferences_input = new JTextField(20);
		JLabel other_prompt = new JLabel("Other: ");
		other_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		other_prompt.setForeground(Color.white);
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
		
		Box final_vertical = Box.createVerticalBox();
		final_vertical.add(vertical_box_horizontal);
		final_vertical.add(buttons_horizontal_box);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		panel_new_visit.add(final_vertical,gbc);
		//panel_new_visit.add(buttons_vertical_box);

		// display user profile at the top.

		save_profile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to save the creation of a new client visit?", "Save?",
						JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					try {
						String hairstyle = hairstyle_input.getText().toUpperCase();
						String haircut = haircut_input.getText().toUpperCase();
						String products_purchased = products_purchased_input.getText().toUpperCase();
						String formula = formula_input.getText().toUpperCase();
						String notes_and_preferences = notes_and_preferences_input.getText().toUpperCase();
						String other = other_input.getText().toUpperCase();

						Date date = new Date();
						simple_date = new SimpleDateFormat("MM-dd-yyyy | hh:mm:ss a");
						String visit_date = simple_date.format(date);
						// verify that there isn't a person with that name
						// already and address already
						ConnectionHandler.add_visit_to_client_table(hairstyle, haircut, products_purchased, formula,
								notes_and_preferences, other, visit_date);

						Home_Page.client_history_frame = new Client_History();
						Home_Page.client_history_frame.setVisible(true);
						Home_Page.go_to_new_client_visit.dispose();
						// New_Client_Profile.dispose();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else if (confirm == JOptionPane.NO_OPTION) {

				} else {
					Home_Page.client_history_frame = new Client_History();
					Home_Page.client_history_frame.setVisible(true);
					Home_Page.go_to_new_client_visit.dispose();
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

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) screenSize.getHeight() - 100;
		int width = (int) screenSize.getWidth() - 100;

		add(panel_new_visit);
		panel_new_visit.setBackground(Color.decode("#660033"));
		getContentPane().setBackground(Color.decode("#660033"));
		pack();
		setSize(width-800, height-300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
