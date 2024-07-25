package model;

import java.io.File;

import javax.servlet.http.Part;

public class ProductModel {

	private String productID;
	private String productName;
	private int price;
	private int stock;
	private String productDescription;
	private String imagePart;
	
	public ProductModel(String productID, String productName, int price, int stock, String productDescription, Part imagePart) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
		this.productDescription = productDescription;
		this.imagePart = getImageUrl(imagePart);
	}


	
	private String getImageUrl(Part part) {
		// Define the directory path to store uploaded user images. This path should be configured elsewhere in the application.
	    String savePath = "C:\\Users\\Dell\\eclipse-workspace\\GalaxyRetailers\\src\\main\\webapp\\images";

	    // Create a File object representing the directory to store uploaded images.
	    File fileSaveDir = new File(savePath);

	    // Initialize the variable to store the extracted image file name.
	    String imageUrlFromPart = null;

	    // Check if the directory to store uploaded images already exists.
	    if (!fileSaveDir.exists()) {
	        // If the directory doesn't exist, create it.
	    	// user mkdirs() method to create multiple or nested folder
	        fileSaveDir.mkdir();
	    }

	    // Get the Content-Disposition header from the request part. This header contains information about the uploaded file, including its filename.
	    String contentDisp = part.getHeader("content-disposition");

	    // Split the Content-Disposition header into individual parts based on semicolons.
	    String[] items = contentDisp.split(";");

	    // Iterate through each part of the Content-Disposition header.
	    for (String s : items) {
	        // Check if the current part starts with "filename" (case-insensitive trim is used).
	        if (s.trim().startsWith("filename")) {
	            // Extract the filename from the current part.
	            // The filename is assumed to be enclosed in double quotes (").
	            // This part removes everything before the equal sign (=) and the double quotes surrounding the filename.
	            imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
	            break; // Exit the loop after finding the filename
	        }
	    }

	    // If no filename was extracted from the Content-Disposition header, set a default filename.
	    if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
	        imageUrlFromPart = "phone1.png";
	    }

	    // Return the extracted or default image file name.
	    return imageUrlFromPart;
	}
	
	public ProductModel() {
		// TODO Auto-generated constructor stub
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getImagePart() {
		return imagePart;
	}

	public void setImagePart(String imagePart) {
		this.imagePart = imagePart;
	}
	
	


	

	
	

}
