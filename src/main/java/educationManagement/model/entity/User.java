package educationManagement.model.entity;

import java.util.Date;

public class User extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4970273005866956586L;
	private String tcNo;
	private Date startDate;
	private String firstName;
	private String lastName;
	private String password;
	private College college;
	private Contact contact;
	
	public User() {

	}

	public User(String tcNo, Date startDate, String firstName, String lastName, College college,
			Contact contact) {
		super();
		this.tcNo = tcNo;
		this.startDate = startDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.college = college;
		this.contact = contact;
	}
	
	public String getTcNo() {
		return tcNo;
	}
	public void setTcNo(String tcNo) {
		this.tcNo = tcNo;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	@Override
	public String toString() {
		return "User [tcNo=" + tcNo + ", startDate=" + startDate + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", college=" + college + ", contact=" + contact + ", getId()=" + getId()
				+ "]";
	}

}
