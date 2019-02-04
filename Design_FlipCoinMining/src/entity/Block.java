package entity;

import java.util.Date;

public class Block {
	
	private final int ID;
	private Date creationDate;
	private Date creationHour;
	private int size;
	private String minerAddress;
	private String previousBlock;
	
	public Block(int ID, Date creationDate, Date creationHour, int size, String minerAddress, String previousBlock) {
		super();
		this.ID = ID;
		this.creationDate = creationDate;
		this.creationHour = creationHour;
		this.size = size;
		this.minerAddress = minerAddress;
		this.previousBlock = previousBlock;
	}

	public Block (int ID) {
		this.ID = ID;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public String getOwner() {
		return minerAddress;
	}

	public void setOwner(String minerAddress) {
		this.minerAddress = minerAddress;
	}

	public String getPreviousBlock() {
		return previousBlock;
	}

	public void setPreviousBlock(String previousBlock) {
		this.previousBlock = previousBlock;
	}

	public int getID() {
		return ID;
	}

	public Date getCreationHour() {
		return creationHour;
	}

	public void setCreationHour(Date creationHour) {
		this.creationHour = creationHour;
	}
	
	@Override
	public String toString() {
		return "Block [ID=" + ID + ", creationDate=" + creationDate + ", creationHour = " + creationHour + ", size=" + size + ", minerAddress=" + minerAddress
				+ ", previousBlock=" + previousBlock + "]";
	}

		
}
