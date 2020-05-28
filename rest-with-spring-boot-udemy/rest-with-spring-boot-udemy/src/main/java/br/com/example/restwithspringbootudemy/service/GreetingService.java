package br.com.example.restwithspringbootudemy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.example.restwithspringbootudemy.exception.ResourceNotFoundException;
import br.com.example.restwithspringbootudemy.model.Greeting;
import br.com.example.restwithspringbootudemy.repository.GreetingRepository;

@Service
public class GreetingService {
	
	@Autowired
	private GreetingRepository repository;
	
	public Greeting create(Greeting greeting) {
		return repository.save(greeting);
	}
	
	public Greeting update (Greeting greeting) {
		Greeting entity = repository.findById(greeting.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		entity.setContent(greeting.getContent());
		return repository.save(entity);
	}
	
	public void delete(long id) {
		Greeting entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		repository.delete(entity);
	}
	
	public Greeting findById(long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
	}
	
	public List<Greeting> findByContent(String content) {
		return repository.findByContent(content);
	}
	
	public Page<Greeting> findByContent(String content, Pageable pageable) {
		return repository.findByContent(content, pageable);
	}
	
	public List<Greeting> findByIdiom(String idiom){
		return repository.findByLanguageIdiom(idiom);
	}
	
	public Page<Greeting> findByIdiom(String idiom, Pageable pageable){
		return repository.findByLanguageIdiom(idiom, pageable);
	}
	
	public List<Greeting> findAll(){
		return repository.findAll();
	}
	
	public Page<Greeting> findAll(Pageable pageable){
		return repository.findAll(pageable);
	}

}
