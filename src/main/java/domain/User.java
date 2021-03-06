package domain;

import org.springframework.data.annotation.Id;

public class User {

	@Id 
	private String id;
	
	private String name;
	private String password;
	
	public User(String name, String password) {
		this.name = name;
		this.password = password; 
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
}
