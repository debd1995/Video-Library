package videoLib.controller;

import java.awt.Toolkit;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import videoLib.model.Fine;
import videoLib.view.HomeUI;

public class FineDBHandler {

private static ArrayList<Fine> fineList,temp;
	
	public static ArrayList<Fine> readFromFine() throws FileNotFoundException,EOFException,IOException,ClassNotFoundException {
		try {			
			
			URL path = MemberDBHandler.class.getClassLoader().getResource("resources/databaseFiles/fineInfo.dat");
			ObjectInputStream oin = new ObjectInputStream(new FileInputStream(new File(path.toURI())));
		
			fineList = (ArrayList<Fine>) oin.readObject();
			oin.close();			
		} catch (EOFException e) {
			fineList = null;
			e.printStackTrace();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		return fineList;
	}
	
	public static void writeToFine(ArrayList<Fine> list) throws FileNotFoundException,IOException{
		String path = FineDBHandler.class.getResource("resources/databaseFiles/fineInfo.dat").getFile();
		ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(path));
		oout.writeObject(list);
		oout.close();
	}
	
	
	//Add Fine
	public static void addFine(Fine fine) throws FileNotFoundException, ClassNotFoundException, IOException{
		fineList=readFromFine();
		
		if(fineList != null){
			fineList.add(fine);
			writeToFine(fineList);
		}
		else{
			fineList = new ArrayList<Fine>();
			fineList.add(fine);
			writeToFine(fineList);
		}
	}
	
	//Delete Fine
	public static void deleteFine(String transID) throws FileNotFoundException, ClassNotFoundException, IOException{
		temp= new ArrayList<Fine>();
		fineList=readFromFine();
		
		if(fineList != null){
			for(Fine fin : fineList){
				if(fin.getTransactionID().equals(transID))
					continue;
				else
					temp.add(fin);
			}
			writeToFine(temp);
		}
		else{
			System.out.println("No elements to delete");
		}
	}
	
	//Search Fine
	public static Fine searchFine(String transID) throws FileNotFoundException, ClassNotFoundException, IOException{
		fineList=readFromFine();
		
		if(fineList != null){
			for(Fine fin : fineList){
				if(fin.getTransactionID().equals(transID))
					return fin;
			}
		}
		
		return null;
	}

	
	
	

}
