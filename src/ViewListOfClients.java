import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ViewListOfClients extends JFrame {
	public static ConnectionHandler connection_to_database;
	static JFrame ViewListOfClients;

	public ViewListOfClients() {
		JMenuBar menu_bar = new JMenuBar();
		JMenu new_client = new JMenu("New Client");
		JTable table = new JTable();
		menu_bar.add(new_client);
		setJMenuBar(menu_bar);
		JPanel list_of_clients = new JPanel();
		ConnectionHandler.statement = null;
		// display the table. turn a database into a Jtable
		try {
			DefaultTableModel model = new DefaultTableModel(new String[] { "Last Name", "First Name", "Stylist" }, 0);
			ConnectionHandler.statement = ConnectionHandler.connection.createStatement();
			ResultSet rs = ConnectionHandler.statement.executeQuery("SELECT last, first, stylist FROM clients");

			while (rs.next()) {
				String last_name = rs.getString("Last");
				String first_name = rs.getString("First");
				// String last_visit = rs.getString("dueDate");
				String stylist = rs.getString("Stylist");
				model.addRow(new Object[] { last_name, first_name, stylist });
			}
			table.setModel(model);
		} catch (SQLException e) {
			System.err.println("error: " + e.getMessage());
		}
		add(list_of_clients);
		list_of_clients.add(new JScrollPane(table), BorderLayout.CENTER);
		pack();
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		ViewListOfClients = new ViewListOfClients();
		ViewListOfClients.setVisible(true);
		try {
			ConnectionHandler.connect();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ConnectionHandler.end_connection();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
