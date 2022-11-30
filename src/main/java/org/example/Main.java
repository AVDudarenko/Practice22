package org.example;

import com.google.protobuf.Empty;

import java.sql.*;
import java.util.Properties;

public class Main {

	static String dbms = "mysql";
	static String serverName = "localhost";
	static String portNumber = "3306";
	static String dbName = "pets";
	static String userOfDataBase = "root";
	static String passwordOfDataBase = "26061995";


	public static void main(String[] args) throws SQLException {
		createDBCarsTable();
	}

	private static void createDBCarsTable() throws SQLException {

		Connection connection = null;
		Statement statement = null;

		String createTableSql = "CREATE TABLE cars("
				+ "CAR_ID INTEGER AUTO_INCREMENT PRIMARY KEY, "
				+ "MODEL VARCHAR(20), "
				+ "CAR_BRAND VARCHAR(20)"
				+ ");";

		try {
			connection = getDBConnection();
			statement = getDBConnection().createStatement();

			statement.execute(createTableSql);
			System.out.println("Table \"dbcars\" is created");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	private static Connection getDBConnection() throws SQLException {
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