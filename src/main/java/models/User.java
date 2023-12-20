package main.java.models;

public class User extends TrelloModel {

	private String fullName;
	private String email;
	private String phoneNumber;
	private String encryptedPassword;
	private String dateJoined;
	private String avatar;
	private String accountRole;

	public User() {
		super();
	}

	public User(String userID) {
		super(userID);
	}

	public User(String fullName, String email, String phoneNumber, String encryptedPassword, String dateJoined,
			String avatar, String accountrole) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.encryptedPassword = encryptedPassword;
		this.dateJoined = dateJoined;
		this.avatar = avatar;
		this.accountRole = accountrole;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(String dateJoined) {
		this.dateJoined = dateJoined;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAccountRole() {
		return accountRole;
	}

	public void setAccountRole(String accountrole) {
		this.accountRole = accountrole;
	}

}
