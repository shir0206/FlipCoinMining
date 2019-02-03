package entity;

public class Participant {
	
	private final int lotteryNumber;
	private final String uniqueAddress;
	private boolean isWinner;
	
	
	public Participant(int lotteryNumber, String uniqueAddress, boolean isWinner) {
		super();
		this.lotteryNumber = lotteryNumber;
		this.uniqueAddress = uniqueAddress;
		this.isWinner = isWinner;
	}


	public boolean isWinner() {
		return isWinner;
	}


	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}


	public int getLotteryNumber() {
		return lotteryNumber;
	}


	public String getUniqueAddress() {
		return uniqueAddress;
	}


	@Override
	public String toString() {
		return "Participant [lotteryNumber=" + lotteryNumber + ", uniqueAddress=" + uniqueAddress + ", isWinner="
				+ isWinner + "]";
	}
	
	
}
