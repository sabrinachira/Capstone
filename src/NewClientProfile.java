import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NewClientProfile extends JFrame {

	public NewClientProfile() {
		JMenuBar menu_bar = new JMenuBar();
		JMenu new_client = new JMenu("New Client");
		menu_bar.add(new_client);

		setJMenuBar(menu_bar);
	}

	public static void main(String[] args) {
		JFrame home_page = new HomePage();
		home_page.setVisible(true);
	}
}
