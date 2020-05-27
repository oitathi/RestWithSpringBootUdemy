package br.com.example.restwithspringbootudemy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.example.restwithspringbootudemy.model.Greeting;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting,  Long> {
	
	List<Greeting> findByContent(String content);
	
	//entity+attribute
	List<Greeting> findByLanguageIdiom(String idiom);

}
