package josh;

import database.DBConnection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.desktop.UserSessionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class loginPage extends JFrame implements ActionListener {
	
	//VARIABLES AND DECLARATIONS
	private Border LINE_BORDER = BorderFactory.createLineBorder(Color.GRAY);
	
	private MainFrame MainFrame;
	private dashboard dashboard;
	
	private JPanel pnlMain;
	private JLabel lblPleaseLogIn;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel placeholder;
	
	private JTextField txtFieldUsername;
	private JPasswordField passFieldPassword;
	
	private JButton btnLogin;
	private JButton btnCancel;
	
	public loginPage() throws HeadlessException {
		initGUI();
	}
	
	public loginPage(GraphicsConfiguration gc) {
		super(gc);
	}
	
	public loginPage(String title) throws HeadlessException {
		super(title);
	}
	
	public loginPage(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}
	//INITIALIZE GUI
	public void initGUI() {
		setResizable(false);
		setTitle("Login");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		{
			pnlMain = new JPanel(new BorderLayout(3, 3));
			add(pnlMain, BorderLayout.CENTER);
			pnlMain.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnlMain.setBorder(LINE_BORDER);
			{//NORTH PANEL
				JPanel pnlNorth = new JPanel(new BorderLayout(5, 5));
				add(pnlNorth, BorderLayout.NORTH);
				pnlNorth.setBorder(LINE_BORDER);
				pnlNorth.setBorder(new EmptyBorder(30,0,0,0));
				{
					lblPleaseLogIn = new JLabel("Login to your Account!", SwingConstants.CENTER);
					pnlNorth.add(lblPleaseLogIn, BorderLayout.NORTH);

				}
			}
			{//CENTER PANEL
				JPanel pnlCenter = new JPanel(new BorderLayout());
				add(pnlCenter, BorderLayout.CENTER);
				pnlCenter.setBorder(new EmptyBorder(20,70,40,70));
				{
					JPanel pnlCenterLabels = new JPanel(new GridLayout(3,1));
					pnlCenter.add(pnlCenterLabels, BorderLayout.WEST);
					
					{
						lblUsername = new JLabel("Username: ");
						pnlCenterLabels.add(lblUsername);
					}
					{
						placeholder  = new JLabel();
						pnlCenterLabels.add(placeholder);
					}
					{
						lblPassword = new JLabel("Password: ");
						pnlCenterLabels.add(lblPassword);
					} 
				}
				{
					JPanel pnlCenterTxtField = new JPanel(new GridLayout(3,1));
					pnlCenter.add(pnlCenterTxtField, BorderLayout.CENTER);
					{
						txtFieldUsername = new JTextField();
						pnlCenterTxtField.add(txtFieldUsername);
					}
					{
						placeholder  = new JLabel();
						pnlCenterTxtField.add(placeholder);
					}
					{
						passFieldPassword = new JPasswordField();
						pnlCenterTxtField.add(passFieldPassword);
					}
				}
				{
					JPanel pnlCenterEast = new JPanel(new BorderLayout());
					pnlCenter.add(pnlCenterEast, BorderLayout.EAST);
					{
						placeholder = new JLabel("");
						pnlCenterEast.add(placeholder, BorderLayout.EAST);
					}
				}
			}
			{//SOUTH PANEL
				JPanel pnlSouth = new JPanel(new FlowLayout());
				add(pnlSouth, BorderLayout.SOUTH);
				pnlSouth.setBorder(LINE_BORDER);
				{
					btnLogin = new JButton("LOGIN");
					pnlSouth.add(btnLogin);
					btnLogin.setActionCommand("login");
					btnLogin.addActionListener(this);
					
					btnCancel = new JButton("CANCEL");
					pnlSouth.add(btnCancel);
					btnCancel.setActionCommand("cancel");
					btnCancel.addActionListener(this);
				}
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		//SWITCH ACTION COMMAND
		switch (actionCommand) {
		
		case "login":
			String user = txtFieldUsername.getText();
			char[] pass = passFieldPassword.getPassword();
			if (userSession.login(user, pass)) {
				dashboard = new dashboard();
				dashboard.setVisible(true);
				this.dispose();
				break;	
			} else {
				JOptionPane.showMessageDialog(null, "Invalid Username or Password.");
			}
			break;
			
		case "cancel":
			MainFrame = new MainFrame();
			MainFrame.setVisible(true);
			this.dispose();
			break;
			
		default:
			System.out.println("Please Try Again.");
		}
	}
}
