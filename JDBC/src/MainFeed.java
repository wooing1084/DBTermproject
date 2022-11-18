import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JLayeredPane;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFeed extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public MainFeed() {
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 480, 800);
		setTitle("Twitter");
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		JPanel appbar = new JPanel();
		appbar.setBackground(new Color(255, 255, 255));
		appbar.setPreferredSize(new Dimension(464,65));
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
		Logo.setBounds(12, 5, 50, 50);
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
				new Profile(ClientInformation.Logined_id);
				dispose();
			}
		});

		
		appbar.add(UserBtn);
		
		ImageIcon searchIcon = ImageManager.GetImageUsingFileSystem("src/assets/UI/search.png",30,30);
		JLabel SearchBtn = new JLabel(searchIcon);
		SearchBtn.setBounds(402, 5, 50, 50);
		SearchBtn.setBackground(new Color(255, 255,255));
		SearchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new UserSearch();
				dispose();
			}
		});
	
		
		
		appbar.add(SearchBtn);
		
			
		
		//-------------
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(464, 695));
		contentPane.add(layeredPane);
		
		JPanel posts = new JPanel();
		posts.setLayout(new BoxLayout(posts, BoxLayout.Y_AXIS));
		//posts.setPreferredSize(new Dimension(464, 695));
		
		
		//수정필요(로그인 후 팔로우 부분 활성화 되면 팔로우한 유저의 게시글 보여주기)
		List<String> list = SQLMethods.Followings(SQLMethods.GetCon(), ClientInformation.Logined_id);
		List<Post> postList = null;
		
		postList = SQLMethods.GetPosts(list);
		
		if(postList != null) {
			for(int i =0;i<postList.size();i++) {
				Post post = postList.get(i);
				PostPanel p1 = null;
				if(post.images == null)
					p1 = new PostPanel(post);
				else
					p1 = new PostPanel(post,post.images);
				p1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						new ViewPost(post.post_id);
					}
				});
				
				posts.add(p1);
			}
			
		}
		
		
		
		
		JScrollPane scrollPane = new JScrollPane(posts);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setSize(464, 695);
		layeredPane.add(scrollPane);
		
		
	
		JPanel btnPanel = new JPanel();
		layeredPane.setLayer(btnPanel, 1);
		btnPanel.setLocation(395, 635);
		btnPanel.setBackground(new Color(255, 0,0,0));
		btnPanel.setSize(50, 50);
		btnPanel.setLayout(null);
		
		//글쓰기 버튼
		ImageIcon plusImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/plus.png", 50, 50);
		JLabel writeBtn = new JLabel(plusImage);
		writeBtn.setBounds(0, 0, 50, 50);
		writeBtn.setBackground(new Color(255, 255,255));

		writeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new posting(ClientInformation.Logined_id);
				dispose();
			}
		});
		
		
		btnPanel.add(writeBtn);
		//btnPanel.add(WriteBtn);
		layeredPane.add(btnPanel);	
		
		
	
		setVisible(true);
	}
}
