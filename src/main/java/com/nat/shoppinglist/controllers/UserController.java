package com.nat.shoppinglist.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nat.shoppinglist.dto.UserDto;
import com.nat.shoppinglist.models.request.UserDetailsRequestModel;
import com.nat.shoppinglist.models.response.UserDetailsRestModel;
import com.nat.shoppinglist.security.GenerateToken;
import com.nat.shoppinglist.security.SecurityConstants;
import com.nat.shoppinglist.service.services.UserService;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserDetailsRestModel getUser(@PathVariable String id) {

		UserDetailsRestModel returnValue = new UserDetailsRestModel();
		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue;
	}
	@PostMapping
	public ResponseEntity<UserDetailsRestModel>  createUser(@RequestBody UserDetailsRequestModel userDetails) {
		UserDetailsRestModel returnValue = new UserDetailsRestModel();
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);

		UserDto createdUser = userService.createUser(userDto);
		returnValue = modelMapper.map(createdUser, UserDetailsRestModel.class);
		 HttpHeaders responseHeaders = new HttpHeaders();
		 String token = GenerateToken.generateToken(returnValue.getEmail());
		 
		 responseHeaders.set(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		 responseHeaders.set("userID", returnValue.getUserId() );
		 
//		return returnValue;
		 return ResponseEntity.ok().headers(responseHeaders).body(returnValue);
	}

}
