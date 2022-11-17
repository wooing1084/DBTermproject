import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
public class Profile extends JFrame {
	
	private JPanel panel;
	private Image backImg;
	private ImageAvatar imageAvatar;
	
	String nickname = null;
	public Profile(String id){
		try {
			String q1 = "select username from user where user_id = \"" + id + "\";";
			ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
			if(rs.next()) {
				nickname = rs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		getContentPane().setLayout(null);
		getContentPane().setBounds(0,0,466,763);
		getContentPane().setVisible(true);
		setBounds(100, 100, 608, 630);
		setTitle("Profile");
		panel = new JPanel();
		panel.setBounds(0, 0, 466, 763);
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel appbar = new JPanel();
		appbar.setBounds(1, 5, 464, 65);
		appbar.setBackground(new Color(255, 255, 255));
		appbar.setPreferredSize(new Dimension(464,65));
		appbar.setLayout(null);
		panel.add(appbar);
		
		ImageIcon logo = ImageManager.GetImageUsingFileSystem("src/assets/logo.png", 50,50);
		
		JLabel Logo = new JLabel(logo);
		Logo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new MainFeed();
			}
		});
		Logo.setBounds(12, 5, 50, 50);
		Logo.setBackground(new Color(255, 255,255));
		Logo.setBounds(200, 5, 50, 50);

		
		appbar.add(Logo);
		
		String myProfileImage = null;
		
		//현재 로그인 되어있는 유저의 프로필 사진 스트링 얻어오기
		try {
			String q1 = "select profile_Image_dir from user where user_id = \"" + ClientInformation.Logined_id + "\";";
			ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
			if(rs.next()) {
				myProfileImage = rs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//현재 로그인 되어있는 유저의 프로필 사진
		ImageIcon myImage = ImageManager.GetImageUsingURL(myProfileImage, 50, 50);
		/*
		Image img = myImage.getImage();
		Image updateMyImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateMyImg);
		*/
		//앱바에 있는 프로필 사진 원형으로 변경 
		/*
		imageAvatar = initComponents(userImage);
		imageAvatar.setBounds(12, 5, 50, 50);
        imageAvatar.setBorderColor(new Color(120, 186, 239));
        appbar.add(imageAvatar);
        */
		JLabel UserBtn = new JLabel(myImage);
		UserBtn.setBounds(12, 5, 50, 50);
		UserBtn.setBackground(new Color(255, 255,255));
		UserBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Profile(ClientInformation.Logined_id);
			}
		});

		
		appbar.add(UserBtn);
		
		if(id.equals(ClientInformation.Logined_id)) {
			ImageIcon settingIcon = ImageManager.GetImageUsingFileSystem("src/assets/setting.png",30,30);
			JLabel settingBtn = new JLabel(settingIcon);
			settingBtn.setBounds(402, 5, 50, 50);
			//SearchBtn.setBackground(new Color(255, 255,255));
			settingBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					new Setting(ClientInformation.Logined_id, ClientInformation.Logined_pwd);
				}
			});
			appbar.add(settingBtn);
		}
		String userBackgroundImage = null;
		try {
			String q1 = "select background_Image_dir from user where user_id = \"" + id + "\";";
			ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
			if(rs.next()) {
				userBackgroundImage = rs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		ImageIcon backIcon = ImageManager.GetImageUsingURL(userBackgroundImage,464,200);
		
		String userProfileImage = null;
		try {
			String q1 = "select profile_Image_dir from user where user_id = \"" + id + "\";";
			ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
			if(rs.next()) {
				userProfileImage = rs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		ImageIcon profileIcon = ImageManager.GetImageUsingURL(userProfileImage,100,200);
		
		
		
		/*Image img = profileIcon.getImage();
		Image updateImg = img.getScaledInstance(330, 150, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		*/
		
		//포스트 레이어부분
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(1, 287, 465, 476);
		//panel_2.setBackground(Color.WHITE);
		panel.add(panel_2);
		
		//프로필사진
		
		imageAvatar = initComponents(profileIcon);
		imageAvatar.setBounds(10, 120, 100, 100);
        imageAvatar.setBorderColor(new Color(255,255,255));
        panel.add(imageAvatar);
		
		//배경 이미지
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(0, 0, 464, 183);
		panel.add(panel_1);
		
		JLabel backImg_1 = new JLabel(backIcon);
		backImg_1.setHorizontalAlignment(SwingConstants.CENTER);
		backImg_1.setBounds(1, 62, 464, 119);
		panel_1.add(backImg_1);
		
		//프로필 옆 배경
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 226, 311, 50);
		panel_3.setBackground(Color.WHITE);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(nickname);
		lblNewLabel.setFont(new Font("LG Smart UI Bold", Font.PLAIN, 23));
		lblNewLabel.setBounds(0, -10, 299, 29);
		panel_3.add(lblNewLabel);
		
		String fId = "@" + id;
		JLabel lblNewLabel_1 = new JLabel(fId);
		lblNewLabel_1.setBounds(0, 17, 52, 15);
		panel_3.add(lblNewLabel_1);
		
		String introduce = null;
		try {
			String q1 = "select introduce from user where user_id = \"" + id + "\";";
			ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
			if(rs.next()) {
				introduce = rs.getString(1);
			}
			else {
				introduce = " ";
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		JLabel lblNewLabel_2 = new JLabel(introduce);
		lblNewLabel_2.setBounds(0, 35, 52, 15);
		panel_3.add(lblNewLabel_2);

		java.util.List<String> follower = SQLMethods.Followers(SQLMethods.GetCon(), id);
		int num_of_follow = follower.size();
		java.util.List<String> followings = SQLMethods.Followings(SQLMethods.GetCon(), id);
		int num_of_following = followings.size();
		
		String numOfFollow = "Follower:"+Integer.toString(num_of_follow)
		+ "   Following: "+Integer.toString(num_of_following);
		JTextArea txtFollow = new JTextArea(numOfFollow);
		//txtFollow.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
		txtFollow.setEditable(false);
		txtFollow.setBounds(150, 35, 300, 100);
		panel_3.add(txtFollow);
		
		
		/*
		String numOfFollowing = "Following:"+Integer.toString(num_of_following);
		JTextArea txtFollowing = new JTextArea(numOfFollowing);
		//txtFollow.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
		txtFollowing.setEditable(false);
		txtFollowing.setBounds(310, 35, 60, 100);
		panel_3.add(txtFollowing);
		*/
		/*
		String numOfFollow = "Follower: "+num_of_follow;
		JLabel lblNewLabel_3 = new JLabel(numOfFollow);
		lblNewLabel_3.setBounds(100, 35, 52, 15);
		panel_3.add(lblNewLabel_3);
		*/
		/*
		RoundedButton fButton = new RoundedButton("팔로우");
		//fButton.setFont(new Font("LG Smart UI Bold", Font.PLAIN, 20));
		//fButton.setForeground(Color.WHITE);
		//fButton.setBackground(Color.BLACK);
		fButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("팔로우 기능!");
			}
		});
		fButton.setBounds(320, 193, 119, 32);
		panel.add(fButton);
		*/
		
		//팔로우 버튼
		ImageIcon followImg = ImageManager.GetImageUsingFileSystem("src/assets/UI/follow_en.png",116,32);
		ImageIcon followingImg = ImageManager.GetImageUsingFileSystem("src/assets/UI/following.png",116,32);
		
		JLabel followButton = new JLabel(followImg);
		JLabel followingButton = new JLabel(followingImg);
		
		followButton.setBounds(335, 193, 116, 32);
		//followButton.setBackground(new Color(255, 255,255));
		followingButton.setBounds(335, 193, 116, 32);
		//followingButton.setBackground(new Color(255, 255,255));
		
		panel.add(followButton);
		panel.add(followingButton);
		
		Statement stmt = null;
		ResultSet rs = null;
		
		String q1 = "select user_id from follow where user_id = \"" + id+ "\" and follower_id = \"" + ClientInformation.Logined_id + "\";";
		try {
			stmt = SQLMethods.GetCon().createStatement();
			rs = stmt.executeQuery(q1);
			
			if(rs.next()) {
				followingButton.setVisible(true);
				followButton.setVisible(false);
			}
			else {
				followingButton.setVisible(false);
				followButton.setVisible(true);
			}
		}catch (SQLException e){
            e.printStackTrace();
        }

		followButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("클릭됨!");
				SQLMethods.Follow(SQLMethods.GetCon(), ClientInformation.Logined_id, id);
				followingButton.setVisible(true);
				followButton.setVisible(false);
			}
		});
		followingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("언팔 클릭됨!");
				SQLMethods.Follow(SQLMethods.GetCon(), ClientInformation.Logined_id, id);
				followingButton.setVisible(false);
				followButton.setVisible(true);
			}
		});
		panel_2.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(1, 5, 464, 695);
		layeredPane.setPreferredSize(new Dimension(464, 695));
		panel_2.add(layeredPane);
		
		JPanel posts = new JPanel();
		posts.setLayout(new BoxLayout(posts, BoxLayout.Y_AXIS));
		
		//이미지 있는 경우
		Post p = new Post("p1");
		PostPanel p1 = new PostPanel(p,p.images);
		//게시글 누르면 포스트로 넘어가기
		//메인피드 135
		p1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(p.post_id + " Clicked");
			}
		});
		posts.add(p1);
		
		//이미지 없는 경우
		PostPanel p4 = new PostPanel(p);
		posts.add(p4);
		
		PostPanel p2 = new PostPanel(p,p.images);
		posts.add(p2);
		
		PostPanel p3 = new PostPanel(p,p.images);
		posts.add(p3);
		
		JScrollPane scrollPane = new JScrollPane(posts);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setSize(464, 450);
		layeredPane.add(scrollPane);
		
		JPanel btnPanel = new JPanel();
		layeredPane.setLayer(btnPanel, 1);
		btnPanel.setLocation(395, 635);
		btnPanel.setBackground(new Color(255, 0,0,0));
		btnPanel.setSize(50, 50);
		btnPanel.setLayout(null);
		
		ImageIcon plusImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/plus.png", 50, 50);
		JLabel writeBtn = new JLabel(plusImage);
		writeBtn.setBounds(0, 0, 50, 50);
		writeBtn.setBackground(new Color(255, 255,255));

		writeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		btnPanel.add(writeBtn);
		//btnPanel.add(WriteBtn);
		layeredPane.add(btnPanel);	
		
		panel_3.setVisible(true);
		panel.setVisible(true);
		setVisible(true);
		
	}
	
	//불러서 사용방법
	//imageAvatar1.setBounds(,,,,)
	//panel.add(imageAvatar1)
	//imageAvatar1.setBorderColor(new Color(,,)
	private ImageAvatar initComponents(ImageIcon icon) {
		
		ImageAvatar imageAvatar1 = new ImageAvatar();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        pack();
        setBounds(0, 0, 478, 763);
        setLocationRelativeTo(null);
        return imageAvatar1;
    }
}
