import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Client_History extends JFrame {
	Statement statement = null;
	DefaultTableModel model = null;
	JTable table = null;
	Connection connection1 = null;
	static String first_name;
	static String last_name;
	static String stylist_name;
	static int id;

	public Client_History() {
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

		JMenuItem add_new_client_visit = new JMenuItem("Add New Client Visit");

		menu_bar.add(Options);

		Options.add(add_new_client_visit);

		setJMenuBar(menu_bar);
		JPanel list_of_visits = new JPanel();

		try {
			draw_Table();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		add_new_client_visit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Home_Page.go_to_new_client_profile = new New_Client_Profile();
				Home_Page.go_to_new_client_profile.setVisible(true);
			}
		});

		table.setRowHeight(30);
		JScrollPane scroll_pane = new JScrollPane(table);
		scroll_pane.setPreferredSize(new Dimension(700, 700));
		add(list_of_visits);
		list_of_visits.add(scroll_pane, BorderLayout.CENTER);
		pack();
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	private void draw_Table() throws ClassNotFoundException {
		Statement statement_draw_table = null;
		try {
			model = new DefaultTableModel(new String[] { "Stylist" , "Hairstyle" , "Haircut" , "Products" , "Formula", "Notes", "Other" }, 0) {
				public boolean isCellEditable(int rowIndex, int mColIndex) {
					return false;
				}
			};

			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					System.exit(0);
				}
			});

			statement_draw_table = ConnectionHandler.connection.createStatement();
			ResultSet rs = statement_draw_table.executeQuery(
					"SELECT Stylist, Hairstyle, Haircut, Products, Formula, Notes, Other FROM table"
							+ View_List_Of_Clients.get_id() + " ORDER BY Last,First");

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
