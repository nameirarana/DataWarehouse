package project;
import java.lang.String;
public class masterData {
	String product_ID;
	String product_Name;
	String supplier_Name;
	String supplier_ID;
	double price;
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
	public static void displayMasterData(masterData masterdata) {
	    System.out.println("Product ID: " + masterdata.getProductID());
		System.out.println("Product Name: " + masterdata.getProductName());
		System.out.println("Supplier Name: " + masterdata.getSupplierName());
		System.out.println("Supplier ID: " + masterdata.getSupplierID());
		System.out.println("Price: " + masterdata.getPrice());
	    System.out.println();
	}
}
