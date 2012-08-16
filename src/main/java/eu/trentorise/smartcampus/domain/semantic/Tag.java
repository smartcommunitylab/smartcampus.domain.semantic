package eu.trentorise.smartcampus.domain.semantic;

import java.io.Serializable;

public class Tag implements Serializable {
	private static final long serialVersionUID = -101002511141341799L;
	
	private Long id;
	private String name;
	private String description;
	private String summary;

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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
}
