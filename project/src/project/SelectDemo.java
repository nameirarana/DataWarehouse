package project;
import java.sql.SQLException;
import java.io.*;
import java.util.*;


public class SelectDemo {
	public static int offset = 0;
	public static meshJoin mJ = new meshJoin();
	public static List<masterData> masterdata;
	public static List<masterData> m2;
	public static masterData [][] disk = new masterData[10][10];
	
	public static void main(String[] args) {
		getMasterData();
		int indexDisk = 0;
		int indexStream = 0;
		List<transactionData> stream = null;
		
		stream = getTransData();
		List<transactionData> all_Stream = stream;
		while(offset < 10000) {
			mJ.addTransToHash(stream);
			if(indexStream > 9) {
				stream = all_Stream.subList(0, 49);
				mJ.removeTransFromHash(stream);
				all_Stream.subList(0, 49).clear();
			}
			if(indexDisk == 10) {
				indexDisk = 0;
			}
			mJ.mesh_Join(disk[indexDisk]);
			indexDisk++;
			indexStream++;
			stream = getTransData();
			for(transactionData T: stream) {
				all_Stream.add(T);
			}
		}
		
		
		mJ.addTransToHash(stream);

		while(!all_Stream.isEmpty()) {
			if(indexStream > 9) {
				int size = all_Stream.size();
				if (size < 49) {
					stream = all_Stream.subList(0, size);
				}
				else {
					stream = all_Stream.subList(0, 49);
				}
				mJ.removeTransFromHash(stream);
				if(size < 49) {
					all_Stream.subList(0, size).clear();
				}
				else {
					all_Stream.subList(0, 49).clear();
				}
				
			}
			if(indexDisk == 10) {
				indexDisk = 0;
			}
			mJ.mesh_Join(disk[indexDisk]);
			indexDisk++;
			indexStream++;
		}
		//mJ.printHash();
    }
	private static void getMasterData() {
		int index = 0;
		DB db = new DB();
		masterdata = db.DBget_MasterData();
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				disk[i][j] = masterdata.get(index);
				index++;
			}
		}
	}
	

	private static List<transactionData> getTransData() {
	    DB db = new DB();
	    List<transactionData> transdata;
	    transdata = db.DBget_TransactionData(offset);
	    offset += 50;
	    return transdata;
	}


	
}
