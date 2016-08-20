package videoLib.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;

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

public class ReturnCD extends JFrame{
	private JPanel contentPane;
	private JTextField txtTransID;
	private JTextField txtMemberID;
	private Transaction trans;
	private String fineAmount;	
	private LocalDate schDate;
	private String issueDate;
	private String cdID;
	private JButton btnSubmitCd;
	private MovieCD movieCD;
	private Transaction transaction;

	/**
	 * Launch the application.
	 */
	private void close(){
		this.dispose();
	}
	private String findCDID(String transID){
		try {
			trans = TransactionDBHandler.searchTransaction(transID);
			cdID = trans.getMovieID();
		} catch (ClassNotFoundException | IOException |NullPointerException e) {
			cdID = "N/A";
			e.printStackTrace();
		}
		return cdID;
	}
	private String issueDate(String transID){
		try {
			trans = TransactionDBHandler.searchTransaction(transID);
			issueDate = trans.getIssueDate().toString();
		} catch (ClassNotFoundException | IOException |NullPointerException e) {
			issueDate = "N/A";
			e.printStackTrace();
		}
		return issueDate;
	}
	
	private LocalDate findSchDate(String transID){
		try {
			LocalDate issueDate;
			int day,month,year;
			trans = TransactionDBHandler.searchTransaction(transID);
			issueDate = trans.getIssueDate();			
			schDate = issueDate.plusDays(2);						
		} catch (ClassNotFoundException | IOException | NullPointerException e) {			
			schDate = null;
			e.printStackTrace();
		}
		
		return schDate;
	}
	
	private String findFine(String transID){
		
		try {
			LocalDate issueDate,today;	
			int diff;
			trans = TransactionDBHandler.searchTransaction(transID);
			issueDate=trans.getIssueDate();
			today = LocalDate.now();
			
			diff = today.getDayOfMonth() - issueDate.getDayOfMonth();
			
			
			if(diff>2){
				fineAmount = Integer.toString((diff-2)*10);
			}
			else
				fineAmount = "0.0";
			
		} catch (ClassNotFoundException | IOException |NullPointerException e) {
			fineAmount = "N/A";
			JOptionPane.showMessageDialog(null, "Invalid TransactionID");
			e.printStackTrace();
		}
		return fineAmount;
		
		
	}
	/**
	 * Create the frame.
	 */
	public ReturnCD() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReturnCD.class.getResource("/resources/images/icon.png")));
		setTitle("ABC Library - Return CD");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 572, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTransactionId = new JLabel("Transaction ID");
		lblTransactionId.setBounds(37, 58, 135, 14);
		contentPane.add(lblTransactionId);
		
		JLabel lblMemberId = new JLabel("Member ID");
		lblMemberId.setBounds(37, 96, 135, 14);
		contentPane.add(lblMemberId);
		
		JLabel lblVideocdId = new JLabel("VideoCD ID");
		lblVideocdId.setBounds(37, 136, 135, 14);
		contentPane.add(lblVideocdId);
		
		JLabel lblIssueDate = new JLabel("Issue Date");
		lblIssueDate.setBounds(37, 178, 135, 14);
		contentPane.add(lblIssueDate);
		
		JLabel lblScheduledDate = new JLabel("Scheduled Date");
		lblScheduledDate.setBounds(37, 218, 135, 14);
		contentPane.add(lblScheduledDate);						
				
		JLabel lblActualDate = new JLabel("Returned Date(today)");
		lblActualDate.setBounds(37, 263, 135, 21);
		contentPane.add(lblActualDate);
		
		JLabel lblFineAmount = new JLabel("Fine Amount");
		lblFineAmount.setBounds(37, 306, 135, 14);
		contentPane.add(lblFineAmount);
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(25, 58, 12, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(25, 96, 12, 14);
		contentPane.add(label_1);
		
		JLabel lblNote = new JLabel("NOTE :-");
		lblNote.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNote.setBounds(25, 366, 56, 14);
		contentPane.add(lblNote);
		
		JLabel lblRequired = new JLabel(" Required");
		lblRequired.setBounds(100, 366, 121, 14);
		contentPane.add(lblRequired);
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(91, 366, 12, 14);
		contentPane.add(label_2);
		
		txtTransID = new JTextField();
		txtTransID.setBounds(216, 55, 129, 20);
		contentPane.add(txtTransID);
		txtTransID.setColumns(10);
		
		txtMemberID = new JTextField();
		txtMemberID.setBounds(216, 93, 129, 20);
		contentPane.add(txtMemberID);
		txtMemberID.setColumns(10);
		
		JLabel lblCdid = new JLabel("");
		lblCdid.setBounds(216, 136, 129, 14);
		contentPane.add(lblCdid);
		
		JLabel lblIsdate = new JLabel("");
		lblIsdate.setBounds(216, 178, 129, 14);
		contentPane.add(lblIsdate);
		
		JLabel lblSchdate = new JLabel("");
		lblSchdate.setBounds(216, 218, 129, 14);
		contentPane.add(lblSchdate);
		
		JLabel lblToday = new JLabel("");
		lblToday.setBounds(216, 266, 129, 14);
		contentPane.add(lblToday);
		
		JLabel lblFine = new JLabel("");
		lblFine.setBounds(216, 306, 129, 14);
		contentPane.add(lblFine);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					if(txtTransID.getText().trim().equals("") || txtMemberID.getText().trim().equals("")){
						JOptionPane.showMessageDialog(null, "Empty Fields");
					}
					else{
						if(TransactionDBHandler.searchTransaction(txtTransID.getText().trim()) != null && MemberDBHandler.searchMember(txtMemberID.getText().trim()) != null){
							lblFine.setText(findFine(txtTransID.getText().trim()));
							lblToday.setText(LocalDate.now().toString());
							lblSchdate.setText(findSchDate(txtTransID.getText().trim()).toString());				
							lblIsdate.setText(issueDate(txtTransID.getText().trim()));
							lblCdid.setText(findCDID(txtTransID.getText().trim()));
							
							btnSubmitCd.setEnabled(true);
							btnCheck.setEnabled(false);
						}
						else{
							JOptionPane.showMessageDialog(null, "Invalid TransactionID or MemberID.");
						}
					}
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnCheck.setBounds(388, 237, 113, 23);
		contentPane.add(btnCheck);
		
		btnSubmitCd = new JButton("Submit CD");
		btnSubmitCd.setEnabled(false);
		btnSubmitCd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ch = JOptionPane.showConfirmDialog(null, "Are you sure ?");
				if(ch==JOptionPane.YES_OPTION){
					
					//increment nomOfCopies
					try {
						movieCD = MovieCDDBHandler.searchMovieCD(lblCdid.getText().trim());
						movieCD.setNumOfCopies(movieCD.getNumOfCopies()+1);
						MovieCDDBHandler.deleteMovieCD(lblCdid.getText().trim());
						MovieCDDBHandler.addMovieCD(movieCD);
					} catch (ClassNotFoundException | IOException | NullPointerException e) {
						JOptionPane.showMessageDialog(null, "Invalid VideoCD ID.");
						e.printStackTrace();
					} catch (URISyntaxException e) {
						JOptionPane.showInternalMessageDialog(null, e.getMessage()+" Return Failed");
					}
					
					//set isReturned to true
					try {
						transaction = TransactionDBHandler.searchTransaction(txtTransID.getText().trim());
						transaction.setIsReturned(true);
						TransactionDBHandler.deleteTransaction(txtTransID.getText().trim());
						TransactionDBHandler.addTransaction(transaction);
					} catch (ClassNotFoundException | IOException | NullPointerException e) {
						JOptionPane.showMessageDialog(null, "Invalid VideoCD ID.");
						e.printStackTrace();
					} catch (URISyntaxException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					
					
					JOptionPane.showMessageDialog(null, "Return Successful.");
					close();
				}
			}
		});
		btnSubmitCd.setBounds(388, 302, 113, 23);
		contentPane.add(btnSubmitCd);
	}



}
