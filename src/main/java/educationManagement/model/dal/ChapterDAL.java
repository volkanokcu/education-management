package educationManagement.model.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import educationManagement.model.entity.Chapter;
import educationManagement.model.entity.College;
import educationManagement.model.entity.Lesson;

public class ChapterDAL extends AbstractDAL<Chapter>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4668936707219060736L;

	@Override
	public boolean insertEntity(Chapter entity) {
		boolean result = false;
		final String sql = "INSERT INTO chapters (college_id, chapter_name) VALUES (?,?)";
		try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, entity.getCollege().getId());
			statement.setString(2, entity.getChapterName());
			result = statement.executeUpdate() > 0;
			if (result) {
				try (ResultSet resultSet = statement.getGeneratedKeys()) {
					entity.setId(resultSet.getInt(1)); // resultset.getInt(1) burdaki 1, 1 nolu colonun değeri
					connection.commit();
				}
			} else connection.rollback();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;
	}

	@Override
	public boolean updateEntity(Chapter entity) {
		boolean result = false;
		final String sql = "UPDATE chapters SET college_id =?, chapter_name=? WHERE chapter_id=?";
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, entity.getCollege().getId());
			statement.setString(2, entity.getChapterName());
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
	public boolean deleteEntity(Chapter entity) {
		boolean result = false;
		final String sql = "DELETE FROM chapters WHERE chapter_id=?";
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
	public Collection<Chapter> getAll() {
		Collection<Chapter> collection = new TreeSet<>();
		try(Statement statement = connection.createStatement()) {
			final String sql = "SELECT * FROM colleges col INNER JOIN chapters chap INNER JOIN lessons les INNER JOIN chapters_to_lessons cles ON col.college_id = chap.college_id AND cles.lesson_id = les.lesson_id AND cles.chapter_id = chap.chapter_id";
			try(ResultSet resultSet = statement.executeQuery(sql)) {
				resultSet.next();
				int incollegeId = resultSet.getInt("chapter_id");
				resultSet.last();
				int numberOfRows = resultSet.getRow();
				resultSet.beforeFirst();
				Set<Lesson> lessons = new TreeSet<>();
				Chapter chapter;
				while (resultSet.next()) {
					if (incollegeId == resultSet.getInt("chapter_id")) {
						Lesson lesson = new Lesson();
						lesson.setId(resultSet.getInt("cles.lesson_id"));
						lesson.setLessonName(resultSet.getString("les.lesson_name"));
						lessons.add(lesson);
						if (resultSet.getRow() == numberOfRows) {
							chapter = createEntity(resultSet);
							chapter.setLessons(lessons);
							collection.add(chapter);
						}
					} else {
						incollegeId = resultSet.getInt("chapter_id");
						resultSet.previous();
						chapter = createEntity(resultSet);
						chapter.setLessons(lessons);
						collection.add(chapter);
						lessons = new TreeSet<>();
					}
				}
			}
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		return collection;
	}
	
	@Override
	public boolean insertLessons(Chapter chapter) {
		boolean result= false;
		Collection<Lesson> lessons = chapter.getLessons();
		final String sql = "INSERT INTO chapters_to_lessons VALUES (?,?)";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			for (Lesson lesson : lessons) {
				statement.setInt(1, chapter.getId());
				statement.setInt(2, lesson.getId());
			}
			result = statement.executeUpdate() == lessons.size();
			if (result) {
				connection.commit();
			} else connection.rollback();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;
	}

	@Override
	public Chapter getById(int id) {
		Chapter chapter = null;
		final String sql = "SELECT * FROM chapters WHERE chapter_id=?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) chapter = createEntity(resultSet);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return chapter;
	}
	
	@Override
	public Chapter createEntity(ResultSet resultSet) throws SQLException {
		Chapter chapter = new Chapter();
		chapter.setId(resultSet.getInt("chapter_id"));
		chapter.setChapterName(resultSet.getString("chapter_name"));
		College college = new College();
		college.setId(resultSet.getInt("college_id"));
		college.setCollegeName(resultSet.getString("college_name"));
		chapter.setCollege(college);
		return chapter;
	}

}
