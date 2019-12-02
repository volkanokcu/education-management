package educationManagement.model.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.TreeSet;

import educationManagement.model.entity.Lesson;

public class LessonDAL extends AbstractDAL<Lesson> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8206743043923564272L;

	@Override
	public boolean insertEntity(Lesson entity) {
		boolean result = false;
		final String sql = "INSERT INTO lessons (chapter_id, lesson_name) VALUES (?,?)";
		try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, entity.getId());
			statement.setString(2, entity.getLessonName());
			result = statement.executeUpdate() > 0;
			if (result) {
				try (ResultSet resultSet = statement.getGeneratedKeys()) {
					if (resultSet.next()) {
						entity.setId(resultSet.getInt(1));
						connection.commit();
					}
				}
			} else connection.rollback();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;
	}

	@Override
	public boolean updateEntity(Lesson entity) {
		boolean result = false;
		final String sql = "UPDATE lessons SET chapter_id=?, lesson_name=? WHERE lesson_id=?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, entity.getId());
			statement.setString(2, entity.getLessonName());
			statement.setInt(3, entity.getId());
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
	public boolean deleteEntity(Lesson entity) {
		boolean result = false;
		final String sql = "DELETE FROM lessons WHERE lesson_id=?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
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
	public Collection<Lesson> getAll() {
		Collection<Lesson> collection = new TreeSet<>();
		final String sql = "SELECT * FROM lessons";
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(sql)) {
				while (resultSet.next()) {
					collection.add(createEntity(resultSet));
				}
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return collection;
	}

	@Override
	public Lesson getById(int id) {
		Lesson lesson = null;
		final String sql = "SELECT * FROM lessons WHERE lesson_id=?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) { 
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) lesson = createEntity(resultSet);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return lesson;
	}

	@Override
	Lesson createEntity(ResultSet resultSet) throws SQLException {
		Lesson lesson = new Lesson();
		lesson.setId(resultSet.getInt("lesson_id"));
		lesson.setLessonName(resultSet.getString("lesson_name"));
		return lesson;
	}

}
