package educationManagement.model.bll;

import educationManagement.model.dal.TeacherDAL;
import educationManagement.model.entity.Teacher;

public class TeacherBLL extends AbstractBLL<Teacher>{

	public TeacherBLL() {
		super(new TeacherDAL());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validateEntity(Teacher entity) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return true;
	}

}
