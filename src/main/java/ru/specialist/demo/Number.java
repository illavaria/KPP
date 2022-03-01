package ru.specialist.demo;

public class Number {

	private String parity;
	private String simplicity;
	private int number;
	
	public Number(String parity, String simplicity, int number) {
		super();
		this.parity = parity;
		this.simplicity=simplicity;
		this.number = number;
	}
	public String getParity() {
		return parity;
	}
	public void setParity(String parity) {
		this.parity = parity;
	}
	public String getSimplicity() {
		return simplicity;
	}
	public void setSimplicity(String simplicity) {
		this.parity = simplicity;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

}
