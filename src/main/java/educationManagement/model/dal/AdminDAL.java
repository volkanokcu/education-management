package educationManagement.model.dal;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import educationManagement.model.entity.Admin;
import educationManagement.model.entity.College;
import educationManagement.model.entity.Contact;
import educationManagement.model.entity.Department;
import educationManagement.model.entity.DepartmentChapter;

public class AdminDAL extends AbstractDAL<Admin> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2776190563454826092L;

	@Override
	public boolean insertEntity(Admin entity) {
		boolean result = false;
		createUserId(entity); //UserID oluşturan methoda gönderdik.
		final String sql = "{CALL InsertAdmin(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		try (CallableStatement statement = connection.prepareCall(sql)) {
			statement.setInt(1, entity.getId());
			statement.setString(2, entity.getTcNo());
			Date date = new Date(entity.getStartDate().getTime());
			statement.setDate(3, date);
			statement.setString(4, entity.getFirstName());
			statement.setString(5, entity.getLastName());
			statement.setString(6, entity.getTcNo());
			statement.setInt(7, entity.getCollege().getId());
			statement.setInt(8, entity.getDepartment().getId());
			statement.setInt(9, entity.getDepartmentChapter().getId());
			statement.setString(10, entity.getAuthority());
			statement.setString(11, entity.getContact().getPhone());
			statement.setString(12, entity.getContact().getMail());
			statement.setString(13, entity.getContact().getAddress());
			statement.setString(14, entity.getContact().getDistrict());
			statement.setString(15, entity.getContact().getCity());
			statement.setInt(16, entity.getContact().getZipCode());
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
	public boolean updateEntity(Admin entity) {
		boolean result = false;
		final String sql = "{CALL UpdateAdmin(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		try (CallableStatement statement = connection.prepareCall(sql)) {
			statement.setInt(1, entity.getId());
			statement.setString(2, entity.getTcNo());
			Date date = new Date(entity.getStartDate().getTime());
			statement.setDate(3, date);
			statement.setString(4, entity.getFirstName());
			statement.setString(5, entity.getLastName());
			statement.setString(6, entity.getTcNo());
			statement.setInt(7, entity.getCollege().getId());
			statement.setInt(8, entity.getDepartment().getId());
			statement.setInt(9, entity.getDepartmentChapter().getId());
			statement.setString(10, entity.getAuthority());
			statement.setString(11, entity.getContact().getPhone());
			statement.setString(12, entity.getContact().getMail());
			statement.setString(13, entity.getContact().getAddress());
			statement.setString(14, entity.getContact().getDistrict());
			statement.setString(15, entity.getContact().getCity());
			statement.setInt(16, entity.getContact().getZipCode());
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
	public boolean deleteEntity(Admin entity) {
		boolean result = false;
		final String sql = "{CALL DeleteAdmin(?)}";
		try (CallableStatement statement = connection.prepareCall(sql)) {
			statement.setInt(1, entity.getId());
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
	public Collection<Admin> getAll() {
		Collection<Admin> collection = new ArrayList<>();
		final String sql = "{CALL GetAllAdmins()}";
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(sql)){
				while(resultSet.next()){
						collection.add(createEntity(resultSet)); 
				}
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return collection;
	}

	@Override
	public Admin getById(int id) {
		Admin entity = null;
		final String sql = "{CALL GetAdminById(?)}";
		try(CallableStatement statement = connection.prepareCall(sql)){
			statement.setInt(1, id);
			try(ResultSet resultSet = statement.executeQuery()){
				if(resultSet.next()) entity = createEntity(resultSet);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}	
		return entity;
	}

	@Override
	public Admin getByTcNo(String tcNo) {
		Admin entity = null;
		final String sql = "{CALL GetAdminByTcNo(?)}";
		try(CallableStatement statement = connection.prepareCall(sql)){
			statement.setString(1, tcNo);
			try(ResultSet resultSet = statement.executeQuery()){
				if(resultSet.next()) entity = createEntity(resultSet);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}	
		return entity;
	}
	
	@Override
	public Admin isUserExist(int id, String password) {
		Admin entity = null;
		final String sql = "{CALL GetAdminByIdAndPassword(?,?)}";
		try(CallableStatement statement = connection.prepareCall(sql)){
			statement.setInt(1, id);
			statement.setString(2, password);
			try(ResultSet resultSet = statement.executeQuery()){
				if(resultSet.next()) entity = createEntity(resultSet);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}	
		return entity;
	}

	@Override
	Admin createEntity(ResultSet resultSet) throws SQLException {
		Admin admin = new Admin();
		admin.setId(resultSet.getInt("user_id"));
		admin.setTcNo(resultSet.getString("user_tcno"));
		admin.setStartDate((java.util.Date)resultSet.getDate("user_startdate"));
		admin.setFirstName(resultSet.getString("user_firstname"));
		admin.setLastName(resultSet.getString("user_lastname"));
		admin.setPassword(resultSet.getString("user_password"));
		admin.setAuthority(resultSet.getString("authority"));
		College college = new College();
		college.setId(resultSet.getInt("college_id"));
		college.setCollegeName(resultSet.getString("college_name"));
		admin.setCollege(college);
		Contact contact = new Contact();
		contact.setPhone(resultSet.getString("phone"));
		contact.setMail(resultSet.getString("mail"));
		contact.setAddress(resultSet.getString("address"));
		contact.setDistrict(resultSet.getString("district"));
		contact.setCity(resultSet.getString("city"));
		contact.setZipCode(resultSet.getInt("zip_code"));
		admin.setContact(contact);
		Department department = new Department();
		department.setId(resultSet.getInt("department_id"));
		department.setDepartmentName("department_name");
		admin.setDepartment(department);
		DepartmentChapter departmentChapter = new DepartmentChapter();
		departmentChapter.setId(resultSet.getInt("department_chapter_id"));
		departmentChapter.setDepartmentChapterName(resultSet.getString("department_chapter_name"));
		admin.setDepartmentChapter(departmentChapter);
		return admin;
	}

	private void createUserId(Admin entity) {
		Random random = new Random();
		String generetedId;
		do {
			generetedId = "10" + (random.nextInt(999999) + 1);
		} while(checkUserId(Integer.parseInt(generetedId)));
		entity.setId(Integer.parseInt(generetedId));
	}

	private boolean checkUserId(int createdUserId) {
		boolean result = false;
		final String sql = "SELECT * FROM users WHERE user_id=?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, createdUserId);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) result = true;
			}
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;
	}

}
