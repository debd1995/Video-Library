package videoLib.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import videoLib.controller.MemberDBHandler;
import java.awt.Toolkit;

public class DeleteMember extends JFrame{

	private JPanel contentPane;
	private JTextField txtID;

	
	private void close(){
		this.dispose();
	}
	
	
	public DeleteMember() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteMember.class.getResource("/resources/images/icon.png")));
		setResizable(false);
		setTitle("ABC Library - Delete Member");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 448, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMemberId = new JLabel("Member ID");
		lblMemberId.setBounds(33, 50, 75, 22);
		contentPane.add(lblMemberId);
		
		txtID = new JTextField();
		txtID.setBounds(138, 51, 126, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtID.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Empty Fields.");
				}
				else{
					try {
						int ch=JOptionPane.showConfirmDialog(null, "Are you sure ?");
						if(ch==JOptionPane.YES_OPTION){
							if(MemberDBHandler.searchMember(txtID.getText().trim()) != null ){
								MemberDBHandler.deleteMember(txtID.getText().trim());
								JOptionPane.showMessageDialog(null, "Delete Successful.");
								close();
							}
							else{
								JOptionPane.showMessageDialog(null, "Invalid ID.");
							}
						}
						else if(ch==JOptionPane.NO_OPTION){
							close();
						}				
					} catch (ClassNotFoundException | IOException e) {					
						e.printStackTrace();
					}
				}
			}
		});
		btnDelete.setBounds(322, 50, 89, 23);
		contentPane.add(btnDelete);
	}


}
