package videoLib.view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import videoLib.controller.MemberDBHandler;
import videoLib.model.Member;

public class ShowMember extends JFrame{
	
	private JPanel contentPane;
	private JTable table;
	private String data[][],headings[]={"ID","Name","Age","City","State","Email","Phn","Membership Issue Date"};
	private ArrayList<Member> memberList;
	

	
	/**
	 * Create the frame.
	 */
	public ShowMember() {
		setTitle("ABC Library - Show All Members");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ShowMember.class.getResource("/resources/images/icon.png")));
		setBounds(100, 100, 717, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		try {
			int row=0;
			memberList=MemberDBHandler.readFromMembers();
			data = new String[memberList.size()][headings.length];
			for(Member mem : memberList){
				data[row][0] = mem.getMemberId();
				data[row][1] = mem.getFullName();
				data[row][2] = Integer.toString(mem.getAge());
				data[row][3] = mem.getCity();
				data[row][4] = mem.getState();
				data[row][5] = mem.getEmail();
				data[row][6] = Long.toString(mem.getPhn());
				data[row][7] = mem.getMembershipIssueDate().toString();
				row++;
			}
			
		} catch (ClassNotFoundException | IOException e) {			
			e.printStackTrace();
		}
		
		table = new JTable(data,headings);
		JScrollPane jsp = new JScrollPane(table);
		contentPane.add(jsp, BorderLayout.CENTER);
	}


}
