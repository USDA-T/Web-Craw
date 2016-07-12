package gov.sba.utils.helpers;

public class LoginInfo {
	private LoginData data = new LoginData();
	
	public LoginInfo() {	
	}
	
	public LoginInfo(String email, String password) {
		this.data.email = email;
		this.data.password = password;
	}

	public String getEmail() {
		return data.email;
	}

	public void setEmail(String email) {
		this.data.email = email;
	}

	public String getPassword() {
		return data.password;
	}

	public void setPassword(String password) {
		this.data.password = password;
	}
}