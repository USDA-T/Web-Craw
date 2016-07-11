package gov.sba.utils.helpers;

public class LoginInfo {
	private LoginData data = new LoginData();
	
	public LoginInfo() {	
	}
	
	public LoginInfo(String loginName, String password) {
		this.data.loginName = loginName;
		this.data.password = password;
	}

	public String getLoginName() {
		return data.loginName;
	}

	public void setLoginName(String loginName) {
		this.data.loginName = loginName;
	}

	public String getPassword() {
		return data.password;
	}

	public void setPassword(String password) {
		this.data.password = password;
	}
}