package no.adstate.user.registration.service;

import no.adstate.user.registration.dto.MailListDto;
import no.adstate.user.registration.dto.UserDto;

public interface UserService {
	
	public UserDto create(UserDto user);
	
	public MailListDto getMailList(Integer userid);

}
 