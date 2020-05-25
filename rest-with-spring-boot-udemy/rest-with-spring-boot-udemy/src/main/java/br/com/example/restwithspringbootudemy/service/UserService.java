package br.com.example.restwithspringbootudemy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.example.restwithspringbootudemy.exception.ResourceNotFoundException;
import br.com.example.restwithspringbootudemy.model.User;
import br.com.example.restwithspringbootudemy.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User create(User greeting) {
		return repository.save(greeting);
	}
	
	public User update (User user) {
		User entity = repository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		entity.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return repository.save(entity);
	}
	
	public void delete(long id) {
		User entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		repository.delete(entity);
	}
	
	public User findById(long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
	}
	
		
	public List<User> findAll(){
		return repository.findAll();
	}


}
