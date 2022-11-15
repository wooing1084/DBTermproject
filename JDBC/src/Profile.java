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
public class Profile extends JFrame {

	private JPanel panel;
	private Image backImg;
	
	public Profile(String id, int pwd){
		getContentPane().setLayout(null);
		setBounds(100, 100, 480, 800);
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
		Image img = profileIcon.getImage();
		Image updateImg = img.getScaledInstance(330, 150, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(1, 267, 465, 496);
		//panel_2.setBackground(Color.WHITE);
		panel.add(panel_2);
		
		JLabel profileImg = new JLabel(updateIcon);
		profileImg.setBounds(1, 10, 100, 100);
		//profileImg.setIcon(profileIcon);
		profileImg.setHorizontalAlignment(JLabel.CENTER);
		
		RoundedButton profileI = new RoundedButton(updateIcon);
		profileI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("KKK");
			}
		});
		profileI.setBounds(1, 115, 149, 150);
		panel.add(profileI);
		//profileI.setIcon(updateIcon);
		profileI.setBorderPainted(false);
		profileI.setFocusPainted(false);
		profileI.setContentAreaFilled(false);
		profileI.setVisible(true);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(0, 0, 464, 183);
		panel.add(panel_1);
		
		JLabel backImg_1 = new JLabel(backIcon);
		backImg_1.setHorizontalAlignment(SwingConstants.CENTER);
		backImg_1.setBounds(1, 62, 464, 119);
		panel_1.add(backImg_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(154, 185, 311, 77);
		panel_3.setBackground(Color.WHITE);
		panel.add(panel_3);
		panel_3.setLayout(null);
		//profileI.
		
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(464, 695));
		panel_2.add(layeredPane);
		
		JPanel posts = new JPanel();
		posts.setLayout(new BoxLayout(posts, BoxLayout.Y_AXIS));
		
		Post p = new Post("p1");
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
		scrollPane.setSize(464, 695);
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
}
