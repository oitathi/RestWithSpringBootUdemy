package br.com.example.restwithspringbootudemy.dto;

import org.springframework.data.domain.Page;

import br.com.example.restwithspringbootudemy.model.Greeting;

public class GreetingDto {
	
	private long id;
	private String content;
	private String idiom;
	
	public GreetingDto(Greeting greeting) {
		this.id = greeting.getId();
		this.content = greeting.getContent();
		this.idiom = greeting.getLanguage().getIdiom();
	}
	
	
	
	public long getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public String getIdiom() {
		return idiom;
	}
	
	public static Page<GreetingDto> convert(Page<Greeting> greetings){
		return greetings.map(GreetingDto::new);
	}
	

}
