package videoLib.view;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import videoLib.controller.MemberDBHandler;
import videoLib.controller.TransactionDBHandler;
import videoLib.model.Member;
import videoLib.model.Transaction;

public class ShowTransaction extends JFrame{
	
	private JPanel contentPane;
	private JTable table;
	private String data[][],headings[]={"Transaction ID","MemberID","Member Name","VideoCD ID","Issue Date","Returned"};
	private ArrayList<Transaction> transList;
	private ArrayList<Member> memberList;
	private Member member;
	private String name;
	private JTextField txtMonth;
	private JTextField txtYear;
	private JButton btnShow;
	private int size;

	private void close(){
		this.dispose();
	}
	
	private String findName(String ID){
		try {
			member = MemberDBHandler.searchMember(ID);
			try{
				name = member.getFullName();
			}catch(NullPointerException e){
				name="N/A";
				e.printStackTrace();
			}
		} catch (ClassNotFoundException | IOException | NullPointerException e) {
			name="N/A";
			e.printStackTrace();
		}
		return name;
	}
	
	
	/**
	 * Create the frame.
	 */
	public ShowTransaction() {
		setTitle("ABC Library - All Transactions");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ShowTransaction.class.getResource("/resources/images/icon.png")));
		setBounds(100, 100, 717, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		//1st init of sata[][]
		try {
			int row = 0;
			transList = TransactionDBHandler.readFromTransaction();
			if(transList != null){
				data = new String[transList.size()][headings.length];
				for(Transaction trans : transList){
					data[row][0] = trans.getTransactionID();
					data[row][1] = trans.getMemberID();
					data[row][2] = findName(trans.getMemberID());
					data[row][3] = trans.getMovieID();
					data[row][4] = trans.getIssueDate().toString();
					data[row][5] = Boolean.toString(trans.getIsReturned());
					row++;
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "No Transaction to Show.");
				close();
			}
		} catch (ClassNotFoundException | IOException e) {		
			e.printStackTrace();
		}
		
		
		
		
		
		contentPane.setLayout(null);
		
		table = new JTable(data,headings);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(5, 48, 691, 358);
		contentPane.add(jsp);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"All", "Today", "Monthly"}));
		comboBox.setBounds(5, 17, 113, 20);
		contentPane.add(comboBox);
		
		txtMonth = new JTextField();
		txtMonth.setVisible(false);
		txtMonth.setBounds(154, 17, 34, 20);
		contentPane.add(txtMonth);
		txtMonth.setColumns(10);
		
		JLabel labelSeparator = new JLabel("/");
		labelSeparator.setVisible(false);
		labelSeparator.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelSeparator.setBounds(191, 20, 9, 14);
		contentPane.add(labelSeparator);
		
		txtYear = new JTextField();
		txtYear.setVisible(false);
		txtYear.setBounds(201, 17, 73, 20);
		contentPane.add(txtYear);
		txtYear.setColumns(10);
		
		btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(comboBox.getSelectedItem().toString().equals("All")){
					System.out.println("a");
					//call with all
					txtMonth.setVisible(false);
					txtYear.setVisible(false);
					labelSeparator.setVisible(false);					
					
					
					//initialize data[][]
					try {
						int row = 0;
						transList = TransactionDBHandler.readFromTransaction();
						data = new String[transList.size()][headings.length];
						for(Transaction trans : transList){
							data[row][0] = trans.getTransactionID();
							data[row][1] = trans.getMemberID();
							data[row][2] = findName(trans.getMemberID());
							data[row][3] = trans.getMovieID();
							data[row][4] = trans.getIssueDate().toString();
							data[row][5] = Boolean.toString(trans.getIsReturned());
							row++;
						}
					} catch (ClassNotFoundException | IOException e) {		
						e.printStackTrace();
					}
					table = new JTable(data,headings);
					JScrollPane jsp = new JScrollPane(table);
					jsp.setBounds(5, 48, 691, 358);
					contentPane.add(jsp);
				}
				else if(comboBox.getSelectedItem().toString().equals("Today")){
					System.out.println("t");
					//call with LocalDate.now()   ---today
					txtMonth.setVisible(false);
					txtYear.setVisible(false);
					labelSeparator.setVisible(false);
					//btnShow.setVisible(false);
					
					
					try {
						size = 0;
						transList = TransactionDBHandler.readFromTransaction();
						for(Transaction trans : transList){
							if(trans.getIssueDate().equals(LocalDate.now())){
								size++;
							}
						}
					} catch (ClassNotFoundException | IOException | NullPointerException e) {
						JOptionPane.showMessageDialog(null, "Failed To Display");
						e.printStackTrace();
					}
					
					data = new String[size][headings.length];
					
					
					
					try {
						int row = 0;
						transList = TransactionDBHandler.readFromTransaction();				
						for(Transaction trans : transList){
							if(trans.getIssueDate().equals(LocalDate.now())){
								data[row][0] = trans.getTransactionID();
								data[row][1] = trans.getMemberID();
								data[row][2] = findName(trans.getMemberID());
								data[row][3] = trans.getMovieID();
								data[row][4] = trans.getIssueDate().toString();
								data[row][5] = Boolean.toString(trans.getIsReturned());
								
								
								row++;
							}
						}
						
						table = new JTable(data,headings);
						JScrollPane jsp = new JScrollPane(table);
						jsp.setBounds(5, 48, 691, 358);
						contentPane.add(jsp);
						System.out.println("inside Today");
						//jsp.repaint();
					} catch (ClassNotFoundException | IOException | NullPointerException e) {
						JOptionPane.showMessageDialog(null, "Can't read VideoCD Information.");
						e.printStackTrace();
					}
				}
				else if(comboBox.getSelectedItem().toString().equals("Monthly")){
					System.out.println("m");
					
					
					size = 0;
					try {
						transList = TransactionDBHandler.readFromTransaction();
						for(Transaction trans : transList){
							if(trans.getIssueDate().getMonthValue()==Integer.parseInt(txtMonth.getText().trim()) && trans.getIssueDate().getYear()==Integer.parseInt(txtYear.getText().trim())){
								size++;
							}
						}
					} catch (ClassNotFoundException | IOException e) {
						
						e.printStackTrace();
					}
					
					
					data = new String[size][headings.length];
					
					
					try {
						int row = 0;
						transList = TransactionDBHandler.readFromTransaction();				
						for(Transaction trans : transList){
							if(trans.getIssueDate().getMonthValue()==Integer.parseInt(txtMonth.getText().trim()) && trans.getIssueDate().getYear()==Integer.parseInt(txtYear.getText().trim())){
								data[row][0] = trans.getTransactionID();
								data[row][1] = trans.getMemberID();
								data[row][2] = findName(trans.getMemberID());
								data[row][3] = trans.getMovieID();
								data[row][4] = trans.getIssueDate().toString();
								data[row][5] = Boolean.toString(trans.getIsReturned());
								
								
								row++;
							}
						}
					}catch(ClassNotFoundException | IOException | NumberFormatException e){
						JOptionPane.showMessageDialog(null, "Error while Reading Data");
						close();
					}
						
						table = new JTable(data,headings);
						JScrollPane jsp = new JScrollPane(table);
						jsp.setBounds(5, 48, 691, 358);
						contentPane.add(jsp);						
					
				}
				
			}
		});
		btnShow.setBounds(301, 16, 89, 23);
		contentPane.add(btnShow);
		
		
		
		comboBox.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				if(comboBox.getSelectedItem().toString().equals("Today")){
							//shifted to button
				}
				else if(comboBox.getSelectedItem().toString().equals("All")){
					
					//shifted to button					
					
				}
				else if(comboBox.getSelectedItem().toString().equals("Monthly")){
					//call with month-Year
					//btnShow.setVisible(true);
					txtMonth.setVisible(true);
					txtMonth.setText("MM");	
					txtMonth.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent arg0) {
							// TODO Auto-generated method stub
							if(txtMonth.getText().trim().equals(""))
								txtMonth.setText("MM");
							
						}
						
						@Override
						public void focusGained(FocusEvent arg0) {
							// TODO Auto-generated method stub
							txtMonth.setText("");
							
						}
					});						
					txtYear.setVisible(true);
					txtYear.setText("YYYY");
					txtYear.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent arg0) {
							// TODO Auto-generated method stub
							if(txtYear.getText().trim().equals(""))
								txtYear.setText("YYYY");
						}
						
						@Override
						public void focusGained(FocusEvent arg0) {
							// TODO Auto-generated method stub
							txtYear.setText("");
						}
					});
					labelSeparator.setVisible(true);
				}
				
			}
		});
	}


}
