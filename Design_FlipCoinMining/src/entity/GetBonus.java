package entity;

public class GetBonus {

	private final String uniqueAddress;
	private final int lotteryNumber;
	private final int bonusNumber;

	public GetBonus(String uniqueAddress, int lotteryNumber, int bonusNumber) {
		super();
		this.uniqueAddress = uniqueAddress;
		this.lotteryNumber = lotteryNumber;
		this.bonusNumber = bonusNumber;
	}
	
	public String getUniqueAddress() {
		return uniqueAddress;
	}
	public int getLotteryNumber() {
		return lotteryNumber;
	}
	public int getBonusNumber() {
		return bonusNumber;
	}

	@Override
	public String toString() {
		return "GetBonus [uniqueAddress=" + uniqueAddress + ", lotteryNumber=" + lotteryNumber + ", bonusNumber="
				+ bonusNumber + "]";
	}
	
	
	
}
