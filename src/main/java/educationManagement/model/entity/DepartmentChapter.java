package educationManagement.model.entity;

public class DepartmentChapter extends AbstractEntity implements Comparable<DepartmentChapter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5789667877830540087L;
	private String departmentChapterName;

	public String getDepartmentChapterName() {
		return departmentChapterName;
	}
	public void setDepartmentChapterName(String departmentChapterName) {
		this.departmentChapterName = departmentChapterName;
	}
	@Override
	public String toString() {
		return departmentChapterName;
	}
	@Override
	public int compareTo(DepartmentChapter o) {
		return this.departmentChapterName.compareToIgnoreCase(o.departmentChapterName);
	}
	
	
}
