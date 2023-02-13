package in.bushansirgur.restapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notes")
public class NotesModel {
	public static final String SEQUENCE_NAME = "notes_sequence";
	@Id
	private Long id;
	private String name;
	private String title;
	private String content;
	private long unixTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	public long getUnixTime() {return unixTime;}
	public void setUnixTime(long unixTime) {this.unixTime = unixTime;}
	@Override
	public String toString() {
		return "NotesModel [id=" + id + ", name=" + name + ", title=" + title + "]";
	}
}
