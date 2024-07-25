package model;

public class OrderModel {
	private int OrderNo;
	private int userNo;
	private String OrderDate;
	private String OrderStatus;
	
	public int getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(int orderNo) {
		OrderNo = orderNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}
	public String getOrderStatus() {
		return OrderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}
}
