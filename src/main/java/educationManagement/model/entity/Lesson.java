package educationManagement.model.entity;

public class Lesson extends AbstractEntity implements Comparable<Lesson> {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5735934161254162700L;
	
	private String lessonName;

	
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	
	
	@Override
	public String toString() {
		return lessonName;
	}
	@Override
	public int compareTo(Lesson o) {
		return this.lessonName.compareToIgnoreCase(o.lessonName);
	}
	

}
