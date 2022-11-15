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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class NamePanel extends JPanel{
	User user;
	JPanel panel;
	
	public NamePanel(Post post) {
		setPreferredSize(new Dimension(480,100));
		//setBackground(new Color(255, 255, 255));		
		setLayout(null);
		
		user = new User(post.user_id);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 480, 80);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		//panel.setBorder(BorderFactory.createEmptyBorder(30 , 30, 30 , 30));
		panel.setLayout(null);
		add(panel);
		
		ImageIcon userImage = ImageManager.GetImageUsingURL(user.profile_Image_Dir, 50, 50);
		JLabel UserIcon = new JLabel(userImage);
		//UserIcon.setBackground(new Color(255, 255, 255));
		UserIcon.setBounds(0, 0, 75, 75);
		panel.add(UserIcon);
		
		JPanel postInfo = new JPanel();
		postInfo.setBounds(60, 20, 282, 20);
		postInfo.setBackground(new Color(255, 255, 255));
		//postInfo.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.add(postInfo);
		postInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel Name = new JLabel(user.username);
		//Name.setBackground(new Color(0, 192, 192));
		Name.setBounds(75, 10, 88, 15);
		//Name.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		postInfo.add(Name);
		
		JPanel followPanel = new JPanel();
		followPanel.setBackground(new Color(255, 255, 255));
		followPanel.setBounds(355,22,90, 20);
		FlowLayout flowLayout = (FlowLayout) followPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.CENTER);
		panel.add(followPanel);
		
		//
		
		JButton f_button = new JButton("following");
		f_button.setPreferredSize(new Dimension(90,20));
		followPanel.add(f_button);
	}	
}
