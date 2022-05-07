package no.adstate.user.registration.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto implements Serializable{

	private static final long serialVersionUID = 4392995509681801603L;

	@NotNull(message = "Username can't be null")
    private String username;
	@NotNull(message = "Password can't be null")
	@Size(min=10)
	private String password;
	@NotNull(message = "Email can't be null")
	private String email;
	@NotNull
	private String mailList;
	
	public UserDto() {
		super();
		
	}
	
	public UserDto(@NotNull(message = "Username can't be null") String username,
			@NotNull(message = "Password can't be null") @Size(min = 10) String password,
			@NotNull(message = "Email can't be null") String email, @NotNull String mailList) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.mailList = mailList;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMailList() {
		return mailList;
	}
	public void setMailList(String mailList) {
		this.mailList = mailList;
	}
				
}
