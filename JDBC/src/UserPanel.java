import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserPanel extends JPanel {

	JPanel panel;
	
	public Dimension getMaximumSize() {
		Dimension d = getPreferredSize();
		d.width = Integer.MAX_VALUE;
		
		return d;
	}
	
	public UserPanel(User user) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(464, 75));
		setBounds(0,0,464,75);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0,0,464,75);
		panel.setPreferredSize(new Dimension(464,75));
		panel.setLayout(null);
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Profile(user.user_id);
				
				
			}
		});
		add(panel);
		
		ImageIcon uIcon = ImageManager.GetUserProfile(user.profile_Image_Dir, 50, 50);
		
		JLabel icon = new JLabel(uIcon);
		
		icon.setBounds(5, 10, 50, 50);
		panel.add(icon);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(80, 15, 158, 40);
		panel.add(infoPanel);
		FlowLayout fl_infoPanel = new FlowLayout(FlowLayout.LEFT, 5, 5);
		infoPanel.setLayout(fl_infoPanel);
		
		JLabel userName = new JLabel(user.username);
		infoPanel.add(userName);
		
		JLabel ID = new JLabel(user.user_id);
		infoPanel.add(ID);
		
		String q1 = "select user_id from follow where follower_id = \"" + ClientInformation.Logined_id + "\" and user_id = \"" + user.user_id + "\";";
		ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
		
		String followUrl = "src/assets/UI/follow_en.png";
		try {
			if(rs.next()) {
				if(rs.getString(1).compareTo("") == 0) {
					followUrl = "src/assets/UI/follow_en.png";
				}
				else {
					followUrl = "src/assets/UI/following.png";
				}
							
			}
			else
				followUrl = "src/assets/UI/follow_en.png";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ImageIcon followIcon = ImageManager.GetImageUsingFileSystem(followUrl,116,32);
		JLabel follow = new JLabel(followIcon);
		/*팔로우에 추가하기 (프로필)*/
		follow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Follow Click");
				SQLMethods.Follow(SQLMethods.GetCon(), ClientInformation.Logined_id, user.user_id);
				
				String q1 = "select user_id from follow where follower_id = \"" + ClientInformation.Logined_id + "\" and user_id = \"" + user.user_id + "\";";
				ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
				
				String imgUrl = "src/assets/UI/followIcon.png";
				try {
					if(rs.next()) {
						if(rs.getString(1).compareTo("") == 0) {
							imgUrl = "src/assets/UI/follow_en.png";
						}
						else {
							imgUrl = "src/assets/UI/following.png";
						}
									
					}
					else
						imgUrl = "src/assets/UI/follow_en.png";
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				ImageIcon fIcon = ImageManager.GetImageUsingFileSystem(imgUrl, 116, 32);
				
				follow.setIcon(fIcon);				
			}
			
			
			
		});
		follow.setBounds(336, 15, 116, 32);
		panel.add(follow);
	}
}
