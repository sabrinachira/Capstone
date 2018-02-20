import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CreateNewVisit extends JFrame {
	static JFrame CreateNewVisit;
	public static ConnectionHandler connection_to_database;

	public CreateNewVisit() {
		setTitle("Hair with a Flair. Your Client-Based Management System.");

		JPanel panel_new_visit = new JPanel();

		JLabel stylist_prompt = new JLabel("Stylist: ");
		JTextField stylist_input = new JTextField(20);
		JLabel hairstyle_prompt = new JLabel("Hairstyle: ");
		JTextField hairstyle_input = new JTextField(20);
		JLabel haircut_length_prompt = new JLabel("Haircut Length: ");
		JTextField haircut_length_input = new JTextField(20);
		JLabel products_purchased_prompt = new JLabel("Products Purchased: ");
		JTextField products_purchased_input = new JTextField(20);
		JLabel formula_prompt = new JLabel("Formula: ");
		JTextField formula_input = new JTextField(20);
		JLabel notes_and_preferences_prompt = new JLabel("Notes & Preferences: ");
		JTextField notes_and_preferences_input = new JTextField(20);
		JLabel other_prompt = new JLabel("Other: ");
		JTextField other_input = new JTextField(20);

		// display user profile at the top.

		panel_new_visit.add(stylist_prompt);
		panel_new_visit.add(stylist_input);
		panel_new_visit.add(hairstyle_prompt);
		panel_new_visit.add(hairstyle_input);
		panel_new_visit.add(haircut_length_prompt);
		panel_new_visit.add(haircut_length_input);
		panel_new_visit.add(products_purchased_prompt);
		panel_new_visit.add(products_purchased_input);
		panel_new_visit.add(formula_prompt);
		panel_new_visit.add(formula_input);
		panel_new_visit.add(notes_and_preferences_prompt);
		panel_new_visit.add(notes_and_preferences_input);
		panel_new_visit.add(other_prompt);
		panel_new_visit.add(other_input);

		add(panel_new_visit);
		pack();
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		CreateNewVisit = new CreateNewVisit();
		CreateNewVisit.setVisible(true);
		try {
			ConnectionHandler.connect();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
