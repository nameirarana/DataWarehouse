package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class meshJoin {
	public static DB db = new DB();
	public static Map<String, ArrayList<factTable>> multiTrans = new HashMap<String, ArrayList<factTable>>();
	private static factTable[] enricjedData;
	public static void addTransToHash(List<transactionData> transdata) {
		for (transactionData Trans : transdata) {
			String pID = Trans.getProductID();
			factTable tempFacts = new factTable();
			tempFacts.setTransactionID(Trans.getTransactionID());
			tempFacts.setProductID(Trans.getProductID());
			tempFacts.setCustomerID(Trans.getCustomerID());
			tempFacts.setCustomerName(Trans.getCustomerName());
			tempFacts.setStoreID(Trans.getStoreID());
			tempFacts.setStoreName(Trans.getStoreName());
			tempFacts.setTDate(Trans.getTDate());
			tempFacts.setQuantity(Trans.getQuantity());
			if (multiTrans.containsKey(pID)){
	
			}
			else{
				multiTrans.put(pID, new ArrayList<factTable>());
			}
			multiTrans.get(pID).add(tempFacts);
		}
		for(String key : multiTrans.keySet()) {
	    	ArrayList<factTable> tempFacts_ = new ArrayList<factTable>();
	    	//System.out.println("\n" + key);
	    	tempFacts_ = multiTrans.get(key);
	    	for (factTable facts : tempFacts_) {
	            //facts.displayFactData(facts);;
	    	
	    	}  
	    	
	    }
	}
	
	public static void mesh_Join(masterData[] masterdata) {
		for(String key : multiTrans.keySet()) {
	    	ArrayList<factTable> tempFacts = new ArrayList<factTable>();
	    	tempFacts = multiTrans.get(key);
	    	for (factTable facts : tempFacts) {
	            for (int i = 0; i < 10; i++){
	    	        if (facts.getProductID().equals(masterdata[i].product_ID)){
	    	        	facts.setProductName(masterdata[i].product_Name);
	    	    		facts.setSupplierName(masterdata[i].getSupplierName());
	    	    		facts.setSupplierID(masterdata[i].getSupplierID());
	    	    		facts.setPrice(masterdata[i].getPrice());
	    	    		facts.setSales();
	    	    		System.out.println("REMOVING");
	    	    		facts.displayFactData(facts);
	    	        }
	            }
	    	}
	    } 
		
	    //System.out.println("Map: " + ht1);
		for(String key : multiTrans.keySet()) {
	    	ArrayList<factTable> tempFacts_ = new ArrayList<factTable>();
	    	//System.out.println("\n" + key);
	    	tempFacts_ = multiTrans.get(key);
	    	for (factTable facts : tempFacts_) {
	            //facts.displayFactData(facts);;
	    	
	    	}  
	    	
	    }
	}
	public static void removeTransFromHash(List<transactionData> transdata) {
		ArrayList<factTable> enrichedData = new ArrayList<factTable>();
		for (transactionData Trans : transdata) {
			String pID = Trans.getProductID();
			int tID = Trans.getTransactionID();
			ArrayList<factTable> tempFacts = new ArrayList<factTable>();
		    //tempFacts = multiTrans.get(pID);
		    for (factTable facts : multiTrans.get(pID)) {
		    	if(facts.getTransactionID()!=tID) {
		    		tempFacts.add(facts);
		    		
		    	}
		    	if(facts.getTransactionID() == tID) {
					db.DBadd_EnrichedProduct(facts);
					db.DBadd_EnrichedCustomer(facts);
					db.DBadd_EnrichedSupplier(facts);
					db.DBadd_EnrichedStore(facts);
					db.DBadd_EnrichedTime(facts);
					enrichedData.add(facts);
					db.DBadd_EnrichedFacts(facts);
		    	}
		    }

			multiTrans.remove(pID);
			multiTrans.put(pID, new ArrayList<factTable>());
			multiTrans.get(pID).addAll(tempFacts);
			
		}
		//removeEnrichedData(enrichedData);
		
	}
	public static void removeEnrichedData(ArrayList<factTable> enrichedData) {
		for(factTable facts: enricjedData) {
			db.DBadd_EnrichedFacts(facts);
		}
	}
	
}
