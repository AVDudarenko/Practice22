package phone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneDAO implements Dao<Integer, Phone> {

	private final List<Phone> phones = new ArrayList<>();
	public static final String SQL_SELECT_ALL_PHONES = "SELECT * FROM phones";
	public static final String SQL_SELECT_PHONE_BY_ID =
			"SELECT * FROM phones WHERE id=?";
	public static final String SQL_DELETE_PHONE_BY_ID = "DELETE FROM phones WHERE id=?";
	public static final String SQL_CREATE_PHONE = "INSERT INTO phones (phone_brand, phone_model, mac_address) values (?, ?, ?)";
	public static final String SQL_UPDATE_PHONE = "UPDATE phones SET mac_address =000000 WHERE mac_address = ?";



	@Override
	public List<Phone> findAll() {
		try (Connection connection = ConnectorToDB.getDBConnection();
			 Statement statement = connection.createStatement()) {
			ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_PHONES);
			while (rs.next()) {
				int id = rs.getInt(1);
				String phoneBrand = rs.getString(1);
				String phoneModel = rs.getString(2);
				String macAddress = rs.getString(3);
				phones.add(new Phone(id, phoneBrand, phoneModel, macAddress));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return phones;
	}

	@Override
	public Phone findEntityById(Integer id) {
		Phone phone = null;
		try (Connection connection = ConnectorToDB.getDBConnection();
			 PreparedStatement preparedStatement =
					 connection.prepareStatement(SQL_SELECT_PHONE_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String phoneBrand = rs.getString(1);
				String phoneModel = rs.getString(2);
				String macAddress = rs.getString(3);
				phone = new Phone(id, phoneBrand, phoneModel, macAddress);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return phone;
	}

	@Override
	public boolean delete(Integer id) {
		boolean isDelete = false;
		try (Connection connection = ConnectorToDB.getDBConnection();
			 PreparedStatement preparedStatement =
					 connection.prepareStatement(SQL_DELETE_PHONE_BY_ID)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			System.out.println("Record is deleted from cars table");
			isDelete = true;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return isDelete;
	}

	@Override
	public boolean delete(Phone phone) {
		boolean isDelete = false;
		try (Connection connection = ConnectorToDB.getDBConnection();
			 PreparedStatement statement =
					 connection.prepareStatement(SQL_DELETE_PHONE_BY_ID)) {
			statement.setInt(1, phone.getId());
			statement.executeUpdate();
			System.out.println("Record is deleted from cars table");
			isDelete = true;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return isDelete;
	}

	@Override
	public boolean create(Phone phone) {
		boolean isCreate = false;
		try (Connection connection = ConnectorToDB.getDBConnection();
			 PreparedStatement statement =
					 connection.prepareStatement(SQL_CREATE_PHONE)) {
			statement.setString(1, phone.getPhoneBrand());
			statement.setString(2, phone.getPhoneModel());
			statement.setString(3, phone.getMacAddress());
			statement.executeUpdate();
			System.out.println("Phone added into table");
			isCreate = true;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return isCreate;
	}

	@Override
	public Phone update(Phone phone) {
		Phone phones = null;
		try (Connection connection = ConnectorToDB.getDBConnection();
			 PreparedStatement preparedStatement =
					 connection.prepareStatement(SQL_UPDATE_PHONE)) {
			preparedStatement.setString(1, phone.getMacAddress());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(0);
				String phoneBrand = rs.getString(1);
				String phoneModel = rs.getString(2);
				String macAddress = rs.getString(3);
				phones = new Phone(id, phoneBrand, phoneModel, macAddress);
			}
			System.out.println("Phone is updated");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return phones;
	}


}
