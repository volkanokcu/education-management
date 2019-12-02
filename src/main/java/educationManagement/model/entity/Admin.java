package educationManagement.model.entity;

import java.util.Date;

public class Admin extends Employee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8577958998340497287L;
	private String authority;
	
	public Admin() {

	}
	
	public Admin(String tcNo, Date startDate, String firstName, String lastName, College college, Contact contact,
			Department department, DepartmentChapter departmentChapter, String authority) {
		super(tcNo, startDate, firstName, lastName, college, contact, department, departmentChapter);
		this.authority = authority;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

}