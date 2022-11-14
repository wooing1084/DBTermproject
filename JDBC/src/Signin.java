import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Signin extends JFrame {

	private JPanel contentPane;
	private JTextField IDTextField;
	private JTextField PWTextField;
	private JTextField NameTextField;
	private JTextField EMailText;

	/**
	 * Create the frame.
	 */
	public Signin() {
		setBounds(150, 150, 326, 304);
		setTitle("Sign in");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(120, 186, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel IDText = new JLabel("ID:");
		IDText.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		IDText.setBounds(39, 54, 28, 19);
		contentPane.add(IDText);
		
		IDTextField = new JTextField();
		IDTextField.setColumns(10);
		IDTextField.setBounds(65, 46, 206, 38);
		contentPane.add(IDTextField);
		
		PWTextField = new JTextField();
		PWTextField.setColumns(10);
		PWTextField.setBounds(65, 94, 206, 38);
		contentPane.add(PWTextField);
		
		NameTextField = new JTextField();
		NameTextField.setColumns(10);
		NameTextField.setBounds(65, 142, 206, 38);
		contentPane.add(NameTextField);
		
		JLabel PWText = new JLabel("PW:");
		PWText.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		PWText.setBounds(28, 105, 39, 19);
		contentPane.add(PWText);
		
		JLabel NameText = new JLabel("Name:");
		NameText.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		NameText.setBounds(12, 153, 55, 19);
		contentPane.add(NameText);
		
		EMailText = new JTextField();
		EMailText.setColumns(10);
		EMailText.setBounds(65, 184, 206, 38);
		contentPane.add(EMailText);
		
		JLabel lblEmail = new JLabel("e-mail:");
		lblEmail.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		lblEmail.setBounds(12, 195, 55, 19);
		contentPane.add(lblEmail);
		
		
		JButton SigninBtn = new JButton("Sign in");
		SigninBtn.setBounds(118, 232, 97, 23);
		SigninBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = IDTextField.getText();
				String pw = PWTextField.getText();
				String name = NameTextField.getText();
				String email = EMailText.getText();
				
				Connection con = SQLMethods.GetCon();
				int result = SQLMethods.Signin(con,id,pw,name,email);
				
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
		});
		
		contentPane.add(SigninBtn);
		
		
		setVisible(true);
	}
}
