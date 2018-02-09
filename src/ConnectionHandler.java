import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//got how to do this connection from: https://stackoverflow.com/questions/24963259/need-help-setting-up-sqlite-on-eclipse-with-java-for-the-first-time/24963627#24963627
/*
 * when ids(rows) are created, they're sequential by when they're added.
 * Must be refreshed so the id(rows) can be updated
 */
public class ConnectionHandler {

	static Connection connection;

	/*
	 * connects the database to the program
	 */
	public void connect() throws ClassNotFoundException {
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		connection = null;

		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
		} catch (SQLException e) {
			System.out.println("no connection");
			System.err.println(e.getMessage());
		}
	}

	/*
	 * end the connection when the user selects the RED X at the top right of
	 * the screen (the close button)
	 */
	public void end_connection() throws ClassNotFoundException {
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/*
	 * adds the client info and new visit into the database
	 */
	public boolean add_visit_to_database(String first_name, String last_name, String phone_number, String address,
			String birthday, String email, String stylist, String hairstyle, String haircut, String products,
			String formula, String notes_and_preferences, String other) throws ClassNotFoundException {
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS clients ( First Name STRING , Last Name STRING, Phone Number STRING, Address STRING, Birthday STRING, Email STRING, Stylist STRING, Hairstyle STRING, Haircut STRING, Products STRING, Formula STRING, Notes and Preferences STRING, Other STRING");

			statement.executeUpdate("INSERT INTO clients values(' " + first_name + "', '" + last_name + "', '"
					+ phone_number + "', '" + address + "', '" + birthday + "', '" + email + "', '" + stylist + "', '"
					+ hairstyle + "', '" + haircut + "', '" + products + "', '" + formula + "', '"
					+ notes_and_preferences + "', '" + other + "')");

			statement.executeUpdate("UPDATE clients SET First Name='" + first_name + "', Last Name='" + last_name
					+ "', Phone Number='" + phone_number + "', Address='" + address + "', Birthday='" + birthday
					+ "', Email Name='" + email + "', Stylist='" + stylist + "', Hairstyle='" + hairstyle
					+ "', Haircut='" + haircut + "', Products='" + products + "', Formula='" + formula
					+ "', Notes and Preferences='" + notes_and_preferences + "', Other ='" + other + "')");

			return true;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	/*
	 * adds the client profile into the database
	 */
	public static boolean add_client_profile_to_database(String first_name, String last_name, String phone_number,
			String address, String birthday, String email) throws ClassNotFoundException {
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS clients ( First Name STRING , Last Name STRING, Phone Number STRING, Address STRING, Birthday STRING, Email STRING");
			statement.executeUpdate("INSERT INTO clients values(' " + first_name + "', '" + last_name + "', '"
					+ phone_number + "', '" + address + "', '" + birthday + "', '" + email + "')");

			statement.executeUpdate("UPDATE clients SET First Name='" + first_name + "', Last Name='" + last_name
					+ "', Phone Number='" + phone_number + "', Address='" + address + "', Birthday='" + birthday
					+ "', Email Name='" + email + "')");

			return true;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	/*
	 * deletes the information from the database when the user selects an
	 * id(row)
	 */
	public void delete_from_database() throws ClassNotFoundException {
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// statement.executeUpdate("DROP TABLE IF EXISTS person");
			// statement.executeUpdate("CREATE TABLE person (id INTEGER, name
			// STRING)");
			//
			// int ids [] = {1,2,3,4,5};
			// String names [] = {"Peter","Pallar","William","Paul","James
			// Bond"};
			//
			// for(int i=0;i<ids.length;i++){
			// statement.executeUpdate("INSERT INTO person values(' "+ids[i]+"',
			// '"+names[i]+"')");
			// }

			// statement.executeUpdate("UPDATE person SET name='Peter' WHERE
			// id='1'");
			// statement.executeUpdate("DELETE FROM person WHERE id='1'");

			ResultSet resultSet = statement.executeQuery("SELECT * from person");

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}