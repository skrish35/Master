package persistance.bean;

public class Order {

	int orderId;
	int orderNumber;
	String prodId;

	public Order(int orderNum, String prodId) {
		this.orderNumber = orderNum;
		this.prodId = prodId;
	}

	public Order() {
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

}
