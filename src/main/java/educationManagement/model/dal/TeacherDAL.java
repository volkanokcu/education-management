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
import java.util.Set;
import java.util.TreeSet;

import educationManagement.model.entity.College;
import educationManagement.model.entity.Contact;
import educationManagement.model.entity.Department;
import educationManagement.model.entity.DepartmentChapter;
import educationManagement.model.entity.Lesson;
import educationManagement.model.entity.Teacher;

public class TeacherDAL extends AbstractDAL<Teacher>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 197815372656659959L;

	@Override
	public boolean insertEntity(Teacher entity) {
		boolean result = false;
		boolean lessonsResult = false;
		createUserId(entity); //UserID oluşturan methoda gönderdik.
		final String sql = "{CALL InsertTeacher(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
			statement.setString(10, entity.getContact().getPhone());
			statement.setString(11, entity.getContact().getMail());
			statement.setString(12, entity.getContact().getAddress());
			statement.setString(13, entity.getContact().getDistrict());
			statement.setString(14, entity.getContact().getCity());
			statement.setInt(15, entity.getContact().getZipCode());
			result = statement.executeUpdate() > 0;
			if (result) {
				lessonsResult = insertTeacherLessons(entity); //Hocanın derslerini ilgili tabloya ekliyoruz
				System.out.println(lessonsResult);
				if (lessonsResult) {
					connection.commit();
				} else {
					connection.rollback();
					result = false;
				}
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;
	}

	@Override
	public boolean updateEntity(Teacher entity) {
		boolean result = false;
		boolean deleteLessonResult = false;
		boolean insertLessonResult = false;
		final String sql = "{CALL UpdateTeacher(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
			statement.setString(10, entity.getContact().getPhone());
			statement.setString(11, entity.getContact().getMail());
			statement.setString(12, entity.getContact().getAddress());
			statement.setString(13, entity.getContact().getDistrict());
			statement.setString(14, entity.getContact().getCity());
			statement.setInt(15, entity.getContact().getZipCode());
			result = statement.executeUpdate() > 0;
			System.out.println("Teacher Result :" + result);
			if (result) {
				deleteLessonResult = deleteTeacherLessons(entity); //Hocaya ait eski dersleri siliyoruz.
				System.out.println("Delete lesson result" + deleteLessonResult);
				insertLessonResult = insertTeacherLessons(entity); //Hocaya ait yeni dersleri ekliyoruz.
				if(deleteLessonResult && insertLessonResult){ 
					connection.commit();
				} else {
					connection.rollback();
					result = false;
				}
			} else connection.rollback();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;
	}

	@Override
	public boolean deleteEntity(Teacher entity) {
		boolean result = false;
		final String sql = "{CALL DeleteTeacher(?)}";
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
	public Collection<Teacher> getAll() {
		Collection<Teacher> collection = new ArrayList<>();
		final String sql = "{CALL GetAllTeachers()}";
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
	public Teacher getById(int id) {
		Teacher entity = null;
		final String sql = "{CALL GetTeacherById(?)}";
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setInt(1, id);
			try(ResultSet resultSet = statement.executeQuery()){
				if(resultSet.next()){
					entity = createEntity(resultSet);
					resultSet.beforeFirst();
					Set<Lesson> lessons = new TreeSet<>();
					while(resultSet.next()) {
						Lesson lesson = new Lesson();
						lesson.setId(resultSet.getInt("lesson_id"));
						lesson.setLessonName(resultSet.getString("lesson_name"));
						lessons.add(lesson);
					}
					entity.setLessons(lessons);
				}
			}
		} catch (SQLException e) {
			printSQLException(e);
		}	
		return entity;
	}
	
	

	@Override
	public Teacher getByTcNo(String tcNo) {
		Teacher entity = null;
		final String sql = "{CALL GetTeacherByTcNo(?)}";
		try(PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, tcNo);
			try(ResultSet resultSet = statement.executeQuery()){
				if(resultSet.next()){
					entity = createEntity(resultSet);
					resultSet.beforeFirst();
					Set<Lesson> lessons = new TreeSet<>();
					while(resultSet.next()) {
						Lesson lesson = new Lesson();
						lesson.setId(resultSet.getInt("lesson_id"));
						lesson.setLessonName(resultSet.getString("lesson_name"));
						lessons.add(lesson);
					}
					entity.setLessons(lessons);
				}
			}
		} catch (SQLException e) {
			printSQLException(e);
		}	
		return entity;
	}
	
	@Override
	public Teacher isUserExist(int id, String password) {
		Teacher entity = null;
		final String sql = "{CALL GetTeacherByIdAndPassword(?,?)}";
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
	Teacher createEntity(ResultSet resultSet) throws SQLException {
		Teacher teacher = new Teacher();
		teacher.setId(resultSet.getInt("user_id"));
		teacher.setTcNo(resultSet.getString("user_tcno"));
		teacher.setStartDate((java.util.Date)resultSet.getDate("user_startdate"));
		teacher.setFirstName(resultSet.getString("user_firstname"));
		teacher.setLastName(resultSet.getString("user_lastname"));
		teacher.setPassword(resultSet.getString("user_password"));
		Department department = new Department();
		department.setId(resultSet.getInt("department_id"));
		department.setDepartmentName(resultSet.getString("department_name"));
		teacher.setDepartment(department);
		DepartmentChapter departmentChapter = new DepartmentChapter();
		departmentChapter.setId(resultSet.getInt("department_chapter_id"));
		departmentChapter.setDepartmentChapterName(resultSet.getString("department_chapter_name"));
		teacher.setDepartmentChapter(departmentChapter);
		College college = new College();
		college.setId(resultSet.getInt("college_id"));
		college.setCollegeName(resultSet.getString("college_name"));
		teacher.setCollege(college);
		Contact contact = new Contact();
		contact.setPhone(resultSet.getString("phone"));
		contact.setMail(resultSet.getString("mail"));
		contact.setAddress(resultSet.getString("address"));
		contact.setDistrict(resultSet.getString("district"));
		contact.setCity(resultSet.getString("city"));
		contact.setZipCode(resultSet.getInt("zip_code"));
		teacher.setContact(contact);
		return teacher;
	}
	
	private boolean insertTeacherLessons(Teacher entity)  {
		Set<Lesson> lessons = entity.getLessons();
		
		boolean result = false;
			final String sql = "INSERT INTO teachers_lessons(teacher_id, lesson_id) VALUES (?,?)";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				int[] count;
				for (Lesson lesson : lessons) {
					System.out.println("dongo");
					statement.setInt(1, entity.getId());
					statement.setInt(2, lesson.getId());
					statement.addBatch();
				}
				count = statement.executeBatch();
				result = count.length == lessons.size();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return result;
	}
	
	private boolean deleteTeacherLessons(Teacher entity) {
		boolean result = false;
			final String sql = "DELETE FROM teachers_lessons WHERE teacher_id=?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, entity.getId());
				result = statement.executeUpdate() >=0;
			} catch (SQLException e) {
				printSQLException(e);
			}
		return result;
	}

	private void createUserId(Teacher entity) {
		Random random = new Random();
		String generetedId;
		do {
			generetedId = "40" + (random.nextInt(999999) + 1);
			System.out.println(generetedId);
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
