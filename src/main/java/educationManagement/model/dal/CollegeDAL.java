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

public class CollegeDAL extends AbstractDAL<College>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7822176810591365347L;

	@Override
	public boolean insertEntity(College entity) {
		boolean result = false;
		final String sql = "INSERT INTO colleges (college_name) VALUES (?)";
		try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, entity.getCollegeName());
			result = statement.executeUpdate() > 0;
			if(result){
				try(ResultSet resultSet = statement.getGeneratedKeys()){
					if(resultSet.next()) {
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
	public boolean updateEntity(College entity) {
		boolean result = false;
		final String sql = "UPDATE colleges SET college_name=? WHERE college_id=?";
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, entity.getCollegeName());
			statement.setInt(2, entity.getId());
			result = statement.executeUpdate() > 0;
			if(result){
				connection.commit();
			} else connection.rollback();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;
	}

	@Override
	public boolean deleteEntity(College entity) {
		boolean result = false;
		final String sql = "DELETE FROM colleges WHERE college_id=?";
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, entity.getId());
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
	public Collection<College> getAll() {
		Collection<College> collection = new TreeSet<>();
		try(Statement statement = connection.createStatement()) {
			final String sql = "SELECT * FROM colleges col INNER JOIN chapters chap ON col.college_id = chap.college_id";
			try(ResultSet resultSet = statement.executeQuery(sql)) {
				resultSet.next();
				int incollegeId = resultSet.getInt("college_id");
				resultSet.last();
				int numberOfRows = resultSet.getRow();
				resultSet.beforeFirst();
				Set<Chapter> chapters = new TreeSet<>();
				College college;
				while (resultSet.next()) {
					if (incollegeId == resultSet.getInt("college_id")) {
						Chapter chapter = new Chapter();
						chapter.setId(resultSet.getInt("chapter_id"));
						chapter.setChapterName(resultSet.getString("chapter_name"));
						chapters.add(chapter);
						if (resultSet.getRow() == numberOfRows) {
							college = createEntity(resultSet);
							college.setChapters(chapters);
							collection.add(college);
						}
					} else {
						incollegeId = resultSet.getInt("college_id");
						resultSet.previous();
						college = createEntity(resultSet);
						college.setChapters(chapters);
						collection.add(college);
						chapters = new TreeSet<>();
					}
				}
			}
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		return collection;
	}

	@Override
	public College getById(int id) {
		College college = null;
		final String sql = "SELECT * FROM colleges WHERE college_id=?";
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			try(ResultSet resultSet = statement.executeQuery()) {
				if(resultSet.next()) college = createEntity(resultSet); 
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return college;
	}
	
	@Override
	public College createEntity(ResultSet resultSet) throws SQLException {
		College college = new College();
		college.setId(resultSet.getInt("college_id"));
		college.setCollegeName(resultSet.getString("college_name"));
		return college;
	}

}
