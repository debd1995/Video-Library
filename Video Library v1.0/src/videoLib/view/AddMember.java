package videoLib.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import videoLib.controller.MemberDBHandler;
import videoLib.model.Member;
import java.awt.Toolkit;

public class AddMember extends JFrame{

	private JPanel contentPane;
	private JTextField txtMemberID;
	private JTextField txtName;
	private JPasswordField passwordField;
	private JTextField txtAge;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtEmail;
	private JTextField txtPhn;
	private String genId;
	private int max;

	/**
	 * Launch the application.
	 */
	
	private void close(){
		this.dispose();
	}
	
	private void refresh(){
		this.dispose();
		new AddMember().setVisible(true);
	}
	private Member createMember(){
		String name,id,password,city,state,email;
		long phn;
		int age;
		LocalDate date;
		Member member;
		
		name=txtName.getText().trim();
		id=txtMemberID.getText().trim();
		password=passwordField.getText();
		age=Integer.parseInt(txtAge.getText().trim());
		city=txtCity.getText().trim();
		state=txtState.getText().trim();
		email=txtEmail.getText().trim();
		date = LocalDate.now();
		phn= Long.parseLong(txtPhn.getText().trim());
		
		member= new Member(id,name,password,age,city,state,date,email,phn);
		return member;
		
	}
	private void genID(){
		ArrayList<Member> memberList;
		try {
			memberList = MemberDBHandler.readFromMembers();
			if(memberList == null){
				genId="201600";
			}
			else{				
				memberList = MemberDBHandler.readFromMembers();
				max = Integer.parseInt(memberList.get(0).getMemberId());
				for(Member mem : memberList){
					if(max < Integer.parseInt(mem.getMemberId())){
						max = Integer.parseInt(mem.getMemberId());
					}
					
				}
				genId = Integer.toString(max+1);
			}
			
			txtMemberID.setText(genId);
				
		} catch (ClassNotFoundException | IOException | NullPointerException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());									
			new HomeUI().setVisible(true);
			close();
			e.printStackTrace();					
		}
	}
	/**
	 * Create the frame.
	 */
	public AddMember() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddMember.class.getResource("/resources/images/icon.png")));
		setResizable(false);
		setTitle("ABC Library - Registration Form");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 392, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMemberId = new JLabel("Member ID");
		lblMemberId.setBounds(31, 46, 68, 14);
		contentPane.add(lblMemberId);
		
		txtMemberID = new JTextField();
		txtMemberID.setEditable(false);
		txtMemberID.setBounds(163, 40, 143, 20);
		contentPane.add(txtMemberID);
		txtMemberID.setColumns(10);
		
		
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setBounds(31, 96, 68, 14);
		contentPane.add(lblFullName);
		
		txtName = new JTextField();
		txtName.setBounds(163, 90, 143, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(31, 151, 68, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(163, 145, 143, 20);
		contentPane.add(passwordField);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(31, 210, 46, 14);
		contentPane.add(lblAge);
		
		txtAge = new JTextField();
		txtAge.setBounds(163, 207, 143, 20);
		contentPane.add(txtAge);
		txtAge.setColumns(10);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(31, 263, 46, 14);
		contentPane.add(lblCity);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(31, 315, 46, 14);
		contentPane.add(lblState);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(31, 365, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblContactNo = new JLabel("Contact No.");
		lblContactNo.setBounds(31, 422, 68, 14);
		contentPane.add(lblContactNo);
		
		txtCity = new JTextField();
		txtCity.setBounds(163, 260, 143, 20);
		contentPane.add(txtCity);
		txtCity.setColumns(10);
		
		txtState = new JTextField();
		txtState.setBounds(163, 312, 143, 20);
		contentPane.add(txtState);
		txtState.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(163, 362, 143, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPhn = new JTextField();
		txtPhn.setBounds(163, 419, 143, 20);
		contentPane.add(txtPhn);
		txtPhn.setColumns(10);
		
		genID();
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Validation
				if(txtName.getText().trim().equals("") || txtAge.getText().trim().equals("") || txtCity.getText().trim().equals("") || txtEmail.getText().trim().equals("") || txtState.getText().trim().equals("") || txtPhn.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Empty Fields.");
				}
				else if(txtName.getText().trim().equalsIgnoreCase("Admin")){
					JOptionPane.showMessageDialog(null, "Name \""+txtName.getText().trim()+"\" not allowed.");
				}
				else{
					try {
						MemberDBHandler.addMember(createMember());
						JOptionPane.showMessageDialog(null, "Successfully Registered..");
					} catch (ClassNotFoundException e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, e.getMessage());
						close();
					} catch (FileNotFoundException e) {
						JOptionPane.showInternalMessageDialog(null, "File Not Found.");
						close();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						close();
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					refresh();
				}
				
				
				
				
			}
		});
		btnSubmit.setBounds(217, 476, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnTc = new JButton("T&C");
		btnTc.setOpaque(false);
		btnTc.setContentAreaFilled(false);
		btnTc.setBorderPainted(false);
		btnTc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TandC().setVisible(true);
				
			}
		});
		btnTc.setBounds(51, 476, 89, 23);
		contentPane.add(btnTc);
		
		
		

	}

}
