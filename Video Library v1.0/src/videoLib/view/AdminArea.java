package videoLib.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class AdminArea extends JFrame{
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	private void close(){
		this.dispose();
	}
	/**
	 * Create the frame.
	 */
	public AdminArea() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminArea.class.getResource("/resources/images/icon.png")));
		setTitle("ABC Library - Admin Area");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDeleteMember = new JButton("Delete Member");
		btnDeleteMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DeleteMember().setVisible(true);
			}
		});
		btnDeleteMember.setBounds(63, 84, 164, 23);
		contentPane.add(btnDeleteMember);
		
		JButton btnAddCdInformation = new JButton("Add CD Information");
		btnAddCdInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddCD().setVisible(true);
			}
		});
		btnAddCdInformation.setBounds(63, 137, 164, 23);
		contentPane.add(btnAddCdInformation);
		
		JButton btnDeleteCdInformation = new JButton("Delete CD Information");
		btnDeleteCdInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DeleteCD().setVisible(true);
			}
		});
		btnDeleteCdInformation.setBounds(63, 253, 164, 23);
		contentPane.add(btnDeleteCdInformation);
		
		JButton btnIssueCd = new JButton("Issue  CD");
		btnIssueCd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IssueCD().setVisible(true);
			}
		});
		btnIssueCd.setBounds(271, 137, 165, 23);
		contentPane.add(btnIssueCd);
		
		JButton btnReturnCd = new JButton("Return CD");
		btnReturnCd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ReturnCD().setVisible(true);
			}
		});
		btnReturnCd.setBounds(271, 253, 165, 23);
		contentPane.add(btnReturnCd);
		
		JButton btnShowMember = new JButton("Show All Member");
		btnShowMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ShowMember().setVisible(true);
			}
		});
		btnShowMember.setBounds(272, 84, 164, 23);
		contentPane.add(btnShowMember);
		
		JButton btnShowCd = new JButton("Show All CD");
		btnShowCd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ShowCD().setVisible(true);
			}
		});
		btnShowCd.setBounds(63, 194, 164, 23);
		contentPane.add(btnShowCd);
		
		JButton btnShowTransaction = new JButton("Show Transaction");
		btnShowTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ShowTransaction().setVisible(true);
			}
		});
		btnShowTransaction.setBounds(271, 194, 165, 23);
		contentPane.add(btnShowTransaction);
		
		JButton btnUpdateMember = new JButton("Update Member");
		btnUpdateMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UpdateMember().setVisible(true);
			}
		});
		btnUpdateMember.setBounds(63, 309, 164, 23);
		contentPane.add(btnUpdateMember);
		
		JButton btnUpdateCdInformation = new JButton("Update CD Information");
		btnUpdateCdInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UpdateCDInformation().setVisible(true);
			}
		});
		btnUpdateCdInformation.setBounds(271, 309, 165, 23);
		contentPane.add(btnUpdateCdInformation);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
				new HomeUI().setVisible(true);
			}
		});
		btnLogout.setBounds(384, 371, 89, 23);
		contentPane.add(btnLogout);
	}

}
