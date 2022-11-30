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
		Scanner scanner = new Scanner(System.in);
		String carModel;
		String cardBrand;
		String command;

		System.out.println("Available commands: createDBTable, insertStatement, getInfoFromDB, deleteCar");
		command = scanner.nextLine();
		switch (command) {
			case "createDBTable" -> createDBCarsTable();
			case "insertStatement" -> {
				System.out.println("Enter count of cars: ");
				int countOfCarsForInsert = scanner.nextInt();
				for (int i = 0; i < countOfCarsForInsert; i++) {
					System.out.println("Please enter car model: ");
					carModel = scanner.nextLine();
					System.out.println("Please enter car brand: ");
					cardBrand = scanner.nextLine();
					insertStatements(cardBrand, carModel);
				}
			}
			case "getInfoFromDB" -> getInfoFromDB();
			case "deleteCar" -> {
				System.out.println("Enter count of cars: ");
				carModel = scanner.nextLine();
				deleteCarFromDB(carModel);
			}
		}
	}

	/**
	 * Method for create database
	 *
	 * @throws SQLException
	 */
	private static void createDBCarsTable() throws SQLException {

		Connection connection = null;
		Statement statement = null;

		String createTableSqlQuery = "CREATE TABLE cars("
				+ "CAR_ID INTEGER AUTO_INCREMENT PRIMARY KEY, "
				+ "MODEL VARCHAR(20), "
				+ "CAR_BRAND VARCHAR(20)"
				+ ");";

		try {
			connection = getDBConnection();
			statement = getDBConnection().createStatement();

			statement.execute(createTableSqlQuery);
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

	/**
	 * Method for connect to database
	 *
	 * @return - return connection object
	 * @throws SQLException
	 */
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

	/**
	 * Method for insert statement into table
	 *
	 * @param carBrand - car brand
	 * @param carModel - car model
	 */
	private static void insertStatements(String carBrand, String carModel) {
		Connection connection;
		try {
			connection = getDBConnection();

			//insert statement
			String insertInfoInTableQuery = " insert into cars (CAR_BRAND, MODEL)"
					+ " values (?, ?)";

			// create the mysql insert prepared statement
			PreparedStatement preparedStatement = connection.prepareStatement(insertInfoInTableQuery);
			preparedStatement.setString(1, carBrand);
			preparedStatement.setString(2, carModel);

			// execute the prepared statement
			preparedStatement.executeUpdate();

			connection.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Method for get information from database
	 */
	private static void getInfoFromDB() {
		String selectTableDBQuery = "select * from cars where CAR_ID > 3;";
		Connection connection;
		Statement statement;
		try {
			connection = getDBConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(selectTableDBQuery);

			while (resultSet.next()) {
				String carModel = resultSet.getNString("CAR_BRAND");
				String carBrand = resultSet.getNString("MODEL");

				System.out.println("================================================");
				System.out.println("Car brand: " + carBrand + " \nCar Model: " + carModel);
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Method delete car by model
	 *
	 * @param carModel - id of car in the table
	 */
	private static void deleteCarFromDB(String carModel) {
		String deleteTableQuery = "DELETE FROM cars WHERE MODEL ==" + carModel + ";";
		Connection connection;
		Statement statement;

		try {
			connection = getDBConnection();
			statement = connection.createStatement();

			statement.executeUpdate(deleteTableQuery);
			System.out.println("Record is deleted from cars table");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
}