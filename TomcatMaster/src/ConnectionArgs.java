public class ConnectionArgs {
	private String userName;;
	private String password;
	private String url;

	public ConnectionArgs(String userName, String password, String url) {
		this.userName = userName;
		this.password = password;
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}