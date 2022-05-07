package no.adstate.user.registration.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user", schema = "public")
public class User {

	@Id
	@SequenceGenerator(sequenceName = "public.user_id_seq", name = "useridseq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "useridseq")
	private Integer userid;
	private String username;
	private String password;
	private String email;
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private Set<Subscription> subscriptions;

	public User() {
		super();
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

}
