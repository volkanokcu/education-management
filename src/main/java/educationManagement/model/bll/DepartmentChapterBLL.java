package educationManagement.model.bll;

import educationManagement.model.dal.DepartmentChapterDAL;
import educationManagement.model.entity.DepartmentChapter;

public class DepartmentChapterBLL extends AbstractBLL<DepartmentChapter>{

	public DepartmentChapterBLL() {
		super(new DepartmentChapterDAL());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validateEntity(DepartmentChapter entity) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return true;
	}

}