package entity;

import java.sql.Timestamp;
import java.util.Date;

public class Riddle {
	
	private final int riddleNumber;
	private Timestamp publishDate;
	private String description;
	private Timestamp solutionTime;
	private String status;
	private int riddleLevel;
	
	
	public Riddle(int riddleNumber, Timestamp publishDate, String description, Timestamp solutionTime, String status,
			int riddleLevel) {
		super();
		this.riddleNumber = riddleNumber;
		this.publishDate = publishDate;
		this.description = description;
		this.solutionTime = solutionTime;
		this.status = status;
		this.riddleLevel = riddleLevel;
	}


	public Timestamp getPublishDate() {
		return publishDate;
	}


	public void setPublishDate(Timestamp publishDate) {
		this.publishDate = publishDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Timestamp getSolutionTime() {
		return solutionTime;
	}


	public void setSolutionTime(Timestamp solutionTime) {
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

	
	
	

}
