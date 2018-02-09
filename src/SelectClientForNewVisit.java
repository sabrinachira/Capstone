import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SelectClientForNewVisit extends JFrame {
	static JFrame SelectClientForNewVisit;
	public static ConnectionHandler connection_to_database;

	public SelectClientForNewVisit() {
		setTitle("Hair with a Flair. Your Client-Based Management System.");

		JPanel panel_select_client_for_new_visit = new JPanel();

		// ************************************************************************************//
		// allow user to select user from list of clients
		// the click saves the database reference and carries it to the
		// CreateNewVisit page

		add(panel_select_client_for_new_visit);
		pack();
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		SelectClientForNewVisit = new SelectClientForNewVisit();
		SelectClientForNewVisit.setVisible(true);
		connection_to_database = new ConnectionHandler();
		try {
			connection_to_database.connect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
