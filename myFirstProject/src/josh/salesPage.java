package josh;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import database.Database;

public class salesPage extends JPanel implements ActionListener {
	private JPanel pnlMain;
	
	private Border outerBorder = BorderFactory.createLineBorder(Color.GRAY);
	private Border innerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
	
	private JLabel lblSales;
	private JLabel lblID;
	private JLabel lblQuantitySold;
	private JLabel lblForPrint;
	private JLabel lblUserToPrint;
	private JLabel lblCategory;
	private JLabel lblStartDate;
	private JLabel lblEndDate;
	
	private JDateChooser calStartDate;
	private JDateChooser calEndDate;

	private JComboBox<String> comboUsers;
	private JComboBox<String> comboCategory;
	
	private JTextField txtFieldID;
	private JTextField txtFieldQuantitySold;
	
	private JButton btnRefresh;
	private JButton btnAddNew;
	private JButton btnCancel;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnRemove;
	
	private JButton btnPrint;
	private JButton btnPreview;
	
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JTable tblSales;
	
	private JPanel pnlPrint; 
	salesPage() {
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
				JPanel pnlNorth = new JPanel(new GridLayout(1, 2));
				pnlMain.add(pnlNorth, BorderLayout.NORTH);
				pnlNorth.setBorder(new EmptyBorder(10, 10, 10, 10));
				{
					{
						JPanel pnlTitle = new JPanel(new BorderLayout());
						pnlNorth.add(pnlTitle);
						{
							lblSales = new JLabel("SALES");
							pnlTitle.add(lblSales, BorderLayout.WEST);
							lblSales.setFont(new Font("Arial", Font.BOLD, 20));
						}
					}
					{
						JPanel pnlButtons = new JPanel(new GridLayout(1,4));
						pnlNorth.add(pnlButtons);
						{
							btnPrint = new JButton("Print");
							pnlButtons.add(btnPrint);
							btnPrint.setActionCommand("print");
							btnPrint.addActionListener(this);
						}
						{
							btnAddNew = new JButton("Add new");
							pnlButtons.add(btnAddNew);
							btnAddNew.setActionCommand("addNew");
							btnAddNew.addActionListener(this);
						}
						{
							btnCancel = new JButton("Cancel");
							pnlButtons.add(btnCancel);
							btnCancel.setActionCommand("cancel");
							btnCancel.addActionListener(this);
							btnCancel.setEnabled(false);
						}
						{
							btnRefresh = new JButton("Refresh");
							pnlButtons.add(btnRefresh);
							btnRefresh.setActionCommand("refresh");
							btnRefresh.addActionListener(this);
						}
					}

				}
			}
			{
				JPanel pnlWest = new JPanel(new BorderLayout());
				pnlMain.add(pnlWest, BorderLayout.WEST);
				pnlWest.setBorder(new EmptyBorder(10, 10, 10, 10));
				pnlWest.setPreferredSize(new Dimension(350, 0));
				{
					JPanel pnlProductForm = new JPanel(new BorderLayout());
					pnlWest.add(pnlProductForm, BorderLayout.NORTH);
					pnlProductForm.setBorder(outerBorder);
					{
						{
							JPanel pnlLabels = new JPanel(new GridLayout(0, 1, 0, 15));
							pnlProductForm.add(pnlLabels, BorderLayout.WEST);
							pnlLabels.setBorder(new EmptyBorder(10, 10, 10, 10));
							{
								lblID = new JLabel("ID: ");
								pnlLabels.add(lblID);
							}
							{
								lblQuantitySold = new JLabel("QUANTITY SOLD: ");
								pnlLabels.add(lblQuantitySold);
							}
						}
						{
							JPanel pnlTxtFields = new JPanel(new GridLayout(0, 1));
							pnlProductForm.add(pnlTxtFields, BorderLayout.CENTER);
							pnlTxtFields.setBorder(new EmptyBorder(10, 10, 10, 10));
							{
								txtFieldID = new JTextField();
								pnlTxtFields.add(txtFieldID);
								txtFieldID.setEditable(false);
								txtFieldID.setFocusable(false);
							}
							{
								txtFieldQuantitySold = new JTextField();
								pnlTxtFields.add(txtFieldQuantitySold);
								txtFieldQuantitySold.setEditable(false);
								txtFieldQuantitySold.setFocusable(false);
							}
						}
						{
							JPanel pnlButtons = new JPanel(new GridLayout(1, 5));
							pnlProductForm.add(pnlButtons, BorderLayout.SOUTH);
							pnlButtons.setBorder(new CompoundBorder(outerBorder, innerBorder));
							{
								btnAdd = new JButton("Add");
								pnlButtons.add(btnAdd);
								btnAdd.setActionCommand("add");
								btnAdd.addActionListener(this);
								btnAdd.setEnabled(false);
							}
							{
								btnUpdate = new JButton("Update");
								pnlButtons.add(btnUpdate);
								btnUpdate.setActionCommand("update");
								btnUpdate.addActionListener(this);
								btnUpdate.setEnabled(false);
							}
							{
								btnRemove = new JButton("Remove");
								pnlButtons.add(btnRemove);
								btnRemove.setActionCommand("remove");
								btnRemove.addActionListener(this);
								btnRemove.setEnabled(false);
							}
						}
					}
				}
				{
					pnlPrint = new JPanel(new BorderLayout());
					pnlWest.add(pnlPrint, BorderLayout.SOUTH);
					pnlPrint.setBorder(new CompoundBorder(outerBorder, innerBorder));
					pnlPrint.setVisible(false);
					{
						lblForPrint = new JLabel("PRINT");
						pnlPrint.add(lblForPrint, BorderLayout.NORTH);
					}
					{
						JPanel pnlQuantity = new JPanel(new GridLayout(0,2));
						pnlPrint.add(pnlQuantity, BorderLayout.CENTER);
						pnlQuantity.setBorder(new EmptyBorder(10, 10, 10, 10));
						{
							{
								JPanel pnlQuantityWest = new JPanel(new GridLayout(5, 1, 0, 0));
								pnlQuantity.add(pnlQuantityWest);
								pnlQuantityWest.setBorder(new EmptyBorder(10, 10, 10, 10));
								{
									lblUserToPrint = new JLabel("User Filter:");
									pnlQuantityWest.add(lblUserToPrint);
								}
								{
									lblCategory = new JLabel("Category:");
									pnlQuantityWest.add(lblCategory);
								}
								{
									lblStartDate = new JLabel("Start Date:");
									pnlQuantityWest.add(lblStartDate);
								}
								{
									lblEndDate = new JLabel("End Date:");
									pnlQuantityWest.add(lblEndDate);
								}
							}
							{
								JPanel pnlQuantityEast = new JPanel(new GridLayout(5, 1, 0, 0));
								pnlQuantity.add(pnlQuantityEast);
								pnlQuantityEast.setBorder(new EmptyBorder(10, 10, 10, 10));
								{
									comboUsers = new JComboBox<String>();
									pnlQuantityEast.add(comboUsers);
									Database db = new Database();
									comboUsers.addItem("ALL");
									comboUsers.setSelectedItem(null);
									List<String> allUsers = db.selectAllUsers();
									for (String user : allUsers) {
										comboUsers.addItem(user);
									}
								}
								{
									String[] comboList = {"Land", "Residential", "Commercial"};
									comboCategory = new JComboBox<>(comboList);
									pnlQuantityEast.add(comboCategory);
									comboCategory.addItem("ALL");
									comboCategory.setSelectedItem(null);
								}
								{
									calStartDate = new JDateChooser();
									pnlQuantityEast.add(calStartDate);
								}
								{
									calEndDate = new JDateChooser();
									pnlQuantityEast.add(calEndDate);
								}
							}
						}
					}
					{
						btnPreview = new JButton("Preview");
						pnlPrint.add(btnPreview, BorderLayout.SOUTH);
						btnPreview.setActionCommand("preview");
						btnPreview.addActionListener(this);
					}
				}
			}
			{
				JPanel pnlCenter = new JPanel(new BorderLayout());
				pnlMain.add(pnlCenter);
				pnlCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
				{
					String[] columnNames = {"ID", "NAME", "CATEGORY", "PRICE", "QUANTITY", "TOTAL", "DATE"};
					Database db = new Database();
					
					model = new DefaultTableModel(columnNames, 0) {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					db.selectAllSales(model);
					tblSales = new JTable(model);
					tblSales.getColumnModel().getColumn(0).setPreferredWidth(30);
					tblSales.getColumnModel().getColumn(4).setPreferredWidth(70);
					scrollPane = new JScrollPane(tblSales);
					pnlCenter.add(scrollPane, BorderLayout.CENTER); 
					scrollPane.setBounds(30, 40, 200, 300);
					
					tblSales.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent event) {
							if (!event.getValueIsAdjusting()) {
								int selectedRow = tblSales.getSelectedRow();
								if(selectedRow != -1){
									btnUpdate.setEnabled(true);
									btnRemove.setEnabled(true);
									String id = tblSales.getValueAt(selectedRow, 0).toString();
									String quantity = tblSales.getValueAt(selectedRow, 4).toString();
									
									txtFieldID.setText(id);
									txtFieldQuantitySold.setText(quantity);
									txtFieldQuantitySold.setEditable(true);
									txtFieldQuantitySold.setFocusable(true);
									btnCancel.setEnabled(true);
								}
							}
						}
					});
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		switch (actionCommand) {
		
		case "print": // OPENS PREVIEW OPTION
			pnlPrint.setVisible(true);
			break;
		
		case "addNew": //ADD NEW, BUTTON
			txtFieldID.setEditable(true);
			txtFieldID.setFocusable(true);
			txtFieldQuantitySold.setEditable(true);
			txtFieldQuantitySold.setFocusable(true);
			btnCancel.setEnabled(true);
			btnAdd.setEnabled(true);
			break;
			
		case "cancel": //CANCEL BUTTON
			txtFieldID.setText("");
			txtFieldID.setEditable(false);
			txtFieldID.setFocusable(false);
			txtFieldQuantitySold.setText("");
			txtFieldQuantitySold.setEditable(false);
			txtFieldQuantitySold.setFocusable(false);
			btnCancel.setEnabled(false);
			btnAdd.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnRemove.setEnabled(false);
			break;
		
		case "refresh":
			Database db = new Database();
			db.selectAllSales(model);
			break;
			
		case "add": //ADD BUTTON
			String currentUser = userSession.getUsername();
			int id = Integer.parseInt(txtFieldID.getText());
			int quantity = Integer.parseInt(txtFieldQuantitySold.getText());
			
			if (Database.addSales(currentUser, id, quantity)) {
				
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Product Added Successfully!");
					db = new Database();
					db.selectAllSales(model);
					
					txtFieldID.setText("");
					txtFieldID.setEditable(false);
					txtFieldID.setFocusable(false);
					txtFieldQuantitySold.setText("");
					txtFieldQuantitySold.setEditable(false);
					txtFieldQuantitySold.setFocusable(false);
					btnCancel.setEnabled(false);
					btnAdd.setEnabled(false);
					btnUpdate.setEnabled(false);
					btnRemove.setEnabled(false);
					break;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Could not complete process, please try again.");
			}
			break;
			
		case "update": //EDIT BUTTON
			id = Integer.parseInt(txtFieldID.getText());
			quantity = Integer.parseInt(txtFieldQuantitySold.getText());
			
			int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to continue and update?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				if (Database.updateSales(id, quantity)) {
					db = new Database();
					db.selectAllSales(model);
					
					txtFieldID.setText("");
		            txtFieldQuantitySold.setText("");
					txtFieldID.setEditable(false);
					txtFieldID.setFocusable(false);
					txtFieldQuantitySold.setEditable(false);
					txtFieldQuantitySold.setFocusable(false);
					btnAdd.setEnabled(false);
					btnUpdate.setEnabled(false);
					btnRemove.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "Could not complete process, please try again.");
				}
				break;
			}
			break;
			
		case "remove":
			id = Integer.parseInt(txtFieldID.getText());
			
			choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to continue and remove?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				if (Database.setInactiveSales(id)) {
					db = new Database();
					db.selectAllSales(model);
					
					txtFieldID.setText("");
		            txtFieldQuantitySold.setText("");
					txtFieldID.setEditable(false);
					txtFieldID.setFocusable(false);
					txtFieldQuantitySold.setEditable(false);
					txtFieldQuantitySold.setFocusable(false);
					btnAdd.setEnabled(false);
					btnUpdate.setEnabled(false);
					btnRemove.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "Could not complete process, please try again.");
				}
				break;
			}
			
		case "preview":
			String user = (String) comboUsers.getSelectedItem();
			String category = (String) comboCategory.getSelectedItem();
			String salesReportPath = "/home/mboriga/git/sample/myFirstProject/src/Reports/MyReports/MySalesReport.jasper";
			Date startDate = calStartDate.getDate();
			Date endDate = calEndDate.getDate();
			
			try {
				printReport.printSalesJReport(user, category, salesReportPath, startDate, endDate);
				pnlPrint.setVisible(false);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Entry cannot be empty!");
			}
			break;
		}
	}

}
