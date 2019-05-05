package entity;

public class Bonus {

	private final int bonusNumber;
	private String description;

	public Bonus(int bonusNumber, String description) {
		super();
		this.bonusNumber = bonusNumber;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBonusNumber() {
		return bonusNumber;
	}

	@Override
	public String toString() {
		return "Bonus [bonusNumber=" + bonusNumber + ", description=" + description + "]";
	}

}
