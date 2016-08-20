package videoLib.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import videoLib.controller.MemberDBHandler;
import videoLib.controller.MovieCDDBHandler;
import videoLib.model.Member;
import videoLib.model.MovieCD;
import java.awt.Toolkit;

public class AddCD extends JFrame{

	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtProd;
	private JTextField txtRYear;
	private JTextField txtID;
	private JComboBox comboRating,comboLang;
	private String genID;
	private JTextField txtNumOfCopies;
	private String PermittedNumOfCopies = "3";
	private int max;

	/**
	 * Launch the application.
	 */
	private void close(){
		this.dispose();
	}
	
	private void genID(){		
		ArrayList<MovieCD> movieList;
		Iterator itr ;
		try {
			movieList = MovieCDDBHandler.readFromMovieCD();
			if(movieList == null){
				genID="201300";
			}
			else{
				movieList = MovieCDDBHandler.readFromMovieCD();
				max = Integer.parseInt(movieList.get(0).getMovieID());
				for(MovieCD mov : movieList){
					if(max < Integer.parseInt(mov.getMovieID())){
						max = Integer.parseInt(mov.getMovieID());
					}
					
				}
				genID = Integer.toString(max+1);
			}
			
			txtID.setText(genID);
				
		} catch (ClassNotFoundException | IOException e) {			
			e.printStackTrace();
		}
	}
	
	private MovieCD createCD(){
		int releaseYear,numOfCopies;
		String movieID,rating,title,language,production;
		
		title=txtTitle.getText().trim();
		language=comboLang.getSelectedItem().toString();
		numOfCopies=Integer.parseInt(txtNumOfCopies.getText().trim());
		production=txtProd.getText().trim();
		rating=comboRating.getSelectedItem().toString();
		releaseYear=Integer.parseInt(txtRYear.getText().trim());
		movieID=txtID.getText().trim();
		
		MovieCD mcd = new MovieCD(movieID,title,language,production,releaseYear,rating,numOfCopies);
		return mcd;
	}
	
	/**
	 * Create the frame.
	 */
	public AddCD() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddCD.class.getResource("/resources/images/icon.png")));
		setResizable(false);
		setTitle("ABC Library - Add CD Information");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 430, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(66, 101, 119, 14);
		contentPane.add(lblTitle);
		
		JLabel lblLanguage = new JLabel("Language");
		lblLanguage.setBounds(66, 133, 119, 14);
		contentPane.add(lblLanguage);
		
		JLabel lblProduction = new JLabel("Production");
		lblProduction.setBounds(66, 169, 119, 14);
		contentPane.add(lblProduction);
		
		JLabel lblReleaseYear = new JLabel("Release Year");
		lblReleaseYear.setBounds(66, 211, 119, 14);
		contentPane.add(lblReleaseYear);
		
		JLabel lblRating = new JLabel("Rating");
		lblRating.setBounds(66, 254, 119, 14);
		contentPane.add(lblRating);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(221, 98, 126, 20);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		txtProd = new JTextField();
		txtProd.setBounds(221, 166, 126, 20);
		contentPane.add(txtProd);
		txtProd.setColumns(10);
		
		txtRYear = new JTextField();
		txtRYear.setBounds(221, 208, 126, 20);
		contentPane.add(txtRYear);
		txtRYear.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(66, 64, 119, 14);
		contentPane.add(lblId);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(221, 61, 126, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		genID();
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtTitle.getText().trim().equals("") || txtProd.getText().trim().equals("") || txtRYear.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Empty Fileds.");
				}
				else{
					try {
						MovieCDDBHandler.addMovieCD(createCD());
						JOptionPane.showMessageDialog(null, "Added successfully.");
						close();
					} catch (ClassNotFoundException | IOException e) {					
						e.printStackTrace();
					} catch (URISyntaxException e) {
						JOptionPane.showMessageDialog(null, e.getCause()+"Add CD Failed");
					}
				}
				
			}
		});
		btnSubmit.setBounds(174, 356, 89, 23);
		contentPane.add(btnSubmit);
		
		comboRating = new JComboBox();
		comboRating.setModel(new DefaultComboBoxModel(new String[] {"U/A", "PG/13", "U/G", "A", "Rated"}));
		comboRating.setBounds(221, 251, 126, 20);
		contentPane.add(comboRating);
		
		comboLang = new JComboBox();
		comboLang.setModel(new DefaultComboBoxModel(new String[] {"English", "Hindi", "Bengali"}));
		comboLang.setBounds(221, 130, 126, 20);
		contentPane.add(comboLang);
		
		JLabel lblNumOfCopies = new JLabel("Num Of Copies");
		lblNumOfCopies.setBounds(66, 299, 119, 14);
		contentPane.add(lblNumOfCopies);
		
		txtNumOfCopies = new JTextField();
		txtNumOfCopies.setEditable(false);
		txtNumOfCopies.setBounds(221, 296, 126, 20);
		contentPane.add(txtNumOfCopies);
		txtNumOfCopies.setColumns(10);
		txtNumOfCopies.setText(PermittedNumOfCopies);
	}

}
