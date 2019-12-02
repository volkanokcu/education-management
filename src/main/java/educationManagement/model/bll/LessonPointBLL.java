package educationManagement.model.bll;

import educationManagement.model.dal.LessonPointDAL;
import educationManagement.model.entity.LessonPoint;

public class LessonPointBLL extends AbstractBLL<LessonPoint>{

	public LessonPointBLL() {
		super(new LessonPointDAL());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validateEntity(LessonPoint entity) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return true;
	}

}