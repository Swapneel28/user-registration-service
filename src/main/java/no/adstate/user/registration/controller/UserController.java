package no.adstate.user.registration.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import no.adstate.user.registration.dto.MailListDto;
import no.adstate.user.registration.dto.UserDto;
import no.adstate.user.registration.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value ="/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto user) {
		UserDto createdUser = userService.create(user);
	    return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);	    
	}
	
	@GetMapping(value="/maillist/{id}")
	public ResponseEntity<MailListDto> getMailList(@PathVariable("id") Integer id){
		MailListDto mailList = userService.getMailList(id);
		if(mailList == null) {
			return new ResponseEntity<MailListDto>(HttpStatus.NO_CONTENT);
		} else if(mailList.getMailList().isEmpty()) {
			return new ResponseEntity<MailListDto>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<MailListDto>(mailList, HttpStatus.OK);
		}
	}
		
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
           Map<String, String> errors = new HashMap<String, String>();
           ex.getBindingResult().getAllErrors().forEach(e -> errors.put(((FieldError) e).getField(), e.getDefaultMessage()));
           return errors;
	}

}