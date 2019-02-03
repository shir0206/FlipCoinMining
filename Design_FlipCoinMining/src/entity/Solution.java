package entity;

public class Solution {

	private final int riddleNumber;
	private int solutionNumber;
	private String result;
	
	
	public Solution(int riddleNumber, int solutionNumber, String result) {
		super();
		this.riddleNumber = riddleNumber;
		this.solutionNumber = solutionNumber;
		this.result = result;
	}


	public int getSolutionNumber() {
		return solutionNumber;
	}


	public void setSolutionNumber(int solutionNumber) {
		this.solutionNumber = solutionNumber;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public int getRiddleNumber() {
		return riddleNumber;
	}


	@Override
	public String toString() {
		return "Solution [riddleNumber=" + riddleNumber + ", solutionNumber=" + solutionNumber + ", result=" + result
				+ "]";
	}
	
	
}
