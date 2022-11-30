package org.example;

import com.google.protobuf.Empty;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {

	static String dbms = "mysql";
	static String serverName = "localhost";
	static String portNumber = "3306";
	static String dbName = "pets";
	static String userOfDataBase = "root";
	static String passwordOfDataBase = "26061995";


	public static void main(String[] args) throws SQLException {
//		createDBCarsTable();
		Scanner scanner = new Scanner(System.in);
		String carModel = null;
		String cardBrand = null;

		for (int i = 0; i < 5; i++) {
			System.out.println("Please enter car model: ");
			carModel = scanner.nextLine();
			System.out.println("Please enter car brand: ");
			cardBrand = scanner.nextLine();
			insertStatements(cardBrand, carModel);
		}
		scanner.close();


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

	private static void insertStatements( String carBrand, String carModel) {
		Connection connection = null;
		try {
			connection = getDBConnection();

			//insert statement
			String query = " insert into cars (MODEL, CAR_BRAND)"
					+ " values (?, ?)";

			// create the mysql insert prepared statement
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, carBrand);
			preparedStatement.setString(2, carModel);

			// execute the prepared statement
			preparedStatement.executeUpdate();

			connection.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}
}