package no.adstate.user.registration.dto;

import java.util.ArrayList;
import java.util.List;

public class MailListDto {

	private Integer userid;
	private String email;
	private List<String> mailList = new ArrayList<>();
	
	public Integer getUserId() {
		return userid;
	}
	public void setUserId(Integer userid) {
		this.userid = userid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getMailList() {
		return mailList;
	}
	public void setMailList(List<String> mailList) {
		this.mailList = mailList;
	}	
}
