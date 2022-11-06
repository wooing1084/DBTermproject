import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.Icon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField IDInput;
	private JTextField PWInput;
	/**
	 * Create the frame.
	 */
	public Login() {
		setBounds(100, 100, 300, 480);
		
		setTitle("Login");
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(120, 186, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon logoImage = null;
		ImageIcon logoText = null;
		
		try {
			BufferedImage img = ImageIO.read(this.getClass().getResource("assets/logo.png"));
			
			//logotext ÀÐ±â ¿À·ù
			BufferedImage img2 = ImageIO.read(this.getClass().getResource("assets/logo.png"));
			
			logoImage = new ImageIcon(img);
			logoText = new ImageIcon(img2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Image temp = logoImage.getImage();
		Image changeTemp = temp.getScaledInstance(100,100, Image.SCALE_SMOOTH);
		logoImage = new ImageIcon(changeTemp);
		
		temp = logoText.getImage();
		changeTemp = temp.getScaledInstance(259,38, Image.SCALE_SMOOTH);
		logoText = new ImageIcon(changeTemp);
		
		JLabel Logo = new JLabel(logoImage);
		Logo.setBackground(new Color(133, 251, 254));
		Logo.setBounds(87, 31, 100, 100);
		contentPane.add(Logo);
		
		IDInput = new JTextField();
		IDInput.setBounds(65, 193, 206, 38);
		contentPane.add(IDInput);
		IDInput.setColumns(10);
		
		PWInput = new JTextField();
		PWInput.setColumns(10);
		PWInput.setBounds(65, 241, 206, 38);
		contentPane.add(PWInput);
		
		JLabel TwitterTextLogo = new JLabel(logoText);
		TwitterTextLogo.setBounds(12, 135, 259, 38);
		contentPane.add(TwitterTextLogo);
		
		JLabel IDText = new JLabel("ID:");
		IDText.setFont(new Font("±¼¸²", Font.PLAIN, 26));
		IDText.setBounds(12, 193, 57, 38);
		contentPane.add(IDText);
		
		JLabel PasswordText = new JLabel("PW:");
		PasswordText.setFont(new Font("±¼¸²", Font.PLAIN, 26));
		PasswordText.setBounds(12, 241, 57, 38);
		contentPane.add(PasswordText);
		
		JButton LoginBtn = new JButton("Login");
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = IDInput.getText();
				String pw = PWInput.getText();
				
				Connection con = SQLMethods.GetCon();
				SQLMethods.Login(con,id,pw);
			}
		});
		LoginBtn.setBounds(51, 306, 97, 38);
		contentPane.add(LoginBtn);
		
		JButton RegisterBtn = new JButton("Sign in");
		RegisterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Signin();
			}
		});
		RegisterBtn.setBounds(160, 306, 97, 38);
		contentPane.add(RegisterBtn);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
