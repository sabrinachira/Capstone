import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ViewListOfClients extends JFrame {
	public static ConnectionHandler connection_to_database;
	static JFrame ViewListOfClients;
	static Connection connection;
	static Statement statement;

	public ViewListOfClients() {
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
			ResultSet resultSet;
			resultSet = statement.executeQuery("SELECT * FROM clients");

			while (resultSet.next()) {
				String last_name = resultSet.getString("Last Name");
				String first_name = resultSet.getString("First Name");
				// String last_visit = resultSet.getString("dueDate");
				String stylist = resultSet.getString("stylist");
				model.addRow(new Object[] { last_name, first_name, stylist });
			}
			table.setModel(model);

		} catch (SQLException e) {
			System.out.println("view");
			System.err.println(e.getMessage());

		}
		add(list_of_clients);
		list_of_clients.add(table);
		// add(PANEL);
		pack();
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		ViewListOfClients = new ViewListOfClients();
		ViewListOfClients.setVisible(true);
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
