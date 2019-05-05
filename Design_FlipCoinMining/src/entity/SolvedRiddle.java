package entity;

import java.sql.Timestamp;

public class SolvedRiddle {

	private final String uniqueAddress;
	private final int riddleNumber;
	private Timestamp time;

	public SolvedRiddle(String uniqueAddress, int riddleNumber, Timestamp time) {
		super();
		this.uniqueAddress = uniqueAddress;
		this.riddleNumber = riddleNumber;
		this.time = time;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
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
