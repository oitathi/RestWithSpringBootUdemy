package br.com.example.restwithspringbootudemy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="greeting")
public class Greeting {
	
	@Id
	@Column(name = "greeting_id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "language_id", nullable = false)
	private Language language;
	
	public Greeting() {
	}
	
	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
	
	
	

}
