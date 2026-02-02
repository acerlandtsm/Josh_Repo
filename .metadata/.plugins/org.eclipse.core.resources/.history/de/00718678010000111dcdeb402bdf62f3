package josh;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class productsPage extends JPanel implements ActionListener {

	private JPanel pnlMain;
	private JLabel lblProducts;
	private JLabel lblSearch;
	private JTextField txtFieldSearch;
	private JButton btnRefresh;
	
	productsPage() {
		initGUI();
	}
	
	public void initGUI() {
		setVisible(false);
		setLayout(new BorderLayout());
		{
			pnlMain = new JPanel(new BorderLayout(3, 3));
			add(pnlMain, BorderLayout.CENTER);
			pnlMain.setBorder(new EmptyBorder(10, 10, 10, 10));
			{
				JPanel pnlNorth = new JPanel(new GridLayout(1,4,5,5));
				pnlMain.add(pnlNorth, BorderLayout.NORTH);
				{
					lblProducts = new JLabel("PRODUCTS");
					pnlNorth.add(lblProducts);
					lblProducts.setFont(new Font("Arial", Font.BOLD, 20));
				}
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
