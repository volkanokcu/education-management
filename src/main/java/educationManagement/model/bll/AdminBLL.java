package educationManagement.model.bll;

import educationManagement.model.dal.AdminDAL;
import educationManagement.model.entity.Admin;

public class AdminBLL extends AbstractBLL<Admin>{

	public AdminBLL() {
		super(new AdminDAL());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validateEntity(Admin entity) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return true;
	}

}
