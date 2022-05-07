package no.adstate.user.registration.util;

import no.adstate.user.registration.dto.MailListDto;
import no.adstate.user.registration.dto.UserDto;
import no.adstate.user.registration.entity.User;

public class Adapter {
	
	  public static User adaptToUserEntity(UserDto userDto) {
		  User user = new User();
		  user.setUsername(userDto.getUsername());
		  user.setPassword(userDto.getPassword());
		  user.setEmail(userDto.getEmail());
		  return user;
	  }
	  
	  public static MailListDto adaptToMailListDto(User user) {
		  MailListDto mailListDto = new MailListDto();
		  mailListDto.setUserId(user.getUserid());
		  mailListDto.setEmail(user.getEmail());
		  user.getSubscriptions().forEach(s -> {
			   String subscription = s.getSubscription();
			   mailListDto.getMailList().add(subscription);
		  });
		  return mailListDto;
	  }
}
