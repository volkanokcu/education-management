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

import educationManagement.model.entity.Chapter;
import educationManagement.model.entity.College;
import educationManagement.model.entity.Contact;
import educationManagement.model.entity.Student;

public class StudentDAL extends AbstractDAL<Student>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2813730647171506532L;

	@Override
	public boolean insertEntity(Student entity) {
		boolean result = false;
		createUserId(entity); //UserID oluşturan methoda gönderdik.
		final String sql = "{CALL InsertStudent(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		try (CallableStatement statement = connection.prepareCall(sql)) {
			statement.setInt(1, entity.getId());
			statement.setString(2, entity.getTcNo());
			Date date = new Date(entity.getStartDate().getTime());
			statement.setDate(3, date);
			statement.setString(4, entity.getFirstName());
			statement.setString(5, entity.getLastName());
			statement.setString(6, entity.getTcNo());
			statement.setInt(7, entity.getCollege().getId());
			statement.setInt(8, entity.getChapter().getId());
			statement.setString(9, entity.getContact().getPhone());
			statement.setString(10, entity.getContact().getMail());
			statement.setString(11, entity.getContact().getAddress());
			statement.setString(12, entity.getContact().getDistrict());
			statement.setString(13, entity.getContact().getCity());
			statement.setInt(14, entity.getContact().getZipCode());
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
	public boolean updateEntity(Student entity) {
		boolean result = false;
		final String sql = "{CALL UpdateStudent(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		try (CallableStatement statement = connection.prepareCall(sql)) {
			statement.setInt(1, entity.getId());
			statement.setString(2, entity.getTcNo());
			Date date = new Date(entity.getStartDate().getTime());
			statement.setDate(3, date);
			statement.setString(4, entity.getFirstName());
			statement.setString(5, entity.getLastName());
			statement.setString(6, entity.getTcNo());
			statement.setInt(7, entity.getCollege().getId());
			statement.setInt(8, entity.getChapter().getId());
			statement.setString(9, entity.getContact().getPhone());
			statement.setString(10, entity.getContact().getMail());
			statement.setString(11, entity.getContact().getAddress());
			statement.setString(12, entity.getContact().getDistrict());
			statement.setString(13, entity.getContact().getCity());
			statement.setInt(14, entity.getContact().getZipCode());
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
	public boolean deleteEntity(Student entity) {
		boolean result = false;
		final String sql = "{CALL DeleteStudent(?)}";
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
	public Collection<Student> getAll() {
		Collection<Student> collection = new ArrayList<>();
		final String sql = "{CALL GetAllStudents()}";
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
	public Student getById(int id) {
		Student entity = null;
		final String sql = "{CALL GetStudentById(?)}";
		try(PreparedStatement statement = connection.prepareStatement(sql)){
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
	public Student getByTcNo(String tcNo) {
		Student entity = null;
		final String sql = "{CALL GetStudentByTcNo(?)}";
		try(PreparedStatement statement = connection.prepareStatement(sql)){
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
	public Student isUserExist(int id, String password) {
		Student entity = null;
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
	Student createEntity(ResultSet resultSet) throws SQLException {
		Student student = new Student();
		student.setId(resultSet.getInt("user_id"));
		System.out.println("girdi");
		student.setTcNo(resultSet.getString("user_tcno"));
		student.setStartDate((java.util.Date)resultSet.getDate("user_startdate"));
		student.setFirstName(resultSet.getString("user_firstname"));
		student.setLastName(resultSet.getString("user_lastname"));
		student.setPassword(resultSet.getString("user_password"));
		College college = new College();
		college.setId(resultSet.getInt("college_id"));
		college.setCollegeName(resultSet.getString("college_name"));
		student.setCollege(college);
		Chapter chapter = new Chapter();
		chapter.setId(resultSet.getInt("chapter_id"));
		chapter.setChapterName(resultSet.getString("chapter_name"));
		student.setChapter(chapter);
		Contact contact = new Contact();
		contact.setPhone(resultSet.getString("phone"));
		contact.setMail(resultSet.getString("mail"));
		contact.setAddress(resultSet.getString("address"));
		contact.setDistrict(resultSet.getString("district"));
		contact.setCity(resultSet.getString("city"));
		contact.setZipCode(resultSet.getInt("zip_code"));
		student.setContact(contact);
		return student;
	}

	private void createUserId(Student entity) {
		Random random = new Random();
		String generetedId;
		do {
			generetedId = "50" + (random.nextInt(999999) + 1);
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
