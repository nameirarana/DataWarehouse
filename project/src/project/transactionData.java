package project;
import java.util.Date;
import java.util.List;
public class transactionData {
	
	int transaction_ID;
	String product_ID;
	String customer_ID;
	String customer_Name;
	String store_ID;
	String store_Name;
	Date t_Date;
	int quantity;
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
	public static void displayTransData(transactionData transdata) {
		System.out.println("Transaction ID: " + transdata.getTransactionID());
		System.out.println("Product ID: " + transdata.getProductID());
		System.out.println("Customer ID: " + transdata.getCustomerID());
		System.out.println("Customer Name: " + transdata.getCustomerName());
		System.out.println("Store ID: " + transdata.getStoreID());
		System.out.println("Store Name: " + transdata.getStoreName());
		System.out.println("T Date: " + transdata.getTDate());
		System.out.println("Quantity: " + transdata.getQuantity());
	    System.out.println();
	}
}
