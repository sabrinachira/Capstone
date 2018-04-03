import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class View_List_Of_Clients extends JFrame {
	Statement statement = null;
	DefaultTableModel model = null;
	JTable table = null;
	Connection connection1 = null;
	static String first_name;
	static String last_name;
	static String stylist_name;
	static int id;

	public View_List_Of_Clients() {
		try {
			ConnectionHandler.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("Hair with a Flair. Your Client-Based Management System | Client List");
		JMenuBar menu_bar = new JMenuBar();
		JMenu Options = new JMenu("Options");
		Options.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

		table = new JTable();

		JMenuItem delete_whole_table = new JMenuItem("Delete Whole Table");
		delete_whole_table.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

		JMenuItem add_new_client = new JMenuItem("Add New Client Profile");
		add_new_client.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

		menu_bar.add(Options);

		Options.add(delete_whole_table);
		Options.add(add_new_client);

		setJMenuBar(menu_bar);
		JPanel list_of_clients = new JPanel();

		try {
			draw_Table();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}

		delete_whole_table.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel delete_label = new JLabel("Are you sure you want to delete the WHOLE client database?");
				delete_label.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
				JLabel sure = new JLabel("Are you sure?");
				sure.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

				int confirm = JOptionPane.showConfirmDialog(null, delete_label, "Delete the WHOLE client database?",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					int confirm2 = JOptionPane.showConfirmDialog(null, sure, "Delete the WHOLE client database?",
							JOptionPane.YES_NO_OPTION);
					if (confirm2 == JOptionPane.YES_OPTION) {
						try {
							ConnectionHandler.delete_whole_table();
							draw_Table();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						}
						try {
							draw_Table();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						}
					} else {
					}
				} else if (confirm == JOptionPane.NO_OPTION) {
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

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) screenSize.getHeight();
		int width = (int) screenSize.getWidth();

		table.setRowHeight(30);
		JScrollPane scroll_pane = new JScrollPane(table);
		scroll_pane.setPreferredSize(new Dimension(width, height - 50));
		add(list_of_clients);
		list_of_clients.setBackground(Color.decode("#660033"));
		add(scroll_pane, BorderLayout.CENTER);

		pack();
		setSize(width, height - 50);

		// setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private void draw_Table() throws ClassNotFoundException {
		Statement statement_draw_table = null;
		try {
			table.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

			ConnectionHandler.create_table("clients");
			model = new DefaultTableModel(new String[] { "ID", "Last Name", "First Name" }, 0) {
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
					JPanel panel = new JPanel();

					if (e.getClickCount() == 2) {						
						int row = table.rowAtPoint(e.getPoint());
						set_first_name(table.getValueAt(row, 2));
						set_last_name(table.getValueAt(row, 1));
						set_id(table.getValueAt(row, 0));
						try {
							ConnectionHandler.create_history_table();
						} catch (ClassNotFoundException e2) {
						}
						Object[] options = { "View Visit History", "Edit Profile", "Create a Visit" };
						JLabel label = new JLabel(
								"Select an option.");
						label.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

						panel.add(label);
						int result = JOptionPane.showOptionDialog(null, panel, "Client", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.PLAIN_MESSAGE, null, options, null);
						// view history
						if (result == JOptionPane.YES_OPTION) {
							Home_Page.client_history_frame = new Client_History();
							Home_Page.client_history_frame.setVisible(true);
							Home_Page.go_to_view_list_of_clients.setVisible(false);
						}
						//update profile
						else if(result == JOptionPane.NO_OPTION){
							try {
								Home_Page.update_profile = new Update_Profile();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Home_Page.update_profile.setVisible(true);
							Home_Page.go_to_view_list_of_clients.setVisible(false);
						}
						// create new visitF
						else if (result == JOptionPane.CANCEL_OPTION) {
							try {
								Home_Page.go_to_new_client_visit = new Create_New_Visit();
								Home_Page.go_to_new_client_visit.setVisible(true);
								Home_Page.go_to_view_list_of_clients.setVisible(false);
							} catch (ClassNotFoundException e1) {
								e1.printStackTrace();
							}
						} else {

						}

					}
				}
			});

			statement_draw_table = ConnectionHandler.connection.createStatement();
			ResultSet rs = statement_draw_table
					.executeQuery("SELECT id, Last, First FROM clients ORDER BY Last, First, id;");

			while (rs.next()) {
				int id = rs.getInt("ID");
				String last_name = rs.getString("Last");
				String first_name = rs.getString("First");
				model.addRow(new Object[] { id, last_name, first_name });
			}

			table.setModel(model);

		} catch (

		SQLException e) {
			System.err.println("error: " + e.getMessage());
		}
	}

	public void set_first_name(Object first) {
		first_name = (String) first;
	}

	public static String get_first_name() {
		return first_name;
	}

	public void set_last_name(Object last) {
		last_name = (String) last;
	}

	public static String get_last_name() {
		return last_name;
	}

	public void set_stylist_name(Object stylist) {
		stylist_name = (String) stylist;
	}

	public static String get_stylist_name() {
		return stylist_name;
	}

	public void set_id(Object id) {
		View_List_Of_Clients.id = (int) id;
	}

	public static int get_id() {
		return id;
	}
}
