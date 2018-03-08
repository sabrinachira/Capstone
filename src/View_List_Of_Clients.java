import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class View_List_Of_Clients extends JFrame {
	View_List_Of_Clients View_List_Of_Clients;
	Statement statement = null;
	DefaultTableModel model = null;
	JTable table = null;
	Connection connection1 = null;

	public View_List_Of_Clients() {
		setTitle("Hair with a Flair. Your Client-Based Management System.");

		JMenuBar menu_bar = new JMenuBar();
		JMenu Options = new JMenu("Options");
		table = new JTable();
		JPopupMenu popup = new JPopupMenu();

		JMenuItem delete_whole_table = new JMenuItem("Delete Whole Table");
		JMenuItem add_new_client = new JMenuItem("Add New Client Profile");

		menu_bar.add(Options);

		Options.add(delete_whole_table);
		Options.add(add_new_client);

		setJMenuBar(menu_bar);
		JPanel list_of_clients = new JPanel();

		draw_Table();
		JMenuItem remove_selected_client = new JMenuItem("Delete client");

		// remove_selected_client.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0) {
		// int row = table.getSelectedRow();
		//
		// if (studentTableListener != null) {
		// studentTableListener.rowDeleted(row);
		// studentTableModel.fireTableRowsDeleted(row, row);
		// }
		// }
		// });

		popup.add(remove_selected_client);

		delete_whole_table.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete the WHOLE client database?",
						"Delete the WHOLE client database?", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					int confirm2 = JOptionPane.showConfirmDialog(null, "Are you sure?",
							"Delete the WHOLE client database?", JOptionPane.YES_NO_OPTION);
					if (confirm2 == JOptionPane.YES_OPTION) {
						try {
							ConnectionHandler.delete_whole_table();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						}
						draw_Table();
					} else {
					}
				} else {
				}

			}
		});

		add_new_client.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame New_Client_Profile = new New_Client_Profile();
				New_Client_Profile.setVisible(true);
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
		Statement statement_draw_table = null;
		try {
			model = new DefaultTableModel(new String[] { "ID", "Last Name", "First Name", "Stylist" }, 0) {
				// public boolean isCellEditable(int rowIndex, int mColIndex) {
				// return false;
				// }
			};

			statement_draw_table = ConnectionHandler.connection.createStatement();
			ResultSet rs = statement_draw_table
					.executeQuery("SELECT id, last, first, stylist FROM clients ORDER BY id, last, first;");

			while (rs.next()) {
				int id = rs.getInt("ID");
				String last_name = rs.getString("Last");
				String first_name = rs.getString("First");
				String stylist = rs.getString("Stylist");
				model.addRow(new Object[] { id, last_name, first_name, stylist });
			}

			table.setModel(model);

		} catch (SQLException e) {
			System.err.println("error: " + e.getMessage());
		}
	}

	public void main(String[] args) {
		View_List_Of_Clients = new View_List_Of_Clients();
		View_List_Of_Clients.setVisible(true);
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
