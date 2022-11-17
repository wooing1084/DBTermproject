import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*
;
import javax.swing.GroupLayout.Alignment;
public class Profile extends JFrame {
	
	private JPanel panel;
	private Image backImg;
	private ImageAvatar imageAvatar;
	
	String nickname = null;
	public Profile(String id, int pwd){
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
			}
		});
		Logo.setBounds(12, 5, 50, 50);
		Logo.setBackground(new Color(255, 255,255));
		Logo.setBounds(200, 5, 50, 50);

		
		appbar.add(Logo);
		
		ImageIcon userImage = ImageManager.GetImageUsingURL("https://pbs.twimg.com/profile_images/1374979417915547648/vKspl9Et_400x400.jpg", 100, 100);
		
		//앱바에 있는 프로필 사진 원형으로 변경 
		/*
		imageAvatar = initComponents(userImage);
		imageAvatar.setBounds(12, 5, 50, 50);
        imageAvatar.setBorderColor(new Color(120, 186, 239));
        appbar.add(imageAvatar);
        */
		JLabel UserBtn = new JLabel(userImage);
		UserBtn.setBounds(12, 5, 50, 50);
		UserBtn.setBackground(new Color(255, 255,255));
		UserBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		
		appbar.add(UserBtn);
		
		ImageIcon searchIcon = ImageManager.GetImageUsingFileSystem("src/assets/UI/search.png",30,30);
		JLabel SearchBtn = new JLabel(searchIcon);
		SearchBtn.setBounds(402, 100, 50, 50);
		SearchBtn.setBackground(new Color(255, 255,255));
		SearchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	
		
		
		appbar.add(SearchBtn);
		
		ImageIcon backIcon = ImageManager.GetImageUsingFileSystem("src/assets/cloud.jpg",464,200);
		
		ImageIcon profileIcon = ImageManager.GetImageUsingFileSystem("src/assets/profile_image.png",50,50);
		
		
		
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
        imageAvatar.setBorderColor(new Color(120, 186, 239));
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
		lblNewLabel.setBounds(0, 10, 299, 29);
		panel_3.add(lblNewLabel);
		
		String fId = "@" + id;
		JLabel lblNewLabel_1 = new JLabel(fId);
		lblNewLabel_1.setBounds(0, 35, 52, 15);
		panel_3.add(lblNewLabel_1);
		
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

		panel_2.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(1, 5, 464, 695);
		layeredPane.setPreferredSize(new Dimension(464, 695));
		panel_2.add(layeredPane);
		
		JPanel posts = new JPanel();
		posts.setLayout(new BoxLayout(posts, BoxLayout.Y_AXIS));
		
		Post p = new Post("abcd1");
		PostPanel p1 = new PostPanel(p,p.images);
		p1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(p.post_id + " Clicked");
			}
		});
		posts.add(p1);
		
		PostPanel p4 = new PostPanel(p);
		posts.add(p4);
		
		
		PostPanel p2 = new PostPanel(p,p.images);
		posts.add(p2);
		
		PostPanel p3 = new PostPanel(p,p.images);
		posts.add(p3);
		
		JScrollPane scrollPane = new JScrollPane(posts);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setSize(464, 495);
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
		Image updateImg = img.getScaledInstance(297, 135, Image.SCALE_SMOOTH);
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
