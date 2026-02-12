package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Database {
	
	public List<String> selectAllUsers() {
		List<String> userList = new ArrayList<>();
		String selectAllUser = "SELECT username FROM tbl_users";
		
		try {	Connection conn = DBConnection.getConnection();
				Statement stmt =  conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectAllUser);
				
				while (rs.next()) {
					String name = rs.getString("username");
					userList.add(name);
				}
				
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error, fetching data: " + e.getMessage());
		}
		return userList;
	}
	
	//SELECT * PRODUCTS
	public void selectAllProducts(DefaultTableModel model) {
		
		model.setRowCount(0);
		
		String selectAll = "SELECT * FROM tbl_products WHERE active_status = 'Y' ORDER BY product_id desc";
		
		try	{	Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectAll);
				
				while (rs.next()) {
					String product_id = rs.getString("product_id");
					String name = rs.getString("name");
					String category = rs.getString("category");
					String quantity = rs.getString("quantity");
					String price = rs.getString("price");
					
					model.addRow(new String[] {product_id, name, category, quantity, price});
				}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error, fetching data: " + e.getMessage());
		}
	}
	//SELECT * SALES
	public void selectAllSales(DefaultTableModel model) {
		
		model.setRowCount(0);
		
		String selectAll = "SELECT * FROM tbl_sales WHERE active_status = 'Y' ORDER BY sale_id desc";
		
		try	{	Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectAll);
		
				while (rs.next()) {
					String product_id = rs.getString("product_id");
					String name = rs.getString("product_name");
					String category = rs.getString("category");
					String price = rs.getString("product_price");
					String quantity = rs.getString("quantity_sold");
					String total = rs.getString("sold_price");
					String date = rs.getString("date_sold");
			
					model.addRow(new String[] {product_id, name, category, price, quantity, total, date});
				}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error, fetching data: " + e.getMessage());
		}
	}
	
	public static Boolean addProducts(String name, String category, int quantity, double price) {
		
		String insertInto = "INSERT INTO tbl_products (name, category, quantity, price) VALUES (?,?,?,?)";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(insertInto)) {
			
			stmt.setString(1, name);
			stmt.setString(2, category);
			stmt.setInt(3, quantity);
			stmt.setDouble(4, price);
			int rowsInserted = stmt.executeUpdate();
			return rowsInserted > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error ading product: " + e.getMessage());
			return false;
		}
	}
	
	// INSERT SALES UPDATE PRODUCTS
	public static Boolean addSales(String currentUser, int id, int quantity) {
	    String checkStock = "SELECT quantity FROM tbl_products WHERE product_id = ?";
	    String insertInto = "";
	    String updateProducts = "UPDATE tbl_products SET quantity = quantity - ? WHERE product_id = ?";
	    
	    try (Connection conn = DBConnection.getConnection()) {
	        conn.setAutoCommit(false);

	        try (PreparedStatement stmtCheck = conn.prepareStatement(checkStock)) {
	            stmtCheck.setInt(1, id);
	            ResultSet rs = stmtCheck.executeQuery();
	            
	            if (rs.next()) {
	                int currentStock = rs.getInt("quantity");
	                if (currentStock < quantity) {
	                    JOptionPane.showMessageDialog(null, "Insufficient stock! Available: " + currentStock);
	                    return false; 
	                }else {
	                	insertInto = "INSERT INTO tbl_sales (product_id, product_name, category, product_price, quantity_sold, sold_price, date_sold, created_by) " +
	                            	 "SELECT product_id, name, category, price, ?, (? * price), NOW(), ? " +
	                            	 "FROM tbl_products WHERE product_id = ?";
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "Product not found!");
	                return false;
	            }
	        }
	        try (PreparedStatement stmtInsert = conn.prepareStatement(insertInto);
	             PreparedStatement stmtUpdate = conn.prepareStatement(updateProducts)) {
	            
	            stmtInsert.setInt(1, quantity);
	            stmtInsert.setInt(2, quantity);
	            stmtInsert.setString(3, currentUser);
	            stmtInsert.setInt(4, id);
	            stmtInsert.executeUpdate();
	            
	            stmtUpdate.setInt(1, quantity);
	            stmtUpdate.setInt(2, id);
	            stmtUpdate.executeUpdate();
	            
	            conn.commit();
	            return true;
	            
	        } catch (Exception e) {
	            conn.rollback();
	            throw e;
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
	        return false;
	    }
	}
	
	public static Boolean updateProducts(int id, String name, String category, int quantity, double price) {
		
		String update = "UPDATE tbl_products SET name=?, category=?, quantity=?, price=? WHERE product_id=?";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(update)) {
			
			stmt.setString(1, name);
			stmt.setString(2, category);
			stmt.setInt(3, quantity);
			stmt.setDouble(4, price);
			stmt.setInt(5, id);
			
			int rowsInserted = stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
			return rowsInserted > 0; 
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error adding product: " + e.getMessage());
			return false;
		}
	}
	
	public static Boolean updateSales(int id, int quantity) {
		String update = "UPDATE tbl_sales s " +
						"SET quantity_sold = ?, " +
						"sold_price = (? * p.price) " +
						"FROM tbl_products p " +
						"WHERE s.product_id = p.product_id " +
						"AND s.product_id = ?";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(update)) {
			
			stmt.setInt(1, quantity);
			stmt.setInt(2, quantity);
			stmt.setInt(3, id);
			
			int rowsInserted = stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
			return rowsInserted > 0; 
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error updating product: " + e.getMessage());
			return false;
		}
	}
	
	public static Boolean setInactiveProducts(int id) {
		String setInactive = "UPDATE tbl_products SET active_status = '' WHERE product_id=?";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(setInactive)) {
			
			stmt.setInt(1, id);
			int rowsInserted = stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Data Removed Successfully!");
			return rowsInserted > 0; 
			
		}  catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error removing product: " + e.getMessage());
			return false;
		}
	}
	
	public static Boolean setInactiveSales (int id) {
		String setInactive = "UPDATE tbl_sales SET active_status = 'N' WHERE product_id = ? ";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(setInactive)) {
			
			stmt.setInt(1, id);
			int rowsInserted = stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Data Removed Successfully!");
			return rowsInserted > 0; 
			
		}  catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error removing sales: " + e.getMessage());
			return false;
		}
	}
}
