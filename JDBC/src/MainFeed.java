import java.awt.EventQueue;
import java.sql.Connection;

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
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JLayeredPane;
import java.awt.Component;

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
		//Logo.setPreferredSize(new Dimension(50,50));
		Logo.setBounds(200, 5, 50, 50);
		appbar.add(Logo);
		
		ImageIcon userImage = ImageManager.GetImageUsingURL("https://pbs.twimg.com/profile_images/1374979417915547648/vKspl9Et_400x400.jpg", 100, 100);
		JLabel UserBtn = new JLabel(userImage);
		UserBtn.setBounds(12, 5, 50, 50);
		appbar.add(UserBtn);
		
		ImageIcon searchIcon = ImageManager.GetImageUsingFileSystem("src/assets/UI/search.png",30,30);
		JLabel SearchBtn = new JLabel(searchIcon);
		SearchBtn.setBounds(402, 5, 50, 50);
		appbar.add(SearchBtn);
		
			
		
		//-------------
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(464, 695));
		contentPane.add(layeredPane);
		
		JPanel posts = new JPanel();
		posts.setLayout(new BoxLayout(posts, BoxLayout.Y_AXIS));
		//posts.setPreferredSize(new Dimension(464, 695));
		
		Post p = new Post("abcd1");
		PostPanel p1 = new PostPanel(p,p.images);
		posts.add(p1);
		
		PostPanel p4 = new PostPanel(p);
		posts.add(p4);
		
		PostPanel p2 = new PostPanel(p,p.images);
		p2.setPreferredSize(new Dimension(464,300));
		posts.add(p2);
		
		PostPanel p3 = new PostPanel(p,p.images);
		p3.setPreferredSize(new Dimension(464,300));
		posts.add(p3);
		
		
		
		JScrollPane scrollPane = new JScrollPane(posts);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setSize(464, 695);
		layeredPane.add(scrollPane);
		
		ImageIcon plusImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/plus.png", 50, 50);
		JLabel WriteBtn = new JLabel(plusImage);
		WriteBtn.setBounds(0, 0, 50, 50);
	
		JPanel btnPanel = new JPanel();
		layeredPane.setLayer(btnPanel, 1);
		btnPanel.setLocation(395, 635);
		btnPanel.setBackground(new Color(255, 0,0,0));
		btnPanel.setSize(50, 50);
		btnPanel.setLayout(null);
		btnPanel.add(WriteBtn);
		layeredPane.add(btnPanel);	
	
		setVisible(true);
	}
}
