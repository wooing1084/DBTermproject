import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;

public class posting extends JFrame {

	private JPanel contentPane;
	private JTextField imurl;
	private JTextField hashTextField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		SQLMethods.init();
//		ClientInformation.Logined_id = "abcd";
//		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					posting frame = new posting();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public posting(String user_id) {
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 480, 500);
		setTitle("Twitter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel appbar = new JPanel();
		appbar.setBackground(new Color(255, 255, 255));
		appbar.setBounds(0,0,464,41);
		appbar.setLayout(null);
		contentPane.add(appbar);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(247, 247, 247));
		textArea.setBounds(10, 90, 445, 210);
		textArea.setLineWrap(true);
		
		JScrollPane scrollPane=new JScrollPane(textArea);
		scrollPane.setBounds(66, 40, 398, 215);
		//scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollPane);
		
		ImageIcon back = ImageManager.GetImageUsingFileSystem("src/assets/UI/back.png",25,25);
		JLabel backBtn = new JLabel(back);
		backBtn.setBounds(12, 9, 25, 25);
		backBtn.setBackground(new Color(255, 255,255));
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainFeed();
				dispose();
			}
		});
		appbar.add(backBtn);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setBorder(null);
		scrollPane_1.setBounds(0, 254, 452, 118);
		contentPane.add(scrollPane_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(255, 255, 255));
		scrollPane_1.setViewportView(panel);
		
		
		
		imurl = new JTextField();
		imurl.setBounds(10, 382, 333, 32);
		contentPane.add(imurl);
		
		imurl.setColumns(10);
		
		
		ImageIcon addImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/image_button.png",81,32);
		JButton btnadd = new JButton(addImage);
		btnadd.setBounds(371, 382, 81, 32);
		btnadd.setContentAreaFilled(false);
		btnadd.setOpaque(false);
		contentPane.add(btnadd);
		
		hashTextField = new JTextField();
		hashTextField.setBounds(10, 417, 333, 32);
		contentPane.add(hashTextField);
		hashTextField.setColumns(10);
		
		ImageIcon tagImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/tag.png",81,32);
		Set<String> tags = new HashSet<String>();
		JButton hashTagBtn = new JButton(tagImage);
		hashTagBtn.setContentAreaFilled(false);
		hashTagBtn.setOpaque(false);
		
		
		
		hashTagBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.invalidate(); 
				
				String q1 = "select user_id from user where user_id = \"" + hashTextField.getText() + "\"";
				ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
				
				try {
					if(rs.next()) {
						if(rs.getString(1).compareTo("") == 0)
							return;
					}
					else
						return;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	

				
				tags.add(hashTextField.getText());
			}
		});
		hashTagBtn.setBounds(371, 417, 81, 32);;
		contentPane.add(hashTagBtn);

		List<String> urls = new ArrayList<String>();
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.invalidate(); 
				scrollPane_1.invalidate();
				
				ImageIcon img = ImageManager.GetImageUsingURL(imurl.getText(), 110,110);
				JLabel lb = new JLabel(img);				
				panel.add(lb);
				panel.validate();
				scrollPane_1.validate();
				
				
				urls.add(imurl.getText());
		    }
		    
		});
		
		ImageIcon twit = ImageManager.GetImageUsingFileSystem("src/assets/UI/twit.png",81,32);
		JButton btnpost = new JButton(twit);
		btnpost.setBounds(375, 5, 81, 32);
		btnpost.setContentAreaFilled(false);
		btnpost.setOpaque(false);
		appbar.add(btnpost);
		
		ImageIcon userImage = ImageManager.GetUserProfile(ClientInformation.Logined_id, 50, 50);
		JLabel profileIcon = new JLabel(userImage);
		profileIcon.setBounds(10, 45, 50, 50);
		contentPane.add(profileIcon);
		
		
		
		btnpost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            String content = textArea.getText();
            Connection con = SQLMethods.GetCon();
            List<String> tagList = new ArrayList<String>(tags);
            
            
            SQLMethods.WritePost(con,ClientInformation.Logined_id,content, urls.toArray(new String[0]), tagList.toArray(new String[0]));
            new MainFeed();
            dispose();
		}
	
		
	});
		
		
		setVisible(true);
	}	
}

//태그
//String q1 = "select user_id from user where user_id = \"" + hashTextField.getText() + "\"";
//ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
//
//try {
//	if(rs.next()) {
//		if(rs.getString(1).compareTo("") == 0)
//			return;
//	}
//	else
//		return;
//} catch (SQLException e1) {
//	// TODO Auto-generated catch block
//	e1.printStackTrace();
//}	
//
//tags.add(hashTextField.getText());

//이미지
//btnadd.addActionListener(new ActionListener() {
//	public void actionPerformed(ActionEvent e) {
//		urls.add(imurl.getText());
//    }
//    
//});