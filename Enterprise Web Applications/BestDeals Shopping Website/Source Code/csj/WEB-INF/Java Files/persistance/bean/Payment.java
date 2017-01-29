package persistance.bean;

public class Payment {
	
	int orderNumber;
	String name;
	int value;

	
	public Payment(int orderNum, String name, int value2) {
		this.orderNumber = orderNum;
		this.name = name;
		this.value = value2;
	}
	public Payment() {
		
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	

}
