package br.com.example.restwithspringbootudemy.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="language")
public class Language {
	
	@Id
	@Column(name = "language_id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String idiom;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "language")
	private Set<Greeting> greetings;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdiom() {
		return idiom;
	}

	public void setIdiom(String idiom) {
		this.idiom = idiom;
	}

	public Set<Greeting> getGreetings() {
		return greetings;
	}

	public void setGreetings(Set<Greeting> greetings) {
		this.greetings = greetings;
	}
	
	
	
}
