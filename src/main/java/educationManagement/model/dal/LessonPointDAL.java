package educationManagement.model.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import educationManagement.model.entity.LessonPoint;
import educationManagement.model.entity.Student;

public class LessonPointDAL extends AbstractDAL<LessonPoint> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1631578560891846064L;

	@Override
	public boolean insertEntity(LessonPoint entity) {
		boolean result = false;
		final String sql = "INSERT INTO students_lessons_points (stuent_id, lesson_id, visapoint1, visapoint2, finalpoint) VALUES (?,?,?,?,?)";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, entity.getStudent().getId());
			statement.setInt(2, entity.getId());
			statement.setByte(3, entity.getVisa1());
			statement.setByte(4, entity.getVisa2());
			statement.setByte(5, entity.getFinalPoint());
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
	public boolean updateEntity(LessonPoint entity) {
		boolean result = false;
		final String sql = "UPDATE students_lessons_points SET stuent_id=? lesson_id=? visapoint1=? visapoint2=? finalpoint=? WHERE student_id=?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, entity.getStudent().getId());
			statement.setInt(2, entity.getId());
			statement.setByte(3, entity.getVisa1());
			statement.setByte(4, entity.getVisa2());
			statement.setByte(5, entity.getFinalPoint());
			statement.setInt(6, entity.getStudent().getId());
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
	public boolean deleteEntity(LessonPoint entity) {
		boolean result = false;
		final String sql = "DELETE FROM students_lessons_points WHERE student_id=?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, entity.getStudent().getId());
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
	public Collection<LessonPoint> getAll() {
		Collection<LessonPoint> lessonPoints = new ArrayList<>();
		final String sql = "SELECT * FROM students_lessons_points";
		try (Statement statement = connection.createStatement()) {
			try(ResultSet resultSet = statement.executeQuery(sql)) {
				while (resultSet.next()) {
					createEntity(resultSet);
					
				}
			}
		
		} catch (SQLException e) {
			printSQLException(e);
		}
		return lessonPoints;
	}

	@Override
	public LessonPoint getById(int id) {
		LessonPoint lessonPoint = null;
		final String sql = "SELECT * FROM chapters WHERE chapter_id=?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) lessonPoint = createEntity(resultSet);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return lessonPoint;
	}

	@Override
	LessonPoint createEntity(ResultSet resultSet) throws SQLException {
		LessonPoint lessonPoint = new LessonPoint();
		Student student = new Student();
		student.setId(resultSet.getInt("student_id"));
		lessonPoint.setStudent(student);
		return lessonPoint;
	}
}
