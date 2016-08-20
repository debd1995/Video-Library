package videoLib.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Member implements Serializable{
	private static final long serialVersionUID = 1L;
	private int age;
	private String memberID,fullName,password,city,state,email;
	private long phn;
	private LocalDate membershipIssueDate;
	
	public Member(){}
	
	public Member(String i,String name,String pass,int ag,String cit,String stat,LocalDate date,String mailID,long num){
		memberID=i;
		fullName=name;		
		password=pass;
		age=ag;
		city=cit;
		state=stat;
		membershipIssueDate=date;
		email=mailID;
		phn=num;
	}

	public String getEmail() {
		return email;
	}

	public long getPhn() {
		return phn;
	}

	public String getMemberId() {
		return memberID;
	}

	public int getAge() {
		return age;
	}

	public String getFullName() {
		return fullName;
	}

	public String getPassword() {
		return password;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public LocalDate getMembershipIssueDate() {		
		return membershipIssueDate;		
	}

}
