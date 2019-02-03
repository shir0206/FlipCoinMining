package entity;

import java.util.Date;

public class Riddle {
	
	private final int riddleNumber;
	private Date publishDate;
	private Date publishHour;
	private String description;
	private int solutionTime;
	private String status;
	private int riddleLevel;
	
	
	public Riddle(int riddleNumber, Date publishDate, Date publishHour, String description, int solutionTime,
			String status, int riddleLevel) {
		super();
		this.riddleNumber = riddleNumber;
		this.publishDate = publishDate;
		this.publishHour = publishHour;
		this.description = description;
		this.solutionTime = solutionTime;
		this.status = status;
		this.riddleLevel = riddleLevel;
	}
	
	public Date getPublishDate() {
		return publishDate;
	}
	
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public Date getPublishHour() {
		return publishHour;
	}
	
	public void setPublishHour(Date publishHour) {
		this.publishHour = publishHour;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getSolutionTime() {
		return solutionTime;
	}
	
	public void setSolutionTime(int solutionTime) {
		this.solutionTime = solutionTime;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getRiddleLevel() {
		return riddleLevel;
	}
	
	public void setRiddleLevel(int riddleLevel) {
		this.riddleLevel = riddleLevel;
	}
	
	public int getRiddleNumber() {
		return riddleNumber;
	}
	
	@Override
	public String toString() {
		return "Riddle [riddleNumber=" + riddleNumber + ", publishDate=" + publishDate + ", publishHour=" + publishHour
				+ ", description=" + description + ", solutionTime=" + solutionTime + ", status=" + status
				+ ", riddleLevel=" + riddleLevel + "]";
	}
	
	
}
