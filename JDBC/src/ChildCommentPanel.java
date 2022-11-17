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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	public ChildCommentPanel(ChildComment comment) {
		setLayout(null);
		setPreferredSize(new Dimension(450,60));
		setBounds(0,0,450,60);
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 60);
		add(panel);
		panel.setLayout(null);
		
		
		ImageIcon pImage = ImageManager.GetUserProfile(comment.user_id, 41, 40);
		JLabel profileIcon = new JLabel(pImage);
		profileIcon.setBounds(45, 10, 41, 40);
		panel.add(profileIcon);
		
		textField = new JTextField(comment.content);
		textField.setBounds(89, 20, 290, 35);
		textField.setEditable(false);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel LikePanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) LikePanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		LikePanel.setBounds(397, 20, 41, 20);
		panel.add(LikePanel);
		
		
		Connection con = SQLMethods.GetCon();
		int cnt = SQLMethods.ChildCommentLikers(con, comment.comment_id).size();

		
		String q1 = "select * from comment_like where comment_id = \"" + comment.comment_id + "\" and  user_id = \"" + ClientInformation.Logined_id + "\";";
		ResultSet rs = SQLMethods.ExecuteQuery(con, q1);
		
		
		String imgURL ="";
		try {
			if(rs.next()) {
				if(rs.getString(1).compareTo("") == 0)
				{
					imgURL = "src/assets/UI/emptyHeart.png";
				}
				else {
					imgURL = "src/assets/UI/fullHeart.png";
					
				}
				
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
		heart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("like click");
				
				
				String heartURL = "";
				
				ImageIcon likeImage_1 = null;
				int cnt1 = SQLMethods.ChildCommentLikers(SQLMethods.GetCon(), comment.comment_id).size();
				
				System.out.println(comment.comment_id + "'s liekPanel Clicked");
				int like=SQLMethods.ChildCommentLike(SQLMethods.GetCon(), ClientInformation.Logined_id , comment.comment_id);
				System.out.println(like);
				if(like==1) {
					heartURL = "src/assets/UI/fullHeart.png";
					likeImage_1 = ImageManager.GetImageUsingFileSystem(heartURL, 20, 20);
					cnt1=cnt1+1;
				}
				else if(like==0) {
					heartURL = "src/assets/UI/emptyHeart.png";
					likeImage_1 = ImageManager.GetImageUsingFileSystem(heartURL, 20, 20);
					cnt1=cnt1-1;
				}
				heart.setIcon(likeImage_1);
				likes.setText("" + cnt1);
				
			}
		});
		heart.setPreferredSize(new Dimension(20,20));
		LikePanel.add(heart);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(89, 0, 290, 20);
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		
		JLabel idLabel = new JLabel(comment.user_id);
		panel_1.add(idLabel);
		
		JLabel dateLabel = new JLabel(comment.date.toString());
		panel_1.add(dateLabel);

		

	}
}
