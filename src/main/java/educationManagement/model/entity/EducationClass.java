package educationManagement.model.entity;

import java.util.Set;

public class EducationClass extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7975645618604860606L;
	
	private String className;
	private Chapter chapter;
	private Teacher responsibleTeacher;
	private Set<Student> students;
	private Set<Teacher> teachers;
	private Set<Lesson> lessons;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public Teacher getResponsibleTeacher() {
		return responsibleTeacher;
	}
	public void setResponsibleTeacher(Teacher responsibleTeacher) {
		this.responsibleTeacher = responsibleTeacher;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	public Set<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
	public Set<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(Set<Lesson> lessons) {
		this.lessons = lessons;
	}
}
