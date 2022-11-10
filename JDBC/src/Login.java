import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.sql.Connection;
import javax.swing.JTextField;
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

		logoImage = ImageManager.GetImage("src/assets/logo.png", 100, 100);
		logoText = ImageManager.GetImage("src/assets/logoText.png", 259, 38);
		
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
				ClientInformation.Logined_id = SQLMethods.Login(con,id,pw);
				
				new MainFeed();
				dispose();
				
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
	}
}
