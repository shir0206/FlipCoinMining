package entity;

import java.sql.Timestamp;
import java.util.Date;

public class Transaction {
	
	private final String ID;
	private Integer size;
	private String type;
	private Double fee;
	private String blockAddress;
	private Timestamp additionDate; 
	
	public Timestamp getAdditionDate() {
		return additionDate;
	}

	public void setAdditionDate(Timestamp additionDate) {
		this.additionDate = additionDate;
	}

	public Transaction(String ID, Integer size, String type, Double fee, String blockAddress, Timestamp additionDate) {
		super();
		this.ID = ID;
		this.size = size;
		this.type = type;
		this.fee = fee;
		this.blockAddress = blockAddress;
		this.additionDate = additionDate;
	}

	public Transaction(String ID, Integer size, String type, Double fee, String blockAddress) {
		super();
		this.ID = ID;
		this.size = size;
		this.type = type;
		this.fee = fee;
		this.blockAddress = blockAddress;
	}
	
	public Transaction(String ID, Integer size, String type, double fee) {
		super();
		this.ID = ID;
		this.size = size;
		this.type = type;
		this.fee = fee;
	}
	
	public Transaction(String ID) {
		this.ID = ID;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public String getblockAddress() {
		return blockAddress;
	}

	public void setblockAddress(String blockAddress) {
		this.blockAddress = blockAddress;
	}

	public String getID() {
		return ID;
	}

	@Override
	public String toString() {
		return "Transaction [ID=" + ID + ", size=" + size + ", type=" + type + ", fee=" + fee
				+ ", blockAddress=" + blockAddress + ", additionDate=" + additionDate + "]";
	}

		
}
