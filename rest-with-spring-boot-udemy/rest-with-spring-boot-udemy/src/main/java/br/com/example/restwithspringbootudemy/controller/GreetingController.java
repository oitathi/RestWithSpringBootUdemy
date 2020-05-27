package br.com.example.restwithspringbootudemy.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.restwithspringbootudemy.dto.GreetingDto;
import br.com.example.restwithspringbootudemy.model.Greeting;
import br.com.example.restwithspringbootudemy.service.GreetingService;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
	
	@Autowired
	private GreetingService service;
	
	@Autowired
    private ModelMapper modelMapper;
		
	@RequestMapping(method = RequestMethod.GET, produces =  MediaType.APPLICATION_JSON_VALUE)
	public List<GreetingDto> find(@RequestParam Map<String,String> filter){
		List<Greeting> greetings;
		if(!filter.isEmpty()) {
			if(filter.containsKey("content")) {
				greetings = service.findByContent(filter.get("content"));
			}else {
				greetings = service.findByIdiom(filter.get("idiom"));
			}
		}else {
			greetings = service.findAll();
		}
		
		return greetings.stream()
		          .map(this::convertToDto)
		          .collect(Collectors.toList());
	}
	
	@RequestMapping( value = "/{id}",method = RequestMethod.GET, produces =  MediaType.APPLICATION_JSON_VALUE)
	public Greeting findById(@PathVariable("id") long id){
		return service.findById(id);
	}
	
	@RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
	public Greeting create(@RequestBody Greeting greeting){
		return service.create(greeting);
	}
	
	@RequestMapping( method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
	public Greeting update(@RequestBody Greeting greeting){
		return service.update(greeting);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") long id){
		service.delete(id);
	}
	
	private GreetingDto convertToDto(Greeting greeting) {
		return modelMapper.map(greeting, GreetingDto.class);
	}
	
}