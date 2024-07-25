package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderHistory {
    private int orderNo;
    private int userNo;
    private Date orderDate;
    private String orderStatus;
    private double totalPayableAmount;
    private List<OrderDetails> orderDetails;

    public OrderHistory(int orderNo, int userNo, Date orderDate, String orderStatus, double totalPayableAmount) {
        this.orderNo = orderNo;
        this.userNo = userNo;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.totalPayableAmount = totalPayableAmount;
        this.orderDetails = new ArrayList<>();
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
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

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void addOrderDetail(OrderDetails orderDetail) {
        orderDetails.add(orderDetail);
    }
}