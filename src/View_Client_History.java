import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
public class View_Client_History extends JFrame {
	Statement statement = null;
	DefaultTableModel model = null;
	JTable table = null;
	Connection connection1 = null;

	public View_Client_History() {
		try {
			ConnectionHandler.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

		try {
			draw_main_table();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

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
							draw_main_table();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						}
						try {
							draw_main_table();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
					}
				} else {
				}
			}
		});

		add_new_client.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Home_Page.go_to_new_client_profile = new New_Client_Profile();
				Home_Page.go_to_new_client_profile.setVisible(true);
			}
		});

		table.setRowHeight(30);
		JScrollPane scroll_pane = new JScrollPane(table);
		scroll_pane.setPreferredSize(new Dimension(700, 700));
		add(list_of_clients);
		list_of_clients.add(scroll_pane, BorderLayout.CENTER);
		pack();
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private void draw_main_table() throws ClassNotFoundException {
		Statement statement_draw_table = null;
		try {
			ConnectionHandler.create_table("clients");
			model = new DefaultTableModel(new String[] { "ID", "Last Name", "First Name", "Stylist" }, 0) {
				public boolean isCellEditable(int rowIndex, int mColIndex) {
					return false;
				}
			};

			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					System.exit(0);
				}
			});

			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						Home_Page.client_history_frame = new Client_History();
						Home_Page.client_history_frame.setVisible(true);
					}
				}
			});

			statement_draw_table = ConnectionHandler.connection.createStatement();
			ResultSet rs = statement_draw_table
					.executeQuery("SELECT id, Last, First, Stylist FROM clients ORDER BY id, Last,First;");

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
}
