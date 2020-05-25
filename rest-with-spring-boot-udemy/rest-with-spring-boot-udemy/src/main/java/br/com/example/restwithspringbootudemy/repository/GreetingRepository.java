package br.com.example.restwithspringbootudemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.example.restwithspringbootudemy.model.Greeting;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting,  Long> {

}
