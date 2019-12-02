package educationManagement.model.bll;

import educationManagement.model.dal.CollegeDAL;
import educationManagement.model.entity.College;


public class CollegeBLL extends AbstractBLL<College>{

	public CollegeBLL() {
		super(new CollegeDAL());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validateEntity(College entity) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return true;
	}

}