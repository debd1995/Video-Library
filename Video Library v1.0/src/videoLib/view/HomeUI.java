package videoLib.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import videoLib.controller.MemberDBHandler;
import videoLib.model.Fine;
import videoLib.model.Member;
import videoLib.model.MovieCD;
import videoLib.model.Transaction;


public class HomeUI extends JFrame{

	private JPanel contentPane;
	private Member member;
	private boolean isAdmin;
	private int count;

	
	
	//Checking wheather another HomeUI is running.. then prompt messege
	
		 ///*
		  	public final void onStart(){
			    Preferences prefs;
			    prefs = Preferences.userRoot().node(this.getClass().getName());
			    prefs.put("RUNNING", "true");
			}

			public final void onFinish(){
			    Preferences prefs;
			    prefs = Preferences.userRoot().node(this.getClass().getName());
			    prefs.put("RUNNING", "false");
			}

			public boolean isRunning(){
			    Preferences prefs;
			    prefs = Preferences.userRoot().node(this.getClass().getName());
			    return prefs.get("RUNNING", null) != null ? Boolean.valueOf(prefs.get("RUNNING", null)) : false;
			}

			private void formWindowClosing(java.awt.event.WindowEvent evt) {
			    onFinish();
			}
			
		//*/
			
			
			
	private boolean isAdmin(String ID){
		try {
			member = MemberDBHandler.searchMember(ID);
			if(member.getFullName().equals("Admin"))
				isAdmin = true;
			else 
				 isAdmin = false;
			
		} catch (ClassNotFoundException | IOException e) {	
			isAdmin = false;
			e.printStackTrace();
		}
		return isAdmin;
	}
	
	private void close(){
		this.dispose();
	}
	public static void main(String[] args) {
		new HomeUI().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public HomeUI() {
		
		/*
		 if(isRunning()){
			JOptionPane.showMessageDialog(null, "Another HomeUI is running. Close it first.");
			System.exit(0);
		}
		*/
		
		
		
		
		
		
		
		
		
		setTitle("ABC Library - Home");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(HomeUI.class.getResource("/resources/images/icon.png")));
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ToolBar.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JLabel lblAbcLibrary = new JLabel("ABC Library");
		lblAbcLibrary.setBounds(0, 42, 494, 43);
		lblAbcLibrary.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbcLibrary.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblAbcLibrary.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(lblAbcLibrary);
		
		JPanel loginChooserPanel = new JPanel();
		loginChooserPanel.setBounds(161, 125, 157, 30);
		contentPane.add(loginChooserPanel);
		loginChooserPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblLoginAs = new JLabel("Login as : ");
		lblLoginAs.setDisplayedMnemonic(KeyEvent.VK_A);
		loginChooserPanel.add(lblLoginAs);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Member", "Admin"}));
		loginChooserPanel.add(comboBox);
		
		//Admin card
		JPanel admin = new JPanel();		
		admin.setBounds(43, 202, 409, 84);
		contentPane.add(admin);
		
		JLabel lblUsername = new JLabel("Login ID :");
		lblUsername.setBounds(20, 17, 69, 14);
		JTextField txtUsername = new JTextField();
		txtUsername.setBounds(142, 14, 227, 20);
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(20, 54, 79, 14);
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setBounds(142, 51, 227, 20);
		admin.setLayout(null);
		
		admin.add(lblUsername);
		admin.add(txtUsername);
		admin.add(lblPassword);
		admin.add(txtPassword);
		
		//Login Button
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtUsername.getText().trim().equals("") || txtPassword.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Empty Fields");
				}
				else{
					Member mem;
					try {
						mem=MemberDBHandler.searchMember(txtUsername.getText().trim());
						if(mem==null){
							System.out.println("not");
							JOptionPane.showMessageDialog(null, txtUsername.getText().trim()+" is not registered.");
						}
						else{
							if(mem.getPassword().equals(txtPassword.getText())){							
								if (comboBox.getSelectedItem().toString()=="Admin" && isAdmin(txtUsername.getText().trim())){
									close();
									new AdminArea().setVisible(true);									
								}								
								else if(comboBox.getSelectedItem().toString()=="Member" && !isAdmin(txtUsername.getText().trim())){
									close();
									new MemberArea().setVisible(true);																					
								}
								else if(comboBox.getSelectedItem().toString()=="Member" && isAdmin(txtUsername.getText().trim())){
									JOptionPane.showMessageDialog(null, "Not a Member");
								}
								else if(comboBox.getSelectedItem().toString()=="Admin" && !isAdmin(txtUsername.getText().trim())){
									JOptionPane.showMessageDialog(null, "Not an Admin");
								}
							}
							else
								JOptionPane.showMessageDialog(null, "Wrong Password");
						}
					} catch (ClassNotFoundException | IOException e) {					
						e.printStackTrace();
					}
				}
					
			}
		});
		btnLogin.setMnemonic(KeyEvent.VK_L);
		btnLogin.setBounds(184, 316, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddMember().setVisible(true);
			}
		});
		btnNewButton.setBounds(383, 421, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnTandC = new JButton("T&C");
		btnTandC.setOpaque(false);
		btnTandC.setContentAreaFilled(false);
		btnTandC.setBorderPainted(false);
		btnTandC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TandC().setVisible(true);
			}
		});
		btnTandC.setBounds(22, 421, 89, 23);
		contentPane.add(btnTandC);
		
		
	}


}
