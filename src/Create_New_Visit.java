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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Create_New_Visit extends JFrame {
	SimpleDateFormat simple_date;

	public Create_New_Visit() throws ClassNotFoundException {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));

		try {
			ConnectionHandler.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		setTitle("Hair with a Flair. Your Client-Based Management System | New Visit");
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

		Box vertical_box2 = Box.createVerticalBox();
		Box buttons_vertical_box = Box.createVerticalBox();
		Box vertical_box_horizontal = Box.createHorizontalBox();

		Font font = new Font("Bookman Old Style", Font.PLAIN, 20);

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
		stylist_input.setFont(font);
		JLabel hairstyle_prompt = new JLabel("Hairstyle: ");
		hairstyle_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		hairstyle_prompt.setForeground(Color.white);
		JTextField hairstyle_input = new JTextField(20);
		hairstyle_input.setFont(font);
		JLabel haircut_prompt = new JLabel("Haircut Length: ");
		haircut_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		haircut_prompt.setForeground(Color.white);
		JTextField haircut_input = new JTextField(20);
		haircut_input.setFont(font);
		JLabel products_purchased_prompt = new JLabel("Products Purchased: ");
		products_purchased_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		products_purchased_prompt.setForeground(Color.white);
		JTextField products_purchased_input = new JTextField(20);
		products_purchased_input.setFont(font);
		JLabel formula_prompt = new JLabel("Formula: ");
		formula_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		formula_prompt.setForeground(Color.white);
		JTextField formula_input = new JTextField(20);
		formula_input.setFont(font);
		JLabel notes_and_preferences_prompt = new JLabel("Notes & Preferences: ");
		notes_and_preferences_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		notes_and_preferences_prompt.setForeground(Color.white);
		JTextField notes_and_preferences_input = new JTextField(20);
		notes_and_preferences_input.setFont(font);
		JLabel other_prompt = new JLabel("Other: ");
		other_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		other_prompt.setForeground(Color.white);
		JTextField other_input = new JTextField(20);
		other_input.setFont(font);
		JButton save_profile = new JButton("SAVE");
		save_profile.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		JButton cancel = new JButton("CANCEL");
		cancel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

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
		buttons_horizontal_box.add(Box.createHorizontalStrut(20));
		buttons_horizontal_box.add(cancel);

		buttons_vertical_box.add(buttons_horizontal_box);

		vertical_box2.add(stylist_horizontal_box);
		vertical_box2.add(Box.createVerticalStrut(20));
		vertical_box2.add(hairstyle_horizontal_box);
		vertical_box2.add(Box.createVerticalStrut(20));
		vertical_box2.add(haircut_horizontal_box);
		vertical_box2.add(Box.createVerticalStrut(20));
		vertical_box2.add(products_horizontal_box);
		vertical_box2.add(Box.createVerticalStrut(20));
		vertical_box2.add(formula_horizontal_box);
		vertical_box2.add(Box.createVerticalStrut(20));
		vertical_box2.add(notes_and_preferences_horizontal_box);
		vertical_box2.add(Box.createVerticalStrut(20));
		vertical_box2.add(other_horizontal_box);

		vertical_box_horizontal.add(vertical_box2);

		Box final_vertical = Box.createVerticalBox();
		final_vertical.add(vertical_box_horizontal);
		final_vertical.add(Box.createVerticalStrut(20));
		final_vertical.add(buttons_horizontal_box);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		panel_new_visit.add(final_vertical, gbc);
		// panel_new_visit.add(buttons_vertical_box);

		// display user profile at the top.

		save_profile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel label = new JLabel("Are you sure you want to save the creation of a new client visit?");
				label.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
				int confirm = JOptionPane.showConfirmDialog(null, label, "Save?", JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					try {
						String stylist = stylist_input.getText().toUpperCase();
						String hairstyle = hairstyle_input.getText().toUpperCase();
						String haircut = haircut_input.getText().toUpperCase();
						String products_purchased = products_purchased_input.getText().toUpperCase();
						String formula = formula_input.getText().toUpperCase();
						String notes_and_preferences = notes_and_preferences_input.getText().toUpperCase();
						String other = other_input.getText().toUpperCase();

						stylist = stylist.replace("'", "`");
						hairstyle = hairstyle.replace("'", "`");
						haircut = haircut.replace("'", "`");
						products_purchased = products_purchased.replace("'", "`");
						formula = formula.replace("'", " ");
						notes_and_preferences = notes_and_preferences.replace("'", "`");
						other = other.replace("'", "`");

						Date date = new Date();
						simple_date = new SimpleDateFormat("MM-dd-yy | hh:mm:ss a");
						String visit_date = simple_date.format(date);
						// verify that there isn't a person with that name
						// already and address already
						ConnectionHandler.add_visit_to_history_table(stylist, hairstyle, haircut, products_purchased,
								formula, notes_and_preferences, other, visit_date);

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
				JLabel label = new JLabel("Are you sure you want to cancel the creation of a new client visit?");
				label.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
				int confirm = JOptionPane.showConfirmDialog(null, label, "Cancel?", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					Home_Page.go_to_new_client_visit.dispose();
					Home_Page.go_to_view_list_of_clients = new View_List_Of_Clients();
					Home_Page.go_to_view_list_of_clients.setVisible(true);
				} else {
				}
			}
		});

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) screenSize.getHeight();
		int width = (int) screenSize.getWidth();

		add(panel_new_visit);
		panel_new_visit.setBackground(Color.decode("#660033"));
		getContentPane().setBackground(Color.decode("#660033"));
		pack();
		setResizable(false);
		setSize(width, height - 80);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
