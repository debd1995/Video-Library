package videoLib.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import videoLib.controller.MemberDBHandler;
import videoLib.controller.MovieCDDBHandler;
import videoLib.controller.TransactionDBHandler;
import videoLib.model.MovieCD;
import videoLib.model.Transaction;
import java.awt.Toolkit;

public class IssueCD extends JFrame{
	private JPanel contentPane;
	private JTextField txtTransID;
	private JTextField txtCDID;
	private JTextField txtMemID;
	private JTextField txtDate;
	private JTextField txtRs;
	private String rs = "50";
	
	private String transactionID,movieID,memberID;
	private LocalDate issueDate;
	
	private String genId;
	
	private ArrayList<MovieCD> movieList;
	private MovieCD movieCD;
	
	private int max;

	/**
	 * Launch the application.
	 */
	private void close(){
		this.dispose();
	}
	private void genID(){
		ArrayList<Transaction> transList;
		Iterator itr ;
		try {
			transList = TransactionDBHandler.readFromTransaction();
			if(transList == null){
				genId="201000";
			}
			else{
				transList = TransactionDBHandler.readFromTransaction();
				max = Integer.parseInt(transList.get(0).getTransactionID());
				for(Transaction trans : transList){
					if(max < Integer.parseInt(trans.getTransactionID())){
						max = Integer.parseInt(trans.getTransactionID());
					}
					
				}
				genId = Integer.toString(max+1);
			}
			
			txtTransID.setText(genId);
				
		} catch (ClassNotFoundException | IOException | NullPointerException e) {			
			e.printStackTrace();
		}
	}
	private Transaction createTrans(){
		transactionID=txtTransID.getText();
		movieID=txtCDID.getText().trim();
		memberID=txtMemID.getText().trim();
		issueDate=LocalDate.now();
		
		return new Transaction(transactionID,movieID,memberID,issueDate);
	}
	/**
	 * Create the frame.
	 */
	public IssueCD() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IssueCD.class.getResource("/resources/images/icon.png")));
		setTitle("ABC LIBRARY - Issue CD");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 493, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTransaction = new JLabel("Transaction");
		lblTransaction.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTransaction.setBounds(160, 30, 178, 47);
		contentPane.add(lblTransaction);
		
		JLabel lblCdId = new JLabel("CD ID");
		lblCdId.setBounds(51, 165, 101, 14);
		contentPane.add(lblCdId);
		
		JLabel lblMemberId = new JLabel("Member ID");
		lblMemberId.setBounds(51, 207, 101, 14);
		contentPane.add(lblMemberId);
		
		JLabel lblIssueDate = new JLabel("Issue Date");
		lblIssueDate.setBounds(51, 248, 101, 14);
		contentPane.add(lblIssueDate);
		
		JLabel lblTransactionId = new JLabel("Transaction ID");
		lblTransactionId.setBounds(51, 123, 101, 14);
		contentPane.add(lblTransactionId);
		
		JLabel lblRupee = new JLabel("Rupee");
		lblRupee.setBounds(51, 293, 101, 14);
		contentPane.add(lblRupee);
		
		txtTransID = new JTextField();
		txtTransID.setEditable(false);
		txtTransID.setBounds(203, 120, 221, 20);
		contentPane.add(txtTransID);
		txtTransID.setColumns(10);
		
		txtCDID = new JTextField();
		txtCDID.setBounds(203, 162, 221, 20);
		contentPane.add(txtCDID);
		txtCDID.setColumns(10);
		
		txtMemID = new JTextField();
		txtMemID.setBounds(203, 204, 221, 20);
		contentPane.add(txtMemID);
		txtMemID.setColumns(10);
		
		txtDate = new JTextField();
		txtDate.setBounds(203, 245, 221, 20);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		txtDate.setEditable(false);
		txtDate.setText(LocalDate.now().toString());
		
		txtRs = new JTextField();
		txtRs.setBounds(203, 290, 221, 20);
		contentPane.add(txtRs);
		txtRs.setColumns(10);
		txtRs.setEditable(false);
		txtRs.setText(rs);
		
		genID();
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				if(txtCDID.getText().trim().equals("") || txtMemID.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Empty Fields");
				}
				else{
					try {
						int ch=JOptionPane.showConfirmDialog(null, "Issue CD ?");
						if(ch==JOptionPane.YES_OPTION){
						
							if(MemberDBHandler.searchMember(txtMemID.getText().trim()) != null && MovieCDDBHandler.searchMovieCD(txtCDID.getText().trim()) != null){
								//Decrement NomOfCopies
								movieCD = MovieCDDBHandler.searchMovieCD(txtCDID.getText().trim());
								if(movieCD.getNumOfCopies()>=1){
									movieCD.setNumOfCopies(movieCD.getNumOfCopies()-1);
									MovieCDDBHandler.deleteMovieCD(txtCDID.getText().trim());
									MovieCDDBHandler.addMovieCD(movieCD);
									
									//add trans
									TransactionDBHandler.addTransaction(createTrans());
								
									JOptionPane.showMessageDialog(null, "CD Issued.");
									close();
								}
								else 
									JOptionPane.showMessageDialog(null, "No Copies are Available.");
							}
							else{
								JOptionPane.showMessageDialog(null, "Invalid VideoCD ID or MemberID");
							}
							
						}
						else
							close();
					} catch (ClassNotFoundException | IOException e) {
						JOptionPane.showMessageDialog(null, "Failed to Issue.");
						e.printStackTrace();
					}catch(NullPointerException e){
						JOptionPane.showMessageDialog(null, "Invalid VideoCD ID.");
					}catch (URISyntaxException e) {
						JOptionPane.showMessageDialog(null, "URISyntaxException\nFailed to Issue");
						e.printStackTrace();
					}
				}
				
				
			}
		});
		btnOk.setBounds(192, 366, 89, 23);
		contentPane.add(btnOk);
	}



}
