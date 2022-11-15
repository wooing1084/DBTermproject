import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class UserPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public UserPanel(User user) {
		setSize(300,75);
		
		ImageIcon uIcon = ImageManager.GetImageUsingURL(user.profile_Image_Dir, 50, 50);
		setLayout(null);
		
		JLabel icon = new JLabel(uIcon);
		icon.setBounds(5, 10, 50, 50);
		add(icon);
		
		JPanel panel = new JPanel();
		panel.setBounds(62, 15, 158, 40);
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel userName = new JLabel(user.username);
		panel.add(userName);
		
		JLabel ID = new JLabel(user.user_id);
		panel.add(ID);
		
		String q1 = "select user_id from follow where follower_id = \"" + ClientInformation.Logined_id + "\" and user_id = \"" + user.user_id + "\";";
		ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
		
		String followUrl = "";
		try {
			if(rs.next()) {
				if(rs.getString(1).compareTo("") == 0) {
					followUrl = "src/assets/UI/followIcon.png";
				}
				else
					followUrl = "src/assets/UI/followingIcon.png";
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ImageIcon followIcon = ImageManager.GetImageUsingFileSystem(followUrl,79,36);
		JLabel follow = new JLabel(followIcon);
		follow.setBounds(215, 15, 79, 36);
		add(follow);
		
		

	}
}
