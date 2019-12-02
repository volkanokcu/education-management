package educationManagement.model.entity;

import java.util.Date;

public class Employee extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2029902200420186936L;
	private Department department;
	private DepartmentChapter departmentChapter;
	
	public Employee() {

	}
	
	public Employee(String tcNo, Date startDate, String firstName, String lastName, College college, Contact contact,
			Department department, DepartmentChapter departmentChapter) {
		super(tcNo, startDate, firstName, lastName, college, contact);
		this.department = department;
		this.departmentChapter = departmentChapter;
	}
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public DepartmentChapter getDepartmentChapter() {
		return departmentChapter;
	}
	public void setDepartmentChapter(DepartmentChapter departmentChapter) {
		this.departmentChapter = departmentChapter;
	} 
	
	
}