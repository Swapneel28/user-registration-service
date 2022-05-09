package no.adstate.user.registration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import no.adstate.user.registration.dto.MailListDto;
import no.adstate.user.registration.dto.UserDto;
import no.adstate.user.registration.entity.Subscription;
import no.adstate.user.registration.entity.User;
import no.adstate.user.registration.repository.SubscriptionRepository;
import no.adstate.user.registration.repository.UserRepository;
import no.adstate.user.registration.service.UserService;
import no.adstate.user.registration.util.Adapter;
import no.adstate.user.registration.util.JsonConversionUtil;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private SubscriptionRepository subscriptionRepository;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void testUsernameNullCheckCase() throws Exception {
		
		 UserDto userDto = new UserDto(null,"###@@@@DFR$%^","samir.raju@outlook.com","");
		 String message = JsonConversionUtil.wrapUserDtoToJsonString(userDto);
		 
		 this.mockMvc.perform(post("/user/create")
				 .contentType(MediaType.APPLICATION_JSON_VALUE)
				 .content(message)).andExpect(status().isBadRequest());
		     
	}
	
	@Test
	public void testPasswordNullCheckCase() throws Exception {
		
		 UserDto userDto = new UserDto("Samraju27",null,"samir.raju@outlook.com","Default");
		 String message = JsonConversionUtil.wrapUserDtoToJsonString(userDto);
		 
		 this.mockMvc.perform(post("/user/create")
				 .contentType(MediaType.APPLICATION_JSON_VALUE)
				 .content(message)).andExpect(status().isBadRequest());
		     
	}
	
	@Test
	public void testEmailNullCheckCase() throws Exception {
		
		 UserDto userDto = new UserDto("Samraju27","###@@@@DFR$%^",null,"Default");
		 String message = JsonConversionUtil.wrapUserDtoToJsonString(userDto);
		 
		 this.mockMvc.perform(post("/user/create")
				 .contentType(MediaType.APPLICATION_JSON_VALUE)
				 .content(message)).andExpect(status().isBadRequest());
		     
	}
	
	@Test
	public void testMailListNullCheckCase() throws Exception {
		
		 UserDto userDto = new UserDto("Samraju27","###@@@@DFR$%^","samir.raju@outlook.com",null);
		 String message = JsonConversionUtil.wrapUserDtoToJsonString(userDto);
		 
		 this.mockMvc.perform(post("/user/create")
				 .contentType(MediaType.APPLICATION_JSON_VALUE)
				 .content(message)).andExpect(status().isBadRequest());
		     
	}
	
	@Test
	public void testPasswordMinimumSizeCase() throws Exception {
		
		 UserDto userDto = new UserDto("Samraju27","###@@","samir.raju@outlook.com","Default");
		 String message = JsonConversionUtil.wrapUserDtoToJsonString(userDto);
		 
		 this.mockMvc.perform(post("/user/create")
				 .contentType(MediaType.APPLICATION_JSON_VALUE)
				 .content(message)).andExpect(status().isBadRequest());
		     
	}
	
	@Test
	public void testUserSuccessCase() throws Exception {
		 
		 User user = new User(1,"Samraju27","###@@@@DFR$%^","samir.raju@outlook.com",null);
		 
		 UserDto userDto = new UserDto("Samraju27","###@@@@DFR$%^","samir.raju@outlook.com","");
		 
		 Mockito.when(userRepository.save(user)).thenReturn(user);
		 
		 Mockito.when(userService.create(userDto)).thenReturn(userDto);
		 
		 String message = JsonConversionUtil.wrapUserDtoToJsonString(userDto);
		 
		 this.mockMvc.perform(post("/user/create")
				 .contentType(MediaType.APPLICATION_JSON_VALUE)
				 .content(message)).andExpect(status().isCreated());
		     
	}
	
	@Test
	public void testNoUseridCase() throws Exception {
		
		 Mockito.when(userRepository.findById(Integer.MAX_VALUE)).thenReturn(null);
		 
		 Mockito.when(userService.getMailList(Integer.MAX_VALUE)).thenReturn(null);
		 
		 this.mockMvc.perform(get("/user/maillist/{id}", Integer.MAX_VALUE))
		             .andExpect(status().isNoContent());
				 
	}
	
	@Test
	public void testNoMailListCase() throws Exception {
		Set<Subscription> subscriptions = new HashSet<>();
		User user = new User(1,"Samraju27","###@@@@DFR$%^","samir.raju@outlook.com",subscriptions);
		
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
		
		MailListDto mailListDto = Adapter.adaptToMailListDto(user);
		
		Mockito.when(userService.getMailList(1)).thenReturn(mailListDto);
		
		this.mockMvc.perform(get("/user/maillist/{id}",user.getUserid()))
		             .andExpect(status().isNotFound());
				 
	}
	
	@Test
	public void testMaillistSuccessCase() throws Exception {
		User user = null;
		Set<Subscription> subscriptions = new HashSet<>();
		Subscription subscription = new Subscription();
		subscription.setUser(user);
		subscription.setSubscription("Default");
		subscriptions.add(subscription);
		
		user = new User(1,"Samraju27","###@@@@DFR$%^","samir.raju@outlook.com",subscriptions);
		
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
		
		MailListDto mailListDto = Adapter.adaptToMailListDto(user);
		
		Mockito.when(userService.getMailList(1)).thenReturn(mailListDto);

		String expectedMessage = JsonConversionUtil.wrapMaillistDtoToJsonString(mailListDto);
		
		MvcResult result = this.mockMvc.perform(get("/user/maillist/{id}",user.getUserid()))
		             .andExpect(status().isOk()).andReturn();
		assertEquals(expectedMessage, result.getResponse().getContentAsString());		 
	}

}