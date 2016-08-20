package videoLib.view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import videoLib.controller.MovieCDDBHandler;
import videoLib.model.MovieCD;

public class ShowCD extends JFrame{
	
	/**
	 * 
	 */
	
	private JPanel contentPane;
	private JTable CDTable;
	private ArrayList<MovieCD> movieList;
	private String headings[]={"ID","Title","Language","Production","Release Year","Rating","Copies"};
	private String data[][];
	
	private void close(){
		this.dispose();
	}
	
	/**
	 * Create the frame.
	 */
	public ShowCD() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ShowCD.class.getResource("/resources/images/icon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 720, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		try {
			int row=0;
			movieList=MovieCDDBHandler.readFromMovieCD();
			if(movieList != null){
				data=new String[movieList.size()][headings.length];						
				//filling up data;			
				for(MovieCD mcd : movieList){
					data[row][0] = mcd.getMovieID();
					data[row][1] = mcd.getTitle();
					data[row][2] = mcd.getLanguage();
					data[row][3] = mcd.getProduction();
					data[row][4] = Integer.toString(mcd.getReleaseYear());
					data[row][5] = mcd.getRating();
					data[row][6] = Integer.toString(mcd.getNumOfCopies());
					
					row++;
				}				
			}
			else{
				JOptionPane.showMessageDialog(null, "No Information to Show.");
				close();
			}
		} catch (ClassNotFoundException | IOException e) {			
			e.printStackTrace();
		}					
		
		CDTable = new JTable(data,headings);
		JScrollPane jsp = new JScrollPane(CDTable); 
		contentPane.add(jsp, BorderLayout.CENTER);
		
		
		
	}


}
