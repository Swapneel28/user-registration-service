package no.adstate.user.registration.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import no.adstate.user.registration.dto.MailListDto;
import no.adstate.user.registration.dto.UserDto;
import no.adstate.user.registration.entity.User;
import no.adstate.user.registration.repository.UserRepository;
import no.adstate.user.registration.util.Adapter;
import no.adstate.user.registration.util.JsonConversionUtil;

@Service
public class UserServiceImpl implements UserService {
		
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public UserDto create(UserDto userDto) {
		
		String message = JsonConversionUtil.wrapUserDtoToJsonString(userDto);
		kafkaTemplate.send("user-registration", message);
		return userDto;
	}

	@Override
	public MailListDto getMailList(Integer userid) {
		
		Optional<User> optionalUser = userRepository.findById(userid);
	    if(optionalUser.isPresent()) {
	    	return Adapter.adaptToMailListDto(optionalUser.get());
	    } else {
	    	return null;
	    }
	}
}
