import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

@SuppressWarnings("serial")
public class ViewListOfRecentClients extends JFrame {
	static JFrame ViewListOfRecentClients;
	public static ConnectionHandler connection_to_database;

	public ViewListOfRecentClients() {
		JMenuBar menu_bar = new JMenuBar();
		JMenu new_client = new JMenu("New Client");

		menu_bar.add(new_client);

		setJMenuBar(menu_bar);

		// add(PANEL);
		pack();
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		ViewListOfRecentClients = new ViewListOfRecentClients();
		ViewListOfRecentClients.setVisible(true);
		try {
			ConnectionHandler.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		finally {
//			try {
//				ConnectionHandler.end_connection();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
}