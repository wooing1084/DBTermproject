import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class Setting extends JFrame {

	private JFrame frame;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	String nickname = null;
	String email = "Email not saved. Please enter e-mail!";
	public Setting(String id, int pwd){

		JPanel contentPane;
		JTextField textField;
		JTextField textField_1;
		
		//Connection connection =  null;
		
		setBounds(500,500,490,542);
		setTitle("Setting");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel(); //메인
		JPanel panel_1 = new JPanel(); //닉네임 변경
		JPanel panel_2 = new JPanel(); //이메일 변경
		JPanel panel_3 = new JPanel(); //내 정보
		
		panel.setVisible(true);
		panel_1.setVisible(false);
		panel_2.setVisible(false);
		panel_3.setVisible(false);
		
		
		panel.setBounds(0, 0, 473, 505);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(255, 255, 255));
		
		JTextArea txtrSetting = new JTextArea();
		txtrSetting.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 33));
		//txtrSetting.setFont(new Font("맑은 고딕", Font.BOLD, 27));
		txtrSetting.setText("Setting");
		txtrSetting.setBackground(new Color(255, 255, 255));
		txtrSetting.setBounds(12, 26, 130, 46);
		panel.add(txtrSetting);
		
		JTextArea txtrEditProfile = new JTextArea();
		txtrEditProfile.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrEditProfile.setText("edit profile");
		txtrEditProfile.setBackground(new Color(255, 255, 255));
		txtrEditProfile.setBounds(12, 113, 212, 31);
		panel.add(txtrEditProfile);
		
		JButton btnNewButton = new JButton("Change Nickname");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(true);
				panel.setVisible(false);
			}
		});
		btnNewButton.setBounds(12, 154, 447, 53);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("change email");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
		btnNewButton_1.setBounds(12, 217, 447, 53);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(true);
				panel.setVisible(false);
			}
		});
		
		JTextArea txtrCheakProfile = new JTextArea();
		txtrCheakProfile.setText("Cheak profile");
		txtrCheakProfile.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrCheakProfile.setBackground(new Color(255, 255, 255));
		txtrCheakProfile.setBounds(12, 338, 212, 31);
		panel.add(txtrCheakProfile);
		
		JButton btnNewButton_2 = new JButton("My profile");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
		btnNewButton_2.setBounds(12, 388, 447, 53);
		panel.add(btnNewButton_2);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_3.setVisible(true);
				panel.setVisible(false);
			}
		});
		
		ImageIcon img = ImageManager.GetImageUsingFileSystem("src/assets/back.png", 50, 50);
		
		JButton btnNewButton_3_1 = new JButton(img);
		btnNewButton_3_1.setBounds(394, 10, 65, 62);
		panel.add(btnNewButton_3_1);
		btnNewButton_3_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//프로필 화면으로 돌아감
			}
		});

		////////////////패널 1/////////////////
		panel_1.setBounds(0, 0, 473, 505);
		panel_1.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JTextArea txtrSetting_1 = new JTextArea();
		txtrSetting_1.setText("Setting");
		txtrSetting_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 33));
		txtrSetting_1.setBackground(Color.WHITE);
		txtrSetting_1.setBounds(12, 26, 130, 46);
		panel_1.add(txtrSetting_1);
		
		JTextArea txtrYourNickname = new JTextArea();
		//txtrYourNickname.setEnabled(false);
		txtrYourNickname.setText("Your nickname");
		txtrYourNickname.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrYourNickname.setBackground(Color.WHITE);
		txtrYourNickname.setBounds(12, 108, 212, 31);
		panel_1.add(txtrYourNickname);
		
		JTextArea txtrChangeNickname = new JTextArea();
		txtrChangeNickname.setText("Change nickname");
		txtrChangeNickname.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrChangeNickname.setBackground(Color.WHITE);
		txtrChangeNickname.setBounds(12, 238, 212, 31);
		panel_1.add(txtrChangeNickname);

		try {
		//자신의 닉네임
			String q1 = "select username from user where user_id = \"" + id + "\";";
			ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(),q1);
			if(rs.next()) {
				nickname = rs.getString(1);
				textField = new JTextField(nickname);
				textField.setForeground(Color.black);
				textField.setEditable(false);
				textField.setBounds(12, 154, 449, 53);
				panel_1.add(textField);
				textField.setColumns(10);	
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//바꿀 닉네임
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(12, 284, 449, 53);
		panel_1.add(textField_1);
		textField_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textField_1.getText();
				String p1 = "update user set username = \""+text+"\"where user_id= \"" + id + "\";";
				if(text.compareTo(nickname) == 0) {
					new CustomDialog("Setting", "Cannot change to same nickname!");
				}
				else if(SQLMethods.ExecuteUpdate(SQLMethods.GetCon(), p1) != 0) {
					new CustomDialog("Setting", "Nickname Changed!");
				}
			}
		});

		JButton btnNewButton_3_3 = new JButton(img);
		//JButton btnNewButton_3_3 = new JButton("New button");
		btnNewButton_3_3.setBounds(396, 10, 65, 62);
		panel_1.add(btnNewButton_3_3);
		btnNewButton_3_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				panel_1.setVisible(false);
			}
		});
		
		
		
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 473, 505);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JTextArea txtrSetting_2 = new JTextArea();
		txtrSetting_2.setText("Setting");
		txtrSetting_2.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 33));
		txtrSetting_2.setBackground(Color.WHITE);
		txtrSetting_2.setBounds(12, 26, 130, 46);
		panel_2.add(txtrSetting_2);
		
		JTextArea txtrYourEmail = new JTextArea("Email not saved. Please enter e-mail!");
		txtrYourEmail.setText("Your e-mail");
		txtrYourEmail.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrYourEmail.setBackground(Color.WHITE);
		txtrYourEmail.setBounds(12, 108, 212, 31);
		panel_2.add(txtrYourEmail);
		try {
			//자신의 이메일
				String q1 = "select email from user where user_id = \"" + id + "\";";
				ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(),q1);
				if(rs.next()) {
					email = rs.getString(1);
					textField = new JTextField(email);
					textField.setForeground(Color.black);
					textField.setEditable(false);
					textField.setBounds(12, 154, 449, 53);
					panel_2.add(textField);
					textField.setColumns(10);	
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		
		
		JTextArea txtrChangeEmail = new JTextArea();
		txtrChangeEmail.setText("Change e-mail");
		txtrChangeEmail.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrChangeEmail.setBackground(Color.WHITE);
		txtrChangeEmail.setBounds(12, 238, 212, 31);
		panel_2.add(txtrChangeEmail);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(12, 284, 449, 53);
		panel_2.add(textField_2);
		textField_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textField_2.getText();
				String p1 = "update user set email = \""+text+"\"where user_id= \"" + id + "\";";
				if(text.compareTo(email) == 0) {
					new CustomDialog("Setting", "Cannot change to same E-mail!");
				}
				else if(SQLMethods.ExecuteUpdate(SQLMethods.GetCon(), p1) != 0) {
					new CustomDialog("Setting", "E-mail Changed!");
				}
			}
		});
		
		JButton btnNewButton_3 = new JButton(img);
		btnNewButton_3.setBounds(396, 10, 65, 62);
		panel_2.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				panel_2.setVisible(false);
			}
		});
		
		
		
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(0, 0, 473, 505);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JTextArea txtrSetting_2_1 = new JTextArea();
		txtrSetting_2_1.setText("Setting");
		txtrSetting_2_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 33));
		txtrSetting_2_1.setBackground(Color.WHITE);
		txtrSetting_2_1.setBounds(12, 26, 130, 46);
		panel_3.add(txtrSetting_2_1);
		
		JTextArea txtrNickname = new JTextArea();
		txtrNickname.setText("ID");
		txtrNickname.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrNickname.setBackground(Color.WHITE);
		txtrNickname.setBounds(12, 119, 212, 31);
		panel_3.add(txtrNickname);

		JTextArea txtrNickname_2 = new JTextArea();
		txtrNickname_2.setText("Nickname");
		txtrNickname_2.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrNickname_2.setBackground(Color.WHITE);
		txtrNickname_2.setBounds(12, 237, 212, 31);
		panel_3.add(txtrNickname_2);
		
		JTextArea txtrNickname_2_1 = new JTextArea();
		txtrNickname_2_1.setText("e-mail");
		txtrNickname_2_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrNickname_2_1.setBackground(Color.WHITE);
		txtrNickname_2_1.setBounds(12, 356, 212, 31);
		panel_3.add(txtrNickname_2_1);
		
		
		textField_3 = new JTextField(id);
		textField_3.setColumns(10);
		textField_3.setBounds(12, 174, 449, 53);
		textField_3.setForeground(Color.black);
		textField_3.setEditable(false);	
		panel_3.add(textField_3);
		
		textField_4 = new JTextField(nickname);
		textField_4.setColumns(10);
		textField_4.setBounds(12, 290, 449, 53);
		textField_4.setForeground(Color.black);
		textField_4.setEditable(false);	
		panel_3.add(textField_4);
		
		textField_5 = new JTextField(email);
		textField_5.setColumns(10);
		textField_5.setBounds(12, 409, 449, 53);
		textField_5.setForeground(Color.black);
		textField_5.setEditable(false);
		panel_3.add(textField_5);
		
		JButton btnNewButton_3_2 = new JButton(img);
		btnNewButton_3_2.setBounds(396, 10, 65, 62);
		panel_3.add(btnNewButton_3_2);
		btnNewButton_3_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				panel_3.setVisible(false);
			}
		});


		
		
		//contentPane = new JPanel();
		
		setVisible(true);
	}
}
