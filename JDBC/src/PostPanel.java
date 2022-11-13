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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.FlowLayout;
import java.io.Serializable;

@SuppressWarnings("serial")
public class PostPanel extends JPanel{
	User user;
	JPanel panel;
	
	public PostPanel(Post post) {
		setPreferredSize(new Dimension(464,200));
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setBackground(new Color(255, 255, 255));		
		setLayout(null);
		
		user = new User(post.user_id);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 464, 200);
		panel.setLayout(null);
		add(panel);
		
		ImageIcon userImage = ImageManager.GetImageUsingURL(user.profile_Image_Dir, 50, 50);
		JLabel UserIcon = new JLabel(userImage);
		UserIcon.setBackground(new Color(255, 255, 255));
		UserIcon.setBounds(12, 5, 50, 50);
		panel.add(UserIcon);
		
		JLabel Name = new JLabel(user.username);
		Name.setBackground(new Color(192, 192, 192));
		Name.setBounds(74, 10, 88, 15);
		panel.add(Name);
		
		JLabel ID = new JLabel(user.user_id);
		ID.setBounds(154, 10, 74, 15);
		panel.add(ID);
		Border border = BorderFactory.createLineBorder(Color.black, 2);
		
		JLabel date = new JLabel(post.date.toString());
		date.setBounds(240, 10, 104, 15);
		panel.add(date);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(12, 65, 428, 100);
		panel.add(scroll);
		
		JTextPane text = new JTextPane();
		text.setText(post.content);
		scroll.setViewportView(text);
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
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 464, 300);
		panel.setLayout(null);
		add(panel);
		
		ImageIcon userImage = ImageManager.GetImageUsingURL(user.profile_Image_Dir, 50, 50);
		JLabel UserIcon = new JLabel(userImage);
		UserIcon.setBackground(new Color(255, 255, 255));
		UserIcon.setBounds(12, 5, 50, 50);
		panel.add(UserIcon);
		
		JLabel Name = new JLabel(user.username);
		Name.setBackground(new Color(192, 192, 192));
		Name.setBounds(74, 10, 88, 15);
		panel.add(Name);
		
		JLabel ID = new JLabel(user.user_id);
		ID.setBounds(154, 10, 74, 15);
		panel.add(ID);
		Border border = BorderFactory.createLineBorder(Color.black, 2);
		
		JLabel date = new JLabel(post.date.toString());
		date.setBounds(240, 10, 104, 15);
		panel.add(date);
		
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
		images.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		imageScroll.add(images);
		imageScroll.setViewportView(images);
		
		for(int i =0;i<imgs.size(); i++) {
			ImageIcon img = ImageManager.GetImageUsingURL(imgs.get(i), 100,100);
			JLabel imgLabel = new JLabel(img);
			imgLabel.setSize(100,100);
			images.add(imgLabel);
			
		}	
	}
}