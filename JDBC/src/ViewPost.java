import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class ViewPost extends JFrame {

	private JPanel contentPane;
	public JTextField commentText;
	public String post_id;

	public ViewPost(String p_id) {
		post_id = p_id;
		
		setBackground(new Color(255, 255, 255));
		setBounds(150, 150, 480, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel top = new JPanel();
		FlowLayout flowLayout = (FlowLayout) top.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		contentPane.add(top, BorderLayout.NORTH);
		
		JPanel center = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) center.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		contentPane.add(center, BorderLayout.CENTER);
		
		JPanel bottom = new JPanel();
		contentPane.add(bottom, BorderLayout.SOUTH);
				
		JPanel appbar = new JPanel();
		appbar.setBackground(new Color(255, 255, 255));
		appbar.setPreferredSize(new Dimension(464,65));
		appbar.setLayout(null);
		top.add(appbar);
	
		
		ImageIcon logo = ImageManager.GetImageUsingFileSystem("src/assets/logo.png", 50,50);
		
		JLabel Logo = new JLabel(logo);
		Logo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		Logo.setBounds(12, 5, 50, 50);
		Logo.setBackground(new Color(255, 255,255));
		Logo.setBounds(200, 5, 50, 50);

		
		appbar.add(Logo);
				
		String imgUrl = "";
		String q1 = "select profile_Image_dir from user where user_id = \"" + ClientInformation.Logined_id + "\";";
		ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
		
		try {
			if(rs.next()) {
				imgUrl = rs.getString(1);
			}
			if(imgUrl.compareTo("") == 0) 
				imgUrl = "https://play-lh.googleusercontent.com/38AGKCqmbjZ9OuWx4YjssAz3Y0DTWbiM5HB0ove1pNBq_o9mtWfGszjZNxZdwt_vgHo";
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		ImageIcon userImage = ImageManager.GetImageUsingURL(imgUrl, 50, 50);
		JLabel UserBtn = new JLabel(userImage);
		UserBtn.setBounds(12, 5, 50, 50);
		UserBtn.setBackground(new Color(255, 255,255));
		UserBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		
		appbar.add(UserBtn);
		
		ImageIcon searchIcon = ImageManager.GetImageUsingFileSystem("src/assets/UI/search.png",30,30);
		JLabel SearchBtn = new JLabel(searchIcon);
		SearchBtn.setBounds(402, 5, 50, 50);
		SearchBtn.setBackground(new Color(255, 255,255));
		SearchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		appbar.add(SearchBtn);
		
		Post p = new Post(p_id);
		PostPanel post = new PostPanel(p);
		center.add(post);

		
		JPanel commentStatus = new JPanel();
		center.add(commentStatus);
		commentStatus.setBorder(new LineBorder(new Color(0, 0, 0)));
		commentStatus.setBackground(new Color(255, 255, 255));
		commentStatus.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		commentStatus.setPreferredSize(new Dimension(464,35));
		
		
		q1 = "select count(liker_id) from post_like where post_id = \"" + p_id + "\";";
		rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
		
		int cnt = 0;
		
		try {
			if(rs.next())
			cnt = rs.getInt(1);
		} catch (SQLException e1) {
				// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel likeCnt = new JLabel(""+cnt);
		commentStatus.add(likeCnt);
		
		JButton likeBtn = new JButton("Likes");
		likeBtn.setBackground(new Color(255, 255, 255));
		likeBtn.setToolTipText("");
		likeBtn.setBorderPainted(false);
		likeBtn.setFocusPainted(false);
		
		commentStatus.add(likeBtn);
		
		List<Comment> list = SQLMethods.Comments(SQLMethods.GetCon(), p_id);
		
		cnt = list.size();

		JLabel commentCnt = new JLabel(""+cnt);
		commentStatus.add(commentCnt);
			
		JLabel lblNewLabel = new JLabel("Comments");
		commentStatus.add(lblNewLabel);
		
		//comment 객체 생성 후 추가(메인피드와 비슷함)
		JPanel comments = new JPanel();
		comments.setBackground(new Color(255, 255, 255));
		comments.setLayout(new BoxLayout(comments, BoxLayout.Y_AXIS));
		
		for(int i =0;i<list.size();i++) {
			CommentPanel c = new CommentPanel(list.get(i), this);
			comments.add(c);
			
			List<ChildComment> cList = SQLMethods.ChildComments(SQLMethods.GetCon(), list.get(i).comment_id);
			
			for(int j =0;j< (cList.size() >= 2 ? 2: cList.size()); j++) {
				ChildCommentPanel cC = new ChildCommentPanel(cList.get(j));
				comments.add(cC);				
			}
			
			
		}
		JScrollPane scrollPane = new JScrollPane(comments);
		scrollPane.setPreferredSize(new Dimension(464, 550));
		
		
		center.add(scrollPane);
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
	
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setPreferredSize(new Dimension(464,60));
		panel.setBackground(new Color(255, 255, 255));
		bottom.add(panel);
		panel.setLayout(null);
		
		commentText = new JTextField();
		commentText.setBounds(12, 10, 367, 48);
		panel.add(commentText);
		commentText.setColumns(10);
		
		JButton enterBtn = new JButton("Enter");
		enterBtn.setBounds(391, 10, 61, 48);
		enterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = commentText.getText();
				SQLMethods.WriteComment(SQLMethods.GetCon(), ClientInformation.Logined_id, p_id,text);
			}
		});
		panel.add(enterBtn);
		

		int h = 800 - 20 - top.getPreferredSize().height - bottom.getPreferredSize().height - post.getPreferredSize().height - commentStatus.getPreferredSize().height;
		scrollPane.setPreferredSize(new Dimension(464,h));
		
		setVisible(true);
	}
}
