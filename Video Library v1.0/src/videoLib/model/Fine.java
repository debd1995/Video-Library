package videoLib.model;

import java.io.Serializable;

public class Fine implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String transactionID,memberID;
	private int fineAmount;
	
	public Fine(){}
	
	public Fine(String transID,String memID,int amount){
		transactionID=transID;
		memberID=memID;
		fineAmount=amount;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public String getMemberID() {
		return memberID;
	}

	public int getFineAmount() {
		return fineAmount;
	}
	
}
