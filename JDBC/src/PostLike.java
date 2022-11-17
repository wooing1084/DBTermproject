import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class PostLike extends JFrame{
private JPanel panel;
	
	public PostLike(String post_id) {
		setBackground(new Color(255, 255, 255));
		setBounds(100,100,480,800);
		
		Font font = new Font("DialogInput", Font.BOLD, 20);
		
		Connection con=SQLMethods.GetCon();
		List<String> likers=SQLMethods.Likers(con,post_id);
		int num_of_like=likers.size();		
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panel.setBackground(new Color(255, 255, 255));
		setContentPane(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		panel_1.setPreferredSize(new Dimension(480,65));
		panel.add(panel_1);
		
		JLabel label = new JLabel("back");
		label.setFont(font);
		label.setBounds(15,7,65,40);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("back");
				dispose();
			}
		});
		panel_1.add(label);
		
		JLabel lblNewLabel = new JLabel("Liker List");
		lblNewLabel.setFont(font);
		lblNewLabel.setBounds(170,7,120,40);
		lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
		panel_1.add(lblNewLabel);
		
		//
		
		JLayeredPane layeredpanel = new JLayeredPane();
		layeredpanel.setPreferredSize(new Dimension(470, 700));
		
		panel.add(layeredpanel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(470, 700));
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
			
		JScrollPane scrollpane=new JScrollPane(panel_3);
		scrollpane.setSize(470, 700);
		//scrollpane.setPreferredSize(new Dimension(460, 700));
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			
			
		for(int i=0;i<num_of_like;i++) {
			User u=new User(post_id);
			UserPanel p1=new UserPanel(u);
			panel_3.add(p1);
		}			
		layeredpanel.add(scrollpane);
		setVisible(true);
	}
}
