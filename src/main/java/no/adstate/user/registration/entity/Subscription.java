package no.adstate.user.registration.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="subscription", schema="public")
public class Subscription {
	
	@Id
	@SequenceGenerator(sequenceName="public.subscription_id_seq", name = "subscriptionidseq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="subscriptionidseq")
	private Integer subscriptionid;
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	private String subscription;
	
	
	
	public Subscription() {
		super();
	}
	
	public Subscription(Integer subscriptionid, User user, String subscription) {
		super();
		this.subscriptionid = subscriptionid;
		this.user = user;
		this.subscription = subscription;
	}


	public Integer getSubscriptionid() {
		return subscriptionid;
	}
	public void setSubscriptionid(Integer subscriptionid) {
		this.subscriptionid = subscriptionid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSubscription() {
		return subscription;
	}
	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}
	
}
