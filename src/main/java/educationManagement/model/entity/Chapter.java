package educationManagement.model.entity;

import java.util.Set;

public class Chapter extends AbstractEntity implements Comparable<Chapter>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5777997240280082820L;
	private College college;
	private Set<Lesson> lessons;
	private String chapterName;
	
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}

	public Set<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(Set<Lesson> lessons) {
		this.lessons = lessons;
	}
	
	@Override
	public String toString() {
		return chapterName;
	}
	
	@Override
	public int compareTo(Chapter o) {
		return this.chapterName.compareToIgnoreCase(o.chapterName);
	}



}
