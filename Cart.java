package model;

public class Cart {
    private int cartNo;
    private int userNo;
    private int productNo;
    private int quantity;
    private int productStockLevel;
	private String productName;
	private double unitPrice;
    

    // Constructor
    public Cart(int cartNo, int userNo, int productNo, int quantity, int productStockLevel) {
        this.cartNo = cartNo;
        this.userNo = userNo;
        this.productNo = productNo;
        this.quantity = quantity;
        this.productStockLevel = productStockLevel;
    }
    
    public Cart(int cartNo, int productNo, String productName, int quantity, double unitPrice) {
        this.cartNo = cartNo;
        this.productNo = productNo;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    

    public Cart(int cartNo2, int userId, int productNo2, int quantity2) {
		this.cartNo = cartNo2;
		this.userNo = userId;
		this.productNo = productNo2;
		this.quantity = quantity2;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	// Getters and setters
    public int getCartNo() {
        return cartNo;
    }

    public void setCartNo(int cartNo) {
        this.cartNo = cartNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductStockLevel() {
        return productStockLevel;
    }

    public void setProductStockLevel(int productStockLevel) {
        this.productStockLevel = productStockLevel;
    }

	@Override
	public String toString() {
		return "Cart [cartNo=" + cartNo + ", userNo=" + userNo + ", productNo=" + productNo + ", quantity=" + quantity
				+ ", productStockLevel=" + productStockLevel + ", productName=" + productName + ", unitPrice="
				+ unitPrice + "]";
	}

   
}
