package entity;

import java.util.Date;

public class SolvedRiddle {
	
	
	private final String uniqueAddress;
	private final int riddleNumber;
	private Date time;
	
	
	public SolvedRiddle(String uniqueAddress, int riddleNumber, Date time) {
		super();
		this.uniqueAddress = uniqueAddress;
		this.riddleNumber = riddleNumber;
		this.time = time;
	}


	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}


	public String getUniqueAddress() {
		return uniqueAddress;
	}


	public int getRiddleNumber() {
		return riddleNumber;
	}


	@Override
	public String toString() {
		return "SolvedRiddle [uniqueAddress=" + uniqueAddress + ", riddleNumber=" + riddleNumber + ", time=" + time
				+ "]";
	}
	

}
