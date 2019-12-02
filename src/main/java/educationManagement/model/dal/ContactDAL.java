package educationManagement.model.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import educationManagement.model.entity.Contact;

public class ContactDAL extends AbstractDAL<Contact>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2812836594909973582L;

	@Override
	public boolean insertEntity(Contact entity) {
		boolean result = false;
		final String sql = "INSERT INTO contacts VALUES (?,?,?,?,?,?,?)";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(2, entity.getPhone());
			statement.setString(3, entity.getMail());
			statement.setString(4, entity.getAddress());
			statement.setString(5, entity.getDistrict());
			statement.setString(6, entity.getCity());
			statement.setInt(7, entity.getZipCode());
			result = statement.executeUpdate() > 0;
			if (result) {
				connection.commit();
			} else connection.rollback();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;
	}

	@Override
	public boolean updateEntity(Contact entity) {
		boolean result = false;
		final String sql = "UPDATE contacts SET phone=?, mail=?, address=?, district=?, city=?, zip_code=? WHERE user_id=?";
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, entity.getPhone());
			statement.setString(2, entity.getMail());
			statement.setString(3, entity.getAddress());
			statement.setString(4, entity.getDistrict());
			statement.setString(5, entity.getCity());
			statement.setInt(6, entity.getZipCode());
			result = statement.executeUpdate() > 0;
			if(result) {
				connection.commit();
			} else connection.rollback();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;
	}

	@Override
	public boolean deleteEntity(Contact entity) {
		boolean result = false;
		final String sql = "DELETE FROM contacts WHERE user_id=?";
		try(PreparedStatement statement = connection.prepareStatement(sql)){

			result = statement.executeUpdate() > 0;
			if(result) {
				connection.commit();
			} else connection.rollback();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;
	}

	@Override
	public Collection<Contact> getAll() {
		Collection<Contact> collection = new ArrayList<>();
		final String sql = "SELECT * FROM contacts";
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(sql)){
				while(resultSet.next()){
					collection.add(createEntity(resultSet));
				}
			}
			if(!collection.isEmpty()) {
				connection.commit();
			} else connection.rollback();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return collection;
	}


	@Override
	public Contact getById(int id) {
		Contact contact = null;
		final String sql = "SELECT * FROM contacts WHERE user_id=?";
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setInt(1, id);
			try(ResultSet resultSet = statement.executeQuery()){
				if(resultSet.next()) contact = createEntity(resultSet);
			}
			if (contact != null) {
				connection.commit();
			} else connection.rollback();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return contact;
	}
	
	@Override
	public Contact createEntity(ResultSet resultSet) throws SQLException {
		Contact contact = new Contact();

		contact.setPhone(resultSet.getString("phone"));
		contact.setMail(resultSet.getString("mail"));
		contact.setAddress(resultSet.getString("address"));
		contact.setDistrict(resultSet.getString("district"));
		contact.setCity(resultSet.getString("city"));
		contact.setZipCode(resultSet.getInt("zip_code"));
		return contact;
	}
}
