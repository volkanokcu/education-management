package educationManagement.model.bll;

import educationManagement.model.dal.LessonDAL;
import educationManagement.model.entity.Lesson;

public class LessonBLL extends AbstractBLL<Lesson>{

	public LessonBLL() {
		super(new LessonDAL());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validateEntity(Lesson entity) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return true;
	}

}