package videoLib.view;

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
import java.awt.Toolkit;

public class DeleteCD extends JFrame{
	private JPanel contentPane;
	private JTextField txtID;

	/**
	 * Launch the application.
	 */
	
	private void close(){
		this.dispose();
	}
	/**
	 * Create the frame.
	 */
	public DeleteCD() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteCD.class.getResource("/resources/images/icon.png")));
		setTitle("ABC library - Delete CD Information");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 442, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCdId = new JLabel("CD ID");
		lblCdId.setBounds(34, 70, 46, 14);
		contentPane.add(lblCdId);
		
		txtID = new JTextField();
		txtID.setBounds(93, 67, 116, 20);
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
							if(MovieCDDBHandler.searchMovieCD(txtID.getText().trim()) == null){
								JOptionPane.showMessageDialog(null, "Invalid ID");
							}
							else{
								boolean flag = MovieCDDBHandler.deleteMovieCD(txtID.getText().trim());
								if(flag == true){
									JOptionPane.showMessageDialog(null, "Delete Successful.");
									close();
								}
								else{
									JOptionPane.showMessageDialog(null, "Delete Unsuccessful.");
									close();
								}
							}
																							
						}
						else if(ch==JOptionPane.NO_OPTION){
							close();
						}
						
					} catch (ClassNotFoundException | IOException |NullPointerException e) {
						JOptionPane.showMessageDialog(null, "Invalid ID.");
						e.printStackTrace();
					} catch (URISyntaxException e) {
						JOptionPane.showMessageDialog(null, e.getMessage()+"Delete Failed");
					}
				}
			}
		});
		btnDelete.setBounds(260, 66, 89, 23);
		contentPane.add(btnDelete);
	}



}
