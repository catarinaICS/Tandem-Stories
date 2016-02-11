package domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Paragraph {
	
	@Id
	private String id;
	
	private String text;
	
	@DBRef
	private User author;
	@DBRef
	private Story story;
	
	@DBRef
	private Paragraph next;
	
	public Paragraph(String text) {
		this.text = text;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Story getStory() {
		return story;
	}
	public void setStory(Story story) {
		this.story = story;
	}
	
	public Paragraph getNext() {
		return next;
	}
	
	public void setNext(Paragraph next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		return "[Paragraph " + getId() + " " + getText() + "]"; 
	}
	
}
