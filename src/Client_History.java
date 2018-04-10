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

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Client_History extends JFrame {
	Statement statement = null;
	DefaultTableModel model = null;
	JTable table = null;
	Connection connection1 = null;
	static String visit_stylist;
	static String visit_hairstyle;
	static String visit_haircut;
	static String visit_products;
	static String visit_formula;
	static String visit_notes;
	static String visit_other;
	static String visit_date;

	public Client_History() {
		try {
			ConnectionHandler.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		setTitle("Hair with a Flair. Your Client-Based Management System | Client History");
		JMenuBar menu_bar = new JMenuBar();
		JMenu Options = new JMenu("Options");
		Options.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		table = new JTable();

		JMenuItem add_new_client_visit = new JMenuItem("Add New Client Visit");
		add_new_client_visit.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		JMenuItem view_client_list = new JMenuItem("View List of Clients");
		view_client_list.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

		menu_bar.add(Options);

		Options.add(add_new_client_visit);
		Options.add(view_client_list);

		JMenuItem how_to = new JMenuItem("'How To' Help");
		how_to.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		JMenuItem about = new JMenuItem("About");
		about.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

		JMenu help = new JMenu("Help");
		help.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		
		menu_bar.add(help);
		help.add(how_to);
		help.add(new JSeparator());
		help.add(new JSeparator());
		help.add(new JSeparator());
		help.add(about);
		setJMenuBar(menu_bar);

		how_to.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Home_Page.how_to = new How_To();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				Home_Page.how_to.setVisible(true);

			}
		});

		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Home_Page.about = new About();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				Home_Page.about.setVisible(true);

			}
		});

		setJMenuBar(menu_bar);

		JPanel list_of_visits = new JPanel();
		String first = "";
		String last = "";
		String phone = "";
		String address = "";
		String email = "";

		try {
			Statement statement_draw_table = ConnectionHandler.connection.createStatement();
			ResultSet rs = statement_draw_table
					.executeQuery("SELECT First, Last, Phone, Address, Email FROM clients WHERE id = "
							+ View_List_Of_Clients.get_id());
			while (rs.next()) {
				first = rs.getString("First");
				last = rs.getString("Last");
				phone = rs.getString("Phone");
				address = rs.getString("Address");
				email = rs.getString("Email");
			}
		} catch (SQLException e3) {
			System.out.println("e3:");
			e3.printStackTrace();
		}

		JLabel name = new JLabel(first + " " + last);
		name.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		name.setForeground(Color.white);
		JLabel phone_info = new JLabel(phone);
		phone_info.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		phone_info.setForeground(Color.white);
		JLabel address_info = new JLabel(address);
		address_info.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		address_info.setForeground(Color.white);
		JLabel email_info = new JLabel(email);
		email_info.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		email_info.setForeground(Color.white);

		Box info_box = Box.createVerticalBox();
		info_box.add(name);
		info_box.add(phone_info);
		info_box.add(address_info);
		info_box.add(email_info);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) screenSize.getHeight();
		int width = (int) screenSize.getWidth();

		list_of_visits.add(info_box);

		try {
			draw_Table();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}

		add_new_client_visit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Home_Page.go_to_new_client_visit = new Create_New_Visit();
					Home_Page.go_to_new_client_visit.setVisible(true);
					Home_Page.client_history_frame.dispose();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});

		view_client_list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Home_Page.go_to_view_list_of_clients = new View_List_Of_Clients();
				Home_Page.go_to_view_list_of_clients.setVisible(true);
				Home_Page.client_history_frame.dispose();
			}
		});

		table.setRowHeight(30);
		JScrollPane scroll_pane = new JScrollPane(table);
		scroll_pane.setPreferredSize(new Dimension(width - 20, height - 400));

		add(list_of_visits);
		list_of_visits.setBackground(Color.decode("#660033"));
		list_of_visits.add(scroll_pane, BorderLayout.CENTER);
		pack();
		setResizable(false);
		setSize(width, height - 80);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	private void draw_Table() throws ClassNotFoundException {
		Statement statement_draw_table = null;
		try {
			table.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

			model = new DefaultTableModel(
					new String[] { "Stylist", "Hairstyle", "Haircut", "Products", "Formula", "Notes", "Other", "Date" },
					0) {
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
						int row = table.rowAtPoint(e.getPoint());
						set_stylist((String) table.getValueAt(row, 0));
						set_hairstyle((String) table.getValueAt(row, 1));
						set_haircut((String) table.getValueAt(row, 2));
						set_products((String) table.getValueAt(row, 3));
						set_formula((String) table.getValueAt(row, 4));
						set_notes((String) table.getValueAt(row, 5));
						set_other((String) table.getValueAt(row, 6));
						set_date((String) table.getValueAt(row, 7));

						try {
							Home_Page.go_to_visit_description = new Visit_Description();
							Home_Page.go_to_visit_description.setVisible(true);
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						}

					}
				}
			});
			statement_draw_table = ConnectionHandler.connection.createStatement();
			ResultSet rs = statement_draw_table.executeQuery(
					"SELECT Stylist, Hairstyle, Haircut, Products, Formula, Notes, Other, Date FROM history WHERE id = "
							+ View_List_Of_Clients.get_id() + " ORDER BY Date DESC");

			while (rs.next()) {
				String stylist = rs.getString("Stylist");
				String hairstyle = rs.getString("Hairstyle");
				String haircut = rs.getString("Haircut");
				String products = rs.getString("Products");
				String formula = rs.getString("Formula");
				String notes = rs.getString("Notes");
				String other = rs.getString("Other");
				String date = rs.getString("Date");
				model.addRow(new Object[] { stylist, hairstyle, haircut, products, formula, notes, other, date });
			}

			table.setModel(model);

		} catch (SQLException e) {
			System.err.println("error: " + e.getMessage());
		}
	}

	public void set_stylist(String stylist) {
		visit_stylist = stylist;
	}

	public static String get_stylist() {
		return visit_stylist;
	}

	public void set_hairstyle(String style) {
		visit_hairstyle = style;
	}

	public static String get_hairstyle() {
		return visit_hairstyle;
	}

	public void set_haircut(String cut) {
		visit_haircut = cut;
	}

	public static String get_haircut() {
		return visit_haircut;
	}

	public void set_products(String products) {
		visit_products = products;
	}

	public static String get_products() {
		return visit_products;
	}

	public void set_formula(String formula) {
		visit_formula = formula;
	}

	public static String get_formula() {
		return visit_formula;
	}

	public void set_notes(String notes) {
		visit_notes = notes;
	}

	public static String get_notes() {
		return visit_notes;
	}

	public void set_other(String other) {
		visit_other = other;
	}

	public static String get_other() {
		return visit_other;
	}

	public void set_date(String date) {
		visit_date = date;
	}

	public static String get_date() {
		return visit_date;
	}

}
