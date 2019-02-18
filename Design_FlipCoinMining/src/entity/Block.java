package entity;

import java.sql.Timestamp;
import java.util.Date;

public class Block {
	
	private final String ID;
	private Timestamp creationDate;
	private int size;
	private String previousBlock;
	private String minerAddress;
	
	public Block(String ID, Timestamp creationDate, int size, String previousBlock, String minerAddress) {
		super();
		this.ID = ID;
		this.creationDate = creationDate;
		this.size = size;
		this.previousBlock = previousBlock;

		this.minerAddress = minerAddress;
	}

	public Block (String ID) {
		this.ID = ID;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public String getMinerAddress() {
		return minerAddress;
	}

	public void setMinerAddress(String minerAddress) {
		this.minerAddress = minerAddress;
	}

	public String getPreviousBlock() {
		return previousBlock;
	}

	public void setPreviousBlock(String previousBlock) {
		this.previousBlock = previousBlock;
	}

	public String getID() {
		return ID;
	}


	
	@Override
	public String toString() {
		return "Block [ID=" + ID + ", creationDate=" + creationDate +  ", size=" + size + ", minerAddress=" + minerAddress
				+ ", previousBlock=" + previousBlock + "]";
	}

		
}
