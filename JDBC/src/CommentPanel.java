import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextPane;

public class CommentPanel extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public CommentPanel(String c_id) {
		setLayout(null);
		setPreferredSize(new Dimension(450,100));
		
		JLabel profileIcon = new JLabel("");
		profileIcon.setBounds(5, 5, 50, 50);
		profileIcon.setBackground(new Color(128, 255, 128));
		profileIcon.setPreferredSize(new Dimension(50,50));
		add(profileIcon);
		
		JPanel commentInfo = new JPanel();
		commentInfo.setBounds(60, 5, 300, 23);
		add(commentInfo);
		commentInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel userID = new JLabel("");
		commentInfo.add(userID);
		
		JLabel date = new JLabel("");
		commentInfo.add(date);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 26, 371, 64);
		add(scrollPane);
		
		JTextPane commentContent = new JTextPane();
		commentContent.setEditable(false);
		scrollPane.setViewportView(commentContent);
		
		JLabel reply = new JLabel("Reply");
		reply.setBounds(5, 75, 57, 15);
		add(reply);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setBounds(360, 5, 71, 23);
		add(panel);
		
		
		String q1 = "select count(user_id) from comment_like where comment_id = \"" + c_id + "\";";
		Connection con = SQLMethods.GetCon();
		ResultSet rs = SQLMethods.ExecuteQuery(con, q1);
		int cnt = 0;
		try {
			if(rs.next()) {
				cnt = rs.getInt(1);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		q1 = "select like_id from comment_like where comment_id = \"" + c_id + "\" and  user_id = \"" + User.user_id + "\";";
		rs = SQLMethods.ExecuteQuery(con, q1);
		
		
		String imgURL ="";
		try {
			if(rs.next()) {
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
		
		JLabel likes = new JLabel(""+cnt);
		panel.add(likes);
		
		JLabel heart = new JLabel(likeImage);
		heart.setPreferredSize(new Dimension(20,20));
		panel.add(heart);

	}
}
