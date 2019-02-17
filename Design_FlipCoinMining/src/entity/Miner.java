package entity;

public class Miner {
		
	private final String uniqueAddress;
	private String name;
	private String password;
	private double digitalProfit;
	private String email;
	

	
	public Miner(String uniqueAddress, String name, String password, double digitalProfit, String email) {
		super();
		this.uniqueAddress = uniqueAddress;
		this.name = name;
		this.password = password;
		this.digitalProfit = digitalProfit;
		this.email = email;
	}

	public Miner(String uniqueAddress) {
		super();
		this.uniqueAddress = uniqueAddress;
	}
	
	public String getName() {
		return name;
	}
	
	public void setname(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public double getDigitalProfit() {
		return digitalProfit;
	}
	
	public void setDigitalProfit(double digitalProfit) {
		this.digitalProfit = digitalProfit;
	}
	
	public String getUniqueAddress() {
		return uniqueAddress;
	}

	@Override
	public String toString() {
		return "Miner [uniqueAddress=" + uniqueAddress + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", digitalProfit=" + digitalProfit + "]";
	}
	
	
	
	
}
