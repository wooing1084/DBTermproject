import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

@SuppressWarnings("serial")
public class PostPanel extends JPanel{
	User user;
	JPanel panel;
	private ImageAvatar imageAvatar;
	public Dimension getMaximumSize() {
		Dimension d = getPreferredSize();
		d.width = Integer.MAX_VALUE;
		
		return d;
	}
	
	public PostPanel(Post post) {
		setPreferredSize(new Dimension(464,200));
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setBackground(new Color(255, 255, 255));		
		setLayout(null);
		
		user = new User(post.user_id);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 464, 200);
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);
		add(panel);
		
		String q1 = "select user_id from posts where post_id = \"" + post.post_id + "\";";
		ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
		String user_id = "";
		try {
			if(rs.next()) {
				user_id = rs.getString(1);
			}
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		ImageIcon userImage = ImageManager.GetUserProfile(user.user_id, 50, 50);
		imageAvatar = initComponents(userImage);
		imageAvatar.setBounds(5, 0, 60, 60);
		imageAvatar.setBorderColor(new Color(255,255,255));
		imageAvatar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Profile(post.user_id);
			}
		});
		panel.add(imageAvatar);
		/*
		JLabel UserIcon = new JLabel(userImage);
		UserIcon.setBackground(new Color(255, 255, 255));
		UserIcon.setBounds(12, 5, 50, 50);
		
		UserIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Profile(post.user_id);
			}
		});
		
		panel.add(UserIcon);
		*/
		JPanel postInfo = new JPanel();
		postInfo.setBounds(61, 5, 282, 20);
		postInfo.setBackground(new Color(255, 255, 255));
		panel.add(postInfo);
		postInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel Name = new JLabel(user.username);
		Name.setBackground(new Color(192, 192, 192));
		Name.setBounds(74, 10, 88, 15);
		postInfo.add(Name);
		
		JLabel margin1 = new JLabel("•");
		margin1.setFont(new Font("굴림", Font.PLAIN, 8));
		margin1.setForeground(new Color(175, 175, 175));
		postInfo.add(margin1);
		
		JLabel ID = new JLabel(user.user_id);
		ID.setForeground(new Color(175, 175, 175));
		ID.setBounds(154, 10, 74, 15);
		postInfo.add(ID);
		Border border = BorderFactory.createLineBorder(Color.black, 2);
		
		JLabel maring2 = new JLabel("•");
		maring2.setFont(new Font("굴림", Font.PLAIN, 8));
		maring2.setForeground(new Color(175, 175, 175));
		postInfo.add(maring2);
		
		JLabel date = new JLabel(post.date.toString());
		date.setBounds(240, 10, 104, 15);
		postInfo.add(date);
		
		JPanel liekPanel = new JPanel();
		liekPanel.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout = (FlowLayout) liekPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		liekPanel.setBounds(355, 22, 84, 20);
		panel.add(liekPanel);
		
		//-----
		
		q1 = "select count(liker_id) from post_like where post_id = \"" + post.post_id + "\";";
		Connection con = SQLMethods.GetCon();
		rs = SQLMethods.ExecuteQuery(con, q1);
		int cnt = 0;
		try {
			if(rs.next()) {
				cnt = rs.getInt(1);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel likeCnt = new JLabel("" + cnt);
		liekPanel.add(likeCnt);
		
		q1 = "select post_id from post_like where post_id = \"" + post.post_id + "\" and  liker_id = \"" + ClientInformation.Logined_id + "\";";
		rs = SQLMethods.ExecuteQuery(con, q1);
		
		
		String imgURL ="";
		try {
			if(rs.next()) {
				if(rs.getString(1).compareTo("") == 0)
					imgURL = "src/assets/UI/emptyHeart.png";
				else
					imgURL = "src/assets/UI/fullHeart.png";
				
			}
			else {
				imgURL = "src/assets/UI/emptyHeart.png";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ImageIcon likeImage = ImageManager.GetImageUsingFileSystem(imgURL, 20, 20);
		
		JLabel likeIcon = new JLabel(likeImage);
		likeIcon.setPreferredSize(new Dimension(20,20));
		liekPanel.add(likeIcon);
		
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(12, 65, 428, 100);
		panel.add(scroll);
		
		JTextPane text = new JTextPane();
		text.setText(post.content);
		scroll.setViewportView(text);
		
		liekPanel.addMouseListener(new MouseAdapter() {
			
			ImageIcon likeImage_1=new ImageIcon();
			@Override
			public void mouseClicked(MouseEvent e) {
				String q1 = "select count(liker_id) from post_like where post_id = \"" + post.post_id + "\";";
				Connection con = SQLMethods.GetCon();
				ResultSet rs = SQLMethods.ExecuteQuery(con, q1);
				
				int cnt = SQLMethods.PostLikers(SQLMethods.GetCon(), post.post_id).size();
				try {
					if(rs.next()) {
						cnt = rs.getInt(1);				
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String heartURL = "";
				
				System.out.println(post.post_id + "'s liekPanel Clicked");
				int like=SQLMethods.PostLike(SQLMethods.GetCon(), ClientInformation.Logined_id, post.post_id);
				System.out.println(like);
				if(like==1) {
					heartURL = "src/assets/UI/fullHeart.png";
					likeImage_1 = ImageManager.GetImageUsingFileSystem(heartURL, 20, 20);
					cnt=cnt+1;
				}
				else if(like==0) {
					heartURL = "src/assets/UI/emptyHeart.png";
					likeImage_1 = ImageManager.GetImageUsingFileSystem(heartURL, 20, 20);
					cnt=cnt-1;
				}
				likeIcon.setIcon(likeImage_1);
				likeCnt.setText("" + cnt);
				
			}
		});
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public PostPanel(Post post, List<String> imgs) {
		setBounds(0, 0, 464, 300);
		setPreferredSize(new Dimension(464,300));
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setBackground(new Color(255, 255, 255));		
		setLayout(null);
		
		user = new User(post.user_id);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 0), ""));
		panel.setBounds(0, 0, 464, 300);
		panel.setLayout(null);
		add(panel);
		
		ImageIcon userImage = ImageManager.GetUserProfile(user.user_id, 50, 50);
		imageAvatar = initComponents(userImage);
		imageAvatar.setBounds(5, 0, 60, 60);
		imageAvatar.setBorderColor(new Color(255,255,255));
		imageAvatar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Profile(post.user_id);
			}
		});
		panel.add(imageAvatar);
		/*
		JLabel UserIcon = new JLabel(userImage);
		UserIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Profile(post.user_id);
			}
		});
		UserIcon.setBackground(new Color(255, 255, 255));
		UserIcon.setBounds(12, 5, 50, 50);
		panel.add(UserIcon);
		*/
		JPanel postInfo = new JPanel();
		postInfo.setBounds(61, 5, 282, 20);
		postInfo.setBackground(new Color(255, 255, 255));
		panel.add(postInfo);
		postInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel Name = new JLabel(user.username);
		Name.setBackground(new Color(192, 192, 192));
		Name.setBounds(74, 10, 88, 15);
		postInfo.add(Name);
		
		JLabel margin1 = new JLabel("•");
		margin1.setFont(new Font("굴림", Font.PLAIN, 8));
		margin1.setForeground(new Color(175, 175, 175));
		postInfo.add(margin1);
		
		JLabel ID = new JLabel(user.user_id);
		ID.setForeground(new Color(175, 175, 175));
		ID.setBounds(154, 10, 74, 15);
		postInfo.add(ID);
		Border border = BorderFactory.createLineBorder(Color.black, 2);
		
		JLabel maring2 = new JLabel("•");
		maring2.setFont(new Font("굴림", Font.PLAIN, 8));
		maring2.setForeground(new Color(175, 175, 175));
		postInfo.add(maring2);
		
		JLabel date = new JLabel(post.date.toString());
		date.setBounds(240, 10, 104, 15);
		postInfo.add(date);
		
		JPanel liekPanel = new JPanel();
		liekPanel.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout = (FlowLayout) liekPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		liekPanel.setBounds(355, 22, 84, 20);
		panel.add(liekPanel);
		
		//-----
		
		String q1 = "select count(liker_id) from post_like where post_id = \"" + post.post_id + "\";";
		Connection con = SQLMethods.GetCon();
		ResultSet rs = SQLMethods.ExecuteQuery(con, q1);
		int cnt = SQLMethods.PostLikers(SQLMethods.GetCon(), post.post_id).size();
		try {
			if(rs.next()) {
				cnt = rs.getInt(1);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel likeCnt = new JLabel("" + cnt);
		liekPanel.add(likeCnt);
		
		q1 = "select post_id from post_like where post_id = \"" + post.post_id + "\" and  liker_id = \"" + ClientInformation.Logined_id + "\";";
		rs = SQLMethods.ExecuteQuery(con, q1);
		
		
		String imgURL ="";
		try {
			if(rs.next()) {
				if(rs.getString(1).compareTo("") == 0)
					imgURL = "src/assets/UI/emptyHeart.png";
				else
					imgURL = "src/assets/UI/fullHeart.png";
				
			}
			else {
				imgURL = "src/assets/UI/emptyHeart.png";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ImageIcon likeImage = ImageManager.GetImageUsingFileSystem(imgURL, 20, 20);
		
		JLabel likeIcon = new JLabel(likeImage);
		likeIcon.setPreferredSize(new Dimension(20,20));
		liekPanel.add(likeIcon);
		
		//----
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(12, 65, 428, 100);
		panel.add(scroll);
		
		JTextPane text = new JTextPane();
		text.setText(post.content);
		scroll.setViewportView(text);
		
		JScrollPane imageScroll = new JScrollPane();
		imageScroll.setBounds(12, 175, 428, 115);
		panel.add(imageScroll);

		JPanel images = new JPanel();
		images.setBackground(new Color(255, 255, 255));
		images.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		imageScroll.add(images);
		imageScroll.setViewportView(images);

		
		for(int i =0;i<imgs.size(); i++) {
			ImageIcon img = ImageManager.GetImageUsingURL(imgs.get(i), 100,100);
			JLabel imgLabel = new JLabel(img);
			imgLabel.setSize(100,100);
			images.add(imgLabel);
			
		}	
		
		liekPanel.addMouseListener(new MouseAdapter() {
			
			ImageIcon likeImage_1=new ImageIcon();
			@Override
			public void mouseClicked(MouseEvent e) {
				String q1 = "select count(liker_id) from post_like where post_id = \"" + post.post_id + "\";";
				Connection con = SQLMethods.GetCon();
				ResultSet rs = SQLMethods.ExecuteQuery(con, q1);
				
				int cnt = 0;
				try {
					if(rs.next()) {
						cnt = rs.getInt(1);				
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String heartURL = "";
				System.out.println(post.post_id + "'s liekPanel Clicked");
				int like=SQLMethods.PostLike(SQLMethods.GetCon(), ClientInformation.Logined_id, post.post_id);
				System.out.println(like);
				if(like==1) {
					heartURL = "src/assets/UI/fullHeart.png";
					likeImage_1 = ImageManager.GetImageUsingFileSystem(heartURL, 20, 20);
					cnt=cnt+1;
				}
				else if(like==0) {
					heartURL = "src/assets/UI/emptyHeart.png";
					likeImage_1 = ImageManager.GetImageUsingFileSystem(heartURL, 20, 20);
					cnt=cnt-1;
				}
				likeIcon.setIcon(likeImage_1);
				likeCnt.setText("" + cnt);
				
			}
		});
	}
private ImageAvatar initComponents(ImageIcon icon) {
		
		ImageAvatar imageAvatar1 = new ImageAvatar();
       

        //
        //ImageIcon profileIcon = ImageManager.GetImageUsingFileSystem("src/assets/profile_image.png",50,50);
		
        Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		
		
        imageAvatar1.setImage(updateIcon); // NOI18N
        GroupLayout layout = new javax.swing.GroupLayout(panel);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addComponent(imageAvatar1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(812, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(142)
        			.addComponent(imageAvatar1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(514, Short.MAX_VALUE))
        );
        //panel.setLayout(layout);

        //pack();
        setBounds(0, 0, 478, 763);
        //setLocationRelativeTo(null);
        return imageAvatar1;
    }
}