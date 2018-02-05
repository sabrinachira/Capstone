import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class ViewListOfRecentClients extends JFrame {

	public ViewListOfRecentClients() {
		JMenuBar menu_bar = new JMenuBar();
		JMenu new_client = new JMenu("New Client");

		menu_bar.add(new_client);

		setJMenuBar(menu_bar);
		

		//add(PANEL);
		pack();
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		JFrame ViewListOfRecentClients = new HomePage();
		ViewListOfRecentClients.setVisible(true);
	}

}
