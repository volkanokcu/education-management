package educationManagement.model.entity;

import java.util.Date;
import java.util.Set;

public class Teacher extends Employee {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4633721001157545752L;
	
	private Set<Lesson> lessons;


	
	public Teacher() {

	}

	public Teacher(String tcNo, Date startDate, String firstName, String lastName, College college, Contact contact,
			Department department, DepartmentChapter departmentChapter, Set<Lesson> lessons) {
		super(tcNo, startDate, firstName, lastName, college, contact, department, departmentChapter);
		this.lessons = lessons;
	}

	public void setLessons(Set<Lesson> lessons) {
		this.lessons = lessons;
	}
	
	public Set<Lesson> getLessons() {
		return lessons;
	}

	@Override
	public String toString() {
		return "Teacher [lessons=" + lessons + ", toString()=" + super.toString() + "]";
	}
	
	
}
