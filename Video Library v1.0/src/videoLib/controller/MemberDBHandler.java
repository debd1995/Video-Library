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
import videoLib.model.Member;

public class MemberDBHandler {

	private static ArrayList<Member> memberList,temp;
	private static String sept = File.separator;
	 
	
	//it will read "memberInfo.db" file and return "ArrayList<Member>"
	public static ArrayList<Member> readFromMembers() throws FileNotFoundException,EOFException,IOException,ClassNotFoundException{
		
		try {
			URL path = MemberDBHandler.class.getClassLoader().getResource(sept+"resources"+sept+"databaseFiles"+sept+"memberInfo.dat");
			System.out.println("path = "+path);
			ObjectInputStream oin = new ObjectInputStream(new FileInputStream(new File(path.toURI())));				
			memberList = (ArrayList<Member>) oin.readObject();			
			oin.close();			
		} catch (EOFException e) {
			memberList = null;
			e.printStackTrace();
		}catch (Exception e) {		
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		
		return memberList;
	}
	
	//it will write "Specific ArrayList" to "memberInfo.db" file
	public static void writeToMembers(ArrayList<Member> list) throws FileNotFoundException,IOException{
		
		try {
			URL path = MemberDBHandler.class.getClassLoader().getResource(sept+"resources"+sept+"databaseFiles"+sept+"memberInfo.dat");
			ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(new File(path.toURI())));		
			oout.writeObject(list);		
			oout.close();
		} catch (URISyntaxException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}		
	}
	
	//Add Member
	public static void addMember(Member member) throws FileNotFoundException, ClassNotFoundException, IOException{
		memberList=readFromMembers();
		if(memberList != null){
			memberList.add(member);
			writeToMembers(memberList);
		}
		else{
			memberList = new ArrayList<Member>();
			memberList.add(member);
			writeToMembers(memberList);
		}
	}
	
	//Delete Member
	public static void deleteMember(String ID) throws FileNotFoundException, ClassNotFoundException, IOException{
		temp= new ArrayList<Member>();
		
		//reading DB file
		memberList=readFromMembers();		
		
		if(memberList != null){
			//deleting member
			for(Member mem : memberList){
				if( mem.getMemberId().equals(ID) )
					continue;
				else
					temp.add(mem);
			}
			//updating DB file
			writeToMembers(temp);
		}
		else{
			System.out.println("No elements To delete");
		}
	}
	
	//Search Member
	public static Member searchMember(String mID) throws FileNotFoundException, ClassNotFoundException, IOException{
		memberList=readFromMembers();		
		if(memberList != null){
			for(Member mem : memberList){
				if(mem.getMemberId().equals(mID))
					return mem;
			}
		}
		return null;
	}
	

}
