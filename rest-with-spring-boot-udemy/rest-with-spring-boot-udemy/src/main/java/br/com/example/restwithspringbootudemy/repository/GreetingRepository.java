package br.com.example.restwithspringbootudemy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.example.restwithspringbootudemy.model.Greeting;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting,Long> {
	
	List<Greeting> findByContent(String content);
	Page<Greeting> findByContent(String content, Pageable pageable);
	
	//entity+attribute
	List<Greeting> findByLanguageIdiom(String idiom);
	Page<Greeting> findByLanguageIdiom(String idiom, Pageable pageable);

}
