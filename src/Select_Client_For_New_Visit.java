import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Select_Client_For_New_Visit extends JFrame {
	Select_Client_For_New_Visit Select_Client_For_New_Visit;
	int row_id;
	Statement statement = null;
	DefaultTableModel model = null;
	JTable table = null;
	public Select_Client_For_New_Visit() {
		setTitle("Hair with a Flair. Your Client-Based Management System.");

		JMenuBar menu_bar = new JMenuBar();
		JMenu new_client = new JMenu("New Client");
		table = new JTable();
		
		JPanel list_of_clients = new JPanel();

		JPanel panel_select_client_for_new_visit = new JPanel();
		draw_Table();

		// display the table. turn a database into a Jtable
		
		// ************************************************************************************//
		// allow user to select user from list of clients
		// the click saves the database reference and carries it to the
		// CreateNewVisit page

		add(panel_select_client_for_new_visit);
		panel_select_client_for_new_visit.add(new JScrollPane(table), BorderLayout.CENTER);
		pack();
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}


	private void draw_Table() {
		try {
			Statement statement_draw_table = null;
			model = new DefaultTableModel(new String[] { "ID", "Last Name", "First Name", "Stylist" }, 0) {
				public boolean isCellEditable(int rowIndex, int mColIndex) {
					return false;
				}
			};
			statement_draw_table = ConnectionHandler.connection.createStatement();
			ResultSet rs = statement_draw_table
					.executeQuery("SELECT id, last, first, stylist FROM clients ORDER BY id, last, first;");

			while (rs.next()) {
				String last_name = rs.getString("Last");
				String first_name = rs.getString("First");
				String stylist = rs.getString("Stylist");
				model.addRow(new Object[] { last_name, first_name, stylist });
			}
			table.setModel(model);
		} catch (SQLException e) {
			System.err.println("error: " + e.getMessage());
		}
	}
	
	public void main(String[] args) {
		Select_Client_For_New_Visit = new Select_Client_For_New_Visit();
		Select_Client_For_New_Visit.setVisible(true);
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
