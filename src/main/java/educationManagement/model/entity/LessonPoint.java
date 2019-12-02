package educationManagement.model.entity;

public class LessonPoint extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1110899904281697990L;
	private Student student;
	private Lesson lesson;
	private byte visa1;
	private byte visa2;
	private byte finalPoint;
	
	public byte getVisa1() {
		return visa1;
	}
	public void setVisa1(byte visa1) {
		this.visa1 = visa1;
	}
	public byte getVisa2() {
		return visa2;
	}
	public void setVisa2(byte visa2) {
		this.visa2 = visa2;
	}
	public byte getFinalPoint() {
		return finalPoint;
	}
	public void setFinalPoint(byte finalPoint) {
		this.finalPoint = finalPoint;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Lesson getLesson() {
		return lesson;
	}
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((lesson == null) ? 0 : lesson.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LessonPoint other = (LessonPoint) obj;
		if (lesson == null) {
			if (other.lesson != null)
				return false;
		} else if (!lesson.equals(other.lesson))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "LessonPoint [student=" + student + ", lesson=" + lesson + ", visa1=" + visa1 + ", visa2=" + visa2
				+ ", finalPoint=" + finalPoint + "]";
	}
	
}
