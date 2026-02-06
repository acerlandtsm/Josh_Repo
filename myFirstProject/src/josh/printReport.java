package josh;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import database.DBConnection;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;

public class printReport {

	static Boolean printJReport(String reportPath, Map<String, Object> param) {
		try {
			Connection conn = DBConnection.getConnection();
			JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);
			
            JasperPrintManager.printReport(jasperPrint, true);

            JOptionPane.showMessageDialog(null, "Started Printing!");
            return true;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error printing report: " + e.getMessage());
			return false;
		}
	}
}
