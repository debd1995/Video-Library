package videoLib.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import videoLib.controller.MovieCDDBHandler;
import videoLib.model.MovieCD;
import java.awt.Toolkit;

public class MemberArea extends JFrame{
	private JPanel contentPane;
	private JComboBox comboBox;
	private JTable table;
	private ArrayList<MovieCD> movieList;
	private String data[][];
	private int releaseYear;
	private int rowCount; 
	private String movieID,rating,title,language,production,headings[]={"ID","Title","Language","Production","Release Year","Rating","Copies"};
	private String movieNames[];
	private int size ;
	
	

	
	private void close(){
		this.dispose();
	}
	/**
	 * Create the frame.
	 */
	public MemberArea() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MemberArea.class.getResource("/resources/images/icon.png")));
		setResizable(false);
		setTitle("ABC Library - Member Area");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome ");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblWelcome.setBounds(261, 28, 105, 38);
		contentPane.add(lblWelcome);
		
		JLabel lblSearchOption = new JLabel("Search Option");
		lblSearchOption.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSearchOption.setBounds(59, 100, 120, 20);
		contentPane.add(lblSearchOption);
		
		JButton btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				size = 0;
				//counting num of video title matches
				try {
					movieList = MovieCDDBHandler.readFromMovieCD();
					
					for(MovieCD mcd : movieList){
						if(comboBox.getSelectedItem().toString().equals(mcd.getTitle()))
							size++;
					}
				} catch (ClassNotFoundException | IOException | NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Can't read VideoCD Information.");
					e.printStackTrace();
				}
				
				//creating object
				data = new String[size][headings.length];
				
				//initializing data[][]
				try {
					int row = 0;
					movieList = MovieCDDBHandler.readFromMovieCD();					
					for(MovieCD mcd : movieList){
						if(comboBox.getSelectedItem().toString().equals(mcd.getTitle())){
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
					
					table = new JTable(data,headings);
					
					JScrollPane jsp = new JScrollPane(table);
					jsp.setBounds(10, 226, 644, 246);
					table.setBounds(10, 222, 645, 344);
					contentPane.add(jsp);
				} catch (ClassNotFoundException | IOException | NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Can't read VideoCD Information.");
					e.printStackTrace();
				}		
			}
		});
		btnDisplay.setBounds(394, 173, 89, 23);
		contentPane.add(btnDisplay);
		
		table = new JTable();
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(10, 226, 644, 246);
		table.setBounds(10, 222, 645, 344);
		contentPane.add(jsp);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
				new HomeUI().main(null);
			}
		});
		btnLogout.setBounds(560, 483, 89, 23);
		contentPane.add(btnLogout);
		
		JLabel lblSelectTitle = new JLabel("Select Title");
		lblSelectTitle.setBounds(59, 177, 89, 14);
		contentPane.add(lblSelectTitle);
		
		try {
			int i=0;
			movieList=MovieCDDBHandler.readFromMovieCD();
			if(movieList == null){
				JOptionPane.showMessageDialog(null, "No information to show.");
				//close();
			}
			movieNames = new String[movieList.size()];
			for(MovieCD mcd : movieList){
				movieNames[i] = mcd.getTitle();
				i++;
			}
		} catch (ClassNotFoundException | IOException e) {
			JOptionPane.showMessageDialog(null, "Cant read VideoCD Information");
			e.printStackTrace();
		}catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Can't initialize ComboBox");
		}
		
		comboBox = new JComboBox(movieNames);
		comboBox.setBounds(172, 174, 160, 20);
		contentPane.add(comboBox);
	}


}
