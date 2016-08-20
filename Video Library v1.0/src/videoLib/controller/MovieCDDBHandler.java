package videoLib.controller;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import videoLib.model.MovieCD;

public class MovieCDDBHandler {
	private static ArrayList<MovieCD> movieList,temp;	
	private static  boolean del = false;
	private static String sept = File.separator;
	
	//it will read "movieCDInfo.db" file and return "ArrayList<MovieCD>"
	public static ArrayList<MovieCD> readFromMovieCD() throws FileNotFoundException,EOFException,IOException, ClassNotFoundException{
		try {
			InputStream path = ClassLoader.getSystemResourceAsStream(sept+"resources"+sept+"databaseFiles"+sept+"movieCDInfo.dat");
			System.out.println("readFromMovieCD() path = "+path);
			ObjectInputStream oin = new ObjectInputStream(path);	
						
			//reading movieCDInfo.db file
			movieList=(ArrayList<MovieCD>)oin.readObject();	
			oin.close();			
		} catch (EOFException e) {
			movieList = null;
			e.printStackTrace();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return movieList;
	}
	
	//write Specific Arraylist to movieCDInfo.db file
	public static void writeToMovieCD(ArrayList<MovieCD> list) throws FileNotFoundException,IOException, URISyntaxException{
		URL path = MemberDBHandler.class.getClassLoader().getResource(sept+"resources"+sept+"databaseFiles"+sept+"movieCDInfo.dat");
		System.out.println("writeToMovieCD(...) path = "+path);			
		ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(new File(path.toURI())));
		oout.writeObject(list);
		oout.close();	
	}
	
	//Add movieCD
	public static void addMovieCD(MovieCD cd) throws FileNotFoundException, ClassNotFoundException, IOException, URISyntaxException{
		movieList=readFromMovieCD();
		
		if(movieList != null){
			movieList.add(cd);
			writeToMovieCD(movieList);
		}
		else {
			movieList = new ArrayList<MovieCD>();
			movieList.add(cd);
			writeToMovieCD(movieList);
		}
	}
	
	//Delete movieCD
	public static boolean deleteMovieCD(String movieID) throws FileNotFoundException, ClassNotFoundException, IOException, URISyntaxException{
		
		del = false;
		temp= new ArrayList<MovieCD>();
		movieList=readFromMovieCD();
		if(movieList != null){
			for(MovieCD mcd : movieList){
				if(mcd.getMovieID().equals(movieID)){
					del = true;
					continue;
				}
				else
					temp.add(mcd);
			}
			writeToMovieCD(temp);
		}
		
		return del;
	}
	
	//Search MovieCD
	public static MovieCD searchMovieCD(String movID) throws FileNotFoundException, ClassNotFoundException, IOException{
		movieList=readFromMovieCD();
		if(movieList != null){
			for(MovieCD mov : movieList){
				if(mov.getMovieID().equals(movID))
					return mov;
			}
		}
		return null;
	}



}
