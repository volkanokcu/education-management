package educationManagement.model.entity;

import java.util.Date;

public class Student extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2180158335050428116L;
	private Chapter chapter;
	
	public Student() {

	}
	
	public Student(String tcNo, Date startDate, String firstName, String lastName, College college, Contact contact,
			Chapter chapter) {
		super(tcNo, startDate, firstName, lastName, college, contact);
		this.chapter = chapter;
	}
	
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	@Override
	public String toString() {
		return "Student [chapter=" + chapter + ", toString()=" + super.toString() + "]";
	}
	
	
}
