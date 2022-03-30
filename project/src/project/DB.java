package project;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DB {

    String host;
    String uName;
    String uPass;
    Connection con;
    String host_Retail;
    Connection con_2;

    public DB(){
        try {
            host = "jdbc:mysql://127.0.0.1:3306/Transaction_and_MasterData_Generator";
            uName = "nameira";
            uPass = "Onedirection1";
            con = DriverManager.getConnection(host, uName, uPass);
            host = "jdbc:mysql://127.0.0.1:3306/Retail_DW";
            uName = "nameira";
            uPass = "Onedirection1";
            con_2 = DriverManager.getConnection(host, uName, uPass);

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    public  ArrayList<masterData> DBget_MasterData(){
        try {

            System.out.println("Hello");
            String sql = "select * from Masterdata";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();//then execute the statement

            ArrayList<masterData> list = new ArrayList<masterData>();
            while(rs.next()) {
            	masterData masterdata = new masterData();
                masterdata.setProductID(rs.getString("product_ID"));
                masterdata.setProductName(rs.getString("product_Name"));
                masterdata.setSupplierName(rs.getString("supplier_Name"));
                masterdata.setSupplierID(rs.getString("supplier_ID"));
                masterdata.setPrice(rs.getDouble("price"));
                list.add(masterdata);
            }
            return list;

        }
        catch (SQLException err) {
            System.out.println(err.getMessage());
            return null;
        }
    }
    
    
    public ArrayList<transactionData> DBget_TransactionData(int offset) {
    	try {
            String query = "SELECT * FROM TRANSACTIONS ORDER BY TRANSACTION_ID ASC LIMIT ? OFFSET ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, 50);
            statement.setInt(2, offset);
            ResultSet rs = statement.executeQuery();
            
            ArrayList<transactionData> list = new ArrayList<transactionData>();
            while(rs.next()) {
            	transactionData transdata = new transactionData();
            	transdata.setTransactionID(rs.getInt("transaction_ID"));
            	transdata.setProductID(rs.getString("product_ID"));
            	transdata.setCustomerID(rs.getString("customer_ID"));
            	transdata.setCustomerName(rs.getString("customer_Name"));
            	transdata.setStoreID(rs.getString("store_ID"));
            	transdata.setStoreName(rs.getString("store_Name"));
            	transdata.setTDate(rs.getDate("t_date"));
            	transdata.setQuantity(rs.getInt("quantity"));
                list.add(transdata);
            }
            return list;
    	}
    	catch (SQLException err) {
            System.out.println(err.getMessage());
            return null;
        }
    }
    
    public void DBadd_EnrichedProduct(factTable facts){
    	try {
            //Inserting in Product Table
            String query = "SELECT * FROM PRODUCT WHERE product_ID = ?";
            PreparedStatement statement = con_2.prepareStatement(query);
            statement.setString(1, facts.getProductID());
            ResultSet rs = statement.executeQuery();
            if(!rs.next()) {
            	//facts.displayFactData(facts);
            	String query_product = "INSERT INTO PRODUCT(product_ID, product_Name, Price) VALUES(?,?,?)";
                PreparedStatement statement_product = con_2.prepareStatement(query_product);
                statement_product.setString(1,facts.getProductID());
    	        statement_product.setString(2, facts.getProductName());
    	        statement_product.setDouble(3, facts.getPrice());
    	        statement_product.executeUpdate();
            }
            
    	}
    	catch (SQLException err) {
            System.out.println(err.getMessage());
           
        }
	}
    
    public void DBadd_EnrichedCustomer(factTable facts){
    	try {
            String query = "SELECT * FROM CUSTOMER WHERE customer_ID = ?";
            PreparedStatement statement = con_2.prepareStatement(query);
            statement.setString(1, facts.getCustomerID());
            ResultSet rs = statement.executeQuery();
            if(!rs.next()) {
            	String query_customer = "INSERT INTO CUSTOMER(customer_ID, customer_Name) VALUES(?,?)";
            	PreparedStatement statement_customer = con_2.prepareStatement(query_customer);
            	statement_customer.setString(1,facts.getCustomerID());
            	statement_customer.setString(2, facts.getCustomerName());
            	statement_customer.executeUpdate();  
            }
    	}
    	catch (SQLException err) {
            System.out.println(err.getMessage());
           
        }
	}
    public void DBadd_EnrichedSupplier(factTable facts){
    	try {
            String query = "SELECT * FROM SUPPLIER WHERE supplier_ID = ?";
            PreparedStatement statement = con_2.prepareStatement(query);
            statement.setString(1, facts.getSupplierID());
            ResultSet rs = statement.executeQuery();
            if(!rs.next()) {
            	String query_supplier = "INSERT INTO SUPPLIER(supplier_ID, supplier_Name) VALUES(?,?)";
            	PreparedStatement statement_supplier = con_2.prepareStatement(query_supplier);
            	statement_supplier.setString(1,facts.getSupplierID());
            	statement_supplier.setString(2, facts.getSupplierName());
            	statement_supplier.executeUpdate();  
            }
    	}
    	catch (SQLException err) {
            System.out.println(err.getMessage());
        }
	}
  
    public void DBadd_EnrichedStore(factTable facts){
    	try {
            String query = "SELECT * FROM STORE WHERE store_ID = ?";
            PreparedStatement statement = con_2.prepareStatement(query);
            statement.setString(1, facts.getStoreID());
            ResultSet rs = statement.executeQuery();
            if(!rs.next()) {
            	String query_supplier = "INSERT INTO STORE(store_ID, store_Name) VALUES(?,?)";
            	PreparedStatement statement_supplier = con_2.prepareStatement(query_supplier);
            	statement_supplier.setString(1,facts.getStoreID());
            	statement_supplier.setString(2, facts.getStoreName());
            	statement_supplier.executeUpdate();  
            }
    	}
    	catch (SQLException err) {
            System.out.println(err.getMessage());
        }
	}

    
    public void DBadd_EnrichedTime(factTable facts){
    	try {
            String query = "SELECT * FROM TIME WHERE t_Date = ?";
            PreparedStatement statement = con_2.prepareStatement(query);
            statement.setDate(1, (java.sql.Date) facts.getTDate());
            ResultSet rs = statement.executeQuery();
            if(!rs.next()) {
            	String query_supplier = "INSERT INTO TIME(t_Date, t_Day, month, quarter, year) VALUES(?,?,?,?,?)";
            	PreparedStatement statement_supplier = con_2.prepareStatement(query_supplier);
            	SimpleDateFormat formatNowDay = new SimpleDateFormat("dd");
                SimpleDateFormat formatNowMonth = new SimpleDateFormat("MM");
                SimpleDateFormat formatNowYear = new SimpleDateFormat("YYYY");
                Date date = new Date();
                date = facts.getTDate();
                String currentDay = formatNowDay.format(date);
                String currentMonth = formatNowMonth.format(date);
                String currentYear = formatNowYear.format(date);
                String currentQuarter = "";
                String Month1;
                Month Month_ = null;
                int day = Integer.parseInt(currentDay);
                int year = Integer.parseInt(currentYear);
                LocalDate date_string;
                if(currentMonth.equals("01")){
                	Month1 = "January";
                	date_string = LocalDate.of(year, Month_.JANUARY, day);
                }
                else if(currentMonth.equals("02")) {
                	Month1 = "February";
                	 date_string = LocalDate.of(year, Month_.FEBRUARY, day);
                }
                else if(currentMonth.equals("03")) {
                	Month1 = "March";
                	date_string = LocalDate.of(year, Month_.MARCH, day);
                }
                else if(currentMonth.equals("04")) {
                	Month1 = "April";
                	date_string = LocalDate.of(year, Month_.APRIL, day);
                }
                else if(currentMonth.equals("05")) {
                	Month1 = "May";
                	date_string = LocalDate.of(year, Month_.MAY, day);
                }
                else if(currentMonth.equals("06")) {
                	Month1 = "June";
                	date_string = LocalDate.of(year, Month_.JUNE, day);
                }
                else if(currentMonth.equals("07")) {
                	Month1 = "July";
                	 date_string = LocalDate.of(year, Month_.JULY, day);
                }
                else if(currentMonth.equals("08")) {
                	Month1 = "August";
                	date_string = LocalDate.of(year, Month_.AUGUST, day);
                }
                else if(currentMonth.equals("09")) {
                	Month1 = "September";
                	date_string = LocalDate.of(year, Month_.SEPTEMBER, day);
                }
                else if(currentMonth.equals("10")) {
                	Month1 = "October";
                	date_string = LocalDate.of(year, Month_.OCTOBER, day);
                }
                else if(currentMonth.equals("11")) {
                	Month1 = "November";
                	 date_string = LocalDate.of(year, Month_.NOVEMBER, day);
                }
                else {
                	Month1 = "December";
                	date_string = LocalDate.of(year, Month_.DECEMBER, day);
                }

                if(currentMonth.equals("01")  || currentMonth.equals("02") || currentMonth.equals("03")) {
                	currentQuarter = "1";
                	
                }
                else if(currentMonth.equals("04")  || currentMonth.equals("05") || currentMonth.equals("06")) {
                	currentQuarter = "2";
                }
                else if(currentMonth.equals("07")  || currentMonth.equals("08") || currentMonth.equals("09")) {
                	currentQuarter = "3";
                }
                else if(currentMonth.equals("10") || currentMonth.equals("11") || currentMonth.equals("12")) {
                	currentQuarter = "4";
                }
            	statement_supplier.setDate(1,(java.sql.Date) date);
            	statement_supplier.setString(2,date_string.getDayOfWeek().toString());
            	statement_supplier.setString(3, Month1);
            	statement_supplier.setString(4, currentQuarter);
            	statement_supplier.setString(5, currentYear);
            	statement_supplier.executeUpdate();  
            }
    	}
    	catch (SQLException err) {
            System.out.println(err.getMessage());
        }
	}
    public void DBadd_EnrichedFacts(factTable facts){
    	try {
            String query = "SELECT time_ID FROM time WHERE t_date = ?";
            PreparedStatement statement = con_2.prepareStatement(query);
            statement.setDate(1, (java.sql.Date) facts.getTDate());
            ResultSet rs = statement.executeQuery();
            rs.next();
            int t_ID = rs.getInt("time_ID");
            String query_supplier = "INSERT INTO SALES(product_ID, customer_ID, supplier_ID, store_ID, time_ID, quantity, total_Sales) VALUES(?,?,?,?,?,?,?)";
           	PreparedStatement statement_supplier = con_2.prepareStatement(query_supplier);
           	statement_supplier.setString(1,facts.getProductID());
           	statement_supplier.setString(2, facts.getCustomerID());
           	statement_supplier.setString(3,facts.getSupplierID());
           	statement_supplier.setString(4, facts.getStoreID());
           	statement_supplier.setInt(5, t_ID);
           	statement_supplier.setDouble(6,facts.getQuantity());
           	statement_supplier.setDouble(7, facts.getSales());
           	statement_supplier.executeUpdate(); 
        }
    	catch (SQLException err) {
            System.out.println(err.getMessage());
        }
	}
}