import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class ChildCommentPanel extends JPanel {
	private JTextField textField;
	
	public Dimension getMaximumSize() {
		Dimension d = getPreferredSize();
		d.width = Integer.MAX_VALUE;
		
		return d;
	}

	/**
	 * Create the panel.
	 */
	public ChildCommentPanel(Comment comment) {
		setLayout(null);
		setPreferredSize(new Dimension(450,50));
		setBounds(0,0,450,50);
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 50);
		add(panel);
		panel.setLayout(null);
		
		JLabel profileIcon = new JLabel("");
		profileIcon.setBounds(40, 4, 41, 40);
		panel.add(profileIcon);
		
		textField = new JTextField();
		textField.setBounds(81, 5, 304, 35);
		textField.setEditable(false);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel LikePanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) LikePanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		LikePanel.setBounds(397, 10, 41, 20);
		panel.add(LikePanel);
		
		
		String q1 = "select count(user_id) from comment_like where comment_id = \"" + comment.comment_id + "\";";
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
		

		
		q1 = "select like_id from comment_like where comment_id = \"" + comment.comment_id + "\" and  user_id = \"" + ClientInformation.Logined_id + "\";";
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
		LikePanel.add(likes);
		
		JLabel heart = new JLabel(likeImage);
		heart.setPreferredSize(new Dimension(20,20));
		LikePanel.add(heart);
		
		JLabel cCommentIcon = new JLabel("L");
		cCommentIcon.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		cCommentIcon.setBounds(12, 4, 29, 40);
		panel.add(cCommentIcon);

		

	}
}
