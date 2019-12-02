package educationManagement.model.entity;

import java.util.Set;

public class College extends AbstractEntity implements Comparable<College>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5992040654059707609L;
	
	private String collegeName;
	private Set<Chapter> chapters;

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String cololegeName) {
		this.collegeName = cololegeName;
	}

	@Override
	public String toString() {
		return collegeName;
	}
	
	public Set<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(Set<Chapter> chapters) {
		this.chapters = chapters;
	}


	@Override
	public int compareTo(College o) {
		return this.collegeName.compareToIgnoreCase(o.collegeName);
	}


}
