package educationManagement.model.entity;

import java.util.Set;

public class Department extends AbstractEntity implements Comparable<Department>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6334645082294922493L;
	private Set<DepartmentChapter> departmentChapters;
	private String departmentName;
	
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public Set<DepartmentChapter> getDepartmentChapters() {
		return departmentChapters;
	}
	
	public void setDepartmentChapters(Set<DepartmentChapter> departmentChapters) {
		this.departmentChapters = departmentChapters;
	}	
	
	@Override
	public String toString() {
		return departmentName;
	}
	
	@Override
	public int compareTo(Department o) {
		return this.departmentName.compareToIgnoreCase(o.departmentName);
	}
	
}
