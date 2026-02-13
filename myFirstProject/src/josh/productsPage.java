package josh;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import database.Database;
public class productsPage extends JPanel implements ActionListener {

	private JPanel pnlMain;
	
	private Border outerBorder = BorderFactory.createLineBorder(Color.GRAY);
	private Border innerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
	
	private JLabel lblProducts;
	private JLabel lblID;
	private JLabel lblName;
	private JLabel lblCategory;
	private JLabel lblQuantity;
	private JLabel lblPrice;
	private JLabel lblForPrint;
	private JLabel lblForQuantityPrint;
	private JLabel lblForActiveStats;
	
	private JTextField txtFieldID;
	private JTextField txtFieldName;
	private JComboBox<String>  comboCategory;
	private JTextField txtFieldQuantity;
	private JTextField txtFieldPrice;
	private JTextField txtFieldForQuantityPrint;
	private JTextField txtFieldForActiveStats;
	
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
	private JTable tblProducts;
	
	private JPanel pnlPrint; 
	
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
				JPanel pnlNorth = new JPanel(new GridLayout(1, 2));
				pnlMain.add(pnlNorth, BorderLayout.NORTH);
				pnlNorth.setBorder(new EmptyBorder(10, 10, 10, 10));
				{
					{
						JPanel pnlTitle = new JPanel(new BorderLayout());
						pnlNorth.add(pnlTitle);
						{
							lblProducts = new JLabel("PRODUCTS");
							pnlTitle.add(lblProducts, BorderLayout.WEST);
							lblProducts.setFont(new Font("Arial", Font.BOLD, 20));
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
								lblName = new JLabel("NAME: ");
								pnlLabels.add(lblName);
							}
							{
								lblCategory = new JLabel("CATEGORY: ");
								pnlLabels.add(lblCategory);
							}
							{
								lblQuantity = new JLabel("QUANTITY: ");
								pnlLabels.add(lblQuantity);
							}
							{
								lblPrice = new JLabel("PRICE: ");
								pnlLabels.add(lblPrice);
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
								txtFieldName = new JTextField();
								pnlTxtFields.add(txtFieldName);
								txtFieldName.setEditable(false);
								txtFieldName.setFocusable(false);
							}
							{
								String[] comboList = {"Land", "Residential", "Commercial"};
								comboCategory = new JComboBox<>(comboList);
								pnlTxtFields.add(comboCategory);
								comboCategory.setEnabled(false);
								comboCategory.setSelectedItem(null);
							}
							{
								txtFieldQuantity = new JTextField();
								pnlTxtFields.add(txtFieldQuantity);
								txtFieldQuantity.setEditable(false);
								txtFieldQuantity.setFocusable(false);
							}
							{
								txtFieldPrice = new JTextField();
								pnlTxtFields.add(txtFieldPrice);
								txtFieldPrice.setEditable(false);
								txtFieldPrice.setFocusable(false);
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
								JPanel pnlQuantityWest = new JPanel(new GridLayout(3, 1, 0, 0));
								pnlQuantity.add(pnlQuantityWest);
								pnlQuantityWest.setBorder(new EmptyBorder(10, 10, 10, 10));
								{
									lblForQuantityPrint = new JLabel("Quantity:");
									pnlQuantityWest.add(lblForQuantityPrint);
								}
								{
									lblForActiveStats = new JLabel("Active Status:");
									pnlQuantityWest.add(lblForActiveStats);
								}
							}
							{
								JPanel pnlQuantityEast = new JPanel(new GridLayout(3, 1, 0, 0));
								pnlQuantity.add(pnlQuantityEast);
								pnlQuantityEast.setBorder(new EmptyBorder(10, 10, 10, 10));
								{
									txtFieldForQuantityPrint = new JTextField();
									pnlQuantityEast.add(txtFieldForQuantityPrint);
								}
								{
									txtFieldForActiveStats = new JTextField();
									pnlQuantityEast.add(txtFieldForActiveStats);
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
					String[] columnNames = {"ID", "NAME", "CATEGORY", "QUANTITY", "PRICE"};
					Database db = new Database();
					
					model = new DefaultTableModel(columnNames, 0) {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					db.selectAllProducts(model);
					tblProducts = new JTable(model);
					tblProducts.getColumnModel().getColumn(0).setPreferredWidth(30);
					tblProducts.getColumnModel().getColumn(1).setPreferredWidth(150);
					tblProducts.getColumnModel().getColumn(2).setPreferredWidth(90);
					tblProducts.getColumnModel().getColumn(3).setPreferredWidth(50);
					scrollPane = new JScrollPane(tblProducts);
					pnlCenter.add(scrollPane, BorderLayout.CENTER); 
					scrollPane.setBounds(30, 40, 200, 300);
					
					tblProducts.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent event) {
							if (!event.getValueIsAdjusting()) {
								int selectedRow = tblProducts.getSelectedRow();
								if(selectedRow != -1){
									btnUpdate.setEnabled(true);
									btnRemove.setEnabled(true);
									String id = tblProducts.getValueAt(selectedRow, 0).toString();
									String name = tblProducts.getValueAt(selectedRow, 1).toString();
									String category = tblProducts.getValueAt(selectedRow, 2).toString();
									String quantity = tblProducts.getValueAt(selectedRow, 3).toString();
									String price = tblProducts.getValueAt(selectedRow, 4).toString();
									
									txtFieldID.setText(id);
									txtFieldName.setText(name);
									comboCategory.setSelectedItem(category);
									txtFieldQuantity.setText(quantity);
									txtFieldPrice.setText(price);
									txtFieldName.setEditable(true);
									txtFieldName.setFocusable(true);
									comboCategory.setEnabled(true);
									txtFieldQuantity.setEditable(true);
									txtFieldQuantity.setFocusable(true);
									txtFieldPrice.setEditable(true);
									txtFieldPrice.setFocusable(true);
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
			txtFieldName.setEditable(true);
			txtFieldName.setFocusable(true);
			comboCategory.setEnabled(true);
			txtFieldQuantity.setEditable(true);
			txtFieldQuantity.setFocusable(true);
			txtFieldPrice.setEditable(true);
			txtFieldPrice.setFocusable(true);
			btnCancel.setEnabled(true);
			btnAdd.setEnabled(true);
			break;
		
		case "cancel": //CANCEL BUTTON
			txtFieldID.setText("");
			txtFieldName.setText("");
            comboCategory.setSelectedItem(null);
            txtFieldQuantity.setText("");
            txtFieldPrice.setText("");
			txtFieldName.setEditable(false);
			txtFieldName.setFocusable(false);
			comboCategory.setEditable(false);
			comboCategory.setFocusable(false);
			txtFieldQuantity.setEditable(false);
			txtFieldQuantity.setFocusable(false);
			txtFieldPrice.setEditable(false);
			txtFieldPrice.setFocusable(false);
			btnCancel.setEnabled(false);
			btnAdd.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnRemove.setEnabled(false);
			break;
		
		case "refresh":
			Database db = new Database();
			db.selectAllProducts(model);
			break;
			
		case "add": //ADD BUTTON
			String name = txtFieldName.getText();
			String category = (String) comboCategory.getSelectedItem();
			int quantity = Integer.parseInt(txtFieldQuantity.getText());
			double price = Double.parseDouble(txtFieldPrice.getText());
			
			if (Database.addProducts(name, category, quantity, price)) {
				
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Product Added Successfully!");
					db = new Database();
					db.selectAllProducts(model);
					
					txtFieldID.setText("");
					txtFieldName.setText("");
		            comboCategory.setSelectedItem(null);
		            txtFieldQuantity.setText("");
		            txtFieldPrice.setText("");
					txtFieldName.setEditable(false);
					txtFieldName.setFocusable(false);
					comboCategory.setEditable(false);
					comboCategory.setFocusable(false);
					txtFieldQuantity.setEditable(false);
					txtFieldQuantity.setFocusable(false);
					txtFieldPrice.setEditable(false);
					txtFieldPrice.setFocusable(false);
					btnAdd.setEnabled(false);
					btnUpdate.setEnabled(false);
					btnRemove.setEnabled(false);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Could not complete process, please try again.");
			}
			break;
			
		case "update": //EDIT BUTTON
			int id = Integer.parseInt(txtFieldID.getText());
			String txtName = txtFieldName.getText();
			String txtCategory = (String) comboCategory.getSelectedItem();
			int txtQuantity = Integer.parseInt(txtFieldQuantity.getText());
			double txtPrice = Double.parseDouble(txtFieldPrice.getText());
			
			int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to continue and update?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				if (Database.updateProducts(id, txtName, txtCategory, txtQuantity, txtPrice)) {
					db = new Database();
					db.selectAllProducts(model);
					
					txtFieldID.setText("");
					txtFieldName.setText("");
		            comboCategory.setSelectedItem(null);
		            txtFieldQuantity.setText("");
		            txtFieldPrice.setText("");
					txtFieldName.setEditable(false);
					txtFieldName.setFocusable(false);
					comboCategory.setEditable(false);
					comboCategory.setFocusable(false);
					txtFieldQuantity.setEditable(false);
					txtFieldQuantity.setFocusable(false);
					txtFieldPrice.setEditable(false);
					txtFieldPrice.setFocusable(false);
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
				if (Database.setInactiveProducts(id)) {
					db = new Database();
					db.selectAllProducts(model);
					
					txtFieldID.setText("");
					txtFieldName.setText("");
		            comboCategory.setSelectedItem(null);
		            txtFieldQuantity.setText("");
		            txtFieldPrice.setText("");
					txtFieldName.setEditable(false);
					txtFieldName.setFocusable(false);
					comboCategory.setEditable(false);
					comboCategory.setFocusable(false);
					txtFieldQuantity.setEditable(false);
					txtFieldQuantity.setFocusable(false);
					txtFieldPrice.setEditable(false);
					txtFieldPrice.setFocusable(false);
					btnAdd.setEnabled(false);
					btnUpdate.setEnabled(false);
					btnRemove.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "Could not complete process, please try again.");
				}
				break;
			}
			break;
		
		case "preview": // PREVIEW OPTION

			//String productsReportPath = "/home/mboriga/git/sample/myFirstProject/src/Reports/MyReports/MyProductReport.jasper";
			String productsReportPath = System.getProperty("user.dir") + "/src/Reports/MyReports/MyProductReport.jasper";
			
			String activeStatus = txtFieldForActiveStats.getText();
			String quantityNo = txtFieldForQuantityPrint.getText();
			try {
				printReport.printProductsJReport(productsReportPath, activeStatus, quantityNo);
				pnlPrint.setVisible(false);
				break;
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Entry cannot be empty!");
				break;
			}
		}
	}
}
