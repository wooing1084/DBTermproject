import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserSearch extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	public UserSearch() {
		setBounds(150, 150, 480, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 464, 80);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(65, 10, 315, 60);
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));
		
		
		JLabel icon = new JLabel(ImageManager.GetImageUsingFileSystem("src/assets/UI/search_2.png",10,10));
		icon.setPreferredSize(new Dimension(40, 40));
		panel_2.add(icon);
		
		textField = new JTextField();
		textField.setBackground(new Color(247, 247, 247));
		textField.setPreferredSize(new Dimension(274, 40));
		panel_2.add(textField);
		
		ImageIcon enterImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/enter button.png", 61, 24);
		JLabel enterBtn = new JLabel(enterImage);
		
		enterBtn.setHorizontalAlignment(SwingConstants.CENTER);
		enterBtn.setBackground(new Color(255, 255, 255));
		enterBtn.setBounds(392, 30, 61, 24);
		panel.add(enterBtn);
		
		ImageIcon backImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/back.png", 40, 40);
		JLabel backBtn = new JLabel(backImage);
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainFeed();
				dispose();
			}
		});
		backBtn.setBounds(12, 20, 41, 40);
		panel.add(backBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(panel_1);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 79, 464, 682);
		contentPane.add(scrollPane);
		
		User[] users = SQLMethods.GetUsers(SQLMethods.GetCon());
		
		for(int i =0;i < users.length; i++) {
			if(users[i].user_id.compareTo(ClientInformation.Logined_id) == 0)
				continue;
			
			UserPanel up = new UserPanel(users[i]);
			panel_1.add(up);
		}

		
		enterBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Enter click");
				User[] users = SQLMethods.GetUsers(SQLMethods.GetCon());
				panel_1.invalidate();
				scrollPane.invalidate();
				panel_1.removeAll();
				
				for(int i =0;i < users.length; i++) {
					if(!users[i].user_id.contains(textField.getText()))
						continue;
					
					if(users[i].user_id.compareTo(ClientInformation.Logined_id) == 0)
						continue;
					
					UserPanel up = new UserPanel(users[i]);
					panel_1.add(up);
				}
				
				panel_1.validate();
				scrollPane.validate();
			}
		});
		
		
		setVisible(true);
	}
}
