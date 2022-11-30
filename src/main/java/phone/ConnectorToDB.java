package phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectorToDB {

	static String dbms = "mysql";
	static String serverName = "localhost";
	static String portNumber = "3306";
	static String dbName = "phonesDB";
	static String userOfDataBase = "root";
	static String passwordOfDataBase = "26061995";

	public static Connection getDBConnection() throws SQLException {
		Connection conn;
		Properties connectionProps = new Properties();
		connectionProps.put("user", userOfDataBase);
		connectionProps.put("password", passwordOfDataBase);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}

		if (!dbName.isEmpty() && dbName != null) {
			conn = DriverManager.getConnection(
					"jdbc:" + dbms + "://" +
							serverName +
							":" + portNumber + "/"
							+ dbName,
					connectionProps);
		} else {
			conn = DriverManager.getConnection(
					"jdbc:" + dbms + "://" +
							serverName +
							":" + portNumber + "/",
					connectionProps);
		}

		System.out.println("Connected to database");
		return conn;
	}
}
