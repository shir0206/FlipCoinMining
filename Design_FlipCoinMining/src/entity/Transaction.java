package entity;

import java.util.Date;

public class Transaction {
	
	private final String ID;
	private Integer size;
	private String type;
	private Double fee;
	private String blockAddress;
	private Date additionTime;
	private Date additionDate; 
	
	public Date getAdditionDate() {
		return additionDate;
	}

	public void setAdditionDate(Date additionDate) {
		this.additionDate = additionDate;
	}
//					results.add(new Transaction(rs.getInt(i++), rs.getInt(i++), rs.getString(i++), rs.getDouble(i++), rs.getString(i++), rs.getDate(i++), rs.getDate(i++)));

	public Transaction(String ID, Integer size, String type, Double fee, String blockAddress, Date additionTime, Date additionDate) {
		super();
		this.ID = ID;
		this.size = size;
		this.type = type;
		this.fee = fee;
		this.blockAddress = blockAddress;
		this.additionTime = additionTime;
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

	public void setfee(double fee) {
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
				+ ", blockAddress=" + blockAddress + ", additionDate=" + additionDate + ", additionTime=" + additionTime + "]";
	}

	public Date getAdditionTime() {
		return additionTime;
	}

	public void setAdditionTime(Date additionTime) {
		this.additionTime = additionTime;
	}


		
}
