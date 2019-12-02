package educationManagement.model.entity;

public class Contact extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1519858522191700638L;
	private String phone;
	private String mail;
	private String address;
	private String district;
	private String city;
	private Integer zipCode;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String county) {
		this.district = county;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	
	@Override
	public String toString() {
		return "Contact [phone=" + phone + ", mail=" + mail + ", address=" + address + ", district=" + district
				+ ", city=" + city + ", zipCode=" + zipCode + ", toString()=" + super.toString() + "]";
	}

}
