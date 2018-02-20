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
	public boolean add_visit_to_database(String first_name, String last_name, String phone_number, String address,
			String birthday, String email, String stylist, String hairstyle, String haircut, String products,
			String formula, String notes_and_preferences, String other) throws ClassNotFoundException {
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("CREATE TABLE IF NOT EXISTS clients ( First text ," + " Last text," + "Phone text,"
					+ " Address text," + " Birthday text," + "Email text," + "Stylist text," + "Hairstyle text,"
					+ "Haircut text," + "Products text," + "Formula text," + "Notes text," + "Other text)");

			statement.executeUpdate(
					"INSERT INTO clients (First,Last,Phone,Address,Birthday,Email, Stylist, Hairstyle, Haircut, Products, Formula, Notes, Other) "
							+ "VALUES ('" + first_name + "','" + last_name + "','" + phone_number + "','" + address
							+ "','" + birthday + "','" + email + "','" + stylist + "','" + hairstyle + "','" + haircut
							+ "','" + products + "','" + formula + "','" + notes_and_preferences + "','" + other
							+ "')");

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
	public static boolean add_client_profile_to_database(String first_name, String last_name, String phone_number,
			String address, String birthday, String email) throws ClassNotFoundException {
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			System.out.println("1");
			// statement.executeUpdate("DROP TABLE IF EXISTS clients");
			// System.out.println("2");

			statement.executeUpdate("CREATE TABLE IF NOT EXISTS clients ( First text ," + " Last text," + "Phone text,"
					+ " Address text," + " Birthday text," + "Email text," + "Stylist text," + "Hairstyle text,"
					+ "Haircut text," + "Products text," + "Formula text," + "Notes text," + "Other text)");

			System.out.println("2");

			statement.executeUpdate("INSERT INTO clients (First,Last,Phone,Address,Birthday,Email) " + "VALUES ('"
					+ first_name + "','" + last_name + "','" + phone_number + "','" + address + "','" + birthday + "','"
					+ email + "')");

			System.out.println("3");

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM clients");
			System.out.println("4");

			while (rs.next()) {
				String first = rs.getString("First");
				String second = rs.getString("Last");
				String third = rs.getString("Phone");
				String fourth = rs.getString("Address");
				String fifth = rs.getString("Birthday");
				String sixth = rs.getString("Email");
				System.out.println("5");

				System.out.println("first = " + first);
				System.out.println("last = " + second);
				System.out.println("phone = " + third);
				System.out.println("address = " + fourth);
				System.out.println("birthday = " + fifth);
				System.out.println("email = " + sixth);
				System.out.println("6");

			}
			System.out.println("7");
			return true;

		} catch (SQLException e) {
			System.out.println("couldn't add client profile");

			System.err.println(e.getMessage());
			return false;
		}
	}

	/*
	 * deletes the information from the database when the user selects an
	 * id(row)
	 */
	public void delete_from_database() throws ClassNotFoundException {
		// try {
		// statement = connection.createStatement();
		// statement.setQueryTimeout(30);
		// set timeout to 30 sec.

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
		//
		// ResultSet result_set = statement.executeQuery("SELECT * from
		// clients");

		// } catch (SQLException e) {
		// System.out.println("couldn't delete");
		// System.err.println(e.getMessage());
		// }
	}
}