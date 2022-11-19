import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Signin extends JFrame {

	private JPanel contentPane;
	private JTextField IDTextField;
	private JPasswordField PWTextField;
	private JTextField NameTextField;
	private JTextField EMailText;

	/**
	 * Create the frame.
	 */
	public Signin() {
		setBounds(150, 150, 300, 480);
		setLocationRelativeTo(null);
		setTitle("Sign in");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon logoImage = ImageManager.GetImageUsingFileSystem("src/assets/logo.png", 100, 100);
		JLabel Logo = new JLabel(logoImage);
		Logo.setBackground(new Color(133, 251, 254));
		Logo.setBounds(92, 32, 100, 100);
		contentPane.add(Logo);
		
		JLabel IDText = new JLabel("ID:");
		IDText.setFont(new Font("Arial", Font.PLAIN, 12));
		IDText.setBounds(39, 163, 28, 19);
		contentPane.add(IDText);
		
		IDTextField = new JTextField();
		IDTextField.setColumns(10);
		IDTextField.setBounds(65, 155, 206, 38);
		contentPane.add(IDTextField);
		
		PWTextField = new JPasswordField();
		PWTextField.setColumns(10);
		PWTextField.setEchoChar('*');
		PWTextField.setBounds(65, 203, 206, 38);
		contentPane.add(PWTextField);
		
		NameTextField = new JTextField();
		NameTextField.setColumns(10);
		NameTextField.setBounds(65, 251, 206, 38);
		contentPane.add(NameTextField);
		
		JLabel PWText = new JLabel("PW:");
		PWText.setFont(new Font("Arial", Font.PLAIN, 12));
		PWText.setBounds(28, 214, 39, 19);
		contentPane.add(PWText);
		
		JLabel NameText = new JLabel("Name:");
		NameText.setFont(new Font("Arial", Font.PLAIN, 12));
		NameText.setBounds(12, 262, 55, 19);
		contentPane.add(NameText);
		
		EMailText = new JTextField();
		EMailText.setColumns(10);
		EMailText.setBounds(65, 293, 206, 38);
		contentPane.add(EMailText);
		
		JLabel lblEmail = new JLabel("e-mail:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		lblEmail.setBounds(12, 304, 55, 19);
		contentPane.add(lblEmail);
		
		ImageIcon signinImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/sign_up.png",190,36);
		JButton SigninBtn = new JButton(signinImage);
		SigninBtn.setBounds(45, 383, 190, 36);
		SigninBtn.setContentAreaFilled(false);
		SigninBtn.setOpaque(false);
		SigninBtn.setBorder(null);
		SigninBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = null; 
				String pw = null; 
				String name = null; 
				String email = null; 
				
				id = IDTextField.getText();
				pw = PWTextField.getText();
				name = NameTextField.getText();
				email = EMailText.getText();
				Connection con = SQLMethods.GetCon();
				int result = SQLMethods.Signin(con,id,pw,name,email);
				
				if(id == null || id.equals("")) {
					new CustomDialog("Dialog", "Please Enter ID!");
				}
				else if(pw == null || pw.equals("")) {
					new CustomDialog("Dialog", "Please Enter Password!");
				}
				else if(name == null || name.equals("")) {
					new CustomDialog("Dialog", "Please Enter name!");
				}
				else if(email == null || email.equals("")) {
					new CustomDialog("Dialog", "Please Enter email!");
				}
				else {
					if(result == 0)
					{
						new CustomDialog("Dialog", "ID is already exists!");
					}
					else if(result == 1){
						new CustomDialog("Dialog", "Sign in Success!");
						dispose();					
					}
					else
						new CustomDialog("Dialog", "Error!");
					
				}
			}
		});
		
		contentPane.add(SigninBtn);
		
		
		setVisible(true);
	}
}
