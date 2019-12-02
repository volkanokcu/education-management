package educationManagement.model.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import educationManagement.model.entity.EducationClass;

public class EducationClassDAL extends AbstractDAL<EducationClass>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3122252646275003442L;

	@Override
	public boolean insertEntity(EducationClass entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEntity(EducationClass entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(EducationClass entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<EducationClass> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EducationClass getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean insertClassLessonsTeachers() {
		boolean result = false;
		return result;
	}
	
	private boolean updateClassLessonsTeachers() {
		boolean result = false;
		return result;
	}
	
	private boolean insertClassStudents() {
		boolean result = false;
		return result;
	}
	
	private boolean updateClassStudents() {
		boolean result = false;
		return result;
	}

	@Override
	EducationClass createEntity(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
