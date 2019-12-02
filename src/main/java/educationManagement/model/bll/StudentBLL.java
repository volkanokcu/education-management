package educationManagement.model.bll;

import educationManagement.model.dal.StudentDAL;
import educationManagement.model.entity.Student;

public class StudentBLL extends AbstractBLL<Student>{

	public StudentBLL() {
		//AbstractBLL'e(super) StudentDAL gönderiyoruz.
		super(new StudentDAL());
	}

	@Override
	public boolean validateEntity(Student entity) throws IllegalArgumentException {
		return true;
	}

}
