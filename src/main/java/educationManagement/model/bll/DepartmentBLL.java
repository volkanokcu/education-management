package educationManagement.model.bll;

import educationManagement.model.dal.DepartmentDAL;
import educationManagement.model.entity.Department;

public class DepartmentBLL extends AbstractBLL<Department>{

	public DepartmentBLL() {
		super(new DepartmentDAL());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validateEntity(Department entity) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return true;
	}

}