package educationManagement.model;

import educationManagement.model.bll.AdminBLL;
import educationManagement.model.bll.ChapterBLL;
import educationManagement.model.bll.CollegeBLL;
import educationManagement.model.bll.DepartmentBLL;
import educationManagement.model.bll.DepartmentChapterBLL;
import educationManagement.model.bll.LessonBLL;
import educationManagement.model.bll.LessonPointBLL;
import educationManagement.model.bll.StudentBLL;
import educationManagement.model.bll.TeacherBLL;

//Singleton design pattern
public class DBContext {

	private static DBContext instance;

	private final StudentBLL studentBLL;
	private final AdminBLL adminBLL;
	private final TeacherBLL teacherBll;
	private final DepartmentBLL departmentBLL;
	private final DepartmentChapterBLL departmentChapterBLL;
	private final CollegeBLL collegeBLL;
	private final ChapterBLL chapterBLL;
	private final LessonBLL lessonBLL;
	private final LessonPointBLL lessonPointBLL;
	
	private DBContext() {
		studentBLL = new StudentBLL();
		adminBLL = new AdminBLL();
		teacherBll = new TeacherBLL();
		departmentBLL = new DepartmentBLL();
		departmentChapterBLL = new DepartmentChapterBLL();
		collegeBLL = new CollegeBLL();
		chapterBLL = new ChapterBLL();
		lessonBLL = new LessonBLL();
		lessonPointBLL = new LessonPointBLL();
		
	}
	
	public static DBContext getInstance() {
		if(instance==null) { 
			synchronized (DBContext.class) {
				if(instance==null) {
					instance = new DBContext();
				}
			}
		}
		return instance;
	}

	public StudentBLL students() {
		return studentBLL;
	}

	public AdminBLL admins() {
		return adminBLL;
	}

	public TeacherBLL teachers() {
		return teacherBll;
	}

	public DepartmentBLL derpartments() {
		return departmentBLL;
	}

	public DepartmentChapterBLL derpatmentchapters() {
		return departmentChapterBLL;
	}

	public CollegeBLL colleges() {
		return collegeBLL;
	}

	public ChapterBLL chapters() {
		return chapterBLL;
	}

	public LessonBLL lessons() {
		return lessonBLL;
	}

	public LessonPointBLL lessonpoints() {
		return lessonPointBLL;
	}
}
