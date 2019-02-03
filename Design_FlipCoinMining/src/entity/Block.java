package entity;

import java.util.Date;

public class Block {
	
	private final int ID;
	private Date creationDate;
	private Date creationHour;
	private int size;
	private String minerAddress;
	private String previousBlock;
	
	public Block(int ID, Date creationDate, int size, String minerAddress, String previousBlock) {
		super();
		this.ID = ID;
		this.creationDate = creationDate;
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

	@Override
	public String toString() {
		return "Block [ID=" + ID + ", creationDate=" + creationDate + ", size=" + size + ", minerAddress=" + minerAddress
				+ ", previousBlock=" + previousBlock + "]";
	}
		
}
