import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class posting extends JFrame {

	private JPanel contentPane;
	private JTextField imurl;
	

	public posting() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 88, 440, 206);
		contentPane.add(textArea);
		
		
		
		imurl = new JTextField();
		imurl.setBounds(135, 304, 208, 23);
		contentPane.add(imurl);
		imurl.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("image url");
		lblNewLabel.setBounds(66, 308, 57, 15);
		contentPane.add(lblNewLabel);
		
		
		
		JButton btnadd = new JButton("add");
		btnadd.setBounds(355, 304, 97, 23);
		contentPane.add(btnadd);
		
		
		JButton btnpost = new JButton("post");
		btnpost.setBounds(355, 337, 97, 23);
		contentPane.add(btnpost);
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 480, 800);
		setTitle("Twitter");
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		
		//
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] arry = {imurl.getText()};
		        List<String> list = new ArrayList<String>(); 
		        Arrays.asList(arry); //String--> List<Strig> ???
		    }
		    
		});
	
		
		btnpost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            
			
		}	
	});
	}
}

