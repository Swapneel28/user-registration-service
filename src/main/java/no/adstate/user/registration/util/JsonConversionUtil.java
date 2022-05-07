package no.adstate.user.registration.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import no.adstate.user.registration.dto.MailListDto;
import no.adstate.user.registration.dto.UserDto;

public class JsonConversionUtil {

	private final static ObjectMapper objectMapper = new ObjectMapper();

	public static String wrapUserDtoToJsonString(UserDto userDto) {
		String data = null;
		try {
			data = objectMapper.writeValueAsString(userDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public static UserDto unwrapJsonStringToUserDto(String data) {
		UserDto userDto = null;
		try {
			userDto = objectMapper.readValue(data, UserDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDto;
	}
	
	public static String wrapMaillistDtoToJsonString(MailListDto mailListDto) {
		String data = null;
		try {
			data = objectMapper.writeValueAsString(mailListDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static MailListDto unwrapJsonStringToMaillistDto(String data) {
		MailListDto mailListDto = null;
		try {
			mailListDto = objectMapper.readValue(data, MailListDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailListDto;
	}

}
