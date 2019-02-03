package entity;

import java.util.Date;

public class Lottery {
	
	private final int lotteryNumber;
	private Date date;
	private int maxParticipants;
	private int numberOfWinners;
	private int numberOfBonuses;
	
	public Lottery(int lotteryNumber, Date date, int maxParticipants, int numberOfWinners, int numberOfBonuses) {
		super();
		this.lotteryNumber = lotteryNumber;
		this.date = date;
		this.maxParticipants = maxParticipants;
		this.numberOfWinners = numberOfWinners;
		this.numberOfBonuses = numberOfBonuses;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	public int getNumberOfWinners() {
		return numberOfWinners;
	}

	public void setNumberOfWinners(int numberOfWinners) {
		this.numberOfWinners = numberOfWinners;
	}

	public int getNumberOfBonuses() {
		return numberOfBonuses;
	}

	public void setNumberOfBonuses(int numberOfBonuses) {
		this.numberOfBonuses = numberOfBonuses;
	}

	public int getLotteryNumber() {
		return lotteryNumber;
	}

	@Override
	public String toString() {
		return "Lottery [lotteryNumber=" + lotteryNumber + ", date=" + date + ", maxParticipants=" + maxParticipants
				+ ", numberOfWinners=" + numberOfWinners + ", numberOfBonuses=" + numberOfBonuses + "]";
	}
		
}
