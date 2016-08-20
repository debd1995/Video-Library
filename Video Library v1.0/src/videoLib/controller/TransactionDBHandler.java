package videoLib.controller;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import videoLib.model.Transaction;

public class TransactionDBHandler {

	private static ArrayList<Transaction> transactionList,temp;
	private static String sept = File.separator;
	
	//it will read "transactionInfo.db" file and return "ArrayList<Transaction>"
	public static ArrayList<Transaction> readFromTransaction() throws FileNotFoundException,EOFException,IOException,ClassNotFoundException{
		
		try {
			URL path = MemberDBHandler.class.getClassLoader().getResource(sept+"resources"+sept+"databaseFiles"+sept+"transactionInfo.dat");
			ObjectInputStream oin = new ObjectInputStream(new FileInputStream(new File(path.toURI())));			
					
			transactionList = (ArrayList<Transaction>) oin.readObject();
			
			oin.close();
		} catch (EOFException e) {
			transactionList = null;
			e.printStackTrace();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return transactionList;
	}
	
	//it will write "Specific ArrayList" to "transactionInfo.db" file
	public static void writeToTransaction(ArrayList<Transaction> list) throws FileNotFoundException,IOException, ClassNotFoundException, URISyntaxException{
		
		URL path = MemberDBHandler.class.getClassLoader().getResource(sept+"resources"+sept+"databaseFiles"+sept+"transactionInfo.dat");
		FileOutputStream fout = new  FileOutputStream(new File(path.toURI()));
		ObjectOutputStream oout = new ObjectOutputStream(fout);
		
		oout.writeObject(list);
		
		oout.close();
		fout.close();
	}
	
	//Add Transaction
	public static void addTransaction(Transaction trans) throws FileNotFoundException, ClassNotFoundException, IOException, URISyntaxException{
		transactionList=readFromTransaction();
		if(transactionList != null){
			transactionList.add(trans);
			writeToTransaction(transactionList);
		}
		else{
			transactionList = new ArrayList<Transaction>();
			transactionList.add(trans);
			writeToTransaction(transactionList);
		}
	}
	
	
	//Search Transaction
	public static Transaction searchTransaction(String transID) throws FileNotFoundException, ClassNotFoundException, IOException{
		transactionList=readFromTransaction();
		if( transactionList != null){
			for(Transaction trans : transactionList){
				if(trans.getTransactionID().equals(transID))
					return trans;
			}
		}
		return null;
	}

	public static void deleteTransaction(String ID) throws FileNotFoundException, ClassNotFoundException, IOException, URISyntaxException {
		
		temp= new ArrayList<Transaction>();
		transactionList=readFromTransaction();
		if(transactionList != null){
			for(Transaction trans : transactionList){
				if(trans.getTransactionID().equals(ID))
					continue;
				else
					temp.add(trans);
			}
			writeToTransaction(temp);
		}
		
	}

}
