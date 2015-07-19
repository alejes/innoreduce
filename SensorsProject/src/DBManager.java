import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBManager {

	private static DBManager instance = null;
	private Connection connection = null;

	// Getter
	public Connection getConnection() {
		return connection;
	}

	public DBManager() {
		connection = getMySQLConnection();
	}

	// /

	// Thread safe instantiate method
	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	// // Thread safe instantiate method
	// public static synchronized DBManager getInstance(ConnectionArgs connargs)
	// {
	// if (instance == null) {
	// instance = new DBManager(connargs);
	// }
	// return instance;
	// }

	private static Connection getMySQLConnection() {
		Connection con = null;
		String USERNAME = "root";
		String PASSWORD = "2773844";
		String URL = "jdbc:mysql://127.0.0.1:3306/sensors";

		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException se) {
			System.out.println(se);
		}
		return con;
	}

	// private static Connection getMySQLConnection(ConnectionArgs connargs) {
	// Connection con = null;
	// // String USERNAME = connargs.getUserName();
	// // String PASSWORD = connargs.getPassword();
	// // String URL = connargs.getUrl();
	// String USERNAME = "Slave-1";
	// String PASSWORD = "2773844";
	// String URL = "jdbc:mysql://localhost:3306/sensors";
	// // String URL = "jdbc:mysql://192.168.56.101:3306/sakila";
	//
	// try {
	// try {
	// Class.forName("com.mysql.jdbc.Driver");
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// }
	// con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	// } catch (SQLException se) {
	// System.out.println(se);
	// }
	// return con;
	// }
}