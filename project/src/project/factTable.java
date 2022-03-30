package project;

import java.util.Date;

public class factTable {
	int transaction_ID;
	String product_ID;
	String product_Name;
	String supplier_Name;
	String supplier_ID;
	double price;
	String customer_ID;
	String customer_Name;
	String store_ID;
	String store_Name;
	Date t_Date;
	int quantity;
	double total_Sale;
	
	public int getTransactionID() {
        return transaction_ID;
    }
    public void setTransactionID(int transId) {
        this.transaction_ID = transId;
    }
	public String getProductID() {
        return product_ID;
    }
    public void setProductID(String prodId) {
        this.product_ID = prodId;
    }
    public String getProductName() {
        return product_Name;
    }
    public void setProductName(String prodName) {
        this.product_Name =prodName;
    }
    public String getSupplierName() {
        return supplier_Name;
    }
    public void setSupplierName(String suppName) {
        this.supplier_Name = suppName;
    }
    public String getSupplierID() {
        return supplier_ID;
    }
    public void setSupplierID(String suppID) {
        this.supplier_ID = suppID;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double Price) {
        this.price = Price;
    }
    public String getCustomerID() {
        return customer_ID;
    }
    public void setCustomerID(String custId) {
        this.customer_ID =custId;
    }
    public String getCustomerName() {
        return customer_Name;
    }
    public void setCustomerName(String custName) {
        this.customer_Name =custName;
    }
    public String getStoreID() {
        return store_ID;
    }
    public void setStoreID(String storeId) {
        this.store_ID = storeId;
    }
    public String getStoreName() {
        return store_Name;
    }
    public void setStoreName(String storeName) {
        this.store_Name = storeName;
    }
    public Date getTDate() {
        return t_Date;
    }
    public void setTDate(Date tDate) {
        this.t_Date = tDate;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quant) {
        this.quantity = quant;
    }  
    public double getSales() {
        return total_Sale;
    }
    public void setSales() {
        this.total_Sale = quantity * price;
    } 

    public static void displayFactData(factTable factdata) {
    	System.out.println("Transaction ID: " + factdata.getTransactionID());
		System.out.println("Product ID: " + factdata.getProductID());
		System.out.println("Product Name: " + factdata.getProductName());
		System.out.println("Customer ID: " + factdata.getCustomerID());
		System.out.println("Customer Name: " + factdata.getCustomerName());
		System.out.println("Store ID: " + factdata.getStoreID());
		System.out.println("Store Name: " + factdata.getStoreName());
		System.out.println("Supplier ID: " + factdata.getSupplierID());
		System.out.println("Supplier Name: " + factdata.getSupplierName());
		System.out.println("T Date: " + factdata.getTDate());
		System.out.println("Price: " + factdata.getPrice());
		System.out.println("Quantity: " + factdata.getQuantity());
		System.out.println("Total Sale: " + factdata.getSales());
	    System.out.println();
	}
}
