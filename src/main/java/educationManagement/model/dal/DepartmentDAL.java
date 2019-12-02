package educationManagement.model.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import educationManagement.model.entity.Department;
import educationManagement.model.entity.DepartmentChapter;

public class DepartmentDAL extends AbstractDAL<Department> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7543772114139536831L;

	@Override
	public boolean insertEntity(Department entity) {
		boolean result = false;
		final String sql = "INSERT INTO departments (department_name) VALUES (?)";
		try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, entity.getDepartmentName());
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
	public boolean updateEntity(Department entity) {
		boolean result = false;
		final String sql = "UPDATE departments SET dapetment_name=? WHERE department_id=?";
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, entity.getDepartmentName());
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
	public boolean deleteEntity(Department entity) {
		boolean result = false;
		final String sql = "DELETE FROM departments WHERE department_id=?";
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
	public Collection<Department> getAll() {
		Collection<Department> collection = new TreeSet<>();
		try(Statement statement = connection.createStatement()) {
			final String sql = "SELECT * FROM departments dep INNER JOIN departments_chapters dchap ON dep.department_id = dchap.department_id";
			try(ResultSet resultSet = statement.executeQuery(sql)) {
				resultSet.next();
				int incollegeId = resultSet.getInt("department_id");
				resultSet.last();
				int numberOfRows = resultSet.getRow();
				resultSet.beforeFirst();
				Set<DepartmentChapter> departmentChapters = new TreeSet<>();
				Department department;
				while (resultSet.next()) {
					if (incollegeId == resultSet.getInt("department_id")) {
						DepartmentChapter departmentChapter = new DepartmentChapter();
						departmentChapter.setId(resultSet.getInt("department_chapter_id"));
						departmentChapter.setDepartmentChapterName(resultSet.getString("department_chapter_name"));
						departmentChapters.add(departmentChapter);
						if (resultSet.getRow() == numberOfRows) {
							department = createEntity(resultSet);
							department.setDepartmentChapters(departmentChapters);
							collection.add(department);
						}
					} else {
						incollegeId = resultSet.getInt("department_id");
						resultSet.previous();
						department = createEntity(resultSet);
						department.setDepartmentChapters(departmentChapters);
						collection.add(department);
						departmentChapters = new TreeSet<>();
					}
				}
			}
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		return collection;
	}

	@Override
	public Department getById(int id) {
		Department department = null;
		final String sql = "SELECT * FROM departments WHERE department_id=?";
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
	public Department createEntity(ResultSet resultSet) throws SQLException {
		Department department = new Department();
		department.setId(resultSet.getInt("department_id"));
		department.setDepartmentName(resultSet.getString("department_name"));
		return department;
	}
}
