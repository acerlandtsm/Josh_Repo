//package Lester;
//
//import java.awt.BorderLayout;
//import java.awt.CardLayout;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.GraphicsConfiguration;
//import java.awt.GridLayout;
//import java.awt.HeadlessException;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.SwingUtilities;
//import javax.swing.border.Border;
//import javax.swing.border.EmptyBorder;
//
//public class MainFrameLes extends JFrame implements ActionListener {
//	
//	//DECLARE VARIABLES AND COMPONENTS
//	private JPanel cardPanel;
//	private Border LINE_BORDER = BorderFactory.createLineBorder(Color.GRAY);
//	
//	private JPanel pnlMain;
//	private JLabel lblWelcome;
//	private JLabel lblLogInSignUp;
//	private JButton btnLogin;
//	private JButton btnSignUp;
//	private Login loginFrame;
//	
//	public MainFrameLes() throws HeadlessException {
//		initGUI();
//	}
//
//	public MainFrameLes(GraphicsConfiguration gc) {
//		super(gc);
//		// TODO Auto-generated constructor stub
//	}
//
//	public MainFrameLes(String title) throws HeadlessException {
//		super(title);
//		// TODO Auto-generated constructor stub
//	}
//
//	public MainFrameLes(String title, GraphicsConfiguration gc) {
//		super(title, gc);
//		// TODO Auto-generated constructor stub
//	}
//	
//	//INITIALIZE GUI
//	private void initGUI() {
//		setResizable(false);
//		setTitle("Onboarding Page");
//		setSize(400, 300);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
//		setLayout(new BorderLayout());
//		
//		{
//			pnlMain = new JPanel(new BorderLayout(3, 3));
//			add(pnlMain, BorderLayout.CENTER);
//			pnlMain.setBorder(new EmptyBorder(10, 10, 10, 10));
//			//pnlMain.setBorder(LINE_BORDER);
//			{
//				JPanel pnlNorth = new JPanel(new BorderLayout(3, 3));
//				pnlMain.add(pnlNorth, BorderLayout.NORTH);
//				pnlNorth.setBorder(LINE_BORDER);
//				{
//					lblWelcome = new JLabel("Welcome to the Application!");
//					pnlNorth.add(lblWelcome, BorderLayout.CENTER);
//					lblWelcome.setFont(new Font("Arial", Font.PLAIN, 25));
//
//				}
//			}
//			{
//				JPanel pnlCenter = new JPanel(new BorderLayout(3, 3));
//				pnlMain.add(pnlCenter, BorderLayout.CENTER);
//				pnlCenter.setBorder(LINE_BORDER);
//				{
//					lblLogInSignUp = new JLabel("Please log in or sign up to continue.");
//					pnlCenter.add(lblLogInSignUp, BorderLayout.CENTER);
//					lblLogInSignUp.setFont(new Font("Arial", Font.PLAIN, 16));
//				}
//			}
//			{
//				JPanel pnlSouth = new JPanel(new GridLayout(1, 2, 10, 0));
//				pnlMain.add(pnlSouth, BorderLayout.SOUTH);
//				{
//					btnLogin = new JButton("Log In");
//					pnlSouth.add(btnLogin);
//					btnLogin.setActionCommand("Login");
//					btnLogin.addActionListener(this);
//				}
//				{
//					btnSignUp = new JButton("Sign Up");
//					pnlSouth.add(btnSignUp);
//					btnSignUp.setActionCommand("SignUp");
//					btnSignUp.addActionListener(this);
//				}
//			}
//		}
//		
//	}
//	
//	//PUT CUSTOM METHODS HERE
//	
//	//ACTION PERFORMED METHOD
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		String actionCommand = e.getActionCommand();
//		
//		if (actionCommand.equals("Login")) {
//			// Open Login Frame
//			
//			loginFrame = new Login();
//			loginFrame.setVisible(true);
//			this.dispose(); // Close the main frame
//		} else if (actionCommand.equals("SignUp")) {
//			// Open Sign Up Frame (not implemented)
//			System.out.println("Sign Up button clicked. Sign Up frame not implemented.");
//		}
//
//	}
//	
//	public static void main(String[]  args) {
//		SwingUtilities.invokeLater(() -> {
//			new MainFrameLes().setVisible(true);
//		});
//	}
//
//}
