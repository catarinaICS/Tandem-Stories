package domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Story {
	
	@Id
	private String id;
	
	private String title;
	
	@DBRef
	private User creator;
	
	@DBRef
	private Paragraph start;
	
	public Story(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public Paragraph getStart() {
		return start;
	}
	
	public void setStart(Paragraph start) {
		this.start = start;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[Story "+ getId() +"]";
	}
	
}
