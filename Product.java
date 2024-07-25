package model;

public class Product {
    private int productId;
    private String productName;
    private String productDescription;
    private double unitPrice;
    private int stockLevel;

    // Constructor
    public Product(int productId, String productName, String productDescription, double unitPrice, int stockLevel) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.unitPrice = unitPrice;
        this.stockLevel = stockLevel;
    }

    public Product(int productId2, String productName2, double unitPrice2) {
    	this.productId = productId2;
        this.productName = productName2;
        this.unitPrice = unitPrice2;
		
	}
    public Product(String ProductNo) {
        this.productId = productId;
    }
    

	public Product() {
		// TODO Auto-generated constructor stub
	}

	// Getters and setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }
}
