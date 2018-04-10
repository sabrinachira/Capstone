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
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db"); //"jdbc:sqlite:C:/Users/sabri/Desktop/workspace/Capstone/sample.db");

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
	 * creates the visit history of a client
	 */
	public static void create_history_table() throws ClassNotFoundException {
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			String query = "CREATE TABLE IF NOT EXISTS history (id INTEGER , Stylist TEXT, Hairstyle TEXT, Haircut TEXT, Products TEXT, Formula TEXT, Notes TEXT, Other TEXT, Date TEXT)";
			statement.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("couldn't create history table");

			System.err.println(e.getMessage());
		}
	}

	/*
	 * adds the client info and new visit into the database
	 */
	public static void add_visit_to_history_table(String stylist, String hairstyle, String haircut, String products,
			String formula, String notes_and_preferences, String other, String date) throws ClassNotFoundException {
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			int client_id = View_List_Of_Clients.get_id();

			statement.execute(
					"INSERT INTO history (id, Stylist, Hairstyle, Haircut, Products, Formula, Notes, Other, Date) "
							+ "VALUES ('" + client_id + "','" + stylist + "','" + hairstyle + "','" + haircut + "','"
							+ products + "','" + formula + "','" + notes_and_preferences + "','" + other + "','" + date
							+ "')");

		} catch (SQLException e) {
			System.out.println("coudln't add visit");
			System.err.println(e.getMessage());
		}
	}

	public static void create_table(String table_name) throws ClassNotFoundException {
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			String query = "CREATE TABLE IF NOT EXISTS clients (id INTEGER PRIMARY KEY AUTOINCREMENT , First TEXT NOT NULL , Last TEXT NOT NULL, Phone TEXT, Address TEXT NOT NULL, Email TEXT)";
			statement.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("couldn't create table " + table_name);

			System.err.println(e.getMessage());
		}
	}

	/*
	 * adds the client profile into the database
	 */
	public static void add_client_profile_to_database(String first_name, String last_name, String phone_number,
			String address, String email) throws ClassNotFoundException {
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			create_table("clients");
			statement.executeUpdate("INSERT INTO clients (First,Last,Phone,Address,Email) " + "VALUES ('" + first_name
					+ "','" + last_name + "','" + phone_number + "','" + address + "','" + email + "')");

		} catch (SQLException e) {
			System.out.println("couldn't add client profile");
			System.err.println(e.getMessage());
		}
	}

	/*
	 * creates the visit history of a client
	 */
	public static void update_clients_profile(String first_name, String last_name, String phone_number, String address,
			String email) throws ClassNotFoundException {
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			int client_id = View_List_Of_Clients.get_id();
			String query = "UPDATE clients SET First = '" + first_name + "', Last = '" + last_name + "', Phone = '"
					+ phone_number + "', Address = '" + address + "', Email = '" + email + "' WHERE id = " + client_id;
			statement.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("couldn't update profile");

			System.err.println(e.getMessage());
		}
	}

	
	/*
	 * Select a certain piece of info from certain table
	 */
	public static String select_info(String table, String info) throws ClassNotFoundException {
		ResultSet rs;
		String output = "";
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			int client_id = View_List_Of_Clients.get_id();
			String query = "SELECT " + info + " FROM " + table + " WHERE id = " + client_id;
			System.out.println(query);
			rs = statement.executeQuery(query);

			while (rs.next()) {

				output = rs.getString(info);

			}

		} catch (SQLException e) {
			System.out.println("couldn't get info from table");
			System.err.println(e.getMessage());
		}

		return output;
	}

	/*
	 * deletes the whole table of clients
	 * https://www.tutorialspoint.com/sqlite/sqlite_delete_query.htm
	 */
	public static void delete_whole_table() throws ClassNotFoundException {
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			boolean clients_dropped = statement.execute("DROP TABLE IF EXISTS clients");
			boolean history_dropped = statement.execute("DROP TABLE IF EXISTS history");

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