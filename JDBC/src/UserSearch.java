import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class UserSearch extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	public UserSearch() {
		setBounds(150, 150, 300, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 284, 60);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 10, 224, 40);
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));
		
		
		JLabel icon = new JLabel(ImageManager.GetImageUsingFileSystem("src/assets/UI/search.png",10,10));
		icon.setPreferredSize(new Dimension(10,10));
		panel_2.add(icon);
		
		textField = new JTextField();
		Dimension size = textField.getPreferredSize();
		size.width = panel_2.getPreferredSize().width - 10;
		textField.setPreferredSize(new Dimension(214, 21));
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel enterBtn = new JLabel("enter");
		enterBtn.setBounds(243, 10, 41, 40);
		panel.add(enterBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 58, 284, 358);
		contentPane.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
	}
}
