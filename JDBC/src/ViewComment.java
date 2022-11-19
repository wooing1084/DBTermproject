import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ViewComment extends JFrame {

	private JPanel contentPane;
	public JTextField commentText;
	public String comment_id;

	public ViewComment(String c_id) {
		comment_id = c_id;
		
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
		
		/*
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
		*/
		
		
		ImageIcon userImage = ImageManager.GetUserProfile(ClientInformation.Logined_id, 50, 50);
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
		
		commentText = new JTextField();
		commentText.setBounds(12, 10, 367, 48);
		commentText.setColumns(10);
		
		
		Comment p = new Comment(c_id);
		CommentPanel comment = new CommentPanel(p,commentText);
		center.add(comment);
		

		
		List<ChildComment> list = SQLMethods.ChildComments(SQLMethods.GetCon(), c_id);

		//comment ��ü ���� �� �߰�(�����ǵ�� �����)
		JPanel comments = new JPanel();
		comments.setBackground(new Color(255, 255, 255));
		comments.setLayout(new BoxLayout(comments, BoxLayout.Y_AXIS));
		
		for(int i =0;i<list.size();i++) {
			ChildCommentPanel c = new ChildCommentPanel(list.get(i));
			comments.add(c);
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
		
		
		panel.add(commentText);
		
		
		ImageIcon enter = ImageManager.GetImageUsingFileSystem("src/assets/UI/enter button.png",82,32);
		JButton enterBtn = new JButton(enter);
		enterBtn.setBounds(391, 10, 61, 48);
		enterBtn.setBorderPainted(false);
		enterBtn.setFocusPainted(false);
		enterBtn.setOpaque(false);
		enterBtn.setContentAreaFilled(false);
		enterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = commentText.getText();
				SQLMethods.WriteChildComment(SQLMethods.GetCon(), ClientInformation.Logined_id, c_id,text);
				
				comments.invalidate();
				
				String q2 = "select max(comment_id) from childcomment;";
				ResultSet rs2 = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q2);
				String c_id= "";
				try {
					if(rs2.next())
					{
						c_id = rs2.getString(1);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				ChildComment c1 = new ChildComment(c_id);
				ChildCommentPanel cP1 = new ChildCommentPanel(c1);
				comments.add(cP1);
				
				comments.validate();
			}
		});
		panel.add(enterBtn);
		

		int h = 800 - 20 - top.getPreferredSize().height - bottom.getPreferredSize().height - comment.getPreferredSize().height;
		scrollPane.setPreferredSize(new Dimension(464,h));
		
		setVisible(true);
	}

}
