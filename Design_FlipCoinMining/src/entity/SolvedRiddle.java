package entity;

import java.util.Date;

public class SolvedRiddle {
	
	
	private final String uniqueAddress;
	private final int riddleNumber;
	private int time;
	
	
	public SolvedRiddle(String uniqueAddress, int riddleNumber, int time) {
		super();
		this.uniqueAddress = uniqueAddress;
		this.riddleNumber = riddleNumber;
		this.time = time;
	}


	public int getTime() {
		return time;
	}


	public void setTime(int time) {
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
