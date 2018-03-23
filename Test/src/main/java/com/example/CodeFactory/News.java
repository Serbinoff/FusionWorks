package com.example.CodeFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
    @NotEmpty
    @Size(min=10, max=100)
	private String name;
    @NotNull
    @Size(min=100, max=2000)
	private String text;
    @NotNull
    @Size(min=50, max=200)
	private String preview;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}
}


