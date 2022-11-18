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
import java.util.List;

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
	private JTextField textField;

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
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel appbar = new JPanel();
		appbar.setBackground(new Color(255, 255, 255));
		appbar.setBounds(0,0,464,65);
		appbar.setLayout(null);
		contentPane.add(appbar);
		
		ImageIcon logo = ImageManager.GetImageUsingFileSystem("src/assets/logo.png", 50,50);
		
		JLabel Logo = new JLabel(logo);
		Logo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainFeed();
				dispose();
			}
		});
	
		Logo.setBackground(new Color(255, 255,255));
		Logo.setBounds(200, 5, 50, 50);

		
		appbar.add(Logo);
		
		String q1 = "select profile_Image_dir from user where user_id = \"" + ClientInformation.Logined_id + "\";";
		ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
		
		String imgUrl = "";
		try {
			if(rs.next()) {
				imgUrl = rs.getString(1);
			}
			if(imgUrl.compareTo("") == 0)  
				imgUrl = "https://play-lh.googleusercontent.com/38AGKCqmbjZ9OuWx4YjssAz3Y0DTWbiM5HB0ove1pNBq_o9mtWfGszjZNxZdwt_vgHo";
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		ImageIcon userImage = ImageManager.GetImageUsingURL(imgUrl, 50, 50);
		JLabel UserBtn = new JLabel(userImage);
		UserBtn.setBounds(12, 5, 50, 50);
		UserBtn.setBackground(new Color(255, 255,255));
		UserBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				User u=new User(user_id);
				new Profile(user_id);
				dispose();
			}
		});
		appbar.add(UserBtn);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 90, 445, 210);
		textArea.setLineWrap(true);
		
		JScrollPane scrollPane=new JScrollPane(textArea);
		scrollPane.setBounds(10, 90, 445, 205);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollPane);
		
		imurl = new JTextField();
		imurl.setBounds(135, 304, 210, 23);
		contentPane.add(imurl);
		
		imurl.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("image url");
		lblNewLabel.setBounds(66, 308, 60, 15);
		contentPane.add(lblNewLabel);
		
		
		
		JButton btnadd = new JButton("add");
		btnadd.setBounds(355, 304, 100, 25);
		contentPane.add(btnadd);
		
		
		JButton btnpost = new JButton("post");
		btnpost.setBounds(355, 369, 100, 25);
		contentPane.add(btnpost);
		
		textField = new JTextField();
		textField.setBounds(135, 339, 210, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("tag");
		btnNewButton.setBounds(355, 336, 100, 25);
		contentPane.add(btnNewButton);
		
		//

		List<String> urls = new ArrayList<String>();
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				urls.add(imurl.getText());
		    }
		    
		});
		
		btnpost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            String content = textArea.getText();
            Connection con = SQLMethods.GetCon();
            SQLMethods.WritePost(con,ClientInformation.Logined_id,content, urls.toArray(new String[0]));
            new MainFeed();
            dispose();
		}
	
		
	});
		setVisible(true);
	}	
}