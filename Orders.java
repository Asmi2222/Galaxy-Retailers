package model;

import java.util.Date;

public class Orders {
    private int orderId;
    private int userId;
    private Date orderDate;
    private String orderStatus;
    private double totalPayableAmount;

    // Constructor
    public Orders(int orderId, int userId, Date orderDate, String orderStatus, double totalPayableAmount) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.totalPayableAmount = totalPayableAmount;
    }

    // Getters and setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getTotalPayableAmount() {
        return totalPayableAmount;
    }

    public void setTotalPayableAmount(double totalPayableAmount) {
        this.totalPayableAmount = totalPayableAmount;
    }
}
