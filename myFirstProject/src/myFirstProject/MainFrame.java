package myFirstProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {
	
	public MainFrame() {
		//FRAME
		setResizable(false);
		setTitle("Onboarding Page");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		//LAYOUT
		setLayout(new BorderLayout());
		
		//PANELS
		JPanel northPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel centerInnerPanel = new JPanel();
		

		JPanel signupPanel = new JPanel();
		
		
		
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		
		//NORTH PANEL
		JLabel welcomeLbl = new JLabel("Welcome back");
		welcomeLbl.setFont(new Font("Arial", Font.PLAIN, 25));
		northPanel.add(welcomeLbl, BorderLayout.CENTER);
		
		//CENTER PANEL
		JLabel welcomeLbl2 = new JLabel("login now or sign up!", SwingConstants.CENTER);
		welcomeLbl2.setFont(new Font("Arial", Font.PLAIN, 16));
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(welcomeLbl2, BorderLayout.CENTER);
		centerPanel.add(centerInnerPanel, BorderLayout.SOUTH);

		//CENTER INNER PANEL
		centerInnerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		JButton loginBtn = new JButton("LOG IN");
		JButton signupBtn = new JButton("SIGN UP");
		centerInnerPanel.add(loginBtn);
		centerInnerPanel.add(signupBtn);
		
		//LOGIN PANEL
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new BorderLayout());
		JLabel title = new JLabel("Welcome, Please Login", SwingConstants.CENTER);
		loginPanel.add(title, BorderLayout.NORTH);
		
		//LOGIN CENTER
		JPanel loginCenter = new JPanel();
		loginCenter.setLayout(new GridLayout(4, 1, 10 ,10));
		
		JLabel usernameLbl = new JLabel("username: ");
		JTextField enterUser = new JTextField();
		JLabel passwordLbl = new JLabel("password: ");
		JPasswordField enterPass = new JPasswordField();
		
		loginCenter.add(usernameLbl);
		loginCenter.add(enterUser);
		loginCenter.add(passwordLbl);
		loginCenter.add(enterPass);
		
		loginCenter.setBorder(new EmptyBorder(0, 40, 0, 40));
		
		loginPanel.add(loginCenter, BorderLayout.CENTER);
		
		//LOGIN SOUTH
		JPanel loginSouth = new JPanel();
		loginSouth.setLayout(new FlowLayout());
		
		JButton loginBtn2 = new JButton("LOG IN");
		JButton cancel = new JButton("CANCEL");
		
		loginSouth.add(loginBtn2);
		loginSouth.add(cancel);
		
		loginPanel.add(loginSouth, BorderLayout.SOUTH);
		loginPanel.setVisible(false);
		
		//SIGN UP PANEL
		signupPanel.setLayout(new FlowLayout());
		JLabel usernameLbl2 = new JLabel("Enter username: ");
		signupPanel.add(usernameLbl2);
		JTextField enterUser2 = new JTextField(15);
		signupPanel.add(enterUser2);
		JLabel passwordLbl2 = new JLabel("Enter password: ");
		signupPanel.add(passwordLbl2);
		JPasswordField enterPass2 = new JPasswordField(15);
		signupPanel.add(enterPass2);
		JLabel passwordLbl3 = new JLabel("confirm password: ");
		signupPanel.add(passwordLbl3);
		JPasswordField enterPass3 = new JPasswordField(15);
		signupPanel.add(enterPass3);
		JButton signupBtn2 = new JButton("SIGNUP");
		signupPanel.add(signupBtn2);
		signupPanel.setVisible(false);
		
		
		//LOGIN BUTTON BEHAVIOR
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				add(loginPanel);
				loginPanel.setVisible(true);
				northPanel.setVisible(false);
				centerPanel.setVisible(false);
				centerInnerPanel.setVisible(false);
				signupPanel.setVisible(false);
			}
			
		});
		
		//LOGIN BUTTON2 BEHAVIOR
		loginBtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
					
		});
		
		//CANCEL BUTTON BEHAVIOR
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(false);
				northPanel.setVisible(true);
				centerPanel.setVisible(true);
				centerInnerPanel.setVisible(true);
			}
			
		});

		signupBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				add(signupPanel, BorderLayout.CENTER);
				signupPanel.setVisible(true);
				northPanel.setVisible(false);
				centerPanel.setVisible(false);
				centerInnerPanel.setVisible(false);
			}
			
		});
		
	}
	public static void main(String[]  args) {
		SwingUtilities.invokeLater(() -> {
			new MainFrame().setVisible(true);
		});
	}
} 



	
