package com.zicartola.ZICartola.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zicartola.ZICartola.dto.UserDTO;
import com.zicartola.ZICartola.entites.User;
import com.zicartola.ZICartola.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserDTO findById(Long id) {
		return new UserDTO(userRepository.findById(id).get());
	}
	
	public List<UserDTO> findAll(){
		List<User> listUser = userRepository.findAll();
		List<UserDTO> listUserDTO = listUser.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return listUserDTO;
		
	}
	
	public void insert(User user) {
		userRepository.save(user);
	}

	
	
}
