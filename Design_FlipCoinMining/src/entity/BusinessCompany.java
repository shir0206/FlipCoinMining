package entity;

public class BusinessCompany extends Miner {

	private final String uniqueAddress;
	private String contactName;
	private String contactPhone;
	private String contactEmail;

	public BusinessCompany(String uniqueAddress, String contactName, String contactPhone, String contactEmail) {
		super(contactEmail);
		this.uniqueAddress = uniqueAddress;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getUniqueAddress() {
		return uniqueAddress;
	}

	@Override
	public String toString() {
		return "BusinessCompany [uniqueAddress=" + uniqueAddress + ", contactName=" + contactName + ", contactPhone="
				+ contactPhone + ", contactEmail=" + contactEmail + "]";
	}

}
