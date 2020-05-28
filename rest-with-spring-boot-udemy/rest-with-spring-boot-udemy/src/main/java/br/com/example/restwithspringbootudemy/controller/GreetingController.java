package br.com.example.restwithspringbootudemy.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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
	
	@RequestMapping(method = RequestMethod.GET, produces =  MediaType.APPLICATION_JSON_VALUE)
	public Page<GreetingDto> find(@RequestParam(required = false) Map<String,String> filter,@RequestParam(required = true) int page,@RequestParam(required = true) int size, @RequestParam(required = true) String sort){
		
		Pageable pageable = PageRequest.of(page, size, Direction.ASC, sort);
		Page<Greeting> greetings;
			
		filter.remove("page");
		filter.remove("size");
		filter.remove("sort");
				
		if(!filter.isEmpty()) {
			if(filter.containsKey("content")) {
				greetings = service.findByContent(filter.get("content"), pageable);
			}else {
				greetings = service.findByIdiom(filter.get("idiom"), pageable);
			}
		}else {
			greetings = service.findAll(pageable);
		}
		
		return GreetingDto.convert(greetings);
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
	
		
		
}