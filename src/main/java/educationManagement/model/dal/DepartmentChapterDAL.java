package educationManagement.model.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import educationManagement.model.entity.DepartmentChapter;

public class DepartmentChapterDAL extends AbstractDAL<DepartmentChapter> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6676111573136237474L;

	@Override
	public boolean insertEntity(DepartmentChapter entity) {
		boolean result = false;
		final String sql = "INSERT INTO departments_chapters (department_name) VALUES (?)";
		try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, entity.getDepartmentChapterName());
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
	public boolean updateEntity(DepartmentChapter entity) {
		boolean result = false;
		final String sql = "UPDATE departments_chapters SET dapetment_chapter_name=? WHERE department_chapter_id=?";
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, entity.getDepartmentChapterName());
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
	public boolean deleteEntity(DepartmentChapter entity) {
		boolean result = false;
		final String sql = "DELETE FROM departments_chapters WHERE department_chapter_id=?";
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
	public Collection<DepartmentChapter> getAll() {
		Collection<DepartmentChapter> collection = new ArrayList<>();
		try(Statement statement = connection.createStatement()) {
			final String sql = "SELECT * FROM departments_chapters";
			try(ResultSet resultSet = statement.executeQuery(sql)) {
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
	public DepartmentChapter getById(int id) {
		DepartmentChapter department = null;
		final String sql = "SELECT * FROM colleges WHERE department_chapter_id=?";
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			try(ResultSet resultSet = statement.executeQuery()) {
				if(resultSet.next()) department = createEntity(resultSet); 
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return department;
	}
	
	@Override
	public DepartmentChapter createEntity(ResultSet resultSet) throws SQLException {
		DepartmentChapter department = new DepartmentChapter();
		department.setId(resultSet.getInt("department_chapter_id"));
		department.setDepartmentChapterName(resultSet.getString("department_chapter_name"));
		return department;
	}
}
