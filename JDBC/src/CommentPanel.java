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
	
	public Dimension getMaximumSize() {
		Dimension d = getPreferredSize();
		d.width = Integer.MAX_VALUE;
		
		return d;
	}
	/**
	 * Create the panel.
	 */
	public CommentPanel(Comment comment) {
		setLayout(null);
		setPreferredSize(new Dimension(450,100));
		setBounds(0, 0, 450, 100);
		String q1 = "select profile_Image_dir from user where user_id = \"" + comment.user_id + "\";";
		ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
		
		String imgUrl = "";
		try {
			if(rs.next()) {
				imgUrl = rs.getString(1);
			}
			if(imgUrl.compareTo("") == 0)  
				imgUrl = "https://play-lh.googleusercontent.com/38AGKCqmbjZ9OuWx4YjssAz3Y0DTWbiM5HB0ove1pNBq_o9mtWfGszjZNxZdwt_vgHo";
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ImageIcon pImage = ImageManager.GetImageUsingURL(imgUrl, 50, 50);
		JLabel profileIcon = new JLabel(pImage);
		profileIcon.setBounds(5, 5, 50, 50);
		profileIcon.setBackground(new Color(128, 255, 128));
		profileIcon.setPreferredSize(new Dimension(50,50));
		add(profileIcon);
		
		JPanel commentInfo = new JPanel();
		commentInfo.setBounds(60, 5, 300, 20);
		add(commentInfo);
		commentInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel userID = new JLabel(comment.user_id);
		commentInfo.add(userID);
		
		JLabel date = new JLabel(comment.date.toString());
		commentInfo.add(date);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 26, 371, 64);
		add(scrollPane);
		
		JTextPane commentContent = new JTextPane();
		commentContent.setText(comment.content);
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
		
		
		q1 = "select count(user_id) from comment_like where comment_id = \"" + comment.comment_id + "\";";
		Connection con = SQLMethods.GetCon();
		rs = SQLMethods.ExecuteQuery(con, q1);
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
		panel.add(likes);
		
		JLabel heart = new JLabel(likeImage);
		heart.setPreferredSize(new Dimension(20,20));
		panel.add(heart);

	}
}
