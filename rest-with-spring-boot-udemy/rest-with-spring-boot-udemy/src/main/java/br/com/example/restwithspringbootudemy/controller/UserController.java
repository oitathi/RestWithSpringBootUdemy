package br.com.example.restwithspringbootudemy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.restwithspringbootudemy.model.User;
import br.com.example.restwithspringbootudemy.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET, produces =  MediaType.APPLICATION_JSON_VALUE)
	public List<User> findAll(){
		return service.findAll();
	}
	
	@RequestMapping( value = "/{id}",method = RequestMethod.GET, produces =  MediaType.APPLICATION_JSON_VALUE)
	public User findById(@PathVariable("id") long id){
		return service.findById(id);
	}
	
	@RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
	public User create(@RequestBody User user){
		return service.create(user);
	}
	
	@RequestMapping( method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
	public User update(@RequestBody User user){
		return service.update(user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") long id){
		service.delete(id);
	}

}
