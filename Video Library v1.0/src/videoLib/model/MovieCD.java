package videoLib.model;

import java.io.Serializable;

public class MovieCD implements Serializable{
	private static final long serialVersionUID = 1L;
	private int releaseYear,numOfCopies;
	private String movieID,rating,title,language,production;
	
	public MovieCD(){}
	
	public MovieCD(String mID,String til,String lan,String prod,int yr,String rat,int numofc){
		movieID=mID;
		title=til;
		language=lan;
		production=prod;
		releaseYear=yr;
		rating=rat;
		numOfCopies=numofc;
	}
	
	public void setNumOfCopies(int num){
		this.numOfCopies = num;
	}
	
	public int getNumOfCopies(){
		return numOfCopies;
	}
	
	public String getMovieID() {
		return movieID;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public String getRating() {
		return rating;
	}

	public String getTitle() {
		return title;
	}

	public String getLanguage() {
		return language;
	}

	public String getProduction() {
		return production;
	}


}
