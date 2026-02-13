package josh;

import java.sql.Connection;
import java.sql.Date;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import database.DBConnection;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class printReport {

	public static void printProductsJReport(String reportPath, String status, String quantity) {
		try {
			Connection conn = DBConnection.getConnection();
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);
			//InputStream rptStream = getClass().getResourceAsStream(reportPath);
			Map<String, Object> parameters = new java.util.HashMap<>();
			parameters.put("ActiveStatus", status);
			parameters.put("Quantity", quantity);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.setTitle("Products");
			viewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
			viewer.setLocationRelativeTo(null);
			viewer.setVisible(true);
            
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error printing report: " + e.getMessage());
		}
	}
	
	public static void printSalesJReport(String user, String category, String reportPath, java.util.Date startDate, java.util.Date endDate) {
		try {
			Connection conn = DBConnection.getConnection(); 
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);
			Map<String, Object> parameters = new java.util.HashMap<>();
			parameters.put("user", user);
			parameters.put("category", category);
			parameters.put("startDate", startDate);
			parameters.put("endDate", endDate);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
			JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.setTitle("Sales");
			viewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
			viewer.setLocationRelativeTo(null);
			viewer.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error printing report: " + e.getMessage());
		}
	}
}
