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
	String profileDir = null;
	String backgroundDir = null;
	String email = "Email not saved. Please enter e-mail!";
	private JTextField textField_6;
	private JTextField textField_7;
	public Setting(String id){

		JPanel contentPane;
		JTextField textField;
		JTextField textField_1;
		
		//Connection connection =  null;
		
		setBounds(0, 0, 478, 763);
		setLocationRelativeTo(null);
		setTitle("Setting");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel(); //메인
		JPanel panel_1 = new JPanel(); //닉네임 변경
		JPanel panel_2 = new JPanel(); //이메일 변경
		JPanel panel_3 = new JPanel(); //내 정보
		JPanel panel_4 = new JPanel(); //프로필 이미지 및 배경사진 변경
		JPanel panel_5 = new JPanel(); //비밀번호 변경
		JPanel panel_6 = new JPanel(); //한줄소개 변경
		
		panel.setVisible(true);
		panel_1.setVisible(false);
		panel_2.setVisible(false);
		panel_3.setVisible(false);
		panel_4.setVisible(false);
		panel_5.setVisible(false);
		panel_6.setVisible(false);
		
		panel.setBounds(0, 0, 478, 763);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(255, 255, 255));
		
		JTextArea txtrSetting = new JTextArea();
		txtrSetting.setEditable(false);
		txtrSetting.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 33));
		//txtrSetting.setFont(new Font("맑은 고딕", Font.BOLD, 27));
		txtrSetting.setText("Setting");
		txtrSetting.setBackground(new Color(255, 255, 255));
		txtrSetting.setBounds(12, 26, 130, 46);
		panel.add(txtrSetting);
		
		JTextArea txtrEditProfile = new JTextArea();
		txtrEditProfile.setEditable(false);
		txtrEditProfile.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrEditProfile.setText("edit profile");
		txtrEditProfile.setBackground(new Color(255, 255, 255));
		txtrEditProfile.setBounds(12, 136, 212, 31);
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
		btnNewButton.setBounds(12, 187, 447, 53);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("change email");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
		btnNewButton_1.setBounds(12, 327, 447, 53);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(true);
				panel.setVisible(false);
			}
		});
		
		JTextArea txtrCheakProfile = new JTextArea();
		txtrCheakProfile.setEditable(false);
		txtrCheakProfile.setText("Cheak profile");
		txtrCheakProfile.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrCheakProfile.setBackground(new Color(255, 255, 255));
		txtrCheakProfile.setBounds(12, 561, 212, 31);
		panel.add(txtrCheakProfile);
		
		JButton btnNewButton_2 = new JButton("My profile");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
		btnNewButton_2.setBounds(12, 612, 447, 53);
		panel.add(btnNewButton_2);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_3.setVisible(true);
				panel.setVisible(false);
			}
		});
		
		ImageIcon img = ImageManager.GetImageUsingFileSystem("src/assets/UI/back.png", 50, 50);
		
		JButton btnNewButton_3_1 = new JButton(img);
		btnNewButton_3_1.setBounds(394, 10, 65, 62);
		btnNewButton_3_1.setContentAreaFilled(false);
		btnNewButton_3_1.setOpaque(false);
		btnNewButton_3_1.setBorder(null);
		btnNewButton_3_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//프로필 화면으로 돌아감
				dispose();
			}
		});
		panel.add(btnNewButton_3_1);
		
		//이미지 바꾸기
		JButton btnChangeImage = new JButton("Change image");
		btnChangeImage.setHorizontalAlignment(SwingConstants.LEFT);
		btnChangeImage.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
		btnChangeImage.setBackground(Color.WHITE);
		btnChangeImage.setBounds(12, 397, 447, 53);
		btnChangeImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_4.setVisible(true);
				panel.setVisible(false);
			}
		});
		panel.add(btnChangeImage);
		
		//비밀번호 바꾸기
		JButton btnChangePassword = new JButton("Change password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChangePassword.setHorizontalAlignment(SwingConstants.LEFT);
		btnChangePassword.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
		btnChangePassword.setBackground(Color.WHITE);
		btnChangePassword.setBounds(12, 257, 447, 53);
		panel.add(btnChangePassword);
		
		//
		JButton btnChangeMessage = new JButton("Change introduce");
		btnChangeMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_6.setVisible(true);
				panel.setVisible(false);
			}
		});
		btnChangeMessage.setHorizontalAlignment(SwingConstants.LEFT);
		btnChangeMessage.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
		btnChangeMessage.setBackground(Color.WHITE);
		btnChangeMessage.setBounds(12, 467, 447, 53);
		panel.add(btnChangeMessage);
		
		


		////////////////패널 1/////////////////
		panel_1.setBounds(0, 0, 473, 505);
		panel_1.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JTextArea txtrSetting_1 = new JTextArea();
		txtrSetting_1.setEditable(false);
		txtrSetting_1.setText("Setting");
		txtrSetting_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 33));
		txtrSetting_1.setBackground(Color.WHITE);
		txtrSetting_1.setBounds(12, 26, 130, 46);
		panel_1.add(txtrSetting_1);
		
		JTextArea txtrYourNickname = new JTextArea();
		txtrYourNickname.setEditable(false);
		//txtrYourNickname.setEnabled(false);
		txtrYourNickname.setText("Your nickname");
		txtrYourNickname.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrYourNickname.setBackground(Color.WHITE);
		txtrYourNickname.setBounds(12, 108, 212, 31);
		panel_1.add(txtrYourNickname);
		
		JTextArea txtrChangeNickname = new JTextArea();
		txtrChangeNickname.setEditable(false);
		txtrChangeNickname.setText("Change nickname");
		txtrChangeNickname.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrChangeNickname.setBackground(Color.WHITE);
		txtrChangeNickname.setBounds(12, 238, 212, 36);
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
		txtrSetting_2.setEditable(false);
		txtrSetting_2.setText("Setting");
		txtrSetting_2.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 33));
		txtrSetting_2.setBackground(Color.WHITE);
		txtrSetting_2.setBounds(12, 26, 130, 46);
		panel_2.add(txtrSetting_2);
		
		JTextArea txtrYourEmail = new JTextArea("Email not saved. Please enter e-mail!");
		txtrYourEmail.setEditable(false);
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
		txtrChangeEmail.setEditable(false);
		txtrChangeEmail.setText("Change e-mail");
		txtrChangeEmail.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrChangeEmail.setBackground(Color.WHITE);
		txtrChangeEmail.setBounds(12, 238, 212, 36);
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
		txtrSetting_2_1.setEditable(false);
		txtrSetting_2_1.setText("Setting");
		txtrSetting_2_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 33));
		txtrSetting_2_1.setBackground(Color.WHITE);
		txtrSetting_2_1.setBounds(12, 26, 130, 46);
		panel_3.add(txtrSetting_2_1);
		
		JTextArea txtrNickname = new JTextArea();
		txtrNickname.setEditable(false);
		txtrNickname.setText("ID");
		txtrNickname.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrNickname.setBackground(Color.WHITE);
		txtrNickname.setBounds(12, 119, 212, 31);
		panel_3.add(txtrNickname);

		JTextArea txtrNickname_2 = new JTextArea();
		txtrNickname_2.setEditable(false);
		txtrNickname_2.setText("Nickname");
		txtrNickname_2.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrNickname_2.setBackground(Color.WHITE);
		txtrNickname_2.setBounds(12, 237, 212, 31);
		panel_3.add(txtrNickname_2);
		
		JTextArea txtrNickname_2_1 = new JTextArea();
		txtrNickname_2_1.setEditable(false);
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
		
		////////////////패널 4/////////////////
		panel_4.setBounds(0, 0, 473, 505);
		panel_4.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JButton btnNewButton_3_4 = new JButton(img);
		btnNewButton_3_4.setBounds(394, 10, 65, 62);
		btnNewButton_3_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				panel_4.setVisible(false);
			}
		});
		panel_4.add(btnNewButton_3_4);
		
		JTextArea txtrSetting_2_1_1 = new JTextArea();
		txtrSetting_2_1_1.setEditable(false);
		txtrSetting_2_1_1.setText("Setting");
		txtrSetting_2_1_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 33));
		txtrSetting_2_1_1.setBackground(Color.WHITE);
		txtrSetting_2_1_1.setBounds(12, 26, 139, 46);
		panel_4.add(txtrSetting_2_1_1);
		
		JTextArea txtrChangeProfileImage = new JTextArea("Change profile image");
		txtrChangeProfileImage.setEditable(false);
		txtrChangeProfileImage.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrChangeProfileImage.setBackground(Color.WHITE);
		txtrChangeProfileImage.setBounds(12, 108, 249, 35);
		panel_4.add(txtrChangeProfileImage);
		
		JTextArea txtrChangeBackgroundImage = new JTextArea();
		txtrChangeBackgroundImage.setEditable(false);
		txtrChangeBackgroundImage.setText("Change background image");
		txtrChangeBackgroundImage.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrChangeBackgroundImage.setBackground(Color.WHITE);
		txtrChangeBackgroundImage.setBounds(12, 238, 311, 40);
		panel_4.add(txtrChangeBackgroundImage);
		
		try {
			//자신의 프로필 사진 경로
				String q1 = "select profile_Image_dir from user where user_id = \"" + id + "\";";
				ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(),q1);
				if(rs.next()) {
					profileDir = rs.getString(1);	
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		try {
			//자신의 배경 사진 경로
				String q1 = "select background_Image_dir from user where user_id = \"" + id + "\";";
				ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(),q1);
				if(rs.next()) {
					backgroundDir = rs.getString(1);	
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		textField_6 = new JTextField();
		textField_6.setToolTipText("Enter profile image path");
		textField_6.setColumns(10);
		textField_6.setBounds(12, 153, 449, 53);
		textField_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textField_6.getText();
				String p1 = "update user set profile_Image_dir = \""+text+"\"where user_id= \"" + id + "\";";
				if(SQLMethods.ExecuteUpdate(SQLMethods.GetCon(), p1) != 0) {
					new CustomDialog("Setting", "profile image Changed!");
				}
			}
		});
		panel_4.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(12, 284, 449, 53);
		//TextHint hint = new TextHint(textField_7, "Enter background image path");
		textField_7.setToolTipText("Enter background image path");
		textField_7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textField_7.getText();
				String p1 = "update user set background_Image_dir = \""+text+"\"where user_id= \"" + id + "\";";
				if(SQLMethods.ExecuteUpdate(SQLMethods.GetCon(), p1) != 0) {
					new CustomDialog("Setting", "background image Changed!");
				}
			}
		});
		panel_4.add(textField_7);
		//panel_4.add(hint);

		/*비밀번호 변경 부분 시간 되면
		panel_5.setBounds(0, 0, 473, 505);
		panel_5.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel_5);
		panel_5.setLayout(null);
		
		JTextArea txt = new JTextArea();
		txt.setEditable(false);
		txt.setText("Setting");
		txt.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 33));
		txt.setBackground(Color.WHITE);
		txt.setBounds(12, 26, 130, 46);
		panel_5.add(txtrSetting_1);
		
		JTextArea txtYourPassword = new JTextArea();
		txtYourPassword.setEditable(false);
		//txtrYourNickname.setEnabled(false);
		txtYourPassword.setText("Your nickname");
		txtYourPassword.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtYourPassword.setBackground(Color.WHITE);
		txtYourPassword.setBounds(12, 108, 212, 31);
		panel_1.add(txtrYourNickname);
		
		JTextArea txtrChangeNickname = new JTextArea();
		txtrChangeNickname.setEditable(false);
		txtrChangeNickname.setText("Change nickname");
		txtrChangeNickname.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrChangeNickname.setBackground(Color.WHITE);
		txtrChangeNickname.setBounds(12, 238, 212, 36);
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

		*/
		/////////////////////한줄소개 변경//////////////
		panel_6.setBounds(0, 0, 473, 505);
		panel_6.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel_6);
		panel_6.setLayout(null);
		
		JTextArea txt2 = new JTextArea();
		txt2.setEditable(false);
		txt2.setText("Setting");
		txt2.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 33));
		txt2.setBackground(Color.WHITE);
		txt2.setBounds(12, 26, 130, 46);
		panel_6.add(txt2);
		
		JTextArea txtYourIntroduce = new JTextArea();
		txtYourIntroduce.setEditable(false);
		//txtrYourNickname.setEnabled(false);
		txtYourIntroduce.setText("Your introduce");
		txtYourIntroduce.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtYourIntroduce.setBackground(Color.WHITE);
		txtYourIntroduce.setBounds(12, 108, 212, 31);
		panel_6.add(txtYourIntroduce);
		
		JTextArea txtrChangeIntroduce = new JTextArea();
		txtrChangeIntroduce.setEditable(false);
		txtrChangeIntroduce.setText("Change introduce");
		txtrChangeIntroduce.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 23));
		txtrChangeIntroduce.setBackground(Color.WHITE);
		txtrChangeIntroduce.setBounds(12, 238, 212, 36);
		panel_6.add(txtrChangeIntroduce);

		String introduce = null;
		try {
		//자신의 한줄소개
			String q1 = "select introduce from user where user_id = \"" + id + "\";";
			ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(),q1);
			if(rs.next()) {
				introduce = rs.getString(1);
				textField = new JTextField(introduce);
				textField.setForeground(Color.black);
				textField.setEditable(false);
				textField.setBounds(12, 154, 449, 53);
				panel_6.add(textField);
				textField.setColumns(10);	
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//바꿀 한줄소개
		JTextField textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(12, 284, 449, 53);
		panel_6.add(textField_8);
		
		JButton btnNewButton_3_5 = new JButton(img);
		btnNewButton_3_5.setBounds(394, 10, 65, 62);
		btnNewButton_3_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				panel_6.setVisible(false);
			}
		});
		panel_6.add(btnNewButton_3_5);
		textField_8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textField_8.getText();
				String p1 = "update user set introduce = \""+text+"\"where user_id= \"" + id + "\";";
				if(SQLMethods.ExecuteUpdate(SQLMethods.GetCon(), p1) != 0) {
					new CustomDialog("Setting", "Introduce Changed!");
				}
			}
		});
		//contentPane = new JPanel();
		setVisible(true);
	}
}

