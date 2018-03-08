import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class View_List_Of_Recent_Clients extends JFrame {
	View_List_Of_Recent_Clients View_List_Of_Recent_Clients;
	Statement statement = null;
	DefaultTableModel model = null;
	JTable table = null;

	public View_List_Of_Recent_Clients() {
		setTitle("Hair with a Flair. Your Client-Based Management System.");
		JMenuBar menu_bar = new JMenuBar();
		JMenu Options = new JMenu("Options");
		table = new JTable();
		JMenuItem delete_whole_table = new JMenuItem("Delete Whole Table");
		JMenuItem add_new_client = new JMenuItem("Add New Client Profile");

		menu_bar.add(Options);

		Options.add(delete_whole_table);
		Options.add(add_new_client);

		setJMenuBar(menu_bar);
		JPanel list_of_clients = new JPanel();

		// ConnectionHandler.statement = null;
		// display the table. turn a database into a Jtable

		draw_Table();

		delete_whole_table.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ConnectionHandler.delete_whole_table();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				draw_Table();
			}
		});

		add_new_client.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame NewClientProfile = new New_Client_Profile();
				NewClientProfile.setVisible(true);
				View_List_Of_Recent_Clients.dispose();
			}
		});

		add(list_of_clients);
		list_of_clients.add(new JScrollPane(table), BorderLayout.CENTER);
		pack();
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private void draw_Table() {
		try {
			model = new DefaultTableModel(new String[] { "Last Name", "First Name", "Stylist" }, 0);
			statement = ConnectionHandler.connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT last, first, stylist, FROM clients ORDER BY date;");

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
		View_List_Of_Recent_Clients = new View_List_Of_Recent_Clients();
		View_List_Of_Recent_Clients.setVisible(true);
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
