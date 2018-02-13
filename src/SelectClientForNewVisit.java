import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class SelectClientForNewVisit extends JFrame {
	static JFrame SelectClientForNewVisit;
	public static ConnectionHandler connection_to_database;

	public SelectClientForNewVisit() {
		setTitle("Hair with a Flair. Your Client-Based Management System.");

		JPanel panel_select_client_for_new_visit = new JPanel();

		
		
		
		JMenuBar menu_bar = new JMenuBar();
		JMenu new_client = new JMenu("New Client");
		JTable table = new JTable();
		menu_bar.add(new_client);
		setJMenuBar(menu_bar);
		JPanel list_of_clients = new JPanel();

		// display the table. turn a database into a Jtable
		try {
			DefaultTableModel model = new DefaultTableModel(new String[] { "Last Name", "First Name", "Stylist" },
					5000);
			ResultSet resultSet = ConnectionHandler.statement.executeQuery("SELECT Last, First, Stylist FROM clients");

			while (resultSet.next()) {
				String last_name = resultSet.getString("Last");
				String first_name = resultSet.getString("First");
				// String last_visit = resultSet.getString("dueDate");
				String stylist = resultSet.getString("stylist");
				model.addRow(new Object[] { last_name, first_name, stylist });
			}
			table.setModel(model);

		} catch (SQLException e) {
			System.out.println("view");
			System.err.println(e.getMessage());

		}
		
		
		
		
		
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
