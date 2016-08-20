package videoLib.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 1L;
	int fine;
	//  transactionID <==> invoice NO.
	private String transactionID,movieID,memberID;
	private LocalDate issueDate;
	boolean isReturned;
	
	public Transaction(){}
	
	public Transaction(String tid,String movid,String memid,LocalDate idate){
		transactionID=tid;
		movieID=movid;
		memberID=memid;
		issueDate=idate;
	}
	
	public void setIsReturned(boolean set){
		isReturned = set;
	}
	
	public boolean getIsReturned(){
		return isReturned;
	}
	
	public void setFine(int rs){
		fine+=rs;
	}
	
	public int getFine(){
		return fine;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public String getMovieID() {
		return movieID;
	}

	public String getMemberID() {
		return memberID;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

}
