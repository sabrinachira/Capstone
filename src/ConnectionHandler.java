import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//got how to do this connection from: https://stackoverflow.com/questions/24963259/need-help-setting-up-sqlite-on-eclipse-with-java-for-the-first-time/24963627#24963627
/*
 * when ids(rows) are created, they're sequential by when they're added.
 * Must be refreshed so the id(rows) can be updated
 */
public class ConnectionHandler {

	static Connection connection;
	static Statement statement;

	/*
	 * connects the database to the program
	 */
	public static void connect() throws ClassNotFoundException {
		// load the sqlite-JDBC driver using the current class loader
		connection = null;

		try {
			// create a database connection
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/sabri/Desktop/workspace/Capstone/sample.db");

		} catch (SQLException e) {
			System.out.println("didn't connect");
			System.err.println(e.getMessage());
		}
	}

	/*
	 * end the connection when the user selects the RED X at the top right of
	 * the screen (the close button)
	 */
	public static void end_connection() throws ClassNotFoundException {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("didn't end connection");
			System.err.println(e.getMessage());
		}
	}

	/*
	 * adds the client info and new visit into the database
	 */
	public boolean add_visit_to_client_table(String stylist, String hairstyle, String haircut, String products,
			String formula, String notes_and_preferences, String other) throws ClassNotFoundException {
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS clients (id INT AUTO_INCREMENT primary key, First text NOT NULL , Last text NOT NULL, Stylist text NOT NULL, Phone text, Address text NOT NULL, Email text, Hairstyle text, Haircut text, Products text, Formula text, Notes text, Other text)");

			statement
					.executeUpdate("INSERT INTO clients (Stylist, Hairstyle, Haircut, Products, Formula, Notes, Other) "
							+ "VALUES ('" + stylist + "','" + hairstyle + "','" + haircut + "','" + products + "','"
							+ formula + "','" + notes_and_preferences + "','" + other + "')");

			return true;

		} catch (SQLException e) {
			System.out.println("coudln't add visit");
			System.err.println(e.getMessage());
			return false;
		}
	}

	/*
	 * adds the client profile into the database
	 */
	public static boolean add_client_profile_to_database(String first_name, String last_name, String stylist,
			String phone_number, String address, String email) throws ClassNotFoundException {
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS clients (id INT AUTO_INCREMENT primary key , First text NOT NULL , Last text NOT NULL, Stylist text NOT NULL, Phone text, Address text NOT NULL, Email text, Hairstyle text, Haircut text, Products text, Formula text, Notes text, Other text)");

			statement.executeUpdate("INSERT INTO clients (First,Last,Stylist,Phone,Address,Email) " + "VALUES ('"
					+ first_name + "','" + last_name + "','" + stylist + "','" + phone_number + "','" + address + "','"
					+ email + "')");
			return true;

		} catch (SQLException e) {
			System.out.println("couldn't add client profile");

			System.err.println(e.getMessage());
			return false;
		}
	}

	/*
	 * deletes the whole table of clients
	 * https://www.tutorialspoint.com/sqlite/sqlite_delete_query.htm
	 */
	public static void delete_whole_table() throws ClassNotFoundException {
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			statement.executeUpdate("DELETE FROM clients");
		} catch (SQLException e) {
			System.out.println("couldn't delete list");
			System.err.println(e.getMessage());
		}
	}

	/*
	 * deletes the information from the database when the user selects an
	 * id(row) https://www.tutorialspoint.com/sqlite/sqlite_delete_query.htm
	 */
	public void delete_from_database() throws ClassNotFoundException {

	}
}