import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class UserPanel extends JPanel {

	JPanel panel;
	private ImageAvatar imageAvatar;
	
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
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
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
		
		ImageIcon uIcon = ImageManager.GetUserProfile(user.user_id, 50, 50);
		
		imageAvatar = initComponents(uIcon);
		imageAvatar.setBounds(0, 5, 60, 60);
        imageAvatar.setBorderColor(new Color(255,255,255));
        panel.add(imageAvatar);
        /*
		JLabel icon = new JLabel(uIcon);
		
		icon.setBounds(5, 10, 50, 50);
		panel.add(icon);
		*/
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(80, 15, 158, 40);
		infoPanel.setBackground(new Color(255, 255, 255));
		panel.add(infoPanel);
		FlowLayout fl_infoPanel = new FlowLayout(FlowLayout.LEFT, 5, 5);
		infoPanel.setLayout(fl_infoPanel);
		
		JLabel userName = new JLabel(user.username);
		userName.setFont(new Font("Thoma", Font.PLAIN, 20));
		userName.setForeground(Color.BLACK);
		infoPanel.add(userName);
		
		String gId = "@" + user.user_id;
		JLabel ID = new JLabel(gId);
		ID.setVerticalAlignment(SwingConstants.BOTTOM);
		ID.setFont(new Font("Thoma", Font.PLAIN, 15));
		ID.setForeground(Color.GRAY);
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
		if(user.user_id.equals(ClientInformation.Logined_id)) {
			follow.setVisible(false);
		}
		else
			follow.setVisible((true));
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
        setBounds(0, 0, 464, 75);
        //setLocationRelativeTo(null);
        return imageAvatar1;
    }
}
