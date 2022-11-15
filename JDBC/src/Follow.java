

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;

import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;

public class Follow extends JFrame{
	private JFrame frame;
	private void Follow() {
		Connection con=SQLMethods.GetCon();
		List<String> following=SQLMethods.Followers(con,"abcd1");
		int num_of_follow=following.size();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("back");
		Dimension buttonsize=new Dimension(65,25);
		btnNewButton.setPreferredSize(buttonsize);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		Dimension labelsize=new Dimension(380,40);
		lblNewLabel.setPreferredSize(labelsize);
		lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		
		panel_2.setBackground(new Color(255, 255, 0));
		
		panel.add(panel_2, BorderLayout.CENTER);
		
		
		//panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		panel_2.setLayout(new GridLayout(1,num_of_follow,0,0));
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_2);
		
		Box box1=Box.createVerticalBox();
		
		//for(int i=0;i<num_of_follow;i++) {
			Post p=new Post("abcd1");
			NamePanel p1=new NamePanel(p);
			Dimension psize=new Dimension(480,80);
			p1.setPreferredSize(psize);
			
			p1.setBackground(new Color(0,0,0));
			box1.add(p1);
			
		//}
		panel_2.add(box1);
		//panel_2.add(Box.createVerticalStrut(800-100*num_of_follow));
	}

}