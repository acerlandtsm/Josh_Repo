package josh;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class signupPage extends JFrame implements ActionListener {
	
	//VARIABLES
	private Border LINE_BORDER = BorderFactory.createLineBorder(Color.GRAY);
	
	private MainFrame MainFrame;
	
	private JPanel pnlMain;
	
	private JLabel lblSignUpNow;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblConfirmPassword;
	
	private JTextField txtFieldUsername;
	private JPasswordField passFieldPassword;
	private JPasswordField passFieldConfirmPassword;
	
	private JButton btnSignup;
	private JButton btnCancel;
	
	public signupPage() throws HeadlessException {
		initGUI();
	}

	public signupPage(GraphicsConfiguration gc) {
		super(gc);
	}
	
	public signupPage(String title) throws HeadlessException{
		
	}
	
	public signupPage(String title, GraphicsConfiguration gc) {
		super(title, gc);
	} 
	
	private void initGUI() {
		setResizable(false);
		setTitle("Sign Up");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		{
			pnlMain = new JPanel(new BorderLayout(3, 3));
			add(pnlMain, BorderLayout.CENTER);
			pnlMain.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnlMain.setBorder(LINE_BORDER);
			{
				JPanel pnlNorth = new JPanel(new BorderLayout(5,5));
				add(pnlNorth, BorderLayout.NORTH);
				pnlNorth.setBorder(new EmptyBorder(10, 10, 10, 10));
				{
					lblSignUpNow = new JLabel("Signup with us now!", SwingConstants.CENTER);
					pnlNorth.add(lblSignUpNow, BorderLayout.NORTH);
				}
			}
			{
				JPanel pnlCenter = new JPanel(new BorderLayout());
				add(pnlCenter, BorderLayout.CENTER);
				{
					JPanel pnlLabels = new JPanel(new GridLayout(4, 0));
					pnlCenter.add(pnlLabels, BorderLayout.WEST);
					pnlLabels.setBorder(new EmptyBorder(10, 30, 10, 10));
					{
						lblUsername = new JLabel("New Username: ");
						pnlLabels.add(lblUsername);
					}
					{
						lblPassword = new JLabel("Password: ");
						pnlLabels.add(lblPassword);
					}
					{
						lblConfirmPassword = new JLabel("Confirm Password: ");
						pnlLabels.add(lblConfirmPassword);
					}
				}
				{
					JPanel pnlTxtFields = new JPanel(new GridLayout(4,0));
					pnlCenter.add(pnlTxtFields, BorderLayout.CENTER);
					pnlTxtFields.setBorder(new EmptyBorder(10, 10, 10, 30));
					
					{
						txtFieldUsername= new JTextField();
						pnlTxtFields.add(txtFieldUsername);
					}
					{
						passFieldPassword = new JPasswordField();
						pnlTxtFields.add(passFieldPassword);
					}
					{
						passFieldConfirmPassword = new JPasswordField();
						pnlTxtFields.add(passFieldConfirmPassword);
					}
				}
			}
			{
				JPanel pnlSouth = new JPanel(new FlowLayout());
				add(pnlSouth, BorderLayout.SOUTH);
				pnlSouth.setBorder(LINE_BORDER);
				{
					btnSignup = new JButton("SIGNUP");
					pnlSouth.add(btnSignup);
					btnSignup.setActionCommand("signup");
					btnSignup.addActionListener(this);
					
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
		
		switch (actionCommand) {
		case "signup":
			String user = txtFieldUsername.getText();
			char[] pass = passFieldPassword.getPassword();
			char[] confirmPass = passFieldConfirmPassword.getPassword();
			if (userSession.accountChecker(user, pass, confirmPass)) {
				if (userSession.signup(user, pass)) {
					JOptionPane.showMessageDialog(null, "Signed up successfully, please log in");
					MainFrame = new MainFrame();
					MainFrame.setVisible(true);
					this.dispose();		
					break;
				}
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
