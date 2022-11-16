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
		
		
		JLabel icon = new JLabel(ImageManager.GetImageUsingFileSystem("src/assets/UI/search.png",10,10));
		icon.setPreferredSize(new Dimension(40, 40));
		panel_2.add(icon);
		
		textField = new JTextField();
		textField.setBackground(new Color(247, 247, 247));
		textField.setPreferredSize(new Dimension(274, 40));
		panel_2.add(textField);
		
		JLabel enterBtn = new JLabel("Enter");
		enterBtn.setHorizontalAlignment(SwingConstants.CENTER);
		enterBtn.setBackground(new Color(255, 255, 255));
		enterBtn.setBounds(392, 10, 60, 60);
		panel.add(enterBtn);
		
		ImageIcon backImage = ImageManager.GetImageUsingFileSystem("src/assets/back.png", 40, 40);
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
		
		User uInfo1 = new User("abcd");	
		UserPanel u1 = new UserPanel(uInfo1);
		panel_1.add(u1);
		UserPanel u2 = new UserPanel(uInfo1);
		panel_1.add(u2);
		UserPanel u3 = new UserPanel(uInfo1);
		panel_1.add(u3);
		
		setVisible(true);
	}
}
