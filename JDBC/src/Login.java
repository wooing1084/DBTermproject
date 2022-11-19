import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
	private JPasswordField PWInput;
	/**
	 * Create the frame.
	 */
	public Login() {
		setBounds(100, 100, 300, 480);
		setLocationRelativeTo(null);
		
		setTitle("Login");
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon logoImage = null;
		ImageIcon logoText = null;

		
		logoImage = ImageManager.GetImageUsingFileSystem("src/assets/logo.png", 100, 100);
	
		logoText = ImageManager.GetImageUsingFileSystem("src/assets/UI/text_logo.png", 174, 39);
		
		JLabel Logo = new JLabel(logoImage);
		Logo.setBackground(new Color(133, 251, 254));
		Logo.setBounds(92, 32, 100, 100);
		contentPane.add(Logo);
		
		IDInput = new JTextField();
		IDInput.setBackground(new Color(236, 236, 236));
		IDInput.setBounds(65, 233, 206, 38);
		contentPane.add(IDInput);
		IDInput.setColumns(10);
		
		PWInput = new JPasswordField();
		PWInput.setEchoChar('*');
		PWInput.setBackground(new Color(236, 236, 236));
		PWInput.setColumns(10);
		PWInput.setBounds(65, 281, 206, 38);
		contentPane.add(PWInput);
		
		JLabel TwitterTextLogo = new JLabel(logoText);
		TwitterTextLogo.setBounds(55, 142, 174, 39);
		contentPane.add(TwitterTextLogo);
		
		JLabel IDText = new JLabel("ID:");
		IDText.setFont(new Font("Arial", Font.PLAIN, 18));
		IDText.setBounds(12, 233, 57, 38);
		contentPane.add(IDText);
		
		JLabel PasswordText = new JLabel("PW:");
		PasswordText.setFont(new Font("Arial", Font.PLAIN, 18));
		PasswordText.setBounds(12, 281, 57, 38);
		contentPane.add(PasswordText);
		
		ImageIcon LoginImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/login.png",190,36);
		JButton LoginBtn = new JButton(LoginImage);
		LoginBtn.setContentAreaFilled(false);
		LoginBtn.setOpaque(false);
		LoginBtn.setBorder(null);
		
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = IDInput.getText();
				String pw = PWInput.getText();
				
				Connection con = SQLMethods.GetCon();
				String result = SQLMethods.Login(con,id,pw);
				
				if(result == "")
					return;
				
				ClientInformation.Logined_id = result;
				 
				new MainFeed();
				dispose();
				
			}
		});
		LoginBtn.setBounds(50, 349, 190, 36);
		contentPane.add(LoginBtn);
		
		ImageIcon signinImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/sign_up.png",190,36);
		JButton RegisterBtn = new JButton(signinImage);
		RegisterBtn.setContentAreaFilled(false);
		RegisterBtn.setOpaque(false);
		RegisterBtn.setBorder(null);
		RegisterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Signin();
			}
		});
		RegisterBtn.setBounds(50, 395, 190, 36);
		contentPane.add(RegisterBtn);
		
		setVisible(true);
	}
}
