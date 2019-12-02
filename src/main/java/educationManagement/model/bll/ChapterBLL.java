package educationManagement.model.bll;

import educationManagement.model.dal.ChapterDAL;
import educationManagement.model.entity.Chapter;


public class ChapterBLL extends AbstractBLL<Chapter>{

	public ChapterBLL() {
		super(new ChapterDAL());
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validateEntity(Chapter entity) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return true;
	}

}