package videoLib.view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import videoLib.controller.MemberDBHandler;
import videoLib.model.Member;

public class UpdateMember extends JFrame{
	
	private JPanel contentPane;
	private JPanel updatePanel;
	private JTextField txtMemberID;
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtEmail;
	private JTextField txtPhn;
	private Member mem;
	private String pass;

	
	private void close(){
		this.dispose();
	}
	private Member createMember(){
		String name,id,password,city,state,email;
		long phn;
		int age;
		LocalDate date;
		Member member;

			name=txtName.getText().trim();
			id=txtMemberID.getText().trim();					
			password=findPassword(txtMemberID.getText().trim());
			age=Integer.parseInt(txtAge.getText().trim());
			city=txtCity.getText().trim();
			state=txtState.getText().trim();
			email=txtEmail.getText().trim();
			date = LocalDate.now();
			phn= Long.parseLong(txtPhn.getText().trim());
			
			member= new Member(id,name,password,age,city,state,date,email,phn);
			return member;
		
		
	}
	
	private String findPassword(String ID){		
		try {
			mem=MemberDBHandler.searchMember(ID);
			pass=mem.getPassword();
		} catch (ClassNotFoundException | IOException |NullPointerException e) {			
			JOptionPane.showMessageDialog(null, "Invalid MemberID");
			e.printStackTrace();
		}
		return pass;
		
	}
	
	
	/**
	 * Create the frame.
	 */
	public UpdateMember() {
		setResizable(false);
		setTitle("ABC Library - Update Member");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateMember.class.getResource("/resources/images/icon.png")));
		setBounds(100, 100, 617, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMemberId = new JLabel("Member ID");
		lblMemberId.setBounds(79, 42, 83, 14);
		contentPane.add(lblMemberId);
		
		txtMemberID = new JTextField();
		txtMemberID.setBounds(184, 39, 199, 20);
		contentPane.add(txtMemberID);
		txtMemberID.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtMemberID.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Empty MemberID");
				}
				else{
					try {
						Member member = MemberDBHandler.searchMember(txtMemberID.getText().trim());
						txtName.setText(member.getFullName());
						txtAge.setText(Integer.toString(member.getAge()));
						txtCity.setText(member.getCity());
						txtState.setText(member.getState());
						txtEmail.setText(member.getEmail());
						txtPhn.setText(Long.toString(member.getPhn()));
						
						updatePanel.setVisible(true);
						
					} catch (ClassNotFoundException | IOException |NullPointerException e) {
						JOptionPane.showMessageDialog(null, "Invalid MemberID");
						e.printStackTrace();
					}
				}
			}
		});
		btnSearch.setBounds(416, 38, 89, 23);
		contentPane.add(btnSearch);
		
		updatePanel = new JPanel();
		updatePanel.setVisible(false);
		updatePanel.setBounds(10, 83, 581, 338);
		contentPane.add(updatePanel);
		updatePanel.setLayout(null);
		
		JLabel lblName = new JLabel("Full Name");
		lblName.setBounds(83, 24, 111, 14);
		updatePanel.add(lblName);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(83, 64, 111, 14);
		updatePanel.add(lblAge);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(83, 107, 111, 14);
		updatePanel.add(lblCity);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(83, 150, 111, 14);
		updatePanel.add(lblState);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(83, 191, 111, 14);
		updatePanel.add(lblEmail);
		
		JLabel lblContactNo = new JLabel("Contact No.");
		lblContactNo.setBounds(83, 239, 111, 14);
		updatePanel.add(lblContactNo);
		
		txtName = new JTextField();
		txtName.setBounds(233, 21, 263, 20);
		updatePanel.add(txtName);
		txtName.setColumns(10);
		
		txtAge = new JTextField();
		txtAge.setBounds(233, 61, 263, 20);
		updatePanel.add(txtAge);
		txtAge.setColumns(10);
		
		txtCity = new JTextField();
		txtCity.setBounds(233, 104, 263, 20);
		updatePanel.add(txtCity);
		txtCity.setColumns(10);
		
		txtState = new JTextField();
		txtState.setBounds(233, 147, 263, 20);
		updatePanel.add(txtState);
		txtState.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(233, 188, 263, 20);
		updatePanel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPhn = new JTextField();
		txtPhn.setBounds(233, 236, 263, 20);
		updatePanel.add(txtPhn);
		txtPhn.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtName.getText().trim().equals("") || txtAge.getText().trim().equals("") || txtCity.getText().trim().equals("") || txtEmail.getText().trim().equals("") || txtPhn.getText().trim().equals("") || txtState.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Empty Fields");
				}
				else{
					Member memb;
					memb=createMember();
					try {
						MemberDBHandler.deleteMember(txtMemberID.getText().trim());
						MemberDBHandler.addMember(memb);
						JOptionPane.showMessageDialog(null, "Update Successful.");
						close();					
					} catch (ClassNotFoundException | IOException e) {
						JOptionPane.showMessageDialog(null, "Failed to Update");
						e.printStackTrace();
					}
				}
				
			}
		});
		btnUpdate.setBounds(407, 286, 89, 23);
		updatePanel.add(btnUpdate);
	}


}
