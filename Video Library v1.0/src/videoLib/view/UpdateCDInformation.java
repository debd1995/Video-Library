package videoLib.view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import videoLib.controller.MovieCDDBHandler;
import videoLib.model.MovieCD;

public class UpdateCDInformation extends JFrame{
	
	private JPanel contentPane,updatePanel;
	private JTextField txtCDID;
	private JTextField txtTitle;
	private JTextField txtLang;
	private JTextField txtProd;
	private JTextField txtYear;
	private JTextField txtRating;
	private JTextField txtNumOfCopies;
	

	private void close(){
		this.dispose();
	}
	
	private MovieCD createCD(){
		int releaseYear,numOfCopies;
		String movieID,rating,title,language,production;
		
		title=txtTitle.getText().trim();
		language=txtLang.getText().trim();
		numOfCopies=Integer.parseInt(txtNumOfCopies.getText().trim());
		production=txtProd.getText().trim();
		rating=txtProd.getText().trim();
		releaseYear=Integer.parseInt(txtYear.getText().trim());
		movieID=txtCDID.getText().trim();
		
		MovieCD mcd = new MovieCD(movieID,title,language,production,releaseYear,rating,numOfCopies);
		return mcd;
	}
	

	/**
	 * Create the frame.
	 */
	public UpdateCDInformation() {
		setResizable(false);
		setTitle("ABC Library - Update CD Information");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateCDInformation.class.getResource("/resources/images/icon.png")));
		setBounds(100, 100, 602, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVideocdId = new JLabel("VideoCD ID");
		lblVideocdId.setBounds(44, 47, 96, 14);
		contentPane.add(lblVideocdId);
		
		txtCDID = new JTextField();
		txtCDID.setBounds(160, 44, 193, 20);
		contentPane.add(txtCDID);
		txtCDID.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtCDID.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Empty Fields");
				}
				else{
					MovieCD mcd;
					try {
						mcd=MovieCDDBHandler.searchMovieCD(txtCDID.getText().trim());
						txtTitle.setText(mcd.getTitle());
						txtLang.setText(mcd.getLanguage());
						txtProd.setText(mcd.getProduction());
						txtYear.setText(Integer.toString(mcd.getReleaseYear()));
						txtRating.setText(mcd.getRating());
						txtNumOfCopies.setText(Integer.toString(mcd.getNumOfCopies()));
						
						updatePanel.setVisible(true);
						
					} catch (ClassNotFoundException | IOException | NullPointerException e) {
						JOptionPane.showMessageDialog(null, "Invalid ID");
						e.printStackTrace();					
					}
				}
				
			}
		});
		btnSearch.setBounds(400, 43, 89, 23);
		contentPane.add(btnSearch);
		
		updatePanel = new JPanel();
		updatePanel.setVisible(false);
		updatePanel.setBounds(10, 95, 566, 346);
		contentPane.add(updatePanel);
		updatePanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(80, 29, 115, 14);
		updatePanel.add(lblTitle);
		
		JLabel lblLanguage = new JLabel("Language");
		lblLanguage.setBounds(80, 69, 115, 14);
		updatePanel.add(lblLanguage);
		
		JLabel lblProduction = new JLabel("Production");
		lblProduction.setBounds(80, 109, 115, 14);
		updatePanel.add(lblProduction);
		
		JLabel lblReleaseYear = new JLabel("Release Year");
		lblReleaseYear.setBounds(80, 151, 115, 14);
		updatePanel.add(lblReleaseYear);
		
		JLabel lblRating = new JLabel("Rating");
		lblRating.setBounds(80, 194, 115, 14);
		updatePanel.add(lblRating);
		
		JLabel lblNumOfCopies = new JLabel("Num Of Copies");
		lblNumOfCopies.setBounds(80, 239, 115, 14);
		updatePanel.add(lblNumOfCopies);
		
		txtTitle = new JTextField();
		txtTitle.setEditable(false);
		txtTitle.setBounds(246, 26, 218, 20);
		updatePanel.add(txtTitle);
		txtTitle.setColumns(10);
		
		txtLang = new JTextField();
		txtLang.setEditable(false);
		txtLang.setBounds(246, 66, 218, 20);
		updatePanel.add(txtLang);
		txtLang.setColumns(10);
		
		txtProd = new JTextField();
		txtProd.setEditable(false);
		txtProd.setBounds(246, 106, 218, 20);
		updatePanel.add(txtProd);
		txtProd.setColumns(10);
		
		txtYear = new JTextField();
		txtYear.setEditable(false);
		txtYear.setBounds(246, 148, 218, 20);
		updatePanel.add(txtYear);
		txtYear.setColumns(10);
		
		txtRating = new JTextField();
		txtRating.setEditable(false);
		txtRating.setBounds(246, 191, 218, 20);
		updatePanel.add(txtRating);
		txtRating.setColumns(10);
		
		txtNumOfCopies = new JTextField();
		txtNumOfCopies.setBounds(246, 236, 218, 20);
		updatePanel.add(txtNumOfCopies);
		txtNumOfCopies.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtNumOfCopies.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Empty Number Of Copies Fields");
				}
				else{
					MovieCD mcd = createCD();
					
					try {
						MovieCDDBHandler.deleteMovieCD(txtCDID.getText().trim());
						MovieCDDBHandler.addMovieCD(mcd);
						JOptionPane.showMessageDialog(null, "Update Successful.");
						close();
					} catch (ClassNotFoundException | IOException e) {
						JOptionPane.showMessageDialog(null, "Update Failed");
						e.printStackTrace();
					} catch (URISyntaxException e) {
						JOptionPane.showMessageDialog(null, e.getMessage()+"UPDATE FAILED");
					}
				}
				
			}
		});
		btnUpdate.setBounds(375, 293, 89, 23);
		updatePanel.add(btnUpdate);
	}


}
